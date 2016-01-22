package com.example.sayem.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sayem on 1/22/2016.
 */
public class AlarmStateDatabase extends SQLiteOpenHelper {

    private AlarmStateDatabase alarmStateDatabase;
    private Context context;

    private static final String DATABASE_NAME = "AlarmStateDatabase";
    private static final int DATABASE_VERSION = 1;

    // SCHEDULE_ALARM variable declaration
    private static final String SCHEDULE_ALARM_TABLE = "schedule_alarm";

    private static final String SCHEDULE_ALARM_STATE = "schedule_alarm_state";

    // NON_SCHEDULE_ALARM variable declaration
    private static final String NON_SCHEDULE_ALARM_TABLE = "non_schedule_alarm";

    private static final String NON_SCHEDULE_ALARM_STATE = "non_schedule_alarm_state";

    String CREATE_SCHEDULE_ALARM_TABLE = "CREATE TABLE " + SCHEDULE_ALARM_TABLE + "("
            + SCHEDULE_ALARM_STATE + " INTEGER" +
            ");";

    String CREATE_NON_SCHEDULE_ALARM_TABLE = "CREATE TABLE " + NON_SCHEDULE_ALARM_TABLE + "("
            + NON_SCHEDULE_ALARM_STATE + " INTEGER" +
            ");";

    public AlarmStateDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_SCHEDULE_ALARM_TABLE);
        db.execSQL(CREATE_NON_SCHEDULE_ALARM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_ALARM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NON_SCHEDULE_ALARM_TABLE);

        onCreate(db);
    }

    public long insertScheduleAlarmTable(AlarmStateClass alarmStateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SCHEDULE_ALARM_STATE, alarmStateClass.getAlarmState());
        database = db.insert(SCHEDULE_ALARM_TABLE, null, contentValues);
        db.close();

        return database;
    }

    public long insertNonScheduleAlarmTable(AlarmStateClass alarmStateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NON_SCHEDULE_ALARM_STATE, alarmStateClass.getAlarmState());
        database = db.insert(NON_SCHEDULE_ALARM_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public int[] readScheduleAlarmTable(){
        int[] alarmState = new int[]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SCHEDULE_ALARM_TABLE + ";", null);
        cursor.moveToFirst();
        alarmState = new int[cursor.getCount()];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                alarmState[i] = cursor.getInt(cursor.getColumnIndex(SCHEDULE_ALARM_STATE));
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return alarmState;
    }

    public int[] readNonScheduleAlarmTable(){
        int[] alarmState = new int[]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NON_SCHEDULE_ALARM_TABLE + ";", null);
        cursor.moveToFirst();
        alarmState = new int[cursor.getCount()];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                alarmState[i] = cursor.getInt(cursor.getColumnIndex(NON_SCHEDULE_ALARM_STATE));
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return alarmState;
    }

    public long updateScheduleAlarmTable(AlarmStateClass alarmStateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SCHEDULE_ALARM_STATE, alarmStateClass.getAlarmState());
        database = db.update(SCHEDULE_ALARM_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

    public long updateNonScheduleAlarmTable(AlarmStateClass alarmStateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NON_SCHEDULE_ALARM_STATE, alarmStateClass.getAlarmState());
        database = db.update(NON_SCHEDULE_ALARM_TABLE, contentValues, null, null);
        db.close();
        return database;
    }
}
