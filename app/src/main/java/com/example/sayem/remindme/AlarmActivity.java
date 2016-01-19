package com.example.sayem.remindme;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;


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
    int to_dayOfMonth;
    int to_monthOfYear;
    int to_year;
    int from_dayOfMonth;
    int from_monthOfYear;
    int from_year;
    int to_hourOfDay;
    int to_minute;
    int from_hourOfDay;
    int from_minute;

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

        setTheAlarmTextView.setText("Non-Schedule based alarm");
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

                datePickerDialog = new DatePickerDialog(AlarmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar fromDateCalendar = Calendar.getInstance();
                        fromDateCalendar.set(Calendar.YEAR, year);
                        fromDateCalendar.set(Calendar.MONTH, monthOfYear);
                        fromDateCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        from_dayOfMonth = fromDateCalendar.get(Calendar.DAY_OF_MONTH);
                        from_monthOfYear = fromDateCalendar.get(Calendar.MONTH);
                        from_year = fromDateCalendar.get(Calendar.YEAR);

                        fromDate = from_dayOfMonth + "/" + (from_monthOfYear+1) + "/" + from_year;
                        fromDateButton.setText(fromDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
        //fromDateButton.setText(fromDate);

        toDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog = new DatePickerDialog(AlarmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar toDateCalendar = Calendar.getInstance();
                        toDateCalendar.set(Calendar.YEAR, year);
                        toDateCalendar.set(Calendar.MONTH, monthOfYear);
                        toDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        to_dayOfMonth = toDateCalendar.get(Calendar.DAY_OF_MONTH);
                        to_monthOfYear = toDateCalendar.get(Calendar.MONTH);
                        to_year = toDateCalendar.get(Calendar.YEAR);

                        toDate = to_dayOfMonth + "/" + (to_monthOfYear+1) + "/" + to_year;
                        toDateButton.setText(toDate);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        fromTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar fromTimeCalendar = Calendar.getInstance();
                        fromTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        fromTimeCalendar.set(Calendar.MINUTE, minute);

                        from_hourOfDay = fromTimeCalendar.get(Calendar.HOUR_OF_DAY);
                        from_minute = fromTimeCalendar.get(Calendar.MINUTE);

                        String am_pm = new String();
                        if (fromTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                            am_pm = "PM";
                        }
                        else if (fromTimeCalendar.get(Calendar.AM_PM) == Calendar.AM){
                            am_pm = "AM";
                        }
                        String hourString = (fromTimeCalendar.get(Calendar.HOUR) == 0)?"12":fromTimeCalendar.get(Calendar.HOUR) + "";
                        String minuteString = (from_minute < 10)?"0" + from_minute:from_minute + "";
                        fromTime = hourString + ":" + minuteString + " " + am_pm;
                        fromTimeButton.setText(fromTime);

                    }
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);

                timePickerDialog.show();
            }
        });

        toTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar toTimeCalendar = Calendar.getInstance();
                        toTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        toTimeCalendar.set(Calendar.MINUTE, minute);
                        
                        to_hourOfDay = toTimeCalendar.get(Calendar.HOUR_OF_DAY);
                        to_minute = toTimeCalendar.get(Calendar.MINUTE);

                        String am_pm = new String();
                        if (toTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                            am_pm = "PM";
                        }
                        else if (toTimeCalendar.get(Calendar.AM_PM) == Calendar.AM){
                            am_pm = "AM";
                        }
                        String hourString = (toTimeCalendar.get(Calendar.HOUR) == 0)?"12":toTimeCalendar.get(Calendar.HOUR) + "";
                        String minuteString = (to_minute < 10)?"0" + to_minute:to_minute + "";
                        toTime = hourString + ":" + minuteString + " " + am_pm;
                        toTimeButton.setText(toTime);

                    }
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);

                timePickerDialog.show();
            }
        });

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
