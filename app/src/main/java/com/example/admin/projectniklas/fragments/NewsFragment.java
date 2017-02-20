package com.example.admin.projectniklas.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.admin.projectniklas.R;
import com.example.admin.projectniklas.StartActivity;
import com.example.admin.projectniklas.adapters.NewsAdapter;
import com.example.admin.projectniklas.models.News;
import com.example.admin.projectniklas.presenters.NewsPresenter;
import com.example.admin.projectniklas.views.NewsView;

import java.util.ArrayList;

public class NewsFragment extends MvpFragment implements NewsView {

    private View fragmentView;
    private RecyclerView feedFragment;
    private NewsAdapter adapter;

    @InjectPresenter
    NewsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if(fragmentView== null) fragmentView = inflater.from(container.getContext())
                .inflate(R.layout.fragment_news,container,false);

        getActivity().setTitle("Лента");
        ((StartActivity) getActivity()).checkMenu(R.id.feedMenuItem);

        /*feedFragment.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("web"));

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/


        feedFragment = (RecyclerView) fragmentView.findViewById(R.id.feedFragment);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext());
        feedFragment.setLayoutManager(lm);

        return fragmentView;
    }

    @Override
    public void onDataReceived(ArrayList<News> news){
        adapter = new NewsAdapter(news,getActivity().getApplicationContext());
        feedFragment.setAdapter(adapter);


    }


    @Override
    public void onErrorReceived(){
        Toast.makeText(getActivity().getApplicationContext(),"Ошибка получения данных",Toast.LENGTH_SHORT).show();
    }

}
