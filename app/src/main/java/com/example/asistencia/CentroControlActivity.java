package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CentroControlActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centro_control);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Centro de Control");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerCentro);


        dbHelper = new DatabaseHelper(this);

        cargarDatos();
    }

    private void cargarDatos(){


        ArrayList<Estudiante> lista = dbHelper.obtenerEstudiantes();

        CentroControlAdapter adapter = new CentroControlAdapter(lista);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}