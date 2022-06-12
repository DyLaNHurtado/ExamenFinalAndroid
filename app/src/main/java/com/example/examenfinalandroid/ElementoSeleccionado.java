package com.example.examenfinalandroid;

import com.example.examenfinalandroid.model.CategoriaEntity;
import com.example.examenfinalandroid.model.RecetaEntity;

public class ElementoSeleccionado {

    private static ElementoSeleccionado elementoSeleccionado;
    private ElementoSeleccionado(){};

    public static ElementoSeleccionado getInstance(){
        if(elementoSeleccionado == null)
            elementoSeleccionado = new ElementoSeleccionado();
        return elementoSeleccionado;
    }

    private CategoriaEntity categoria = null;
    private RecetaEntity receta = null;

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public RecetaEntity getReceta() {
        return receta;
    }

    public void setReceta(RecetaEntity receta) {
        this.receta = receta;
    }
}
