package com.example.examenfinalandroid.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.examenfinalandroid.model.RecetaEntity;

import java.util.List;

@Dao
public interface RecetaDao {

    @Insert
    long insertReceta(RecetaEntity r);

    @Query("SELECT * FROM tabla_recetas")
    List<RecetaEntity> getRecetas();

    @Query("SELECT * FROM tabla_recetas where categoria = :mCategoria")
    List<RecetaEntity> selectByCategoria(String mCategoria);
}
