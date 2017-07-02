package com.ro0kiey.igank.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ro0kieY on 2017/6/30.
 */

public class MeiziBean {

        /**
         * _id : 5941db7b421aa92c794633cd
         * createdAt : 2017-06-15T08:57:31.47Z
         * desc : 6-15
         * publishedAt : 2017-06-15T13:55:57.947Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg
         * used : true
         * who : 代码家
         */

        @SerializedName("_id")
        public String id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;

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

    @Override
    public String toString() {
        return "MeiziBean{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }
}
