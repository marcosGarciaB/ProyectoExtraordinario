package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class ActivityVenta extends AppCompatActivity {
    private ListView listView;
    private AdaptadorPlantilla adapter;
    private Button btVolver2;
    private TextView textoVacio;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_venta);

        textoVacio = findViewById(R.id.texto_vacio_venta);
        listView = findViewById(R.id.listaJugadoresSiguiendo_venta);
        btVolver2 = findViewById(R.id.btVolver2ID_venta);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setJugadoresCompradosDesdePreferencias(getApplicationContext());

        adapter = new AdaptadorPlantilla(this, new ArrayList<>(), true, sharedViewModel);
        listView.setAdapter(adapter);

        sharedViewModel.getJugadoresComprados().observe(this, jugadoresObservados  -> {
            adapter.actualizarLista(jugadoresObservados);

            if (jugadoresObservados.isEmpty()) {
                textoVacio.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            } else {
                textoVacio.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });

        btVolver2.setOnClickListener(v -> finish());
    }
}