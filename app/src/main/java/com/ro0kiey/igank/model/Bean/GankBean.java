package com.ro0kiey.igank.model.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/10.
 */

public class GankBean {

    /**
     * _id : 595ad074421aa90ca3bb6a90
     * createdAt : 2017-07-04T07:17:08.609Z
     * desc : Android 有两套相机 Api，使用起来很麻烦，好在 Foto 开源了他们在 Android 上的 Camera 封装 Api，力荐！
     * images : ["http://img.gank.io/0a15bae7-c513-4feb-bbe2-1273b8b809ce"]
     * publishedAt : 2017-07-04T11:50:36.484Z
     * source : chrome
     * type : Android
     * url : https://github.com/Fotoapparat/Fotoapparat
     * used : true
     * who : 代码家
     */

    @SerializedName("_id")
    private String id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
