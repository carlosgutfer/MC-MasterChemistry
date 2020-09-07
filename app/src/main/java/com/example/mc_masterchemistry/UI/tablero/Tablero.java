package com.example.mc_masterchemistry.UI.tablero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mc_masterchemistry.ElementoViewModel;
import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.UI.fin_del_juego;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;
import com.example.mc_masterchemistry.ui.tablero.TableroFragment;

import java.util.ArrayList;
import java.util.List;

public class Tablero extends AppCompatActivity implements View.OnClickListener {

    private Button limpiar,fusion,barajar,peroxido;
    public ArrayList<ElementoEntity> baraja = new ArrayList<>(64);
    private int BiTer;

    private int O=0, H=0;
    private int imagen=0;
    private int NO1,NO=0,NOm,NOnm,NOsuma=0;
    private int NCborrado=0;
    private ArrayList<Integer> calculo = new ArrayList();
    private int gemas=0, gemasFin=0;
    private int  idm=10, idNm=0, idHuO=0;
    private int idElemento=0;
    private boolean CombinacionValida=true;
    private boolean AñadirValido=true;
    private boolean minNumero=true;

    private String mensaje;
    private String StElemento;
    private ArrayList<String> elementosCreados = new ArrayList<>();
    private ArrayList<Integer>idsCompuestosCreados = new ArrayList<>();
    private ArrayList<Integer>listPeroxidos=new ArrayList<>();
    private int mismoelemento=0;
    private int cartasM=0, cartasOuH=0, cartasNM=0;
    private int CBMetal=0, CBNoMetal=0;
    private int suma=0;
    private int conperoxido=0;

    List<ElementoEntity> allElementos;
    private ElementoViewModel melementoviewModel;

    private CombinacionQuimica combi = new CombinacionQuimica(this);


    private int NumerosOxi []= new int [8];
    private int Ids[]=new int [2];

    private ArrayList<Integer> ListE1NO1 = new ArrayList<>();
    private ArrayList<Integer> ListE1NO2= new ArrayList<>();
    private ArrayList<Integer> ListE2NO1 = new ArrayList<>();
    private ArrayList<Integer> ListE2NO2 = new ArrayList<>();


    private ImageButton  C1NO1,C1NO2,C2NO1,C2NO2,C3NO1,C3NO2,C4NO1,C4NO2;

    private ImageButton E1NO1,E1NO2,E2NO1,E2NO2;

    private ImageButton finNO,fin1,fin2,fin3,fin4,NORep,NORep2;

    private TextView IVgemas,NombreMetal,NombreNometal;

