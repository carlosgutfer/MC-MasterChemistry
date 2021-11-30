package com.example.mc_masterchemistry.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mc_masterchemistry.R;

public class EleccionBinarioTerciarios extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_binario_terciarios);
        Button binario = findViewById(R.id.Bt_personalizado);
        Button terciario = findViewById(R.id.Bt_terciario);
        Button biTer = findViewById(R.id.Bt_BiTer);
        binario.setOnClickListener(this);
        terciario.setOnClickListener(this);
        biTer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.Bt_personalizado)
            nuevoJuego(2);
        if(v.getId()==R.id.Bt_terciario)
            nuevoJuego(1);
        if(v.getId()==R.id.Bt_BiTer)
            nuevoJuego(3);
    }

    private void nuevoJuego(int i)
    {
        Intent nuevaPantalla = new Intent(this, eleccionJuego.class);
        nuevaPantalla.putExtra("valor", i);
        startActivity(nuevaPantalla);
    }
}
