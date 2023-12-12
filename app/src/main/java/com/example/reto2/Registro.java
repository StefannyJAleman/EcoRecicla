package com.example.reto2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.sleep;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retoIntermedio.R;
import com.example.retoIntermedio.databinding.ActivityPantallapcategoriasBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

public class Registro extends AppCompatActivity {

    private EditText edtNombre, edtDoc, edtEmail, edtContrasena, edtRecontrasena;
    private CheckBox check;
    private Button btnregistro;
    private TextView enviarIniciarSesion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnregistro = findViewById(R.id.btnRegistro);

        enviarIniciarSesion = findViewById(R.id.enviarIniciarSesion);

        setupListeners();
    }

    private void setupListeners() {

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarUsuario()) {
                    User user = crearUsuario();
                    SaveUser(user);
                    Toast.makeText(getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Todos los campos deben estar diligenciados", Toast.LENGTH_LONG).show();
                }
            }
        });

        enviarIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviarPaginaLogin = new Intent(Registro.this, IniciarSesion.class);
                startActivity(enviarPaginaLogin);
            }
        });

                    TextInputLayout nombres = findViewById(R.id.edittextNombre);
                    TextInputLayout doc = findViewById(R.id.edittextDoc);
                    TextInputLayout email = findViewById(R.id.edittextEmail);
                    TextInputLayout contrasena = findViewById(R.id.editTextContraseña);
                    TextInputLayout Recontrasena = findViewById(R.id.editTextConContraseña);

                    edtNombre = Objects.requireNonNull(nombres.getEditText());
                    edtDoc = Objects.requireNonNull(doc.getEditText());
                    edtEmail = Objects.requireNonNull(email.getEditText());
                    edtContrasena = Objects.requireNonNull(contrasena.getEditText());
                    edtRecontrasena = Objects.requireNonNull(Recontrasena.getEditText());

                    check = findViewById(R.id.checkBox2);

                    btnregistro = findViewById(R.id.btnRegistro);

                    Intent Registro = new Intent(getApplicationContext(), ActivityPantallapcategoriasBinding.class);

                    btnregistro.setOnClickListener(new View.OnClickListener() {



                        @Override
                        public void onClick(View view) {


                            if (validarUsuario()) {
                                User user = crearUsuario();
                                SaveUser(user);
                                Toast.makeText(getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_LONG).show();

                                try {
                                    sleep(500);
                                    startActivity(Registro);
                                    finish();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Todos los campos deben estar diligenciados", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
    }

                public boolean validarUsuario(){
                boolean validarDate=true;

                if (edtNombre.getText().toString().isEmpty()){
                    edtNombre.setBackgroundColor(Color.RED);
                    validarDate=false;
                } else {
                    edtNombre.setBackgroundColor(Color.TRANSPARENT);
                }
                if (edtDoc.getText().toString().isEmpty()){
                    edtDoc.setBackgroundColor(Color.RED);
                    validarDate=false;
                }else {
                    edtDoc.setBackgroundColor(Color.TRANSPARENT);
                }
                if (edtEmail.getText().toString().isEmpty()){
                    edtEmail.setBackgroundColor(Color.RED);
                    validarDate=false;
                }else {
                    edtEmail.setBackgroundColor(Color.TRANSPARENT);
                }
                if (edtContrasena.getText().toString().isEmpty()){
                    edtContrasena.setBackgroundColor(Color.RED);
                    validarDate=false;
                }else {
                    edtContrasena.setBackgroundColor(Color.TRANSPARENT);
                }
                if (edtRecontrasena.getText().toString().isEmpty()){
                    edtRecontrasena.setBackgroundColor(Color.RED);
                    validarDate=false;
                }else {
                    edtRecontrasena.setBackgroundColor(Color.TRANSPARENT);
                }
                if (!edtContrasena.getText().toString().equals(edtRecontrasena.getText().toString())){
                    edtContrasena.setBackgroundColor(Color.RED);
                    edtRecontrasena.setBackgroundColor(Color.RED);
                    validarDate=false;
                    Toast.makeText(getApplicationContext(), "La contraseña no coincide", Toast.LENGTH_LONG).show();
                }else {
                    edtContrasena.setBackgroundColor(Color.TRANSPARENT);
                    edtRecontrasena.setBackgroundColor(Color.TRANSPARENT);
                }

                if (!check.isChecked()){
                    check.setBackgroundColor(Color.RED);
                    validarDate=false;
                } else {
                    check.setBackgroundColor(Color.TRANSPARENT);
                }

                return validarDate;

            }


            public User crearUsuario(){
                String NombreUser,ApellidosUser, CedulaUser, CorreoUser, ContrasenaUser;

                NombreUser=edtNombre.getText().toString();
                CedulaUser=edtDoc.getText().toString();
                CorreoUser=edtEmail.getText().toString();
                ContrasenaUser=edtContrasena.getText().toString();

                return new User(NombreUser,CedulaUser,CorreoUser,ContrasenaUser);

            }

    private void SaveUser(User user) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                File fileUser = new File(getFilesDir(), "user.txt");

                try {
                    FileWriter writer = new FileWriter(fileUser, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write(
                            user.getNombres() + "," +
                                    user.getCedula() + "," +
                                    user.getCorreo() + "," +
                                    user.getContrasena()
                    );

                    bufferedWriter.newLine();
                    bufferedWriter.close();
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
        }

class User {
    private String etNombres;
    private String etCedula;
    private String etCorreo;
    private String etContrasena;

    public User(String etNombres, String etCedula, String etCorreo, String etContrasena) {
        this.etNombres = etNombres;
        this.etCedula = etCedula;
        this.etCorreo = etCorreo;
        this.etContrasena = etContrasena;
    }

    public String getNombres() {
        return etNombres;
    }

    public String getCedula() {
        return etCedula;
    }

    public String getCorreo() {
        return etCorreo;
    }

    public String getContrasena() {
        return etContrasena;
    }
}



