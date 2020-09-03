package com.example.mc_masterchemistry.db.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.List;


@Dao
public interface ElementoDao {

    @Insert  void insert(ElementoEntity Elemento);

    @Query("SELECT * FROM Elementos")
        LiveData<List<ElementoEntity>> getAllElementos();

    @Query("DELETE FROM Elementos")
     void deleteAll();


}
