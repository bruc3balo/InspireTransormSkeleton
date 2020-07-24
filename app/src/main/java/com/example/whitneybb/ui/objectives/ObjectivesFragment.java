package com.example.whitneybb.ui.objectives;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AllMightyPullAdapter;
import com.example.whitneybb.adapter.SliderAdapter;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.ObjectiveModel;
import com.example.whitneybb.ui.goals.GoalsViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.whitneybb.MainActivity.smartFab;

public class ObjectivesFragment extends Fragment {

    private ObjectivesViewModel homeViewModel;
    private ViewPager2 viewPager2;
    private List<Object> objectiveList = new ArrayList<>();
    private AllMightyPullAdapter allMightyPullAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainActivity.currentPage = 2;
        smartFab(MainActivity.currentPage);
        homeViewModel = new ViewModelProvider(this).get(ObjectivesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_objectives, container, false);


        for (int i = 0;i<1;i++) {

        }

        viewPager2 = root.findViewById(R.id.objectiveViewPager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        allMightyPullAdapter = new AllMightyPullAdapter();
        viewPager2.setAdapter(allMightyPullAdapter);
        viewPager2.setPadding(40,80,40,120);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(60));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleX(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(requireContext(), "adapter : "+viewPager2.getCurrentItem(), Toast.LENGTH_SHORT).show();
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        } );

        allMightyPullAdapter.setOnItemClickListener(new AllMightyPullAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object) {

            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(ObjectivesViewModel.class);
        homeViewModel.getAllObjectives().observe(getViewLifecycleOwner(), new Observer<List<ObjectiveModel>>() {
            @Override
            public void onChanged(List<ObjectiveModel> objectiveModels) {
                objectiveList.clear();
                objectiveList.addAll(objectiveModels); //todo check for duplicates in list
                allMightyPullAdapter.submitList(objectiveList);
                allMightyPullAdapter.notifyDataSetChanged();
                Toast.makeText(requireContext(), "size is " + objectiveList.size(), Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        SliderAdapter.currentClass = 0;
        super.onResume();
    }

    @Override
    public void onStop() {
    //    SliderAdapter.currentClass = 4;
        super.onStop();
    }

    @Override
    public void onDestroy() {
    //    SliderAdapter.currentClass = 4;
        super.onDestroy();
    }
}