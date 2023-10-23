package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear botones

        Button btnregitrarse=findViewById(R.id.btnRegistroInicio);
        Button btnLoginInicio=findViewById(R.id.btnLoginInicio);

        btnregitrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paginaRegistro = new Intent(Inicio.this, Registro.class);
                startActivity(paginaRegistro);
            }
        });
        btnLoginInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paginaInicio = new Intent(Inicio.this, IniciarSesion.class);
                startActivity(paginaInicio);
            }
        });


    }
}