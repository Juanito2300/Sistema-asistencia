package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_ver_asistencia extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtSinDatos;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_asistencia);

        // TITULO SUPERIOR
        if(getSupportActionBar()!=null){

            getSupportActionBar().setTitle("Control de Asistencia");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // REFERENCIAS

        recyclerView = findViewById(R.id.recyclerEstudiantes);
        txtSinDatos = findViewById(R.id.txtSinDatos);

        // BASE DE DATOS

        dbHelper = new DatabaseHelper(this);

        // CONFIGURAR RECYCLER

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // CARGAR DATOS

        cargarDatos();
    }

    private void cargarDatos(){

        // OBTENER LISTA

        ArrayList<Estudiante> lista = dbHelper.obtenerEstudiantes();

        // VALIDAR SI HAY DATOS

        if(lista.isEmpty()){

            txtSinDatos.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        } else {

            txtSinDatos.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        // ADAPTER

        EstudianteAdapter adapter = new EstudianteAdapter(lista);

        recyclerView.setAdapter(adapter);
    }

    // BOTON ATRAS

    @Override
    public boolean onSupportNavigateUp(){

        finish();
        return true;
    }
}