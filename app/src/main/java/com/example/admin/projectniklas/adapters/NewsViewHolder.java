package com.example.admin.projectniklas.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.projectniklas.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public TextView newsText , channel , date;
    public CircleImageView logo;

    public NewsViewHolder(View itemView){
        super(itemView);
        this.itemView = itemView;
        channel = (TextView) itemView.findViewById(R.id.channel);
        newsText = (TextView) itemView.findViewById(R.id.newsText);
        date = (TextView) itemView.findViewById(R.id.date);
        logo = (CircleImageView) itemView.findViewById(R.id.logo);


    }

}
