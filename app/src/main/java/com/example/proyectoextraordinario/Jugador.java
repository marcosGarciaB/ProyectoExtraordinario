package com.example.proyectoextraordinario;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private String valor_mercado;
    private String valor_carta;
    private String escudo;
    private String foto;
    private String posicion;
    private ArrayList<Estadisticas> estadisticas;

    public Jugador(String nombre, String valor_mercado, String valor_carta, String escudo, String foto, String posicion, ArrayList<Estadisticas> estadisticas) {
        this.nombre = nombre;
        this.valor_mercado = valor_mercado;
        this.valor_carta = valor_carta;
        this.escudo = escudo;
        this.foto = foto;
        this.posicion = posicion;
        this.estadisticas = estadisticas;
    }

    public Jugador(String nombre, String valor_mercado, String valor_carta, String escudo, String foto, String posicion) {
        this.nombre = nombre;
        this.valor_mercado = valor_mercado;
        this.valor_carta = valor_carta;
        this.escudo = escudo;
        this.foto = foto;
        this.posicion = posicion;
        this.estadisticas = estadisticas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor_mercado() {
        return valor_mercado;
    }

    public void setValor_mercado(String valor_mercado) {
        this.valor_mercado = valor_mercado;
    }

    public String getValor_carta() {
        return valor_carta;
    }

    public void setValor_carta(String valor_carta) {
        this.valor_carta = valor_carta;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public ArrayList<Estadisticas> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(ArrayList<Estadisticas> estadisticas) {
        this.estadisticas = estadisticas;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", valor_mercado='" + valor_mercado + '\'' +
                ", valor_carta='" + valor_carta + '\'' +
                ", escudo='" + escudo + '\'' +
                ", foto='" + foto + '\'' +
                ", posicion='" + posicion + '\'' +
                ", estadisticas=" + estadisticas +
                '}';
    }
}
