package com.example.whitneybb.ui.alerts;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AlertAdapter;
import com.example.whitneybb.model.AlertsModel;
import com.example.whitneybb.ui.diary.DiaryFragment;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.MainActivity.smartFab;

public class AlertsFragment extends Fragment {

    private static AlertsViewModel mViewModel;
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

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        recyclerAdapter = new AlertAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                requireActivity().getWindow().setStatusBarColor(Color.RED);
                new Handler().postDelayed(() -> requireActivity().getWindow().setStatusBarColor(Color.BLACK), 300);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //todo delete dialog
                //todo conform delete dialog
                mViewModel.delete(alertList.get(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        recyclerAdapter.setClickListener((view, position) -> {
            Toast.makeText(requireContext(), ""+alertList.get(position).getRepeatDays(), Toast.LENGTH_SHORT).show();
        });


        return v;
    }



    public static AlertsViewModel getViewModel() {
        return mViewModel;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlertsViewModel.class);
        mViewModel.getAllAlerts().observe(getViewLifecycleOwner(), alertsModels -> {
            alertList.clear();
            alertList.addAll(alertsModels); //todo check for duplicates in list
            recyclerAdapter.submitList(alertList);
            recyclerAdapter.notifyDataSetChanged();
            Toast.makeText(requireContext(), "size is " + alertList.size(), Toast.LENGTH_SHORT).show();
            Toast.makeText(requireContext(), "onChanged", Toast.LENGTH_SHORT).show();
            // alertList.add(new AlertsModel());
        });
    }

}