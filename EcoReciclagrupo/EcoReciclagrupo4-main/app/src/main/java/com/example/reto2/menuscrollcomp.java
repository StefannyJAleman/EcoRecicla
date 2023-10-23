package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class menuscrollcomp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscrollcomp);

        ImageView iconoPerfil = findViewById(R.id.iconoPerfilMenu);

        SharedPreferences sharedPreferences = getSharedPreferences("flujoCompletado", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean flujoCompletado = sharedPreferences.getBoolean("flujoCompletado", false);


            if (flujoCompletado) {
                startActivity(new Intent(menuscrollcomp.this, bienvenida.class));
                editor.putBoolean("flujoCompletado", false);
                editor.apply();
            }

            iconoPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent paginaPerfil = new Intent(menuscrollcomp.this, PerfilUsuario.class);
                    startActivity(paginaPerfil);
                }
            });
    }
}