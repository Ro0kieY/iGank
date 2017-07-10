package com.ro0kiey.igank.model.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class 前端Bean {

        /**
         * _id : 5955f036421aa90ca209c3d8
         * createdAt : 2017-06-30T14:31:18.374Z
         * desc : Aurora IMUI，一个通用的即时通讯库。不局限于任何 IM SDK，现在已经支持 React Native。Github 近两千 star，炒鸡好用的！
         * images : ["http://img.gank.io/f61b2422-d35c-4b08-98ed-30b54788f82d"]
         * publishedAt : 2017-07-04T11:50:36.484Z
         * source : web
         * type : 前端
         * url : http://y0.cn/gank
         * used : true
         * who : null
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
