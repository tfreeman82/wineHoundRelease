package com.example.tristanfreeman.winehound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tristanfreeman on 3/18/15.
 */
public class wineSQLiteHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "wines.db";
    private static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    //wine table functionality
    public static final String WINES_TABLE = "WINES";
    public static final String COLUMN_WINE = "wine";

    private static final String DATABASE_CREATE = "create table"
            + WINES_TABLE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_WINE
            + " text not null);";
    //creates database if needed
    public wineSQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    //only called to create database schema the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
