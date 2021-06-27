package com.mostafa.mvvmblog.ui.post.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafa.mvvmblog.R;
import com.mostafa.mvvmblog.data.models.User;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> mUsers;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserViewHolder)holder).userName.setText(mUsers.get(position).getUsername());
        ((UserViewHolder)holder).userEmail.setText(mUsers.get(position).getEmail());
        ((UserViewHolder)holder).userWebsite.setText(mUsers.get(position).getWebsite());


    }

    @Override
    public int getItemCount() {
        if (mUsers != null) {
            return mUsers.size();
        }
        return 0;
    }

    public void setUsers(List<User> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }
}
