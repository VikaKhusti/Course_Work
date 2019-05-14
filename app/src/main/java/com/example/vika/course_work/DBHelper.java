package com.example.vika.course_work;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table users ("
                + "id integer primary key autoincrement,"
                + "username text,"
                + "login text,"
                + "password text" + ");");
        Log.d(LOG_TAG, "DB Users is created");


         String username = "Admin";
         String login = "admin_login";
         String password = "admin_pass";
         ContentValues cv = new ContentValues();
         cv.put("username", username);
         cv.put("login", login);
         cv.put("password", password);
         db.insert("users", null, cv);

         Log.d(LOG_TAG, "Admin is created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "users");

        onCreate(db);
    }
}
