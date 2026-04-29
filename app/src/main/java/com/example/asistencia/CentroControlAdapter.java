package com.example.asistencia;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CentroControlAdapter extends RecyclerView.Adapter<CentroControlAdapter.ViewHolder>{

    ArrayList<Estudiante> lista;

    public CentroControlAdapter(ArrayList<Estudiante> lista){
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_centro_control,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

        Estudiante e = lista.get(position);

        holder.tvNombre.setText(e.nombre);
        holder.tvTelefono.setText("Tel: " + e.telefono);
        holder.tvCedula.setText("Cédula: " + e.cedula);
        holder.tvCorreo.setText("Correo: " + e.correo);

        holder.btnEliminar.setOnClickListener(v -> {

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Eliminar")
                    .setMessage("¿Seguro que quieres eliminar este estudiante?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        DatabaseHelper db = new DatabaseHelper(v.getContext());

                        if(db.eliminarEstudiante(e.id)){
                            lista.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(v.getContext(),"Eliminado",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(v.getContext(),"Error",Toast.LENGTH_SHORT).show();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        holder.btnEditar.setOnClickListener(v -> {

            View view = LayoutInflater.from(v.getContext())
                    .inflate(R.layout.dialog_editar_estudiante, null);

            EditText etNombre = view.findViewById(R.id.etNombre);
            EditText etTelefono = view.findViewById(R.id.etTelefono);
            EditText etCedula = view.findViewById(R.id.etCedula);
            EditText etCorreo = view.findViewById(R.id.etCorreo);

            etNombre.setText(e.nombre);
            etTelefono.setText(e.telefono);
            etCedula.setText(e.cedula);
            etCorreo.setText(e.correo);

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Editar estudiante")
                    .setView(view)
                    .setPositiveButton("Guardar", (dialog, which) -> {

                        DatabaseHelper db = new DatabaseHelper(v.getContext());

                        boolean actualizado = db.actualizarEstudiante(
                                e.id,
                                etNombre.getText().toString(),
                                etTelefono.getText().toString(),
                                etCedula.getText().toString(),
                                etCorreo.getText().toString()
                        );

                        if(actualizado){
                            e.nombre = etNombre.getText().toString();
                            e.telefono = etTelefono.getText().toString();
                            e.cedula = etCedula.getText().toString();
                            e.correo = etCorreo.getText().toString();

                            notifyItemChanged(position);
                            Toast.makeText(v.getContext(),"Actualizado",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(v.getContext(),"Error",Toast.LENGTH_SHORT).show();
                        }

                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre,tvTelefono,tvCedula,tvCorreo,btnEditar,btnEliminar;

        public ViewHolder(View itemView){
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCedula = itemView.findViewById(R.id.tvCedula);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}