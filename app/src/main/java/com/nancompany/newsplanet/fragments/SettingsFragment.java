package com.nancompany.newsplanet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.nancompany.newsplanet.R;
import com.nancompany.newsplanet.StartActivity;
import com.nancompany.newsplanet.presenters.SettingsPresenter;
import com.nancompany.newsplanet.views.SettingsView;

public class SettingsFragment extends MvpFragment implements SettingsView {

    private View fragmentView;
    private Spinner timeZone;
    private ArrayAdapter<CharSequence> adapter;
    private FloatingActionButton fab;

    @InjectPresenter
    SettingsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if (fragmentView == null) fragmentView = inflater.from(container.getContext())
                .inflate(R.layout.fragment_settings, container, false);
        getActivity().setTitle("Настройки");

        fab = (FloatingActionButton)getActivity().findViewById(R.id.addButton);
        fab.setVisibility(View.INVISIBLE);



        timeZone = (Spinner) fragmentView.findViewById(R.id.timeZone);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.time_zone, android.R.layout.simple_spinner_item);
        timeZone.setAdapter(adapter);
        presenter.getTimeZone();

        timeZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.setTimeZone(adapter.getItem(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ((StartActivity) getActivity()).checkMenu(R.id.settingsMenuItem);
        return fragmentView;
    }


    @Override
    public void ooTimeZoneReceived(String zone) {
        int position = adapter.getPosition(zone);
        timeZone.setSelection(adapter.getPosition(zone));

    }
}

