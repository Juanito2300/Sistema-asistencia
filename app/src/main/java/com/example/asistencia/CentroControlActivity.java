package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CentroControlActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centro_control);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Centro de Control");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerCentro);

        cargarDatos();
    }

    private void cargarDatos(){

        ArrayList<Estudiante> lista = new ArrayList<>();

        lista.add(new Estudiante("Juan Pérez","3001112233","123456","juanito@gmail.com"));
        lista.add(new Estudiante("Ana Gómez","3002223344","987654","angomez5@gmail.com"));
        lista.add(new Estudiante("Carlos López","3003334455","456789","carlitos45@gmail.com"));
        lista.add(new Estudiante("María Torres","3004445566","654321","marito678@gmail.com"));

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