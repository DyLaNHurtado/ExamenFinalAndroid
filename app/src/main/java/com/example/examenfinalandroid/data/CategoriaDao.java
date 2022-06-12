package com.example.examenfinalandroid.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.examenfinalandroid.model.CategoriaEntity;

import java.util.List;

@Dao
public interface CategoriaDao {

    @Insert
    long insertCategoria(CategoriaEntity c);

    @Query("SELECT * FROM tabla_categorias")
    List<CategoriaEntity> getCategorias();

    @Query("SELECT nombre FROM tabla_categorias")
    String[] getNombreCategorias();

}
