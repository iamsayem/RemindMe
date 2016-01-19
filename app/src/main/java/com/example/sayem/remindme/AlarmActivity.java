package com.example.sayem.remindme;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AlarmActivity extends Activity {

    TextView setTheAlarmTextView;
    TextView scheduleBasedAlarmTextView;
    Switch alarmOnOffSwitch;
    Switch scheduleOnOffSwitch;
    TextView fromTextView;
    Button fromDateButton;
    Button fromTimeButton;
    TextView toTextView;
    Button toDateButton;
    Button toTimeButton;
    TextView setAlarmToneTextView;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    String fromDate;
    String toDate;
    String fromTime;
    String toTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        initializeAll();
    }

    private void initializeAll(){

        calendar = Calendar.getInstance();

        setTheAlarmTextView = (TextView) findViewById(R.id.setTheAlarmTextView);
        scheduleBasedAlarmTextView = (TextView) findViewById(R.id.scheduleBasedAlarmTextView);
        alarmOnOffSwitch = (Switch) findViewById(R.id.alarmOnOffSwitch);
        scheduleOnOffSwitch = (Switch) findViewById(R.id.scheduleOnOffSwitch);
        fromTextView = (TextView) findViewById(R.id.fromTextView);
        fromDateButton = (Button) findViewById(R.id.fromDateButton);
        fromTimeButton = (Button) findViewById(R.id.fromTimeButton);
        toTextView = (TextView) findViewById(R.id.toTextView);
        toDateButton = (Button) findViewById(R.id.toDateButton);
        toTimeButton = (Button) findViewById(R.id.toTimeButton);
        setAlarmToneTextView = (TextView) findViewById(R.id.setAlarmToneTextView);

        setAlarmToneTextView.setText("Set the alarm");
        scheduleBasedAlarmTextView.setText("Schedule based alarm");
        fromTextView.setText("From");
        toTextView.setText("To");
        setAlarmToneTextView.setText("Set alarm tone");


        alarmOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                        alarmOnOffTextView.setText("Alarm On");
                    Intent intent = new Intent(getApplicationContext(), UserLocationActivity.class);
                    startActivity(intent);
                } else {
//                        alarmOnOffTextView.setText("Alarm Off");
                }
            }
        });

        scheduleOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                        alarmOnOffTextView.setText("Alarm On");

                } else {
//                        alarmOnOffTextView.setText("Alarm Off");
                }
            }
        });

        fromDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.get(Calendar.YEAR);
                dateCalendar.get(Calendar.MONTH);
                dateCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AlarmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        fromDate = dayOfMonth + "/" + monthOfYear + "/" + year;
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
        fromDateButton.setText(fromDate);

        toDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.get(Calendar.YEAR);
                dateCalendar.get(Calendar.MONTH);
                dateCalendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AlarmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        toDate = dayOfMonth + "/" + monthOfYear + "/" + year;
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
        toDateButton.setText(toDate);

        fromTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.get(Calendar.HOUR_OF_DAY);
                timeCalendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        fromTime = hourOfDay + ":" + minute;
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

                timePickerDialog.show();
            }
        });
        fromTimeButton.setText(fromTime);

        toTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.get(Calendar.HOUR_OF_DAY);
                timeCalendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        toTime = hourOfDay + ":" + minute;
                    }
                },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

                timePickerDialog.show();
            }
        });
        toTimeButton.setText(toTime);

    }

    private void prepareData(){

        if (alarmOnOffSwitch.isChecked()){
//                alarmOnOffTextView.setText("Alarm On");
            Intent intent = new Intent(getApplicationContext(), UserLocationActivity.class);
            startActivity(intent);
        }
        else{
//                alarmOnOffTextView.setText("Alarm Off");
        }


        if (scheduleOnOffSwitch.isChecked()){
//                alarmOnOffTextView.setText("Alarm On");

        }
        else{
//                alarmOnOffTextView.setText("Alarm Off");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareData();
    }

}
