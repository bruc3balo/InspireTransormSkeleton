package com.example.whitneybb.ui.diary.pages;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.whitneybb.login.LoginActivity;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.DiaryPageModel;
import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.ui.editors.Diary_NotesEditorActivity;
import com.example.whitneybb.utils.randomDuties.IdGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.model.DiaryModel.DIARY_ID;
import static com.example.whitneybb.model.DiaryPageModel.ENTRY_ID;
import static com.example.whitneybb.ui.editors.Diary_NotesEditorActivity.EDITOR_SPECIFIC;


public class DiaryPagesActivity extends AppCompatActivity implements IdGenerator {

    private ViewPager2 viewPager2;
    private List<Object> diaryPagesObjectList = new ArrayList<>();
    private AllMightyPullAdapter allMightyPullAdapter;
    private int count = 0;
    private DiaryPagesViewModel diaryPagesViewModel;
    private String dId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_pages);

        diaryPagesViewModel = new ViewModelProvider(this).get(DiaryPagesViewModel.class);


        MainActivity.currentPage = 6;

        dId = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get(DIARY_ID)).toString();

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
                changePage(String.valueOf(position+1));
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        } );


        allMightyPullAdapter.setOnItemClickListener(object -> {
            DiaryPageModel page = (DiaryPageModel) object;
            Toast.makeText(DiaryPagesActivity.this, "Diary " + page.getEntryId(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DiaryPagesActivity.this, Diary_NotesEditorActivity.class).putExtra(ENTRY_ID,page.getEntryId()).putExtra(EDITOR_SPECIFIC,"DIARY"));
        });


        diaryPagesViewModel.getAllDiaryPages().observe(this, diaryPageModels -> {
            diaryPagesObjectList.clear();
            diaryPagesObjectList.addAll(diaryPageModels);
            allMightyPullAdapter.submitList(diaryPagesObjectList);
            allMightyPullAdapter.notifyDataSetChanged();
            System.out.println(diaryPageModels.size()+"");
        });

        getWindow().setStatusBarColor(Color.DKGRAY);

    }

    private void changePage (String c) {
        TextView pageCount = findViewById(R.id.pageCount);
        pageCount.setText(c);
    }


    public void addNewPage(View view) {
        Dialog new_Page = new Dialog(this);
        new_Page.setContentView(R.layout.diary_page);
        EditText diaryPageTitleField = new_Page.findViewById(R.id.diaryPageTitleField);
        Button savePage = new_Page.findViewById(R.id.savePageButton);
        new_Page.show();
        savePage.setOnClickListener(v -> {
            if (diaryPageTitleField.getText().toString().isEmpty()) {
                diaryPageTitleField.setError("Set title");
                diaryPageTitleField.requestFocus();
            } else {
                String ttl = diaryPageTitleField.getText().toString().replaceAll(" ","");
                String time = Calendar.getInstance().getTime().toString();

                DiaryPageModel pageModel = new DiaryPageModel();
                pageModel.setCreatedAt(time);
                pageModel.setDiaryId(dId);
                pageModel.setEntryId(getId(ttl,LogModel.DIARY_PAGE_LOG));
                pageModel.setEntryBody("");
                pageModel.setEntryTitle(diaryPageTitleField.getText().toString());
                pageModel.setUpdatedAt(time);

                diaryPagesViewModel.insert(pageModel);
                new_Page.cancel();
            }
        });

    }

    @Override
    public String getId(String s, String cat) {
        return s+IdGenerator.block+cat;
    }
}