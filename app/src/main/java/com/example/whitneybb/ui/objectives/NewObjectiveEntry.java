package com.example.whitneybb.ui.objectives;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.StandardRecyclerListAdapter;
import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.model.ObjectiveModel;
import com.example.whitneybb.utils.randomDuties.IdGenerator;
import com.example.whitneybb.utils.timepicker.DatePickerFragment;
import com.example.whitneybb.utils.timepicker.TimePickerFragment;

import java.util.Calendar;
import java.util.LinkedList;

import static com.example.whitneybb.model.ObjectiveModel.ABOUT_OBJECTIVE;
import static com.example.whitneybb.model.ObjectiveModel.EXTENDING_OBJECTIVE;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_ACHIEVED;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_EXPIRY;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_ID;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_LIMITS;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_REMARK;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_REWARD;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_SCORE;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_STEPS;
import static com.example.whitneybb.model.ObjectiveModel.OBJECTIVE_TITLE;
import static com.example.whitneybb.model.ObjectiveModel.OBJ_EXTENSION_CONTENT;
import static com.example.whitneybb.model.ObjectiveModel.OBJ_EXTENSION_ID;
import static com.example.whitneybb.model.ObjectiveModel.OBJ_QUANTIFIABLE;
import static com.example.whitneybb.model.ObjectiveModel.SACRIFICE_COST;
import static com.example.whitneybb.model.ObjectiveModel.SET_OBJECTIVE_SCORE;
import static com.example.whitneybb.model.ObjectiveModel.TIMESTAMP;

public class NewObjectiveEntry extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,IdGenerator {

