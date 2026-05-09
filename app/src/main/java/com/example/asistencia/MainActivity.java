package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnLogin, btnRegistro;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = etUsuario.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(usuario.isEmpty() || password.isEmpty()){
                    Toast.makeText(
                            MainActivity.this,
                            "Ingrese usuario y contraseña",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                boolean acceso = dbHelper.validarLogin(usuario, password);

                if(acceso){

                    Toast.makeText(
                            MainActivity.this,
                            "Bienvenido",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(
                            MainActivity.this,
                            MenuActivity.class
                    );


                    intent.putExtra("usuario", usuario);
                    startActivity(intent);

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        MainActivity.this,
                        registro.class
                );



                startActivity(intent);
            }
        });
    }
}