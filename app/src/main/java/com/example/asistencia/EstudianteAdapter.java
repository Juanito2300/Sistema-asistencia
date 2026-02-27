package com.example.asistencia;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder>{

    ArrayList<Estudiante> lista;

    public EstudianteAdapter(ArrayList<Estudiante> lista){
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_estudiante, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        Estudiante e = lista.get(position);

        holder.tvNombre.setText(e.nombre);
        holder.tvTelefono.setText(e.telefono);
        holder.tvCedula.setText(e.cedula);
        holder.tvDireccion.setText(e.direccion);

        // 🔥 RESET obligatorio
        holder.btnSi.setVisibility(View.VISIBLE);
        holder.btnNo.setVisibility(View.VISIBLE);
        holder.btnSi.setTextSize(20);
        holder.btnNo.setTextSize(20);

        // 🔥 Aplicar estado actual
        if(e.asistencia == 1){
            holder.btnSi.setTextSize(32);
            holder.btnNo.setVisibility(View.GONE);
        }
        else if(e.asistencia == 0){
            holder.btnNo.setTextSize(32);
            holder.btnSi.setVisibility(View.GONE);
        }
        // Si es -1 se ven los dos normales

        // ✔ ASISTIÓ
        holder.btnSi.setOnClickListener(v -> {
            e.asistencia = 1;
            notifyItemChanged(holder.getAdapterPosition());
        });

        // ❌ FALTA → abre WhatsApp
        holder.btnNo.setOnClickListener(v -> {

            e.asistencia = 0;
            notifyItemChanged(holder.getAdapterPosition());

            String numero = e.telefono;

            // Limpiar caracteres que no sean números
            numero = numero.replaceAll("[^0-9]", "");

            // Si tiene 10 dígitos, agregamos código Colombia 57
            if(numero.length() == 10){
                numero = "57" + numero;
            }

            String mensaje = "Cordial saludo.\n\n"
                    + "Le informamos que el estudiante " + e.nombre +
                    " no registró asistencia a la jornada académica del día de hoy.\n\n"
                    + "De manera atenta, solicitamos enviar la respectiva excusa o justificación "
                    + "por este mismo medio a la mayor brevedad posible.\n\n"
                    + "Agradecemos su atención y colaboración.\n\n"
                    + "Atentamente,\n"
                    + "Coordinación Académica.";

            try {

                String url = "https://wa.me/" + numero + "?text=" + Uri.encode(mensaje);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                v.getContext().startActivity(intent);

            } catch (Exception ex) {

                Toast.makeText(v.getContext(),
                        "WhatsApp no está instalado",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvTelefono, tvCedula, tvDireccion, btnSi, btnNo;

        public ViewHolder(View itemView){
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCedula = itemView.findViewById(R.id.tvCedula);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btnSi = itemView.findViewById(R.id.btnSi);
            btnNo = itemView.findViewById(R.id.btnNo);
        }
    }
}