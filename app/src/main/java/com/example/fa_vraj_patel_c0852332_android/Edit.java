package com.example.fa_vraj_patel_c0852332_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    private Database_Handler db;
    private Bundle extras;
    private EditText title;
    private Button editButton;
    private String latitude ="" ;
    private String longitude ="" ;
    private int testNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.nameEditpage);
        editButton = findViewById(R.id.buttonEditpage) ;
        db = new Database_Handler(this);
        extras = getIntent().getExtras();

        if(extras != null){

            title.setText(extras.getString("title"));
            latitude = extras.getString("latitude");
            longitude = extras.getString("longitude");
            testNum =  extras.getInt("id") ;

        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(testNum != 0 && !latitude.isEmpty() && !longitude.isEmpty()){
                    db.editRegion(new region(testNum,latitude   ,longitude ,
                            title.getText().toString()));

                    Intent intent = new Intent( Edit.this,MainActivity.class );
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}