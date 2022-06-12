package com.example.examenfinalandroid.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_recetas")
public class RecetaEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public String nombre;
    public int foto;
    public String tiempoRealizacion;
    public int dificultad;
    public String categoria;
    public String ingredientes;
    public String elaboracion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTiempoRealizacion() {
        return tiempoRealizacion;
    }

    public void setTiempoRealizacion(String tiempoRealizacion) {
        this.tiempoRealizacion = tiempoRealizacion;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }
}
