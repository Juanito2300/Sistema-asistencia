package com.example.asistencia;

public class Estudiante {

    int id;
    String nombre;
    String telefono;
    String cedula;
    String correo;

    public Estudiante(int id, String nombre, String telefono, String cedula, String correo){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cedula = cedula;
        this.correo = correo;
    }
}