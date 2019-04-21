package com.facebook.model;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public interface OpenGraphAction extends GraphObject {

    public static final class Factory {
        @Deprecated
        public static OpenGraphAction createForPost() {
            return createForPost(OpenGraphAction.class, null);
        }

        public static OpenGraphAction createForPost(String type) {
            return createForPost(OpenGraphAction.class, type);
        }

        public static <T extends OpenGraphAction> T createForPost(Class<T> graphObjectClass, String type) {
            OpenGraphAction object = (OpenGraphAction) com.facebook.model.GraphObject.Factory.create((Class) graphObjectClass);
            if (type != null) {
                object.setType(type);
            }
            return object;
        }
    }

    GraphObject getApplication();

    JSONObject getComments();

    Date getCreatedTime();

    GraphObject getData();

    Date getEndTime();

    Date getExpiresTime();

    @PropertyName("fb:explicitly_shared")
    boolean getExplicitlyShared();

    GraphUser getFrom();

    String getId();

    List<JSONObject> getImage();

    JSONObject getLikes();

    String getMessage();

    GraphPlace getPlace();

    Date getPublishTime();

    String getRef();

    Date getStartTime();

    GraphObjectList<GraphObject> getTags();

    String getType();

    void setApplication(GraphObject graphObject);

    void setComments(JSONObject jSONObject);

    void setCreatedTime(Date date);

    void setData(GraphObject graphObject);

    void setEndTime(Date date);

    void setExpiresTime(Date date);

    @PropertyName("fb:explicitly_shared")
    void setExplicitlyShared(boolean z);

    void setFrom(GraphUser graphUser);

    void setId(String str);

    void setImage(List<JSONObject> list);

    @CreateGraphObject("url")
    @PropertyName("image")
    void setImageUrls(List<String> list);

    void setLikes(JSONObject jSONObject);

    void setMessage(String str);

    void setPlace(GraphPlace graphPlace);

    void setPublishTime(Date date);

    void setRef(String str);

    void setStartTime(Date date);

    void setTags(List<? extends GraphObject> list);

    void setType(String str);
}
