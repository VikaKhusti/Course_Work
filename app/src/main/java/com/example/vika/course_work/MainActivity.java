package com.example.vika.course_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button log_in, sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log_in =  (Button) findViewById(R.id.logInBtn);
        sign_up = (Button) findViewById(R.id.signUpBtn);

        log_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.logInBtn:
                intent = new Intent(this, LogInActivity.class);
                startActivity(intent);
                break;
            case R.id.signUpBtn:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
