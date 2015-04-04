package com.example.tristanfreeman.winehound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by tristanfreeman on 3/18/15.
 */
public class wineSQLiteHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "wines.db";
    private static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    //wine table functionality
    public static final String WINES_TABLE = "WINES";
    public static final String COLUMN_WINE = "WINE";
    public static final String KEY_COLUMN_WINE_NAME = "NAME";
    public static final String KEY_COLUMN_WINE_BRAND = "BRAND";
    public static final String KEY_COLUMN_WINE_ML = "ML";
    public static final String KEY_COLUMN_WINE_RETAILER = "RETAILER";
    public static final String KEY_COLUMN_WINE_NOTES = "NOTES";
    public static final String KEY_COLUMN_WINE_RATING = "RATING";


    private static final String DATABASE_CREATE = "CREATE TABLE "
            + WINES_TABLE + "(" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_COLUMN_WINE_NAME + " TEXT, " + KEY_COLUMN_WINE_BRAND + " TEXT, " + KEY_COLUMN_WINE_ML + " INTEGER, "
            + KEY_COLUMN_WINE_RETAILER + " TEXT, " + KEY_COLUMN_WINE_NOTES + " TEXT, " + KEY_COLUMN_WINE_RATING + " FLOAT)";
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
        db.execSQL("DROP TABLE IF EXISTS " + WINES_TABLE);
        onCreate(db);
    }
}
