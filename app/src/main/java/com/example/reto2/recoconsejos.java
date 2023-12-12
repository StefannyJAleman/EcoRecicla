package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.retoIntermedio.R;

public class recoconsejos extends BaseActivityR {

    ImageView retroceder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoconsejos);

        setupBottomNavigation();

        retroceder=findViewById(R.id.Retroceder);

        retroceder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent pantalla_consejos=new Intent(getApplicationContext(), categoria_aceiteindustrial.class);
                startActivity(pantalla_consejos);
            }
        });


    }
}

