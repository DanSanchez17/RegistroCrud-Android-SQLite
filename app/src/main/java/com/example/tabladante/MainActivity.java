package com.example.tabladante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.invoke.ConstantCallSite;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar, btnBuscar, btnBorrar, btnActualizar;
    EditText etMatricula, etPaterno, etMaterno, etCarrera, etGrupo, etTurno, etEdad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos variables de botones
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBorrar = (Button)findViewById(R.id.btnBorrar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);

        //instanciamos variables de edittext
        etMatricula = (EditText)findViewById(R.id.etMatricula);
        etPaterno = (EditText)findViewById(R.id.etPaterno);
        etMaterno = (EditText)findViewById(R.id.etMaterno);
        etCarrera = (EditText)findViewById(R.id.etCarrera);
        etGrupo = (EditText)findViewById(R.id.etGrupo);
        etTurno = (EditText)findViewById(R.id.etTurno);
        etEdad = (EditText)findViewById(R.id.etEdad);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = ayudabd.getWritableDatabase();
                ContentValues valores =  new ContentValues();
                valores.put(AyudaBD.DatosTabla.COLUMNA_MATRICULA,etMatricula.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_PATERNO,etPaterno.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_MATERNO,etMaterno.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_CARRERA,etCarrera.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_GRUPO,etGrupo.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_TURNO,etTurno.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_EDAD,etEdad.getText().toString());

                Long IdGuardado = db.insert(AyudaBD.DatosTabla.NOMBRE_TABLA, AyudaBD.DatosTabla.COLUMNA_MATRICULA, valores);
                Toast.makeText(getApplicationContext(), "Registro Guardado con Exito: " + IdGuardado, Toast.LENGTH_LONG).show();

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = ayudabd.getReadableDatabase();

                String[] argsel = {etMatricula.getText().toString()};

                String[] projection = {AyudaBD.DatosTabla.COLUMNA_PATERNO, AyudaBD.DatosTabla.COLUMNA_MATERNO,
                        AyudaBD.DatosTabla.COLUMNA_CARRERA, AyudaBD.DatosTabla.COLUMNA_GRUPO,
                        AyudaBD.DatosTabla.COLUMNA_TURNO, AyudaBD.DatosTabla.COLUMNA_EDAD };

                Cursor c = db.query(AyudaBD.DatosTabla.NOMBRE_TABLA, projection, AyudaBD.DatosTabla.COLUMNA_MATRICULA+"=?",
                        argsel, null, null, null);

                c.moveToFirst();
                etPaterno.setText(c.getString(0 ));
                etMaterno.setText((c.getString(1)));
                etCarrera.setText((c.getString(2)));
                etGrupo.setText((c.getString(3)));
                etTurno.setText((c.getString(4)));
                etEdad.setText((c.getString(5)));



            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = ayudabd.getWritableDatabase();
                String selection = AyudaBD.DatosTabla.COLUMNA_MATRICULA + "=?";
                String[] argsel = {etMatricula.getText().toString()};

                db.delete(AyudaBD.DatosTabla.NOMBRE_TABLA, selection, argsel);

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db  = ayudabd.getWritableDatabase();
                ContentValues valores  = new ContentValues();

                valores.put(AyudaBD.DatosTabla.COLUMNA_PATERNO,etPaterno.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_MATERNO,etMaterno.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_CARRERA,etCarrera.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_GRUPO,etGrupo.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_TURNO,etTurno.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_EDAD,etEdad.getText().toString());

                String selection = AyudaBD.DatosTabla.COLUMNA_MATRICULA + "=?";
                String[] argsel = {etMatricula.getText().toString()};

                int count = db.update(AyudaBD.DatosTabla.NOMBRE_TABLA, valores, selection, argsel);

                Toast.makeText(getApplicationContext(), "Registro Actualizado con Exito: ",Toast.LENGTH_LONG).show();
            }
        });



    }
}
