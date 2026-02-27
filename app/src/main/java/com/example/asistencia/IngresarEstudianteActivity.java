package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

public class IngresarEstudianteActivity extends AppCompatActivity {

    EditText etNombre, etTelefono, etCedula, etDireccion;
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

        dbHelper = new DatabaseHelper(this);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etCedula = findViewById(R.id.etCedula);
        etDireccion = findViewById(R.id.etDireccion);
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
        String direccion = etDireccion.getText().toString().trim();

        if(nombre.isEmpty() || telefono.isEmpty() || cedula.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
        values.put(DatabaseHelper.COLUMN_TELEFONO, telefono);
        values.put(DatabaseHelper.COLUMN_CEDULA, cedula);
        values.put(DatabaseHelper.COLUMN_DIRECCION, direccion);

        long result = db.insert(DatabaseHelper.TABLE_ESTUDIANTES, null, values);

        if(result != -1) {
            Toast.makeText(this, "Estudiante guardado con éxito", Toast.LENGTH_SHORT).show();
            finish(); // vuelve al menú
        } else {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}