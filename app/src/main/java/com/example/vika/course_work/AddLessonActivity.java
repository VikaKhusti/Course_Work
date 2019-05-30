package com.example.vika.course_work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class AddLessonActivity extends AppCompatActivity  implements View.OnClickListener {

    TextView titleTV, countTV;
    Button contBtn;
    long rowID;
    EditText les_nameEt, themeEt, linkEt;
    String title, count, lesName, theme, link;
    DBCourses dbCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        titleTV = (TextView) findViewById(R.id.titleTextView);
        countTV = (TextView) findViewById(R.id.countTextView);
        les_nameEt = (EditText) findViewById(R.id.nameOfLesEditText);
        themeEt = (EditText) findViewById(R.id.themeEditText);
        linkEt = (EditText) findViewById(R.id.linkEditText);
        contBtn = (Button) findViewById(R.id.contButton);
        contBtn.setOnClickListener(this);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        count = intent.getStringExtra("count");
        rowID = intent.getLongExtra("rowID", -1);

        titleTV.setText(title);
        countTV.setText(count);

        dbCourses = new DBCourses(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        ContentValues cv;
        SQLiteDatabase database = dbCourses.getWritableDatabase();
        switch (v.getId()){
            case R.id.contButton:
                lesName = les_nameEt.getText().toString();
                theme = themeEt.getText().toString();
                link = linkEt.getText().toString();

                cv = new ContentValues();
                cv.put("course_id", rowID);
                cv.put("lesson_title", lesName);

                long rowLesID = database.insert("lessons", null, cv);
                Log.d(LOG_TAG, "------row inserted with ID = " + rowLesID +
                        "------ content values : " + cv);

                intent = new Intent(this, AddTestsActivity.class);
                intent.putExtra("lessonTitle", lesName);
                intent.putExtra("lessonTheme", theme);
                intent.putExtra("lessonLink", link);
                intent.putExtra("rowID", rowID);
                startActivity(intent);
                Log.d(LOG_TAG, "id = " + rowID);
        }

    }
}
