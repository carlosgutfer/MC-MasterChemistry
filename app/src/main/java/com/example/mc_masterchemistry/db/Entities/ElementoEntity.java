package com.example.mc_masterchemistry.db.Entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName="Elementos")

public class ElementoEntity {
    @PrimaryKey
    @NonNull
    private final String nombre;
    private final int NO1,NO2,NO3,NO4, id;
    private final int IM1, IM2, IM3, IM4;
    private final String simbolo;

    public ElementoEntity(@NonNull String nombre, int id, int NO1, int NO2, int NO3, int NO4, int IM1, int IM2, int IM3, int IM4, String simbolo) {
        this.nombre = nombre;
        this.id = id;
        this.NO1 = NO1;
        this.NO2 = NO2;
        this.NO3 = NO3;
        this.NO4 = NO4;
        this.IM1 = IM1;
        this.IM2 = IM2;
        this.IM3 = IM3;
        this.IM4 = IM4;
        this.simbolo = simbolo;
    }

    public int getIM1() {
        return IM1;
    }



    public int getIM2() {
        return IM2;
    }


    public int getIM3() {
        return IM3;
    }


    public int getIM4() {
        return IM4;
    }


    public String getSimbolo() {
        return simbolo;
    }


    public int getId() {
        return id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public int getNO1() {
        return NO1;
    }


    public int getNO2() {
        return NO2;
    }


    public int getNO3() {
        return NO3;
    }


    public int getNO4() {
        return NO4;
    }

}
