package com.example.mc_masterchemistry.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mc_masterchemistry.R;

public class EleccionBinarioTerciarios extends AppCompatActivity implements View.OnClickListener {
        private Button binario, terciario, BiTer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_binario_terciarios);
        binario=findViewById(R.id.Bt_personalizado);
        terciario=findViewById(R.id.Bt_terciario);
        BiTer=findViewById(R.id.Bt_BiTer);
        binario.setOnClickListener(this);
        terciario.setOnClickListener(this);
        BiTer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.Bt_personalizado){
            Intent binario = new Intent(this, eleccionJuego.class);
            binario.putExtra("valor",2);
            startActivity(binario);
        }
        if(v.getId()==R.id.Bt_terciario){
            Intent terciario = new Intent(this, eleccionJuego.class);
            terciario.putExtra("valor",1);
            startActivity(terciario);
        }
        if(v.getId()==R.id.Bt_BiTer){
            Intent binario = new Intent(this, eleccionJuego.class);
            binario.putExtra("valor",3);
            startActivity(binario);
        }

    }
}
