package com.facebook.stetho.inspector.protocol.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.Response;
import com.facebook.internal.ServerProtocol;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DatabasePeerManager;
import com.facebook.stetho.inspector.database.DatabasePeerManager.ExecuteResultHandler;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

@TargetApi(11)
public class Database implements ChromeDevtoolsDomain {
    private static final int MAX_EXECUTE_RESULTS = 250;
    private final DatabasePeerManager mDatabasePeerManager;
    private final ObjectMapper mObjectMapper = new ObjectMapper();

    /* renamed from: com.facebook.stetho.inspector.protocol.module.Database$1 */
    class C20121 implements ExecuteResultHandler<ExecuteSQLResponse> {
        C20121() {
        }

        public ExecuteSQLResponse handleRawQuery() throws SQLiteException {
            ExecuteSQLResponse response = new ExecuteSQLResponse();
            response.columnNames = Arrays.asList(new String[]{Response.SUCCESS_KEY});
            response.values = Arrays.asList(new Object[]{ServerProtocol.DIALOG_RETURN_SCOPES_TRUE});
            return response;
        }

        public ExecuteSQLResponse handleSelect(Cursor result) throws SQLiteException {
            ExecuteSQLResponse response = new ExecuteSQLResponse();
            response.columnNames = Arrays.asList(result.getColumnNames());
            response.values = Database.this.flattenRows(result, 250);
            return response;
        }

        public ExecuteSQLResponse handleInsert(long insertedId) throws SQLiteException {
            ExecuteSQLResponse response = new ExecuteSQLResponse();
            response.columnNames = Arrays.asList(new String[]{"ID of last inserted row"});
            response.values = Arrays.asList(new Object[]{Long.valueOf(insertedId)});
            return response;
        }

        public ExecuteSQLResponse handleUpdateDelete(int count) throws SQLiteException {
            ExecuteSQLResponse response = new ExecuteSQLResponse();
            response.columnNames = Arrays.asList(new String[]{"Modified rows"});
            response.values = Arrays.asList(new Object[]{Integer.valueOf(count)});
            return response;
        }
    }

    public static class AddDatabaseEvent {
        @JsonProperty(required = true)
        public DatabaseObject database;
    }

    public static class DatabaseObject {
        @JsonProperty(required = true)
        public String domain;
        @JsonProperty(required = true)
        /* renamed from: id */
        public String f6030id;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String version;
    }

    public static class Error {
        @JsonProperty(required = true)
        public int code;
        @JsonProperty(required = true)
        public String message;
    }

    private static class ExecuteSQLRequest {
        @JsonProperty(required = true)
        public String databaseId;
        @JsonProperty(required = true)
        public String query;

        private ExecuteSQLRequest() {
        }
    }

    private static class ExecuteSQLResponse implements JsonRpcResult {
        @JsonProperty
        public List<String> columnNames;
        @JsonProperty
        public Error sqlError;
        @JsonProperty
        public List<Object> values;

        private ExecuteSQLResponse() {
        }

        /* synthetic */ ExecuteSQLResponse(C20121 x0) {
            this();
        }
    }

    private static class GetDatabaseTableNamesRequest {
        @JsonProperty(required = true)
        public String databaseId;

        private GetDatabaseTableNamesRequest() {
        }
    }

    private static class GetDatabaseTableNamesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<String> tableNames;

        private GetDatabaseTableNamesResponse() {
        }

        /* synthetic */ GetDatabaseTableNamesResponse(C20121 x0) {
            this();
        }
    }

    @Deprecated
    public Database(Context context) {
        this.mDatabasePeerManager = new DatabasePeerManager(context);
    }

    public Database(Context context, DatabaseFilesProvider databaseFilesProvider) {
        this.mDatabasePeerManager = new DatabasePeerManager(context, databaseFilesProvider);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
        this.mDatabasePeerManager.addPeer(peer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
        this.mDatabasePeerManager.removePeer(peer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDatabaseTableNames(JsonRpcPeer peer, JSONObject params) throws JsonRpcException {
        GetDatabaseTableNamesRequest request = (GetDatabaseTableNamesRequest) this.mObjectMapper.convertValue(params, GetDatabaseTableNamesRequest.class);
        try {
            GetDatabaseTableNamesResponse response = new GetDatabaseTableNamesResponse();
            response.tableNames = this.mDatabasePeerManager.getDatabaseTableNames(request.databaseId);
            return response;
        } catch (SQLiteException e) {
            throw new JsonRpcException(new JsonRpcError(ErrorCode.INVALID_REQUEST, e.toString(), null));
        }
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult executeSQL(JsonRpcPeer peer, JSONObject params) {
        ExecuteSQLRequest request = (ExecuteSQLRequest) this.mObjectMapper.convertValue(params, ExecuteSQLRequest.class);
        try {
            return (JsonRpcResult) this.mDatabasePeerManager.executeSQL(request.databaseId, request.query, new C20121());
        } catch (SQLiteException e) {
            Error error = new Error();
            error.code = 0;
            error.message = e.getMessage();
            ExecuteSQLResponse response = new ExecuteSQLResponse();
            response.sqlError = error;
            return response;
        }
    }

    private List<Object> flattenRows(Cursor cursor, int limit) {
        int column;
        Util.throwIfNot(limit >= 0);
        List<Object> flatList = new ArrayList();
        int numColumns = cursor.getColumnCount();
        for (int row = 0; row < limit && cursor.moveToNext(); row++) {
            for (column = 0; column < numColumns; column++) {
                switch (cursor.getType(column)) {
                    case 0:
                        flatList.add(null);
                        break;
                    case 1:
                        flatList.add(Long.valueOf(cursor.getLong(column)));
                        break;
                    case 2:
                        flatList.add(Double.valueOf(cursor.getDouble(column)));
                        break;
                    case 4:
                        flatList.add(cursor.getBlob(column));
                        break;
                    default:
                        flatList.add(cursor.getString(column));
                        break;
                }
            }
        }
        if (!cursor.isAfterLast()) {
            for (column = 0; column < numColumns; column++) {
                flatList.add("{truncated}");
            }
        }
        return flatList;
    }
}
