package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.retoIntermedio.R;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        Button btnregitrarse=findViewById(R.id.btnLogin);

        TextView enviarRegistro = findViewById(R.id.enviarRegistro);



        btnregitrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paginaMenu = new Intent(IniciarSesion.this, menuscrollcomp.class);
                startActivity(paginaMenu);

            }
        });

        enviarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paginaEnviarRegistro = new Intent(IniciarSesion.this, Registro.class);
                startActivity(paginaEnviarRegistro);


            }
        });

    }
}