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
    LinearLayout btnPerfil;

    String usuarioLogueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        usuarioLogueado = getIntent().getStringExtra("usuario");

        cardIngresar = findViewById(R.id.cardIngresar);
        cardVerAsistencia = findViewById(R.id.cardVerAsistencia);
        cardCentroControl = findViewById(R.id.cardCentroControl);
        cardComunicacion = findViewById(R.id.cardComunicacion);
        cardGPS = findViewById(R.id.cardGPS);
        btnPerfil = findViewById(R.id.btnPerfil);

        cardIngresar.setOnClickListener(v ->
                startActivity(new Intent(this, IngresarEstudianteActivity.class)));

        cardVerAsistencia.setOnClickListener(v ->
                startActivity(new Intent(this, activity_ver_asistencia.class)));

        cardCentroControl.setOnClickListener(v ->
                startActivity(new Intent(this, CentroControlActivity.class)));

        cardComunicacion.setOnClickListener(v ->
                startActivity(new Intent(this, ComunicacionActivity.class)));

        cardGPS.setOnClickListener(v ->
                startActivity(new Intent(this, gps.class)));

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, PerfilActivity.class);
            intent.putExtra("usuario", usuarioLogueado);
            startActivity(intent);
        });
    }
}