package com.mostafa.mvvmblog.ui.post.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafa.mvvmblog.R;
import com.mostafa.mvvmblog.ui.post.adapter.PostRecyclerAdapter;
import com.mostafa.mvvmblog.data.models.Post;
import com.mostafa.mvvmblog.ui.post.viewmodels.PostViewModel;
import com.mostafa.mvvmblog.utils.Resource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PostViewModel viewModel;
    private RecyclerView mRecyclerView;
    PostRecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.post_list);
        setUpViewModel();
        subscribeObervers();//setupObserver
        setUpUI();
    }

    private void setUpUI() {

        mAdapter = new PostRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpViewModel() {
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
    }

    private void subscribeObervers() {
//      viewModel.observePosts().removeObservers(this);
//        viewModel.observePosts().removeObservers(getViewLifecycleOwner());it will be in fragment
        viewModel.observePosts().observe(this, new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {

                        case LOADING: {
                            Log.d(TAG, "onChanged: LOADING...");
                            showProgressBar(true);
                            break;
                        }

                        case SUCCESS: {
                            mAdapter.setPosts(listResource.data);
                            showProgressBar(false);
                            break;
                        }

                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(MainActivity.this, listResource.message, Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onChanged: ERROR..." + listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    public void showProgressBar(boolean visibility) {
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}