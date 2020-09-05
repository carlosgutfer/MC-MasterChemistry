package com.example.mc_masterchemistry.UI.terciarios;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class tablero_terciarios extends AppCompatActivity implements dialoge_reto.OnInputListener, View.OnClickListener {
    private Button limpiar,fusion,barajar,reto;
    public ArrayList<ElementoEntity> baraja = new ArrayList<>(64);

    private int O=0, H=0;
    private int imagen=0;
    private int NO1,NO=0,NOm,NOnm,NOsuma=0;
    private int NCborrado=0;
    private ArrayList<Integer> calculo = new ArrayList();
    private ArrayList<Integer> calculoOxo = new ArrayList<>();
    private int gemas=0, gemasFin=0;
    private int  idm=10, idNm=0, idO, idH;
    private int idElemento=0;
    private int numOxo=0;
    private boolean CombinacionValida=true;
    private boolean AñadirValido=true;
    private boolean minNumero=true;

    private String mensaje;
    private String StElemento;
    private ArrayList<String> elementosCreados = new ArrayList<>();
    public  ArrayList<Integer>idsCompuestosCreados = new ArrayList<>();
    private int mismoelemento=0;
    private int cartasM=0, cartasNM=0;
    private int CBMetal=0, CBNoMetal=0;
    private int suma=0;


    private boolean control=true;
    private  boolean retoactivo=true;
    private int numLimpiardulicado;
    private boolean variablesuma =true;
    public static int  retoH=0, retoH2O=0;
    private int BiTer;






    List<ElementoEntity> allElementos;
    private ElementoViewModel melementoviewModel;

    private CombinacionQuimiciaTeryBi combi = new CombinacionQuimiciaTeryBi(this);
    private Retos TipoReto = new Retos(this);

    private int NumerosOxi []= new int [8];
    private int Ids[]=new int [2];

    private ArrayList<Integer> ListE1NO1 = new ArrayList<>();
    private ArrayList<Integer> ListE1NO2= new ArrayList<>();
    private ArrayList<Integer> ListE2NO1 = new ArrayList<>();
    private ArrayList<Integer> ListE2NO2 = new ArrayList<>();


    private ImageButton C1NO1,C1NO2,C2NO1,C2NO2,C3NO1,C3NO2,C4NO1,C4NO2;

    private ImageButton E1NO1,E1NO2,E2NO1,E2NO2;

    private ImageButton finNO,fin1,fin2,fin3,fin4,NORep,NORep2;

    private TextView IVgemas,NombreMetal,NombreNometal;

    private ImageButton elementos;
    private TextView listaElementos;
    private EditText entradaCompuesto;

        public tablero_terciarios(){

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        if (height>1280) {
            setContentView(R.layout.activity_terciarios_activity);
        }else{
            setContentView(R.layout.tablero_terciarios_pequeno);
        }




        reto=findViewById(R.id.Bt_reto);

            reto.setOnClickListener(v -> {
                    dialoge_reto dialog = new dialoge_reto();
                    dialog.show(getSupportFragmentManager(), "Fragment");

            });


        melementoviewModel = new ViewModelProvider(this).get(ElementoViewModel.class);
        melementoviewModel.getAllElementos().observe(this, allEllementos -> setelementos(allEllementos));

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
    private void setelementos(List<ElementoEntity> items) {
        this.allElementos=items;
        crearBaraja(allElementos);
        repartir(baraja);
    }

    public void onClick(View v) {


        if(v.getId()==R.id.Btn_elementosCreados){

            if(listaElementos.getVisibility()==View.INVISIBLE){
                listaElementos.setVisibility(VISIBLE);
            }else{
                listaElementos.setVisibility(View.INVISIBLE);
            }

        }

        if(v.getId()==R.id.btn_E1NO1){
            if(TipoReto.numeroTipo==4&retoactivo){
                retoduplicar();
                if(ListE2NO1.size()>0){
                ListE2NO1.remove(0);
                ListE2NO2.remove(0);}
                   numLimpiardulicado=1;
            }

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
            } else if (calculo.size()<ListE1NO1.size()&idNm>9){
                NOm = ListE1NO1.get(0);
                metalNometal(NOm, R.id.btn_E1NO1);
                control=false;

            }else if(ListE1NO1.get(0)==NO&mismoelemento<ListE1NO1.size()&idm<10){
                mismoElemento(idElemento);
            }

        }
        if(v.getId()==R.id.btn_E1NO2){
            if(TipoReto.numeroTipo==4&retoactivo){
                retoduplicar();
                if(ListE2NO1.size()>0){
                    ListE2NO1.remove(0);
                    ListE2NO2.remove(0);}
                numLimpiardulicado=1;
            }
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
            }else if (calculo.size()<ListE1NO2.size()&idNm>9 ){
                NOm=ListE1NO2.get(0);
                metalNometal(NOm,R.id.btn_E1NO2 );
                control=false;
            } else if(ListE1NO2.get(0)==NO&mismoelemento<ListE1NO1.size()&idm<10){

                mismoElemento(idElemento);
            }
        }
        if(v.getId()==R.id.btn_E2NO1){
            if(TipoReto.numeroTipo==4&retoactivo){
                retoduplicar();
                if(ListE1NO1.size()>0) {
                    ListE1NO1.remove(0);
                    ListE1NO2.remove(0);
                }
                numLimpiardulicado=2;
            }
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
            }else if (calculo.size()<ListE2NO1.size()&idm<10 ){
                NOnm=ListE2NO1.get(0);
                metalNometal(NOnm,R.id.btn_E2NO1 );
                control=false;
            }else if(ListE2NO1.get(0)==NO&mismoelemento<ListE2NO1.size()&idNm>9){
                mismoElemento(idElemento);
            }
        }
        if(v.getId()==R.id.btn_E2NO2){
            if(TipoReto.numeroTipo==4&retoactivo){
                retoduplicar();
                if(ListE1NO1.size()>0) {
                    ListE1NO1.remove(0);
                    ListE1NO2.remove(0);
                }
                numLimpiardulicado=2;
            }
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
            }else if (calculo.size()<ListE2NO2.size()&idm<10){
                NOnm=ListE2NO2.get(0);
                metalNometal(NOnm,R.id.btn_E2NO2 );
                control=false;
            }else if(ListE2NO2.get(0)==NO&mismoelemento<ListE2NO1.size()&idNm>9){
                mismoElemento(idElemento);
            }
        }

        if(v.getId()==R.id.carta1NO1){

            if(idm<10&idNm>9) {
                Oxosales(R.id.carta1NO1);
                fin3.setImageResource(baraja.get(0).getIM1());
            }else {
                fin1.setImageResource(baraja.get(0).getIM1());
                añadircalculo(R.id.carta1NO1);
            }
        }
        if(v.getId()==R.id.carta1NO2){

                fin1.setImageResource(baraja.get(0).getIM2());
                añadircalculo(R.id.carta1NO2);
        }

        if(v.getId()==R.id.carta2NO1){
            if(idm<10&idNm>9) {
                Oxosales(R.id.carta2NO1);
                fin4.setImageResource(baraja.get(1).getIM1());
            }else {
                fin2.setImageResource(baraja.get(1).getIM1());
                añadircalculo(R.id.carta2NO1);
            }
        }
        if(v.getId()==R.id.carta2NO2){

                fin2.setImageResource(baraja.get(1).getIM2());
                añadircalculo(R.id.carta2NO2);}

        if(v.getId()==R.id.carta3NO1){

            if(idm<10&idNm>9) {
                NORep.setVisibility(VISIBLE);
                NORep.setImageResource(baraja.get(2).getIM1());
                Oxosales(R.id.carta3NO1);
            }else {
                fin3.setImageResource(baraja.get(2).getIM1());
                añadircalculo(R.id.carta3NO1);
            }
        }
        if(v.getId()==R.id.carta3NO2){

                fin3.setImageResource(baraja.get(2).getIM2());
                añadircalculo(R.id.carta3NO2);

        }

        if(v.getId()==R.id.carta4NO1){
               if(idm<10&idNm>9) {
                NORep2.setVisibility(VISIBLE);
                NORep2.setImageResource(baraja.get(3).getIM1());
                Oxosales(R.id.carta4NO1);

            }else {
                fin4.setImageResource(baraja.get(3).getIM1());
                añadircalculo(R.id.carta4NO1);
            }
        }
        if(v.getId()==R.id.carta4NO2){

                fin4.setImageResource(baraja.get(3).getIM2());
                añadircalculo(R.id.carta4NO2);
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
        BiTer=getIntent().getIntExtra("BiTer",BiTer);
        Ids=getIntent().getIntArrayExtra("ids");
        NumerosOxi=getIntent().getIntArrayExtra("numeros de Oxidacion");
        if(BiTer==3){
            gemasFin=getIntent().getIntExtra("gemas",gemasFin);
            IVgemas.setText(" "+gemasFin);
        }
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
        String compuesto = entradaCompuesto.getText().toString();
        if (TipoReto.numeroTipo==3|TipoReto.numeroTipo==1){
                if (TipoReto.numeroTipo==3){
                    if(compuesto.contains("Au")) {
                        if(combi.elementoRepetido(elementosCreados,compuesto)) {
                            for (int i = 0; i < idsCompuestosCreados.size(); i = i + 3) {
                                if (idsCompuestosCreados.get(i) == 13 & idsCompuestosCreados.get(i + 1) > 9 & idsCompuestosCreados.get(i + 2) == -1) {
                                    if (compuesto.equals("Au" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo())|compuesto.equals("Au2" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo())) {
                                    int posicion = elementosCreados.indexOf("H" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo());
                                    idsCompuestosCreados.set(i, 6);
                                    if (posicion != -1) {
                                        elementosCreados.set(posicion, "Au" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo());
                                    } else {
                                        posicion = elementosCreados.indexOf("H2" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo());
                                        elementosCreados.set(posicion, "Au2" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo());
                                     }

                                    listaElementos.setText(mostrarElementos());
                                    gemasFin++;
                                    IVgemas.setText(" " + gemasFin);
                                    entradaCompuesto.setText("");
                                    TipoReto.numeroTipo = 5;
                                    }else{
                                        Toast.makeText(this,"¡Upps! La formula introducida no es correcta. Revisa los símbolos, el orden (segun la secuencia IUPAC) y los subíndices.",Toast.LENGTH_LONG).show();
                                    }

                                } else if (idsCompuestosCreados.get(i) > 9 & idsCompuestosCreados.get(i + 1) == 13 & idsCompuestosCreados.get(i + 2) == -1) {
                                    if (compuesto.equals("Au3" + allElementos.get(idsCompuestosCreados.get(i)).getSimbolo())|compuesto.equals("Au4" + allElementos.get(idsCompuestosCreados.get(i)).getSimbolo())) {
                                        int posicion = elementosCreados.indexOf(allElementos.get(idsCompuestosCreados.get(i)).getSimbolo() + "H" + "3");
                                        if (posicion != -1) {
                                            elementosCreados.set(posicion, "Au" + "3" + allElementos.get(idsCompuestosCreados.get(i)).getSimbolo());
                                        } else {
                                            posicion = elementosCreados.indexOf(allElementos.get(idsCompuestosCreados.get(i)).getSimbolo() + "H" + "4");
                                            elementosCreados.set(posicion, "Au" + "4" + allElementos.get(idsCompuestosCreados.get(i)).getSimbolo());

                                        }
                                        idsCompuestosCreados.set(i + 1, idsCompuestosCreados.get(i));
                                        idsCompuestosCreados.set(i, 6);
                                        listaElementos.setText(mostrarElementos());
                                        gemasFin++;
                                        IVgemas.setText(" " + gemasFin);
                                        entradaCompuesto.setText("");
                                        TipoReto.numeroTipo = 5;
                                    }else{
                                        Toast.makeText(this,"¡Upps! La formula introducida no es correcta. Revisa los símbolos, el orden (segun la secuencia IUPAC) y los subíndices.",Toast.LENGTH_LONG).show();

                                    }

                                }
                            }
                             }else {
                                    mensaje = combi.getMensaje(mensaje);
                                    Toast.makeText(this, mensaje,Toast.LENGTH_LONG).show();
                            }

                    }
                }else if (TipoReto.numeroTipo==1){
                    compuesto = entradaCompuesto.getText().toString();
                        if (!TipoReto.compuestovalido(compuesto, idsCompuestosCreados, allElementos, elementosCreados)) {
                            TipoReto.compuestosCreados(compuesto, idsCompuestosCreados, allElementos, elementosCreados);
                            for (int i = 0; i < idsCompuestosCreados.size(); i = i + 3) {
                                if (idsCompuestosCreados.get(i) == 17 & idsCompuestosCreados.get(i + 1) > 9 & idsCompuestosCreados.get(i + 2) == -1) {
                                    idsCompuestosCreados.set(i, 13);
                                    idsCompuestosCreados.set(i + 2, 17);
                                }
                            }
                            listaElementos.setText(mostrarElementos());
                            gemasFin++;
                            IVgemas.setText(" " + gemasFin);
                            entradaCompuesto.setText("");
                            TipoReto.numeroTipo = 5;

                        }else {
                            Toast.makeText(this, "El elemento esta repedito, o el valor introducido no es correcto",Toast.LENGTH_LONG).show();
                        }
                }

        }else {

            if(TipoReto.numeroTipo==0){
                if(TipoReto.comodin(allElementos,compuesto)<10&TipoReto.comodin(allElementos,compuesto)!=-1){
                    idm=allElementos.get(TipoReto.comodin(allElementos,compuesto)).getId();
                }else if(TipoReto.comodin(allElementos,compuesto)>9){
                    idNm=allElementos.get(TipoReto.comodin(allElementos,compuesto)).getId();
                }else if(TipoReto.comodin(allElementos,compuesto)==-1){
                    Toast.makeText(this, "El elemento introducido no esta en la lista", Toast.LENGTH_LONG).show();
                    gemas = 0;
                    limpiar();
                }

            }

                if (calculoOxo.size() > 0) {
                    sumaOxo(calculoOxo);
                }
                for (int i = 0; i < calculo.size(); i++) {
                    if (calculo.get(i) != 0) {
                        suma = suma + calculo.get(i);
                        gemas++;
                    }
                }

                suma = suma + NO + NOsuma;

            if(TipoReto.numeroTipo==0){
                NO=TipoReto.sumaNoComodin(allElementos,idm,idNm,suma);
                suma=suma+NO;
            }
                gemas++;
                if (NOsuma != 0) {
                    gemas++;
                }
                if (control) {
                    tipoElemento(calculo);
                }

                CombinacionValida = combi.combinacionesPosibles(CombinacionValida, idm, idNm, idH, idO, calculo);
                if (CombinacionValida) {
                    if (suma == 0) {
                        if (NOsuma - NO == NO) {
                            minNumero = combi.minElementos(calculo, NO, NO);
                        }
                        if (NOsuma - NO == 0) {
                            minNumero = combi.minElementos(calculo, NO, NOsuma);
                        }
                        if (minNumero) {

                            AñadirValido = combi.comprobarElementoList(StElemento, allElementos, elementosCreados, idm, idNm, idH, idO, cartasM, cartasNM, calculo, compuesto, idsCompuestosCreados, numOxo);
                            if (AñadirValido) {
                                if (TipoReto.numeroTipo == 2) {
                                    if (TipoReto.retotodos(calculo)) {
                                        gemas++;
                                    } else {
                                        gemas = gemas - 3;
                                    }
                                }
                                gemasFin = gemasFin + gemas;
                                combi.getList(elementosCreados);
                                combi.getIds(idsCompuestosCreados);
                                listaElementos.setText(mostrarElementos());
                                RetoSustituir(idsCompuestosCreados);
                                borrarElementosList(CBMetal, CBNoMetal);
                                IVgemas.setText(" " + gemasFin);
                                if (TipoReto.numeroTipo != 5) {
                                    retoactivo = true;
                                    TipoReto.numeroTipo = 5;
                                    variablesuma = true;
                                }
                                descartar();
                                JuegoGanado(ListE1NO1, ListE2NO1);
                            } else {
                                mensaje = combi.getMensaje(mensaje);
                                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                                limpiar();
                            }

                        } else {
                            mensaje = combi.getMensaje(mensaje);
                            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                            limpiar();
                        }

                    } else {
                        Toast.makeText(this, "La suma de los Numeros de Oxidación debe ser 0 y debes elegir un NO de tu metal o Nometal", Toast.LENGTH_LONG).show();
                        gemas = 0;
                        limpiar();

                    }
                } else {
                    mensaje = combi.getMensaje(mensaje);
                    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    gemas = 0;
                    limpiar();
                }
            }
    }

    //Metodos caundo se unen un MT Y UN NO
    private void limpiarMetalNOMetal(){

        fin1.setImageDrawable(null);
        fin2.setImageDrawable(null);
        fin3.setImageDrawable(null);
        fin4.setImageDrawable(null);
        if(baraja.get(0).getNO1()==-1){
        C1NO1.setVisibility(View.INVISIBLE);
        C1NO2.setVisibility(View.INVISIBLE);}
        if(baraja.get(1).getNO1()==-1){
        C2NO1.setVisibility(View.INVISIBLE);
        C2NO2.setVisibility(View.INVISIBLE);}
        if(baraja.get(2).getNO1()==-1){
        C3NO1.setVisibility(View.INVISIBLE);
        C3NO2.setVisibility(View.INVISIBLE);}
        if(baraja.get(3).getNO1()==-1){
        C4NO1.setVisibility(View.INVISIBLE);
        C4NO2.setVisibility(View.INVISIBLE);}
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
            Intent clasificacion = new Intent(this, clasificacion_terciarios.class);
            clasificacion.putStringArrayListExtra("CompuestosCreados", elementosCreados);
            clasificacion.putExtra("gemasCreadas",gemasFin);
            clasificacion.putIntegerArrayListExtra("IdsCompuestosCreados",idsCompuestosCreados);
            startActivity(clasificacion);
            finish();
        }


    }
    private void descartar() {

        for (int i = 0; i < 4; i++) {
            baraja.remove(0);
        }
        if(baraja.size()>=4) {
            TipoReto.numeroTipo=5;
            repartir(baraja);
            limpiar();
        }else{
            Intent clasificacion = new Intent(this, clasificacion_terciarios.class);
            clasificacion.putStringArrayListExtra("CompuestosCreados", elementosCreados);
            clasificacion.putExtra("gemasCreadas",gemasFin);
            clasificacion.putIntegerArrayListExtra("IdsCompuestosCreados",idsCompuestosCreados);
            startActivity(clasificacion);
            finish();
        }

    }
    private void limpiar(){

        if(TipoReto.numeroTipo==4&!retoactivo){
            if(numLimpiardulicado==1){
                ListE1NO1.remove(0);
                ListE1NO2.remove(0);
            }
            if(numLimpiardulicado==2){
                ListE2NO1.remove(0);
                ListE2NO2.remove(0);
            }
            retoactivo=true;
            variablesuma=true;
        }
        finNO.setImageDrawable(null);
        fin1.setImageDrawable(null);
        fin2.setImageDrawable(null);
        fin3.setImageDrawable(null);
        fin4.setImageDrawable(null);
        if(ListE1NO2.size()!=0) {
            E1NO1.setVisibility(VISIBLE);
            E1NO2.setVisibility(VISIBLE);}
        if(ListE2NO1.size()!=0) {
            E2NO1.setVisibility(VISIBLE);
            E2NO2.setVisibility(VISIBLE);
        }
        C1NO1.setVisibility(VISIBLE);
        if(baraja.get(0).getNO1()!=-2) {
            C1NO2.setVisibility(VISIBLE);
        }
        C2NO1.setVisibility(VISIBLE);
        if(baraja.get(1).getNO1()!=-2) {
            C2NO2.setVisibility(VISIBLE);
        }

        C3NO1.setVisibility(VISIBLE);
        if(baraja.get(2).getNO1()!=-2) {
            C3NO2.setVisibility(VISIBLE);
        }
        C4NO1.setVisibility(VISIBLE);
        if(baraja.get(3).getNO1()!=-2) {
            C4NO2.setVisibility(VISIBLE);
        }
        NORep.setVisibility(View.INVISIBLE);
        NORep2.setVisibility(View.INVISIBLE);
        calculo.clear();
        calculoOxo.clear();
        NOm=0; NOnm=0;
        gemas=0;
        NO=0;
        NCborrado=0;
        idElemento=0;
        NOsuma=0;
        idm=10;
        idNm=0;
        idH=0;
        idO=0;
        CombinacionValida=true;
        AñadirValido=true;
        mismoelemento = 0;
        cartasM=0;
        cartasNM=0;
        CBMetal=0;
        CBNoMetal=0;
        entradaCompuesto.setText("");
        suma=0;
        numOxo=0;
        minNumero=true;
        control=true;
        retoactivo=true;

    }

    // Metodos sumaMismo elemento
    private void mismoElemento(int id) {
        mismoelemento++;
        switch (id) {

            case R.id.btn_E1NO1:
                if (NOsuma == 0) {
                    NORep.setVisibility(VISIBLE);
                    NORep.setImageResource(NumerosOxi[4]);
                    NOsuma = NO;

                } else if (NOsuma != 0) {
                    NORep2.setVisibility(VISIBLE);
                    NORep2.setImageResource(NumerosOxi[4]);
                    NOsuma = NOsuma + NO;
                    gemas++;

                }break;

            case R.id.btn_E1NO2:
                if (NOsuma == 0) {
                    NORep.setVisibility(VISIBLE);
                    NORep.setImageResource(NumerosOxi[5]);
                    NOsuma = NO;

                } else if (NOsuma != 0) {
                    NORep2.setVisibility(VISIBLE);
                    NORep2.setImageResource(NumerosOxi[5]);
                    NOsuma = NOsuma + NO;
                    gemas++;

                }break;

            case R.id.btn_E2NO1:
                if (NOsuma == 0) {
                    NORep.setVisibility(VISIBLE);
                    NORep.setImageResource(NumerosOxi[6]);
                    NOsuma = NO;

                } else if (NOsuma != 0) {
                    NORep2.setVisibility(VISIBLE);
                    NORep2.setImageResource(NumerosOxi[6]);
                    NOsuma = NOsuma + NO;
                    gemas++;

                }break;

            case R.id.btn_E2NO2:
                if (NOsuma == 0) {
                    NORep.setVisibility(VISIBLE);
                    NORep.setImageResource(NumerosOxi[7]);
                    NOsuma = NO;

                    gemas++;
                } else if (NOsuma != 0) {
                    NORep2.setVisibility(VISIBLE);
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
            if (elementoModificar.indexOf("OH2")!=-1){
                todosElementos = todosElementos + " " + elementoModificar.replace("OH","(OH)").replace("2",dos)+'\n';

            }else{
                todosElementos = todosElementos + " " + elementoModificar.replace("2",dos).replace("3",tres).replace("4",cuatro)+ '\n';

            }
        }

        return  todosElementos;
    }

    private void tipoElemento(ArrayList<Integer> HuO){

        for (int i=0; i<HuO.size();i++){
            if(HuO.get(i)==-2){
                this.idO=17;
            }else if(HuO.get(i)==1){
                this.idH=13;
            }else if(HuO.get(i)==-1){
                this.idH=13;
            }
        }

    }

private void Oxosales(int id){
        this.idO=17;
    numOxo++;
    E1NO1.setVisibility(View.INVISIBLE);
        E1NO2.setVisibility(View.INVISIBLE);
    if(id==R.id.carta1NO1){
        if(calculoOxo.size()<1) {
            calculoOxo.add(0, baraja.get(0).getNO1());
        }
        else if (calculoOxo.size()>=1){
            calculoOxo.remove(0);
            calculoOxo.add(0, baraja.get(0).getNO1());
        }

    }

    if(id==R.id.carta2NO1){
        if (calculoOxo.size() <1) {
            calculoOxo.add(0,0);
            calculoOxo.add(1, baraja.get(1).getNO1());
        }  else if (calculo.size()>=2) {
            calculoOxo.remove(1);
            calculoOxo.add(1, baraja.get(1).getNO1());
        }
        else if (calculoOxo.size()==1){
            calculoOxo.add(1,baraja.get(1).getNO1());
        }
    }

    if(id==R.id.carta3NO1){
        if (calculoOxo.size()<1){
            calculoOxo.add(0,0);
            calculoOxo.add(1,0);
            calculoOxo.add(2,baraja.get(2).getNO1());
        }else if(calculoOxo.size()==1){
            calculoOxo.add(1,0);
            calculoOxo.add(2,baraja.get(2).getNO1());
        }
        else if (calculoOxo.size()==2){
            calculoOxo.add(2,baraja.get(2).getNO1());
        }
        else if (calculoOxo.size()>=3) {
            calculoOxo.remove(2);
            calculoOxo.add(2, baraja.get(2).getNO1());
        }

    }

    if(id==R.id.carta4NO1){
        if (calculoOxo.size()<1){
            calculoOxo.add(0,0);
            calculoOxo.add(1,0);
            calculoOxo.add(2,0);
            calculoOxo.add(3, baraja.get(3).getNO1());
        }
        if (calculoOxo.size()==1){
            calculoOxo.add(1,0);
            calculoOxo.add(2,0);
            calculoOxo.add(3, baraja.get(3).getNO1());
        }
        if (calculoOxo.size()==2){
            calculoOxo.add(2,0);
            calculoOxo.add(3, baraja.get(3).getNO1());
        }
        else if(calculoOxo.size()==3){
            calculoOxo.add(3, baraja.get(3).getNO1());
        }
        else if (calculoOxo.size()==4){
            calculoOxo.remove(3);
            calculoOxo.add(3, baraja.get(3).getNO1());
        }
    }



}
private void sumaOxo(ArrayList<Integer> calculoOxo){
    for(int i =0; i<calculoOxo.size();i++){
        if (calculoOxo.get(i)!=0) {
            suma = suma + calculoOxo.get(i);
            gemas++;
        }
    }

}
//Reto duplicar
    private void retoduplicar() {

        if(variablesuma) {
            if(ListE1NO1.size()>0) {
                ListE1NO1.add(NumerosOxi[0]);
                ListE1NO2.add(NumerosOxi[1]);
            }
            if(ListE2NO1.size()>0){
          ListE2NO1.add(NumerosOxi[2]);
           ListE2NO2.add(NumerosOxi[3]);
            }


        }
        retoactivo=false;
        variablesuma = false;
    }


    private void RetoSustituir(ArrayList<Integer> idsCreadas){

        if(idsCreadas.size()>=3) {
            for (int i = 0; i < idsCreadas.size(); i = i + 3) {
                if (idsCreadas.get(i) == 13&idsCreadas.get(i+1)>9&idsCreadas.get(i+2)==-1 ) {
                    this.retoH=1;
                }
                if(idsCreadas.get(i)>=9&idsCreadas.get(i)<17&idsCreadas.get(i+1)==17&idsCreadas.get(i+2)==-1){
                    this.retoH2O=2;
                }
                if(idsCreadas.get(i)==17&idsCreadas.get(i+1)>17&idsCreadas.get(i+2)==-1){
                    this.retoH2O=2;
                }
            }
        }


    }


}

