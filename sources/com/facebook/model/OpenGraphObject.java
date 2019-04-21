package com.facebook.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface OpenGraphObject extends GraphObject {

    public static final class Factory {
        public static OpenGraphObject createForPost(String type) {
            return createForPost(OpenGraphObject.class, type);
        }

        public static <T extends OpenGraphObject> T createForPost(Class<T> graphObjectClass, String type) {
            return createForPost(graphObjectClass, type, null, null, null, null);
        }

        public static <T extends OpenGraphObject> T createForPost(Class<T> graphObjectClass, String type, String title, String imageUrl, String url, String description) {
            OpenGraphObject object = (OpenGraphObject) com.facebook.model.GraphObject.Factory.create((Class) graphObjectClass);
            if (type != null) {
                object.setType(type);
            }
            if (title != null) {
                object.setTitle(title);
            }
            if (imageUrl != null) {
                object.setImageUrls(Arrays.asList(new String[]{imageUrl}));
            }
            if (url != null) {
                object.setUrl(url);
            }
            if (description != null) {
                object.setDescription(description);
            }
            object.setCreateObject(true);
            object.setData(com.facebook.model.GraphObject.Factory.create());
            return object;
        }
    }

    GraphObject getApplication();

    GraphObjectList<GraphObject> getAudio();

    @PropertyName("fbsdk:create_object")
    boolean getCreateObject();

    Date getCreatedTime();

    GraphObject getData();

    String getDescription();

    String getDeterminer();

    String getId();

    GraphObjectList<GraphObject> getImage();

    boolean getIsScraped();

    String getPostActionId();

    List<String> getSeeAlso();

    String getSiteName();

    String getTitle();

    String getType();

    Date getUpdatedTime();

    String getUrl();

    GraphObjectList<GraphObject> getVideo();

    void setApplication(GraphObject graphObject);

    void setAudio(GraphObjectList<GraphObject> graphObjectList);

    @PropertyName("fbsdk:create_object")
    void setCreateObject(boolean z);

    void setCreatedTime(Date date);

    void setData(GraphObject graphObject);

    void setDescription(String str);

    void setDeterminer(String str);

    void setId(String str);

    void setImage(GraphObjectList<GraphObject> graphObjectList);

    @CreateGraphObject("url")
    @PropertyName("image")
    void setImageUrls(List<String> list);

    void setIsScraped(boolean z);

    void setPostActionId(String str);

    void setSeeAlso(List<String> list);

    void setSiteName(String str);

    void setTitle(String str);

    void setType(String str);

    void setUpdatedTime(Date date);

    void setUrl(String str);

    void setVideo(GraphObjectList<GraphObject> graphObjectList);
}
