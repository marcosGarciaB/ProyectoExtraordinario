package com.example.proyectoextraordinario;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Integer> botonSeleccionado = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Jugador>> jugadoresComprados = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Integer> dineroDisponible = new MutableLiveData<>();

    //Para los distintos fragments.
    public void setBotonSeleccionado(int botonID) {
        botonSeleccionado.setValue(botonID);
    }

    public LiveData<Integer> getBotonSeleccionado() {
        return botonSeleccionado;
    }

    //Para compra-venta de jugadores.
    public LiveData<ArrayList<Jugador>> getJugadoresComprados() {
        return jugadoresComprados;
    }

    public void setJugadoresCompradosDesdePreferencias(Context context) {
        ArrayList<Jugador> lista = Preferencias.obtenerJugadoresComprados(context);
        jugadoresComprados.setValue(lista);
    }

    public void agregarJugadorComprado(Context context, Jugador jugador) {
        ArrayList<Jugador> listaActual = jugadoresComprados.getValue();
        if (listaActual == null) listaActual = new ArrayList<>();

        if (!listaActual.contains(jugador)) {
            listaActual.add(jugador);
            jugadoresComprados.setValue(new ArrayList<>(listaActual));
            Preferencias.guardarJugadorComprado(context, jugador);
        }
    }

    public void eliminarJugadorComprado(Context context, Jugador jugador) {
        ArrayList<Jugador> listaActual = jugadoresComprados.getValue();
        if (listaActual != null && listaActual.contains(jugador)) {
            listaActual.remove(jugador);
            jugadoresComprados.setValue(new ArrayList<>(listaActual));
            Preferencias.eliminarJugadorComprado(context, jugador);
        }
    }

    public void setListaJugadoresComprados(ArrayList<Jugador> jugadores) {
        jugadoresComprados.setValue(jugadores);
    }

    //Para el dinero de la barra de tareas.
    public void dineroJugadorVendido(Context context, int dinero) {

    }

    public void dineroJugadorComprado(Context context, int dinero) {

    }

    public void obtenerDinero(Context context, int dinero) {
        dineroDisponible.setValue(dinero);
    }
}
