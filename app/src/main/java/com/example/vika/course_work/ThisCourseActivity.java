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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.vika.course_work.UserActivity.LOG_TAG;

public class ThisCourseActivity extends AppCompatActivity implements View.OnClickListener {
    TextView selected, desTv, countTv;
    Button start;
    DBCourses dbCourses;
    DBLessons dbLessons;
    String Title, Description, selectedItem;
    int Count, Position;
    ArrayList<String> lessonsArrayList;
    ArrayList<String> lessonArrayList;
    ListView lessonsLv;
    String[] data = new String[3];
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_course);

        selected = (TextView) findViewById(R.id.textViewSelected);
        desTv = (TextView) findViewById(R.id.DestextView);
        countTv = (TextView) findViewById(R.id.CounttextView);
        start = (Button) findViewById(R.id.startBtn);
        start.setOnClickListener(this);

        lessonsLv = (ListView) findViewById(R.id.lvLessons);
        Intent intent = getIntent();
        Title = intent.getStringExtra("course_title");
        dbCourses = new DBCourses(this);
        dbLessons = new DBLessons(this);
        selected.setText(Title);
        ID = intent.getIntExtra("index", -1);

        data = readDB(Title);
        desTv.setText(data[0]);
        countTv.setText(data[1]);
        ID = Integer.valueOf(data[2]);
        Log.d(LOG_TAG, "ID of course = " + ID);

        lessonsArrayList = new ArrayList<String>();
        lessonArrayList = new ArrayList<String>();
        SQLiteDatabase database = dbLessons.getReadableDatabase();

        Log.d(LOG_TAG, "----Rows in lessons---");
        Cursor cursor = database.query("lessons", null, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            int idColIndex = cursor.getColumnIndex("id");
            int course_idColIndex = cursor.getColumnIndex("course_id");
            int titleColIndex = cursor.getColumnIndex("title");
            int themeColIndex = cursor.getColumnIndex("theme");
            int linkColIndex = cursor.getColumnIndex("link");
            int testColIndex = cursor.getColumnIndex("test");

            do{
                if(ID==cursor.getInt(course_idColIndex)){
                    lessonArrayList.add(String.valueOf(cursor.getInt(idColIndex)));
                    lessonArrayList.add(String.valueOf(cursor.getInt(course_idColIndex)));
                    lessonArrayList.add(cursor.getString(titleColIndex));
                    lessonArrayList.add(cursor.getString(themeColIndex));
                    lessonArrayList.add(cursor.getString(linkColIndex));
                    lessonArrayList.add(cursor.getString(testColIndex));

                    lessonsArrayList.add(cursor.getString(titleColIndex));
                }

            //    Log.d(LOG_TAG,
             //           "ID = " + cursor.getInt(idColIndex) +
              //                  ", course_id = " + cursor.getInt(course_idColIndex) +
               //                 ", title = " + cursor.getString(titleColIndex) +
               //                 ", theme =  " + cursor.getString(themeColIndex) +
               //                 ", link = " + cursor.getString(linkColIndex) +
              //                  ", test = " + cursor.getString(testColIndex)
              //  );
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        Log.d(LOG_TAG, "Lesson ArrL IS : " + lessonArrayList);
        String[] lessons = lessonsArrayList.toArray(new String[lessonsArrayList.size()]);
        Log.d(LOG_TAG, "ARRAY IS : " + Arrays.toString(lessons));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lessons);
        lessonsLv.setAdapter(adapter);


        lessonsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


    String[] readDB(String title)
    {
        SQLiteDatabase database = dbCourses.getReadableDatabase();

        Log.d(LOG_TAG, "-----Rows in courses------");
        Cursor cursor = database.query("courses", null, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            int idColIndex = cursor.getColumnIndex("id");
            int titleColIndex = cursor.getColumnIndex("title");
            int descriptionColIndex = cursor.getColumnIndex("description");
            int countColIndex = cursor.getColumnIndex("count");

                do{
                    Log.d(LOG_TAG,
                            "ID = " + cursor.getInt(idColIndex) +
                            ", title = " + cursor.getString(titleColIndex) +
                            ", description = " + cursor.getString(descriptionColIndex) +
                            ", count = " + cursor.getInt(countColIndex)
                    );

                        if(Title.equals(cursor.getString(titleColIndex))){
                            Description =  cursor.getString(descriptionColIndex);
                            data[0] = Description;
                            Count = cursor.getInt(countColIndex);
                            data[1] = String.valueOf(Count);
                            ID = cursor.getInt(idColIndex);
                            data[2] = String.valueOf(cursor.getInt(idColIndex));
                        }
                }
                while (cursor.moveToNext());

        }
        else {
            Log.d(LOG_TAG, "------ 0 rows ------");

        }
        cursor.close();
        return data;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.startBtn:
                intent= new Intent(this, TestActivity.class);
                for(int i = 0; i < lessonArrayList.size(); i++)
                {
                    if(lessonArrayList.get(i).equals(selectedItem))
                    {
                        intent.putExtra("title", lessonArrayList.get(i));
                        intent.putExtra("theme", lessonArrayList.get(i + 1));
                        intent.putExtra("link", lessonArrayList.get(i + 2));
                        intent.putExtra("test", lessonArrayList.get(i + 3));
                    }
                }
                startActivity(intent);
                break;

        }
    }
}
