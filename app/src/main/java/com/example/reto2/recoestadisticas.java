package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.retoIntermedio.R;

public class recoestadisticas extends BaseActivityR {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoestadisticas);

        setupBottomNavigation();
    }
}