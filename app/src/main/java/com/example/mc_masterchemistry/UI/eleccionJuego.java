package com.example.mc_masterchemistry.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mc_masterchemistry.ElementoViewModel;
import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.UI.tablero.Tablero;
import com.example.mc_masterchemistry.UI.terciarios.tablero_terciarios;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.List;

public class eleccionJuego extends AppCompatActivity implements View.OnClickListener {

    List<ElementoEntity> allElementos;
    private final int[] ids =new int[2];
    private final int[] NumerosOxi = new int [8];
    private int control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ElementoViewModel melementoviewModel = new ViewModelProvider(this).get(ElementoViewModel.class);
        melementoviewModel.getAllElementos().observe(this, this::setelementos);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_juego);
        Button btn_aleatorio = findViewById(R.id.Bt_aleatorio);
        Button btn_personalizado = findViewById(R.id.Bt_personalizado);

        btn_aleatorio.setOnClickListener(this);
        btn_personalizado.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.Bt_aleatorio){
            control=getIntent().getIntExtra("valor",control);
           if(control==1)
               comenzarJuegoAleatorio(false);
           else if(control==2)
               comenzarJuegoAleatorio(true);
           else if(control==3)
               comenzarJuegoAleatorio(true);
        }
        if (v.getId()==R.id.Bt_personalizado)
        {
            control=getIntent().getIntExtra("valor",control);
            Intent personalizado = new Intent(this, tablaPeriodica.class);
            personalizado.putExtra("valor",control);
            startActivity(personalizado);
            finish();
        }
    }

    private void comenzarJuegoAleatorio(boolean tablero)
    {
        sorteo();
        Intent jugar;
        if(tablero)
            jugar = new Intent(this, Tablero.class);
        else
            jugar = new Intent(this, tablero_terciarios.class);
        jugar.putExtra("numeros de Oxidacion", NumerosOxi);
        jugar.putExtra("ids", ids);
        jugar.putExtra("BiTer",control);
        startActivity(jugar);
        finish();
    }

    private void setelementos(List<ElementoEntity> items) {
        this.allElementos = items;
    }

    private void sorteo()
    {
        int azar= (int) Math.floor(Math.random() * 10);
        ElementoEntity elementoM = allElementos.get(azar);
        ids[0]= elementoM.getId();
        NumerosOxi[0]= elementoM.getNO1();
        NumerosOxi[1]= elementoM.getNO2();
        NumerosOxi[4]= elementoM.getIM1();
        NumerosOxi[5]= elementoM.getIM2();
        azar = (int) Math.floor(Math.random() * (22 - 10) + 10);
            if (azar==17|azar==13)
                azar=15;
        ElementoEntity elementoNM = allElementos.get(azar);
        ids[1]= elementoNM.getId();
        NumerosOxi[2]= elementoNM.getNO1();
        NumerosOxi[6]= elementoNM.getIM1();
        if(azar==10|azar==11|azar==14|azar==15){
            azar = (int) Math.floor(Math.random() * 2);

            switch (azar) {
                case 0:
                    NumerosOxi[3] = elementoNM.getNO2();
                    NumerosOxi[7] = elementoNM.getIM2();
                    break;
                case 1:
                    NumerosOxi[3] = elementoNM.getNO3();
                    NumerosOxi[7] = elementoNM.getIM3();
                    break;
            }
        }else {
            azar = (int) Math.floor(Math.random() * 3);
                switch (azar)
                {
                    case 0:
                        NumerosOxi[3] = elementoNM.getNO2();
                        NumerosOxi[7] = elementoNM.getIM2();
                        break;
                    case 1:
                        NumerosOxi[3] = elementoNM.getNO3();
                        NumerosOxi[7] = elementoNM.getIM3();
                        break;
                    case 2:
                        NumerosOxi[3] = elementoNM.getNO4();
                        NumerosOxi[7] = elementoNM.getIM4();
                        break;
                }
        }
    }


}
