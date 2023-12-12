package com.example.reto2;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.retoIntermedio.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivityR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.barnav);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                handleNavigationItem(item.getItemId());
                return true;
            }
        });
    }

    protected void handleNavigationItem(int itemId) {

        Intent intent = null;

        if (itemId == R.id.home) {
            Toast.makeText(BaseActivityR.this, "Home", Toast.LENGTH_LONG).show();
            intent = new Intent(BaseActivityR.this, pantallapcategorias.class);
        } else if (itemId == R.id.puntos) {
            Toast.makeText(BaseActivityR.this, "puntos", Toast.LENGTH_LONG).show();
            intent = new Intent(BaseActivityR.this, PuntosAcopio.class);
        } else if (itemId == R.id.calcular) {
            Toast.makeText(BaseActivityR.this, "calcular", Toast.LENGTH_LONG).show();
            intent = new Intent(BaseActivityR.this, RecoCalcular.class);
        } else if (itemId == R.id.perfil) {
            Toast.makeText(BaseActivityR.this, "perfil", Toast.LENGTH_LONG).show();
            intent = new Intent(BaseActivityR.this, Perfil.class);
        }

        if (intent != null) {
            startActivity(intent);


        }
    }
}