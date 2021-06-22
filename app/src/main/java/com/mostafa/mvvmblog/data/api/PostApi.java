package com.mostafa.mvvmblog.data.api;

import com.mostafa.mvvmblog.data.models.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostApi {
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );
    @GET("posts")
    Flowable<List<Post>> getAllPosts();

}
