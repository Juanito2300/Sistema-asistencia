package com.example.asistencia;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COL_USER_ID = "id_usuario";
    public static final String COL_USER_NOMBRE = "usuario";
    public static final String COL_USER_CORREO = "correo";
    public static final String COL_USER_TELEFONO = "telefono";
    public static final String COL_USER_PASSWORD = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABLA ESTUDIANTES
        String CREATE_TABLE_ESTUDIANTES = "CREATE TABLE " + TABLE_ESTUDIANTES + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOMBRE + " TEXT, "
                + COL_TELEFONO + " TEXT, "
                + COL_CEDULA + " TEXT, "
                + COL_CORREO + " TEXT)";

        db.execSQL(CREATE_TABLE_ESTUDIANTES);


        // TABLA USUARIOS
        String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLE_USUARIOS + " ("
                + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER_NOMBRE + " TEXT, "
                + COL_USER_CORREO + " TEXT, "
                + COL_USER_TELEFONO + " TEXT, "
                + COL_USER_PASSWORD + " TEXT)";

        db.execSQL(CREATE_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTUDIANTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);

        onCreate(db);
    }

    public boolean insertarEstudiante(String nombre, String telefono,
                                      String cedula, String correo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NOMBRE, nombre);
        values.put(COL_TELEFONO, telefono);
        values.put(COL_CEDULA, cedula);
        values.put(COL_CORREO, correo);

        long resultado = db.insert(TABLE_ESTUDIANTES, null, values);

        return resultado != -1;
    }

    public ArrayList<Estudiante> obtenerEstudiantes() {

        ArrayList<Estudiante> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ESTUDIANTES,
                null
        );

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_ID)
                );

                String nombre = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NOMBRE)
                );

                String telefono = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_TELEFONO)
                );

                String cedula = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CEDULA)
                );

                String correo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CORREO)
                );

                lista.add(
                        new Estudiante(
                                id,
                                nombre,
                                telefono,
                                cedula,
                                correo
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista;
    }

    public boolean actualizarEstudiante(int id, String nombre,
                                        String telefono,
                                        String cedula,
                                        String correo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NOMBRE, nombre);
        values.put(COL_TELEFONO, telefono);
        values.put(COL_CEDULA, cedula);
        values.put(COL_CORREO, correo);

        int resultado = db.update(
                TABLE_ESTUDIANTES,
                values,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return resultado > 0;
    }

    public boolean eliminarEstudiante(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int resultado = db.delete(
                TABLE_ESTUDIANTES,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return resultado > 0;
    }

    public boolean registrarUsuario(String usuario,
                                    String correo,
                                    String telefono,
                                    String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USER_NOMBRE, usuario);
        values.put(COL_USER_CORREO, correo);
        values.put(COL_USER_TELEFONO, telefono);
        values.put(COL_USER_PASSWORD, password);

        long resultado = db.insert(
                TABLE_USUARIOS,
                null,
                values
        );

        return resultado != -1;
    }

    public boolean validarLogin(String usuario,
                                String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USUARIOS +
                        " WHERE usuario=? AND password=?",
                new String[]{usuario, password}
        );

        boolean existe = cursor.getCount() > 0;

        cursor.close();

        return existe;
    }

    public Cursor obtenerUsuario(String usuario){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM usuarios WHERE usuario=?",
                new String[]{usuario}
        );
    }

    public boolean actualizarUsuario(String usuarioActual,
                                                                String nuevoUsuario,
                                                                String correo,
                                                                String telefono,
                                                                String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USER_NOMBRE, nuevoUsuario);
        values.put(COL_USER_CORREO, correo);
        values.put(COL_USER_TELEFONO, telefono);
        values.put(COL_USER_PASSWORD, password);

        int resultado = db.update(
                TABLE_USUARIOS,
                values,
                "usuario=?",
                new String[]{usuarioActual}
        );

        return resultado > 0;
    }

    public boolean eliminarUsuario(String usuario){

        SQLiteDatabase db = this.getWritableDatabase();

        int resultado = db.delete(
                TABLE_USUARIOS,
                "usuario=?",
                new String[]{usuario}
        );

        return resultado > 0;
    }
}