package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {
    Button addCourseBtn, allCoursesBtn;
    String course_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        addCourseBtn = (Button) findViewById(R.id.addButton);
        allCoursesBtn = (Button) findViewById(R.id.allCoursesBtn);
        addCourseBtn.setOnClickListener(this);
        allCoursesBtn.setOnClickListener(this);


    }


        @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.addButton:
                intent = new Intent(this, AddCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.allCoursesBtn:
                intent = new Intent(this, AllCoursesActivity.class);
                startActivity(intent);
                break;
        }
    }


}
