package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class ActivitySiguiendo extends AppCompatActivity {

    private ListView listView;
    private AdaptadorPlantilla adapter;
    private Button btVolver2;
    private TextView textoVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_siguiendo);

        textoVacio = findViewById(R.id.texto_vacio);
        listView = findViewById(R.id.listaJugadoresSiguiendo);
        btVolver2 = findViewById(R.id.btVolver2ID);

        ArrayList<Jugador> jugadores = Preferencias.obtenerJugadoresSeguidos(getApplicationContext());

        adapter = new AdaptadorPlantilla(this, jugadores, false);
        listView.setAdapter(adapter);

        if (jugadores.isEmpty()) {
            textoVacio.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);

        } else {
            textoVacio.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
        btVolver2.setOnClickListener(v -> finish());
    }
}