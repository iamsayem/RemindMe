package com.example.sayem.remindme;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;


public class AlarmActivity extends Activity {

    TextView setTheAlarmTextView;
    TextView scheduleBasedAlarmTextView;
    Switch nonScheduleOnOffSwitch;
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

    private void prepareData(){

        prepareNonScheduleAlarmSwitchState();
        prepareScheduleAlarmSwitchState();
        prepareFromDate();
        prepareFromTime();
        prepareToDate();
        prepareToTime();
        prepareAlarmTone();

    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareData();
    }

    private void initializeAll(){

        calendar = Calendar.getInstance();

        setTheAlarmTextView = (TextView) findViewById(R.id.setTheAlarmTextView);
        scheduleBasedAlarmTextView = (TextView) findViewById(R.id.scheduleBasedAlarmTextView);
        nonScheduleOnOffSwitch = (Switch) findViewById(R.id.nonScheduleOnOffSwitch);
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
        //setAlarmToneTextView.setText("Set alarm tone");

        initializeNonScheduleOnOffSwitch();
        initializeScheduleOnOffSwitch();
        initializeFromDateButton();
        initializeFromTimeButton();
        initializeToDateButton();
        initializeToTimeButton();
        initializeAlarmToneTextView();

    }

    private void initializeNonScheduleOnOffSwitch(){

        nonScheduleOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean alarmState = false;
                AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(getApplicationContext());

                if (isChecked) {
                    scheduleOnOffSwitch.setEnabled(false);
                    nonScheduleOnOffSwitch.setEnabled(true);
                    alarmState = true;
                    AlarmStateClass alarmStateClass = new AlarmStateClass(alarmState ? 1 : 0);
                    if (alarmStateDatabase.readNonScheduleAlarmTable().length == 0) {
                        alarmStateDatabase.insertNonScheduleAlarmTable(alarmStateClass);
                    } else {
                        alarmStateDatabase.updateNonScheduleAlarmTable(alarmStateClass);
                    }

                    /*Intent intent = new Intent(getApplicationContext(), UserLocationActivity.class);
                    startActivity(intent);*/

                } else {
                    scheduleOnOffSwitch.setEnabled(true);
                    nonScheduleOnOffSwitch.setEnabled(true);
                    alarmState = false;
                    AlarmStateClass alarmStateClass = new AlarmStateClass(alarmState ? 1 : 0);

                    if (alarmStateDatabase.readNonScheduleAlarmTable().length == 0) {
                        alarmStateDatabase.insertNonScheduleAlarmTable(alarmStateClass);
                    } else {
                        alarmStateDatabase.updateNonScheduleAlarmTable(alarmStateClass);

                    }
                }
            }
        });

    }

    private void initializeScheduleOnOffSwitch(){

        scheduleOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean alarmState = false;
                AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(getApplicationContext());

                if (isChecked) {
                    nonScheduleOnOffSwitch.setEnabled(false);
                    scheduleOnOffSwitch.setEnabled(true);
                    alarmState = true;
                    AlarmStateClass alarmStateClass = new AlarmStateClass(alarmState ? 1 : 0);
                    if (alarmStateDatabase.readScheduleAlarmTable().length == 0) {
                        alarmStateDatabase.insertScheduleAlarmTable(alarmStateClass);
                    } else {
                        alarmStateDatabase.updateScheduleAlarmTable(alarmStateClass);
                    }

                } else {
                    nonScheduleOnOffSwitch.setEnabled(true);
                    scheduleOnOffSwitch.setEnabled(true);
                    alarmState = false;
                    AlarmStateClass alarmStateClass = new AlarmStateClass(alarmState ? 1 : 0);

                    if (alarmStateDatabase.readScheduleAlarmTable().length == 0) {
                        alarmStateDatabase.insertScheduleAlarmTable(alarmStateClass);
                    } else {
                        alarmStateDatabase.updateScheduleAlarmTable(alarmStateClass);
                    }

                }
            }
        });

    }

    private void initializeFromDateButton(){

        calendar = Calendar.getInstance();
        fromDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog = new DatePickerDialog(AlarmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar fromDateCalendar = Calendar.getInstance();
                        fromDateCalendar.set(Calendar.YEAR, year);
                        fromDateCalendar.set(Calendar.MONTH, monthOfYear);
                        fromDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        from_dayOfMonth = fromDateCalendar.get(Calendar.DAY_OF_MONTH);
                        from_monthOfYear = fromDateCalendar.get(Calendar.MONTH);
                        from_year = fromDateCalendar.get(Calendar.YEAR);

                        FromDateClass fromDateClass = new FromDateClass(from_dayOfMonth, from_monthOfYear, from_year);
                        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
                        if (fromToDateDatabase.readFromDateTable().length == 0) {
                            fromToDateDatabase.insertFromDateTable(fromDateClass);
                        } else {
                            fromToDateDatabase.updateFromDateTable(fromDateClass);
                        }

                        fromDate = from_dayOfMonth + "/" + (from_monthOfYear + 1) + "/" + from_year;
                        fromDateButton.setText(fromDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

    }

    private void initializeFromTimeButton(){

        calendar = Calendar.getInstance();
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

                        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
                        FromTimeClass fromTimeClass = new FromTimeClass(from_hourOfDay, from_minute);
                        TimeAmPmClass timeAmPmClass = new TimeAmPmClass(am_pm);

                        if (fromToDateDatabase.readFromTimeTable().length == 0 && fromToDateDatabase.readFromTimeAmPmTable().length == 0){
                            fromToDateDatabase.insertFromTimeTable(fromTimeClass);
                            fromToDateDatabase.insertFromTimeAmPmTable(timeAmPmClass);
                        }else{
                            fromToDateDatabase.updateFromTimeTable(fromTimeClass);
                            fromToDateDatabase.updateFromTimeAmPmTable(timeAmPmClass);
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

    }

    private void initializeToDateButton(){

        calendar = Calendar.getInstance();
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

                        ToDateClass toDateClass = new ToDateClass(to_dayOfMonth, to_monthOfYear, to_year);
                        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
                        if (fromToDateDatabase.readToDateTable().length == 0) {
                            fromToDateDatabase.insertToDateTable(toDateClass);
                        } else {
                            fromToDateDatabase.updateToDateTable(toDateClass);
                        }

                        toDate = to_dayOfMonth + "/" + (to_monthOfYear + 1) + "/" + to_year;
                        toDateButton.setText(toDate);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

    }

    private void initializeToTimeButton(){

        calendar = Calendar.getInstance();
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
                        if (toTimeCalendar.get(Calendar.AM_PM) == Calendar.PM) {
                            am_pm = "PM";
                        } else if (toTimeCalendar.get(Calendar.AM_PM) == Calendar.AM) {
                            am_pm = "AM";
                        }

                        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
                        ToTimeClass toTimeClass = new ToTimeClass(to_hourOfDay, to_minute);
                        TimeAmPmClass timeAmPmClass = new TimeAmPmClass(am_pm);

                        if (fromToDateDatabase.readToTimeTable().length == 0 && fromToDateDatabase.readToTimeAmPmTable().length == 0) {
                            fromToDateDatabase.insertToTimeTable(toTimeClass);
                            fromToDateDatabase.insertToTimeAmPmTable(timeAmPmClass);
                        } else {
                            fromToDateDatabase.updateToTimeTable(toTimeClass);
                            fromToDateDatabase.updateToTimeAmPmTable(timeAmPmClass);
                        }

                        String hourString = (toTimeCalendar.get(Calendar.HOUR) == 0) ? "12" : toTimeCalendar.get(Calendar.HOUR) + "";
                        String minuteString = (to_minute < 10) ? "0" + to_minute : to_minute + "";
                        toTime = hourString + ":" + minuteString + " " + am_pm;
                        toTimeButton.setText(toTime);

                    }
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);

                timePickerDialog.show();
            }
        });

    }


    private void prepareNonScheduleAlarmSwitchState(){

        AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(getApplicationContext());
        int alarmState[] = alarmStateDatabase.readNonScheduleAlarmTable();
        int alarmStateTemp = 0;
        for (int i = 0; i < alarmState.length; i++){
            alarmStateTemp = alarmState[i];
        }
        nonScheduleOnOffSwitch.setChecked((alarmStateTemp == 1) ? true : false);

        if (nonScheduleOnOffSwitch.isChecked()){
            /*Intent intent = new Intent(getApplicationContext(), UserLocationActivity.class);
            startActivity(intent);*/
            scheduleOnOffSwitch.setEnabled(false);
        }
        else {

        }

    }


    private void prepareScheduleAlarmSwitchState(){

        AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(getApplicationContext());
        int alarmState[] = alarmStateDatabase.readScheduleAlarmTable();
        int alarmStateTemp = 0;
        for (int i = 0; i < alarmState.length; i++){
            alarmStateTemp = alarmState[i];
        }
        scheduleOnOffSwitch.setChecked((alarmStateTemp == 1) ? true : false);

        if (scheduleOnOffSwitch.isChecked()){
            /*Intent intent = new Intent(getApplicationContext(), UserLocationActivity.class);
            startActivity(intent);*/
            nonScheduleOnOffSwitch.setEnabled(false);
        }
        else{

        }

    }


    private void prepareFromDate(){
        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
        int[][] dataFromDate = fromToDateDatabase.readFromDateTable();
        int[] dataFromDateTemp = new int[3];
        for (int i = 0; i < dataFromDate.length; i++){
            for (int j = 0; j < dataFromDate[i].length; j++){
                dataFromDateTemp[j] = dataFromDate[i][j];
            }
        }
        fromDate = dataFromDateTemp[0] + "/" + (dataFromDateTemp[1] + 1) + "/" + dataFromDateTemp[2] + "";
        fromDateButton.setText(fromDate);
    }

    private void prepareFromTime(){
        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
        int[][] dataFromTime = fromToDateDatabase.readFromTimeTable();
        int[] dataFromTimeTemp = new int[2];
        String[] dataFromTimeAmPm = fromToDateDatabase.readFromTimeAmPmTable();
        String temp = new String();
        for (int i = 0; i < dataFromTime.length; i++){
            for (int j = 0; j < dataFromTime[i].length; j++){
                dataFromTimeTemp[j] = dataFromTime[i][j];
            }
        }
        for (int i = 0; i < dataFromTimeAmPm.length; i++){
            // nothing
            temp = dataFromTimeAmPm[i];
        }

        if (temp.equals("PM")){
            dataFromTimeTemp[0] -= 12;
        }

        String hourString = (dataFromTimeTemp[0] == 0)?"12":dataFromTimeTemp[0] + "";
        String minuteString = (dataFromTimeTemp[1] < 10)?"0" + dataFromTimeTemp[1]:dataFromTimeTemp[1] + "";
        fromTime = hourString + ":" + minuteString + " " + temp;
        fromTimeButton.setText(fromTime);
    }

    private void prepareToDate(){
        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
        int[][] dataToDate = fromToDateDatabase.readToDateTable();
        int[] dataToDateTemp = new int[3];
        for (int i = 0; i < dataToDate.length; i++){
            for (int j = 0; j < dataToDate[i].length; j++){
                dataToDateTemp[j] = dataToDate[i][j];
            }
        }
        toDate = dataToDateTemp[0] + "/" + (dataToDateTemp[1] + 1) + "/" + dataToDateTemp[2] + "";
        toDateButton.setText(toDate);
    }

    private void prepareToTime(){
        FromToDateDatabase fromToDateDatabase = new FromToDateDatabase(getApplicationContext());
        int[][] dataToTime = fromToDateDatabase.readToTimeTable();
        int[] dataToTimeTemp = new int[2];
        String[] dataToTimeAmPm = fromToDateDatabase.readToTimeAmPmTable();
        String temp = new String();
        for (int i = 0; i < dataToTime.length; i++){
            for (int j = 0; j < dataToTime[i].length; j++){
                dataToTimeTemp[j] = dataToTime[i][j];
            }
        }
        for (int i = 0; i < dataToTimeAmPm.length; i++){
            // nothing
            temp = dataToTimeAmPm[i];
        }

        if (temp.equals("PM")){
            dataToTimeTemp[0] -= 12;
        }

        String hourString = (dataToTimeTemp[0] == 0)?"12":dataToTimeTemp[0] + "";
        String minuteString = (dataToTimeTemp[1] < 10)?"0" + dataToTimeTemp[1]:dataToTimeTemp[1] + "";
        toTime = hourString + ":" + minuteString + " " + temp + "";
        toTimeButton.setText(toTime);
    }

    private void prepareAlarmTone(){
        AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(getApplicationContext());
        String[] alarmToneUri = new String[]{};
        alarmToneUri = alarmStateDatabase.readAlarmToneTable();
        String alarmToneNameTemp = new String();
        for (int i = 0; i < alarmToneUri.length; i++){
            alarmToneNameTemp = alarmToneUri[i];
        }

        if (alarmToneUri.length > 0) {
            Uri uri = Uri.parse(alarmToneNameTemp);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
            String alarmToneName = ringtone.getTitle(getApplicationContext());
            setAlarmToneTextView.setText(Html.fromHtml("<medium>" + "Set alarm tone" + "</medium>" + "<br />"
                    + "<small>" + alarmToneName + "</small>"));
        } else{
            setAlarmToneTextView.setText(Html.fromHtml("<medium>" + "Set alarm tone" + "</medium>"));
        }

    }

    private void initializeAlarmToneTextView(){
        setAlarmToneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent ringtone = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                ringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                ringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                ringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                ringtone.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
                startActivityForResult(ringtone, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK){
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
            String alarmToneName = ringtone.getTitle(getApplicationContext());

            AlarmStateDatabase alarmStateDatabase = new AlarmStateDatabase(getApplicationContext());
            String alarmToneUri = uri.toString();
            AlarmToneClass alarmToneClass = new AlarmToneClass(alarmToneUri);
            if (alarmStateDatabase.readAlarmToneTable().length == 0){
                alarmStateDatabase.insertAlarmToneTable(alarmToneClass);
            }else {
                alarmStateDatabase.updateAlarmToneTable(alarmToneClass);
            }
            setAlarmToneTextView.setText(Html.fromHtml("<medium>" + "Set alarm tone" + "</medium>" + "<br />"
                    + "<small>" + alarmToneName + "</small>"));
        }

    }
}
