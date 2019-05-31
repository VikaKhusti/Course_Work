package com.example.vika.course_work;

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
    TextView selected;
    int ID;
    String FILENAME;
    int n = 1;
    ArrayList<String> files;
    Button look;
    ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_course);

        selected = (TextView) findViewById(R.id.textViewSelected);
        Intent intent = getIntent();
        String s = intent.getStringExtra("course_title");
        ID = intent.getIntExtra("index", -1);
        selected.setText(s);
        int id = 1;
        look = (Button) findViewById(R.id.readButton);
        stringArrayList = new ArrayList<String>();
        stringArrayList= intent.getStringArrayListExtra("stringArrayList");

        files = new ArrayList<>();
        for(int i = 0; i < 10; i++){
                files.add(ID + "." + id);
                id++;
        }

        look.setOnClickListener(this);
    }

    void readFile()
    {
        for(int i = 0; i < files.size(); i++) {
            FILENAME = files.get(i);
            Log.d(LOG_TAG, "file: " + FILENAME);
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

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.readButton:
                Log.d(LOG_TAG, "logs: ");
                Log.d(LOG_TAG, "files are " + files);
                readFile();
                break;

        }
    }
}
