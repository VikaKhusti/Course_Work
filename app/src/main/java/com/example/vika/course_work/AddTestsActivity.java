package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddTestsActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    String lessonTitle, lessonTheme, lessonLink;
    TextView lessonTitlTextView, testTv;
    EditText testEt, aEt, bEt, cEt, dEt;
    Button saveAndExitButton, addAndContButton;
    int rowID;
    int N = 1;
    String Content = "", corr, test, a, b, c, d;

    public void FillFile(String t, String a, String b, String c, String d, String correct){


        Content += "Тест №" + N + ": " + "\n" +
                "a) " + a + "\n" +
                "b) " + b + "\n" +
                "c) " + c + "\n" +
                "d) " + d + "\n";


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
        Content += "Правильна відповідь: " + corr + "\n";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tests);
        Intent intent = getIntent();
        lessonTitle = intent.getStringExtra("lessonTitle");
        lessonTheme = intent.getStringExtra("lessonTheme");
        lessonLink = intent.getStringExtra("lessonLink");
        rowID = intent.getIntExtra("rowID", -1);



       // if(rowID == -1){
       //     throw new IllegalArgumentException("Trouble with database");
      //  }

        lessonTitlTextView = (TextView) findViewById(R.id.nameOfLesTextView);
        testTv = (TextView) findViewById(R.id.testTextView);
        lessonTitlTextView.setText("Урок: " + lessonTitle);

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
        saveAndExitButton = (Button) findViewById(R.id.saveAndExitBtn);
        saveAndExitButton.setOnClickListener(this);
        addAndContButton = (Button) findViewById(R.id.addAndContBtn);
        addAndContButton.setOnClickListener(this);

        Content += rowID + "\n" +
                "Назва: " + lessonTitle + "\n" +
                "Тема: " +  lessonTheme + "\n" +
                " Посилання: " + lessonLink  + "\n";

        FillFile(test, a, b, c, d, corr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAndContBtn:
                testEt.setText("");
                aEt.setText("");
                bEt.setText("");
                cEt.setText("");
                dEt.setText("");
                FillFile(test, a, b, c, d, corr);
                Log.d(LOG_TAG, "content is " + Content);
                N++;
                break;
            case R.id.saveAndExitBtn:

                break;
        }
    }
}
