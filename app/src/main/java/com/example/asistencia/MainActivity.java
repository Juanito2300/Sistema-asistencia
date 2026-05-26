package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnLogin, btnRegistro;
    ImageView imgMostrar;

    DatabaseHelper dbHelper;

    boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistro);
        imgMostrar = findViewById(R.id.imgMostrar);

        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1200);

        btnLogin.startAnimation(animation);
        btnRegistro.startAnimation(animation);

        imgMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passwordVisible){

                    etPassword.setInputType(
                            InputType.TYPE_CLASS_TEXT |
                                    InputType.TYPE_TEXT_VARIATION_PASSWORD
                    );

                    passwordVisible = false;

                } else {

                    etPassword.setInputType(
                            InputType.TYPE_CLASS_TEXT |
                                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    );

                    passwordVisible = true;
                }

                etPassword.setSelection(etPassword.getText().length());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = etUsuario.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(usuario.isEmpty()){

                    etUsuario.setError("Ingrese el usuario");
                    etUsuario.requestFocus();
                    return;
                }

                if(password.isEmpty()){

                    etPassword.setError("Ingrese la contraseña");
                    etPassword.requestFocus();
                    return;
                }

                if(password.length() < 4){

                    etPassword.setError("Mínimo 4 caracteres");
                    etPassword.requestFocus();
                    return;
                }

                boolean acceso = dbHelper.validarLogin(usuario, password);

                if(acceso){

                    Toast.makeText(
                            MainActivity.this,
                            "Bienvenido " + usuario,
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