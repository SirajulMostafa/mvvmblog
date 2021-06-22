package com.mostafa.mvvmblog.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mostafa.mvvmblog.data.api.PostApi;
import com.mostafa.mvvmblog.data.api.ServiceGenerator;
import com.mostafa.mvvmblog.data.models.Post;

import java.util.List;

public class PostRepository {


    //singleton
    private  static PostRepository instance;
    private MediatorLiveData<List<Post>> mPosts= new MediatorLiveData<>() ;
    private PostApi postApi;

    public static PostRepository getInstance() {
        if (instance==null){
            instance= new PostRepository();
        }
        return instance;
    }
    private  PostRepository(){
       // mPosts = new MutableLiveData<>();
       postApi = ServiceGenerator.getPostApi();


    }


    public LiveData<List<Post>> getPosts(){
        return mPosts;
    }
    public PostApi getPostApi(){
        return postApi;
    }


}
