package com.example.mc_masterchemistry;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mc_masterchemistry.db.DAO.ElementoDao;
import com.example.mc_masterchemistry.db.ElementoRoomDatabase;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;


import java.util.List;

public class ElementoRepository {

    private final LiveData<List<ElementoEntity>> allElementos;

    public ElementoRepository(Application application) {
        ElementoRoomDatabase db = ElementoRoomDatabase.getRoomDataBase(application);
        ElementoDao dao = db.DAO();
        allElementos= dao.getAllElementos();
        }
    public LiveData<List<ElementoEntity>> getAll(){
        return  allElementos;}






}
