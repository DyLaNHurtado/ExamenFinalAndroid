package com.example.examenfinalandroid.fragmentos;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.examenfinalandroid.ElementoSeleccionado;
import com.example.examenfinalandroid.R;
import com.example.examenfinalandroid.adapters.RecetaAdapter;
import com.example.examenfinalandroid.data.DataRoomDB;
import com.example.examenfinalandroid.model.CategoriaEntity;
import com.example.examenfinalandroid.model.RecetaEntity;

import java.util.ArrayList;
import java.util.List;

public class ListaReceta extends Fragment {

    ImageView imageAddReceta;

    DataRoomDB database;
    CategoriaEntity categoriaElegida;

    RecyclerView recyclerReceta;
    List<RecetaEntity> recetaEntities = new ArrayList<>();
    RecetaAdapter recetaAdapter;
    LinearLayoutManager llm;

    String opcionSpinner;
    int dificultadReceta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = DataRoomDB.getInstance(getContext());

        categoriaElegida = ElementoSeleccionado.getInstance().getCategoria();
        System.out.println(categoriaElegida.getNombre());
        recetaEntities = database.recetaDao().selectByCategoria(categoriaElegida.getNombre());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_receta, container, false);

        imageAddReceta = v.findViewById(R.id.imageAddReceta);
        recyclerReceta = v.findViewById(R.id.recyclerReceta);

        llm = new LinearLayoutManager(getContext());
        recyclerReceta.setLayoutManager(llm);
        recetaAdapter = new RecetaAdapter(recetaEntities, getActivity());
        recyclerReceta.setAdapter(recetaAdapter);

        imageAddReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_new_receta);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.show();
                dialog.getWindow().setAttributes(lp);

                EditText editTextNombreReceta = dialog.findViewById(R.id.editTextNombreReceta);
                EditText editTextDuracionReceta = dialog.findViewById(R.id.editTextDuracionReceta);
                EditText editTextIngredientesReceta = dialog.findViewById(R.id.editTextIngredientesReceta);
                EditText editTextElaboracionReceta = dialog.findViewById(R.id.editTextElaboracionReceta);
                Spinner spinnerCategoria = dialog.findViewById(R.id.spinnerCategoria);
                RadioGroup radioGroupDificultad = dialog.findViewById(R.id.radioGroupDificultad);

                Button btnCancelarReceta = dialog.findViewById(R.id.btnCancelarReceta);
                Button btnAgregarReceta = dialog.findViewById(R.id.btnAgregarReceta);

                ArrayAdapter adapterSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, database.categoriaDao().getNombreCategorias());
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategoria.setAdapter(adapterSpinner);

                spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        opcionSpinner = adapterSpinner.getItem(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                radioGroupDificultad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.radioButtonFacil:
                                dificultadReceta = 1;
                                break;
                            case R.id.radioButtonNormal:
                                dificultadReceta = 2;
                                break;
                            case R.id.radioButtonDificil:
                                dificultadReceta = 3;
                        }
                    }
                });

                btnCancelarReceta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnAgregarReceta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RecetaEntity receta = new RecetaEntity();

                        receta.setFoto(R.drawable.imagen_comida);
                        receta.setNombre(editTextNombreReceta.getText().toString());
                        receta.setTiempoRealizacion(editTextDuracionReceta.getText().toString());
                        receta.setIngredientes(editTextIngredientesReceta.getText().toString());
                        receta.setElaboracion(editTextElaboracionReceta.getText().toString());
                        receta.setCategoria(opcionSpinner);
                        receta.setDificultad(dificultadReceta);

                        dialog.dismiss();

                        long resultado = database.recetaDao().insertReceta(receta);
                        Log.i("insert() = ", "" + resultado); // Comprobacion

                        recetaEntities.clear();
                        recetaEntities = database.recetaDao().selectByCategoria(categoriaElegida.getNombre());
                        recetaAdapter = new RecetaAdapter(recetaEntities, getActivity());
                        recyclerReceta.setAdapter(recetaAdapter);

                        System.out.println("Click");
                    }
                });

            }
        });

        return v;
    }
}