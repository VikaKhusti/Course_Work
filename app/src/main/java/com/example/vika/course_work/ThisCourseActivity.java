package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThisCourseActivity extends AppCompatActivity {
    TextView selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_course);

        selected = (TextView) findViewById(R.id.textViewSelected);
        Intent intent = getIntent();
        String s = intent.getStringExtra("course_title");
        selected.setText("selected item is " + s);
    }
}
