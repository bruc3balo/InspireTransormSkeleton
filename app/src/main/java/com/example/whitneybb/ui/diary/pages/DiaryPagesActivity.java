package com.example.whitneybb.ui.diary.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AllMightyPullAdapter;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;
import com.example.whitneybb.ui.editors.Diary_NotesEditorActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.ui.diary.DiaryFragment.DIARY_KEY;


public class DiaryPagesActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private List<Object> diaryPagesObjectList = new ArrayList<>();
    private AllMightyPullAdapter allMightyPullAdapter;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_pages);

        MainActivity.currentPage = 6;

        String dId = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get(DIARY_KEY)).toString();

        allMightyPullAdapter = new AllMightyPullAdapter();
        viewPager2 = findViewById(R.id.diaryPagesPager);
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

       /* new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
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
            DiaryPageModel page = (DiaryPageModel) object;
            Toast.makeText(DiaryPagesActivity.this, "Diary " + page.getEntryId(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DiaryPagesActivity.this, Diary_NotesEditorActivity.class).putExtra(DIARY_KEY,page.getDiaryId()));
        });

        DiaryPagesViewModel diaryPagesViewModel = new ViewModelProvider(this).get(DiaryPagesViewModel.class);

        diaryPagesViewModel.getAllDiaryPages().observe(this, diaryPageModels -> {
            System.out.println(diaryPageModels.size()+"");
            //Toast.makeText(DiaryPagesActivity.this, ""+diaryPageModels.get(0).getDiaryId(), Toast.LENGTH_SHORT).show();
        });
      /*  diaryPagesViewModel.getDiaryPagesWithId(dId).observe(this, diaryPageModel -> {
            diaryPagesObjectList.clear();
            System.out.println("Page loaded "+count++);
            diaryPagesObjectList.add(diaryPageModel);
            allMightyPullAdapter.submitList(diaryPagesObjectList);
        });*/
    }


}