package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        configurarBotones();
    }

    private void configurarBotones() {

        // Busca TODOS los botones dentro del layout
        int totalHijos = ((android.view.ViewGroup)findViewById(android.R.id.content)).getRootView().getHeight();

        // Método sencillo: recorrer todo el layout
        recorrerVistas(findViewById(android.R.id.content));
    }

    private void recorrerVistas(View view) {

        if (view instanceof Button) {

            Button btn = (Button) view;

            if (btn.getText().toString().equals("Ubicación")) {

                btn.setOnClickListener(v -> abrirMapa());
            }
        }

        if (view instanceof android.view.ViewGroup) {

            android.view.ViewGroup grupo = (android.view.ViewGroup) view;

            for (int i = 0; i < grupo.getChildCount(); i++) {
                recorrerVistas(grupo.getChildAt(i));
            }
        }
    }

    private void abrirMapa() {

        // Coordenadas UNAD Medellín (Prado aprox)
        double lat = 6.25184;
        double lon = -75.56359;

        Uri uri = Uri.parse("geo:" + lat + "," + lon + "?q=" + lat + "," + lon + "(UNAD Medellín)");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");

        startActivity(intent);
    }
}