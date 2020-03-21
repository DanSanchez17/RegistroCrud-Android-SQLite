package com.example.tabladante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class AyudaBD extends SQLiteOpenHelper {

    public static class DatosTabla implements BaseColumns {
        public static final String NOMBRE_TABLA = "Registro";
        public static final String COLUMNA_MATRICULA = "matricula";
        public static final String COLUMNA_PATERNO = "paterno";
        public static final String COLUMNA_MATERNO = "materno";
        public static final String COLUMNA_CARRERA = "carrera";
        public static final String COLUMNA_GRUPO = "grupo";
        public static final String COLUMNA_TURNO = "turno";
        public static final String COLUMNA_EDAD = "edad";

        private static final String CREAR_TABLA_1 =
                "CREATE TABLE " + DatosTabla.NOMBRE_TABLA + " (" +
                        DatosTabla.COLUMNA_MATRICULA + " INTEGER PRIMARY KEY," +
                        DatosTabla.COLUMNA_PATERNO + " TEXT," +
                        DatosTabla.COLUMNA_MATERNO + " TEXT," +
                        DatosTabla.COLUMNA_CARRERA + " TEXT," +
                        DatosTabla.COLUMNA_GRUPO + " TEXT," +
                        DatosTabla.COLUMNA_TURNO + " TEXT," +
                        DatosTabla.COLUMNA_EDAD + " TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTabla.NOMBRE_TABLA;

    }



    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dantebd.db";

    public AyudaBD(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatosTabla.CREAR_TABLA_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
