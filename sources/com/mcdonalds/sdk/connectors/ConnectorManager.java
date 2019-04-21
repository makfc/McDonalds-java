package com.mcdonalds.sdk.connectors;

import android.content.Context;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import com.mcdonalds.sdk.connectors.baidu.BaiduConnector;
import com.mcdonalds.sdk.connectors.cybersource.CybersourceConnector;
import com.mcdonalds.sdk.connectors.cybersource.CybersourceSecurityConnector;
import com.mcdonalds.sdk.connectors.easyaddress.EasyAddressConnector;
import com.mcdonalds.sdk.connectors.google.GoogleConnector;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.MiddlewareStoreLocatorConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.RequestManager;
import java.util.HashMap;

public class ConnectorManager {
    private static final String CONNECTOR = "Connector";
    private static final String CONNECTOR_CONFIGS_PATH = "connectors";
    public static final String MCD_CONNECTORS_RELOADED_NOTIFICATION = "com.mcdonalds.notification.MCD_MODULES_RELOADED_NOTIFICATION";
    private static ConnectorManager mSharedInstance;
    private HashMap<String, BaseConnector> mConnectorMap;
    private Context mContext;

    private ConnectorManager() {
    }

    public ConnectorManager initialize(Context context) {
        this.mContext = context;
        this.mConnectorMap = new HashMap();
        return this;
    }

    public static ConnectorManager getSharedInstance() {
        if (mSharedInstance == null) {
            mSharedInstance = new ConnectorManager().initialize(McDonalds.getContext());
        }
        return mSharedInstance;
    }

    public static void setSharedInstance(ConnectorManager connectorManager) {
        mSharedInstance = connectorManager;
    }

    public static <T> T getConnector(String implementation) {
        return getSharedInstance().getConnectorImpl(implementation);
    }

    public <T> T getConnectorImpl(String implementation) {
        String connectorName = implementation.toLowerCase();
        BaseConnector connector = (BaseConnector) this.mConnectorMap.get(connectorName);
        if (connector != null) {
            return connector;
        }
        connector = getConnectorForName(connectorName);
        this.mConnectorMap.put(connectorName, connector);
        return connector;
    }

    private BaseConnector getConnectorForName(String connectorName) {
        Object obj = -1;
        switch (connectorName.hashCode()) {
            case -2010373337:
                if (connectorName.equals(MiddlewareStoreLocatorConnector.NAME)) {
                    obj = 4;
                    break;
                }
                break;
            case -1240244679:
                if (connectorName.equals(GoogleConnector.NAME)) {
                    obj = 2;
                    break;
                }
                break;
            case -1110306382:
                if (connectorName.equals(EasyAddressConnector.NAME)) {
                    obj = 1;
                    break;
                }
                break;
            case -816997998:
                if (connectorName.equals(MiddlewareConnector.NAME)) {
                    obj = 3;
                    break;
                }
                break;
            case 93498907:
                if (connectorName.equals(BaiduConnector.NAME)) {
                    obj = 5;
                    break;
                }
                break;
            case 575116724:
                if (connectorName.equals(CybersourceConnector.NAME)) {
                    obj = 7;
                    break;
                }
                break;
            case 949164276:
                if (connectorName.equals(CybersourceSecurityConnector.NAME)) {
                    obj = 8;
                    break;
                }
                break;
            case 1439492565:
                if (connectorName.equals("autonavi")) {
                    obj = null;
                    break;
                }
                break;
            case 1664135880:
                if (connectorName.equals(MWCustomerSecurityConnector.NAME)) {
                    obj = 6;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new AutoNaviConnector(this.mContext);
            case 1:
                return new EasyAddressConnector(this.mContext);
            case 2:
                return new GoogleConnector(this.mContext);
            case 3:
                return new MiddlewareConnector(this.mContext);
            case 4:
                return new MiddlewareStoreLocatorConnector(this.mContext);
            case 5:
                return new BaiduConnector(this.mContext);
            case 6:
                return new MWCustomerSecurityConnector(this.mContext);
            case 7:
                return new CybersourceConnector(this.mContext);
            case 8:
                return new CybersourceSecurityConnector();
            default:
                MCDLog.fatal("No Valid Connector found for type: " + connectorName);
                return null;
        }
    }

    public void clearConnectors() {
        for (BaseConnector connector : this.mConnectorMap.values()) {
            if (connector.getNetworkConnection() != null) {
                RequestManager.unregister(connector.getContext(), connector.getNetworkConnection());
            }
        }
        this.mConnectorMap = new HashMap();
    }
}
