package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class registro extends AppCompatActivity {

    EditText etNuevoUsuario, etCorreo, etTelefono, etNuevaPassword, etConfirmarPassword;
    Button btnRegistrarUsuario;
    TextView tvVolverLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Enlazar elementos del XML
        etNuevoUsuario = findViewById(R.id.etNuevoUsuario);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etNuevaPassword = findViewById(R.id.etNuevaPassword);
        etConfirmarPassword = findViewById(R.id.etConfirmarPassword);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        tvVolverLogin = findViewById(R.id.tvVolverLogin);

        // Listener para registrar usuario
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = etNuevoUsuario.getText().toString().trim();
                String correo = etCorreo.getText().toString().trim();
                String telefono = etTelefono.getText().toString().trim();
                String password = etNuevaPassword.getText().toString().trim();
                String confirmar = etConfirmarPassword.getText().toString().trim();

                // Validar campos vacíos
                if(usuario.isEmpty() || correo.isEmpty() || telefono.isEmpty() || password.isEmpty() || confirmar.isEmpty()){
                    Toast.makeText(registro.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar contraseña
                if(!password.equals(confirmar)){
                    Toast.makeText(registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Aquí podrías guardar los datos en base de datos o SharedPreferences
                Toast.makeText(registro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();

                // Volver al login
                Intent intent = new Intent(registro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Listener para volver al login desde el TextView
        tvVolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}