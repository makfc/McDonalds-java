package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.app.Fragment;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphObjectException;
import com.facebook.NativeAppCallAttachmentStore;
import com.facebook.NativeAppCallContentProvider;
import com.facebook.Settings;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.DialogFeatureConfig;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import com.facebook.model.GraphObjectList;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookDialog {
    public static final String COMPLETION_GESTURE_CANCEL = "cancel";
    private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
    public static final String RESULT_ARGS_DIALOG_COMPLETE_KEY = "didComplete";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY = "completionGesture";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_ID_KEY = "postId";
    private static NativeAppCallAttachmentStore attachmentStore;
    private Activity activity;
    private PendingCall appCall;
    private Fragment fragment;
    private OnPresentCallback onPresentCallback;

    public interface Callback {
        void onComplete(PendingCall pendingCall, Bundle bundle);

        void onError(PendingCall pendingCall, Exception exception, Bundle bundle);
    }

    public static abstract class Builder<CONCRETE extends Builder<?>> {
        protected final Activity activity;
        protected final PendingCall appCall;
        protected final String applicationId;
        protected String applicationName;
        protected Fragment fragment;
        protected HashMap<String, Bitmap> imageAttachments = new HashMap();
        protected HashMap<String, File> mediaAttachmentFiles = new HashMap();

        /* renamed from: com.facebook.widget.FacebookDialog$Builder$1 */
        class C19571 implements OnPresentCallback {
            C19571() {
            }

            public void onPresent(Context context) throws Exception {
                if (Builder.this.imageAttachments != null && Builder.this.imageAttachments.size() > 0) {
                    FacebookDialog.getAttachmentStore().addAttachmentsForCall(context, Builder.this.appCall.getCallId(), Builder.this.imageAttachments);
                }
                if (Builder.this.mediaAttachmentFiles != null && Builder.this.mediaAttachmentFiles.size() > 0) {
                    FacebookDialog.getAttachmentStore().addAttachmentFilesForCall(context, Builder.this.appCall.getCallId(), Builder.this.mediaAttachmentFiles);
                }
            }
        }

        public abstract EnumSet<? extends DialogFeature> getDialogFeatures();

        public abstract Bundle getMethodArguments();

        public Builder(Activity activity) {
            Validate.notNull(activity, "activity");
            this.activity = activity;
            this.applicationId = Utility.getMetadataApplicationId(activity);
            this.appCall = new PendingCall((int) NativeProtocol.DIALOG_REQUEST_CODE);
        }

        public CONCRETE setRequestCode(int requestCode) {
            this.appCall.setRequestCode(requestCode);
            return this;
        }

        public CONCRETE setApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public CONCRETE setFragment(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public FacebookDialog build() {
            Bundle extras;
            validate();
            String action = FacebookDialog.getActionForFeatures(getDialogFeatures());
            int protocolVersion = FacebookDialog.getProtocolVersionForNativeDialog(this.activity, action, FacebookDialog.getVersionSpecForFeatures(this.applicationId, action, getDialogFeatures()));
            if (NativeProtocol.isVersionCompatibleWithBucketedIntent(protocolVersion)) {
                extras = getMethodArguments();
            } else {
                extras = setBundleExtras(new Bundle());
            }
            Intent intent = NativeProtocol.createPlatformActivityIntent(this.activity, this.appCall.getCallId().toString(), action, protocolVersion, this.applicationName, extras);
            if (intent == null) {
                FacebookDialog.logDialogActivity(this.activity, this.fragment, FacebookDialog.getEventName(action, extras.containsKey(NativeProtocol.EXTRA_PHOTOS), false), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED);
                throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
            }
            this.appCall.setRequestIntent(intent);
            return new FacebookDialog(this.activity, this.fragment, this.appCall, getOnPresentCallback());
        }

        /* Access modifiers changed, original: protected */
        public String getWebFallbackUrlInternal() {
            String featureName = null;
            String action = null;
            Iterator i$ = getDialogFeatures().iterator();
            if (i$.hasNext()) {
                DialogFeature feature = (DialogFeature) i$.next();
                featureName = feature.name();
                action = feature.getAction();
            }
            DialogFeatureConfig config = Utility.getDialogFeatureConfig(this.applicationId, action, featureName);
            if (config == null) {
                return null;
            }
            Uri fallbackUrl = config.getFallbackUrl();
            if (fallbackUrl == null) {
                return null;
            }
            Bundle methodArguments = getMethodArguments();
            Bundle webParams = ServerProtocol.getQueryParamsForPlatformActivityIntentWebFallback(this.activity, this.appCall.getCallId().toString(), NativeProtocol.getLatestKnownVersion(), this.applicationName, methodArguments);
            if (webParams == null) {
                return null;
            }
            if (fallbackUrl.isRelative()) {
                fallbackUrl = Utility.buildUri(ServerProtocol.getDialogAuthority(), fallbackUrl.toString(), webParams);
            }
            return fallbackUrl.toString();
        }

        public boolean canPresent() {
            return FacebookDialog.handleCanPresent(this.activity, getDialogFeatures());
        }

        /* Access modifiers changed, original: 0000 */
        public void validate() {
        }

        /* Access modifiers changed, original: 0000 */
        public OnPresentCallback getOnPresentCallback() {
            return new C19571();
        }

        /* Access modifiers changed, original: protected */
        public List<String> addImageAttachments(Collection<Bitmap> bitmaps) {
            ArrayList<String> attachmentUrls = new ArrayList();
            for (Bitmap bitmap : bitmaps) {
                String attachmentName = UUID.randomUUID().toString();
                addImageAttachment(attachmentName, bitmap);
                attachmentUrls.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), attachmentName));
            }
            return attachmentUrls;
        }

        /* Access modifiers changed, original: protected */
        public List<String> addImageAttachmentFiles(Collection<File> bitmapFiles) {
            ArrayList<String> attachmentUrls = new ArrayList();
            for (File bitmapFile : bitmapFiles) {
                String attachmentName = UUID.randomUUID().toString();
                addImageAttachment(attachmentName, bitmapFile);
                attachmentUrls.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), attachmentName));
            }
            return attachmentUrls;
        }

        /* Access modifiers changed, original: protected */
        public String addVideoAttachmentFile(File videoFile) {
            String attachmentName = UUID.randomUUID().toString();
            addVideoAttachment(attachmentName, videoFile);
            return NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), attachmentName);
        }

        /* Access modifiers changed, original: 0000 */
        public List<String> getImageAttachmentNames() {
            return new ArrayList(this.imageAttachments.keySet());
        }

        /* Access modifiers changed, original: protected */
        public Bundle setBundleExtras(Bundle extras) {
            return extras;
        }

        /* Access modifiers changed, original: protected */
        public void putExtra(Bundle extras, String key, String value) {
            if (value != null) {
                extras.putString(key, value);
            }
        }

        /* Access modifiers changed, original: protected */
        public CONCRETE addImageAttachment(String imageName, Bitmap bitmap) {
            this.imageAttachments.put(imageName, bitmap);
            return this;
        }

        /* Access modifiers changed, original: protected */
        public CONCRETE addImageAttachment(String imageName, File attachment) {
            this.mediaAttachmentFiles.put(imageName, attachment);
            return this;
        }

        /* Access modifiers changed, original: protected */
        public CONCRETE addVideoAttachment(String videoName, File attachment) {
            this.mediaAttachmentFiles.put(videoName, attachment);
            return this;
        }
    }

    public interface DialogFeature {
        String getAction();

        int getMinVersion();

        String name();
    }

    interface OnPresentCallback {
        void onPresent(Context context) throws Exception;
    }

    private static abstract class ShareDialogBuilderBase<CONCRETE extends ShareDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private String caption;
        private boolean dataErrorsFatal;
        private String description;
        private ArrayList<String> friends;
        protected String link;
        private String name;
        private String picture;
        private String place;
        private String ref;

        public ShareDialogBuilderBase(Activity activity) {
            super(activity);
        }

        public CONCRETE setName(String name) {
            this.name = name;
            return this;
        }

        public CONCRETE setCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public CONCRETE setDescription(String description) {
            this.description = description;
            return this;
        }

        public CONCRETE setLink(String link) {
            this.link = link;
            return this;
        }

        public CONCRETE setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public CONCRETE setPlace(String place) {
            this.place = place;
            return this;
        }

        public CONCRETE setFriends(List<String> friends) {
            this.friends = friends == null ? null : new ArrayList(friends);
            return this;
        }

        public CONCRETE setRef(String ref) {
            this.ref = ref;
            return this;
        }

        public CONCRETE setDataErrorsFatal(boolean dataErrorsFatal) {
            this.dataErrorsFatal = dataErrorsFatal;
            return this;
        }

        /* Access modifiers changed, original: protected */
        public Bundle setBundleExtras(Bundle extras) {
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_NAME, this.applicationName);
            putExtra(extras, NativeProtocol.EXTRA_TITLE, this.name);
            putExtra(extras, NativeProtocol.EXTRA_SUBTITLE, this.caption);
            putExtra(extras, NativeProtocol.EXTRA_DESCRIPTION, this.description);
            putExtra(extras, NativeProtocol.EXTRA_LINK, this.link);
            putExtra(extras, NativeProtocol.EXTRA_IMAGE, this.picture);
            putExtra(extras, NativeProtocol.EXTRA_PLACE_TAG, this.place);
            putExtra(extras, NativeProtocol.EXTRA_REF, this.ref);
            extras.putBoolean(NativeProtocol.EXTRA_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            if (!Utility.isNullOrEmpty(this.friends)) {
                extras.putStringArrayList(NativeProtocol.EXTRA_FRIEND_TAGS, this.friends);
            }
            return extras;
        }

        /* Access modifiers changed, original: protected */
        public Bundle getMethodArguments() {
            Bundle methodArguments = new Bundle();
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_TITLE, this.name);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_SUBTITLE, this.caption);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_DESCRIPTION, this.description);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_LINK, this.link);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_IMAGE, this.picture);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_REF, this.ref);
            methodArguments.putBoolean(NativeProtocol.METHOD_ARGS_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            if (!Utility.isNullOrEmpty(this.friends)) {
                methodArguments.putStringArrayList(NativeProtocol.METHOD_ARGS_FRIEND_TAGS, this.friends);
            }
            return methodArguments;
        }
    }

    public static class MessageDialogBuilder extends ShareDialogBuilderBase<MessageDialogBuilder> {
        public MessageDialogBuilder(Activity activity) {
            super(activity);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG);
        }

        public MessageDialogBuilder setPlace(String place) {
            return this;
        }

        public MessageDialogBuilder setFriends(List<String> list) {
            return this;
        }
    }

    public enum MessageDialogFeature implements DialogFeature {
        MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204),
        PHOTOS(NativeProtocol.PROTOCOL_VERSION_20140324),
        VIDEO(NativeProtocol.PROTOCOL_VERSION_20141218);
        
        private int minVersion;

        private MessageDialogFeature(int minVersion) {
            this.minVersion = minVersion;
        }

        public String getAction() {
            return NativeProtocol.ACTION_MESSAGE_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    private static abstract class OpenGraphDialogBuilderBase<CONCRETE extends OpenGraphDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private OpenGraphAction action;
        private String actionType;
        private boolean dataErrorsFatal;
        private String previewPropertyName;

        @Deprecated
        public OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String actionType, String previewPropertyName) {
            super(activity);
            Validate.notNull(action, Parameters.ACTION);
            Validate.notNullOrEmpty(actionType, "actionType");
            Validate.notNullOrEmpty(previewPropertyName, "previewPropertyName");
            if (action.getProperty(previewPropertyName) == null) {
                throw new IllegalArgumentException("A property named \"" + previewPropertyName + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
            }
            String typeOnAction = action.getType();
            if (Utility.isNullOrEmpty(typeOnAction) || typeOnAction.equals(actionType)) {
                this.action = action;
                this.actionType = actionType;
                this.previewPropertyName = previewPropertyName;
                return;
            }
            throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
        }

        public OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) {
            super(activity);
            Validate.notNull(action, Parameters.ACTION);
            Validate.notNullOrEmpty(action.getType(), "action.getType()");
            Validate.notNullOrEmpty(previewPropertyName, "previewPropertyName");
            if (action.getProperty(previewPropertyName) == null) {
                throw new IllegalArgumentException("A property named \"" + previewPropertyName + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
            }
            this.action = action;
            this.actionType = action.getType();
            this.previewPropertyName = previewPropertyName;
        }

        public CONCRETE setDataErrorsFatal(boolean dataErrorsFatal) {
            this.dataErrorsFatal = dataErrorsFatal;
            return this;
        }

        public CONCRETE setImageAttachmentsForAction(List<Bitmap> bitmaps) {
            return setImageAttachmentsForAction(bitmaps, false);
        }

        public CONCRETE setImageAttachmentsForAction(List<Bitmap> bitmaps, boolean isUserGenerated) {
            Validate.containsNoNulls(bitmaps, "bitmaps");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateActionAttachmentUrls(addImageAttachments(bitmaps), isUserGenerated);
            return this;
        }

        public CONCRETE setImageAttachmentFilesForAction(List<File> bitmapFiles) {
            return setImageAttachmentFilesForAction(bitmapFiles, false);
        }

        public CONCRETE setImageAttachmentFilesForAction(List<File> bitmapFiles, boolean isUserGenerated) {
            Validate.containsNoNulls(bitmapFiles, "bitmapFiles");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateActionAttachmentUrls(addImageAttachmentFiles(bitmapFiles), isUserGenerated);
            return this;
        }

        private void updateActionAttachmentUrls(List<String> attachmentUrls, boolean isUserGenerated) {
            List<JSONObject> attachments = this.action.getImage();
            if (attachments == null) {
                attachments = new ArrayList(attachmentUrls.size());
            }
            for (String url : attachmentUrls) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(NativeProtocol.IMAGE_URL_KEY, url);
                    if (isUserGenerated) {
                        jsonObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    }
                    attachments.add(jsonObject);
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
            this.action.setImage(attachments);
        }

        public CONCRETE setImageAttachmentsForObject(String objectProperty, List<Bitmap> bitmaps) {
            return setImageAttachmentsForObject(objectProperty, bitmaps, false);
        }

        public CONCRETE setImageAttachmentsForObject(String objectProperty, List<Bitmap> bitmaps, boolean isUserGenerated) {
            Validate.notNull(objectProperty, "objectProperty");
            Validate.containsNoNulls(bitmaps, "bitmaps");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateObjectAttachmentUrls(objectProperty, addImageAttachments(bitmaps), isUserGenerated);
            return this;
        }

        public CONCRETE setImageAttachmentFilesForObject(String objectProperty, List<File> bitmapFiles) {
            return setImageAttachmentFilesForObject(objectProperty, bitmapFiles, false);
        }

        public CONCRETE setImageAttachmentFilesForObject(String objectProperty, List<File> bitmapFiles, boolean isUserGenerated) {
            Validate.notNull(objectProperty, "objectProperty");
            Validate.containsNoNulls(bitmapFiles, "bitmapFiles");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateObjectAttachmentUrls(objectProperty, addImageAttachmentFiles(bitmapFiles), isUserGenerated);
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public void updateObjectAttachmentUrls(String objectProperty, List<String> attachmentUrls, boolean isUserGenerated) {
            try {
                OpenGraphObject object = (OpenGraphObject) this.action.getPropertyAs(objectProperty, OpenGraphObject.class);
                if (object == null) {
                    throw new IllegalArgumentException("Action does not contain a property '" + objectProperty + "'");
                } else if (object.getCreateObject()) {
                    GraphObjectList<GraphObject> attachments = object.getImage();
                    if (attachments == null) {
                        attachments = Factory.createList(GraphObject.class);
                    }
                    for (String url : attachmentUrls) {
                        GraphObject graphObject = Factory.create();
                        graphObject.setProperty(NativeProtocol.IMAGE_URL_KEY, url);
                        if (isUserGenerated) {
                            graphObject.setProperty(NativeProtocol.IMAGE_USER_GENERATED_KEY, Boolean.valueOf(true));
                        }
                        attachments.add(graphObject);
                    }
                    object.setImage(attachments);
                } else {
                    throw new IllegalArgumentException("The Open Graph object in '" + objectProperty + "' is not marked for creation");
                }
            } catch (FacebookGraphObjectException e) {
                throw new IllegalArgumentException("Property '" + objectProperty + "' is not a graph object");
            }
        }

        /* Access modifiers changed, original: protected */
        public Bundle setBundleExtras(Bundle extras) {
            putExtra(extras, NativeProtocol.EXTRA_PREVIEW_PROPERTY_NAME, this.previewPropertyName);
            putExtra(extras, NativeProtocol.EXTRA_ACTION_TYPE, this.actionType);
            extras.putBoolean(NativeProtocol.EXTRA_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            JSONObject jsonAction = flattenChildrenOfGraphObject(this.action.getInnerJSONObject());
            putExtra(extras, NativeProtocol.EXTRA_ACTION, !(jsonAction instanceof JSONObject) ? jsonAction.toString() : JSONObjectInstrumentation.toString(jsonAction));
            return extras;
        }

        /* Access modifiers changed, original: protected */
        public Bundle getMethodArguments() {
            Bundle methodArgs = new Bundle();
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_PREVIEW_PROPERTY_NAME, this.previewPropertyName);
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_ACTION_TYPE, this.actionType);
            methodArgs.putBoolean(NativeProtocol.METHOD_ARGS_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            JSONObject jsonAction = flattenChildrenOfGraphObject(this.action.getInnerJSONObject());
            putExtra(methodArgs, "ACTION", !(jsonAction instanceof JSONObject) ? jsonAction.toString() : JSONObjectInstrumentation.toString(jsonAction));
            return methodArgs;
        }

        private JSONObject flattenChildrenOfGraphObject(JSONObject graphObject) {
            try {
                String jSONObjectInstrumentation;
                if (graphObject instanceof JSONObject) {
                    jSONObjectInstrumentation = JSONObjectInstrumentation.toString(graphObject);
                } else {
                    jSONObjectInstrumentation = graphObject.toString();
                }
                graphObject = JSONObjectInstrumentation.init(jSONObjectInstrumentation);
                Iterator<String> keys = graphObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    if (!key.equalsIgnoreCase("image")) {
                        graphObject.put(key, flattenObject(graphObject.get(key)));
                    }
                }
                return graphObject;
            } catch (JSONException e) {
                throw new FacebookException(e);
            }
        }

        private Object flattenObject(Object object) throws JSONException {
            if (object == null) {
                return null;
            }
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                if (jsonObject.optBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY)) {
                    return object;
                }
                if (jsonObject.has("id")) {
                    return jsonObject.getString("id");
                }
                if (jsonObject.has(NativeProtocol.IMAGE_URL_KEY)) {
                    return jsonObject.getString(NativeProtocol.IMAGE_URL_KEY);
                }
                return object;
            } else if (!(object instanceof JSONArray)) {
                return object;
            } else {
                JSONArray jsonArray = (JSONArray) object;
                JSONArray newArray = new JSONArray();
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    newArray.put(flattenObject(jsonArray.get(i)));
                }
                return newArray;
            }
        }
    }

    public static class OpenGraphActionDialogBuilder extends OpenGraphDialogBuilderBase<OpenGraphActionDialogBuilder> {
        @Deprecated
        public OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction action, String actionType, String previewPropertyName) {
            super(activity, action, actionType, previewPropertyName);
        }

        public OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction action, String previewPropertyName) {
            super(activity, action, previewPropertyName);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG);
        }
    }

    public enum OpenGraphActionDialogFeature implements DialogFeature {
        OG_ACTION_DIALOG(NativeProtocol.PROTOCOL_VERSION_20130618);
        
        private int minVersion;

        private OpenGraphActionDialogFeature(int minVersion) {
            this.minVersion = minVersion;
        }

        public String getAction() {
            return NativeProtocol.ACTION_OGACTIONPUBLISH_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public static class OpenGraphMessageDialogBuilder extends OpenGraphDialogBuilderBase<OpenGraphMessageDialogBuilder> {
        public OpenGraphMessageDialogBuilder(Activity activity, OpenGraphAction action, String previewPropertyName) {
            super(activity, action, previewPropertyName);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG);
        }
    }

    public enum OpenGraphMessageDialogFeature implements DialogFeature {
        OG_MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204);
        
        private int minVersion;

        private OpenGraphMessageDialogFeature(int minVersion) {
            this.minVersion = minVersion;
        }

        public String getAction() {
            return NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public static class PendingCall implements Parcelable {
        public static final Creator<PendingCall> CREATOR = new C20281();
        private UUID callId;
        private int requestCode;
        private Intent requestIntent;

        /* renamed from: com.facebook.widget.FacebookDialog$PendingCall$1 */
        static class C20281 implements Creator<PendingCall> {
            C20281() {
            }

            public PendingCall createFromParcel(Parcel in) {
                return new PendingCall(in);
            }

            public PendingCall[] newArray(int size) {
                return new PendingCall[size];
            }
        }

        public PendingCall(int requestCode) {
            this.callId = UUID.randomUUID();
            this.requestCode = requestCode;
        }

        private PendingCall(Parcel in) {
            this.callId = UUID.fromString(in.readString());
            this.requestIntent = (Intent) in.readParcelable(null);
            this.requestCode = in.readInt();
        }

        private void setRequestIntent(Intent requestIntent) {
            this.requestIntent = requestIntent;
        }

        public Intent getRequestIntent() {
            return this.requestIntent;
        }

        public UUID getCallId() {
            return this.callId;
        }

        private void setRequestCode(int requestCode) {
            this.requestCode = requestCode;
        }

        public int getRequestCode() {
            return this.requestCode;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.callId.toString());
            parcel.writeParcelable(this.requestIntent, 0);
            parcel.writeInt(this.requestCode);
        }
    }

    private static abstract class PhotoDialogBuilderBase<CONCRETE extends PhotoDialogBuilderBase<?>> extends Builder<CONCRETE> {
        static int MAXIMUM_PHOTO_COUNT = 6;
        private ArrayList<String> friends;
        private ArrayList<String> imageAttachmentUrls = new ArrayList();
        private String place;

        public abstract int getMaximumNumberOfPhotos();

        public PhotoDialogBuilderBase(Activity activity) {
            super(activity);
        }

        public CONCRETE setPlace(String place) {
            this.place = place;
            return this;
        }

        public CONCRETE setFriends(List<String> friends) {
            this.friends = friends == null ? null : new ArrayList(friends);
            return this;
        }

        public CONCRETE addPhotos(Collection<Bitmap> photos) {
            this.imageAttachmentUrls.addAll(addImageAttachments(photos));
            return this;
        }

        public CONCRETE addPhotoFiles(Collection<File> photos) {
            this.imageAttachmentUrls.addAll(addImageAttachmentFiles(photos));
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public void validate() {
            super.validate();
            if (this.imageAttachmentUrls.isEmpty()) {
                throw new FacebookException("Must specify at least one photo.");
            } else if (this.imageAttachmentUrls.size() > getMaximumNumberOfPhotos()) {
                throw new FacebookException(String.format("Cannot add more than %d photos.", new Object[]{Integer.valueOf(getMaximumNumberOfPhotos())}));
            }
        }

        /* Access modifiers changed, original: protected */
        public Bundle setBundleExtras(Bundle extras) {
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_NAME, this.applicationName);
            putExtra(extras, NativeProtocol.EXTRA_PLACE_TAG, this.place);
            extras.putStringArrayList(NativeProtocol.EXTRA_PHOTOS, this.imageAttachmentUrls);
            if (!Utility.isNullOrEmpty(this.friends)) {
                extras.putStringArrayList(NativeProtocol.EXTRA_FRIEND_TAGS, this.friends);
            }
            return extras;
        }

        /* Access modifiers changed, original: protected */
        public Bundle getMethodArguments() {
            Bundle methodArgs = new Bundle();
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            methodArgs.putStringArrayList(NativeProtocol.METHOD_ARGS_PHOTOS, this.imageAttachmentUrls);
            if (!Utility.isNullOrEmpty(this.friends)) {
                methodArgs.putStringArrayList(NativeProtocol.METHOD_ARGS_FRIEND_TAGS, this.friends);
            }
            return methodArgs;
        }
    }

    public static class PhotoMessageDialogBuilder extends PhotoDialogBuilderBase<PhotoMessageDialogBuilder> {
        public PhotoMessageDialogBuilder(Activity activity) {
            super(activity);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, MessageDialogFeature.PHOTOS);
        }

        /* Access modifiers changed, original: 0000 */
        public int getMaximumNumberOfPhotos() {
            return MAXIMUM_PHOTO_COUNT;
        }

        public PhotoMessageDialogBuilder setPlace(String place) {
            return this;
        }

        public PhotoMessageDialogBuilder setFriends(List<String> list) {
            return this;
        }
    }

    public static class PhotoShareDialogBuilder extends PhotoDialogBuilderBase<PhotoShareDialogBuilder> {
        public PhotoShareDialogBuilder(Activity activity) {
            super(activity);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG, ShareDialogFeature.PHOTOS);
        }

        /* Access modifiers changed, original: 0000 */
        public int getMaximumNumberOfPhotos() {
            return MAXIMUM_PHOTO_COUNT;
        }
    }

    public static class ShareDialogBuilder extends ShareDialogBuilderBase<ShareDialogBuilder> {
        public ShareDialogBuilder(Activity activity) {
            super(activity);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG);
        }
    }

    public enum ShareDialogFeature implements DialogFeature {
        SHARE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20130618),
        PHOTOS(NativeProtocol.PROTOCOL_VERSION_20140204),
        VIDEO(NativeProtocol.PROTOCOL_VERSION_20141028);
        
        private int minVersion;

        private ShareDialogFeature(int minVersion) {
            this.minVersion = minVersion;
        }

        public String getAction() {
            return NativeProtocol.ACTION_FEED_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    private static abstract class VideoDialogBuilderBase<CONCRETE extends VideoDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private String place;
        private String videoAttachmentUrl;

        public VideoDialogBuilderBase(Activity activity) {
            super(activity);
        }

        public CONCRETE setPlace(String place) {
            this.place = place;
            return this;
        }

        public CONCRETE addVideoFile(File video) {
            this.videoAttachmentUrl = addVideoAttachmentFile(video);
            return this;
        }

        public CONCRETE setVideoUrl(String videoUrl) {
            this.videoAttachmentUrl = videoUrl;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public void validate() {
            super.validate();
            if (this.videoAttachmentUrl == null || this.videoAttachmentUrl.isEmpty()) {
                throw new FacebookException("Must specify at least one video.");
            }
        }

        /* Access modifiers changed, original: protected */
        public Bundle getMethodArguments() {
            Bundle methodArgs = new Bundle();
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            methodArgs.putString(NativeProtocol.METHOD_ARGS_VIDEO, this.videoAttachmentUrl);
            return methodArgs;
        }
    }

    public static class VideoMessageDialogBuilder extends VideoDialogBuilderBase<VideoMessageDialogBuilder> {
        public VideoMessageDialogBuilder(Activity activity) {
            super(activity);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<MessageDialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, MessageDialogFeature.VIDEO);
        }

        public VideoMessageDialogBuilder setPlace(String place) {
            return this;
        }
    }

    public static class VideoShareDialogBuilder extends VideoDialogBuilderBase<VideoShareDialogBuilder> {
        public VideoShareDialogBuilder(Activity activity) {
            super(activity);
        }

        /* Access modifiers changed, original: protected */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG, ShareDialogFeature.VIDEO);
        }
    }

    public static boolean getNativeDialogDidComplete(Bundle result) {
        if (result.containsKey(RESULT_ARGS_DIALOG_COMPLETE_KEY)) {
            return result.getBoolean(RESULT_ARGS_DIALOG_COMPLETE_KEY);
        }
        return result.getBoolean(EXTRA_DIALOG_COMPLETE_KEY, false);
    }

    public static String getNativeDialogCompletionGesture(Bundle result) {
        if (result.containsKey(RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY)) {
            return result.getString(RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
        }
        return result.getString(EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getNativeDialogPostId(Bundle result) {
        if (result.containsKey(RESULT_ARGS_DIALOG_COMPLETION_ID_KEY)) {
            return result.getString(RESULT_ARGS_DIALOG_COMPLETION_ID_KEY);
        }
        return result.getString(EXTRA_DIALOG_COMPLETION_ID_KEY);
    }

    private FacebookDialog(Activity activity, Fragment fragment, PendingCall appCall, OnPresentCallback onPresentCallback) {
        this.activity = activity;
        this.fragment = fragment;
        this.appCall = appCall;
        this.onPresentCallback = onPresentCallback;
    }

    public PendingCall present() {
        logDialogActivity(this.activity, this.fragment, getEventName(this.appCall.getRequestIntent()), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED);
        if (this.onPresentCallback != null) {
            try {
                this.onPresentCallback.onPresent(this.activity);
            } catch (Exception e) {
                throw new FacebookException(e);
            }
        }
        if (this.fragment != null) {
            this.fragment.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
        } else {
            this.activity.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
        }
        return this.appCall;
    }

    public static boolean handleActivityResult(Context context, PendingCall appCall, int requestCode, Intent data, Callback callback) {
        if (requestCode != appCall.getRequestCode()) {
            return false;
        }
        if (attachmentStore != null) {
            attachmentStore.cleanupAttachmentsForCall(context, appCall.getCallId());
        }
        if (callback != null) {
            if (NativeProtocol.isErrorResult(data)) {
                Bundle errorData = NativeProtocol.getErrorDataFromResultIntent(data);
                callback.onError(appCall, NativeProtocol.getExceptionFromErrorData(errorData), errorData);
            } else {
                callback.onComplete(appCall, NativeProtocol.getSuccessResultsFromIntent(data));
            }
        }
        return true;
    }

    public static boolean canPresentShareDialog(Context context, ShareDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(ShareDialogFeature.SHARE_DIALOG, features));
    }

    public static boolean canPresentMessageDialog(Context context, MessageDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, features));
    }

    public static boolean canPresentOpenGraphActionDialog(Context context, OpenGraphActionDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG, features));
    }

    public static boolean canPresentOpenGraphMessageDialog(Context context, OpenGraphMessageDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG, features));
    }

    private static boolean handleCanPresent(Context context, Iterable<? extends DialogFeature> features) {
        String actionName = getActionForFeatures(features);
        String applicationId = Settings.getApplicationId();
        if (Utility.isNullOrEmpty(applicationId)) {
            applicationId = Utility.getMetadataApplicationId(context);
        }
        return getProtocolVersionForNativeDialog(context, actionName, getVersionSpecForFeatures(applicationId, actionName, features)) != -1;
    }

    private static int getProtocolVersionForNativeDialog(Context context, String action, int[] versionSpec) {
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(context, action, versionSpec);
    }

    private static NativeAppCallAttachmentStore getAttachmentStore() {
        if (attachmentStore == null) {
            attachmentStore = new NativeAppCallAttachmentStore();
        }
        return attachmentStore;
    }

    private static int[] getVersionSpecForFeatures(String applicationId, String actionName, Iterable<? extends DialogFeature> features) {
        int[] intersectedRange = null;
        for (DialogFeature feature : features) {
            intersectedRange = Utility.intersectRanges(intersectedRange, getVersionSpecForFeature(applicationId, actionName, feature));
        }
        return intersectedRange;
    }

    private static int[] getVersionSpecForFeature(String applicationId, String actionName, DialogFeature feature) {
        DialogFeatureConfig config = Utility.getDialogFeatureConfig(applicationId, actionName, feature.name());
        if (config != null) {
            return config.getVersionSpec();
        }
        return new int[]{feature.getMinVersion()};
    }

    private static String getActionForFeatures(Iterable<? extends DialogFeature> features) {
        Iterator i$ = features.iterator();
        if (i$.hasNext()) {
            return ((DialogFeature) i$.next()).getAction();
        }
        return null;
    }

    private static void logDialogActivity(Activity activity, Fragment fragment, String eventName, String outcome) {
        if (fragment != null) {
            activity = fragment.getActivity();
        }
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, outcome);
        logger.logSdkEvent(eventName, null, parameters);
    }

    private static String getEventName(Intent intent) {
        String action = intent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION);
        boolean hasPhotos = intent.hasExtra(NativeProtocol.EXTRA_PHOTOS);
        boolean hasVideo = false;
        Bundle extras = intent.getBundleExtra(NativeProtocol.EXTRA_PROTOCOL_METHOD_ARGS);
        if (extras != null) {
            ArrayList<String> photo = extras.getStringArrayList(NativeProtocol.METHOD_ARGS_PHOTOS);
            String video = extras.getString(NativeProtocol.METHOD_ARGS_VIDEO);
            if (!(photo == null || photo.isEmpty())) {
                hasPhotos = true;
            }
            if (!(video == null || video.isEmpty())) {
                hasVideo = true;
            }
        }
        return getEventName(action, hasPhotos, hasVideo);
    }

    private static String getEventName(String action, boolean hasPhotos, boolean hasVideo) {
        if (action.equals(NativeProtocol.ACTION_FEED_DIALOG)) {
            if (hasVideo) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_VIDEO_SHARE;
            }
            return hasPhotos ? AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_PHOTO_SHARE : AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_SHARE;
        } else if (action.equals(NativeProtocol.ACTION_MESSAGE_DIALOG)) {
            return hasPhotos ? AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_PHOTO_MESSAGE : AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_MESSAGE;
        } else {
            if (action.equals(NativeProtocol.ACTION_OGACTIONPUBLISH_DIALOG)) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_OG_SHARE;
            }
            if (action.equals(NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG)) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_OG_MESSAGE;
            }
            if (action.equals(NativeProtocol.ACTION_LIKE_DIALOG)) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_LIKE;
            }
            throw new FacebookException("An unspecified action was presented");
        }
    }
}
