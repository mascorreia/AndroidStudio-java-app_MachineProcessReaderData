package com.example.cm_project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LuBan.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE1_NAME = "Process_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "S1";
    private static final String COL_3 = "S2";
    private static final String COL_4 = "S3";
    private static final String COL_5 = "S4";
    private static final String COL_6 = "S5";
    private static final String COL_7 = "S6";
    private static final String COL_8 = "S7";
    private static final String COL_9 = "S8";
    private static final String COL_10 = "C1";
    private static final String COL_11 = "C2";
    private static final String COL_12 = "C3";
    private static final String COL_13 = "C4";
    private static final String COL_14 = "C5";
    private static final String COL_15 = "C6";
    private static final String COL_16 = "C7";
    private static final String COL_17 = "C8";
    private static final String COL_18 = "DATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE1_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "S1 TEXT, S2 TEXT, S3 TEXT, S4 TEXT, S5 TEXT, S6 TEXT, S7 TEXT, S8 TEXT," +
                "C1 TEXT, C2 TEXT, C3 TEXT, C4 TEXT, C5 TEXT, C6 TEXT, C7 TEXT, C8 TEXT, DATE TEXT) ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(db);
    }

    public void resetCsvTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE1_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "S1 TEXT, S2 TEXT, S3 TEXT, S4 TEXT, S5 TEXT, S6 TEXT, S7 TEXT, S8 TEXT," +
                "C1 TEXT, C2 TEXT, C3 TEXT, C4 TEXT, C5 TEXT, C6 TEXT, C7 TEXT, C8 TEXT, DATE TEXT) ");
    }

    public boolean insertData(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8,
                              String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, s1);
        contentValues.put(COL_3, s2);
        contentValues.put(COL_4, s3);
        contentValues.put(COL_5, s4);
        contentValues.put(COL_6, s5);
        contentValues.put(COL_7, s6);
        contentValues.put(COL_8, s7);
        contentValues.put(COL_9, s8);
        contentValues.put(COL_10, c1);
        contentValues.put(COL_11, c2);
        contentValues.put(COL_12, c3);
        contentValues.put(COL_13, c4);
        contentValues.put(COL_14, c5);
        contentValues.put(COL_15, c6);
        contentValues.put(COL_16, c7);
        contentValues.put(COL_17, c8);
        contentValues.put(COL_18, date);

        long result = db.insert(TABLE1_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + COL_1 + " = " + id, null);
        return res;
    }

    public int countLines(){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer count = (int) (long) DatabaseUtils.queryNumEntries(db, TABLE1_NAME);
        return count;
    }

    public Cursor countPieces(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(S1) FROM " + TABLE1_NAME + " WHERE S1 = 1", null);
        return res;
    }

    public Cursor countIndividualPieces(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(" + COL_3 + "), SUM(" + COL_4 + "), SUM(" + COL_2 + ") - SUM(" + COL_3 + ") - SUM(" + COL_4 + ") FROM " + TABLE1_NAME + " WHERE " + COL_2 + " = 1 or " + COL_3 + " = 1 or " + COL_4 + " = 1", null);
        return res;


    }


}
