package com.example.mc_masterchemistry;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;

import java.util.List;

public class tiposViewModel extends AndroidViewModel {

    private TiposRepository mRepository;
    private LiveData<List<InorganicaEntity>> allinorganica;


    public tiposViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TiposRepository(application);
        allinorganica=mRepository.getAll();
    }

    public LiveData<List<InorganicaEntity>> getAllElementos(){
        return  allinorganica;}

}
