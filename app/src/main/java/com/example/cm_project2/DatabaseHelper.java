package com.example.cm_project2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String COL_10 = "S9";
    private static final String COL_11 = "C1";
    private static final String COL_12 = "C2";
    private static final String COL_13 = "C3";
    private static final String COL_14 = "C4";
    private static final String COL_15 = "C5";
    private static final String COL_16 = "C6";
    private static final String COL_17 = "C7";
    private static final String COL_18 = "C8";
    private static final String COL_19 = "DATETIME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static final String TABLE_CREATE_PROCESS =
            "CREATE TABLE " + TABLE1_NAME + "()";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PROCESS);
        //db.execSQL(LUBAN_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
