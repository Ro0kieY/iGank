package com.ro0kiey.igank.model;

import com.ro0kiey.igank.model.Bean.GankBean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/12.
 */

public class TypeList {

    /**
     * error : false
     * results : [{"_id":"596587a7421aa97de5c7c91c","createdAt":"2017-07-12T10:21:27.468Z","desc":"Android library to display progress like google does in some of his services.","publishedAt":"2017-07-12T13:05:59.766Z","source":"chrome","type":"Android","url":"https://github.com/jpardogo/GoogleProgressBar","used":true,"who":"galois"},{"_id":"5965ad43421aa90ca3bb6ae9","createdAt":"2017-07-12T13:01:55.875Z","desc":"Android 上最便捷的第三方 Keyboard 集合。","images":["http://img.gank.io/6f9816fd-bada-4912-a133-6a7194d35292"],"publishedAt":"2017-07-12T13:05:59.766Z","source":"chrome","type":"Android","url":"https://github.com/hoanganhtuan95ptit/AwesomeKeyboard","used":true,"who":"代码家"},{"_id":"595f7f6d421aa90ca209c416","createdAt":"2017-07-07T20:32:45.22Z","desc":"根据实际开发中遇到的需求，使用 Gradle 对应用的不同版本进行个性化定制。","images":["http://img.gank.io/0be70b9b-dc5a-4778-bb7b-0f5ff0e73d2a"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"http://www.imliujun.com/gradle1.html","used":true,"who":"LiuJun"},{"_id":"5964263e421aa90ca3bb6adc","createdAt":"2017-07-11T09:13:34.550Z","desc":"你的Android应用稳定吗？","publishedAt":"2017-07-11T13:46:33.911Z","source":"web","type":"Android","url":"http://url.cn/4BbWlxC","used":true,"who":"陈宇明"},{"_id":"59646466421aa90c9203d367","createdAt":"2017-07-11T13:38:46.38Z","desc":"Android 信用卡交易效果 UI 。","images":["http://img.gank.io/b9a34460-8224-449d-903d-3ef54a3f35b6"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"https://github.com/KingsMentor/Luhn","used":true,"who":"代码家"},{"_id":"59646491421aa90ca209c433","createdAt":"2017-07-11T13:39:29.898Z","desc":"效果很棒的 Fab 按钮。","images":["http://img.gank.io/221dc9b8-f3cb-4602-8a52-43a780328925"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"https://github.com/jahirfiquitiva/FABsMenu","used":true,"who":"代码家"},{"_id":"595f9710421aa90ca3bb6abf","createdAt":"2017-07-07T22:13:36.139Z","desc":"基于ASM，通过注解，实现对方法调用时的参数、返回值、耗时等信息的纪录。","publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/saymagic/Daffodil","used":true,"who":"saymagic"},{"_id":"5960ede0421aa90cb4724b95","createdAt":"2017-07-08T22:36:16.905Z","desc":"Android之重新推导设备尺寸","images":["http://img.gank.io/666f8d19-4d94-4d14-bfc8-5ff516777f4b"],"publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/3475c0006948","used":true,"who":"bolex"},{"_id":"5962e177421aa90c9203d358","createdAt":"2017-07-10T10:07:51.286Z","desc":"懂得智能配色的ImageView,还能给自己设置多彩的阴影哦。","images":["http://img.gank.io/4cc10e1d-fb75-4c22-9df9-091706b55c82"],"publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/DingMouRen/PaletteImageView-","used":true,"who":null},{"_id":"5962e66f421aa90ca3bb6ad2","createdAt":"2017-07-10T10:29:03.275Z","desc":"基于Glide V4.0封装的图片加载库，可以监听加载图片时的进度","publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/sfsheng0322/GlideImageView","used":true,"who":"孙福生"}]
     */

    private boolean error;
    private List<GankBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankBean> getResults() {
        return results;
    }

    public void setResults(List<GankBean> results) {
        this.results = results;
    }
}
