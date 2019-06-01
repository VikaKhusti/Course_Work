package com.example.vika.course_work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBLessons extends SQLiteOpenHelper {
    public DBLessons(Context context) {
        super(context, "lessonsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table lessons (" +
                "id integer primary key autoincrement," +
                "course_id integer," +
                "title text," +
                "theme text," +
                "link text," +
                "test text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "lessons");
    }
}
