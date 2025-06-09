package com.example.proyectoextraordinario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends BaseAdapter {

    private Context context;
    private List<Jugador> jugador;
    private List<Estadisticas> estadisticas;

    public Adaptador(Context context, List<Jugador> jugador, List<Estadisticas> estadisticas) {
        this.context = context;
        this.jugador = jugador;
        this.estadisticas = estadisticas;
    }

    @Override
    public int getCount() {
        return jugador.size();
    }

    @Override
    public Object getItem(int position) {
        return jugador.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item, null);

        ImageView fotoJugador = convertView.findViewById(R.id.fotoJugadorID);
        ImageView escudo = convertView.findViewById(R.id.escudoJugadorID);
        TextView valoracion = convertView.findViewById(R.id.valorCartaID);
        TextView posicion = convertView.findViewById(R.id.posicionID);
        TextView nombre = convertView.findViewById(R.id.nombreJugadorID);
        TextView valorMercado = convertView.findViewById(R.id.precioID);

        Picasso.get().load(jugador.get(position).getFoto()).into(fotoJugador);
        Picasso.get().load(jugador.get(position).getEscudo()).into(escudo);
        nombre.setText(jugador.get(position).getNombre());
        posicion.setText(jugador.get(position).getPosicion());
        valoracion.setText(jugador.get(position).getValor_carta());
        valorMercado.setText(jugador.get(position).getValor_mercado());

        return convertView;
    }
}
