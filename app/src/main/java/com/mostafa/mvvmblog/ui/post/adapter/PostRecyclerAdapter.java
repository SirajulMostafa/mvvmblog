package com.mostafa.mvvmblog.ui.post.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafa.mvvmblog.R;
import com.mostafa.mvvmblog.data.models.Post;

import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Post> mPosts;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_list_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        int itemViewType = getItemViewType(position);
        ((PostViewHolder)holder).title.setText(mPosts.get(position).getTitle());
        ((PostViewHolder)holder).postBody.setText(mPosts.get(position).getBody());
        ((PostViewHolder)holder).postUserid.setText(String.valueOf(mPosts.get(position).getUserId()));


    }

    @Override
    public int getItemCount() {
        if (mPosts != null) {
            return mPosts.size();
        }
        return 0;
    }

    public void setPosts(List<Post> posts) {
        this.mPosts = posts;
        notifyDataSetChanged();
    }
}
