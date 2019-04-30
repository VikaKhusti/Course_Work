package com.example.vika.course_work;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button submit;
    EditText usernameET, loginET, passwordET;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        submit = (Button) findViewById(R.id.log_in);
        submit.setOnClickListener(this);
        usernameET = (EditText) findViewById(R.id.usernameEditText);
        loginET = (EditText) findViewById(R.id.loginEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        String username = usernameET.getText().toString();
        String login = loginET.getText().toString();
        String password = passwordET.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId())
        {
            case R.id.log_in:
                contentValues.put("username", username);
                contentValues.put("login", login);
                contentValues.put("password", password);
                long rowID = database.insert("users", null, contentValues);
                Log.d(LOG_TAG, "------row inserted with ID = " + rowID +
                        "------ content values : " + contentValues);

                Intent intent = new Intent(this, IndexActivity.class);
                startActivity(intent);
                break;
        }
        dbHelper.close();
    }
}
