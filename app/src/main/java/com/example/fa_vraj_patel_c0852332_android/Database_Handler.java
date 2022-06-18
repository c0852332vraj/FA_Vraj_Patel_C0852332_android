package com.example.fa_vraj_patel_c0852332_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database_Handler extends SQLiteOpenHelper {


    public Database_Handler(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLACES_TABLE = "CREATE TABLE " + Utils.TABLE_NAME + " (" +
                Utils.KEY_ID + " INTEGER PRIMARY KEY," +
                Utils.LATITUDE + " TEXT," +
                Utils.LONGITUDE + " TEXT," +
                Utils.TITLE + " TEXT)";

        db.execSQL(CREATE_PLACES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);
        onCreate(db);
    }
    public List<region> getAllRegions() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<region> regionList = new ArrayList<>();
        String getAll = "SELECT * FROM " + Utils.TABLE_NAME;
        Cursor cursor = database.rawQuery(getAll, null);
        if (cursor.moveToFirst()) {
            do {
                region reg = new region();
                reg.setId(cursor.getInt(0));
                reg.setReglatitude(cursor.getString(1));
                reg.setReglongitude(cursor.getString(2));
                reg.setTitle(cursor.getString(3));

                regionList.add(reg);
            } while (cursor.moveToNext());
        }
        return regionList;
    }


    public int getRegionsNum() {
        SQLiteDatabase database = this.getReadableDatabase();
        String getAll = "SELECT * FROM " + Utils.TABLE_NAME;
        Cursor cursor = database.rawQuery(getAll, null);
        return cursor.getCount();
    }

    public void addRegion(region reg) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.LATITUDE, reg.getReglatitude());
        contentValues.put(Utils.LONGITUDE, reg.getReglongitude());
        contentValues.put(Utils.TITLE, reg.getTitle());
        database.insert(Utils.TABLE_NAME, null, contentValues);
        database.close();
    }


    public int editRegion(region reg) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.LATITUDE, reg.getReglatitude());
        contentValues.put(Utils.LONGITUDE, reg.getReglongitude());
        contentValues.put(Utils.TITLE, reg.getTitle());

        int result = database.update(Utils.TABLE_NAME, contentValues,
                Utils.KEY_ID + "=?",
                new String[]{String.valueOf(reg.getId())});
        database.close();
        return result;
    }

    public void deleteRegion(region reg) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Utils.TABLE_NAME, Utils.KEY_ID + "=?",
                new String[]{String.valueOf(reg.getId())});
        database.close();
    }
}
