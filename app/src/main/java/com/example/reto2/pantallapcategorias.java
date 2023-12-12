package com.example.reto2;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.retoIntermedio.R;

public class pantallapcategorias extends BaseActivityR {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallapcategorias);



    CardView categ_ai = findViewById(R.id.categ_ai);

        categ_ai.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View view) {
            // Aquí colocas el código que quieres ejecutar al hacer clic en el CardView
            // Por ejemplo, puedes abrir una nueva actividad
            Intent intent = new Intent(pantallapcategorias.this, categoria_aceiteindustrial.class);
            startActivity(intent);
        }
    });



        setupBottomNavigation();
    }

    @Override
    protected void handleNavigationItem(int itemId) {
        Intent intent = null;

        if (itemId == R.id.home) {
            Toast.makeText(pantallapcategorias.this, "Home", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.puntos) {
            Toast.makeText(pantallapcategorias.this, "puntos", Toast.LENGTH_LONG).show();
            intent = new Intent(pantallapcategorias.this, PuntosAcopio.class);
        } else if (itemId == R.id.calcular) {
            Toast.makeText(pantallapcategorias.this, "calcular", Toast.LENGTH_LONG).show();
            intent = new Intent(pantallapcategorias.this, RecoCalcular.class);
        } else if (itemId == R.id.perfil) {
            Toast.makeText(pantallapcategorias.this, "perfil", Toast.LENGTH_LONG).show();
            intent = new Intent(pantallapcategorias.this, Perfil.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
