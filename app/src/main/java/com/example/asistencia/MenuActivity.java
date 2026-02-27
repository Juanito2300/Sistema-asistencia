package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    LinearLayout cardIngresar;
    LinearLayout cardVerAsistencia;
    LinearLayout cardCentroControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardIngresar = findViewById(R.id.cardIngresar);
        cardVerAsistencia = findViewById(R.id.cardVerAsistencia);
        cardCentroControl = findViewById(R.id.cardCentroControl);

        cardIngresar.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, IngresarEstudianteActivity.class);
            startActivity(intent);
        });

        cardVerAsistencia.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, activity_ver_asistencia.class);
            startActivity(intent);
        });

        cardCentroControl.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, CentroControlActivity.class);
            startActivity(intent);
        });
    }
}