package com.example.sayem.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sayem on 1/20/2016.
 */
public class FromToDateDatabase extends SQLiteOpenHelper {

    private FromToDateDatabase fromToDateDatabase;
    private Context context;

    private static final String DATABASE_NAME = "FromToDateDatabase";
    private static final int DATABASE_VERSION = 1;

    // FROM_TIME_TABLE variable declaration
    private static final String FROM_TIME_TABLE = "from_time";

    private static final String FROM_HOUR_OF_DAY = "from_hour_of_day";
    private static final String FROM_MINUTE = "from_minute";

    // FROM_DATE_TABLE variable declaration
    private static final String FROM_DATE_TABLE = "from_date";

    private static final String FROM_DAY_OF_MONTH = "from_day_of_month";
    private static final String FROM_MONTH_OF_YEAR = "from_month_of_year";
    private static final String FROM_YEAR = "from_year";

    // TO_TIME_TABLE variable declaration
    private static final String TO_TIME_TABLE = "to_time";

    private static final String TO_HOUR_OF_DAY = "to_hour_of_day";
    private static final String TO_MINUTE = "to_minute";

    // TO_DATE_TABLE variable declaration
    private static final String TO_DATE_TABLE = "to_date";

    private static final String TO_DAY_OF_MONTH = "to_day_of_month";
    private static final String TO_MONTH_OF_YEAR = "to_month_of_year";
    private static final String TO_YEAR = "to_year";

    // FROM_TIME_AM_PM variable declaration
    private static final String FROM_TIME_AM_PM_TABLE = "from_time_am_pm";

    private static final String FROM_TIME_AM_PM = "from_am_pm";

    // TO_TIME_AM_PM variable declaration
    private static final String TO_TIME_AM_PM_TABLE = "to_time_am_pm";

    private static final String TO_TIME_AM_PM = "to_am_pm";


    String CREATE_FROM_DATE_TABLE = "CREATE TABLE " + FROM_DATE_TABLE + "("
            + FROM_DAY_OF_MONTH + " INTEGER" + ","
            + FROM_MONTH_OF_YEAR + " INTEGER" + ","
            + FROM_YEAR + " INTEGER"
            + ");";

    String CREATE_FROM_TIME_TABLE = "CREATE TABLE " + FROM_TIME_TABLE + "("
            + FROM_HOUR_OF_DAY + " INTEGER" + ","
            + FROM_MINUTE + " INTEGER"
            + ");";

    String CREATE_TO_DATE_TABLE = "CREATE TABLE " + TO_DATE_TABLE + "("
            + TO_DAY_OF_MONTH + " INTEGER" + ","
            + TO_MONTH_OF_YEAR + " INTEGER" + ","
            + TO_YEAR + " INTEGER"
            + ");";

    String CREATE_TO_TIME_TABLE = "CREATE TABLE " + TO_TIME_TABLE + "("
            + TO_HOUR_OF_DAY + " INTEGER" + ","
            + TO_MINUTE + " INTEGER"
            + ");";

    String CREATE_FROM_TIME_AM_PM_TABLE = "CREATE TABLE " + FROM_TIME_AM_PM_TABLE + "("
            + FROM_TIME_AM_PM + " TEXT" +
            ");";

    String CREATE_TO_TIME_AM_PM_TABLE = "CREATE TABLE " + TO_TIME_AM_PM_TABLE + "("
            + TO_TIME_AM_PM + " TEXT" +
            ");";


