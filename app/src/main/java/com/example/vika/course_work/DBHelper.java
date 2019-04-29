package com.example.vika.course_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDB";
    public static final String TABLE_USERS = "users";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";


    public DBHelper(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "(" + KEY_ID +
                " integer primary key," + KEY_NAME + " text," +
                KEY_LOGIN + "text," + KEY_PASSWORD + "text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERS);

        onCreate(db);
    }
}
