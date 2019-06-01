package com.example.vika.course_work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class ThisCourseActivity extends AppCompatActivity implements View.OnClickListener {
    TextView selected, desTv, countTv;
    DBCourses dbCourses;
    String Title, Description;
    int Count;
    ArrayList<String> lessonsArrayList;
    ListView coursesListView;
    String[] data = new String[2];
    int ID;
    String FILENAME;
    int n = 1;
    ArrayList<String> files;
    ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_course);

        selected = (TextView) findViewById(R.id.textViewSelected);
        desTv = (TextView) findViewById(R.id.DestextView);
        countTv = (TextView) findViewById(R.id.CounttextView);


        coursesListView = (ListView) findViewById(R.id.lvLessons);
        Intent intent = getIntent();
        Title = intent.getStringExtra("course_title");
        dbCourses = new DBCourses(this);
        selected.setText(Title);
        ID = intent.getIntExtra("index", -1);

        data = readDB(Title);
        readDB(Title);
        desTv.setText(data[0]);
        countTv.setText(data[1]);

        int id = 1;
        stringArrayList = new ArrayList<String>();
        stringArrayList= intent.getStringArrayListExtra("stringArrayList");

        files = new ArrayList<>();
        for(int i = 0; i < 10; i++){
                files.add(ID + "." + id);
                id++;
        }
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

    void readFiles()
    {
        for(int i = 0; i < files.size(); i++) {
            FILENAME = files.get(i);
            Log.d(LOG_TAG, "file: " + FILENAME);
            readFile(FILENAME);
        }

    }

    void readFile(String FILENAME)
    {
        try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        openFileInput(FILENAME)));
                String str = "";
                while ((str = br.readLine()) != null) {
                    Log.d(LOG_TAG, str);
                    files.add(str);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {


        }
    }
}
