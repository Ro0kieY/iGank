package com.ro0kiey.igank.model.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class 休息视频Bean {

        /**
         * _id : 5958dc1f421aa90c9203d313
         * createdAt : 2017-07-02T19:42:23.505Z
         * desc : 5G已经到来，比4G快几100倍，一秒下载1个G
         * publishedAt : 2017-07-04T11:50:36.484Z
         * source : chrome
         * type : 休息视频
         * url : http://www.bilibili.com/video/av11787133/
         * used : true
         * who : LHF
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


}
