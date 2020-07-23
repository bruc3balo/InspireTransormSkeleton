package com.example.whitneybb.ui.goals;

import androidx.lifecycle.ViewModelProviders;

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

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.SliderAdapter;

import java.util.LinkedList;

public class GoalsFragment extends Fragment {

    private GoalsViewModel mViewModel;
    private ViewPager2 viewPager2;
    private LinkedList<GoalsFragment> list = new LinkedList<>();

    public static GoalsFragment newInstance() {
        return new GoalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.goals_fragment, container, false);

        SliderAdapter.currentClass = 3;


        for (int i = 0;i<1;i++) {
            list.add(new GoalsFragment());
        }

        viewPager2 = v.findViewById(R.id.goalsViewPager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        SliderAdapter sliderAdapter = new SliderAdapter(requireContext(),list);
        viewPager2.setAdapter(sliderAdapter);
        viewPager2.setPadding(40,80,40,80);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
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

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        SliderAdapter.currentClass = 3;
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