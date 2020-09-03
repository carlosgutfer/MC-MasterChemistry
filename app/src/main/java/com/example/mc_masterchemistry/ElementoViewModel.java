package com.example.mc_masterchemistry;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.List;


public class ElementoViewModel extends AndroidViewModel {
    private ElementoRepository mRepository;
    private LiveData<List<ElementoEntity>> allElementos;

    public ElementoViewModel( @NonNull Application application) {
        super(application);
        mRepository = new ElementoRepository(application);
        allElementos=mRepository.getAll();
    }

    public LiveData<List<ElementoEntity>> getAllElementos(){
        return  allElementos;}




}
