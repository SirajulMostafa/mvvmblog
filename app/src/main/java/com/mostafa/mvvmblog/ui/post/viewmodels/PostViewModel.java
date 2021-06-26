package com.mostafa.mvvmblog.ui.post.viewmodels;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.mostafa.mvvmblog.data.api.ServiceGenerator;
import com.mostafa.mvvmblog.data.models.Post;
import com.mostafa.mvvmblog.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    private MediatorLiveData<Resource<List<Post>>> posts ;

     public PostViewModel(MediatorLiveData<Resource<List<Post>>> posts) {
        this.posts = posts;
        Log.d(TAG, "PostViewModel: post viewmodel is working");
    } 

    public PostViewModel() {
    }


    public LiveData<Resource<List<Post>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));
            final LiveData<Resource<List<Post>>> resourceLiveData = LiveDataReactiveStreams.fromPublisher(
                    ServiceGenerator.getPostApi().getAllPosts()

                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(Throwable throwable) throws Throwable {
                                    Post post = new Post();
                                    post.setId(-1);
                                    ArrayList<Post> posts = new ArrayList<>();
                                    posts.add(post);

                                    return posts;
                                }
                            }).map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply(List<Post> posts) throws Throwable {
                            if (posts.size() > 0) {
                                if (posts.get(0).getId() == -1) {
                                    return Resource.error("Something went wrong ...",null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    }).subscribeOn(Schedulers.io()));
            posts.addSource(resourceLiveData, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(resourceLiveData);
                }
            });
        }

        return posts;
    }
}
