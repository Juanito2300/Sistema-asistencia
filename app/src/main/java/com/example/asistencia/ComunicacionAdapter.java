package com.example.asistencia;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComunicacionAdapter extends RecyclerView.Adapter<ComunicacionAdapter.ViewHolder>{

    ArrayList<Estudiante> lista;

    public ComunicacionAdapter(ArrayList<Estudiante> lista){
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, correo, telefono;
        Button btnLlamar, btnCorreo;

        public ViewHolder(View itemView){
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombre);
            correo = itemView.findViewById(R.id.txtCorreo);
            telefono = itemView.findViewById(R.id.txtTelefono);
            btnLlamar = itemView.findViewById(R.id.btnLlamar);
            btnCorreo = itemView.findViewById(R.id.btnCorreo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comunicacion, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        Estudiante e = lista.get(position);

        holder.nombre.setText("Nombre: " + e.nombre);
        holder.correo.setText("Correo: " + e.correo);
        holder.telefono.setText("Teléfono: " + e.telefono);

        holder.btnLlamar.setOnClickListener(v -> {

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Confirmar")
                    .setMessage("¿Llamar a " + e.nombre + "?")
                    .setPositiveButton("Sí", (d,w)->{
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + e.telefono));
                        v.getContext().startActivity(intent);
                    })
                    .setNegativeButton("No",null)
                    .show();
        });

        holder.btnCorreo.setOnClickListener(v -> {

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Confirmar")
                    .setMessage("¿Enviar correo a " + e.correo + "?")
                    .setPositiveButton("Sí", (d,w)->{

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{e.correo});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hola, buen día...");

                        v.getContext().startActivity(Intent.createChooser(intent,"Enviar correo"));

                    })
                    .setNegativeButton("No",null)
                    .show();
        });
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }
}