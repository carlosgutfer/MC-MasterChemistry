package com.example.mc_masterchemistry.UI.tablero;

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
import com.example.mc_masterchemistry.UI.terciarios.tablero_terciarios;
import com.example.mc_masterchemistry.commun.Constants;
import com.example.mc_masterchemistry.commun.SharePreferences;

import java.util.ArrayList;

public class ActivityClasificacion extends AppCompatActivity implements View.OnClickListener {

    private TextView TV_CompuestosClasificar,TV_Gemas;
    private Button btn_comprobar;
    private RadioButton hidrurosMetalicos,hidrurosNoMetalicos, acidosHidracidos,Halogenos,salNeutra,OxiNoMetal,OxiMetal,peroxido;
    private RadioGroup eleccion;


    private int control;
    private int BiTer;
    private int[] NumerosOxi = new int [8];
    private int[] Ids =new int [2];


    private ArrayList<String> ListCompuestos;
    private ArrayList<Integer> IdsCompuestos;
    private ArrayList<Integer> Listperoxidos;
    private int gemas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
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
        peroxido=findViewById(R.id.RB_peroxido);

        eleccion = findViewById(R.id.RG_elecciones);

        btn_comprobar.setOnClickListener(this);
        eleccion.setOnCheckedChangeListener((eleccion, checkedId) ->
        {
            if (hidrurosMetalicos.isChecked())
                this.control=1;
            else if(hidrurosNoMetalicos.isChecked())
                this.control=2;
            else if (acidosHidracidos.isChecked())
                this.control=3;
            else if (Halogenos.isChecked())
                this.control=4;
            else if (OxiNoMetal.isChecked())
                this.control=5;
            else if (OxiMetal.isChecked())
                this.control=6;
            else if (salNeutra.isChecked())
                this.control=7;
            else if(peroxido.isChecked())
                this.control=8;
        });
        Intent();
        mostrarElementos(ListCompuestos,gemas);
    }

    private void Intent()
    {
        BiTer=getIntent().getIntExtra("BiTer",BiTer);
        gemas=getIntent().getIntExtra("gemasCreadas",gemas);
        ListCompuestos=getIntent().getStringArrayListExtra("CompuestosCreados");
        IdsCompuestos=getIntent().getIntegerArrayListExtra("IdsCompuestosCreados");
        Listperoxidos=getIntent().getIntegerArrayListExtra("controlPeroxidos");
        Ids = getIntent().getIntArrayExtra("ids");
        NumerosOxi=getIntent().getIntArrayExtra("numeros de Oxidacion");

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

        if(v.getId()==R.id.Btn_comprobar)
        {
            switch(control)
            {
                case 1:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)==13&Listperoxidos.get(0)==0)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es un Hidruro metálico",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;
                case 2:
                    if(IdsCompuestos.get(0)>9&IdsCompuestos.get(0)<13&IdsCompuestos.get(1)==13&Listperoxidos.get(0)==0){
                        gemas++;
                    }else{
                        Toast.makeText(this," El compuesto no es un Hidruro  no metálico",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;
                case 3:
                    if(IdsCompuestos.get(0)==13&IdsCompuestos.get(1)>13&Listperoxidos.get(0)==0)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es un Ácido hidrácido",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;

                case 4:
                    if(IdsCompuestos.get(0)==17&IdsCompuestos.get(1)>17&Listperoxidos.get(0)==0)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es un Halógeno de oxígeno",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;

                case 5:
                    if(IdsCompuestos.get(0)<17&IdsCompuestos.get(0)>9&IdsCompuestos.get(1)==17&Listperoxidos.get(0)==0)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es un Óxido de no metal",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;
                case 6:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)==17&Listperoxidos.get(0)==0)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es un  Óxido de  metal",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;
                case 7:
                    if(IdsCompuestos.get(0)<10&IdsCompuestos.get(1)>9)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es una  Sal neutra binaria",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;
                case 8:
                    if(IdsCompuestos.get(0) < 10 & IdsCompuestos.get(1) == 17 & Listperoxidos.get(0) != 0)
                        gemas++;
                    else{
                        Toast.makeText(this," El compuesto no es un Peróxido",Toast.LENGTH_LONG).show();
                        gemas--;
                    }
                    break;
            }
            eliminar(IdsCompuestos);
            comprobarJuegoFinalizado();
        }
    }

    private void comprobarJuegoFinalizado()
    {
        if (ListCompuestos.size() == 0)
            juegoFinalizado();
        else
            mostrarElementos(ListCompuestos, gemas);
    }

    private void eliminar(ArrayList<Integer> compuestos){
        ListCompuestos.remove(0);
        for (int i=0;i<2;i++) {
            compuestos.remove(0);
        }
        Listperoxidos.remove(0);
    }

    private void juegoFinalizado()
    {
        if(BiTer!=3){
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
        } else{
            Intent jugar = new Intent(this, tablero_terciarios.class);
            jugar.putExtra("gemas",gemas);
            jugar.putExtra("ids",Ids);
            jugar.putExtra("numeros de Oxidacion", NumerosOxi);
            jugar.putExtra("BiTer",BiTer);
            startActivity(jugar);
            finish();
        }
    }
}
