package com.example.mc_masterchemistry.db.Entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Tipos")
public class InorganicaEntity {
    @PrimaryKey
    @NonNull
    private final String nombre;
    private final Integer URL_Imagen_nombre;
    private final String datos;

    public InorganicaEntity(@NonNull String nombre, Integer URL_Imagen_nombre, String datos) {
        this.nombre = nombre;
        this.URL_Imagen_nombre = URL_Imagen_nombre;
        this.datos=datos;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }
    public Integer getURL_Imagen_nombre() {
        return URL_Imagen_nombre;
    }
    public String getDatos() {
        return datos;
    }
}
