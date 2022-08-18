package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
   public static final String DBNAME = "Login";
   
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table users(username text primary key,password  text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("drop table if exists users");
    }

    public boolean insertdata(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        long insert = db.insert("users", null, cv);
        if (insert==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean checkusername(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? " ,new String[]{username});
    if (cursor.getCount()>0)
    {
        return true;
    }
    else
    {
        return false;
    }
    }

    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username  = ? and password= ?", new String[] {username,password});
   if (cursor.getCount()>0)
   {
       return true;
   }
   else
   {
       return false;
   }
    }
}
