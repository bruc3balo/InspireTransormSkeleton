package com.example.whitneybb.ui.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.AlertAdapter;
import com.example.whitneybb.model.AlertsModel;


import java.util.LinkedList;

public class Wishlist extends AppCompatActivity {

    private LinkedList<AlertsModel> list = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);


        RecyclerView recyclerView = findViewById(R.id.wishlistRecyclerView);

        for (int i = 0; i< 4;i++) {
            list.add(new AlertsModel());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        AlertAdapter recylerAdapter = new AlertAdapter();
        recyclerView.setAdapter(recylerAdapter);
        recylerAdapter.submitList(list);
    }
}