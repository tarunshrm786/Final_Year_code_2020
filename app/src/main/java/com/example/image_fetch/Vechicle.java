package com.example.image_fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Vechicle extends AppCompatActivity {

    EditText vechciletype,vechcilenumber;
    Button btnregistervechcile;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechicle);

        vechcilenumber = (EditText)findViewById(R.id.vechcilenumber);
        vechciletype = (EditText)findViewById(R.id.vechciletype);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        btnregistervechcile = (Button)findViewById(R.id.btn_vechcileregister);


        btnregistervechcile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                HashMap<String,Object> map = new HashMap<>();
                map.put("vechciletype",vechciletype.getText().toString());
                map.put("vechcilenumber",vechcilenumber.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("VechcileRegisteration").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("jfbvkj", "onComplete: ");

                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(Vechicle.this, Token.class);
                                startActivity(intent);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("jfbvkj", "onFailure: "+e.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("jfbvkj", "onSuccess: ");
                    }
                });

            }
        });

    }
}
