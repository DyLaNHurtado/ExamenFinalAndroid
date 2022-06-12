package com.example.examenfinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.examenfinalandroid.fragmentos.ListaCategoria;

/*
    Clase para la actividad principal.

    Cada clase tiene ciertos comentarios de ayuda.
 */
public class MainActivity extends AppCompatActivity {

    Button btnVerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reemplazarFragmentos(new ListaCategoria());

        btnVerMain = findViewById(R.id.btnVerMain);
        btnVerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    // Metodo que reemplaza el fragmento en el frameLayout de la Actividad.
    // aunque en este caso, solo lo hace 1 vez, cada vez que se inicia la APP o se pulsa sobre el btnVerMain
    private void reemplazarFragmentos(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragLayout, f);
        ft.commit();
    }


}