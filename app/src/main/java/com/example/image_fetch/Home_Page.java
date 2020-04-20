package com.example.image_fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_Page extends AppCompatActivity {

    //add Firebase Database stuff
    Button logout;
    private FirebaseAuth auth;
    private  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

//        logout = (Button) findViewById(R.id.btn_logout);

        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser() == null) {
//            finish();
//            startActivity(new Intent(this, Login.class));
//        }
//
//        FirebaseUser user = auth.getCurrentUser();
    }

//    public void logout(View view) {
//        auth.signOut();
//        Intent intent = new Intent(Home_Page.this, Login.class);
//        startActivity(intent);
//        finish();
//    }

    public void Book(View view) {
        Intent intent = new Intent(Home_Page.this, Vechicle.class);
        startActivity(intent);
//        finish();
    }
}