package com.example.vika.course_work;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    EditText titleET, descET, countET;
    ImageButton saveBtn;
    String title, description, count;
    DBCourses dbCourses;
    long rowID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        titleET = (EditText) findViewById(R.id.titleEditText);
        descET = (EditText) findViewById(R.id.descEditText);
        countET = (EditText) findViewById(R.id.countEditText);
        saveBtn = (ImageButton) findViewById(R.id.saveImageButton);
        saveBtn.setOnClickListener(this);



        dbCourses = new DBCourses(this);


    }



    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbCourses.getWritableDatabase();
        ContentValues cv = new ContentValues();

        switch (v.getId()){
            case R.id.saveImageButton:
                title = titleET.getText().toString();
                description = descET.getText().toString();
                count = countET.getText().toString();

                cv.put("title", title);
                cv.put("description", description);
                cv.put("count", count);

                if (title.equals(""))
                {
                    Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();
                    break;
                }
                else{
                    rowID = database.insert("courses", null, cv);
                    Log.d(LOG_TAG, "------row inserted with ID = " + rowID +
                            "------ content values : " + cv);

                    Intent intent = new Intent(this, AddLessonActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("count", count);
                    intent.putExtra("rowID", rowID);
                    startActivity(intent);
                    Log.d(LOG_TAG, "id = " + rowID);
                }
            break;
        }
    }
}
