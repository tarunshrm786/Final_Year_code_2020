package com.example.image_fetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.WriterException;

import java.util.HashMap;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Token extends AppCompatActivity {

    String TAG="GenerateQrCode";
    EditText accountno;
    ImageView qrimg;
    Button start;
    String inputvalue;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    private ProgressBar progressBar;
    EditText name,mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        name = (EditText)findViewById(R.id.name);
        mobileno = (EditText)findViewById(R.id.mobileno);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        qrimg = (ImageView)findViewById(R.id.qrcode);
        accountno = (EditText)findViewById(R.id.accountno);
        start = (Button)findViewById(R.id.createbtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                HashMap<String,Object> map = new HashMap<>();
                map.put("customername",name.getText().toString());
                map.put("mobilenumber",mobileno.getText().toString());
                map.put("accountnumber",accountno.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("CustomerPayments").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("jfbvkj", "onComplete: ");

                                progressBar.setVisibility(View.GONE);
//                                Intent intent = new Intent(Token.this, Token.class);
//                                startActivity(intent);

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

                inputvalue=accountno.getText().toString().trim();

                if (inputvalue.length()>0){

                    WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();

                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;

                    int smallerdimension=width<height ? width:height;
                    smallerdimension=smallerdimension*3/4;

                    qrgEncoder=new QRGEncoder(inputvalue, null, QRGContents.Type.TEXT,smallerdimension);

                    try{

                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrimg.setImageBitmap(bitmap);

                    }
                    catch (WriterException e){
                        Log.v(TAG, accountno.toString());
                    }
                }else{

                    accountno.setError("Required");
                }
            }
        });
    }

    public void homepage(View view) {
                    Intent intent = new Intent(Token.this, Home_Page.class);
                    startActivity(intent);
    }
}
