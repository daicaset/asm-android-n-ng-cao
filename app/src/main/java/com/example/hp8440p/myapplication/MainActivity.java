package com.example.hp8440p.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hp8440p.myapplication.Course.Course_Activity;
import com.example.hp8440p.myapplication.Map.MapsActivity;
import com.example.hp8440p.myapplication.News.NewsActivity;


public class MainActivity extends AppCompatActivity {
ImageView map,news,course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = findViewById(R.id.map);
        news = findViewById(R.id.News);

        course = findViewById(R.id.course);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(MainActivity.this,MapsActivity.class);
              startActivity(i);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Course_Activity.class);
                startActivity(i);
            }
        });
    }
}
