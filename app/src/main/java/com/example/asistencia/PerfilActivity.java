package com.example.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    TextView tvUsuario,tvCorreo,tvTelefono;
    Button btnEditar, btnEliminar;

    DatabaseHelper dbHelper;
    String usuarioActual;
    String passwordActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        dbHelper = new DatabaseHelper(this);

        tvUsuario = findViewById(R.id.tvUsuarioPerfil);
        tvCorreo = findViewById(R.id.tvCorreoPerfil);
        tvTelefono = findViewById(R.id.tvTelefonoPerfil);
        btnEditar = findViewById(R.id.btnEditarPerfil);
        btnEliminar = findViewById(R.id.btnEliminarPerfil);

        usuarioActual = getIntent().getStringExtra("usuario");

        cargarDatos();

        btnEditar.setOnClickListener(v -> mostrarModalEditar());

        btnEliminar.setOnClickListener(v -> eliminarCuenta());
    }

    private void cargarDatos(){
        Cursor cursor = dbHelper.obtenerUsuario(usuarioActual);

        if(cursor.moveToFirst()){

            String usuario = cursor.getString(cursor.getColumnIndexOrThrow("usuario"));
            String correo = cursor.getString(cursor.getColumnIndexOrThrow("correo"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            passwordActual = cursor.getString(cursor.getColumnIndexOrThrow("password"));

            tvUsuario.setText("Usuario: " + usuario);
            tvCorreo.setText("Correo: " + correo);
            tvTelefono.setText("Teléfono: " + telefono);
        }

        cursor.close();
    }

    private void mostrarModalEditar(){

        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_dialog_editar_perfil,null);

        EditText etUsuario = view.findViewById(R.id.etEditarUsuario);
        EditText etCorreo = view.findViewById(R.id.etEditarCorreo);
        EditText etTelefono = view.findViewById(R.id.etEditarTelefono);
        EditText etPassword = view.findViewById(R.id.etEditarPassword);

        Cursor cursor = dbHelper.obtenerUsuario(usuarioActual);

        if(cursor.moveToFirst()){

            etUsuario.setText(
                    cursor.getString(cursor.getColumnIndexOrThrow("usuario"))
            );

            etCorreo.setText(
                    cursor.getString(cursor.getColumnIndexOrThrow("correo"))
            );

            etTelefono.setText(
                    cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
            );

            etPassword.setText(
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
        }

        cursor.close();

        new AlertDialog.Builder(this)
                .setTitle("Editar Perfil")
                .setView(view)
                .setPositiveButton("Guardar",(d,w)->{

                    boolean actualizado = dbHelper.actualizarUsuario(
                            usuarioActual,
                            etUsuario.getText().toString(),
                            etCorreo.getText().toString(),
                            etTelefono.getText().toString(),
                            etPassword.getText().toString()
                    );

                    if(actualizado){
                        Toast.makeText(this,"Perfil actualizado",Toast.LENGTH_SHORT).show();
                        usuarioActual = etUsuario.getText().toString();
                        cargarDatos();
                    }

                })
                .setNegativeButton("Cancelar",null)
                .show();
    }

    private void eliminarCuenta(){

        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Eliminar cuenta definitivamente?")
                .setPositiveButton("Sí",(d,w)->{

                    boolean eliminado = dbHelper.eliminarUsuario(usuarioActual);

                    if(eliminado){
                        Toast.makeText(this,"Cuenta eliminada",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(
                                PerfilActivity.this,
                                MainActivity.class
                        );

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }

                })
                .setNegativeButton("No",null)
                .show();
    }
}