    public FromToDateDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_FROM_DATE_TABLE);
        db.execSQL(CREATE_FROM_TIME_TABLE);
        db.execSQL(CREATE_TO_DATE_TABLE);
        db.execSQL(CREATE_TO_TIME_TABLE);
        db.execSQL(CREATE_FROM_TIME_AM_PM_TABLE);
        db.execSQL(CREATE_TO_TIME_AM_PM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + FROM_DATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FROM_TIME_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TO_DATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TO_TIME_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FROM_TIME_AM_PM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TO_TIME_AM_PM_TABLE);

        onCreate(db);
    }

    public long insertFromDateTable(FromDateClass fromDateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FROM_DAY_OF_MONTH, fromDateClass.getFrom_dayOfMonth());
        contentValues.put(FROM_MONTH_OF_YEAR, fromDateClass.getFrom_monthOfYear());
        contentValues.put(FROM_YEAR, fromDateClass.getFrom_year());
        database = db.insert(FROM_DATE_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public long insertFromTimeTable(FromTimeClass fromTimeClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FROM_HOUR_OF_DAY, fromTimeClass.getFrom_hourOfDay());
        contentValues.put(FROM_MINUTE, fromTimeClass.getFrom_minute());
        database = db.insert(FROM_TIME_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public long insertToDateTable(ToDateClass toDateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TO_DAY_OF_MONTH, toDateClass.getTo_dayOfMonth());
        contentValues.put(TO_MONTH_OF_YEAR, toDateClass.getTo_monthOfYear());
        contentValues.put(TO_YEAR, toDateClass.getTo_year());
        database = db.insert(TO_DATE_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public long insertToTimeTable(ToTimeClass toTimeClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TO_HOUR_OF_DAY, toTimeClass.getTo_hourOfDay());
        contentValues.put(TO_MINUTE, toTimeClass.getTo_minute());
        database = db.insert(TO_TIME_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public long insertFromTimeAmPmTable(TimeAmPmClass timeAmPmClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FROM_TIME_AM_PM, timeAmPmClass.getAm_pm());
        database = db.insert(FROM_TIME_AM_PM_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public long insertToTimeAmPmTable(TimeAmPmClass timeAmPmClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TO_TIME_AM_PM, timeAmPmClass.getAm_pm());
        database = db.insert(TO_TIME_AM_PM_TABLE, null, contentValues);
        db.close();
        return database;
    }

    public int[][] readFromDateTable(){
        int[][] from_date_data = new int[][]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FROM_DATE_TABLE + ";", null);
        cursor.moveToFirst();
        from_date_data = new int[cursor.getCount()][3];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                int j = 0;
                while(j < 3){
                    from_date_data[i][j] = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(j)));
                    j++;
                }
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return from_date_data;
    }

    public int[][] readFromTimeTable(){
        int[][] from_time_data = new int[][]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FROM_TIME_TABLE + ";", null);
        cursor.moveToFirst();
        from_time_data = new int[cursor.getCount()][2];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                int j = 0;
                while(j < 2){
                    from_time_data[i][j] = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(j)));
                    j++;
                }
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return from_time_data;
    }

    public int[][] readToDateTable(){
        int[][] to_date_data = new int[][]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TO_DATE_TABLE + ";", null);
        cursor.moveToFirst();
        to_date_data = new int[cursor.getCount()][3];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                int j = 0;
                while(j < 3){
                    to_date_data[i][j] = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(j)));
                    j++;
                }
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return to_date_data;
    }

    public int[][] readToTimeTable(){
        int[][] to_time_data = new int[][]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TO_TIME_TABLE + ";", null);
        cursor.moveToFirst();
        to_time_data = new int[cursor.getCount()][2];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                int j = 0;
                while(j < 2){
                    to_time_data[i][j] = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(j)));
                    j++;
                }
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return to_time_data;
    }

    public String[] readFromTimeAmPmTable(){
        String[] am_pm = new String[]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FROM_TIME_AM_PM_TABLE + ";", null);
        cursor.moveToFirst();
        am_pm = new String[cursor.getCount()];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                am_pm[i] = cursor.getString(cursor.getColumnIndex(FROM_TIME_AM_PM));
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return am_pm;
    }

    public String[] readToTimeAmPmTable(){
        String[] am_pm = new String[]{};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TO_TIME_AM_PM_TABLE + ";", null);
        cursor.moveToFirst();
        am_pm = new String[cursor.getCount()];
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                am_pm[i] = cursor.getString(cursor.getColumnIndex(TO_TIME_AM_PM));
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return am_pm;
    }

    public long updateFromDateTable(FromDateClass fromDateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FROM_DAY_OF_MONTH, fromDateClass.getFrom_dayOfMonth());
        contentValues.put(FROM_MONTH_OF_YEAR, fromDateClass.getFrom_monthOfYear());
        contentValues.put(FROM_YEAR, fromDateClass.getFrom_year());
        database = db.update(FROM_DATE_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

    public long updateFromTimeTable(FromTimeClass fromTimeClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FROM_HOUR_OF_DAY, fromTimeClass.getFrom_hourOfDay());
        contentValues.put(FROM_MINUTE, fromTimeClass.getFrom_minute());
        database = db.update(FROM_TIME_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

    public long updateToDateTable(ToDateClass toDateClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TO_DAY_OF_MONTH, toDateClass.getTo_dayOfMonth());
        contentValues.put(TO_MONTH_OF_YEAR, toDateClass.getTo_monthOfYear());
        contentValues.put(TO_YEAR, toDateClass.getTo_year());
        database = db.update(TO_DATE_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

    public long updateToTimeTable(ToTimeClass toTimeClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TO_HOUR_OF_DAY, toTimeClass.getTo_hourOfDay());
        contentValues.put(TO_MINUTE, toTimeClass.getTo_minute());
        database = db.update(TO_TIME_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

    public long updateFromTimeAmPmTable(TimeAmPmClass timeAmPmClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FROM_TIME_AM_PM, timeAmPmClass.getAm_pm());
        database = db.update(FROM_TIME_AM_PM_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

    public long updateToTimeAmPmTable(TimeAmPmClass timeAmPmClass){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TO_TIME_AM_PM, timeAmPmClass.getAm_pm());
        database = db.update(TO_TIME_AM_PM_TABLE, contentValues, null, null);
        db.close();
        return database;
    }

}
