package com.example.reto2;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.retoIntermedio.R;

public class categoria_aceiteindustrial extends BaseActivityR {
    ImageView retroceder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_aceiteindustrial);

        setupBottomNavigation();

        CardView reco_consejos = findViewById(R.id.reco_consejos);
        retroceder=findViewById(R.id.Retroceder);

        reco_consejos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent intent = new Intent(categoria_aceiteindustrial.this, recoconsejos.class);
                startActivity(intent);

            }
        });
        retroceder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent pantalla_cateai =new Intent(getApplicationContext(), pantallapcategorias.class);
                startActivity(pantalla_cateai);
            }
        });
    }
}
