package com.example.image_fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ParkingOwnerHomePage extends AppCompatActivity {

   // https://theengineerscafe.com/save-and-retrieve-data-firebase-android/

private EditText user_name,user_mobile,user_address,user_dob,parking_name,parking_space,parking_charge;
    private Button profile_submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_owner_home_page);

        user_name=(EditText)findViewById(R.id.User_name);
        user_mobile=(EditText)findViewById(R.id.User_Mobile);
        user_address=(EditText) findViewById(R.id.User_address);
        user_dob=(EditText)findViewById(R.id.User_DOB);
        parking_name=(EditText)findViewById(R.id.P_Name);
        parking_space=(EditText)findViewById(R.id.Parking_space);
        parking_charge=(EditText)findViewById(R.id.P_charge);
        profile_submitbtn=(Button) findViewById(R.id.Profile_submit_btn);

        profile_submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object>map=new HashMap<>();
                map.put("user_name",user_name.getText().toString());
                map.put("user_mobile",user_mobile.getText().toString());
                map.put("user_address",user_address.getText().toString());
                map.put("user_dob",user_dob.getText().toString());
                map.put("parking_name",parking_name.getText().toString());
                map.put("parking_space",parking_space.getText().toString());
                map.put("parking_charge",parking_charge.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("ParkingOwnerDetails").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("jfbvkj","onComplete: ");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("jfbvkj","OnFailure: "+e.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("jfbvkj","onSuccess: ");
                        Toast.makeText(getApplicationContext(), "Profile Save Sucessfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ParkingOwnerHomePage.this,ParkingOwnerHomePage.class));
                    }
                });


            }
        });

    }
}