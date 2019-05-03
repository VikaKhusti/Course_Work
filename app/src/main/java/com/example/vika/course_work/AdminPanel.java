package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        addBtn = (Button) findViewById(R.id.addButton);
        addBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
                Intent intent = new Intent(this, AddCourseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