    private ImageButton elementos;
    private TextView listaElementos;
    private EditText entradaCompuesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        if (height<=1280&&width<=768) {
            setContentView(R.layout.tablero_pequeno_activity);
        }else{
            setContentView(R.layout.tablero_activity);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TableroFragment.newInstance())
                    .commitNow();
        }

        melementoviewModel = new ViewModelProvider(this).get(ElementoViewModel.class);
        melementoviewModel.getAllElementos().observe(this, new Observer<List<ElementoEntity>>() {
            @Override
            public void onChanged(@Nullable List<ElementoEntity> allEllementos) {
                setelementos(allEllementos);
            }
        });

        elementos=findViewById(R.id.Btn_elementosCreados);
        listaElementos=findViewById(R.id.TV_elementosCreados);

        E1NO1 = findViewById(R.id.btn_E1NO1);
        E1NO2 = findViewById(R.id.btn_E1NO2);
        E2NO1 = findViewById(R.id.btn_E2NO1);
        E2NO2 = findViewById(R.id.btn_E2NO2);

        C1NO1 = findViewById(R.id.carta1NO1);
        C1NO2 = findViewById(R.id.carta1NO2);
        C2NO1 = findViewById(R.id.carta2NO1);
        C2NO2 = findViewById(R.id.carta2NO2);
        C3NO1 = findViewById(R.id.carta3NO1);
        C3NO2 = findViewById(R.id.carta3NO2);
        C4NO1 = findViewById(R.id.carta4NO1);
        C4NO2 = findViewById(R.id.carta4NO2);


        NORep=findViewById(R.id.IV_NOERep1);
        NORep2=findViewById(R.id.IV_NOERep2);
        finNO = findViewById(R.id.IV_finNO);
        fin1 = findViewById(R.id.IV_fin1);
        fin2 = findViewById(R.id.IV_fin2);
        fin3 = findViewById(R.id.IV_fin3);
        fin4 = findViewById(R.id.IV_fin4);


        NombreMetal=findViewById(R.id.TVnombreM);
        NombreNometal=findViewById(R.id.Tv_nombreNometal);
        IVgemas = findViewById(R.id.IV_gemas);
        entradaCompuesto=findViewById(R.id.ET_entradaCompuesto);

        peroxido=findViewById(R.id.Bt_peroxido);
        limpiar = findViewById(R.id.btn_limpiar);
        fusion = findViewById(R.id.btn_fusion);
        barajar = findViewById(R.id.btn_barajar);


        E1NO1.setOnClickListener(this);
        E1NO2.setOnClickListener(this);
        E2NO1.setOnClickListener(this);
        E2NO2.setOnClickListener(this);

        C1NO1.setOnClickListener(this);
        C1NO2.setOnClickListener(this);
        C2NO1.setOnClickListener(this);
        C2NO2.setOnClickListener(this);
        C3NO1.setOnClickListener(this);
        C3NO2.setOnClickListener(this);
        C4NO1.setOnClickListener(this);
        C4NO2.setOnClickListener(this);

        peroxido.setOnClickListener(this);
        limpiar.setOnClickListener(this);
        fusion.setOnClickListener(this);
        barajar.setOnClickListener(this);

        elementos.setOnClickListener(this);

    }
        @Override
        protected void onStart () {
            super.onStart();
            Intent();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putIntArray("eleccion",Ids);
            outState.putIntArray("NumerosOxi",NumerosOxi);
            outState.putIntegerArrayList("ListE1NO1",ListE1NO1);
            outState.putIntegerArrayList("ListE1NO2",ListE1NO2);
            outState.putIntegerArrayList("ListE2NO1",ListE2NO1);
            outState.putIntegerArrayList("ListE2NO2",ListE2NO2);
            outState.putInt("BiTer",BiTer);
            outState.putStringArrayList("CompuestosCreados", elementosCreados);
            outState.putInt("gemasCreadas",gemasFin);
            outState.putIntegerArrayList("IdsCompuestosCreados",idsCompuestosCreados);
            outState.putIntegerArrayList("controlPeroxidos",listPeroxidos);

        }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Ids=savedInstanceState.getIntArray("eleccion");
        NumerosOxi=savedInstanceState.getIntArray("NumerosOxi");
        ListE1NO1=savedInstanceState.getIntegerArrayList("ListE1NO1");
        ListE1NO2=savedInstanceState.getIntegerArrayList("ListE1NO2");
        ListE2NO1=savedInstanceState.getIntegerArrayList("ListE2NO1");
        ListE2NO2=savedInstanceState.getIntegerArrayList("ListE2NO2");
        BiTer=savedInstanceState.getInt("BiTer");
        elementosCreados=savedInstanceState.getStringArrayList("CompuestosCreados");
        gemasFin=savedInstanceState.getInt("gemasCreadas");
        idsCompuestosCreados=savedInstanceState.getIntegerArrayList("IdsCompuestosCreados");
        listPeroxidos=savedInstanceState.getIntegerArrayList("controlPeroxidos");

        E1NO1.setImageResource(NumerosOxi[4]);
        E1NO2.setImageResource(NumerosOxi[5]);
        E2NO1.setImageResource(NumerosOxi[6]);
        E2NO2.setImageResource(NumerosOxi[7]);

        IVgemas.setText(String.valueOf(gemasFin));
        listaElementos.setText(mostrarElementos());
    }

        private void setelementos(List<ElementoEntity> items) {
        this.allElementos=items;
        crearBaraja(allElementos);
        repartir(baraja);
    }

    public void onClick(View v) {


        if(v.getId()==R.id.Btn_elementosCreados){

            if(listaElementos.getVisibility()==View.INVISIBLE){
                listaElementos.setVisibility(View.VISIBLE);
            }else{
                listaElementos.setVisibility(View.INVISIBLE);
            }

        }

        if(v.getId()==R.id.btn_E1NO1){
            CBMetal++;
            idm=Ids[0];
           cartasM= combi.sumaMetal(cartasM);
            if (NO==0) {
                finNO.setImageResource(NumerosOxi[4]);
                NO = ListE1NO1.get(0);
                E1NO2.setVisibility(View.INVISIBLE);
                idElemento=R.id.btn_E1NO1;
                if(calculo.size()>0){
                    for (int x=0; x<calculo.size();x++) {
                        if (calculo.get(x)!=0) {
                            ocultarElementos(idElemento, calculo.get(x));
                        }
                    }
                }
                mismoelemento++;
            } else if (calculo.size()<ListE1NO1.size()&ListE1NO1.get(0)!=NO){
                NOm = ListE1NO1.get(0);
                 metalNometal(NOm, R.id.btn_E1NO1);
             }else if(ListE1NO1.get(0)==NO&mismoelemento<ListE1NO1.size()){
                mismoElemento(idElemento);
            }

         }
        if(v.getId()==R.id.btn_E1NO2){
            CBMetal++;
            cartasM=combi.sumaMetal(cartasM);
            idm=Ids[0];
            if (NO==0) {
                    finNO.setImageResource(NumerosOxi[5]);
                    NO = ListE1NO2.get(0);
                    E1NO1.setVisibility(View.INVISIBLE);
                    idElemento=R.id.btn_E1NO2;
                if(calculo.size()>0){
                    for (int x=0; x<calculo.size();x++) {
                        if (calculo.get(x)!=0) {
                            ocultarElementos(idElemento, calculo.get(x));
                        }
                    }
                }
                mismoelemento++;
            }else if (calculo.size()<ListE1NO2.size()&ListE1NO2.get(0)!=NO ){

                NOm=ListE1NO2.get(0);
                metalNometal(NOm,R.id.btn_E1NO2 );
            } else if(ListE1NO2.get(0)==NO&mismoelemento<ListE1NO1.size()){

                mismoElemento(idElemento);
            }
        }
        if(v.getId()==R.id.btn_E2NO1){
            CBNoMetal++;
            cartasNM=combi.sumaNoMetal(cartasNM);
            idNm=Ids[1];
         if(NO==0) {
                 finNO.setImageResource(NumerosOxi[6]);
                 NO = ListE2NO1.get(0);
                 E2NO2.setVisibility(View.INVISIBLE);
                 idElemento=R.id.btn_E2NO1;
             if(calculo.size()>0){
                 for (int x=0; x<calculo.size();x++) {
                     if (calculo.get(x)!=0) {
                         ocultarElementos(idElemento, calculo.get(x));
                     }
                 }
             }
             mismoelemento++;
         }else if (calculo.size()<ListE2NO1.size()&ListE2NO1.get(0)!=NO ){
             NOnm=ListE2NO1.get(0);
             metalNometal(NOnm,R.id.btn_E2NO1 );
         }else if(ListE2NO1.get(0)==NO&mismoelemento<ListE2NO1.size()){
             mismoElemento(idElemento);
         }
        }
        if(v.getId()==R.id.btn_E2NO2){
            CBNoMetal++;
            cartasNM=combi.sumaNoMetal(cartasNM);
            idNm=Ids[1];
            if(NO==0) {
                    finNO.setImageResource(NumerosOxi[7]);
                    NO = ListE2NO2.get(0);
                    E2NO1.setVisibility(View.INVISIBLE);
                    idElemento=R.id.btn_E2NO2;
                if(calculo.size()>0){
                    for (int x=0; x<calculo.size();x++) {
                        if (calculo.get(x)!=0) {
                            ocultarElementos(idElemento, calculo.get(x));
                        }
                    }
                }
                mismoelemento++;
            }else if (calculo.size()<ListE2NO2.size()&ListE2NO2.get(0)!=NO){
                NOnm=ListE2NO2.get(0);
                metalNometal(NOnm,R.id.btn_E2NO2 );
            }else if(ListE2NO2.get(0)==NO&mismoelemento<ListE2NO1.size()){
                mismoElemento(idElemento);
            }
        }

        if(v.getId()==R.id.carta1NO1){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin1.setImageResource(baraja.get(0).getIM1());
                añadircalculo(R.id.carta1NO1);
                ocultarElementos(idElemento,baraja.get(0).getNO1());


        }
        if(v.getId()==R.id.carta1NO2){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin1.setImageResource(baraja.get(0).getIM2());
                añadircalculo(R.id.carta1NO2);
                ocultarElementos(idElemento,baraja.get(0).getNO2());

        }
        if(v.getId()==R.id.carta2NO1){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin2.setImageResource(baraja.get(1).getIM1());
                añadircalculo(R.id.carta2NO1);
               ocultarElementos(idElemento,baraja.get(1).getNO1());

        }
        if(v.getId()==R.id.carta2NO2){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin2.setImageResource(baraja.get(1).getIM2());
                añadircalculo(R.id.carta2NO2);
                ocultarElementos(idElemento,baraja.get(1).getNO2());

        }
        if(v.getId()==R.id.carta3NO1){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin3.setImageResource(baraja.get(2).getIM1());
                añadircalculo(R.id.carta3NO1);
                ocultarElementos(idElemento,baraja.get(2).getNO1());

        }
        if(v.getId()==R.id.carta3NO2){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin3.setImageResource(baraja.get(2).getIM2());
                añadircalculo(R.id.carta3NO2);
                ocultarElementos(idElemento,baraja.get(2).getNO2());




        }
        if(v.getId()==R.id.carta4NO1){
            cartasOuH=combi.sumaHuO(cartasOuH);

                fin4.setImageResource(baraja.get(3).getIM1());
                añadircalculo(R.id.carta4NO1);
                ocultarElementos(idElemento,baraja.get(3).getNO1());

        }
        if(v.getId()==R.id.carta4NO2){
            cartasOuH=combi.sumaHuO(cartasOuH);
                fin4.setImageResource(baraja.get(3).getIM2());
                añadircalculo(R.id.carta4NO2);
                ocultarElementos(idElemento,baraja.get(3).getNO2());

        }

        if(v.getId()==R.id.btn_fusion){
            fusion();
        }
        if(v.getId()==R.id.btn_barajar){
            descartar();
            }
        if (v.getId()==R.id.btn_limpiar){
            limpiar();

        }
        if(v.getId()==R.id.Bt_peroxido){
            peroxidos( baraja);
        }
    }


    //Metodos control grafico
    private void crearBaraja(List<ElementoEntity> carta){
        H=0; O=0;
        Ids=getIntent().getIntArrayExtra("ids");
        NombreMetal.setText(allElementos.get(Ids[0]).getNombre());
        NombreNometal.setText(allElementos.get(Ids[1]).getNombre());
        if (Ids[1]==21){
            NombreNometal.setTextColor(getResources().getColor(R.color.verdeLiquido));
        }else if(Ids[1]==20){
            NombreNometal.setTextColor(getResources().getColor(R.color.RojoGas));
        }

        for (int fila=0;fila<65;fila++) {
            int azar = (int) Math.floor(Math.random() * 2);
            if (azar == 1 & H < 32) {
                baraja.add(carta.get(13));
                H++;
            } else if (azar == 1 & H > 31) {
                baraja.add(carta.get(17));
                O++;
            } else if (azar == 0 & 0 < 32) {
                baraja.add(carta.get(17));
                O++;
            } else if (azar == 0 & O > 31) {
                baraja.add(carta.get(13));
                H++;
            }
        }
    }
    private void repartir(ArrayList<ElementoEntity> baraja) {
        imagen=0;
        if (ListE1NO1.size()==0|ListE1NO2.size()==0){
            E1NO1.setVisibility(View.INVISIBLE);
            E1NO2.setVisibility(View.INVISIBLE);
        }
        else if(ListE2NO1.size()==0||ListE2NO2.size()==0){
            E2NO1.setVisibility(View.INVISIBLE);
            E2NO2.setVisibility(View.INVISIBLE);
        }
        for (int x =0; x <4; x++) {

            NO1 = baraja.get(x).getNO1();


            if(NO1==-1&imagen==0){
                C1NO1.setImageResource(baraja.get(x).getIM1());
                C1NO2.setImageResource(baraja.get(x).getIM2());

            }

            else  if(NO1==-1&&imagen==1){
                C2NO1.setImageResource(baraja.get(x).getIM1());
                C2NO2.setImageResource(baraja.get(x).getIM2());

            }

            else  if(NO1==-1&&imagen==2){
                C3NO1.setImageResource(baraja.get(x).getIM1());
                C3NO2.setImageResource(baraja.get(x).getIM2());

            }

            else if(NO1==-1&&imagen==3){
                C4NO1.setImageResource(baraja.get(x).getIM1());
                C4NO2.setImageResource(baraja.get(x).getIM2());

            }

            if(NO1==-2&imagen==0){
                C1NO1.setImageResource(baraja.get(x).getIM1());
                C1NO2.setVisibility(View.INVISIBLE);

            }

            else  if(NO1==-2&&imagen==1){
                C2NO1.setImageResource(baraja.get(x).getIM1());
                C2NO2.setVisibility(View.INVISIBLE);

            }

            else  if(NO1==-2&&imagen==2){
                C3NO1.setImageResource(baraja.get(x).getIM1());
                C3NO2.setVisibility(View.INVISIBLE);

            }

            else if(NO1==-2&&imagen==3){
                C4NO1.setImageResource(baraja.get(x).getIM1());
                C4NO2.setVisibility(View.INVISIBLE);

            }
            imagen++;
        }

    }
    private void Intent(){
        Ids=getIntent().getIntArrayExtra("ids");
        NumerosOxi=getIntent().getIntArrayExtra("numeros de Oxidacion");
        BiTer=getIntent().getIntExtra("BiTer",BiTer);
        for(int i=0;i<3;i++) {
            ListE1NO1.add(NumerosOxi[0]);
            ListE1NO2.add(NumerosOxi[1]);
            ListE2NO1.add(NumerosOxi[2]);
            ListE2NO2.add(NumerosOxi[3]);
        }
        E1NO1.setImageResource(NumerosOxi[4]);
        E1NO2.setImageResource(NumerosOxi[5]);
        E2NO1.setImageResource(NumerosOxi[6]);
        E2NO2.setImageResource(NumerosOxi[7]);

    }
    private void ocultarElementos(int IdElemento,int NO) {
        if(NO==-1|NO==1){
            idHuO=allElementos.get(13).getId();
        }else if (NO==-2){
            idHuO=allElementos.get(17).getId();
        }else{
            idHuO=0;
        }

        imagen=0;
        for (int i=0; i<4;i++) {
            if(baraja.get(i).getNO1()!=NO&baraja.get(i).getNO2()!=NO){
                switch (imagen) {
                    case 0:
                        C1NO1.setVisibility(View.INVISIBLE);
                        C1NO2.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        C2NO1.setVisibility(View.INVISIBLE);
                        C2NO2.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        C3NO1.setVisibility(View.INVISIBLE);
                        C3NO2.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        C4NO1.setVisibility(View.INVISIBLE);
                        C4NO2.setVisibility(View.INVISIBLE);
                        break;
                }
            }
            imagen++;
        }
        switch (IdElemento) {
            case R.id.btn_E1NO1:
                E1NO2.setVisibility(View.INVISIBLE);
                E2NO1.setVisibility(View.INVISIBLE);
                E2NO2.setVisibility(View.INVISIBLE);
            break;
            case R.id.btn_E1NO2:
               E1NO1.setVisibility(View.INVISIBLE);
               E2NO1.setVisibility(View.INVISIBLE);
               E2NO2.setVisibility(View.INVISIBLE);
            break;
            case R.id.btn_E2NO1:
                E1NO1.setVisibility(View.INVISIBLE);
                E1NO2.setVisibility(View.INVISIBLE);
                E2NO2.setVisibility(View.INVISIBLE);
            break;
            case R.id.btn_E2NO2:
                E1NO1.setVisibility(View.INVISIBLE);
                E1NO2.setVisibility(View.INVISIBLE);
                E2NO1.setVisibility(View.INVISIBLE);
            break;
        }
    }

    //Metodos para fusión
    private void añadircalculo(int id){

        if(id==R.id.carta1NO1){

            if(calculo.size()<1) {
                calculo.add(0, baraja.get(0).getNO1());
            }
            else if (calculo.size()>=1){
                calculo.remove(0);
                calculo.add(0, baraja.get(0).getNO1());
            }
        }
        if(id==R.id.carta1NO2){
            if(calculo.size()<1){
                calculo.add(0,baraja.get(0).getNO2());
            }
            else if (calculo.size()>=1){
                calculo.remove(0);
                calculo.add(0, baraja.get(0).getNO2());
            }
        }
        if(id==R.id.carta2NO1){
            if (calculo.size() <1) {
                calculo.add(0,0);
                calculo.add(1, baraja.get(1).getNO1());
            }  else if (calculo.size()>=2) {
                calculo.remove(1);
                calculo.add(1, baraja.get(1).getNO1());
            }
            else if (calculo.size()==1){
                calculo.add(1,baraja.get(1).getNO1());
            }

        }
        if(id==R.id.carta2NO2){
            if (calculo.size() < 1) {
                calculo.add(0,0);
                calculo.add(1, baraja.get(1).getNO2());
            }  else if (calculo.size()>=2) {
                calculo.remove(1);
                calculo.add(1, baraja.get(1).getNO2());
            }
            else if (calculo.size()==1){
                calculo.add(1,baraja.get(1).getNO2());
            }
        }
        if(id==R.id.carta3NO1){
            if (calculo.size()<1){
                calculo.add(0,0);
                calculo.add(1,0);
                calculo.add(2,baraja.get(2).getNO1());
            }else if(calculo.size()==1){
                calculo.add(1,0);
                calculo.add(2,baraja.get(2).getNO1());
            }
            else if (calculo.size()==2){
                calculo.add(2,baraja.get(2).getNO1());
            }
            else if (calculo.size()>=3) {
                calculo.remove(2);
                calculo.add(2, baraja.get(2).getNO1());
            }

        }
        if(id==R.id.carta3NO2){
            if (calculo.size()<1){
                calculo.add(0,0);
                calculo.add(1,0);
                calculo.add(2,baraja.get(2).getNO2());
            }else if(calculo.size()==1){
                calculo.add(1,0);
                calculo.add(2,baraja.get(2).getNO2());
            }
            else if (calculo.size()==2){
                calculo.add(2,baraja.get(2).getNO2());
            }
            else if (calculo.size()>=3) {
                calculo.remove(2);
                calculo.add(2, baraja.get(2).getNO2());
            }
        }
        if(id==R.id.carta4NO1){
            if (calculo.size()<1){
                calculo.add(0,0);
                calculo.add(1,0);
                calculo.add(2,0);
                calculo.add(3, baraja.get(3).getNO1());
            }
            if (calculo.size()==1){
                calculo.add(1,0);
                calculo.add(2,0);
                calculo.add(3, baraja.get(3).getNO1());
            }
            if (calculo.size()==2){
                calculo.add(2,0);
                calculo.add(3, baraja.get(3).getNO1());
            }
            else if(calculo.size()==3){
                calculo.add(3, baraja.get(3).getNO1());
            }
            else if (calculo.size()==4){
                calculo.remove(3);
                calculo.add(3, baraja.get(3).getNO1());
            }

        }
        if(id==R.id.carta4NO2) {
            if (calculo.size()<1){
                calculo.add(0,0);
                calculo.add(1,0);
                calculo.add(2,0);
                calculo.add(3, baraja.get(3).getNO2());
            }
            if (calculo.size()==1){
                calculo.add(1,0);
                calculo.add(2,0);
                calculo.add(3, baraja.get(3).getNO2());
            }
            if (calculo.size()==2){
                calculo.add(2,0);
                calculo.add(3, baraja.get(3).getNO2());
            }
            else if (calculo.size()==3){
                calculo.add(3, baraja.get(3).getNO2());
            }
            else if (calculo.size()==4){
                calculo.remove(3);
                calculo.add(3, baraja.get(3).getNO2());
            }
        }
    }
    private void fusion(){
      for(int i =0; i<calculo.size();i++){
          if (calculo.get(i)!=0) {
              suma = suma + calculo.get(i);
                gemas++;
          }
      }
        suma=suma+NO+NOsuma;
        gemas++;
        if(NOsuma!=0){
            gemas++;
        }
        CombinacionValida=combi.combinacionesMetal(CombinacionValida,idm,idNm,idHuO,calculo);
        if (CombinacionValida){
            if (suma == 0 ) {
                if(NOsuma-NO==NO) {
                    minNumero = combi.minElementos(calculo, NO, NO); }
                if (NOsuma-NO==0){
                    minNumero = combi.minElementos(calculo, NO, NOsuma); }
                if(minNumero){
                String compuesto = entradaCompuesto.getText().toString();
                AñadirValido=combi.comprobarElementoList(StElemento,allElementos,elementosCreados,idm,idNm,idHuO,cartasM,cartasNM,cartasOuH,compuesto,idsCompuestosCreados);
                if(AñadirValido){
                            gemasFin = gemasFin + gemas;
                            setListPeroxidos(listPeroxidos);
                            combi.getList(elementosCreados);
                            combi.getIds(idsCompuestosCreados);
                            listaElementos.setText(mostrarElementos());
                            borrarElementosList(CBMetal, CBNoMetal);
                            IVgemas.setText(" " + gemasFin);
                            descartar();
                            JuegoGanado(ListE1NO1, ListE2NO1);
                         }else{
                             mensaje=combi.getMensaje(mensaje);
                            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
                            limpiar();
                    }

            }else{
                mensaje=combi.getMensaje(mensaje);
                Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
                limpiar();
            }

            } else {
                Toast.makeText(this, "La suma de los Numeros de Oxidación debe ser 0 y debes elegir un NO de tu metal o Nometal", Toast.LENGTH_LONG).show();
                gemas = 0;
                limpiar();

            }
        }else{
            mensaje=combi.getMensaje(mensaje);
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
            gemas=0;
            limpiar();
        }

    }

    //Metodos caundo se unen un MT Y UN NO
    private void limpiarMetalNOMetal(){
        fin1.setImageDrawable(null);
        fin2.setImageDrawable(null);
        fin3.setImageDrawable(null);
        fin4.setImageDrawable(null);
        C1NO1.setVisibility(View.INVISIBLE);
        C1NO2.setVisibility(View.INVISIBLE);
        C2NO1.setVisibility(View.INVISIBLE);
        C2NO2.setVisibility(View.INVISIBLE);
        C3NO1.setVisibility(View.INVISIBLE);
        C3NO2.setVisibility(View.INVISIBLE);
        C4NO1.setVisibility(View.INVISIBLE);
        C4NO2.setVisibility(View.INVISIBLE);
    }
    private void metalNometal(int NO, int id) {
        NCborrado++;
        if(NCborrado==1) {
            limpiarMetalNOMetal();
        }
        if (id == R.id.btn_E1NO1) {
            if (calculo.size() < 1) {
                E1NO2.setVisibility(View.INVISIBLE);
                E2NO1.setVisibility(View.INVISIBLE);
                E2NO2.setVisibility(View.INVISIBLE);
                calculo.add(NO);
                fin1.setImageResource(NumerosOxi[4]);
            } else if (calculo.size() == 1) {
                fin2.setImageResource(NumerosOxi[4]);
                calculo.add(1, NO);
            } else if (calculo.size() == 2) {
                fin3.setImageResource(NumerosOxi[4]);
                calculo.add(2, NO);
            }
        }
        if (id == R.id.btn_E1NO2) {
            if (calculo.size() < 1) {
                E1NO1.setVisibility(View.INVISIBLE);
                E2NO1.setVisibility(View.INVISIBLE);
                E2NO2.setVisibility(View.INVISIBLE);
                calculo.add(NO);
                fin1.setImageResource(NumerosOxi[5]);
            } else if (calculo.size() == 1) {
                fin2.setImageResource(NumerosOxi[5]);
                calculo.add(1, NO);
            } else if (calculo.size() == 2) {
                fin3.setImageResource(NumerosOxi[5]);
                calculo.add(2, NO);
            }
        }
        if (id == R.id.btn_E2NO1) {

            if (calculo.size() < 1) {
                E1NO1.setVisibility(View.INVISIBLE);
                E1NO2.setVisibility(View.INVISIBLE);
                E2NO2.setVisibility(View.INVISIBLE);
                calculo.add(NO);
                fin1.setImageResource(NumerosOxi[6]);
            } else if (calculo.size() == 1) {
                fin2.setImageResource(NumerosOxi[6]);
                calculo.add(1, NO);
            } else if (calculo.size() == 2) {
                fin3.setImageResource(NumerosOxi[6]);
                calculo.add(2, NO);
            }
        }
        if (id == R.id.btn_E2NO2) {

            if (calculo.size() < 1) {
                E1NO1.setVisibility(View.INVISIBLE);
                E1NO2.setVisibility(View.INVISIBLE);
                E2NO1.setVisibility(View.INVISIBLE);
                calculo.add(NO);
                fin1.setImageResource(NumerosOxi[7]);
            } else if (calculo.size() == 1) {
                fin2.setImageResource(NumerosOxi[7]);
                calculo.add(1, NO);
            } else if (calculo.size() == 2) {
                fin3.setImageResource(NumerosOxi[7]);
                calculo.add(2, NO);
            }
        }
    }

    //Metodos de control de juego
    private void borrarElementosList(int BRMetal, int BRNM){

        for (int i=0;i<BRMetal;i++){
            ListE1NO1.remove(0);
            ListE1NO2.remove(0);
        }
        for(int x=0;x<BRNM;x++){
            ListE2NO1.remove(0);
            ListE2NO2.remove(0);
        }

    }
    private void JuegoGanado(ArrayList E1, ArrayList E2){

        if (ListE1NO1.size()==0&ListE2NO1.size()==0){
            Intent clasificacion = new Intent(this, ActivityClasificacion.class);
            clasificacion.putExtra("BiTer",BiTer);
            clasificacion.putStringArrayListExtra("CompuestosCreados", elementosCreados);
            clasificacion.putExtra("gemasCreadas",gemasFin);
            clasificacion.putIntegerArrayListExtra("IdsCompuestosCreados",idsCompuestosCreados);
            clasificacion.putIntegerArrayListExtra("controlPeroxidos",listPeroxidos);
            clasificacion.putExtra("ids",Ids);
            clasificacion.putExtra("numeros de Oxidacion", NumerosOxi);
            startActivity(clasificacion);
            finish();
        }


    }
    private void descartar() {

        for (int i = 0; i < 4; i++) {
            baraja.remove(0);
        }
        if(baraja.size()>=4) {
            repartir(baraja);
            limpiar();

        }else{
             if(elementosCreados.size()==0){
                Intent i = new Intent(this, fin_del_juego.class);
                startActivity(i);
            }else {
                 Intent clasificacion = new Intent(this, ActivityClasificacion.class);
                 clasificacion.putStringArrayListExtra("CompuestosCreados", elementosCreados);
                 clasificacion.putExtra("BiTer", BiTer);
                 clasificacion.putExtra("gemasCreadas", gemasFin);
                 clasificacion.putIntegerArrayListExtra("IdsCompuestosCreados", idsCompuestosCreados);
                 clasificacion.putIntegerArrayListExtra("controlPeroxidos", listPeroxidos);
                 clasificacion.putExtra("ids", Ids);
                 clasificacion.putExtra("numeros de Oxidacion", NumerosOxi);
                 startActivity(clasificacion);
                 finish();
             }
        }

    }
    private void limpiar(){
        finNO.setImageDrawable(null);
        fin1.setImageDrawable(null);
        fin2.setImageDrawable(null);
        fin3.setImageDrawable(null);
        fin4.setImageDrawable(null);
        if(ListE1NO2.size()!=0) {
            E1NO1.setVisibility(View.VISIBLE);
            E1NO2.setVisibility(View.VISIBLE);}
        if(ListE2NO1.size()!=0) {
            E2NO1.setVisibility(View.VISIBLE);
            E2NO2.setVisibility(View.VISIBLE);
        }
        C1NO1.setVisibility(View.VISIBLE);
        if(baraja.get(0).getNO1()!=-2) {
            C1NO2.setVisibility(View.VISIBLE);
        }
        C2NO1.setVisibility(View.VISIBLE);
        if(baraja.get(1).getNO1()!=-2) {
            C2NO2.setVisibility(View.VISIBLE);
        }

        C3NO1.setVisibility(View.VISIBLE);
        if(baraja.get(2).getNO1()!=-2) {
            C3NO2.setVisibility(View.VISIBLE);
        }
        C4NO1.setVisibility(View.VISIBLE);
        if(baraja.get(3).getNO1()!=-2) {
            C4NO2.setVisibility(View.VISIBLE);
        }
        NORep.setVisibility(View.INVISIBLE);
        NORep2.setVisibility(View.INVISIBLE);
        calculo.clear();
        NOm=0; NOnm=0;
        gemas=0;
        NO=0;
        NCborrado=0;
        idElemento=0;
        NOsuma=0;
        idm=10;
        idNm=0;
        idHuO=0;
        CombinacionValida=true;
        AñadirValido=true;
        mismoelemento = 0;
        cartasM=0;
        cartasOuH=0;
        cartasNM=0;
        CBMetal=0;
        CBNoMetal=0;
        entradaCompuesto.setText("");
        suma=0;
        conperoxido=0;
        minNumero=true;

    }

    // Metodos sumaMismo elemento
    private void mismoElemento(int id) {
         mismoelemento++;
        switch (id) {

            case R.id.btn_E1NO1:
                if (NOsuma == 0) {
                    NORep.setVisibility(View.VISIBLE);
                    NORep.setImageResource(NumerosOxi[4]);
                    NOsuma = NO;

                } else if (NOsuma != 0) {
                    NORep2.setVisibility(View.VISIBLE);
                    NORep2.setImageResource(NumerosOxi[4]);
                    NOsuma = NOsuma + NO;
                    gemas++;

                }break;

            case R.id.btn_E1NO2:
                if (NOsuma == 0) {
                    NORep.setVisibility(View.VISIBLE);
                    NORep.setImageResource(NumerosOxi[5]);
                    NOsuma = NO;

                } else if (NOsuma != 0) {
                    NORep2.setVisibility(View.VISIBLE);
                    NORep2.setImageResource(NumerosOxi[5]);
                    NOsuma = NOsuma + NO;
                    gemas++;

                }break;

            case R.id.btn_E2NO1:
                if (NOsuma == 0) {
                    NORep.setVisibility(View.VISIBLE);
                    NORep.setImageResource(NumerosOxi[6]);
                    NOsuma = NO;

                } else if (NOsuma != 0) {
                    NORep2.setVisibility(View.VISIBLE);
                    NORep2.setImageResource(NumerosOxi[6]);
                    NOsuma = NOsuma + NO;
                    gemas++;

                }break;

            case R.id.btn_E2NO2:
                if (NOsuma == 0) {
                    NORep.setVisibility(View.VISIBLE);
                    NORep.setImageResource(NumerosOxi[7]);
                    NOsuma = NO;

                    gemas++;
                } else if (NOsuma != 0) {
                    NORep2.setVisibility(View.VISIBLE);
                    NORep2.setImageResource(NumerosOxi[7]);
                    NOsuma = NOsuma + NO;

                    gemas++;
                }break;

        }
    }

    private String mostrarElementos(){
        String todosElementos="";
        String elementoModificar;
        Resources res = getResources();
        String dos = res.getString(R.string.dos);
        String tres=res.getString(R.string.tres);
        String cuatro=res.getString(R.string.cuatro);
        for(int i=0; i<elementosCreados.size();i++){
            elementoModificar=elementosCreados.get(i);
            todosElementos = todosElementos + " " + elementoModificar.replace("2",dos).replace("3",tres).replace("4",cuatro)+ '\n';
        }

        return  todosElementos;
    }

    //Peroxidos
    private void peroxidos(ArrayList<ElementoEntity> baraja){
        this.conperoxido=1;
        if (calculo.size()>0) {
            for (int x = 0; x < calculo.size(); x++) {
                if (calculo.get(0) == 1 || calculo.get(0) == -1) {
                    limpiar();
                    break;
                }
            }
        }
        if(idNm!=0){
            limpiar();
            E2NO1.setVisibility(View.INVISIBLE);
            E2NO2.setVisibility(View.INVISIBLE);
        }
        int dosOxi=0;
        for (int i=0;i<4;i++) {
            if (baraja.get(i).getNO1() == -2) {
                dosOxi++;
            }
        }
            if (dosOxi>=2){
                this.suma=2;
                imagen=0;
                for (int i=0;i<4;i++) {
                    int ocultar=baraja.get(i).getNO1();
                     if(ocultar==-1&&imagen==0){
                        C1NO1.setVisibility(View.INVISIBLE);
                        C1NO2.setVisibility(View.INVISIBLE);

                    }

                    else  if(ocultar==-1&&imagen==1){
                        C2NO1.setVisibility(View.INVISIBLE);
                        C2NO2.setVisibility(View.INVISIBLE);

                    }

                    else  if(ocultar==-1&&imagen==2){
                        C3NO1.setVisibility(View.INVISIBLE);
                        C3NO2.setVisibility(View.INVISIBLE);

                    }

                    else if(ocultar==-1&&imagen==3){
                        C4NO1.setVisibility(View.INVISIBLE);
                        C4NO2.setVisibility(View.INVISIBLE);

                    }
                    imagen++;
                }
            }else{
                Toast.makeText(this,"Tienes que tener al menos dos Oxígenos en distintas columnas para formar un peróxido",Toast.LENGTH_LONG).show();
            }
    }
    private void setListPeroxidos(ArrayList<Integer> peroxidos){
        peroxidos.add(conperoxido);
    }


}
