package com.example.reto2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.retoIntermedio.R;




public class Perfil extends BaseActivityR {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        setupBottomNavigation();


       ImageButton imageButtonestadi=findViewById(R.id.imageButtonestadi);
        Button btncerrarSesion=findViewById(R.id.btncerrarSesion);

        imageButtonestadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaestadisticas = new Intent(Perfil.this, recoestadisticas.class);
                startActivity(pantallaestadisticas);
            }
        });
        btncerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cerrarsesion = new Intent(Perfil.this, Inicio.class);
                startActivity(cerrarsesion);
            }
        });


    }
}
