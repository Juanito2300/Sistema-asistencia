package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    LinearLayout cardIngresar;
    LinearLayout cardVerAsistencia;
    LinearLayout cardCentroControl;
    LinearLayout cardComunicacion;
    LinearLayout cardGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardIngresar = findViewById(R.id.cardIngresar);
        cardVerAsistencia = findViewById(R.id.cardVerAsistencia);
        cardCentroControl = findViewById(R.id.cardCentroControl);
        cardComunicacion = findViewById(R.id.cardComunicacion);
        cardGPS = findViewById(R.id.cardGPS);

        cardIngresar.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, IngresarEstudianteActivity.class));
        });

        cardVerAsistencia.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, activity_ver_asistencia.class));
        });

        cardCentroControl.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, CentroControlActivity.class));
        });

        cardComunicacion.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, ComunicacionActivity.class));
        });

        cardGPS.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, gps.class));
        });

    }
}