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

import java.util.ArrayList;

import static com.example.vika.course_work.UserActivity.LOG_TAG;
import static com.example.vika.course_work.UserActivity.username;

public class AllCoursesActivity extends AppCompatActivity implements View.OnClickListener {

    DBCourses db;
    ArrayList<String> stringArrayList;
    ArrayList<String> dataFromDB;
    ListView lvMain;
    Button look, addLes;
    String selectedItem;
    int Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
        lvMain = (ListView) findViewById(R.id.lvLessons);
        look = (Button) findViewById(R.id.lookBtn);
        addLes = (Button) findViewById(R.id.addLessBtn);
        look.setOnClickListener(this);
        addLes.setOnClickListener(this);

        db = new DBCourses(this);
        SQLiteDatabase database = db.getWritableDatabase();
        stringArrayList = new ArrayList<String>();
        dataFromDB = new ArrayList<String>();

        if(username.equals("Admin")){
            addLes.setVisibility(View.VISIBLE);
        }


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
                dataFromDB.add(String.valueOf(cursor.getInt(idColIndex)));
                dataFromDB.add(cursor.getString(titleColIndex));
                dataFromDB.add(cursor.getString(descriptionColIndex));
                dataFromDB.add(cursor.getString(countColIndex));
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
                intent = new Intent(this, ThisCourseActivity.class);
                intent.putExtra("course_title", selectedItem);
                startActivity(intent);
                break;

            case R.id.addLessBtn:
                String id = "", title = "", des = "", count= "";
                Log.d(LOG_TAG, "data from db = " + dataFromDB);
                for(int i= 0; i<dataFromDB.size(); i++){
                    if(selectedItem.equals(dataFromDB.get(i)))
                    {
                        id = dataFromDB.get(i-1);
                        title = dataFromDB.get(i);
                        des = dataFromDB.get(i+1);
                        count = dataFromDB.get(i+2);
                    }
                }
                intent = new Intent(this, AddLessonActivity.class);
                intent.putExtra("title" , title);
                intent.putExtra("count", count);
                intent.putExtra("rowID", Long.valueOf(id));
                startActivity(intent);
                break;
        }
    }
}