package com.example.examenfinalandroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalandroid.ElementoSeleccionado;
import com.example.examenfinalandroid.R;
import com.example.examenfinalandroid.fragmentos.VerReceta;
import com.example.examenfinalandroid.model.RecetaEntity;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {

    private List<RecetaEntity> recetaEntitiesList;
    private Activity context;

    public RecetaAdapter(List<RecetaEntity> recetaEntitiesList, Activity context) {
        this.recetaEntitiesList = recetaEntitiesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receta_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecetaEntity item = recetaEntitiesList.get(position);

        holder.textViewRecetaLista.setText(item.getNombre());
        holder.imageViewRecetaLista.setImageResource(item.getFoto());

        int opcionRadioGroup = item.getDificultad();

        switch (opcionRadioGroup){
            case 1:
                holder.imageDificultadLista.setImageResource(R.drawable.icon_easy);
                break;
            case 2:
                holder.imageDificultadLista.setImageResource(R.drawable.icon_normal);
                break;
            case 3:
                holder.imageDificultadLista.setImageResource(R.drawable.icon_hard);
        }

    }

    @Override
    public int getItemCount() {
        return recetaEntitiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutRecetaLista;
        TextView textViewRecetaLista;
        ImageView imageDificultadLista, imageViewRecetaLista;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutRecetaLista = itemView.findViewById(R.id.layoutRecetaView);
            textViewRecetaLista = itemView.findViewById(R.id.textViewRecetaLista);
            imageDificultadLista = itemView.findViewById(R.id.imageDificultadLista);
            imageViewRecetaLista = itemView.findViewById(R.id.imageViewRecetaLista);

            onClickDetalle();
        }

        private void onClickDetalle(){
            layoutRecetaLista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ElementoSeleccionado.getInstance().setReceta(recetaEntitiesList.get(getAdapterPosition()));
                    Toast.makeText(context, "Receta seleccionada: " + recetaEntitiesList.get(getAdapterPosition()).getNombre(), Toast.LENGTH_SHORT).show();

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, new VerReceta()).addToBackStack(null).commit();
                }
            });
        }
    }
}
