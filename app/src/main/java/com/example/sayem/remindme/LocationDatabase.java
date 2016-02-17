package com.example.sayem.remindme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sayem on 2/17/2016.
 */
public class LocationDatabase extends SQLiteOpenHelper {

    private LocationDatabase locationDatabase;
    private Context ourContext;

    private static final String DATABASE_NAME = "LocationDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String PHARMACY_LOCATION = "pharmacy_location";
    private static final String SUPER_SHOP_LOCATION = "super_shop_location";
    private static final String STATIONERY_SHOP_LOCATION = "stationery_shop_location";
    private static final String MARKET_LOCATION = "market_location";
    private static final String HOTEL_LOCATION = "hotel_location";
    private static final String HARDWARE_SHOP_LOCATION = "hardware_shop_location";
    private static final String COMPUTER_ACCESSORIES_SHOP_LOCATION = "computer_accessories_shop_location";
    private static final String OTHERS_LOCATION = "others_location";

    private static final String PHARMACY_LATITUDE = "pharmacy_latitude";
    private static final String PHARMACY_LONGITUDE = "pharmacy_longitude";
    private static final String SUPER_SHOP_LATITUDE = "super_shop_latitude";
    private static final String SUPER_SHOP_LONGITUDE = "super_shop_longitude";
    private static final String STATIONERY_SHOP_LATITUDE = "stationery_shop_latitude";
    private static final String STATIONERY_SHOP_LONGITUDE = "stationery_shop_longitude";
    private static final String MARKET_LATITUDE = "market_latitude";
    private static final String MARKET_LONGITUDE = "market_longitude";
    private static final String HOTEL_LATITUDE = "hotel_latitude";
    private static final String HOTEL_LONGITUDE = "hotel_longitude";
    private static final String HARDWARE_SHOP_LATITUDE = "hardware_shop_latitude";
    private static final String HARDWARE_SHOP_LONGITUDE = "hardware_shop_longitude";
    private static final String COMPUTER_ACCESSORIES_SHOP_LATITUDE = "computer_accessories_shop_latitude";
    private static final String COMPUTER_ACCESSORIES_SHOP_LONGITUDE = "computer_accessories_shop_longitude";
    private static final String OTHERS_LATITUDE = "others_latitude";
    private static final String OTHERS_LONGITUDE = "others_longitude";

     String pharmacyRow1 = new String();
     String pharmacyRow2 = new String();
     String pharmacyRow3 = new String();
     String pharmacyRow4 = new String();

    String othersRow1 = new String();


    String CREATE_TABLE_PHARMACY_LOCATION = "CREATE TABLE " + PHARMACY_LOCATION + "("
            + PHARMACY_LATITUDE + " REAL" + ","
            + PHARMACY_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_SUPER_SHOP_LOCATION = "CREATE TABLE " + SUPER_SHOP_LOCATION + "("
            + SUPER_SHOP_LATITUDE + " REAL" + ","
            + SUPER_SHOP_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_STATIONERY_SHOP_LOCATION = "CREATE TABLE " + STATIONERY_SHOP_LOCATION + "("
            + STATIONERY_SHOP_LATITUDE + " REAL" + ","
            + STATIONERY_SHOP_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_MARKET_LOCATION = "CREATE TABLE " + MARKET_LOCATION + "("
            + MARKET_LATITUDE + " REAL" + ","
            + MARKET_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_HOTEL_LOCATION = "CREATE TABLE " + HOTEL_LOCATION + "("
            + HOTEL_LATITUDE + " REAL" + ","
            + HOTEL_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_HARDWARE_SHOP_LOCATION = "CREATE TABLE " + HARDWARE_SHOP_LOCATION + "("
            + HARDWARE_SHOP_LATITUDE + " REAL" + ","
            + HARDWARE_SHOP_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_COMPUTER_ACCESSORIES_SHOP_LOCATION = "CREATE TABLE " + COMPUTER_ACCESSORIES_SHOP_LOCATION + "("
            + COMPUTER_ACCESSORIES_SHOP_LATITUDE + " REAL" + ","
            + COMPUTER_ACCESSORIES_SHOP_LONGITUDE + " REAL"
            + ");";

    String CREATE_TABLE_OTHERS_LOCATION = "CREATE TABLE " + OTHERS_LOCATION + "("
            + OTHERS_LATITUDE + " REAL" + ","
            + OTHERS_LONGITUDE + " REAL"
            + ");";


    public LocationDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PHARMACY_LOCATION);
        db.execSQL(CREATE_TABLE_SUPER_SHOP_LOCATION);
        db.execSQL(CREATE_TABLE_STATIONERY_SHOP_LOCATION);
        db.execSQL(CREATE_TABLE_MARKET_LOCATION);
        db.execSQL(CREATE_TABLE_HOTEL_LOCATION);
        db.execSQL(CREATE_TABLE_HARDWARE_SHOP_LOCATION);
        db.execSQL(CREATE_TABLE_COMPUTER_ACCESSORIES_SHOP_LOCATION);
        db.execSQL(CREATE_TABLE_OTHERS_LOCATION);

        insertLocationIntoPharmacy();
        db.execSQL(pharmacyRow1);
        db.execSQL(pharmacyRow2);
        db.execSQL(pharmacyRow3);
        db.execSQL(pharmacyRow4);

        insertLocationIntoOthers();
        db.execSQL(othersRow1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PHARMACY_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + SUPER_SHOP_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + STATIONERY_SHOP_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + MARKET_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + HOTEL_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + HARDWARE_SHOP_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + COMPUTER_ACCESSORIES_SHOP_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + OTHERS_LOCATION);

        onCreate(db);
    }

    private void insertLocationIntoPharmacy(){

        pharmacyRow1 = "INSERT INTO " + PHARMACY_LOCATION + " ("
                + PHARMACY_LATITUDE + ", " + PHARMACY_LONGITUDE
                + " ) Values (22.8969794 , 89.50932538)";
        pharmacyRow1 = "INSERT INTO " + PHARMACY_LOCATION + " ("
                + PHARMACY_LATITUDE + ", " + PHARMACY_LONGITUDE
                + " ) Values (22.89697149  , 89.50942278)";

    }

    private void insertLocationIntoSuperShop(){

    }

    private void insertLocationIntoStationeryShop(){

    }

    private void insertLocationIntoMarket(){

    }

    private void insertLocationIntoHotel(){

    }

    private void insertLocationIntoHardwareShop(){

    }

    private void insertLocationIntoComputerAccessoriesShop(){

    }

    private void insertLocationIntoOthers(){
        othersRow1 = "INSERT INTO " + PHARMACY_LOCATION + " ("
                + PHARMACY_LATITUDE + ", " + PHARMACY_LONGITUDE
                + " ) Values (22.8986909  , 89.5014969)";
    }

    public double[][] readData(String group_position){
        double[][] location = new double[][]{};
        if (group_position.equals("Pharmacy")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + PHARMACY_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Super Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + SUPER_SHOP_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Stationery Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + STATIONERY_SHOP_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Market")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + MARKET_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Hotel")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + HOTEL_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Hardware Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + HARDWARE_SHOP_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Computer Accessories Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + COMPUTER_ACCESSORIES_SHOP_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }

        else if (group_position.equals("Others")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + OTHERS_LOCATION + ";", null);
            cursor.moveToFirst();
            location = new double[cursor.getCount()][2];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    int j = 0;
                    while(j < 2){
                        location[i][j] = cursor.getDouble(cursor.getColumnIndex(cursor.getColumnName(j)));
                        j++;
                    }
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        return location;
    }
}
