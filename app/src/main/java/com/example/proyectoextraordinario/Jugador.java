package com.example.proyectoextraordinario;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Jugador implements Parcelable {
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

    protected Jugador(Parcel in) {
        nombre = in.readString();
        valor_mercado = in.readString();
        valor_carta = in.readString();
        escudo = in.readString();
        foto = in.readString();
        posicion = in.readString();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(valor_mercado);
        dest.writeString(valor_carta);
        dest.writeString(escudo);
        dest.writeString(foto);
        dest.writeString(posicion);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jugador)) return false;
        Jugador jugador = (Jugador) o;
        return nombre != null && nombre.equals(jugador.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
}
