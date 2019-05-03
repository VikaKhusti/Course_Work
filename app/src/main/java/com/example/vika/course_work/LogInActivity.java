package com.example.vika.course_work;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    public static String USERNAME;

    Button login;
    EditText usernameET, loginET, passwordET;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = (Button) findViewById(R.id.log_in);
        login.setOnClickListener(this);
        loginET = (EditText) findViewById(R.id.loginEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);

        dbHelper = new DBHelper(this);
    }



    @Override
    public void onClick(View v) {

        String login = loginET.getText().toString();
        String password = passwordET.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.log_in:

                Log.d(LOG_TAG, "------ Rows in mytable: ------");
                Cursor cursor = database.query("users", null, null,
                        null, null, null,
                        null, null);

                if (cursor.moveToFirst())
                {
                    int idColIndex = cursor.getColumnIndex("id");
                    int usernameColIndex = cursor.getColumnIndex("username");
                    int loginColIndex = cursor.getColumnIndex("login");
                    int passwordColIndex = cursor.getColumnIndex("password");


                        do {
                            Log.d(LOG_TAG,
                                    "ID = " + cursor.getInt(idColIndex) + ", username = "
                                            + cursor.getString(usernameColIndex) + ", login = "
                                            + cursor.getString(loginColIndex) + ", password = "
                                            + cursor.getString(passwordColIndex)
                            );

                                    if (login.equals(cursor.getString(loginColIndex))) {
                                        if (password.equals(cursor.getString(passwordColIndex))) {
                                            String username = cursor.getString(usernameColIndex);
                                            Intent intent = new Intent(this, UserActivity.class);
                                            intent.putExtra("username", username);
                                            startActivity(intent);

                                    } else {
                                        Toast toast = Toast.makeText(LogInActivity.this,
                                                "Логін або пароль не правильний", Toast.LENGTH_LONG);
                                        toast.show();

                                    }

                                }



                        }
                        while (cursor.moveToNext());
                    }
                    else {
                        Log.d(LOG_TAG, "------ 0 rows ------");

                    }
                    cursor.close();
                    break;
                }
        }
    }
