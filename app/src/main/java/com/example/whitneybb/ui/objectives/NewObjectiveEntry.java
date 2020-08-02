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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.StandardRecyclerListAdapter;
import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.model.ObjectiveModel;
import com.example.whitneybb.ui.editors.Diary_NotesEditorActivity;
import com.example.whitneybb.utils.randomDuties.IdGenerator;
import com.example.whitneybb.utils.timepicker.DatePickerFragment;
import com.example.whitneybb.utils.timepicker.TimePickerFragment;
import com.google.android.material.chip.Chip;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.example.whitneybb.adapter.AllMightyPullAdapter.listFromString;
import static com.example.whitneybb.model.GoalsModel.LONG_TERM;
import static com.example.whitneybb.model.GoalsModel.MID_TERM;
import static com.example.whitneybb.model.GoalsModel.SHORT_TERM;
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
import static com.example.whitneybb.model.ObjectiveModel.OBJ_NOTES;
import static com.example.whitneybb.model.ObjectiveModel.OBJ_QUANTIFIABLE;
import static com.example.whitneybb.model.ObjectiveModel.SACRIFICE_COST;
import static com.example.whitneybb.model.ObjectiveModel.SET_OBJECTIVE_SCORE;
import static com.example.whitneybb.model.ObjectiveModel.TIMESTAMP;
import static com.example.whitneybb.ui.editors.Diary_NotesEditorActivity.EDITOR_SPECIFIC;
import static com.example.whitneybb.ui.goals.NewGoalEntry.listToString;

public class NewObjectiveEntry extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, IdGenerator {

    private EditText sacrifice, steps, rewardField, limitField, objectiveTitle, aboutObjective;
    private StandardRecyclerListAdapter sacrificeAdapter, stepsAdapter, rewardAdapter, limitAdapter;
    private LinkedList<String> sacrificeList = new LinkedList<>(), stepsList = new LinkedList<>(), rewardList = new LinkedList<>(), limitList = new LinkedList<>();
    private ImageButton sacrificeB, stepsB, rewardB, limitB;
    private String time = "", date = "";
    private TextView pickDateObj, pickTimeObj, notesBodyObj,objNoteTv;
    private boolean isUpdating;
    private ObjectivesViewModel objectivesViewModel;
    private ObjectiveModel editorObj;
    private static final int NOTE_REQUEST = 1;
    private String uid = "", objId = "", noteC = "";
    private boolean formValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objectivesViewModel = new ViewModelProvider(this).get(ObjectivesViewModel.class);
        formValid = false;

        if (getIntent().getExtras() != null) {
            isUpdating = true;
            objId = Objects.requireNonNull(getIntent().getExtras().get(OBJECTIVE_ID)).toString();
        }

        setContentView(R.layout.activity_new_objective_entry);

        Toolbar toolbar = findViewById(R.id.newObjToolbar);
        setSupportActionBar(toolbar);

        Button submitObj = findViewById(R.id.submitObj);
        TextView expiryPickerTv = findViewById(R.id.expiryPickerTv);
        notesBodyObj = findViewById(R.id.notesBodyObj);
        notesBodyObj.setVisibility(View.GONE);

        //tv
        pickDateObj = findViewById(R.id.pickDateObj);
        pickDateObj.setOnClickListener(this);
        pickTimeObj = findViewById(R.id.pickTimeObj);
        pickTimeObj.setOnClickListener(this);
        objNoteTv = findViewById(R.id.objNoteTv);

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

