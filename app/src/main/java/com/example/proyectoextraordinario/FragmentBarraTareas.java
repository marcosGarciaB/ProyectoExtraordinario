package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentBarraTareas extends Fragment {

    private ImageButton btPlantilla, btMercado, btEscudo, btHome, btAjustes;
    private SharedViewModel sharedViewModel;

    public FragmentBarraTareas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barra_tareas, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        btPlantilla = view.findViewById(R.id.plantillaID);
        btMercado = view.findViewById(R.id.mercadoID);
        btEscudo = view.findViewById(R.id.escudoID);
        btHome = view.findViewById(R.id.homeID);
        btAjustes = view.findViewById(R.id.ajustesID);

        btHome.setOnClickListener(v -> sharedViewModel.setBotonSeleccionado(1));
        btEscudo.setOnClickListener(v -> sharedViewModel.setBotonSeleccionado(2));
        btMercado.setOnClickListener(v -> sharedViewModel.setBotonSeleccionado(3));
        btPlantilla.setOnClickListener(v -> sharedViewModel.setBotonSeleccionado(4));
        btAjustes.setOnClickListener(v -> sharedViewModel.setBotonSeleccionado(5));

        return view;
    }
}