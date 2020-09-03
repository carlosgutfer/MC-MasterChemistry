package com.example.mc_masterchemistry.db.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mc_masterchemistry.db.Entities.InorganicaEntity;

import java.util.List;

@Dao
public interface InorganicaDao {

    @Insert void insert(InorganicaEntity tipo);

    @Query("SELECT * FROM Tipos")
    LiveData<List<InorganicaEntity>> getallInorganica();

    @Query("DELETE FROM Tipos")
    void deleteAll();

}
