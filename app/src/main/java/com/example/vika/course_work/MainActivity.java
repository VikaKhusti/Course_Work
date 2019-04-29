package com.example.vika.course_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button log_in, sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log_in =  (Button) findViewById(R.id.logInBtn);
        sign_up = (Button) findViewById(R.id.signUpBtn);
    }
}
