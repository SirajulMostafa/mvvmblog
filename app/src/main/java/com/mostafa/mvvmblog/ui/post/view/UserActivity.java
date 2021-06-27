package com.mostafa.mvvmblog.ui.post.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mostafa.mvvmblog.R;
import com.mostafa.mvvmblog.data.models.User;
import com.mostafa.mvvmblog.ui.post.adapter.UserRecyclerAdapter;
import com.mostafa.mvvmblog.ui.post.viewmodels.UserViewModel;
import com.mostafa.mvvmblog.utils.Resource;

import java.util.List;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private UserRecyclerAdapter mAdapter;
    private UserViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.user_list);
        setUpViewModel();
        subscribeObervers();//setupObserver
        setUpUI();
    }

    private void setUpUI() {

        mAdapter = new UserRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpViewModel() {
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }
    private void subscribeObervers() {
        viewModel.observeUsers().observe(this, new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {

                        case LOADING: {
                            Log.d(TAG, "onChanged: LOADING...");
                            showProgressBar(true);
                            break;
                        }

                        case SUCCESS: {
                            mAdapter.setUsers(listResource.data);
                            showProgressBar(false);
                            break;
                        }

                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(UserActivity.this, listResource.message, Toast.LENGTH_SHORT).show();
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