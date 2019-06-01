package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    final String LOG_TAG = "myLogs";
    String lessonTitle, lessonTheme, lessonLink;
    TextView lessonTitlTextView, testTv;
    EditText testEt, aEt, bEt, cEt, dEt;
    Button saveAndExitButton, addAndContButton;
    long rowID;
    int N = 1, ID = 1;
    String Content = "", corr, test, a, b, c, d;
    String FILENAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tests);
        Intent intent = getIntent();
        lessonTitle = intent.getStringExtra("lessonTitle");
        lessonTheme = intent.getStringExtra("lessonTheme");
        lessonLink = intent.getStringExtra("lessonLink");
        rowID = intent.getLongExtra("rowID", -1);
        Log.d(LOG_TAG, "id = " + rowID);


        lessonTitlTextView = (TextView) findViewById(R.id.nameOfLesTextView);
        testTv = (TextView) findViewById(R.id.testTextView);
        lessonTitlTextView.setText("Урок: " + lessonTitle);

        FILENAME = rowID + "." + ID;


        saveAndExitButton = (Button) findViewById(R.id.saveAndExitBtn);
        saveAndExitButton.setOnClickListener(this);
        addAndContButton = (Button) findViewById(R.id.addAndContBtn);
        addAndContButton.setOnClickListener(this);

        Content += rowID + "\n" +
                "Назва: " + lessonTitle + "\n" +
                "Тема: " +  lessonTheme + "\n" +
                "Посилання: " + lessonLink  + "\n";


    }

    public void FillFile(String Test, String a, String b, String c, String d){
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

     void  writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            bw.write(Content);
            bw.close();
            Log.d(LOG_TAG, "Файл з іменем " + FILENAME + " записаний тут" + getFilesDir());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //void readFile() {
    //    try {
    //        BufferedReader br = new BufferedReader(new InputStreamReader(
    //                openFileInput(FILENAME)));
    //        String str = "";
    //        while ((str = br.readLine()) != null) {
    //            Log.d(LOG_TAG, str);
    //        }
    //
    //    } catch (FileNotFoundException e) {
    //        e.printStackTrace();
   //     } catch (IOException e) {
    //      e.printStackTrace();
    //    }
   //  }

    @Override
    public void onClick(View v) {
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
                Log.d(LOG_TAG, "VALUES ARE: a  = " + a + "; b = " + b + "; c = " + c + "; d = " + d + "; test = " + test);
                FillFile(test, a, b, c, d);
                Log.d(LOG_TAG, "CONTENT IS: " + Content);
                testEt.setText("");
                aEt.setText("");
                bEt.setText("");
                cEt.setText("");
                dEt.setText("");
                N++;
                break;
            case R.id.saveAndExitBtn:
                writeFile();
                ID++;
               // readFile();
                Intent intent = new Intent(this, AdminPanel.class);
                startActivity(intent);
                break;
        }
    }
}
