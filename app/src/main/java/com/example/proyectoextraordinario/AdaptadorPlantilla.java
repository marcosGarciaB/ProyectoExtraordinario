package com.example.proyectoextraordinario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPlantilla extends BaseAdapter {

    private Context context;
    private ArrayList<Jugador> jugadores;

    public AdaptadorPlantilla(Context context, ArrayList<Jugador> jugadores) {
        this.context = context;
        this.jugadores = jugadores;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.jugador_plantilla, parent, false);
        }

        ImageView fotoJugador = convertView.findViewById(R.id.fotoJugadorID);
        TextView nombreJugador = convertView.findViewById(R.id.nombreJugadorID);

        Jugador jugador = jugadores.get(position);

        nombreJugador.setText(jugador.getNombre());
        Picasso.get().load(jugador.getFoto()).placeholder(R.drawable.ic_launcher_foreground).into(fotoJugador);

        return convertView;
    }

    public void actualizarJugadores(ArrayList<Jugador> nuevosJugadores) {
        this.jugadores = nuevosJugadores;
        notifyDataSetChanged();
    }
}