    private EditText sacrifice,steps,rewardField,limitField,objectiveTitle,aboutObjective;
    private StandardRecyclerListAdapter sacrificeAdapter,stepsAdapter,rewardAdapter,limitAdapter;
    private LinkedList<String> sacrificeList = new LinkedList<>(),stepsList = new LinkedList<>(),rewardList = new LinkedList<>(),limitList = new LinkedList<>();
    private ImageButton sacrificeB,stepsB,rewardB,limitB;
    private String time = ""    ,date = "";
    private TextView pickDateObj, pickTimeObj;


    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_objective_entry);

        //tv
        pickDateObj = findViewById(R.id.pickDateObj);
        pickDateObj.setOnClickListener(this);
        pickTimeObj = findViewById(R.id.pickTimeObj);
        pickTimeObj.setOnClickListener(this);

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
        objectiveTitle = findViewById(R.id.objectiveTitle);
        aboutObjective = findViewById(R.id.aboutObjective);

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

                case R.id.pickDateObj:
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.show(getSupportFragmentManager(), "date picker");
                    break;

            case R.id.pickTimeObj:
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
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

    public void validateForm(View view) {
        ObjectiveModel obj = new ObjectiveModel();
        if (objectiveTitle.getText().toString().isEmpty()) { //todo add {} and  remove from editor
            objectiveTitle.setError("Give title");
            objectiveTitle.requestFocus();
        } else if (objectiveTitle.getText().toString().contains("{") || objectiveTitle.getText().toString().contains("}")) {
            objectiveTitle.setError("Remove \" {} \" ");
        } else if (aboutObjective.getText().toString().isEmpty()) {
            aboutObjective.setError("Give about ");
            aboutObjective.requestFocus();
        } else if (limitList.size() == 0) {
            limitField.setError("Give limits");
            limitField.requestFocus();
        } else if (stepsList.size() == 0) {
            steps.setError("List steps");
            steps.requestFocus();
        } else if (sacrificeList.size() == 0) {
            sacrifice.setError("List sacrifices");
            sacrifice.requestFocus();
        } else if (rewardList.size() == 0) {
            rewardField.setError("List reward");
            rewardField.requestFocus();
        } else if (time.equals("")) {
            Toast.makeText(this, "Pick a time", Toast.LENGTH_SHORT).show();
        } else if (date.equals("")) {
            Toast.makeText(this, "Pick a date", Toast.LENGTH_SHORT).show();
        } else {
            String idTitle = objectiveTitle.getText().toString().replaceAll(" ", "");
            String timeN = Calendar.getInstance().getTime().toString();

            obj.setObjectiveId(getId(idTitle,LogModel.OBJECTIVE_LOG));
            obj.setObjectiveTitle(objectiveTitle.getText().toString());
            obj.setAboutObjective(aboutObjective.getText().toString());

            String limits = "";
            for (int i = 0;i <= limitList.size() - 1 ;i++) {
                try {
                    limits = limits.concat("{" + limitList.get(i) + "}");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            obj.setObjectiveLimits(limits);

            String sacrifices = "";
            for (int i = 0;i <= sacrificeList.size() - 1 ;i++) {
                try {
                    sacrifices = sacrifices.concat("{" + sacrificeList.get(i) + "}");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            obj.setSacrificeObjectiveCost(sacrifices);

            String rewards = "";
            for (int i = 0;i <= rewardList.size() - 1 ;i++) {
                try {
                    rewards = rewards.concat("{" + rewardList.get(i) + "}");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            obj.setObjectiveReward(rewards);

            String steps = "";
            for (int i = 0;i <= stepsList.size() - 1 ;i++) {
                try {
                    steps = steps.concat("{" + stepsList.get(i) + "}");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            obj.setObjectiveSteps(steps);

            obj.setSetAt(timeN);
            obj.setUpdatedAt(timeN);
            obj.setObjectiveExpiry(time + date);

            obj.setObjectiveRemarks("");
            obj.setObjectiveExtensionId("");
            obj.setObjectiveExtensionContent("");

            obj.setExtensionOfObjective(false);
            obj.setObjectiveAchieved(false);
            obj.setQuantifiable(false); //todo make quantifiable

            obj.setSetObjectiveScore(0);
            obj.setObjectiveId(getId(idTitle,LogModel.OBJECTIVE_LOG));


            saveObjective(obj);
        }
    }


    private void saveObjective (ObjectiveModel obj) {
        Intent data = new Intent();

        data.putExtra(OBJECTIVE_ID,obj.getObjectiveId());
        data.putExtra(ABOUT_OBJECTIVE,obj.getAboutObjective());
        data.putExtra(OBJECTIVE_TITLE,obj.getObjectiveTitle());
        data.putExtra(OBJECTIVE_EXPIRY,obj.getObjectiveExpiry());

        data.putExtra(TIMESTAMP,obj.getSetAt());
        data.putExtra(TIMESTAMP,obj.getUpdatedAt());
        data.putExtra(OBJECTIVE_LIMITS,obj.getObjectiveLimits());
        data.putExtra(OBJECTIVE_STEPS,obj.getObjectiveSteps());
        data.putExtra(SACRIFICE_COST,obj.getSacrificeObjectiveCost());

        data.putExtra(OBJECTIVE_REWARD,obj.getObjectiveReward());
        data.putExtra(OBJECTIVE_REMARK,obj.getObjectiveRemarks());
        data.putExtra(OBJ_EXTENSION_ID,obj.getObjectiveExtensionId());
        data.putExtra(OBJ_EXTENSION_CONTENT,obj.getObjectiveExtensionContent());

        data.putExtra(EXTENDING_OBJECTIVE,obj.isExtensionOfObjective());
        data.putExtra(OBJECTIVE_ACHIEVED,obj.isObjectiveAchieved());
        data.putExtra(OBJ_QUANTIFIABLE,obj.isQuantifiable());
        data.putExtra(SET_OBJECTIVE_SCORE,obj.getSetObjectiveScore());
        data.putExtra(OBJECTIVE_SCORE,obj.getObjectiveScore());

        setResult(RESULT_OK,data);
        finish();
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time = "Hour : " + hourOfDay+ " Minute : " + minute;
        pickTimeObj.setText(time);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = "Day : "+dayOfMonth + " Month : "+ month + " Year" + year;
        pickDateObj.setText(date);
    }





    @Override
    public String getId(String s, String cat) {
        return s + IdGenerator.block + cat;
    }
}