package com.example.examenfinalandroid.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenfinalandroid.ElementoSeleccionado;
import com.example.examenfinalandroid.R;
import com.example.examenfinalandroid.model.RecetaEntity;

public class VerReceta extends Fragment {

    ImageView imageRecetaDetalle, imageDificultadDetalle;
    TextView tituloRecetaDetalle, tiempoRecetaDetalle, ingredientesRecetaDetalle, elaboracionRecetaDetalles;
    Button btnVolverReceta;

    RecetaEntity recetaElegida;
    int dificultadReceta;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recetaElegida = ElementoSeleccionado.getInstance().getReceta();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver_receta, container, false);

        imageRecetaDetalle = v.findViewById(R.id.imageRecetaDetalle);
        imageDificultadDetalle = v.findViewById(R.id.imageDificultadDetalle);

        tituloRecetaDetalle = v.findViewById(R.id.tituloRecetaDetalle);
        tiempoRecetaDetalle = v.findViewById(R.id.tiempoRecetaDetalle);
        ingredientesRecetaDetalle = v.findViewById(R.id.ingredientesRecetaDetalle);
        elaboracionRecetaDetalles = v.findViewById(R.id.elaboracionRecetaDetalle);

        btnVolverReceta = v.findViewById(R.id.btnVolverReceta);

        imageRecetaDetalle.setImageResource(recetaElegida.getFoto());

        dificultadReceta = recetaElegida.getDificultad();
        switch (dificultadReceta){
            case 1:
                imageDificultadDetalle.setImageResource(R.drawable.icon_easy);
                break;
            case 2:
                imageDificultadDetalle.setImageResource(R.drawable.icon_normal);
                break;
            case 3:
                imageDificultadDetalle.setImageResource(R.drawable.icon_hard);
        }

        tituloRecetaDetalle.setText(recetaElegida.getNombre());
        tiempoRecetaDetalle.setText(recetaElegida.getTiempoRealizacion());
        ingredientesRecetaDetalle.setText(recetaElegida.getIngredientes());
        elaboracionRecetaDetalles.setText(recetaElegida.getElaboracion());

        return v;
    }
}