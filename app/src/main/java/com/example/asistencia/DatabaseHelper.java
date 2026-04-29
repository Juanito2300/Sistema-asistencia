package com.example.asistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "asistencia.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ESTUDIANTES = "estudiantes";
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_CEDULA = "cedula";
    public static final String COL_CORREO = "correo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_ESTUDIANTES + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOMBRE + " TEXT, "
                + COL_TELEFONO + " TEXT, "
                + COL_CEDULA + " TEXT, "
                + COL_CORREO + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTUDIANTES);
        onCreate(db);
    }

    // INSERTAR
    public boolean insertarEstudiante(String nombre, String telefono, String cedula, String correo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NOMBRE, nombre);
        values.put(COL_TELEFONO, telefono);
        values.put(COL_CEDULA, cedula);
        values.put(COL_CORREO, correo);

        long resultado = db.insert(TABLE_ESTUDIANTES, null, values);
        return resultado != -1;
    }

    // OBTENER TODOS (CON ID)
    public ArrayList<Estudiante> obtenerEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ESTUDIANTES, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE));
                String telefono = cursor.getString(cursor.getColumnIndexOrThrow(COL_TELEFONO));
                String cedula = cursor.getString(cursor.getColumnIndexOrThrow(COL_CEDULA));
                String correo = cursor.getString(cursor.getColumnIndexOrThrow(COL_CORREO));

                lista.add(new Estudiante(id, nombre, telefono, cedula, correo));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    // ACTUALIZAR
    public boolean actualizarEstudiante(int id, String nombre, String telefono, String cedula, String correo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NOMBRE, nombre);
        values.put(COL_TELEFONO, telefono);
        values.put(COL_CEDULA, cedula);
        values.put(COL_CORREO, correo);

        int resultado = db.update(TABLE_ESTUDIANTES, values, "id=?", new String[]{String.valueOf(id)});
        return resultado > 0;
    }

    // ELIMINAR
    public boolean eliminarEstudiante(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int resultado = db.delete(TABLE_ESTUDIANTES, "id=?", new String[]{String.valueOf(id)});
        return resultado > 0;
    }
}