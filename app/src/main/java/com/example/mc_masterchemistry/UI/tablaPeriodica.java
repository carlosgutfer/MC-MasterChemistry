package com.example.mc_masterchemistry.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mc_masterchemistry.ElementoViewModel;
import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.UI.tablero.Tablero;
import com.example.mc_masterchemistry.UI.terciarios.tablero_terciarios;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;


import java.util.List;

import static android.view.View.INVISIBLE;

public class tablaPeriodica extends AppCompatActivity implements View.OnClickListener  {

    private Button jugar;

    private Button hierro, cobalto, niquel, cobre, paladio, platino, oro, mercurio, talio, estanno, fosforo, azufre,
            cloro, arsenico, selenio, bromo, antimonio, teluro, iodo, astato;

    private ImageButton M1, M2, M3, M4, NM1, NM2, NM3, NM4;
    private TextView metal, Nometal;
    private ImageButton eleccion1, eleccion2, eleccion3, eleccion4;

    private ElementoViewModel melementoviewModel;
    List<ElementoEntity> allElementos;

    private int ids[]=new int[2];
    private int NumerosOxi []= new int [8];
    private int idm , idNm;
    int control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_periodica);

        melementoviewModel = new ViewModelProvider(this).get(ElementoViewModel.class);
        melementoviewModel.getAllElementos().observe(this, allEllementos -> setelementos(allEllementos));




        jugar=findViewById(R.id.btn_jugar);
        metal = findViewById(R.id.TV_metal);
        Nometal = findViewById(R.id.TV_NoMetal);

        M1 = findViewById(R.id.IB_m1);
        M2 = findViewById(R.id.IB_m2);
        M3 = findViewById(R.id.IB_m3);
        M4 = findViewById(R.id.IB_m4);
        NM1 = findViewById(R.id.IB_NM1);
        NM2 = findViewById(R.id.IB_NM2);
        NM3 = findViewById(R.id.IB_NM3);
        NM4 = findViewById(R.id.IB_NM4);


        eleccion1 = findViewById(R.id.IV_eleccion1);
        eleccion2 = findViewById(R.id.IV_eleccion2);
        eleccion3 = findViewById(R.id.IV_eleccion3);
        eleccion4 = findViewById(R.id.IV_eleccion4);



        hierro = findViewById(R.id.bt_Fe);
        cobalto = findViewById(R.id.bt_Co);
        niquel = findViewById(R.id.bt_Ni);
        cobre = findViewById(R.id.bt_Cu);
        paladio = findViewById(R.id.bt_Pd);
        platino = findViewById(R.id.bt_Pt);
        oro = findViewById(R.id.bt_Au);
        mercurio = findViewById(R.id.bt_Hg);
        talio = findViewById(R.id.bt_talio);
        estanno = findViewById(R.id.bt_Sn);
        fosforo = findViewById(R.id.bt_P);
        azufre = findViewById(R.id.bt_S);
        cloro = findViewById(R.id.bt_Cl);
        arsenico = findViewById(R.id.bt_As);
        selenio = findViewById(R.id.bt_Se);
        bromo = findViewById(R.id.bt_Br);
        antimonio = findViewById(R.id.bt_Sb);
        teluro = findViewById(R.id.bt_Te);
        iodo = findViewById(R.id.bt_I);
        astato = findViewById(R.id.bt_At);

        hierro.setOnClickListener(this);
        cobalto.setOnClickListener(this);
        niquel.setOnClickListener(this);
        cobre.setOnClickListener(this);
        paladio.setOnClickListener(this);
        platino.setOnClickListener(this);
        oro.setOnClickListener(this);
        mercurio.setOnClickListener(this);
        talio.setOnClickListener(this);
        estanno.setOnClickListener(this);
        fosforo.setOnClickListener(this);
        azufre.setOnClickListener(this);
        cloro.setOnClickListener(this);
        arsenico.setOnClickListener(this);
        selenio.setOnClickListener(this);
        bromo.setOnClickListener(this);
        antimonio.setOnClickListener(this);
        teluro.setOnClickListener(this);
        iodo.setOnClickListener(this);
        astato.setOnClickListener(this);

        M1.setOnClickListener(this);
        M2.setOnClickListener(this);
        M3.setOnClickListener(this);
        M4.setOnClickListener(this);


        NM1.setOnClickListener(this);
        NM2.setOnClickListener(this);
        NM3.setOnClickListener(this);
        NM4.setOnClickListener(this);

        jugar.setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("eleccion",ids);
        outState.putIntArray("NumerosOxi",NumerosOxi);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        ids=savedInstanceState.getIntArray("eleccion");
        NumerosOxi=savedInstanceState.getIntArray("NumerosOxi");

        if (NumerosOxi[0]!=0) {
            metal.setText(allElementos.get(ids[0]).getNombre());

            eleccion1.setVisibility(View.VISIBLE);
            eleccion2.setVisibility(View.VISIBLE);
            eleccion1.setImageResource(NumerosOxi[4]);
            eleccion2.setImageResource(NumerosOxi[5]);

            M1.setVisibility(View.VISIBLE);
            M2.setVisibility(View.VISIBLE);
            M1.setImageResource(NumerosOxi[4]);
            M2.setImageResource(NumerosOxi[5]);
            if (allElementos.get(ids[0]).getIM3()!=0){
                M3.setVisibility(View.VISIBLE);
                M3.setImageResource(allElementos.get(ids[0]).getIM3());
            }else{
                M3.setVisibility(INVISIBLE);
            }
            if(allElementos.get(ids[0]).getIM4()!=0){
                M4.setVisibility(View.VISIBLE);
                M4.setImageResource(allElementos.get(ids[0]).getIM4());
            }else{
                M4.setVisibility(INVISIBLE);
            }
        }
        if (NumerosOxi[2]!=0){
            Nometal.setText(allElementos.get(ids[1]).getNombre());


            eleccion3.setVisibility(View.VISIBLE);
            eleccion4.setVisibility(View.VISIBLE);
            eleccion3.setImageResource(NumerosOxi[6]);
            eleccion4.setImageResource(NumerosOxi[7]);


            NM1.setVisibility(View.VISIBLE);
            NM2.setVisibility(View.VISIBLE);
            NM1.setImageResource(NumerosOxi[6]);
            NM2.setImageResource(NumerosOxi[7]);
        }
        if (allElementos.get(ids[1]).getIM3()!=0){
            NM3.setVisibility(View.VISIBLE);
            NM3.setImageResource(allElementos.get(ids[1]).getIM3());
        }else{
            NM3.setVisibility(INVISIBLE);
        }
        if(allElementos.get(ids[1]).getIM4()!=0){
            NM4.setVisibility(View.VISIBLE);
           NM4.setImageResource(allElementos.get(ids[1]).getIM4());
        }else{
            NM4.setVisibility(INVISIBLE);
        }
    }

    private void setelementos(List<ElementoEntity> items) {
        this.allElementos=items;
    }

    @Override
    public void onClick(View v) {

        //Metales
        if (v.getId() == R.id.bt_Fe) {

            idm = allElementos.get(0).getId();
            DatosElemento(idm);

        }
        if (v.getId() == R.id.bt_Co) {
            idm=allElementos.get(1).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_Ni) {
            idm = allElementos.get(2).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_Cu) {
            idm = allElementos.get(3).getId();
            DatosElemento(idm);

        }
        if (v.getId() == R.id.bt_Pd) {

            idm = allElementos.get(4).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_Pt) {

            idm = allElementos.get(5).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_Au) {

            idm = allElementos.get(6).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_Hg) {

            idm = allElementos.get(7).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_talio) {

            idm = allElementos.get(8).getId();
            DatosElemento(idm);
        }
        if (v.getId() == R.id.bt_Sn) {

            idm = allElementos.get(9).getId();
            DatosElemento(idm);
        }


        //NOmetales

        if (v.getId() == R.id.bt_Sb) {
            idNm=allElementos.get(10).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_As) {
            idNm=allElementos.get(11).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_P) {

            idNm=allElementos.get(12).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_Te) {

            idNm=allElementos.get(14).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_Se) {

            idNm=allElementos.get(15).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_S) {
            idNm=allElementos.get(16).getId();
            DatosElemento(idNm);
        }

        if (v.getId() == R.id.bt_At) {
            idNm=allElementos.get(18).getId();
            DatosElemento(idNm);
        }

        if (v.getId() == R.id.bt_I) {
            idNm=allElementos.get(19).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_Br) {
            idNm=allElementos.get(20).getId();
            DatosElemento(idNm);
        }
        if (v.getId() == R.id.bt_Cl) {
            idNm=allElementos.get(21).getId();
            DatosElemento(idNm);
        }

        //Elecciones
        if (v.getId()==R.id.IB_NM2){
            eleccion4.setImageResource(allElementos.get(ids[1]).getIM2());
            NumerosOxi[3]=allElementos.get(ids[1]).getNO2();
            NumerosOxi[7]=allElementos.get(ids[1]).getIM2();
        }
        if (v.getId()==R.id.IB_NM3){
            eleccion4.setImageResource(allElementos.get(ids[1]).getIM3());
            NumerosOxi[3]=allElementos.get(ids[1]).getNO3();
            NumerosOxi[7]=allElementos.get(ids[1]).getIM3();
        }
        if (v.getId()==R.id.IB_NM4){
            eleccion4.setImageResource(allElementos.get(ids[1]).getIM4());
            NumerosOxi[3]=allElementos.get(ids[1]).getNO4();
            NumerosOxi[7]=allElementos.get(ids[1]).getIM4();
        }

        if (v.getId()==R.id.IB_m1){
                eleccion2.setImageResource(allElementos.get(ids[0]).getIM1());
                NumerosOxi[1]=allElementos.get(ids[0]).getNO1();
                NumerosOxi[5]=allElementos.get(ids[0]).getIM1();
        }
        if (v.getId()==R.id.IB_m2){
            eleccion1.setImageResource(allElementos.get(ids[0]).getIM2());
            NumerosOxi[0]=allElementos.get(ids[0]).getNO2();
            NumerosOxi[4]=allElementos.get(ids[0]).getIM2();
        }
        if (v.getId()==R.id.IB_m3){
            eleccion1.setImageResource(allElementos.get(ids[0]).getIM3());
            NumerosOxi[0]=allElementos.get(ids[0]).getNO3();
            NumerosOxi[4]=allElementos.get(ids[0]).getIM3();
        }
        if (v.getId()==R.id.IB_m4){
            eleccion2.setImageResource(allElementos.get(ids[0]).getIM4());
            NumerosOxi[1]=allElementos.get(ids[0]).getNO4();
            NumerosOxi[5]=allElementos.get(ids[0]).getIM4();
        }

        if(v.getId()==R.id.btn_jugar) {

            control = getIntent().getIntExtra("valor", control);
            if (control == 1) {
                if (NumerosOxi[1] != NumerosOxi[0]) {
                    Intent jugar = new Intent(this, tablero_terciarios.class);
                    jugar.putExtra("numeros de Oxidacion", NumerosOxi);
                    jugar.putExtra("ids", ids);
                    startActivity(jugar);
                    finish();
                } else {
                    Toast.makeText(this, "Los números de Oxidación deben ser distintos en el metal", Toast.LENGTH_LONG).show();
                }
            } else if (control == 2) {
                if (NumerosOxi[1] != NumerosOxi[0]) {
                    Intent jugar = new Intent(this, Tablero.class);
                    jugar.putExtra("numeros de Oxidacion", NumerosOxi);
                    jugar.putExtra("ids", ids);
                    startActivity(jugar);
                    finish();
                } else {
                    Toast.makeText(this, "Los números de Oxidación deben ser distintos en el metal", Toast.LENGTH_LONG).show();
                }
            } else if (control == 3) {
                if (NumerosOxi[1] != NumerosOxi[0]) {
                    Intent jugar = new Intent(this, Tablero.class);
                    jugar.putExtra("numeros de Oxidacion", NumerosOxi);
                    jugar.putExtra("ids", ids);
                    jugar.putExtra("BiTer",control);
                    startActivity(jugar);
                    finish();
                } else {
                    Toast.makeText(this, "Los números de Oxidación deben ser distintos en el metal", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void DatosElemento(int id){
        if (id<10 ){
            ids[0]=id;

            NumerosOxi[0]=allElementos.get(id).getNO1();
            NumerosOxi[1]=allElementos.get(id).getNO2();
            NumerosOxi[4]=allElementos.get(id).getIM1();
            NumerosOxi[5]=allElementos.get(id).getIM2();

            metal.setText(allElementos.get(id).getNombre());

            eleccion1.setVisibility(View.VISIBLE);
            eleccion2.setVisibility(View.VISIBLE);
            eleccion1.setImageResource(allElementos.get(id).getIM1());
            eleccion2.setImageResource(allElementos.get(id).getIM2());

            M1.setVisibility(View.VISIBLE);
            M2.setVisibility(View.VISIBLE);
            M1.setImageResource(allElementos.get(id).getIM1());
            M2.setImageResource(allElementos.get(id).getIM2());

            if (M3.getVisibility() == INVISIBLE) {
                M3.setVisibility(View.VISIBLE);
            }
            if (M4.getVisibility() == INVISIBLE) {
                M4.setVisibility(View.VISIBLE);
            }

            if (allElementos.get(id).getIM3()==0){
                M3.setVisibility(INVISIBLE);
                M4.setVisibility(INVISIBLE);
            } else{
                M3.setImageResource(allElementos.get(id).getIM3());
                M4.setImageResource(allElementos.get(id).getIM4());
            }

        }
        else{
            ids[1]=id;



            NumerosOxi[2]=allElementos.get(id).getNO1();
            NumerosOxi[3]=allElementos.get(id).getNO2();
            NumerosOxi[6]=allElementos.get(id).getIM1();
            NumerosOxi[7]=allElementos.get(id).getIM2();

            Nometal.setText(allElementos.get(id).getNombre());

            eleccion3.setVisibility(View.VISIBLE);
            eleccion4.setVisibility(View.VISIBLE);
            eleccion3.setImageResource(allElementos.get(id).getIM1());
            eleccion4.setImageResource(allElementos.get(id).getIM2());

            NM1.setVisibility(View.VISIBLE);
            NM2.setVisibility(View.VISIBLE);
            NM1.setImageResource(allElementos.get(id).getIM1());
            NM2.setImageResource(allElementos.get(id).getIM2());


            if (NM3.getVisibility() == INVISIBLE) {
                NM3.setVisibility(View.VISIBLE);
            }

            if (NM4.getVisibility() == INVISIBLE) {
                NM4.setVisibility(View.VISIBLE);
            }

            if (allElementos.get(id).getIM4()==0&allElementos.get(id).getIM3()==0){
                NM3.setVisibility(INVISIBLE);
                NM4.setVisibility(INVISIBLE);
            } else if (allElementos.get(id).getIM4()==0){
                NM4.setVisibility(INVISIBLE);
                NM3.setImageResource(allElementos.get(id).getIM3());
            } else {
                NM3.setImageResource(allElementos.get(id).getIM3());
                NM4.setImageResource(allElementos.get(id).getIM4());
            }

            if(id==21){
                Nometal.setTextColor(getResources().getColor(R.color.verdeLiquido));
            }else if (id==20){
                Nometal.setTextColor(getResources().getColor(R.color.RojoGas));
            }
            else{
                Nometal.setTextColor(getResources().getColor(R.color.negro));
            }


        }
    }




}



