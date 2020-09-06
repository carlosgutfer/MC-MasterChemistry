package com.example.mc_masterchemistry.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.UI.MejorPuntuacion.MejorPuntuacionActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button salir, formulacion,jugar,tutorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salir=findViewById(R.id.bt_salir);
        formulacion=findViewById(R.id.bt_salir);
        jugar=findViewById(R.id.Bt_jugar);
        tutorial=findViewById(R.id.BT_tutorial);

        salir.setOnClickListener(this);
        formulacion.setOnClickListener(this);
        jugar.setOnClickListener(this);
        tutorial.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

            if (v.getId()==R.id.bt_salir) {
                Intent record = new Intent(this, MejorPuntuacionActivity.class);
                startActivity(record);
        };
        if (v.getId()==R.id.Bt_formulacion) {
            Intent formulacion = new Intent(this, com.example.mc_masterchemistry.UI.formulacion.formulacion.class);
            startActivity(formulacion);
        }
        if(v.getId()==R.id.Bt_jugar){
            Intent jugar = new Intent(this, EleccionBinarioTerciarios.class);
            startActivity(jugar);
        }
        if(v.getId()==R.id.BT_tutorial){
            Intent tutorial = new Intent(this, TutorialActivity.class);
            startActivity(tutorial);
        }


    }




}
