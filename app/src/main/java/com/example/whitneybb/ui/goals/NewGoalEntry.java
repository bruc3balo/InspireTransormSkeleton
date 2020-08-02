package com.example.whitneybb.ui.goals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import com.example.whitneybb.R;
import com.example.whitneybb.adapter.StandardRecyclerListAdapter;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.utils.randomDuties.IdGenerator;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.whitneybb.MainActivity.ADD_GOALS_REQUEST;
import static com.example.whitneybb.adapter.AllMightyPullAdapter.listFromString;
import static com.example.whitneybb.login.LoginActivity.truncate;
import static com.example.whitneybb.model.GoalsModel.ABOUT_GOAL;
import static com.example.whitneybb.model.GoalsModel.GOAL_ACHIEVED;
import static com.example.whitneybb.model.GoalsModel.GOAL_CONTENT;
import static com.example.whitneybb.model.GoalsModel.GOAL_ID;
import static com.example.whitneybb.model.GoalsModel.GOAL_LIMITATIONS;
import static com.example.whitneybb.model.GoalsModel.GOAL_NOTES;
import static com.example.whitneybb.model.GoalsModel.GOAL_PRIVATE;
import static com.example.whitneybb.model.GoalsModel.GOAL_REVIEW;
import static com.example.whitneybb.model.GoalsModel.GOAL_REWARD;
import static com.example.whitneybb.model.GoalsModel.GOAL_SACRIFICE;
import static com.example.whitneybb.model.GoalsModel.GOAL_STEPS;
import static com.example.whitneybb.model.GoalsModel.GOAL_TERM;
import static com.example.whitneybb.model.GoalsModel.GOAL_XP;
import static com.example.whitneybb.model.GoalsModel.LONG_TERM;
import static com.example.whitneybb.model.GoalsModel.MID_TERM;
import static com.example.whitneybb.model.GoalsModel.SHORT_TERM;
import static com.example.whitneybb.model.ObjectiveModel.TIMESTAMP;


