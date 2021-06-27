package com.mostafa.mvvmblog.data.api;

import com.mostafa.mvvmblog.data.models.Post;
import com.mostafa.mvvmblog.data.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface UserApi {

    @GET("users")
    Flowable<List<User>> getAllUsers();

    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id
    );
}
