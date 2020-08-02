package com.example.whitneybb;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.whitneybb.model.AlertsModel;
import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.model.GoalsModel;
import com.example.whitneybb.model.NotesModel;
import com.example.whitneybb.model.ObjectiveModel;
import com.example.whitneybb.ui.alerts.AlertsViewModel;
import com.example.whitneybb.ui.alerts.NewAlertActivity;
import com.example.whitneybb.ui.diary.DiaryViewModel;
import com.example.whitneybb.ui.diary.NewDiaryEntry;
import com.example.whitneybb.ui.goals.GoalsViewModel;
import com.example.whitneybb.ui.goals.NewGoalEntry;
import com.example.whitneybb.ui.notes.NewNotesEntry;
import com.example.whitneybb.ui.notes.NotesViewModel;
import com.example.whitneybb.ui.objectives.NewObjectiveEntry;
import com.example.whitneybb.ui.objectives.ObjectivesViewModel;
import com.example.whitneybb.ui.profile.ProfileActiviy;
import com.example.whitneybb.utils.broadcasts.AlertReceiver;
import com.example.whitneybb.utils.settings.SettingsActivity;
import com.example.whitneybb.utils.timepicker.TimePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

import static com.example.whitneybb.model.AlertsModel.ALERT_DESCRIPTION;
import static com.example.whitneybb.model.AlertsModel.ALERT_ID;
import static com.example.whitneybb.model.AlertsModel.ALERT_ON;
import static com.example.whitneybb.model.AlertsModel.ALERT_REPEAT;
import static com.example.whitneybb.model.AlertsModel.ALERT_RING_TIME;
import static com.example.whitneybb.model.AlertsModel.ALERT_TITLE;
import static com.example.whitneybb.model.AlertsModel.REPEAT_DAYS;
import static com.example.whitneybb.model.AlertsModel.SNOOZE_COUNT;
import static com.example.whitneybb.model.AlertsModel.SNOOZE_TIME;
import static com.example.whitneybb.model.AlertsModel.STOPPED_AT;
import static com.example.whitneybb.model.DiaryModel.ABOUT_DIARY;
import static com.example.whitneybb.model.DiaryModel.CREATED_AT;
import static com.example.whitneybb.model.DiaryModel.DIARY_COVER;
import static com.example.whitneybb.model.DiaryModel.DIARY_ID;
import static com.example.whitneybb.model.DiaryModel.DIARY_OWNER;
import static com.example.whitneybb.model.DiaryModel.DIARY_PASSWORD;
import static com.example.whitneybb.model.DiaryModel.DIARY_PROTECTED_PASSWORD;
import static com.example.whitneybb.model.DiaryModel.DIARY_REMINDER_TIME;
import static com.example.whitneybb.model.DiaryModel.DIARY_SCHEDULE;
import static com.example.whitneybb.model.DiaryModel.DIARY_TITLE;
import static com.example.whitneybb.model.DiaryModel.UPDATED_AT;
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
import static com.example.whitneybb.model.NotesModel.NOTE_COLOR;
import static com.example.whitneybb.model.NotesModel.NOTE_CONTENT;
import static com.example.whitneybb.model.NotesModel.NOTE_ID;
import static com.example.whitneybb.model.NotesModel.NOTE_OWNER;
import static com.example.whitneybb.model.NotesModel.NOTE_PASSWORD;
import static com.example.whitneybb.model.NotesModel.NOTE_PRIORITY;
import static com.example.whitneybb.model.NotesModel.NOTE_PRIVATE;
import static com.example.whitneybb.model.NotesModel.NOTE_TITLE;
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

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView emailTv, uidTv;
    private ImageView headerImage;
    public static int currentPage;
    public static final int ADD_DIARY_REQUEST = 1;
    public static final int ADD_NOTES_REQUEST = 2;
    public static final int ADD_OBJECTIVE_REQUEST = 3;
    public static final int ADD_GOALS_REQUEST = 4;
    public static final int ADD_ALERT_REQUEST = 5;
    public static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.left_chevron);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentPage) {
                    case 0:
                        Intent intentN = new Intent(MainActivity.this, NewNotesEntry.class);
                        startActivityForResult(intentN, ADD_NOTES_REQUEST);
                        //Snackbar.make(view, "Notes", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        break;
                    case 1:
                        //Snackbar.make(view, "Goals", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intentG = new Intent(MainActivity.this, NewGoalEntry.class);
                        startActivityForResult(intentG, ADD_GOALS_REQUEST);
                        break;
                    case 2:
                        //Snackbar.make(view, "Objectives", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intentO = new Intent(MainActivity.this, NewObjectiveEntry.class);
                        startActivityForResult(intentO, ADD_OBJECTIVE_REQUEST);
                        break;
                    case 3:
                        //Snackbar.make(view, "Diary", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intentD = new Intent(MainActivity.this, NewDiaryEntry.class);
                        startActivityForResult(intentD, ADD_DIARY_REQUEST);
                        break;
                    case 4:
                        //Snackbar.make(view, "Alerts", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intentA = new Intent(MainActivity.this, NewAlertActivity.class);
                        startActivityForResult(intentA, ADD_ALERT_REQUEST);
                        break;
                    default:
                        break;
                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_objectives, R.id.nav_goals, R.id.nav_diary, R.id.nav_alerts, R.id.nav_summary, R.id.nav_notes, R.id.nav_calendar, R.id.nav_aboutUs).setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        View header = navigationView.getHeaderView(0);

        emailTv = header.findViewById(R.id.emailTv);

        uidTv = header.findViewById(R.id.uidTv);

        headerImage = header.findViewById(R.id.headerImage);

        headerImage.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActiviy.class)));

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);

        getWindow().setStatusBarColor(Color.BLACK);

        updateUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    public static void smartFab(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                fab.setVisibility(View.VISIBLE);
                break;
            case 5:
            case 6:
            case 7:
                fab.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ADD_DIARY_REQUEST:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        DiaryModel diary = new DiaryModel();

                        diary.setDiaryTitle(Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(DIARY_TITLE)).toString());
                        diary.setDiaryPassword(Objects.requireNonNull(data.getExtras().get(DIARY_PASSWORD)).toString());
                        diary.setDailyScheduleEntry(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(DIARY_SCHEDULE)).toString()));

                        diary.setUpdatedAt(Objects.requireNonNull(data.getExtras().get(UPDATED_AT)).toString());
                        diary.setCreatedAt(Objects.requireNonNull(data.getExtras().get(CREATED_AT)).toString());
                        diary.setDiaryOwner(Objects.requireNonNull(data.getExtras().get(DIARY_OWNER)).toString());

                        diary.setDiaryCoverUrl(Objects.requireNonNull(data.getExtras().get(DIARY_COVER)).toString());
                        diary.setDiaryAbout(Objects.requireNonNull(data.getExtras().get(ABOUT_DIARY)).toString());
                        diary.setPasswordProtected(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(DIARY_PROTECTED_PASSWORD)).toString()));

                        diary.setDairyReminderTime(Objects.requireNonNull(data.getExtras().get(DIARY_REMINDER_TIME)).toString());
                        diary.setDiaryId(Objects.requireNonNull(data.getExtras().get(DIARY_ID)).toString());


                        DiaryViewModel diaryViewModel = new ViewModelProvider(MainActivity.this).get(DiaryViewModel.class);
                        diaryViewModel.insert(diary);
                        Toast.makeText(this, "Diary Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Nothing returned", Toast.LENGTH_SHORT).show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Diary creation cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Diary Creation Failed", Toast.LENGTH_SHORT).show();
                }
                break;

            case ADD_NOTES_REQUEST:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        NotesModel note = new NotesModel();

                        note.setNoteId(Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(NOTE_ID)).toString());
                        note.setNoteOwner(Objects.requireNonNull(data.getExtras().get(NOTE_OWNER)).toString());
                        note.setNoteColor(Objects.requireNonNull(data.getExtras().get(NOTE_COLOR)).toString());

                        note.setCreatedAt(Objects.requireNonNull(data.getExtras().get(CREATED_AT)).toString());

                        note.setUpdatedAt(Objects.requireNonNull(data.getExtras().get(UPDATED_AT)).toString());
                        note.setNoteTitle(Objects.requireNonNull(data.getExtras().get(NOTE_TITLE)).toString());
                        note.setNoteContent(Objects.requireNonNull(data.getExtras().get(NOTE_CONTENT)).toString());

                        note.setNotePriority(Integer.parseInt(Objects.requireNonNull(data.getExtras().get(NOTE_PRIORITY)).toString()));
                        note.setNotePrivate(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(NOTE_PRIVATE)).toString()));
                        note.setNotePassword(Objects.requireNonNull(data.getExtras().get(NOTE_PASSWORD)).toString());


                        NotesViewModel notesViewModel = new ViewModelProvider(MainActivity.this).get(NotesViewModel.class);
                        notesViewModel.insert(note);
                        Toast.makeText(this, "Note Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Nothing returned", Toast.LENGTH_SHORT).show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Notes creation cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Notes Creation Failed", Toast.LENGTH_SHORT).show();
                }
                break;

            case ADD_ALERT_REQUEST:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        AlertsModel alert = new AlertsModel();

                        alert.setAlertRingTime(Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(ALERT_RING_TIME)).toString());
                        alert.setAlertRepeat(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(ALERT_REPEAT)).toString()));
                        alert.setSnoozeTime(Objects.requireNonNull(data.getExtras().get(SNOOZE_TIME)).toString());
                        alert.setAlertTitle(Objects.requireNonNull(data.getExtras().get(ALERT_TITLE)).toString());
                        alert.setAlertDescription(Objects.requireNonNull(data.getExtras().get(ALERT_DESCRIPTION)).toString());
                        alert.setStoppedAt(Objects.requireNonNull(data.getExtras().get(STOPPED_AT)).toString());
                        alert.setRepeatDays(Objects.requireNonNull(data.getExtras().get(REPEAT_DAYS)).toString());
                        alert.setAlertOn(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(ALERT_ON)).toString()));
                        alert.setSnoozeCount(Integer.parseInt(Objects.requireNonNull(data.getExtras().get(SNOOZE_COUNT)).toString()));
                        alert.setCreatedAt(Objects.requireNonNull(data.getExtras().get(CREATED_AT)).toString());
                        alert.setUpdatedAt(Objects.requireNonNull(data.getExtras().get(UPDATED_AT)).toString());
                        alert.setAlertId(Objects.requireNonNull(data.getExtras().get(ALERT_ID)).toString());

                        AlertsViewModel alertsViewModel = new ViewModelProvider(MainActivity.this).get(AlertsViewModel.class);
                        alertsViewModel.insert(alert);
                        Toast.makeText(this, "Alert Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Nothing returned", Toast.LENGTH_SHORT).show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Alerts creation cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Alerts Creation Failed", Toast.LENGTH_SHORT).show();
                }
                break;

            case ADD_GOALS_REQUEST:
                if (resultCode == RESULT_OK) {
                        /*GoalsModel goal = new GoalsModel();

                        goal.setGoalId(Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(GOAL_ID)).toString());
                        goal.setGoalTerm(Objects.requireNonNull(data.getExtras().get(GOAL_TERM)).toString());
                        goal.setGoalContent(Objects.requireNonNull(data.getExtras().get(GOAL_CONTENT)).toString());

                        goal.setGoalPrivate(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(GOAL_PRIVATE)).toString()));
                        goal.setGoalSetAt(Objects.requireNonNull(data.getExtras().get(TIMESTAMP)).toString());
                        goal.setGoalUpdatedAt(Objects.requireNonNull(data.getExtras().get(TIMESTAMP)).toString());
                        goal.setGoalAchieved(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(GOAL_ACHIEVED)).toString()));

                        goal.setGoalExperienceRating(Integer.parseInt(Objects.requireNonNull(data.getExtras().get(GOAL_XP)).toString()));
                        goal.setGoalLimitations(Objects.requireNonNull(data.getExtras().get(GOAL_LIMITATIONS)).toString());

                        goal.setStepsToGoal(Objects.requireNonNull(data.getExtras().get(GOAL_STEPS)).toString());
                        goal.setGoalNotes(Objects.requireNonNull(data.getExtras().get(GOAL_NOTES)).toString());
                        goal.setReward(Objects.requireNonNull(data.getExtras().get(GOAL_REWARD)).toString());

                        goal.setGoalReview(Objects.requireNonNull(data.getExtras().get(GOAL_REVIEW)).toString());
                        goal.setAboutGoal(Objects.requireNonNull(data.getExtras().get(ABOUT_GOAL)).toString());
                        goal.setGoalSacrifices(Objects.requireNonNull(data.getExtras().get(GOAL_SACRIFICE)).toString());


                        GoalsViewModel goalsViewModel = new ViewModelProvider(MainActivity.this).get(GoalsViewModel.class);
                        goalsViewModel.insert(goal);*/
                    Toast.makeText(this, "Goal Created", Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Goals Creation Failed", Toast.LENGTH_SHORT).show();
                }
                break;

            case ADD_OBJECTIVE_REQUEST:
                if (resultCode == RESULT_OK) {
                        /*ObjectiveModel objective = new ObjectiveModel();

                        objective.setObjectiveId(Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get(OBJECTIVE_ID)).toString());
                        objective.setObjectiveExpiry(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_EXPIRY)).toString());
                        objective.setSetAt(Objects.requireNonNull(data.getExtras().get(TIMESTAMP)).toString());
                        objective.setUpdatedAt(Objects.requireNonNull(data.getExtras().get(TIMESTAMP)).toString());


                        objective.setObjectiveLimits(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_LIMITS)).toString());
                        objective.setObjectiveTitle(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_TITLE)).toString());
                        objective.setAboutObjective(Objects.requireNonNull(data.getExtras().get(ABOUT_OBJECTIVE)).toString());

                        objective.setExtensionOfObjective(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(EXTENDING_OBJECTIVE)).toString()));
                        objective.setObjectiveAchieved(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_ACHIEVED)).toString()));
                        objective.setSacrificeObjectiveCost(Objects.requireNonNull(data.getExtras().get(SACRIFICE_COST)).toString());

                        objective.setQuantifiable(Boolean.parseBoolean(Objects.requireNonNull(data.getExtras().get(OBJ_QUANTIFIABLE)).toString()));
                        objective.setObjectiveRemarks(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_REMARK)).toString());
                        objective.setObjectiveReward(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_REWARD)).toString());

                        objective.setSetObjectiveScore(Integer.parseInt(Objects.requireNonNull(data.getExtras().get(SET_OBJECTIVE_SCORE)).toString()));
                        objective.setObjectiveSteps(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_STEPS)).toString());
                        objective.setObjectiveExtensionContent(Objects.requireNonNull(data.getExtras().get(OBJ_EXTENSION_CONTENT)).toString());

                        objective.setObjectiveExtensionId(Objects.requireNonNull(data.getExtras().get(OBJ_EXTENSION_ID)).toString());
                        objective.setAboutObjective(Objects.requireNonNull(data.getExtras().get(ABOUT_OBJECTIVE)).toString());
                        objective.setObjectiveScore(Integer.parseInt(Objects.requireNonNull(data.getExtras().get(OBJECTIVE_SCORE)).toString()));

                        ObjectivesViewModel objectivesViewModel = new ViewModelProvider(MainActivity.this).get(ObjectivesViewModel.class);
                        objectivesViewModel.insert(objective);*/
                    Toast.makeText(this, "Objective created", Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Objective Creation Failed", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }

    }

    private void updateUi() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String uid = "";
        String email = "";
        String empty = "-";

        if (firebaseAuth.getCurrentUser() != null) {
            email = firebaseAuth.getCurrentUser().getEmail();
            uid = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
            emailTv.setText(email);
            uidTv.setText(uid);
        } else {
            emailTv.setText(empty);
            uidTv.setText(empty);
        }

    }


}