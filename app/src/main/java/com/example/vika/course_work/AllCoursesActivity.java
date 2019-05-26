package com.example.vika.course_work;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class AllCoursesActivity extends AppCompatActivity {

    DBCourses db;
    ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);

        db = new DBCourses(this);
        SQLiteDatabase database = db.getWritableDatabase();
        stringArrayList = new ArrayList<String>();

        Cursor cursor = database.query("courses", null, null,
                null, null, null,
                null, null);
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("id");
            int titleColIndex = cursor.getColumnIndex("title");
            int descriptionColIndex = cursor.getColumnIndex("description");
            int countColIndex = cursor.getColumnIndex("count");


                do {
                    Log.d(LOG_TAG,
                            "ID = " + cursor.getInt(idColIndex) + ", title = "
                                    + cursor.getString(titleColIndex) + ", description = "
                                    + cursor.getString(descriptionColIndex) + ", count = "
                                    + cursor.getString(countColIndex)
                    );
                    stringArrayList.add(cursor.getString(titleColIndex));
                    }

                while (cursor.moveToNext());

            }

        cursor.close();
        Log.d(LOG_TAG, "stringArrayList is : " + stringArrayList);

        for(int i = 0; i < stringArrayList.size(); i++){
            if(stringArrayList.get(i).equals("")){
                stringArrayList.remove(i);
            }
        }
        Log.d(LOG_TAG, "stringArrayList now is : " + stringArrayList);
    }
}