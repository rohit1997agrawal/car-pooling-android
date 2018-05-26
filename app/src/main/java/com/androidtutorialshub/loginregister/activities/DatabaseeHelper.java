package com.androidtutorialshub.loginregister.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.name;

/**
 * Created by rohit on 9/30/2017.
 */

public class DatabaseeHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "rides.db";
    public static final String TABLE_NAME = "rides_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Date_Journey";
    public static final String COL_3 = "Source";
    public static final String COL_4 = "Departure";
    public static final String COL_5 = "Destination";
    public static final String COL_6 = "Arrival";
    public static final String COL_7 = "Type_car";
    public static final String COL_8 = "Seats";

    public static final String COL_9 = "Fare";
    public static final String COL_10 = "Number";


    public DatabaseeHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE_JOURNEY TEXT , SOURCE TEXT , DEPARTURE DATE ,DESTINATION TEXT,ARRIVAL TEXT , TYPE_CAR TEXT , SEATS INTEGER , FARE INTEGER , NUMBER INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String Date_Journey, String Source, String Departure, String Destination, String Arrival, String Type_Car, String Seats, String Fare, String Number) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Date_Journey);
        contentValues.put(COL_3, Source);
        contentValues.put(COL_4, Departure);
        contentValues.put(COL_5, Destination);
        contentValues.put(COL_6, Arrival);
        contentValues.put(COL_7, Type_Car);
        contentValues.put(COL_8, Seats);
        contentValues.put(COL_9, Fare);
        contentValues.put(COL_10, Number);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllRides() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String Date_Journey, String Source, String Departure, String Destination, String Arrival, String Type_Car, String Seats, String Fare, String Number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, Date_Journey);
        contentValues.put(COL_3, Source);
        contentValues.put(COL_4, Departure);
        contentValues.put(COL_5, Destination);
        contentValues.put(COL_6, Arrival);
        contentValues.put(COL_7, Type_Car);
        contentValues.put(COL_8, Seats);
        contentValues.put(COL_9, Fare);
        contentValues.put(COL_10, Number);

        int result = db.update(TABLE_NAME, contentValues, "id = ?", new String[] { id });
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


}
