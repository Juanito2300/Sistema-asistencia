package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class activity_ver_asistencia extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_asistencia);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Ver Asistencia");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerEstudiantes);

        dbHelper = new DatabaseHelper(this);

        cargarDatos();
    }

    private void cargarDatos(){

        ArrayList<Estudiante> lista = dbHelper.obtenerEstudiantes();

        EstudianteAdapter adapter = new EstudianteAdapter(lista);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}