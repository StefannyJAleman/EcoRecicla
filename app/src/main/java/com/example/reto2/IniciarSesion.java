package com.example.reto2;

import android.app.Dialog;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.retoIntermedio.R;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class IniciarSesion extends AppCompatActivity {
    Dialog myDialog;
    private ArrayList<User> users;
    EditText ext_name, ext_password;
    TextView registrarte;
    Button btnLogin;

    private Intent paginaEnviarRegistro;
    private Intent logi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);



        users = new ArrayList<>();

        Button btnregitrarse = findViewById(R.id.btnLogin);

        TextView enviarRegistro = findViewById(R.id.enviarRegistro);

        TextInputLayout name = findViewById(R.id.ext_name);
        TextInputLayout password = findViewById(R.id.ext_password);

        ext_name = name.getEditText();
        ext_password = password.getEditText();

        btnLogin = findViewById(R.id.btnLogin);

        paginaEnviarRegistro = new Intent(getApplicationContext(), Registro.class);

        btnregitrarse.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                String cedula = ext_name.getText().toString();
                String contrasena = ext_password.getText().toString();

                if (cedula.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese cédula y contraseña", Toast.LENGTH_LONG).show();
                } else {
                    Intent paginaEnviarRegistro = new Intent(IniciarSesion.this, Registro.class);
                    startActivity(paginaEnviarRegistro);
                }
            }
        });

        logi = new Intent(getApplicationContext(), pantallapcategorias.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cedula = ext_name.getText().toString();
                String contrasena = ext_password.getText().toString();

                if (cedula.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese cédula y contraseña", Toast.LENGTH_LONG).show();
                } else {
                    File fileUser = new File(getFilesDir(), "user.txt");
                    ArrayList<User> users = listUser(fileUser);

                    if (users.isEmpty()) {
                        // No hay usuarios registrados
                        Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                    } else {
                        // Validar las credenciales solo si hay usuarios registrados
                        boolean state = false;

                        for (User i : users) {
                            if (i.getCedula().equals(cedula) || i.getNombres().equals(cedula)) {
                                state = true;
                                if (i.getContrasena().equals(contrasena)) {
                                    // La cédula o el nombre de usuario y la contraseña coinciden, procede con la lógica de inicio de sesión
                                    String nombreUsuario = i.getNombres();
                                    logi.putExtra("etCedula", i.getCedula());
                                    logi.putExtra("nombreUsuario", nombreUsuario);
                                    logi.putExtra("username", nombreUsuario);
                                    startActivity(logi);
                                    break;
                                } else {
                                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        if (!state) {
                            Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                        }
                    }
                }
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
                public ArrayList<User> listUser (File data){
                    ArrayList<User> list = new ArrayList<>();

                    try {
                        FileReader reader = new FileReader(data);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String user;


                        while ((user = bufferedReader.readLine()) != null) {
                            String[] userData = user.split(",");
                            String etNombres = userData[0];
                            String etCedula = userData[1];
                            String etCorreo = userData[2];
                            String etContraseña = userData[3];

                            User userObject = new User(etNombres, etCedula, etCorreo, etContraseña);
                            list.add(userObject);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return list;
                }
            }