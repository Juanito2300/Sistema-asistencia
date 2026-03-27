package com.example.asistencia;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ComunicacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicacion);

        Button btnLlamar = findViewById(R.id.btnLlamar);
        Button btnCorreo = findViewById(R.id.btnCorreo);

        String nombre1 = "Juan esteban";
        String telefono1 = "3105928091";
        String correo1 = "juanestebanecheverri63@gmail.com";

        btnLlamar.setOnClickListener(v -> confirmarLlamada(nombre1, telefono1));
        btnCorreo.setOnClickListener(v -> confirmarCorreo(correo1));

        Button btnLlamar2 = findViewById(R.id.btnLlamar2);
        Button btnCorreo2 = findViewById(R.id.btnCorreo2);

        String nombre2 = "Juan jose";
        String telefono2 = "3000000000";
        String correo2 = "juan@gmail.com";

        btnLlamar2.setOnClickListener(v -> confirmarLlamada(nombre2, telefono2));
        btnCorreo2.setOnClickListener(v -> confirmarCorreo(correo2));
    }
    private void confirmarLlamada(String nombre, String telefono) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Deseas llamar a " + nombre + " al número " + telefono + "?")
                .setPositiveButton("Sí", (d, w) -> {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + telefono));
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void confirmarCorreo(String correo) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Deseas enviar un correo a " + correo + "?")
                .setPositiveButton("Sí", (d, w) -> {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hola, buen día...");

                    try {

                        intent.setPackage("com.google.android.gm");
                        startActivity(intent);
                    } catch (Exception e) {
                        Intent fallback = new Intent(Intent.ACTION_SEND);
                        fallback.setType("message/rfc822");
                        fallback.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
                        fallback.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                        fallback.putExtra(Intent.EXTRA_TEXT, "Hola, buen día...");

                        startActivity(Intent.createChooser(fallback, "Enviar correo con..."));
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}