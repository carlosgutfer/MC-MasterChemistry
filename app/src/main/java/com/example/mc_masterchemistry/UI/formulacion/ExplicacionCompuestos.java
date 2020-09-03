package com.example.mc_masterchemistry.UI.formulacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mc_masterchemistry.R;

public class ExplicacionCompuestos extends AppCompatActivity {

    private TextView titulo, informacion;

    private String nombre, datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicacion_compuestos);
        titulo=findViewById(R.id.TV_titulo);
        informacion=findViewById(R.id.TV_información);
        Intent();
        titulo.setText(nombre);
        informacion.setText(datos);

    }

    private  void Intent(){
       nombre=getIntent().getStringExtra("Nombre");
       datos=getIntent().getStringExtra("Datos");
    }
}
