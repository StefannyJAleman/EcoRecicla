package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.retoIntermedio.R;
public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnregitrarse=findViewById(R.id.btnRegistroInicio);
        Button btnLoginInicio=findViewById(R.id.btnLoginInicio);

        btnregitrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paginaRegistro = new Intent(Inicio.this, com.example.reto2.Registro.class);
                startActivity(paginaRegistro);
            }
        });
        btnLoginInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paginaInicio = new Intent(Inicio.this, com.example.reto2.IniciarSesion.class);
                startActivity(paginaInicio);
            }
        });


    }
}