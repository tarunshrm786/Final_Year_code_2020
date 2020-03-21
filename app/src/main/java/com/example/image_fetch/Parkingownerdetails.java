package com.example.image_fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parkingownerdetails extends AppCompatActivity {

    TextView t1,t2;
    DatabaseReference ref;
    Button btnloaddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingownerdetails);

        t1 =(TextView) findViewById(R.id.textView1);
        t2 =(TextView) findViewById(R.id.textView2);

        btnloaddata = (Button)findViewById(R.id.btn_loaddata);

        btnloaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("Post");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String Author = dataSnapshot.child("author").getValue().toString();
                        String Title = dataSnapshot.child("title").getValue().toString();

                        t1.setText(Author);
                        t2.setText(Title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
