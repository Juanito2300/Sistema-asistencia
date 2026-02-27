package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class activity_ver_asistencia extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ArrayList<String> listaDatos;

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

        ArrayList<Estudiante> lista = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT nombre, telefono, cedula, direccion FROM estudiantes", null);

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

        EstudianteAdapter adapter = new EstudianteAdapter(lista);

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