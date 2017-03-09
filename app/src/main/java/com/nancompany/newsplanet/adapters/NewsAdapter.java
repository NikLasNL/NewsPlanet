package com.nancompany.newsplanet.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nancompany.newsplanet.NewsPlanetApplication;
import com.nancompany.newsplanet.R;
import com.nancompany.newsplanet.models.News;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    ArrayList<News> newsList;
    Context context;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    SharedPreferences sp = NewsPlanetApplication.getSp();
    String timeZone = sp.getString("timeZone", "GMT+0");


    public NewsAdapter(ArrayList<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    public NewsAdapter(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        final News news = newsList.get(position);
        holder.date.setText(dateFormat.format(news.getDate()));
        holder.channel.setText(news.getChannel());
        holder.newsText.setText(news.getNews());
        Picasso.with(context).load(String.valueOf(news.getLogo()))
                .placeholder(R.drawable.logo_placeholder)
                .into(holder.logo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(news.getWeb());
                Intent link = new Intent(Intent.ACTION_VIEW,uri);
                link.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(link);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
