package com.nancompany.newsplanet.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.nancompany.newsplanet.R;
import com.nancompany.newsplanet.StartActivity;
import com.nancompany.newsplanet.adapters.SubscribeAdapter;
import com.nancompany.newsplanet.models.Subscribe;
import com.nancompany.newsplanet.presenters.SubscribePresenter;
import com.nancompany.newsplanet.views.SubscribeView;

import java.util.List;

public class SubscribeFragment extends MvpFragment implements SubscribeView {

    private View fragmentView;
    private RecyclerView recyclerView;
    private FloatingActionButton fag;

    @InjectPresenter
    SubscribePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if (fragmentView == null) fragmentView = inflater.from(container.getContext())
                .inflate(R.layout.fragment_subscribe, container, false);

        getActivity().setTitle("Подписки");
        ((StartActivity) getActivity()).checkMenu(R.id.faseMenuItem);

        fag = (FloatingActionButton) getActivity().findViewById(R.id.addButton);
        fag.setVisibility(View.VISIBLE);


        //addButton = (Button) fragmentView.findViewById(R.id.addButton);
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(lm);

        fag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        ItemTouchHelper.SimpleCallback swipe = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                TextView url = (TextView) viewHolder.itemView.findViewById(R.id.url);
                presenter.deleteSubscribe(url.getText().toString());

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipe);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return fragmentView;
    }

    @Override
    public void getAllSubscribe(List<Subscribe> subs) {
        if(subs.isEmpty())showDialog();
        else {
        SubscribeAdapter adapter = new SubscribeAdapter(subs);
        recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onErrorReceived(String s) {
        Toast.makeText(getActivity().getApplicationContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

    private void showDialog() {
        View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.dialog_subscribe, null);
        final EditText url = (EditText) v.findViewById(R.id.url);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Добавить подписку")
                .setView(v)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.saveSubscribe(url.getText().toString());

                    }
                }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create();
        dialog.show();

    }


}


