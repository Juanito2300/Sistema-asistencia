package com.example.asistencia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IngresarEstudianteActivity extends AppCompatActivity {

    EditText etNombre, etTelefono, etCedula, etCorreo;
    Button btnGuardar;


    DatabaseHelper dbHelper;

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

        dbHelper = new DatabaseHelper(this);

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


        boolean insertado = dbHelper.insertarEstudiante(nombre, telefono, cedula, correo);

        if(insertado){
            Toast.makeText(this, "Estudiante guardado correctamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }else{
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
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