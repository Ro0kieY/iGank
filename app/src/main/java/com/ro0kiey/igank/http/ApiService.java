package com.ro0kiey.igank.http;

import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.model.DailyGank;
import com.ro0kiey.igank.model.Meizi;
import com.ro0kiey.igank.model.TypeList;
import com.ro0kiey.igank.model.休息视频;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ro0kieY on 2017/6/30.
 */

public interface ApiService {

    @GET("data/福利/{count}/{page}")
    Observable<Meizi> getMeiziData(@Path("count") int count, @Path("page") int page);

    @GET("data/休息视频/{count}/{page}")
    Observable<休息视频> getShipinData(@Path("count") int count, @Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<DailyGank> getDailyGank(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/{type}/{count}/{page}")
    Observable<TypeList> getListGank(@Path("type") String type, @Path("count") int count, @Path("page") int page);

}
