package com.mostafa.mvvmblog.ui.post.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mostafa.mvvmblog.R;
import com.mostafa.mvvmblog.data.models.Post;
import com.mostafa.mvvmblog.ui.post.viewmodels.PostViewModel;
import com.mostafa.mvvmblog.utils.Resource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        subscribeObervers();
    }

    private void subscribeObervers() {
//        viewModel.observePosts().removeObservers(this);
        viewModel.observePosts().observe(this, new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {

                        case LOADING: {
                            Log.d(TAG, "onChanged: LOADING...");
                            break;
                        }

                        case SUCCESS: {
                            Log.d(TAG, "onChanged: got posts..." + listResource.data.size());
                            Log.d(TAG, "onChanged: see first post..." +  listResource.data.get(0).getTitle().toString());
                            // adapter.setPosts(listResource.data);
                            for (Post post : listResource.data) {
                                Log.d(TAG, "onChanged: "+post);
                            }

                            break;
                        }

                        case ERROR: {
                            Log.e(TAG, "onChanged: ERROR..." + listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }
}