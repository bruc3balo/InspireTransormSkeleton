package com.example.whitneybb.ui.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AllMightyPullAdapter;
import com.example.whitneybb.adapter.SliderAdapter;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.ui.diary.pages.DiaryPagesActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.whitneybb.MainActivity.smartFab;
import static com.example.whitneybb.model.DiaryModel.DIARY_ID;

public class DiaryFragment extends Fragment {

    private DiaryViewModel diaryViewModel;
    private List<Object> diaryObjectList = new ArrayList<>();
    private AllMightyPullAdapter allMightyPullAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MainActivity.currentPage = 3;
        smartFab(MainActivity.currentPage);

        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);

        ViewPager2 viewPager2 = root.findViewById(R.id.diaryViewPager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        allMightyPullAdapter = new AllMightyPullAdapter();

        viewPager2.setAdapter(allMightyPullAdapter);
        viewPager2.setPadding(40,80,40,120);
        viewPager2.setClipToPadding(true);

        viewPager2.setClipChildren(true);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(60));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleX(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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

        /*new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                diaryViewModel.delete(allMightyPullAdapter.getDiaryAt(viewHolder.getAdapterPosition()));
                Toast.makeText(requireContext(), "Diary Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView();*/

        allMightyPullAdapter.setOnItemClickListener(object -> {
            DiaryModel diary = (DiaryModel) object;
            Toast.makeText(requireContext(), "Diary " + diary.getDiaryCoverUrl(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(requireContext(), DiaryPagesActivity.class).putExtra(DIARY_ID,diary.getDiaryId()));
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);

        diaryViewModel.getAllDiaries().observe(getViewLifecycleOwner(), diaryModels -> {
            diaryObjectList.clear();
            diaryObjectList.addAll(diaryModels); //todo check for duplicates in list
            allMightyPullAdapter.submitList(diaryObjectList);
            allMightyPullAdapter.notifyDataSetChanged();
            Toast.makeText(requireContext(), "size is " + diaryObjectList.size(), Toast.LENGTH_SHORT).show();
            Toast.makeText(requireContext(), "onChanged", Toast.LENGTH_SHORT).show();
        });
    }


    private void backUpDiaries() {

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