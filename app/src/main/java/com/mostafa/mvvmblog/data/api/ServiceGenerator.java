package com.mostafa.mvvmblog.data.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mostafa.mvvmblog.utils.Constrains.BASE_URL;

public class ServiceGenerator {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static PostApi postApi = retrofit.create(PostApi.class);
    public static PostApi getPostApi() { return postApi; }
    private static UserApi userApi = retrofit.create(UserApi.class);
    public static UserApi getUserApi() { return userApi; }
}
