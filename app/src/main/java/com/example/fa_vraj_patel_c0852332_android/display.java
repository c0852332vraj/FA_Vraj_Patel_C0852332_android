package com.example.fa_vraj_patel_c0852332_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class display extends AppCompatActivity {

    private Database_Handler db;
    private Bundle extras;
    private TextView latitude, longitude, title;
    private Button delete, edit;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        latitude = findViewById(R.id.textViewlatitude);
        longitude = findViewById(R.id.textViewlongitude);
        title = findViewById(R.id.textViewName);
        delete = findViewById(R.id.buttonDelete);
        edit = findViewById(R.id.buttonEdit);

        db = new Database_Handler(this);
        extras = getIntent().getExtras();


        if(extras != null){

            latitude.setText(String.valueOf(extras.getDouble("latitude")));
            longitude.setText(String.valueOf(extras.getDouble("longitude")));
            title.setText(extras.getString("title"));

            Float testNum =  extras.getFloat("id") ;
            num = Math.round(testNum);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(display.this, Edit.class);
                intent.putExtra("id" , num);
                intent.putExtra("title" , title.getText().toString());
                intent.putExtra("latitude" , latitude.getText().toString());
                intent.putExtra("longitude" , longitude.getText().toString());

                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(num != 0){
                    db.deleteRegion(new region(num,latitude.getText().toString()
                            ,longitude.getText().toString(),
                            title.getText().toString()));

                    Intent intent = new Intent( display.this,MainActivity.class );
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}