package com.example.vika.course_work;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    public static String username;
    public static final String LOG_TAG = "myLogs";
    TextView userTextView;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userTextView = (TextView) findViewById(R.id.usernameTV);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        if(username.equals("Admin"))
        {
            Intent i = new Intent(this, AdminPanel.class);
            startActivity(i);
        }
        userTextView.setText(username);
        Log.d(LOG_TAG, "username now is " + username);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    protected void onResume() {
        String newUsername = sp.getString("change_username", username);
        userTextView.setText(newUsername);
        Log.d(LOG_TAG, "username now is " + newUsername);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, (R.string.preferences));
        mi.setIntent(new Intent(this, PrefActivity.class));

        MenuItem menuItem = menu.add(0, 2, 0, "Всі курси");
        menuItem.setIntent(new Intent(this, AllCoursesActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
