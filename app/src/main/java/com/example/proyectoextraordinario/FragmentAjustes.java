package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentAjustes extends Fragment {

    private Button btCambiarNombre, btCambiarEquipo, btCambiarIdioma, btModoOscuro, btSalir;
    private SwitchCompat switchPartidos, switchNoticias;

    public FragmentAjustes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        btCambiarNombre = view.findViewById(R.id.btCambiarNombreID);
        btCambiarEquipo = view.findViewById(R.id.btCambiarEquipoID);
        btCambiarIdioma = view.findViewById(R.id.btCambiarIdiomaID);
        btModoOscuro = view.findViewById(R.id.btModoOscuroID);
        btSalir = view.findViewById(R.id.btSalirID);

        switchPartidos = view.findViewById(R.id.switchPartidosID);
        switchNoticias = view.findViewById(R.id.switchNoticiasID);

        btCambiarIdioma.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        });

        return view;
    }
}