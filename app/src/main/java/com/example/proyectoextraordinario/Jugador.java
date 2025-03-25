package com.example.proyectoextraordinario;

public class Jugador {
    private String nombre;
    private String apellido;
    private String valor_mercado;
    private String equipo;

    public Jugador(String nombre, String apellido, String valor_mercado, String equipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.valor_mercado = valor_mercado;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getValor_mercado() {
        return valor_mercado;
    }

    public void setValor_mercado(String valor_mercado) {
        this.valor_mercado = valor_mercado;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
