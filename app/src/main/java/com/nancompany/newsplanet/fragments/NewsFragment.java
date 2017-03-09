package com.nancompany.newsplanet.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.nancompany.newsplanet.R;
import com.nancompany.newsplanet.StartActivity;
import com.nancompany.newsplanet.adapters.NewsAdapter;
import com.nancompany.newsplanet.models.News;
import com.nancompany.newsplanet.presenters.NewsPresenter;
import com.nancompany.newsplanet.views.NewsView;

import java.util.ArrayList;

public class NewsFragment extends MvpFragment implements NewsView {

    private View fragmentView;
    private RecyclerView feedFragment;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;

    @InjectPresenter
    NewsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if (fragmentView == null) fragmentView = inflater.from(container.getContext())
                .inflate(R.layout.fragment_news, container, false);

        getActivity().setTitle("Лента");
        ((StartActivity) getActivity()).checkMenu(R.id.feedMenuItem);


        feedFragment = (RecyclerView) fragmentView.findViewById(R.id.feedFragment);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext());
        feedFragment.setLayoutManager(lm);

        fab = (FloatingActionButton)getActivity().findViewById(R.id.addButton);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((StartActivity) getActivity()).openSub();
            }
        });

        swipeRefreshLayout= (SwipeRefreshLayout)fragmentView.findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateNews();
            }
        });

        return fragmentView;
    }

    @Override
    public void onDataReceived(ArrayList<News> news) {
        adapter = new NewsAdapter(news, getActivity().getApplicationContext());
        feedFragment.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

    }


    @Override
    public void onErrorReceived() {
        Toast.makeText(getActivity().getApplicationContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

}