        if (isUpdating) {

            objNoteTv.setVisibility(View.VISIBLE);
            toolbar.setTitle("Updating Objective");
            toolbar.setSubtitle("Making changes ...");
            submitObj.setText("Update");
            expiryPickerTv.setText("Modify date and time for this objective ");
            notesBodyObj.setVisibility(View.VISIBLE);
            objNoteTv.setOnClickListener(v -> startActivityForResult(new Intent(this, Diary_NotesEditorActivity.class).putExtra(OBJECTIVE_ID, objId).putExtra(EDITOR_SPECIFIC, "OBJECTIVE"), NOTE_REQUEST));

            objectivesViewModel.getAllObjectives().observe(this, objectiveModels -> {
                for (int i = 0; i <= objectiveModels.size() - 1; i++) {
                    if (objectiveModels.get(i).getObjectiveId().equals(objId)) {
                        editorObj = objectiveModels.get(i);
                        objectiveTitle.setText(editorObj.getObjectiveTitle());
                        aboutObjective.setText(editorObj.getAboutObjective());
                        noteC = editorObj.getObj_notes();
                        pickTimeObj.setText(editorObj.getObjectiveExpiry());
                        pickDateObj.setText(editorObj.getObjectiveExpiry());
                        updateRvs(editorObj.getObjectiveReward(), editorObj.getObjectiveLimits(), editorObj.getObjectiveSteps(), editorObj.getSacrificeObjectiveCost());
                        setNoteDisplay(editorObj);
                        Toast.makeText(this, ""+editorObj.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            objNoteTv.setVisibility(View.GONE);
            toolbar.setTitle("New Objective");
            toolbar.setSubtitle("A journey begin");
            submitObj.setText("Create");
            expiryPickerTv.setText("Set a date and time for objective to achieve this objective");
            notesBodyObj.setVisibility(View.GONE);
        }
    }

    private void updateRvs(String reward, String limit, String step, String sacrifice) {
        String r = reward, l = limit, s = step, sac = sacrifice;
        try {
            rewardList.addAll(listFromString(r));
            limitList.addAll(listFromString(l));
            stepsList.addAll(listFromString(s));
            sacrificeList.addAll(listFromString(sac));
            updateLists();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void setNoteDisplay(ObjectiveModel editorObj) {
        noteC = editorObj.getObj_notes();
        notesBodyObj.setText(noteC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == NOTE_REQUEST) {
            if (resultCode == RESULT_OK) {
                notesBodyObj.setText(noteC);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled by user", Toast.LENGTH_SHORT).show();
            }
        }
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
                addItem(sacrifice, sacrificeList);
                break;

            case R.id.addStep:
                addItem(steps, stepsList);
                break;

            case R.id.objectiveRewardAdd:
                addItem(rewardField, rewardList);
                break;

            case R.id.addObjLimit:
                addItem(limitField, limitList);
                break;

            case R.id.pickDateObj:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;

            case R.id.pickTimeObj:
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                break;
            default:
                break;
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
        } else if (!isUpdating) {
             if (time.equals("")) {
                Toast.makeText(this, "Pick a time", Toast.LENGTH_SHORT).show();
            } else if (date.equals("")) {
                Toast.makeText(this, "Pick a date", Toast.LENGTH_SHORT).show();
            } else {
                 formValid = true;
             }
        } else {
            formValid = true;
        }

        if (formValid) {
            String idTitle = objectiveTitle.getText().toString().replaceAll(" ", "");
            String timeN = Calendar.getInstance().getTime().toString();

            obj.setObjectiveId(getId(idTitle, LogModel.OBJECTIVE_LOG));
            obj.setObjectiveTitle(objectiveTitle.getText().toString());
            obj.setAboutObjective(aboutObjective.getText().toString());

            String limits = "";
            obj.setObjectiveLimits(listToString(limits, limitList));

            String sacrifices = "";
            obj.setSacrificeObjectiveCost(listToString(sacrifices, sacrificeList));

            String rewards = "";

            obj.setObjectiveReward(listToString(rewards, rewardList));

            String steps = "";
            obj.setObjectiveSteps(listToString(steps, stepsList));

            obj.setUpdatedAt(timeN);
            obj.setObjectiveExpiry(time + date);


            obj.setObjectiveId(getId(idTitle, LogModel.OBJECTIVE_LOG));
            obj.setObj_notes(noteC);

            if (isUpdating) {
                updateObjective(obj);
            } else {
                obj.setObjectiveRemarks("");
                obj.setObjectiveExtensionId("");
                obj.setObjectiveExtensionContent("");

                obj.setExtensionOfObjective(false);
                obj.setObjectiveAchieved(false);
                obj.setQuantifiable(false); //todo make quantifiable

                obj.setSetObjectiveScore(0);
                obj.setSetAt(timeN);
                saveObjective(obj);
            }
        } else {
            Toast.makeText(this, "Check details", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveObjective(ObjectiveModel obj) {
        objectivesViewModel.insert(obj);
        setResult(RESULT_OK);
        finish();
    }

    private void updateObjective(ObjectiveModel obj) {
        objectivesViewModel.update(obj);
        Toast.makeText(this, "Objective Updated     "+obj.getObjectiveTitle(), Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time = "Hour : " + hourOfDay + " Minute : " + minute;
        pickTimeObj.setText(time);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = "Day : " + dayOfMonth + " Month : " + month + " Year" + year;
        pickDateObj.setText(date);
    }


    @Override
    public String getId(String s, String cat) {
        return s + IdGenerator.block + cat;
    }
}