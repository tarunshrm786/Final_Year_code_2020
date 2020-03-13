package com.example.image_fetch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Modules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
    }

    public void customer_page(View view) {
        Intent intent = new Intent(Modules.this, Login.class);
        startActivity(intent);
    }

    public void adminlogin(View view) {
        Intent intent = new Intent(Modules.this, AdminLogin.class);
        startActivity(intent);
    }

    public void parkingown(View view) {
        Intent intent = new Intent(Modules.this, Park.class);
        startActivity(intent);
    }
}
