package com.example.admin.projectniklas.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.admin.projectniklas.R;
import com.example.admin.projectniklas.StartActivity;
import com.example.admin.projectniklas.adapters.SubscribeAdapter;
import com.example.admin.projectniklas.models.Subscribe;
import com.example.admin.projectniklas.presenters.SubscribePresenter;
import com.example.admin.projectniklas.views.SubscribeView;

import java.util.List;

public class SubscribeFragment extends MvpFragment implements SubscribeView {

    private View fragmentView;
    private RecyclerView recyclerView;
    private Button addButton;

    @InjectPresenter
    SubscribePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if (fragmentView == null) fragmentView = inflater.from(container.getContext())
                .inflate(R.layout.fragment_subscribe, container, false);

        getActivity().setTitle("Подписки");
        ((StartActivity) getActivity()).checkMenu(R.id.faseMenuItem);

        addButton = (Button) fragmentView.findViewById(R.id.addButton);
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(lm);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        ItemTouchHelper.SimpleCallback swipe = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) { //ДОПИСАТЬ \\ ПРОБЛЕМС
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
        SubscribeAdapter adapter = new SubscribeAdapter(subs);
        recyclerView.setAdapter(adapter);
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


