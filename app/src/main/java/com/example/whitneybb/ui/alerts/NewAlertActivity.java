package com.example.whitneybb.ui.alerts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.whitneybb.R;
import com.example.whitneybb.model.AlertsModel;
import com.example.whitneybb.model.LogModel;
import com.example.whitneybb.utils.broadcasts.AlertReceiver;
import com.example.whitneybb.utils.randomDuties.IdGenerator;
import com.example.whitneybb.utils.timepicker.DatePickerFragment;
import com.example.whitneybb.utils.timepicker.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.LinkedList;


import static com.example.whitneybb.MainActivity.ADD_ALERT_REQUEST;
import static com.example.whitneybb.login.LoginActivity.truncate;
import static com.example.whitneybb.model.AlertsModel.ALERT_DESCRIPTION;
import static com.example.whitneybb.model.AlertsModel.ALERT_ID;
import static com.example.whitneybb.model.AlertsModel.ALERT_ON;
import static com.example.whitneybb.model.AlertsModel.ALERT_REPEAT;
import static com.example.whitneybb.model.AlertsModel.ALERT_RING_TIME;
import static com.example.whitneybb.model.AlertsModel.ALERT_TITLE;
import static com.example.whitneybb.model.AlertsModel.ALL_DAY;
import static com.example.whitneybb.model.AlertsModel.FRIDAY;
import static com.example.whitneybb.model.AlertsModel.MONDAY;
import static com.example.whitneybb.model.AlertsModel.ONE_TIME_ALARM;
import static com.example.whitneybb.model.AlertsModel.REPEAT_ALARM;
import static com.example.whitneybb.model.AlertsModel.REPEAT_DAYS;
import static com.example.whitneybb.model.AlertsModel.REPEAT_ON_DAY;
import static com.example.whitneybb.model.AlertsModel.SATURDAY;
import static com.example.whitneybb.model.AlertsModel.SNOOZE_COUNT;
import static com.example.whitneybb.model.AlertsModel.SNOOZE_TIME;
import static com.example.whitneybb.model.AlertsModel.STOPPED_AT;
import static com.example.whitneybb.model.AlertsModel.SUNDAY;
import static com.example.whitneybb.model.AlertsModel.THURSDAY;
import static com.example.whitneybb.model.AlertsModel.TUESDAY;
import static com.example.whitneybb.model.AlertsModel.WEDNESDAY;
import static com.example.whitneybb.model.DiaryModel.CREATED_AT;
import static com.example.whitneybb.model.DiaryModel.UPDATED_AT;

public class NewAlertActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, View.OnClickListener, IdGenerator {

    private String alertLabel = "", alertDescription = "", alarmTime = "";
    private EditText alertTitleField, alertDescriptionField;
    private static final int Q1_SNOOZE = 15 * 60000;
    private static final int Q2_SNOOZE = 30 * 60000;
    private static final int Q3_SNOOZE = 45 * 60000;
    private static final int Q4_SNOOZE = 60 * 60000;
    private String ALARM_TYPE = ONE_TIME_ALARM;
    private TextView sun, mon, tue, wed, thur, fri, sat;
    private TextView q1, q2, q3, q4;
    private TextView repeatTv, snoozeTv, pickTime;
    private LinearLayout repeatLayout, snoozeLayout;
    private boolean sunB, monB, tueB, wedB, thurB, friB, satB;
    private boolean alertRepeat;

