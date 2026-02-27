package com.example.asistencia;

public class Estudiante {

    String nombre;
    String telefono;
    String cedula;
    String direccion;

    // -1 = nada seleccionado
    //  1 = asistió (✔)
    //  0 = no asistió (❌)
    int asistencia = -1;

    public Estudiante(String nombre, String telefono, String cedula, String direccion){
        this.nombre = nombre;
        this.telefono = telefono;
        this.cedula = cedula;
        this.direccion = direccion;
    }
}