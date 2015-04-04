package com.example.tristanfreeman.winehound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;


import java.util.ArrayList;

/**
 * Created by tristanfreeman on 4/3/15.
 */
public class WineDataSource {

    private Context context;
    private wineSQLiteHelper helper;

    public WineDataSource(Context context){

        context = context;
        helper = new wineSQLiteHelper(context);

    }

    private SQLiteDatabase open(){
        return helper.getWritableDatabase();
    }

    private void close(SQLiteDatabase database){
        database.close();
    }

    public ArrayList<Wine> read(){
        ArrayList<Wine> wines = readWines();

        return wines;
    }

    public ArrayList<Wine> readWines(){
        SQLiteDatabase database = open();

        Cursor cursor = database.query(
                wineSQLiteHelper.WINES_TABLE,new String[] {wineSQLiteHelper.KEY_COLUMN_WINE_NAME, wineSQLiteHelper.KEY_COLUMN_WINE_RATING, BaseColumns._ID},
                null,//selection
                null,//selection args
                null,//group by
                null,//having
                null);//order

        ArrayList<Wine> wines = new ArrayList<Wine>();
        if(cursor.moveToFirst()){
            do{
                Wine wine = new Wine(getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor, wineSQLiteHelper.KEY_COLUMN_WINE_NAME),
                        getFloatFromColumnName(cursor, wineSQLiteHelper.KEY_COLUMN_WINE_RATING));
                wines.add(wine);
            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return wines;
    }

    private int getIntFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }

    private float getFloatFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getFloat(columnIndex);
    }

    public void create(Wine wine){
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues wineValues = new ContentValues();
        wineValues.put(wineSQLiteHelper.KEY_COLUMN_WINE_NAME, wine.getName());
        wineValues.put(wineSQLiteHelper.KEY_COLUMN_WINE_BRAND, wine.getBrand());
        wineValues.put(wineSQLiteHelper.KEY_COLUMN_WINE_ML, wine.getMl());
        wineValues.put(wineSQLiteHelper.KEY_COLUMN_WINE_RETAILER, wine.getRetailer());
        wineValues.put(wineSQLiteHelper.KEY_COLUMN_WINE_NOTES, wine.getNotes());
        wineValues.put(wineSQLiteHelper.KEY_COLUMN_WINE_RATING, wine.getRating());
        long wineID = database.insert(wineSQLiteHelper.WINES_TABLE, null, wineValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }
}
