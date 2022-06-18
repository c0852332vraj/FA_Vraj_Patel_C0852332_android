package com.example.fa_vraj_patel_c0852332_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class insert extends AppCompatActivity {


    private Database_Handler db;
    private TextView latitude, longitude;
    private EditText title;
    private Button save;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        latitude = findViewById(R.id.latitudetext);
        longitude = findViewById(R.id.longitudetext);
        title = findViewById(R.id.titleText);
        save = findViewById(R.id.buttonSave);

        db = new Database_Handler(this);
        extras = getIntent().getExtras();

        if(extras != null){

            latitude.setText(String.valueOf(extras.getDouble("latitude")));
            longitude.setText(String.valueOf(extras.getDouble("longitude")));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.addRegion(new region(latitude.getText().toString()
                        ,longitude.getText().toString(),
                        title.getText().toString()));

                Intent intent = new Intent( insert.this,MainActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }
}