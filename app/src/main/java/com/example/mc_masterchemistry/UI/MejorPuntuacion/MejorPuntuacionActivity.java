package com.example.mc_masterchemistry.UI.MejorPuntuacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.commun.Constants;
import com.example.mc_masterchemistry.commun.SharePreferences;

public class MejorPuntuacionActivity extends AppCompatActivity implements MejorPuntuacionFragment.OnListFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mejor_puntuacion);

    }


}