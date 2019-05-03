package com.example.vika.course_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText titleET, descrET, countET;
    ImageButton imageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        titleET = (EditText) findViewById(R.id.title);
        descrET = (EditText) findViewById(R.id.description);
        countET = (EditText) findViewById(R.id.count);
        imageBtn = (ImageButton) findViewById(R.id.imageButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                //ToDO
                break;
        }
    }
}
