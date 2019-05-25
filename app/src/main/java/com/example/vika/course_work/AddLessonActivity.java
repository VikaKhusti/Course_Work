package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddLessonActivity extends AppCompatActivity  implements View.OnClickListener {

    TextView titleTV, countTV;
    Button contBtn;
    EditText les_nameEt, themeEt, linkEt;
    String title, count, lesName, theme, link;
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

        titleTV.setText(title);
        countTV.setText(count);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.contButton:
                lesName = les_nameEt.getText().toString();
                theme = themeEt.getText().toString();
                link = linkEt.getText().toString();
                intent = new Intent(this, AddTestsActivity.class);
                intent.putExtra("lessonTitle", lesName);
                intent.putExtra("lessonTheme", theme);
                intent.putExtra("lessonLink", link);
                startActivity(intent);
        }

    }
}
