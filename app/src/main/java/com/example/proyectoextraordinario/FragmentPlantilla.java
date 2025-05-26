package com.example.proyectoextraordinario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentPlantilla extends Fragment {
    private ArrayList<View> titularesViews = new ArrayList<>();
    private ArrayList<View> banquilloViews = new ArrayList<>();
    private SharedViewModel sharedViewModel;

    public FragmentPlantilla() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plantilla, container, false);

        int[] titularesIds = {R.id.jugador1, R.id.jugador2, R.id.jugador3, R.id.jugador4, R.id.jugador5};
        int[] banquilloIds = {R.id.banquillo1, R.id.banquillo2, R.id.banquillo3};

        for (int id : titularesIds) {
            FrameLayout frame = view.findViewById(id);
            View jugadorView = inflater.inflate(R.layout.jugador_plantilla, frame, false);
            frame.addView(jugadorView);
            titularesViews.add(jugadorView);
        }

        for (int id : banquilloIds) {
            FrameLayout frame = view.findViewById(id);
            View jugadorView = inflater.inflate(R.layout.jugador_plantilla, frame, false);
            frame.addView(jugadorView);
            banquilloViews.add(jugadorView);
        }

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getJugadoresComprados().observe(getViewLifecycleOwner(), jugadores -> {

            for (int i = 0; i < titularesViews.size(); i++) {
                if (i < jugadores.size()) {
                    mostrarJugadorEnVista(titularesViews.get(i), jugadores.get(i));
                } else {
                    limpiarVistaJugador(titularesViews.get(i));
                }
            }

            for (int i = 0; i < banquilloViews.size(); i++) {
                int index = i + 5;
                if (index < jugadores.size()) {
                    mostrarJugadorEnVista(banquilloViews.get(i), jugadores.get(index));
                } else {
                    limpiarVistaJugador(banquilloViews.get(i));
                }
            }
        });

        return view;
    }

    private void mostrarJugadorEnVista(View jugadorView, Jugador jugador) {
        ImageView foto = jugadorView.findViewById(R.id.fotoJugadorID);
        TextView texto = jugadorView.findViewById(R.id.nombreJugadorID);

        Picasso.get().load(jugador.getFoto()).into(foto);

        String descripcion = jugador.getPosicion() + "\n" +
                jugador.getValor_carta() + "\n" +
                jugador.getNombre();
        texto.setText(descripcion);
    }

    private void limpiarVistaJugador(View jugadorView) {
        ImageView foto = jugadorView.findViewById(R.id.fotoJugadorID);
        TextView texto = jugadorView.findViewById(R.id.nombreJugadorID);

        foto.setImageResource(R.drawable.ic_launcher_foreground);
        texto.setText("");
    }


}