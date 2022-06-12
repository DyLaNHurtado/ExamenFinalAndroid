package com.example.examenfinalandroid.utils;

import com.example.examenfinalandroid.model.CategoriaEntity;
import com.example.examenfinalandroid.model.RecetaEntity;

// Clase util que almacena ciertos datos.
// Es parecida a la clase abstracta de la BBDD.
public class ElementoSeleccionado {

    // Creamos un objeto static de la clase + constructor vacio
    private static ElementoSeleccionado elementoSeleccionado;
    private ElementoSeleccionado(){};

    // Creamos metodo para obtener la instancia
    public static ElementoSeleccionado getInstance(){
        if(elementoSeleccionado == null)
            elementoSeleccionado = new ElementoSeleccionado();
        return elementoSeleccionado;
    }

    // Creamos las entidades donde se almacenaran de forma temporal
    private CategoriaEntity categoria = null;
    private RecetaEntity receta = null;

    // GET & SET
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
