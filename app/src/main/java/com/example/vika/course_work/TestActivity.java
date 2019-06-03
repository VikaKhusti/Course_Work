package com.example.vika.course_work;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import static com.example.vika.course_work.UserActivity.LOG_TAG;


public class TestActivity extends AppCompatActivity {

    String title, theme, link, test;
    TextView lesson_info;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        lesson_info = (TextView) findViewById(R.id.lessonInfoTextView) ;
        webView = findViewById(R.id.webView);
        // включаем поддержку JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        webView.loadUrl("https://www.youtube.com/watch?v=G0FhItCbvvI");

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        theme = intent.getStringExtra("theme");
        link = intent.getStringExtra("link");
        test = intent.getStringExtra("test");
        String info = "Навза: " + title + "\n"  +
                 "Тема: " + theme + "\n" ;
        lesson_info.setText(info);


        Log.d(LOG_TAG, "intent values = " + title +
                ", " + theme +
                ", " + link +
                ", " + test);


    }
}
