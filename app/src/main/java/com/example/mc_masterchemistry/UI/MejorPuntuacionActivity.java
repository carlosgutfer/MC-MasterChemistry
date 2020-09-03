package com.example.mc_masterchemistry.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.commun.Constants;
import com.example.mc_masterchemistry.commun.SharePreferences;

public class MejorPuntuacionActivity extends AppCompatActivity {
    private TextView puntuaciones;
    private int total, mejorPuntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mejor_puntuacion);
        puntuaciones=findViewById(R.id.TV_RegsitroGemas);

        mejorPuntuacion=SharePreferences.getIntegerValue(Constants.BEST_SCORE);
        total=SharePreferences.getIntegerValue(Constants.TOTAL_SCORE);
        if(total!=-1) {
            puntuaciones.setText("La mejor puntuación obtenida es: "+mejorPuntuacion+'\n'+
                                    '\n'+ "El número total de gemas obtenidas es: "+total);

        }

    }


}