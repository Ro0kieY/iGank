package com.ro0kiey.igank.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by R0okieY on 2017/6/30.
 */

public class RetrofitClient {

    protected static final Object monitor = new Object();
    private static Retrofit retrofit;
    private static ApiService apiService;
    public static final String BASE_URL = "http://gank.io/api/";

    private RetrofitClient() {
    }

    static {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiService getApiServiceInstance(){

        synchronized (monitor){
            if (apiService  == null){
                apiService = retrofit.create(ApiService.class);
            }
            return apiService;
        }
    }

}
