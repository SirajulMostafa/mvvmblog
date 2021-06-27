package com.mostafa.mvvmblog.ui.post.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafa.mvvmblog.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView userName,userEmail,userWebsite;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
         userName= itemView.findViewById(R.id.userName);
        userEmail = itemView.findViewById(R.id.userEmail);
        userWebsite = itemView.findViewById(R.id.userWebsite);



    }
}
