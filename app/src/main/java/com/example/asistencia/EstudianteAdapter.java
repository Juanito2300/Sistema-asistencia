package com.example.asistencia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {

    ArrayList<Estudiante> lista;

    public EstudianteAdapter(ArrayList<Estudiante> lista){
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, telefono, cedula, correo;
        TextView btnSi, btnNo;

        public ViewHolder(View itemView){
            super(itemView);

            nombre = itemView.findViewById(R.id.tvNombre);
            telefono = itemView.findViewById(R.id.tvTelefono);
            cedula = itemView.findViewById(R.id.tvCedula);
            correo = itemView.findViewById(R.id.tvCorreo);
            btnSi = itemView.findViewById(R.id.btnSi);
            btnNo = itemView.findViewById(R.id.btnNo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_estudiante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        Estudiante est = lista.get(position);

        holder.nombre.setText(est.nombre);
        holder.telefono.setText(est.telefono);
        holder.cedula.setText(est.cedula);
        holder.correo.setText(est.correo);

        Context context = holder.itemView.getContext();

        holder.btnNo.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Confirmar")
                    .setMessage("¿Seguro que NO asistió?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        // ocultar ✔
                        holder.btnSi.setVisibility(View.GONE);

                        holder.btnNo.setEnabled(false);

                        try {
                            String mensaje = "Hola, buen día.%0A%0ASe registra que no asististe a la clase programada el día de hoy. Por favor envía una justificación lo antes posible.%0A%0AGracias.";
                            String url = "https://wa.me/57" + est.telefono + "?text=" + mensaje;

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            context.startActivity(intent);

                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });


        holder.btnSi.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Confirmar")
                    .setMessage("¿Confirmar asistencia?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        holder.btnNo.setVisibility(View.GONE);

                        // dejar ✔ fijo
                        holder.btnSi.setEnabled(false);

                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }
}