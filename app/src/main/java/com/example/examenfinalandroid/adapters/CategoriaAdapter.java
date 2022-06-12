package com.example.examenfinalandroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalandroid.R;
import com.example.examenfinalandroid.model.CategoriaEntity;

import java.util.List;

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
        holder.imageViewCategoriaLista.setImageResource(R.drawable.imagen_cocina);
    }

    @Override
    public int getItemCount() {
        return categoriaEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoriaLista;
        ImageView imageViewCategoriaLista;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCategoriaLista = itemView.findViewById(R.id.textViewCategoriaLista);
            imageViewCategoriaLista = itemView.findViewById(R.id.imageViewCategoriaLista);
        }
    }
}
