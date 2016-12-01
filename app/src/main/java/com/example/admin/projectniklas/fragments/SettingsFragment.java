package com.example.admin.projectniklas.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.projectniklas.R;
import com.example.admin.projectniklas.StartActivity;

public class SettingsFragment extends Fragment {

    View fragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        if(fragmentView== null) fragmentView = inflater.from(container.getContext())
                .inflate(R.layout.fragment_settings,container,false);
        getActivity().setTitle("Настройки");
        ((StartActivity) getActivity()).checkMenu(R.id.settingsMenuItem);
        return fragmentView;
    }
}
