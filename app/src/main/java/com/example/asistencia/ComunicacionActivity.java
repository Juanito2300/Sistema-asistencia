package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ComunicacionActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicacion);

        recycler = findViewById(R.id.recyclerComunicacion);

        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Estudiante> lista = db.obtenerEstudiantes();

        ComunicacionAdapter adapter = new ComunicacionAdapter(lista);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
}