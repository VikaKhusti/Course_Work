package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class TestActivity extends AppCompatActivity {

    String title, theme, link, test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        theme = intent.getStringExtra("theme");
        link = intent.getStringExtra("link");
        test = intent.getStringExtra("test");

        Log.d(LOG_TAG, "intent values = " + title +
                ", " + theme +
                ", " + link +
                ", " + test);


    }
}
