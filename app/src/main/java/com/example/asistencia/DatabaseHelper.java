package com.example.asistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "asistencia.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ESTUDIANTES = "estudiantes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_CEDULA = "cedula";
    public static final String COLUMN_DIRECCION = "direccion";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_ESTUDIANTES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOMBRE + " TEXT,"
                + COLUMN_TELEFONO + " TEXT,"
                + COLUMN_CEDULA + " TEXT,"
                + COLUMN_DIRECCION + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTUDIANTES);
        onCreate(db);
    }

    // 🔴 MÉTODO PARA ELIMINAR
    public void eliminarEstudiante(String cedula) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ESTUDIANTES, COLUMN_CEDULA + "=?", new String[]{cedula});
        db.close();
    }
    public void actualizarEstudiante(String cedulaOriginal,
                                     String nombre,
                                     String telefono,
                                     String cedula,
                                     String direccion){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_TELEFONO, telefono);
        values.put(COLUMN_CEDULA, cedula);
        values.put(COLUMN_DIRECCION, direccion);

        db.update(TABLE_ESTUDIANTES,
                values,
                COLUMN_CEDULA + "=?",
                new String[]{cedulaOriginal});

        db.close();
    }
}
