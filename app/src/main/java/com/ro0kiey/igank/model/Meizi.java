package com.ro0kiey.igank.model;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/6/30.
 */

public class Meizi {

    /**
     * error : false
     * results : [{"_id":"5941db7b421aa92c794633cd",
     *          "createdAt":"2017-06-15T08:57:31.47Z",
     *          "desc":"6-15","publishedAt":"2017-06-15T13:55:57.947Z",
     *          "source":"chrome",
     *          "type":"福利",
     *          "url":"https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg","used":true,
     *          "who":"代码家"}]
     */

    public boolean error;
    public List<MeiziBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MeiziBean> getResults() {
        return results;
    }

    public void setResults(List<MeiziBean> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "Meizi{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
