package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AddLessonActivity extends AppCompatActivity {

    TextView titleTV, descrTV, countTV;
    String title, description ,count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        titleTV = (TextView) findViewById(R.id.titleTV);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
    }
}
