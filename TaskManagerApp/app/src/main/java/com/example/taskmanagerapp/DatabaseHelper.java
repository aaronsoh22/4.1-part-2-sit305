package com.example.taskmanagerapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "TaskManager.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Tasks(Title TEXT primary key, Description TEXT, DueDate TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Tasks");
    }
    public Boolean insertuserdata(String Title, String Description, String DueDate)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", Title);
        contentValues.put("Description", Description);
        contentValues.put("DueDate", DueDate);
        long result=DB.insert("Tasks", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String Title, String Description, String DueDate)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Description", Description);
        contentValues.put("DueDate", DueDate);
        Cursor cursor = DB.rawQuery("Select * from Tasks where Title = ?", new String[]{Title});
        if (cursor.getCount() > 0) {
            long result = DB.update("Tasks", contentValues, "Title=?", new String[]{Title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String Title)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tasks where Title = ?", new String[]{Title});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Tasks", "Title=?", new String[]{Title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from Tasks", null);
    }
}