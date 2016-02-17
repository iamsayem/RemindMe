package com.example.sayem.remindme;

/**
 * Created by Sayem on 1/3/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sayem on 12/19/2015.
 */
public class ItemListDatabase extends SQLiteOpenHelper{

    private ItemListDatabase itemListDatabase;
    private Context ourContext;

    private static final String PHARMACY_ITEM_NAME = "pharmacy_item_name";
    private static final String SUPER_SHOP_ITEM_NAME = "super_shop_item_name";
    private static final String STATIONERY_SHOP_ITEM_NAME = "stationery_shop_item_name";
    private static final String MARKET_ITEM_NAME = "market_item_name";
    private static final String HOTEL_ITEM_NAME = "hotel_item_name";
    private static final String HARDWARE_SHOP_ITEM_NAME = "hardware_shop_item_name";
    private static final String COMPUTER_ACCESSORIES_SHOP_ITEM_NAME = "computer_accessories_shop_item_name";
    private static final String OTHERS_ITEM_NAME = "others_item_name";

    private static final String DATABASE_NAME = "ItemListDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String PHARMACY = "Pharmacy";
    private static final String SUPER_SHOP = "Super_Shop";
    private static final String STATIONERY_SHOP = "Stationery_Shop";
    private static final String MARKET = "Market";
    private static final String HOTEL = "Hotel";
    private static final String HARDWARE_SHOP = "Hardware_Shop";
    private static final String COMPUTER_ACCESSORIES_SHOP = "Computer_Accessories_Shop";
    private static final String OTHERS = "Others";

    String CREATE_TABLE_PHARMACY = "CREATE TABLE " + PHARMACY + "("
            + PHARMACY_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_SUPER_SHOP = "CREATE TABLE " + SUPER_SHOP + "("
            + SUPER_SHOP_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_STATIONERY_SHOP = "CREATE TABLE " + STATIONERY_SHOP + "("
            + STATIONERY_SHOP_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_MARKET = "CREATE TABLE " + MARKET + "("
            + MARKET_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_HOTEL = "CREATE TABLE " + HOTEL + "("
            + HOTEL_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_HARDWARE_SHOP = "CREATE TABLE " + HARDWARE_SHOP + "("
            + HARDWARE_SHOP_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_COMPUTER_ACCESSORIES_SHOP = "CREATE TABLE " + COMPUTER_ACCESSORIES_SHOP + "("
            + COMPUTER_ACCESSORIES_SHOP_ITEM_NAME + " TEXT"
            + ");";
    String CREATE_TABLE_OTHERS = "CREATE TABLE " + OTHERS + "("
            + OTHERS_ITEM_NAME + " TEXT"
            + ");";




    public ItemListDatabase(Context ourContext){
        super(ourContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PHARMACY);
        db.execSQL(CREATE_TABLE_SUPER_SHOP);
        db.execSQL(CREATE_TABLE_STATIONERY_SHOP);
        db.execSQL(CREATE_TABLE_MARKET);
        db.execSQL(CREATE_TABLE_HOTEL);
        db.execSQL(CREATE_TABLE_HARDWARE_SHOP);
        db.execSQL(CREATE_TABLE_COMPUTER_ACCESSORIES_SHOP);
        db.execSQL(CREATE_TABLE_OTHERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PHARMACY);
        db.execSQL("DROP TABLE IF EXISTS " + SUPER_SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + STATIONERY_SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + MARKET);
        db.execSQL("DROP TABLE IF EXISTS " + HOTEL);
        db.execSQL("DROP TABLE IF EXISTS " + HARDWARE_SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + COMPUTER_ACCESSORIES_SHOP);
        db.execSQL("DROP TABLE IF EXISTS " + OTHERS);

        onCreate(db);
    }


