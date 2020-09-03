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

    private Button btn_aleatorio, btn_personalizado;
    List<ElementoEntity> allElementos;
    private ElementoViewModel melementoviewModel;
    private int ids[]=new int[2];
    private   int NumerosOxi []= new int [8];
    private ElementoEntity ElementoM,ElementoNM;
    private int control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        melementoviewModel = new ViewModelProvider(this).get(ElementoViewModel.class);
        melementoviewModel.getAllElementos().observe(this, allEllementos -> setelementos(allEllementos));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_juego);
        btn_aleatorio=findViewById(R.id.Bt_aleatorio);
        btn_personalizado=findViewById(R.id.Bt_personalizado);

        btn_aleatorio.setOnClickListener(this);
        btn_personalizado.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.Bt_aleatorio){
            control=getIntent().getIntExtra("valor",control);
           if(control==1) {
                sorteo();
                Intent jugar = new Intent(this, tablero_terciarios.class);
                jugar.putExtra("numeros de Oxidacion", NumerosOxi);
                jugar.putExtra("ids", ids);
                jugar.putExtra("BiTer",control);
                startActivity(jugar);
                finish();
            }else if(control==2){
               sorteo();
               Intent jugar = new Intent(this, Tablero.class);
               jugar.putExtra("numeros de Oxidacion", NumerosOxi);
               jugar.putExtra("ids", ids);
               jugar.putExtra("BiTer",control);
               startActivity(jugar);
               finish();
           }else if(control==3){
               sorteo();
               Intent jugar = new Intent(this, Tablero.class);
               jugar.putExtra("numeros de Oxidacion", NumerosOxi);
               jugar.putExtra("ids", ids);
               jugar.putExtra("BiTer",control);
               startActivity(jugar);
               finish();
           }

        }
        if (v.getId()==R.id.Bt_personalizado){
            control=getIntent().getIntExtra("valor",control);
            Intent personalizado = new Intent(this, tablaPeriodica.class);
            personalizado.putExtra("valor",control);
            startActivity(personalizado);
            finish();

        }

    }
    private void setelementos(List<ElementoEntity> items) {
        this.allElementos = items;
    }

    private void sorteo(){
        int azar= (int) Math.floor(Math.random() * 10);
        ElementoM=allElementos.get(azar);
        ids[0]=ElementoM.getId();
        NumerosOxi[0]=ElementoM.getNO1();
        NumerosOxi[1]=ElementoM.getNO2();
        NumerosOxi[4]=ElementoM.getIM1();
        NumerosOxi[5]=ElementoM.getIM2();

        azar = (int) Math.floor(Math.random() * (22 - 10) + 10);
            if (azar==17|azar==13){
                azar=15;
            }
        ElementoNM=allElementos.get(azar);
        ids[1]=ElementoNM.getId();
        NumerosOxi[2]=ElementoNM.getNO1();
        NumerosOxi[6]=ElementoNM.getIM1();

        if(azar==10|azar==11|azar==14|azar==15){
            azar = (int) Math.floor(Math.random() * 2);

            switch (azar) {
                case 0:
                    NumerosOxi[3] = ElementoNM.getNO2();
                    NumerosOxi[7] = ElementoNM.getIM2();
                    break;
                case 1:
                    NumerosOxi[3] = ElementoNM.getNO3();
                    NumerosOxi[7] = ElementoNM.getIM3();
                    break;

            }
        }else {

            azar = (int) Math.floor(Math.random() * 3);

                switch (azar) {
                    case 0:
                        NumerosOxi[3] = ElementoNM.getNO2();
                        NumerosOxi[7] = ElementoNM.getIM2();
                        break;
                    case 1:
                        NumerosOxi[3] = ElementoNM.getNO3();
                        NumerosOxi[7] = ElementoNM.getIM3();
                        break;
                    case 2:
                        NumerosOxi[3] = ElementoNM.getNO4();
                        NumerosOxi[7] = ElementoNM.getIM4();
                        break;
                }


        }
    }


}
