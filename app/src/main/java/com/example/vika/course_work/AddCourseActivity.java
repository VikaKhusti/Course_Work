package com.example.vika.course_work;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddCourseActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    EditText titleET, descrET, countET;
    Button save;
    DBCourses dbCourses;
    String title, description ,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        titleET = (EditText) findViewById(R.id.titleEditText);
        descrET = (EditText) findViewById(R.id.descriptionEditText);
        countET = (EditText) findViewById(R.id.countEditText);


        save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(this);

        title = titleET.getText().toString();
        description = descrET.getText().toString();
        count = countET.getText().toString();

        dbCourses = new DBCourses(this);
    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbCourses.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()){
            case R.id.saveBtn:
                Log.d(LOG_TAG, "in saveBtn listener");
                Log.d(LOG_TAG, "title: " + title + "desciption: " + description + "count: " + count);
                contentValues.put("title", title);
                contentValues.put("description", description);
                contentValues.put("count", count);
                Log.d(LOG_TAG, "content values are: " + contentValues);

                long rowID = database.insert("courses", null, contentValues);
                Log.d(LOG_TAG, "------row inserted with ID = " + rowID +
                        "------ content values : " + contentValues);

               Intent intent = new Intent(this, AddLessonActivity.class);
               // intent.putExtra("title", title);
               // intent.putExtra("desciption", description);
              //  intent.putExtra("count", count);
               startActivity(intent);
                break;
        }
    }
}
