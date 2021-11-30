package com.example.mc_masterchemistry;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mc_masterchemistry.db.DAO.InorganicaDao;
import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;
import com.example.mc_masterchemistry.db.TiposRoomDatabase;

import java.util.List;

public class TiposRepository
{
    private final LiveData<List<InorganicaEntity>> allElementos;

    public TiposRepository(Application application) {
        TiposRoomDatabase db = TiposRoomDatabase.getRoomDataBase(application);
        InorganicaDao dao = db.DAO();
        allElementos= dao.getallInorganica();
    }

    public LiveData<List<InorganicaEntity>> getAll(){
        return  allElementos;}


}
