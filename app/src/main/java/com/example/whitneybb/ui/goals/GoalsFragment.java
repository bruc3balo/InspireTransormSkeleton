package com.example.whitneybb.ui.goals;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AllMightyPullAdapter;
import com.example.whitneybb.adapter.SliderAdapter;
import com.example.whitneybb.model.GoalsModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.whitneybb.MainActivity.smartFab;
import static com.example.whitneybb.model.GoalsModel.GOAL_ID;

public class GoalsFragment extends Fragment {

    private GoalsViewModel mViewModel;
    private ViewPager2 viewPager2;
    private List<Object> goalObjectList = new ArrayList<>();
    private AllMightyPullAdapter allMightyPullAdapter;
    public static GoalsFragment newInstance() {
        return new GoalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.goals_fragment, container, false);

        MainActivity.currentPage = 1;
        smartFab(MainActivity.currentPage);


        viewPager2 = v.findViewById(R.id.goalsViewPager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        allMightyPullAdapter = new AllMightyPullAdapter();
        viewPager2.setAdapter(allMightyPullAdapter);
        viewPager2.setPadding(40,80,40,80);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleX(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        } );


        allMightyPullAdapter.setOnItemClickListener(object -> {
            GoalsModel goal = (GoalsModel) object;
            startActivity(new Intent(requireContext(), NewGoalEntry.class).putExtra(GOAL_ID,goal.getGoalId()));
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);
        mViewModel.getAllGoals().observe(getViewLifecycleOwner(), goalsModels -> {
            goalObjectList.clear();
            goalObjectList.addAll(goalsModels); //todo check for duplicates in list
            allMightyPullAdapter.submitList(goalObjectList);
            allMightyPullAdapter.notifyDataSetChanged();
            Toast.makeText(requireContext(), "size is " + goalObjectList.size(), Toast.LENGTH_SHORT).show();
            Toast.makeText(requireContext(), "onChanged", Toast.LENGTH_SHORT).show();
           // goalObjectList.add(new GoalsModel());
        });
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        MainActivity.currentPage = 1;
        super.onResume();
    }

    @Override
    public void onStop() {
     //   SliderAdapter.currentClass = 4;
        super.onStop();
    }


    @Override
    public void onDestroy() {
    //    SliderAdapter.currentClass = 4;
        super.onDestroy();
    }
}