    public long insertData(ItemNameClass itemNameClass, String group_position){
        long database = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (group_position.equals("Pharmacy")){
            contentValues.put(PHARMACY_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(PHARMACY, null, contentValues);
            db.close();

//            String sql = "insert into " + PHARMACY + "(" + PHARMACY_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }
        else if (group_position.equals("Super Shop")){
            contentValues.put(SUPER_SHOP_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(SUPER_SHOP, null, contentValues);
            db.close();

//            String sql = "insert into " + SUPER_SHOP + "(" + SUPER_SHOP_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }
        else if (group_position.equals("Stationery Shop")){
            contentValues.put(STATIONERY_SHOP_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(STATIONERY_SHOP, null, contentValues);
            db.close();

//            String sql = "insert into " + STATIONERY_SHOP + "(" + STATIONERY_SHOP_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }
        else if (group_position.equals("Market")){
            contentValues.put(MARKET_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(MARKET, null, contentValues);
            db.close();

//            String sql = "insert into " + MARKET + "(" + MARKET_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }
        else if (group_position.equals("Hotel")){
            contentValues.put(HOTEL_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(HOTEL, null, contentValues);
            db.close();


//            String sql = "insert into " + HOTEL + "(" + HOTEL_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }
        else if (group_position.equals("Hardware Shop")){
            contentValues.put(HARDWARE_SHOP_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(HARDWARE_SHOP, null, contentValues);
            db.close();

//            String sql = "insert into " + HARDWARE_SHOP + "(" + HARDWARE_SHOP_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }
        else if (group_position.equals("Computer Accessories Shop")){
            contentValues.put(COMPUTER_ACCESSORIES_SHOP_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(COMPUTER_ACCESSORIES_SHOP, null, contentValues);
            db.close();

//            String sql = "insert into " + COMPUTER_ACCESSORIES_SHOP + "(" + COMPUTER_ACCESSORIES_SHOP_ITEM_NAME + ")" + " values" + "('"
//                    + itemNameClass.getItemName() + "');";
//            db.execSQL(sql);
        }

        else if (group_position.equals("Others")){
            contentValues.put(OTHERS_ITEM_NAME, itemNameClass.getItemName());
            database = db.insert(OTHERS, null, contentValues);
            db.close();
        }
        return database;
    }

    public String[] readData(String group_position){
        String[] item = new String[]{};
        if (group_position.equals("Pharmacy")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + PHARMACY + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(PHARMACY_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();

        }
        else if (group_position.equals("Super Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + SUPER_SHOP + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(SUPER_SHOP_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();

            db.close();

        }
        else if (group_position.equals("Stationery Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + STATIONERY_SHOP + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(STATIONERY_SHOP_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();

            db.close();

        }
        else if (group_position.equals("Market")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + MARKET + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(MARKET_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();

            db.close();

        }
        else if (group_position.equals("Hotel")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + HOTEL + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(HOTEL_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();

            db.close();

        }
        else if (group_position.equals("Hardware Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + HARDWARE_SHOP + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(HARDWARE_SHOP_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }
        else if (group_position.equals("Computer Accessories Shop")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + COMPUTER_ACCESSORIES_SHOP + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(COMPUTER_ACCESSORIES_SHOP_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();

            db.close();

        }

        else if (group_position.equals("Others")){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + OTHERS + ";", null);
            cursor.moveToFirst();
            item = new String[cursor.getCount()];
            if (cursor.moveToFirst()){
                int i = 0;
                do {
                    item[i] = cursor.getString(cursor.getColumnIndex(OTHERS_ITEM_NAME));
                    i++;
                }while(cursor.moveToNext());
            }
            cursor.close();

            db.close();
        }
        return item;
    }

    public int deleteTable(String group_position){
        SQLiteDatabase db = this.getWritableDatabase();
        int database = 0;
        if (group_position.equals("Pharmacy")){
            database = db.delete(PHARMACY, null, null);
            db.close();
        }
        else if (group_position.equals("Super Shop")){
            database = db.delete(SUPER_SHOP, null, null);
            db.close();
        }
        else if (group_position.equals("Stationery Shop")){
            database = db.delete(STATIONERY_SHOP, null, null);
            db.close();
        }
        else if (group_position.equals("Market")){
            database = db.delete(MARKET, null, null);
            db.close();
        }
        else if (group_position.equals("Hotel")){
            database = db.delete(HOTEL, null, null);
            db.close();
        }
        else if (group_position.equals("Hardware Shop")){
            database = db.delete(HARDWARE_SHOP, null, null);
            db.close();
        }
        else if (group_position.equals("Computer Accessories Shop")){
            database = db.delete(COMPUTER_ACCESSORIES_SHOP, null, null);
            db.close();
        }
        else if (group_position.equals("Others")){
            database = db.delete(OTHERS, null, null);
            db.close();
        }
        return database;
    }


    public int deleteRow(String group_position, String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        int database = 0;
        if (group_position.equals("Pharmacy")){
            database = db.delete(PHARMACY, PHARMACY_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Super Shop")){
            database = db.delete(SUPER_SHOP, SUPER_SHOP_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Stationery Shop")){
            database = db.delete(STATIONERY_SHOP, STATIONERY_SHOP_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Market")){
            database = db.delete(MARKET, MARKET_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Hotel")){
            database = db.delete(HOTEL, HOTEL_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Hardware Shop")){
            database = db.delete(HARDWARE_SHOP, HARDWARE_SHOP_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Computer Accessories Shop")){
            database = db.delete(COMPUTER_ACCESSORIES_SHOP, COMPUTER_ACCESSORIES_SHOP_ITEM_NAME + " = ?", new String[] {itemName});
            db.close();
        }
        else if (group_position.equals("Others")){
            database = db.delete(OTHERS, OTHERS_ITEM_NAME + " = ?", new String[]{itemName});
            db.close();
        }
        return database;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PHARMACY, null, null);
        db.delete(SUPER_SHOP, null, null);
        db.delete(STATIONERY_SHOP, null, null);
        db.delete(MARKET, null, null);
        db.delete(HOTEL, null, null);
        db.delete(HARDWARE_SHOP, null, null);
        db.delete(COMPUTER_ACCESSORIES_SHOP, null, null);
        db.delete(OTHERS, null, null);
        db.close();
    }
}
