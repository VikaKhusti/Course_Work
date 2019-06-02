package com.example.vika.course_work;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class ThisCourseActivity extends AppCompatActivity implements View.OnClickListener {
    TextView selected, desTv, countTv;
    DBCourses dbCourses;
    DBLessons dbLessons;
    String Title, Description;
    int Count;
    ArrayList<String> lessonsArrayList;
    ListView lessonsLv;
    String[] data = new String[3];
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_course);

        selected = (TextView) findViewById(R.id.textViewSelected);
        desTv = (TextView) findViewById(R.id.DestextView);
        countTv = (TextView) findViewById(R.id.CounttextView);

        lessonsLv = (ListView) findViewById(R.id.lvMain);
        Intent intent = getIntent();
        Title = intent.getStringExtra("course_title");
        dbCourses = new DBCourses(this);
        dbLessons = new DBLessons(this);
        selected.setText(Title);
        ID = intent.getIntExtra("index", -1);

        data = readDB(Title);
        desTv.setText(data[0]);
        countTv.setText(data[1]);
        ID = Integer.valueOf(data[2]);
        Log.d(LOG_TAG, "ID of course = " + ID);

        String[] lesson = new String[7];
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
                if(ID==cursor.getInt(course_idColIndex)){
                    lesson[0] = (String.valueOf(cursor.getInt(idColIndex)));
                    lesson[1] = (String.valueOf(cursor.getInt(course_idColIndex)));
                    lesson[2] = (cursor.getString(titleColIndex));
                    lesson[3] = (cursor.getString(themeColIndex));
                    lesson[4] = (cursor.getString(linkColIndex));
                    lesson[5] = (cursor.getString(testColIndex));}

            //    Log.d(LOG_TAG,
             //           "ID = " + cursor.getInt(idColIndex) +
              //                  ", course_id = " + cursor.getInt(course_idColIndex) +
               //                 ", title = " + cursor.getString(titleColIndex) +
               //                 ", theme =  " + cursor.getString(themeColIndex) +
               //                 ", link = " + cursor.getString(linkColIndex) +
              //                  ", test = " + cursor.getString(testColIndex)
              //  );
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        Log.d(LOG_TAG, "ARRAY IS : " + Arrays.toString(lesson));

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
                            ID = cursor.getInt(idColIndex);
                            data[2] = String.valueOf(cursor.getInt(idColIndex));
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


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {


        }
    }
}
