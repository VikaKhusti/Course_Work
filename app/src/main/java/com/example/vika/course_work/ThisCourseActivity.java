package com.example.vika.course_work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class ThisCourseActivity extends AppCompatActivity implements View.OnClickListener {
    TextView selected, desTv, countTv;
    DBCourses dbCourses;
    DBLessons dbLessons;
    String Title, Description;
    int Count;
    ArrayList<String> lessonsArrayList;
    ListView lessonsLv;
    String[] data = new String[2];
    int ID;
    int n = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_course);

        selected = (TextView) findViewById(R.id.textViewSelected);
        desTv = (TextView) findViewById(R.id.DestextView);
        countTv = (TextView) findViewById(R.id.CounttextView);

        lessonsLv = (ListView) findViewById(R.id.lvLessons);
        Intent intent = getIntent();
        Title = intent.getStringExtra("course_title");
        dbCourses = new DBCourses(this);
        dbLessons = new DBLessons(this);
        selected.setText(Title);
        ID = intent.getIntExtra("index", -1);

        data = readDB(Title);
        desTv.setText(data[0]);
        countTv.setText(data[1]);

        lessonsArrayList = new ArrayList<String>();
        readLessonDB();

    }


    String[] readDB(String title)
    {
        SQLiteDatabase database = dbCourses.getReadableDatabase();

        Log.d(LOG_TAG, "-----Rows in courses------");
        Cursor cursor = database.query("courses", null, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            int idColIndex = cursor.getColumnIndex("id");
            int titleColIndex = cursor.getColumnIndex("title");
            int descriptionColIndex = cursor.getColumnIndex("description");
            int countColIndex = cursor.getColumnIndex("count");

                do{
                    Log.d(LOG_TAG,
                            "ID = " + cursor.getInt(idColIndex) +
                            ", title = " + cursor.getString(titleColIndex) +
                            ", description = " + cursor.getString(descriptionColIndex) +
                            ", count = " + cursor.getInt(countColIndex)
                    );

                        if(Title.equals(cursor.getString(titleColIndex))){
                            Description =  cursor.getString(descriptionColIndex);
                            data[0] = Description;
                            Count = cursor.getInt(countColIndex);
                            data[1] = String.valueOf(Count);
                        }
                }
                while (cursor.moveToNext());

        }
        else {
            Log.d(LOG_TAG, "------ 0 rows ------");

        }
        cursor.close();
        return data;
    }

    void readLessonDB()
    {
        SQLiteDatabase database = dbLessons.getReadableDatabase();

        Log.d(LOG_TAG, "----Rows in lessons---");
        Cursor cursor = database.query("lessons", null, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            int idColIndex = cursor.getColumnIndex("id");
            int course_idColIndex = cursor.getColumnIndex("course_id");
            int titleColIndex = cursor.getColumnIndex("title");
            int themeColIndex = cursor.getColumnIndex("theme");
            int linkColIndex = cursor.getColumnIndex("link");
            int testColIndex = cursor.getColumnIndex("test");

                do{
                    Log.d(LOG_TAG,
                            "ID = " + cursor.getInt(idColIndex) +
                            ", course_id = " + cursor.getInt(course_idColIndex) +
                            ", title = " + cursor.getString(titleColIndex) +
                            ", theme =  " + cursor.getString(themeColIndex) +
                            ", link = " + cursor.getString(linkColIndex) +
                            ", test = " + cursor.getString(testColIndex)
                    );


                }
                while (cursor.moveToNext());
        }
        cursor.close();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {


        }
    }
}
