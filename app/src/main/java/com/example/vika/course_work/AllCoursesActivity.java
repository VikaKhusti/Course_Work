package com.example.vika.course_work;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class AllCoursesActivity extends AppCompatActivity implements View.OnClickListener {

    DBCourses db;
    ArrayList<String> stringArrayList;
    ListView lvMain;
    Button look;
    String selectedItem;
    String index;
    int Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
        lvMain = (ListView) findViewById(R.id.lvMain);
        look = (Button) findViewById(R.id.lookBtn);
        look.setOnClickListener(this);

        db = new DBCourses(this);
        SQLiteDatabase database = db.getWritableDatabase();
        stringArrayList = new ArrayList<String>();

        Cursor cursor = database.query("courses", null, null,
                null, null, null,
                null, null);
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("id");
            int titleColIndex = cursor.getColumnIndex("title");
            int descriptionColIndex = cursor.getColumnIndex("description");
            int countColIndex = cursor.getColumnIndex("count");


            do {
                Log.d(LOG_TAG,
                        "ID = " + cursor.getInt(idColIndex) + ", title = "
                                + cursor.getString(titleColIndex) + ", description = "
                                + cursor.getString(descriptionColIndex) + ", count = "
                                + cursor.getString(countColIndex)
                );
                stringArrayList.add(cursor.getString(titleColIndex));
            }

            while (cursor.moveToNext());

        }

        cursor.close();

        for(int i = 0; i < stringArrayList.size(); i++){
            if(stringArrayList.get(i).equals("")){
                stringArrayList.remove(i);
            }
        }
        Log.d(LOG_TAG, "stringArrayList now is : " + stringArrayList);
        String[] coursesArray = stringArrayList.toArray(new String[stringArrayList.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, coursesArray);
        lvMain.setAdapter(adapter);


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                selectedItem = (String) parent.getItemAtPosition(position);
                Position = position;
                Log.d(LOG_TAG, "selected item is " + selectedItem);
                Log.d(LOG_TAG, "pos is " + position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.lookBtn:
                int pos = Integer.valueOf(Position);
                pos++;
                Log.d(LOG_TAG, "index is" + index);
                Log.d(LOG_TAG, "pos now is" + pos);
                intent = new Intent(this, ThisCourseActivity.class);
                intent.putExtra("course_title", selectedItem);
                intent.putExtra("index", pos);
                intent.putStringArrayListExtra("stringArrayList", stringArrayList);
                startActivity(intent);
        }
    }
}