package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class registro extends AppCompatActivity {

    EditText etNuevoUsuario, etCorreo, etTelefono, etNuevaPassword, etConfirmarPassword;
    Button btnRegistrarUsuario;
    TextView tvVolverLogin;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        dbHelper = new DatabaseHelper(this);

        etNuevoUsuario = findViewById(R.id.etNuevoUsuario);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etNuevaPassword = findViewById(R.id.etNuevaPassword);
        etConfirmarPassword = findViewById(R.id.etConfirmarPassword);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        tvVolverLogin = findViewById(R.id.tvVolverLogin);

        btnRegistrarUsuario.setOnClickListener(view -> {

            String usuario = etNuevoUsuario.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String password = etNuevaPassword.getText().toString().trim();
            String confirmar = etConfirmarPassword.getText().toString().trim();

            if(usuario.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                    || password.isEmpty() || confirmar.isEmpty()){

                Toast.makeText(registro.this,
                        "Complete todos los campos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if(!password.equals(confirmar)){
                Toast.makeText(registro.this,
                        "Las contraseñas no coinciden",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            boolean registrado = dbHelper.registrarUsuario(
                    usuario,
                    correo,
                    telefono,
                    password
            );

            if(registrado){
                Toast.makeText(registro.this,
                        "Usuario registrado correctamente",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(registro.this,
                        MainActivity.class));
                finish();

            }else{
                Toast.makeText(registro.this,
                        "Error al registrar",
                        Toast.LENGTH_SHORT).show();
            }

        });

        tvVolverLogin.setOnClickListener(view -> {
            startActivity(new Intent(registro.this,
                    MainActivity.class));
            finish();
        });
    }
}