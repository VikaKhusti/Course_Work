package com.example.vika.course_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCourses extends SQLiteOpenHelper {
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "courses");

        onCreate(db);
    }
}
