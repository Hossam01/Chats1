package com.example.hossam.chats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Hossam on 10/29/2016.
 */
public class Datasend extends SQLiteOpenHelper {
    public static final String name="unity.db";
    public static final int version=1;

    public Datasend(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if NOT EXISTS send(id INTEGER primary key,name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if EXISTS send");
        onCreate(db);
    }


    public void insert(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name", name);
        db.insert("send", null, cv);
    }

    public ArrayList select()
    {
        ArrayList arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from send",null);
        res.moveToFirst();
        while (res.isAfterLast()==false) {
            arrayList.add(res.getString(res.getColumnIndex("id"))+":"+res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return arrayList;
    }
}
