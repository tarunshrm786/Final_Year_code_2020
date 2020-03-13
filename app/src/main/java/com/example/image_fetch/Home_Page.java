package com.example.image_fetch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
    }

    public void Book(View view) {
        Intent intent = new Intent(Home_Page.this, Vechicle.class);
        startActivity(intent);
    }
}
