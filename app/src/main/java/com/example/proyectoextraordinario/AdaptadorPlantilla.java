package com.example.proyectoextraordinario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPlantilla extends BaseAdapter {

    private Context context;
    private ArrayList<Jugador> jugadores;
    private boolean mostrarBoton;
    private SharedViewModel sharedViewModel;

    public AdaptadorPlantilla(Context context, ArrayList<Jugador> jugadores, boolean mostrarBoton) {
        this.context = context;
        this.jugadores = jugadores;
        this.mostrarBoton = mostrarBoton;
    }

    public AdaptadorPlantilla(Context context, ArrayList<Jugador> jugadores, boolean mostrarBoton, SharedViewModel sharedViewModel) {
        this.context = context;
        this.jugadores = jugadores;
        this.mostrarBoton = mostrarBoton;
        this.sharedViewModel = sharedViewModel;
    }

    @Override
    public int getCount() {
        return jugadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jugadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.jugador_plantilla, parent, false);

        ImageView fotoJugador = convertView.findViewById(R.id.fotoJugadorID);
        TextView nombreJugador = convertView.findViewById(R.id.nombreJugadorID);
        Button btVenta = convertView.findViewById(R.id.btVenderJugador);

        Jugador jugador = jugadores.get(position);

        nombreJugador.setText(jugador.getNombre());
        Picasso.get().load(jugador.getFoto()).into(fotoJugador);

        if (mostrarBoton) {
            btVenta.setVisibility(View.VISIBLE);
            btVenta.setOnClickListener(v -> {
                jugadores.remove(position);
                sharedViewModel.eliminarJugadorComprado(context, jugador);
                notifyDataSetChanged();
            });
        } else {
            btVenta.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void actualizarLista(ArrayList<Jugador> nuevosJugadores) {
        this.jugadores.clear();
        this.jugadores.addAll(nuevosJugadores);
        notifyDataSetChanged();
    }

}