package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class IngresarEstudianteActivity extends AppCompatActivity {

    EditText etNombre, etTelefono, etCedula, etCorreo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_estudiante);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Ingresar Estudiante");
        }

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etCedula = findViewById(R.id.etCedula);
        etCorreo = findViewById(R.id.etCorreo);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarEstudiante();
            }
        });
    }

    private void guardarEstudiante() {

        String nombre = etNombre.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String cedula = etCedula.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();

        if(nombre.isEmpty() || telefono.isEmpty() || cedula.isEmpty() || correo.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Estudiante guardado correctamente", Toast.LENGTH_SHORT).show();

        limpiarCampos();
    }

    private void limpiarCampos(){
        etNombre.setText("");
        etTelefono.setText("");
        etCedula.setText("");
        etCorreo.setText("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}