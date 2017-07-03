package com.ro0kiey.igank.http;

import com.ro0kiey.igank.model.Meizi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ro0kieY on 2017/6/30.
 */

public interface ApiService {

    @GET("data/福利/{count}/{page}")
    Observable<Meizi> getMeiziData(@Path("count") int count, @Path("page") int page);

}