    private int snoozeDuration = Q1_SNOOZE; //15min
    private LinkedList<String> repeatDays = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alert);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alert_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        alertTitleField = findViewById(R.id.alertTitleField);
        alertDescriptionField = findViewById(R.id.alertDescriptionField);

        //sun
        sun = findViewById(R.id.sun);
        sun.setOnClickListener(this);
        sunB = false;
        //mon
        mon = findViewById(R.id.mon);
        mon.setOnClickListener(this);
        monB = false;
        //tue
        tue = findViewById(R.id.tue);
        tue.setOnClickListener(this);
        tueB = false;
        //wed
        wed = findViewById(R.id.wed);
        wed.setOnClickListener(this);
        wedB = false;
        //thur
        thur = findViewById(R.id.thur);
        thur.setOnClickListener(this);
        thurB = false;
        //fri
        fri = findViewById(R.id.fri);
        fri.setOnClickListener(this);
        friB = false;
        //sat
        sat = findViewById(R.id.sat);
        sat.setOnClickListener(this);
        satB = false;

        //snooze
        //q1
        q1 = findViewById(R.id.snooze15);
        q1.setOnClickListener(this);
        q2 = findViewById(R.id.snooze30);
        q2.setOnClickListener(this);
        q3 = findViewById(R.id.snooze45);
        q3.setOnClickListener(this);
        q4 = findViewById(R.id.snooze1hr);
        q4.setOnClickListener(this);

        //repeat
        repeatLayout = findViewById(R.id.repeatLayout);
        repeatTv = findViewById(R.id.repeatTv);

        //snooze
        snoozeTv = findViewById(R.id.snoozeTv);
        snoozeLayout = findViewById(R.id.snoozeLayout);

        //pickTime
        pickTime = findViewById(R.id.pickTimeTv);
        pickTime.setOnClickListener(this);

        alertRepeat = false;

        getWindow().setStatusBarColor(Color.BLACK);
    }

    public void openTimePicker() {
        Toast.makeText(this, "Pick a time for alarm" , Toast.LENGTH_SHORT).show();
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void showDatePickerDialog() {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(this, "Hour : " + hourOfDay + " Minute : " + minute, Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        alarmTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        switch (ALARM_TYPE) {
            default:break;
            case REPEAT_ON_DAY:

                break;
            case ONE_TIME_ALARM:
                //setOneTimeAlarm(c);
                break;
        }
    }

    private void updateTimeText(Calendar c) {
        String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        pickTime.setText(timeText);
        Toast.makeText(this, timeText, Toast.LENGTH_SHORT).show();
    }

    private void setOneTimeAlarm(Calendar c) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void setOneTimeAlarmWithSnooze(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void settOneTimeAlarmWakeUp(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void setOneTimeAlarmWakeUpWithSnooze(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void setRepeatAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

    private void setRepeatingAlarmWithSnooze(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void setRepeatAlarmWakeUp(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void setRepeatAlarmWakeUpWithSnooze(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void setAlarmMustRing() {

    }

    private void setAllDayAlarm() {

    }

    private void cancelAlarm (){}

    public void cancelAlarm(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0); //todo request code

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this, "Alarm canceled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: //Remind me all day
                ALARM_TYPE = ALL_DAY;
                pickTime.setVisibility(View.GONE);
                repeatTv.setVisibility(View.GONE);
                repeatLayout.setVisibility(View.GONE);
                snoozeTv.setVisibility(View.GONE);
                snoozeLayout.setVisibility(View.GONE);
                alertRepeat = false;
                break;
            case 1: //Set a one time alarm
                ALARM_TYPE = ONE_TIME_ALARM;
                pickTime.setVisibility(View.VISIBLE);
                repeatTv.setVisibility(View.GONE);
                repeatLayout.setVisibility(View.GONE);
                snoozeTv.setVisibility(View.VISIBLE);
                snoozeLayout.setVisibility(View.VISIBLE);
                alertRepeat = false;
                break;
            case 2: //Set a repeated alarm
                ALARM_TYPE = REPEAT_ON_DAY;
                pickTime.setVisibility(View.VISIBLE);
                repeatTv.setVisibility(View.VISIBLE);
                repeatLayout.setVisibility(View.VISIBLE);
                snoozeTv.setVisibility(View.VISIBLE);
                snoozeLayout.setVisibility(View.VISIBLE);
                alertRepeat = true;
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ALARM_TYPE = ONE_TIME_ALARM;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public void validateForm(View view) {
        if (alertTitleField.getText().toString().isEmpty()) {
            alertTitleField.setError("Give a title");
            alertTitleField.requestFocus();
        } else if (alertDescriptionField.getText().toString().isEmpty()) {
            alertDescriptionField.setError("Give a description");
            alertTitleField.requestFocus();
        } else if (alarmTime.equals("")) {
            Toast.makeText(this, "Pick a time", Toast.LENGTH_SHORT).show();
            pickTime.setTextColor(Color.RED);
            pickTime.requestFocus();
            new Handler().postDelayed(() -> pickTime.setTextColor(Color.BLACK), 300);
        } else if (ALARM_TYPE.equals(REPEAT_ON_DAY)) {
             boolean[] daysList = new boolean[]{sunB, monB, tueB, wedB, thurB, friB, satB};
            repeatDays.clear();
            for (int i = 0; i <= 6; i++) { //add days to mix
                String day = "";
                switch (i) {
                    default:
                        break;
                    case 0:
                        day = SUNDAY;
                        break;

                    case 1:
                        day = MONDAY;
                        break;

                    case 2:
                        day = TUESDAY;
                        break;

                    case 3:
                        day = WEDNESDAY;
                        break;

                    case 4:
                        day = THURSDAY;
                        break;

                    case 5:
                        day = FRIDAY;
                        break;

                    case 6:
                        day = SATURDAY;
                        break;
                }
                if (daysList[i]) {
                    repeatDays.add(day);
                }
            }

            if (repeatDays.size() == 0) {
                Toast.makeText(this, "Choose Repeat days", Toast.LENGTH_SHORT).show();
            } else {
                String days = "";
                String time = truncate(Calendar.getInstance().getTime().toString(),16);
                String idTitle = alertTitleField.getText().toString().replaceAll(" ","");

                for (int i = 0;i <= repeatDays.size() - 1 ;i++) {
                  try {
                      days = days.concat("{" + repeatDays.get(i) + "}");
                  } catch (Exception e) {
                  e.printStackTrace();
                  }
                }
                AlertsModel alert = new AlertsModel(alarmTime, alertRepeat,  String.valueOf(snoozeDuration),  alertTitleField.getText().toString(),  alertDescriptionField.getText().toString(),  "", days ,  true,  0, time ,  time,getId(idTitle, LogModel.ALERT_LOG));
                saveObject(alert);
            }

        } else {
            String days = "";
            String time = truncate(Calendar.getInstance().getTime().toString(),16);
            String idTitle = alertTitleField.getText().toString().replaceAll(" ","");

            AlertsModel alert = new AlertsModel(alarmTime, alertRepeat,  String.valueOf(snoozeDuration),  alertTitleField.getText().toString(),  alertDescriptionField.getText().toString(),  "", days ,  true,  0, time ,  time,getId(idTitle, LogModel.ALERT_LOG));
            saveObject(alert);

        }

    }

    private void saveObject(AlertsModel alert) {
        Intent data = new Intent();
        data.putExtra(ALERT_RING_TIME,alert.getAlertRingTime());
        data.putExtra(ALERT_REPEAT,String.valueOf(alert.isAlertRepeat()));
        data.putExtra(SNOOZE_TIME,alert.getSnoozeTime());
        data.putExtra(ALERT_TITLE,alert.getAlertTitle());
        data.putExtra(ALERT_DESCRIPTION,alert.getAlertDescription());
        data.putExtra(STOPPED_AT,alert.getStoppedAt());
        data.putExtra(REPEAT_DAYS,alert.getRepeatDays());
        data.putExtra(ALERT_ON,String.valueOf(alert.isAlertOn()));
        data.putExtra(SNOOZE_COUNT,String.valueOf(alert.getSnoozeCount()));
        data.putExtra(CREATED_AT,alert.getCreatedAt());
        data.putExtra(UPDATED_AT,alert.getUpdatedAt());
        data.putExtra(ALERT_ID,alert.getAlertId());
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public void onClick(View v) {
        TextView tV = (TextView) v;
        switch (v.getId()) {
            case R.id.sun:
                sunB = !sunB;
                if (sunB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;
            case R.id.mon:
                monB = !monB;
                if (monB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;
            case R.id.tue:
                tueB = !tueB;
                if (tueB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;
            case R.id.wed:
                wedB = !wedB;
                if (wedB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;
            case R.id.thur:
                thurB = !thurB;
                if (thurB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;
            case R.id.fri:
                friB = !friB;
                if (friB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;
            case R.id.sat:
                satB = !satB;
                if (satB) {
                    tV.setBackground(getDrawable(R.drawable.cirle_bg));
                } else {
                    tV.setBackground(null);
                }
                break;

            case R.id.snooze15:
                q1.setBackground(getDrawable(R.drawable.cirle_bg_red));
                q2.setBackground(null);
                q3.setBackground(null);
                q4.setBackground(null);
                break;
            case R.id.snooze30:
                q2.setBackground(getDrawable(R.drawable.cirle_bg_red));
                q3.setBackground(null);
                q4.setBackground(null);
                q1.setBackground(null);
                break;
            case R.id.snooze45:
                q3.setBackground(getDrawable(R.drawable.cirle_bg_red));
                q4.setBackground(null);
                q1.setBackground(null);
                q2.setBackground(null);
                break;
            case R.id.snooze1hr:
                q4.setBackground(getDrawable(R.drawable.cirle_bg_red));
                q1.setBackground(null);
                q2.setBackground(null);
                q3.setBackground(null);
                break;
            case R.id.pickTimeTv:
                openTimePicker();
                break;
            default:
                break;
        }
    }

    @Override
    public String getId(String s, String cat) {
        return s + IdGenerator.block + cat;
    }
}