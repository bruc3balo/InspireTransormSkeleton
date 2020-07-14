package com.example.whitneybb.ui.diary;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.SliderAdapter;

import java.util.LinkedList;

public class DiaryFragment extends Fragment {

    private DiaryViewModel galleryViewModel;
    private ViewPager2 viewPager2;
    private LinkedList<DiaryFragment> list = new LinkedList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SliderAdapter.currentClass = 1;

        galleryViewModel =
                ViewModelProviders.of(this).get(DiaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        for (int i = 0;i<4;i++) {
            list.add(new DiaryFragment());
        }

        viewPager2 = root.findViewById(R.id.diaryViewPager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        SliderAdapter sliderAdapter = new SliderAdapter(requireContext(),list);
        viewPager2.setAdapter(sliderAdapter);
        viewPager2.setPadding(40,80,40,120);
        viewPager2.setClipToPadding(true);
        viewPager2.setClipChildren(true);
        viewPager2.setOffscreenPageLimit(1);
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
        return root;
    }

    @Override
    public void onResume() {
        SliderAdapter.currentClass = 1;
        super.onResume();
    }

    @Override
    public void onStop() {
      //  SliderAdapter.currentClass = 4;
        super.onStop();
    }


    @Override
    public void onDestroy() {
       // SliderAdapter.currentClass = 4;
        super.onDestroy();
    }
}