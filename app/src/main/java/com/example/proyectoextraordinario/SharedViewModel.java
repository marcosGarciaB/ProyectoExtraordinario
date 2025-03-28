package com.example.proyectoextraordinario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Integer> botonSeleccionado = new MutableLiveData<>();

    public void setBotonSeleccionado(int botonID) {
        botonSeleccionado.setValue(botonID);
    }

    public LiveData<Integer> getBotonSeleccionado() {
        return botonSeleccionado;
    }
}
