package com.project.finalyear.thaispellinggame.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.finalyear.thaispellinggame.R;
import com.project.finalyear.thaispellinggame.model.UserModel;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Namwan on 11/23/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserModel> list;

    public UserAdapter(List<UserModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_single_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        UserModel user = list.get(position);

        holder.mName.setText(user.name);
        holder.mStatus.setEnabled(user.isStatus());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(),0,0,"Name");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName,mStatus;
        ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.user_single_name);
            mStatus = (TextView) itemView.findViewById(R.id.user_single_status);
            mImage = (ImageView) itemView.findViewById(R.id.user_single_image);
        }
    }
}
