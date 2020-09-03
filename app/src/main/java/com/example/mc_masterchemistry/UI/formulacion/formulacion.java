package com.example.mc_masterchemistry.UI.formulacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.mc_masterchemistry.R;
import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;


public class formulacion extends AppCompatActivity implements inorganicaFragment.OnListFragmentInteractionListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulacion);



    }

    @Override
    public void onListFragmentInteraction(InorganicaEntity item) {

    }


}
