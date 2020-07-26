package com.example.whitneybb.ui.objectives;

import android.content.Intent;
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

import java.util.LinkedList;

public class NewObjectiveEntry extends AppCompatActivity implements View.OnClickListener {

    private EditText sacrifice,steps,rewardField,limitField;
    private StandardRecyclerListAdapter sacrificeAdapter,stepsAdapter,rewardAdapter,limitAdapter;
    private LinkedList<String> sacrificeList = new LinkedList<>(),stepsList = new LinkedList<>(),rewardList = new LinkedList<>(),limitList = new LinkedList<>();
    private ImageButton sacrificeB,stepsB,rewardB,limitB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_objective_entry);

        //lists
        sacrificeB = findViewById(R.id.sacrificeAdd);
        sacrificeB.setOnClickListener(this);
        stepsB = findViewById(R.id.addStep);
        stepsB.setOnClickListener(this);
        rewardB = findViewById(R.id.objectiveRewardAdd);
        rewardB.setOnClickListener(this);
        limitB = findViewById(R.id.addObjLimit);
        limitB.setOnClickListener(this);

        //field
        sacrifice = findViewById(R.id.sacrificeField);
        steps = findViewById(R.id.objectiveStepsField);
        rewardField = findViewById(R.id.objectiveRewardField);
        limitField = findViewById(R.id.ObjectiveLimitsField);

        //adapter
        sacrificeAdapter = new StandardRecyclerListAdapter(this, sacrificeList);
        stepsAdapter = new StandardRecyclerListAdapter(this, stepsList);
        rewardAdapter = new StandardRecyclerListAdapter(this, rewardList);
        limitAdapter = new StandardRecyclerListAdapter(this, limitList);

        //rv
        RecyclerView sacrificeRv = findViewById(R.id.objSacrificeRv);
        sacrificeRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        sacrificeRv.setAdapter(sacrificeAdapter);

        RecyclerView stepsRv = findViewById(R.id.objStepsRv);
        stepsRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        stepsRv.setAdapter(stepsAdapter);

        RecyclerView rewardRv = findViewById(R.id.objRewardRv);
        rewardRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rewardRv.setAdapter(rewardAdapter);

        RecyclerView limitRv = findViewById(R.id.objLimitRv);
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
            case R.id.sacrificeAdd:
                addItem(sacrifice,sacrificeList);
                break;

            case R.id.addStep:
                addItem(steps,stepsList);
                break;

            case R.id.objectiveRewardAdd:
                addItem(rewardField,rewardList);
                break;

            case R.id.addObjLimit:
                addItem(limitField,limitList);
                break;
            default:break;
        }
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

    private void updateLists() {
        stepsAdapter.notifyDataSetChanged();
        sacrificeAdapter.notifyDataSetChanged();
        rewardAdapter.notifyDataSetChanged();
        limitAdapter.notifyDataSetChanged();
    }
}