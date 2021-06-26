package com.mostafa.mvvmblog.ui.post.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafa.mvvmblog.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView title,postBody,postUserid;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.postTitle);
        postBody = itemView.findViewById(R.id.postbody);
        postUserid = itemView.findViewById(R.id.postUser);



    }
}
