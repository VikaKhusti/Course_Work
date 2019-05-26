package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddTestsActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    String lessonTitle, lessonTheme, lessonLink;
    TextView lessonTitlTextView, testTv;
    EditText testEt, aEt, bEt, cEt, dEt;
    Button save;
    String Content, corr;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tests);
        Intent intent = getIntent();
        lessonTitle = intent.getStringExtra("lessonTitle");
        lessonTheme = intent.getStringExtra("lessonTheme");
        lessonLink = intent.getStringExtra("lessonLink");

        lessonTitlTextView = (TextView) findViewById(R.id.nameOfLesTextView);
        testTv = (TextView) findViewById(R.id.testTextView);
        lessonTitlTextView.setText("Урок: " + lessonTitle);

        testEt = (EditText) findViewById(R.id.testEditText);
        aEt = (EditText) findViewById(R.id.aEditText);
        bEt = (EditText) findViewById(R.id.bEditText);
        cEt = (EditText) findViewById(R.id.cEditText);
        dEt = (EditText) findViewById(R.id.dEditText);
        save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(this);


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        corr = null;
                        Toast toast = Toast.makeText(AddTestsActivity.this, "Правильна відповідь не вказана", Toast.LENGTH_LONG);
                        toast.show();
                        break;
                    case R.id.aRadioButton:
                        corr = "a";
                        Toast toast1 = Toast.makeText(AddTestsActivity.this, "a is checked", Toast.LENGTH_LONG);  //testing radiobutton's listener
                        toast1.show();
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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                //toDo
                break;
        }
    }
}
