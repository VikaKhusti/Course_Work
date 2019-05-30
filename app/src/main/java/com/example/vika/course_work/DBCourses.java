package com.example.vika.course_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBCourses extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    public DBCourses(Context context) {
        super(context, "myDB2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table courses (" +
                "id integer primary key autoincrement," +
                "title text," +
                "description text," +
                "count integer);");
        Log.d(LOG_TAG, "DB Courses is created");

        db.execSQL("create table lessons (" +
                "id integer primary key autoincrement," +
                "course_id integer," +
                "lesson_title text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "courses");

        onCreate(db);
    }
}
