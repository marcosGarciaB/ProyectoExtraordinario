package com.example.proyectoextraordinario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Integer> botonSeleccionado = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Jugador>> jugadoresComprados = new MutableLiveData<>();

    public void setBotonSeleccionado(int botonID) {
        botonSeleccionado.setValue(botonID);
    }

    public LiveData<Integer> getBotonSeleccionado() {
        return botonSeleccionado;
    }

    public void setJugadorComprado(Jugador jugador) {
        ArrayList<Jugador> listaActual = jugadoresComprados.getValue();
        if (listaActual == null) {
            listaActual = new ArrayList<>();
        }
        listaActual.add(jugador);
        jugadoresComprados.setValue(new ArrayList<>(listaActual));
    }

    public LiveData<ArrayList<Jugador>> getJugadoresComprados() {
        return jugadoresComprados;
    }

}
