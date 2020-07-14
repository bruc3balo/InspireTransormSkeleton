package com.example.whitneybb.ui.alerts;

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

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.RecylerAdapter;

import java.util.LinkedList;

public class AlertsFragment extends Fragment {

    private AlertsViewModel mViewModel;
    private LinkedList<Object> list = new LinkedList<>();

    public static AlertsFragment newInstance() {
        return new AlertsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.alerts_fragment, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.alertsRecyclerView);

        for (int i = 0; i< 4;i++) {
            list.add(new Object());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false));
        RecylerAdapter recylerAdapter = new RecylerAdapter(requireContext(),list);
        recyclerView.setAdapter(recylerAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AlertsViewModel.class);
        // TODO: Use the ViewModel
    }

}