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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examenfinalandroid.R;
import com.example.examenfinalandroid.adapters.CategoriaAdapter;
import com.example.examenfinalandroid.data.DataRoomDB;
import com.example.examenfinalandroid.model.CategoriaEntity;

import java.util.ArrayList;
import java.util.List;

public class ListaCategoria extends Fragment {

    ImageView imageAddCategoria;

    DataRoomDB database;

    RecyclerView recyclerCategoria;
    List<CategoriaEntity> categoriaEntities = new ArrayList<>();
    CategoriaAdapter categoriaAdapter;
    LinearLayoutManager llm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = DataRoomDB.getInstance(getContext());
        categoriaEntities = database.categoriaDao().getCategorias();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_categoria, container, false);

        imageAddCategoria = v.findViewById(R.id.imageAddCategoria);
        recyclerCategoria = v.findViewById(R.id.recyclerCategoria);

        llm = new LinearLayoutManager(getContext());
        recyclerCategoria.setLayoutManager(llm);
        categoriaAdapter = new CategoriaAdapter(categoriaEntities, getActivity());
        recyclerCategoria.setAdapter(categoriaAdapter);

        imageAddCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_new_categoria);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.show();
                dialog.getWindow().setAttributes(lp);

                EditText editTextNewCategoria = dialog.findViewById(R.id.editTextNewCategoria);
                Button btnCancelarCategoria = dialog.findViewById(R.id.btnCancelarCategoria);
                Button btnAgregarCategoria = dialog.findViewById(R.id.btnAgregarCategoria);

                btnCancelarCategoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnAgregarCategoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CategoriaEntity categoria = new CategoriaEntity();

                        categoria.setFoto(R.drawable.imagen_cocina);
                        categoria.setNombre(editTextNewCategoria.getText().toString());

                        dialog.dismiss();

                        long resultado = database.categoriaDao().insertCategoria(categoria);
                        Log.i("insert() = ", "" + resultado); // Comprobacion

                        categoriaEntities = database.categoriaDao().getCategorias();
                        categoriaAdapter = new CategoriaAdapter(categoriaEntities, getActivity());
                        recyclerCategoria.setAdapter(categoriaAdapter);

                        Toast.makeText(getContext(), "Categoria Agregada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return v;
    }
}