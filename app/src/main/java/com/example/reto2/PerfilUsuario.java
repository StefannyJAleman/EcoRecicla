package com.example.reto2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        ImageView btnHome=findViewById(R.id.Home);
        ImageView btncerrarSesion=findViewById(R.id.cerrarSesion);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent enviarHome = new Intent(PerfilUsuario.this, Menu.class);
                startActivity(enviarHome);
            }

        });
        btncerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent enviarcerrarSesion = new Intent(PerfilUsuario.this, Inicio.class);
                startActivity(enviarcerrarSesion);
            }
        });

    }
}