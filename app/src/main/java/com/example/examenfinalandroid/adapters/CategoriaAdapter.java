package com.example.examenfinalandroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalandroid.utils.ElementoSeleccionado;
import com.example.examenfinalandroid.R;
import com.example.examenfinalandroid.fragmentos.ListaReceta;
import com.example.examenfinalandroid.model.CategoriaEntity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

// Clase para el recyclerView de Categorias
public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private List<CategoriaEntity> categoriaEntityList;
    private Activity context;

    public CategoriaAdapter(List<CategoriaEntity> categoriaEntityList, Activity context) {
        this.categoriaEntityList = categoriaEntityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CategoriaEntity item = categoriaEntityList.get(position);

        holder.textViewCategoriaLista.setText(item.getNombre());
        holder.imageViewCategoriaLista.setImageResource(item.getFoto());
    }

    @Override
    public int getItemCount() {
        return categoriaEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*
        Para esta ocasion, y asi evitar problemas, he llamado directamente a la interfaz del item_view,
        y he creado un metodo llamado onClick [por ejemplo], que sea capaz
        de obtener el elemento pulsado en la lista, y abriendo el nuevo fragmento correspondiente con un
        dato especifico (la categoria).
        Para ello, he creado una clase llamada "ElementoSeleccionado".
         */
        LinearLayout layoutCategoriaLista;
        TextView textViewCategoriaLista;
        ImageView imageViewCategoriaLista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutCategoriaLista = itemView.findViewById(R.id.layoutCategoriaView);
            textViewCategoriaLista = itemView.findViewById(R.id.textViewCategoriaLista);
            imageViewCategoriaLista = itemView.findViewById(R.id.imageViewCategoriaLista);

            onClick();
        }

        private void onClick() {
            layoutCategoriaLista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ElementoSeleccionado.getInstance().setCategoria(categoriaEntityList.get(getAdapterPosition()));
                    Snackbar.make(context.findViewById(R.id.layoutActivity), "Categoria seleccionada: " + categoriaEntityList.get(getAdapterPosition()).getNombre(), Snackbar.LENGTH_SHORT).show();

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, new ListaReceta()).addToBackStack(null).commit();

                }
            });
        }
    }
}
