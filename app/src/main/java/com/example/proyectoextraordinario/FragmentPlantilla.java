package com.example.proyectoextraordinario;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FragmentPlantilla extends Fragment {

    private SharedViewModel sharedViewModel;
    private TableLayout tableTitulares, tableSuplentes;
    private TextView textTitulares, textBanquillo;
    public FragmentPlantilla() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plantilla, container, false);

        tableTitulares = view.findViewById(R.id.tableLayoutTitulares);
        tableSuplentes = view.findViewById(R.id.tableLayoutSuplentes);
        textTitulares = view.findViewById(R.id.tvTitularesID);
        textBanquillo = view.findViewById(R.id.tvBanquilloID);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.setJugadoresCompradosDesdePreferencias(requireContext());

        sharedViewModel.getJugadoresComprados().observe(getViewLifecycleOwner(), jugadoresObservados -> {
            tableTitulares.removeAllViews();
            tableSuplentes.removeAllViews();

            int titularesCount = Math.min(jugadoresObservados.size(), 5);
            int suplentesCount = jugadoresObservados.size() - titularesCount;

            textTitulares.setVisibility(titularesCount > 0 ? View.VISIBLE : View.GONE);
            textBanquillo.setVisibility(suplentesCount > 0 ? View.VISIBLE : View.GONE);

            for (int i = 0; i < jugadoresObservados.size(); i += 2) {
                TableRow fila = new TableRow(requireContext());
                fila.setPadding(0, 16, 0, 16);
                fila.setGravity(Gravity.CENTER);

                for (int j = i; j < i + 2 && j < jugadoresObservados.size() && j < 5; j++) {
                    Jugador jugador = jugadoresObservados.get(j);
                    View celda = crearVistaJugador(jugador, inflater);
                    fila.addView(celda);
                }

                if (i < 6) tableTitulares.addView(fila);
            }

            //Suplentes.
            for (int i = 5; i < jugadoresObservados.size(); i++) {
                TableRow fila = new TableRow(requireContext());
                fila.setGravity(Gravity.CENTER);
                View celda = crearVistaJugador(jugadoresObservados.get(i), inflater);
                fila.addView(celda);
                tableSuplentes.addView(fila);
            }
        });
        return view;
    }

    private View crearVistaJugador(Jugador jugador, LayoutInflater inflater) {
        //Contenedor principal.
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(15, 15, 15, 15);
        layout.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
        ));

        //Izquierda.
        LinearLayout izquierda = new LinearLayout(requireContext());
        izquierda.setOrientation(LinearLayout.VERTICAL);
        izquierda.setGravity(Gravity.CENTER_HORIZONTAL);
        izquierda.setPadding(5, 0, 8, 0);

        TextView posicion = new TextView(requireContext());
        posicion.setText(jugador.getPosicion());
        posicion.setTextColor(Color.WHITE);
        posicion.setTextSize(15);
        posicion.setTypeface(null, Typeface.BOLD);

        ImageView escudo = new ImageView(requireContext());
        escudo.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        Picasso.get().load(jugador.getEscudo()).into(escudo);

        izquierda.addView(posicion);
        izquierda.addView(escudo);

        //Centro.
        LinearLayout centro = new LinearLayout(requireContext());
        centro.setOrientation(LinearLayout.VERTICAL);
        centro.setGravity(Gravity.CENTER_HORIZONTAL);
        centro.setPadding(5, 0, 8, 0);

        ImageView foto = new ImageView(requireContext());
        foto.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        Picasso.get().load(jugador.getFoto()).into(foto);

        TextView nombre = new TextView(requireContext());
        nombre.setText(jugador.getNombre());
        nombre.setTextColor(Color.WHITE);
        nombre.setTextSize(15);
        nombre.setHeight(50);
        nombre.setWidth(100);
        nombre.setTypeface(null, Typeface.BOLD);
        nombre.setGravity(Gravity.CENTER);

        centro.addView(foto);
        centro.addView(nombre);

        //Derecha.
        TextView media = new TextView(requireContext());
        media.setText(String.valueOf(jugador.getValor_carta()));
        media.setTextColor(Color.WHITE);
        media.setTextSize(15);
        media.setTypeface(null, Typeface.BOLD);
        media.setPadding(5, 0, 0, 0);

        layout.addView(izquierda);
        layout.addView(centro);
        layout.addView(media);

        // Animación: fade in + traslación desde abajo
        layout.setAlpha(0f);
        layout.setTranslationY(50); // ligeramente desplazado hacia abajo
        layout.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(500)
                .start();
        return layout;
    }
}