package com.nancompany.newsplanet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nancompany.newsplanet.R;
import com.nancompany.newsplanet.models.Subscribe;

import java.util.List;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeViewHolder> {

    private List<Subscribe> subs;
    public SubscribeAdapter(List<Subscribe> subs){this.subs = subs; }
    @Override
    public SubscribeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscride,parent,false);
        return new SubscribeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubscribeViewHolder holder, int position) {
        Subscribe sub = subs.get(position);
        holder.name.setText(sub.getName());
        holder.url.setText(sub.getUrl());

    }

    @Override
    public int getItemCount() {
        return subs.size();
    }
}
