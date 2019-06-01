package com.example.vika.course_work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddTestsActivity extends AppCompatActivity implements View.OnClickListener {

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

    final String LOG_TAG = "myLogs";
    String lessonTitle, lessonTheme, lessonLink;
    TextView lessonTitlTextView, testTv;
    EditText testEt, aEt, bEt, cEt, dEt;
    Button saveAndExitButton, addAndContButton;
    long courseID;
    DBLessons dbLessons;
    int N = 1, ID = 1;
    String Content = "", corr = "", test, a, b, c, d;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tests);
        Intent intent = getIntent();
        lessonTitle = intent.getStringExtra("lessonTitle");
        lessonTheme = intent.getStringExtra("lessonTheme");
        lessonLink = intent.getStringExtra("lessonLink");
        courseID = intent.getLongExtra("rowID", -1);
        Log.d(LOG_TAG, "id = " + courseID);


        lessonTitlTextView = (TextView) findViewById(R.id.nameOfLesTextView);
        testTv = (TextView) findViewById(R.id.testTextView);
        lessonTitlTextView.setText("Урок: " + lessonTitle);
        dbLessons = new DBLessons(this);


        saveAndExitButton = (Button) findViewById(R.id.saveAndExitBtn);
        saveAndExitButton.setOnClickListener(this);
        addAndContButton = (Button) findViewById(R.id.addAndContBtn);
        addAndContButton.setOnClickListener(this);

    }

    public void FillTest(String Test, String a, String b, String c, String d){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.aRadioButton:
                        corr = "a";
                        break;
                    case R.id.bRadioButton:
                        corr = "b";
                        break;
                    case R.id.cRadioButton:
                        corr = "c";
                        Toast.makeText(AddTestsActivity.this, "clicked c", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.dRadioButton:
                        corr = "d";
                        break;

                }
            }
        });
        Content += "Тест №" + N + " :" + Test + "\n" +
                "a) " + a + "\n" +
                "b) " + b + "\n" +
                "c) " + c + "\n" +
                "d) " + d + "\n" +
                "Правильна відповідь: " + corr + "\n";

    }


    @Override
    public void onClick(View v) {
        SQLiteDatabase database = dbLessons.getWritableDatabase();
        ContentValues cv = new ContentValues();

        switch (v.getId()) {
            case R.id.addAndContBtn:

                testEt = (EditText) findViewById(R.id.testEditText);
                test = testEt.getText().toString();
                aEt = (EditText) findViewById(R.id.aEditText);
                a = aEt.getText().toString();
                bEt = (EditText) findViewById(R.id.bEditText);
                b = bEt.getText().toString();
                cEt = (EditText) findViewById(R.id.cEditText);
                c = cEt.getText().toString();
                dEt = (EditText) findViewById(R.id.dEditText);
                d = dEt.getText().toString();
                FillTest(test, a, b, c, d);
                Log.d(LOG_TAG, "CONTENT IS: " + Content);
                testEt.setText("");
                aEt.setText("");
                bEt.setText("");
                cEt.setText("");
                dEt.setText("");
                N++;

                break;
            case R.id.saveAndExitBtn:

                cv.put("course_id", courseID);
                cv.put("title", lessonTitle);
                cv.put("theme", lessonTheme);
                cv.put("link", lessonLink);
                cv.put("test", Content);

                long rowID = database.insert("lessons", null, cv);
                Log.d(LOG_TAG, "---rows inserted with ID = " + rowID +
                        "content values are : " + cv);

                if(rowID >= 1)
                    Toast.makeText(AddTestsActivity.this, "Урок успішно записаний в БД", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AdminPanel.class);
                startActivity(intent);
                break;
        }
    }
}
