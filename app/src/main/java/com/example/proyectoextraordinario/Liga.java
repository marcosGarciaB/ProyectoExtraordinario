package com.example.proyectoextraordinario;

import java.util.ArrayList;

public class Liga {
    private String nombre;
    private ArrayList<String> escudos;
    private ArrayList<String> equipos;

    public Liga(String nombre, ArrayList<String> escudos, ArrayList<String> equipos) {
        this.nombre = nombre;
        this.escudos = escudos;
        this.equipos = equipos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getEscudos() {
        return escudos;
    }

    public void setEscudos(ArrayList<String> escudos) {
        this.escudos = escudos;
    }

    public ArrayList<String> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<String> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "Ligas{" +
                "nombre='" + nombre + '\'' +
                ", escudos=" + escudos +
                ", equipos=" + equipos +
                '}';
    }
}