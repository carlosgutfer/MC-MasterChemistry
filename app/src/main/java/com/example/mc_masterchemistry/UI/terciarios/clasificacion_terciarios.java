package com.example.mc_masterchemistry.UI.terciarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.UI.fin_del_juego;
import com.example.mc_masterchemistry.commun.Constants;
import com.example.mc_masterchemistry.commun.SharePreferences;

import java.util.ArrayList;

public class clasificacion_terciarios extends AppCompatActivity implements View.OnClickListener {

    private TextView TV_CompuestosClasificar,TV_Gemas;
    private Button btn_comprobar;
    private RadioButton hidrurosMetalicos,hidrurosNoMetalicos, acidosHidracidos,Halogenos,salNeutra,OxiNoMetal,OxiMetal,oxoacidos,hidroxidos,oxosales;
    private RadioGroup eleccion;


    private int control;

    private ArrayList<String> ListCompuestos;
    private ArrayList<Integer> IdsCompuestos;
    private int gemas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion_terciarios);
        TV_CompuestosClasificar = findViewById(R.id.TV_CompuestosClasificar);
        TV_Gemas = findViewById(R.id.TV_GemasFin);

        btn_comprobar = findViewById(R.id.Btn_comprobar);

        hidrurosNoMetalicos=findViewById(R.id.RB_HidrurosNoMetal);
        Halogenos = findViewById(R.id.RB_Halogenuro);
        salNeutra = findViewById(R.id.RB_SalNeutra);
        OxiNoMetal = findViewById(R.id.RB_OxidoNoMetal);
        OxiMetal = findViewById(R.id.RB_OxidoMetal);
        hidrurosMetalicos = findViewById(R.id.RB_Hidruros);
        acidosHidracidos = findViewById(R.id.RB_acidos);
        oxoacidos=findViewById(R.id.RB_oxoacidos);
        hidroxidos=findViewById(R.id.RB_hidroxidos);
        oxosales=findViewById(R.id.RB_oxosales);

        eleccion = findViewById(R.id.RG_elecciones);

        btn_comprobar.setOnClickListener(this);
        eleccion.setOnCheckedChangeListener((eleccion, checkedId) -> {
            if (hidrurosMetalicos.isChecked()) {
                control=1;
                setControl(control);

            }
            if(hidrurosNoMetalicos.isChecked()){
                control=2;
                setControl(control);


            }
            if (acidosHidracidos.isChecked()) {
                control=3;
                setControl(control);

            }
            if (Halogenos.isChecked()) {
                control=4;
                setControl(control);
            }
            if (OxiNoMetal.isChecked()) {
                control=5;
                setControl(control);
            }
            if (OxiMetal.isChecked()) {
                control=6;
                setControl(control);

            }
            if (salNeutra.isChecked()) {
                control=7;
                setControl(control);
            }
            if(oxoacidos.isChecked()){
                control=8;
                setControl(control);
            }
            if(hidroxidos.isChecked()){
                control=9;
                setControl(control);
            }
            if(oxosales.isChecked()){
                control=10;
                setControl(control);
            }

        });
        Intent();
        mostrarElementos(ListCompuestos,gemas);
    }

    private void Intent(){

        gemas=getIntent().getIntExtra("gemasCreadas",gemas);
        ListCompuestos=getIntent().getStringArrayListExtra("CompuestosCreados");
        IdsCompuestos=getIntent().getIntegerArrayListExtra("IdsCompuestosCreados");
    }

    private void mostrarElementos(ArrayList<String> ListCompuestos,int gemas) {
        String elementoModificar=ListCompuestos.get(0);
        Resources res = getResources();
        String dos = res.getString(R.string.dos);
        String tres=res.getString(R.string.tres);
        String cuatro=res.getString(R.string.cuatro);
        TV_CompuestosClasificar.setText(elementoModificar.replace("2",dos).replace("3",tres).replace("4",cuatro));
        TV_Gemas.setText(" "+gemas);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.Btn_comprobar){
            switch (control){
                case 1:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)==13&IdsCompuestos.get(2)==-1){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un Hidruro metálico",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);

                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }

                    break;
                case 2:
                    if(IdsCompuestos.get(0)>9&IdsCompuestos.get(0)<13&IdsCompuestos.get(1)==13&IdsCompuestos.get(2)==-1){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un Hidruro  no metálico",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);

                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;
                case 3:
                    if(IdsCompuestos.get(0)==13&IdsCompuestos.get(1)>13&IdsCompuestos.get(2)==-1){
                        gemas++;

                    }else{
                        Toast.makeText(this," El compuesto no es un Ácido hidrácido",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;

                case 4:
                    if(IdsCompuestos.get(0)==17&IdsCompuestos.get(1)>17&IdsCompuestos.get(2)==-1){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un Halógeno de oxígeno",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;

                case 5:
                    if(IdsCompuestos.get(0)<17&IdsCompuestos.get(0)>9&IdsCompuestos.get(1)==17&IdsCompuestos.get(2)==-1){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un Óxido de no metal",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0) {
                        juegoFinalizado();
                    }else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;

                case 6:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)==17&IdsCompuestos.get(2)==-1){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un  Óxido de  metal",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;

                case 7:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)>9&IdsCompuestos.get(2)==-1){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es una  Sal neutra binaria",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;
                case 8:
                    if(IdsCompuestos.get(0)==13&IdsCompuestos.get(1)>9&IdsCompuestos.get(2)==17){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un oxoácido",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;
                case 9:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)==17&IdsCompuestos.get(2)==13){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un hidróxido",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;
                case 10:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)>9&IdsCompuestos.get(2)==17){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es una  oxosal",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    eliminar(IdsCompuestos);
                    if(ListCompuestos.size()==0){
                        juegoFinalizado();}
                    else {
                        mostrarElementos(ListCompuestos, gemas);
                    }
                    break;
                    }
            }

        }

    private void setControl(Integer control){
        this.control=control;
    }

    private void eliminar(ArrayList<Integer> compuestos){
        ListCompuestos.remove(0);
        for (int i=0;i<3;i++) {
            compuestos.remove(0);
        }

    }

    private void juegoFinalizado(){
        int total = SharePreferences.getIntegerValue(Constants.TOTAL_SCORE);
        if(total !=-1){
            total = total +gemas;}
        else{
            total =gemas;
        }
        SharePreferences.setIntegerValue(Constants.TOTAL_SCORE, total);
        int mejor = SharePreferences.getIntegerValue(Constants.BEST_SCORE);
        if(mejor <gemas)
            SharePreferences.setIntegerValue(Constants.BEST_SCORE, gemas);


        Intent i = new Intent(this, fin_del_juego.class);
        i.putExtra("GemasGanadas",gemas);
        startActivity(i);
        finish();
    }
}
