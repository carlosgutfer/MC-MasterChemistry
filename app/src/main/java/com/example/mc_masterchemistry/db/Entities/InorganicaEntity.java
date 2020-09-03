package com.example.mc_masterchemistry.db.Entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Tipos")
public class InorganicaEntity {
    @PrimaryKey
    @NonNull
    private String nombre;
    private Integer URL_Imagen_nombre;
    private String datos;

    public InorganicaEntity(String nombre, Integer URL_Imagen_nombre, String datos) {
        this.nombre = nombre;
        this.URL_Imagen_nombre = URL_Imagen_nombre;
        this.datos=datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getURL_Imagen_nombre() {
        return URL_Imagen_nombre;
    }

    public void setURL_Imagen_nombre(Integer URL_Imagen_nombre) {
        this.URL_Imagen_nombre = URL_Imagen_nombre;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}
