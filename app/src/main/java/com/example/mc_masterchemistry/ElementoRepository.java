package com.example.mc_masterchemistry;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mc_masterchemistry.db.DAO.ElementoDao;
import com.example.mc_masterchemistry.db.ElementoRoomDatabase;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;


import java.util.List;

public class ElementoRepository {

    private ElementoDao Dao;
    private LiveData<List<ElementoEntity>> allElementos;

    public ElementoRepository(Application application) {
        ElementoRoomDatabase db = ElementoRoomDatabase.getRoomDataBase(application);
        Dao=db.DAO();
        allElementos=Dao.getAllElementos();

        }



        public LiveData<List<ElementoEntity>> getAll(){
            return  allElementos;}






}
