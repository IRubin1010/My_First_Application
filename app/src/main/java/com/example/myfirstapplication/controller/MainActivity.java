package com.example.myfirstapplication.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfirstapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configFirstButton();
        configSecondButton();
        configthirdButton();
        configforthButton();
    }

    private void configforthButton() {
        Button gps = findViewById(R.id.GPSbtn);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GetCurrentLocation.class));
            }
        });
    }


    private void configFirstButton() {
        Button nextBtn = findViewById(R.id.AddParcelActivityBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddPackage.class));
            }
        });
    }
    private void configSecondButton() {
        Button nextBtn = findViewById(R.id.HistoryParcelActivityBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, getPackageByEmail.class));
            }
        });
    }
    private void configthirdButton() {
        Button nextBtn = findViewById(R.id.Addbtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddPerson.class));
            }
        });
    }
}
