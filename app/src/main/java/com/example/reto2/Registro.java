package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.retoIntermedio.R;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnregitro=findViewById(R.id.btnRegistro);
        TextView enviaraLogin=findViewById(R.id.enviarIniciarSesion);

        btnregitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviarMenu = new Intent(Registro.this, menuscrollcomp.class);
                startActivity(enviarMenu);

            }
        });

        enviaraLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviarPaginaLogin = new Intent(Registro.this,IniciarSesion.class);
                startActivity(enviarPaginaLogin);
            }
        });
        }
    }
