package com.example.asistencia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CentroControlAdapter extends RecyclerView.Adapter<CentroControlAdapter.ViewHolder> {

    ArrayList<Estudiante> lista;

    public CentroControlAdapter(ArrayList<Estudiante> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_centro_control, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Estudiante e = lista.get(position);

        holder.tvNombre.setText(e.nombre);
        holder.tvTelefono.setText(e.telefono);
        holder.tvCedula.setText(e.cedula);
        holder.tvDireccion.setText(e.direccion);

        // 🔴 BOTÓN ELIMINAR
        holder.btnEliminar.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Confirmar eliminación")
                    .setMessage("¿Desea eliminar a " + lista.get(pos).nombre + "?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        db.eliminarEstudiante(lista.get(pos).cedula);

                        lista.remove(pos);
                        notifyItemRemoved(pos);
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // 🔵 BOTÓN EDITAR
        holder.btnEditar.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            Estudiante estudiante = lista.get(pos);

            View dialogView = LayoutInflater.from(v.getContext())
                    .inflate(R.layout.dialog_editar_estudiante, null);

            EditText etNombre = dialogView.findViewById(R.id.etNombre);
            EditText etTelefono = dialogView.findViewById(R.id.etTelefono);
            EditText etCedula = dialogView.findViewById(R.id.etCedula);
            EditText etDireccion = dialogView.findViewById(R.id.etDireccion);

            // Cargar datos actuales
            etNombre.setText(estudiante.nombre);
            etTelefono.setText(estudiante.telefono);
            etCedula.setText(estudiante.cedula);
            etDireccion.setText(estudiante.direccion);

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Editar estudiante")
                    .setView(dialogView)
                    .setPositiveButton("Guardar", (dialog, which) -> {

                        String nuevoNombre = etNombre.getText().toString().trim();
                        String nuevoTelefono = etTelefono.getText().toString().trim();
                        String nuevaCedula = etCedula.getText().toString().trim();
                        String nuevaDireccion = etDireccion.getText().toString().trim();

                        DatabaseHelper db = new DatabaseHelper(v.getContext());
                        db.actualizarEstudiante(
                                estudiante.cedula,
                                nuevoNombre,
                                nuevoTelefono,
                                nuevaCedula,
                                nuevaDireccion
                        );

                        // Actualizar objeto en memoria
                        estudiante.nombre = nuevoNombre;
                        estudiante.telefono = nuevoTelefono;
                        estudiante.cedula = nuevaCedula;
                        estudiante.direccion = nuevaDireccion;

                        notifyItemChanged(pos);

                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvTelefono, tvCedula, tvDireccion;
        TextView btnEditar, btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCedula = itemView.findViewById(R.id.tvCedula);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}