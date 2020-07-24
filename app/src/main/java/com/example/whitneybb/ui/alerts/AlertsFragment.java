package com.example.whitneybb.ui.alerts;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AlertAdapter;
import com.example.whitneybb.model.AlertsModel;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.whitneybb.MainActivity.smartFab;

public class AlertsFragment extends Fragment {

    private AlertsViewModel mViewModel;
    private List<AlertsModel> alertList = new ArrayList<>();
    private AlertAdapter recyclerAdapter;
    public static AlertsFragment newInstance() {
        return new AlertsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentPage = 4;
        smartFab(MainActivity.currentPage);
        final View v = inflater.inflate(R.layout.alerts_fragment, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.alertsRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false));
        recyclerAdapter = new AlertAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlertsViewModel.class);
        mViewModel.getAllAlerts().observe(getViewLifecycleOwner(), new Observer<List<AlertsModel>>() {
            @Override
            public void onChanged(List<AlertsModel> alertsModels) {
                alertList.clear();
                alertList.addAll(alertsModels); //todo check for duplicates in list
                recyclerAdapter.submitList(alertList);
                recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(requireContext(), "size is " + alertList.size(), Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

}