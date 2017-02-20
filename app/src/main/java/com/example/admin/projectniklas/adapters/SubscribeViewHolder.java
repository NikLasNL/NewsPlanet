package com.example.admin.projectniklas.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.projectniklas.R;

public class SubscribeViewHolder extends RecyclerView.ViewHolder{

    TextView name,url;

    public SubscribeViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        url = (TextView) itemView.findViewById(R.id.url);
    }
}