public class NewGoalEntry extends AppCompatActivity implements View.OnClickListener, IdGenerator {
    private ImageButton doB, dontB, rewardB, limitB;
    private EditText doField, dontField, rewardField, limitField, goalTitle, aboutGoal;
    private LinkedList<String> doList = new LinkedList<>(), dontList = new LinkedList<>(), rewardList = new LinkedList<>(), limitList = new LinkedList<>();
    private StandardRecyclerListAdapter doAdapter, dontAdapter, rewardAdapter, limitAdapter;
    private Chip shortTerm, midTerm, longTerm;
    private String termGoal = "",keyId = "";
    private boolean goalPrivacy;
    private boolean isUpdating;
    private GoalsViewModel goalsViewModel;
    private GoalsModel editedGoal;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isUpdating = false;
        goalsViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);
        setContentView(R.layout.activity_new_goal_entry);

        Toolbar toolbar = findViewById(R.id.newGoalToolbar);
        setSupportActionBar(toolbar);

        Button submit = findViewById(R.id.submitGoal);


        if (getIntent().getExtras() != null) {
            keyId = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get(GOAL_ID)).toString();
            isUpdating = true;
        }

        final Chip[] chipList = new Chip[]{shortTerm = findViewById(R.id.shortTermChip), midTerm = findViewById(R.id.midTermChip), longTerm = findViewById(R.id.longTermChip)};

        //chips
        chipList[0].setOnClickListener(v -> setSelectedChip(chipList, 0));
        chipList[1].setOnClickListener(v -> setSelectedChip(chipList, 1));
        chipList[2].setOnClickListener(v -> setSelectedChip(chipList, 2));

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
        goalTitle = findViewById(R.id.goalTitle);
        aboutGoal = findViewById(R.id.aboutGoal);

        //adapter
        doAdapter = new StandardRecyclerListAdapter(this, doList);
        dontAdapter = new StandardRecyclerListAdapter(this, dontList);
        rewardAdapter = new StandardRecyclerListAdapter(this, rewardList);
        limitAdapter = new StandardRecyclerListAdapter(this, limitList);

        goalPrivacy = false;

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

        SwitchCompat goalPrivacySwitch = findViewById(R.id.goalPrivacySwitch);
        goalPrivacySwitch.setOnClickListener(this);


        if (isUpdating) {
            toolbar.setTitle("Update Goal");
            toolbar.setSubtitle("Making changes ...");
            submit.setText("Update");
            goalsViewModel.getAllGoals().observe(this, goalsModels -> {
                for (int i = 0; i <= goalsModels.size() - 1; i++) {
                    if (goalsModels.get(i).getGoalId().equals(keyId)) {
                        editedGoal = goalsModels.get(i);
                        aboutGoal.setText(editedGoal.getAboutGoal());
                        goalTitle.setText(editedGoal.getGoalContent());
                        termGoal = editedGoal.getGoalTerm();
                        goalPrivacySwitch.setChecked(editedGoal.isGoalPrivate());
                        updateRvs(editedGoal.getReward(),editedGoal.getGoalLimitations(),editedGoal.getStepsToGoal(),editedGoal.getGoalSacrifices(),termGoal,chipList);
                    }
                }
            });
        } else {
            submit.setText("Create");
            toolbar.setTitle("New Goal");
            toolbar.setSubtitle("The first step to achieving");
        }

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
        if (isUpdating) {

        } else {
            return super.onCreateOptionsMenu(menu);
        }
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (isUpdating) {
            //todo delete
        } else {
            return super.onOptionsItemSelected(item);
        }
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
            case R.id.goalPrivacySwitch:
                goalPrivacy = !goalPrivacy;
                Toast.makeText(this, "" + goalPrivacy, Toast.LENGTH_SHORT).show();
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

    private void setSelectedChip(Chip[] chipList, int selectedChip) {
        LinkedList<Integer> others = new LinkedList<>();
        others.add(0);
        others.add(1);
        others.add(2);
        others.remove(selectedChip);
        switch (selectedChip) {
            case 0:
                termGoal = GoalsModel.SHORT_TERM;
                chipList[selectedChip].setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_red_light)));
                chipList[others.get(0)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                chipList[others.get(1)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                break;
            case 1:
                termGoal = GoalsModel.MID_TERM;
                chipList[selectedChip].setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_orange_light)));
                chipList[others.get(0)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                chipList[others.get(1)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                break;
            case 2:
                termGoal = GoalsModel.LONG_TERM;
                chipList[selectedChip].setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_green_light)));
                chipList[others.get(0)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                chipList[others.get(1)].setChipStrokeColor(ColorStateList.valueOf(Color.BLACK));
                break;
            default:
                break;
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

    public void validateForm(View view) {
        GoalsModel goal = new GoalsModel();
        if (goalTitle.getText().toString().isEmpty()) {
            goalTitle.setError("Give title");
            goalTitle.requestFocus();
        } else if (aboutGoal.getText().toString().isEmpty()) {
            aboutGoal.setError("FIll about");
            aboutGoal.requestFocus();
        } else if (doList.size() == 0) {
            doField.setError("Fill things to do");
            doField.requestFocus();
        } else if (dontList.size() == 0) {
            dontField.setError("Fill things not to do");
            dontField.requestFocus();
        } else if (limitList.size() == 0) {
            limitField.setError("Fill limitations");
            limitField.requestFocus();
        } else if (rewardList.size() == 0) {
            rewardField.setError("Fill rewards");
            rewardField.requestFocus();
        } else if (termGoal.equals("")) {
            Toast.makeText(this, "Pick goal term", Toast.LENGTH_SHORT).show();
        } else {
            goal.setGoalContent(goalTitle.getText().toString());
            goal.setGoalPrivate(goalPrivacy);
            String time = truncate(Calendar.getInstance().getTime().toString(), 16);

            String idTitle = goalTitle.getText().toString().replaceAll(" ", "");
            goal.setGoalId(getId(idTitle, LogModel.GOAL_LOG));

            String rewards = "";
            goal.setReward(listToString(rewards,rewardList));

            String dos = "";
            goal.setStepsToGoal(listToString(dos,doList));

            String donts = "";
            goal.setGoalSacrifices(listToString(donts,dontList));

            String limits = "";
            goal.setGoalLimitations(listToString(limits,limitList));

            goal.setGoalTerm(termGoal);
            goal.setGoalUpdatedAt(time);
            goal.setAboutGoal(aboutGoal.getText().toString());

            if (isUpdating) {
                updateGoal(goal);
            } else {
                goal.setGoalNotes("");
                goal.setGoalReview("");
                goal.setGoalExperienceRating(0);
                goal.setGoalAchieved(false);
                goal.setGoalSetAt(time);
                saveData(goal);
            }

        }
    }


    private void saveData(GoalsModel goal) {
        goalsViewModel.insert(goal);
        setResult(RESULT_OK);
        finish();
    }



    @Override
    public String getId(String s, String cat) {
        return s + IdGenerator.block + cat;
    }

    public static String listToString(String s,LinkedList<String> list) {

        for (int i = 0; i <= list.size() - 1; i++) {
            try {
                s = s.concat("{" + list.get(i) + "}");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    private void updateGoal(GoalsModel goal) {
        goalsViewModel.update(goal);
        Toast.makeText(this, "Goal Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updateRvs(String reward,String limit,String step,String sacrifice,String term,Chip[] chipList) {
        String r = reward,l = limit,s = step,sac = sacrifice;
        try {
            rewardList.addAll(listFromString(r));
            rewardAdapter.notifyDataSetChanged();
            limitList.addAll(listFromString(l));
            limitAdapter.notifyDataSetChanged();
            doList.addAll(listFromString(s));
            doAdapter.notifyDataSetChanged();
            dontList.addAll(listFromString(sac));
            dontAdapter.notifyDataSetChanged();
            switch (term) {
                case SHORT_TERM:
                    setSelectedChip(chipList,0);
                    break;
                case MID_TERM:
                    setSelectedChip(chipList,1);
                    break;
                case LONG_TERM:
                    setSelectedChip(chipList,2);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}