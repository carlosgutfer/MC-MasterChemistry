package com.example.mc_masterchemistry;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mc_masterchemistry.db.DAO.InorganicaDao;
import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;
import com.example.mc_masterchemistry.db.TiposRoomDatabase;

import java.util.List;

public class TiposRepository {
    private InorganicaDao Dao;
    private LiveData<List<InorganicaEntity>> allElementos;


    public TiposRepository(Application application) {
       TiposRoomDatabase db = TiposRoomDatabase.getRoomDataBase(application);
        Dao=db.DAO();
        allElementos=Dao.getallInorganica();
    }

    public LiveData<List<InorganicaEntity>> getAll(){
        return  allElementos;}


}
