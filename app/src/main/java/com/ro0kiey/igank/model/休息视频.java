package com.ro0kiey.igank.model;

import com.ro0kiey.igank.model.Bean.休息视频Bean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/9.
 */

public class 休息视频 {
    /**
     * error : false
     * 休息视频Been : [{"_id":"595e3f40421aa90cb4724b82","createdAt":"2017-07-06T21:46:40.350Z","desc":"九个神奇的磁力小道具","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av11702675/","used":true,"who":"LHF"},{"_id":"595509e9421aa90cb4724b4c","createdAt":"2017-06-29T22:08:41.907Z","desc":"【敖厂长】横跨11年怪诞游戏事件","publishedAt":"2017-07-06T11:57:03.770Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av11733920/","used":true,"who":"LHF"},{"_id":"5945137c421aa90f1036ac39","createdAt":"2017-06-17T19:33:16.963Z","desc":"【将军】几分钟看《黑镜：马上回来》9分英国神剧","publishedAt":"2017-07-05T11:15:30.556Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av11396230/","used":true,"who":"LHF"},{"_id":"5958dc1f421aa90c9203d313","createdAt":"2017-07-02T19:42:23.505Z","desc":"5G已经到来，比4G快几100倍，一秒下载1个G","publishedAt":"2017-07-04T11:50:36.484Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av11787133/","used":true,"who":"LHF"},{"_id":"593feee5421aa92c769a8c7b","createdAt":"2017-06-13T21:55:49.59Z","desc":"经典表情包出处","publishedAt":"2017-06-15T13:55:57.947Z","source":"chrome","type":"休息视频","url":"http://music.163.com/#/event?id=1699013374&uid=358508583","used":true,"who":"lxxself"},{"_id":"593a9127421aa92c769a8c47","createdAt":"2017-06-09T20:14:31.126Z","desc":"中国全球首创，超导悬浮列车，时速将达3千公里，还不费电","publishedAt":"2017-06-14T11:34:54.556Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av11180635/","used":true,"who":"LHF"},{"_id":"593d67da421aa92c7be61bf0","createdAt":"2017-06-11T23:55:06.288Z","desc":"陈一发儿，羞耻的看自己的鬼畜！","publishedAt":"2017-06-12T11:11:11.25Z","source":"chrome","type":"休息视频","url":"http://www.acfun.cn/v/ac3769202","used":true,"who":"lxxself"},{"_id":"592ce52f421aa92c7be61b6d","createdAt":"2017-05-30T11:21:19.74Z","desc":"【问舰】10分钟解读《我，机器人》，机器人狂虐人类，人类要如何反败为胜","publishedAt":"2017-06-09T12:50:03.131Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10905778/","used":true,"who":"LHF"},{"_id":"5926d206421aa92c7946331b","createdAt":"2017-05-25T20:45:58.119Z","desc":"【谷阿莫】5分鐘看完谷阿莫的8分鐘看完《加勒比海盜1-4集》","publishedAt":"2017-06-08T11:27:47.21Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10819369/","used":true,"who":"LHF"},{"_id":"5926d6bd421aa92c73b64765","createdAt":"2017-05-25T21:06:05.616Z","desc":"钢铁侠睡过的女人，比我吃的盐都多","publishedAt":"2017-06-07T11:43:31.396Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10819814/","used":true,"who":"LHF"}]
     */

    public boolean error;
    public List<休息视频Bean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<休息视频Bean> getResults() {
        return results;
    }

    public void setResults(List<休息视频Bean> 休息视频Bean) {
        this.results = 休息视频Bean;
    }
}
