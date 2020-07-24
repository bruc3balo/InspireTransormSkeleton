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

import com.example.whitneybb.model.DiaryModel;
import com.example.whitneybb.ui.alerts.NewAlertActivity;
import com.example.whitneybb.ui.diary.DiaryViewModel;
import com.example.whitneybb.ui.diary.NewDiaryEntry;
import com.example.whitneybb.ui.goals.NewGoalEntry;
import com.example.whitneybb.ui.notes.NewNotesEntry;
import com.example.whitneybb.ui.objectives.NewObjectiveEntry;
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
                        startActivityForResult(intentG, ADD_DIARY_REQUEST);
                        break;
                    case 2:
                        //Snackbar.make(view, "Objectives", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intentO = new Intent(MainActivity.this, NewObjectiveEntry.class);
                        startActivityForResult(intentO, ADD_OBJECTIVE_REQUEST);
                        break;
                    case 3:
                        //Snackbar.make(view, "Diary", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intentD = new Intent(MainActivity.this, NewDiaryEntry.class);
                        startActivityForResult(intentD, ADD_GOALS_REQUEST);
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
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActiviy.class));
            }
        });

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

        if (requestCode == ADD_DIARY_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra(NewDiaryEntry.EXTRA_TITLE);
                int id = data.getIntExtra(NewDiaryEntry.EXTRA_ID, 4);

                DiaryModel diary = new DiaryModel(title, id);
                DiaryViewModel diaryViewModel = new ViewModelProvider(MainActivity.this).get(DiaryViewModel.class);
                diaryViewModel.insert(diary);
                Toast.makeText(this, "Diary Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nothing returned", Toast.LENGTH_SHORT).show();
            }

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