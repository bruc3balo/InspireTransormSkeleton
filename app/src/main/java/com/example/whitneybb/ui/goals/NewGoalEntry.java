package com.example.whitneybb.ui.goals;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.StandardRecyclerListAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.LinkedList;


public class NewGoalEntry extends AppCompatActivity implements  View.OnClickListener {
    private ImageButton doB, dontB, rewardB, limitB;
    private EditText doField, dontField, rewardField, limitField;
    private LinkedList<String> doList = new LinkedList<>(), dontList = new LinkedList<>(), rewardList = new LinkedList<>(), limitList = new LinkedList<>();
    private StandardRecyclerListAdapter doAdapter, dontAdapter, rewardAdapter, limitAdapter;
    private Chip shortTerm, midTerm, longTerm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal_entry);
        final Chip[] chipList = new Chip[]{shortTerm = findViewById(R.id.shortTermChip), midTerm = findViewById(R.id.midTermChip), longTerm = findViewById(R.id.longTermChip)};
        //chips
        chipList[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedChip(chipList,0);
            }
        });

        chipList[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedChip(chipList,1);
            }
        });
        chipList[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedChip(chipList,2);
            }
        });


        //lists
        doB = findViewById(R.id.addDo);
        doB.setOnClickListener(this);
        dontB = findViewById(R.id.addDont);
        dontB.setOnClickListener(this);
        rewardB = findViewById(R.id.addReward);
        rewardB.setOnClickListener(this);
        limitB = findViewById(R.id.addLimitation);
        limitB.setOnClickListener(this);

        //field
        doField = findViewById(R.id.doField);
        dontField = findViewById(R.id.dontField);
        rewardField = findViewById(R.id.rewardField);
        limitField = findViewById(R.id.limitationField);

        //adapter
        doAdapter = new StandardRecyclerListAdapter(this, doList);
        dontAdapter = new StandardRecyclerListAdapter(this, dontList);
        rewardAdapter = new StandardRecyclerListAdapter(this, rewardList);
        limitAdapter = new StandardRecyclerListAdapter(this, limitList);

        //rv
        RecyclerView doRv = findViewById(R.id.doRv);
        doRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        doRv.setAdapter(doAdapter);

        RecyclerView dontRv = findViewById(R.id.dontRv);
        dontRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dontRv.setAdapter(dontAdapter);

        RecyclerView rewardRv = findViewById(R.id.rewardRv);
        rewardRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rewardRv.setAdapter(rewardAdapter);

        RecyclerView limitRv = findViewById(R.id.limitRv);
        limitRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        limitRv.setAdapter(limitAdapter);

        getWindow().setStatusBarColor(Color.BLACK);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int checkSelfPermission(String permission) {
        return super.checkSelfPermission(permission);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addDo:
                addItem(doField, doList);
                break;
            case R.id.addDont:
                addItem(dontField, dontList);
                break;
            case R.id.addReward:
                addItem(rewardField, rewardList);
                break;
            case R.id.addLimitation:
                addItem(limitField, limitList);
                break;
            default:
                break;
        }
    }

    private void updateLists() {
        doAdapter.notifyDataSetChanged();
        dontAdapter.notifyDataSetChanged();
        rewardAdapter.notifyDataSetChanged();
        limitAdapter.notifyDataSetChanged();
    }

    private void setSelectedChip(Chip[] chipList,int selectedChip) {
        LinkedList<Integer> others = new LinkedList<>();
        others.add(0);
        others.add(1);
        others.add(2);
        others.remove(selectedChip);
        switch (selectedChip) {
            case 0:
                chipList[selectedChip].setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_red_light)));
                chipList[others.get(0)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                chipList[others.get(1)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                break;
            case 1:
                chipList[selectedChip].setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_orange_light)));
                chipList[others.get(0)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                chipList[others.get(1)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                break;
            case 2:
                chipList[selectedChip].setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_green_light)));
                chipList[others.get(0)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                chipList[others.get(1)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                break;
            default:break;
        }
        others.clear();
    }

    private void addItem(EditText field, LinkedList<String> list) {
        String doS = field.getText().toString().trim();
        if (list.contains(doS)) {
            Toast.makeText(this, "Already added", Toast.LENGTH_SHORT).show();
        } else if (doS.trim().isEmpty()) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        } else {
            list.add(doS);
        }
        updateLists();

        if (list.contains(doS)) {
            field.setText("");
        } else {
            Toast.makeText(this, "Not saved : " + field.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        }
    }
}