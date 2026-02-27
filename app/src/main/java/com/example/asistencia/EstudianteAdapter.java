package com.example.asistencia;

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
        holder.tvTelefono.setText("Tel: " + e.telefono);
        holder.tvCedula.setText("Cédula: " + e.cedula);
        holder.tvDireccion.setText("Dirección: " + e.direccion);

        holder.btnSi.setOnClickListener(v ->
                Toast.makeText(v.getContext(),
                        "Asistió",
                        Toast.LENGTH_SHORT).show());

        holder.btnNo.setOnClickListener(v ->
                Toast.makeText(v.getContext(),
                        "No asistió",
                        Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre,tvTelefono,tvCedula,tvDireccion,btnSi,btnNo;

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