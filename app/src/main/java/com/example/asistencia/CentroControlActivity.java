package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        ArrayList<Estudiante> lista = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT nombre, telefono, cedula, direccion FROM estudiantes",
                null);

        while(cursor.moveToNext()){
            lista.add(
                    new Estudiante(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
                    )
            );
        }

        cursor.close();
        db.close();

        CentroControlAdapter adapter = new CentroControlAdapter(lista);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}