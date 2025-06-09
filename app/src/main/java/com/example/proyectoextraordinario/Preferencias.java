package com.example.proyectoextraordinario;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;


public class Preferencias {

    private static Gson gson = new Gson();

    private static final String PREF_COMPRAS = "jugador-comprado";
    private static final String PREF_COMPRAS_JSON = "compras_objetos";

    private static final String PREF_SEGUIMIENTO = "seguido";
    private static final String PREF_SEGUIMIENTO_JSON = "jugadores_objetos";

    private static final String PREF_USUARIO = "nombre_usuario1";
    private static final String CLAVE_NOMBRE = "clave_usuario2";

    private static final String PREF_EQUIPO= "nombre_equipo1";
    private static final String CLAVE_EQUIPO = "clave_equipo2";

    private static final String PREF_ESCUDO = "escudo1";
    private static final String CLAVE_ESCUDO = "clave-escudo1";

    //Jugadores que he comprado.
    public static boolean estaComprado(Context context, Jugador jugador) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_COMPRAS, Context.MODE_PRIVATE);
        return prefs.getBoolean(jugador.getNombre(), false);
    }

    public static void guardarJugadorComprado(Context context, Jugador jugador) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_COMPRAS, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(jugador.getNombre(), true).apply();

        SharedPreferences prefsComprados = context.getSharedPreferences(PREF_COMPRAS_JSON, Context.MODE_PRIVATE);
        String jugadorJson = gson.toJson(jugador);
        prefsComprados.edit().putString(jugador.getNombre(), jugadorJson).apply();
    }

    public static void eliminarJugadorComprado(Context context, Jugador jugador) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_COMPRAS, Context.MODE_PRIVATE);
        prefs.edit().remove(jugador.getNombre()).apply();

        SharedPreferences prefsComprados = context.getSharedPreferences(PREF_COMPRAS_JSON, Context.MODE_PRIVATE);
        prefsComprados.edit().remove(jugador.getNombre()).apply();
    }

    public static ArrayList<Jugador> obtenerJugadoresComprados(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_COMPRAS, Context.MODE_PRIVATE);
        SharedPreferences prefsComprados = context.getSharedPreferences(PREF_COMPRAS_JSON, Context.MODE_PRIVATE);

        ArrayList<Jugador> lista = new ArrayList<>();

        for (String clave : prefs.getAll().keySet()) {
            boolean siguiendo = prefs.getBoolean(clave, false);
            if (siguiendo) {
                String jugadorJson = prefsComprados.getString(clave, null);
                if (jugadorJson != null) {
                    Jugador jugador = gson.fromJson(jugadorJson, Jugador.class);
                    lista.add(jugador);
                }
            }
        }
        return lista;
    }

    //Jugadores a los que sigo.
    public static boolean estaSiguiendo(Context context, Jugador jugador) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_SEGUIMIENTO, Context.MODE_PRIVATE);
        return prefs.getBoolean(jugador.getNombre(), false);
    }

    public static void guardarJugadorSeguido(Context context, Jugador jugador) {
        SharedPreferences prefsSeg = context.getSharedPreferences(PREF_SEGUIMIENTO, Context.MODE_PRIVATE);
        prefsSeg.edit().putBoolean(jugador.getNombre(), true).apply();

        SharedPreferences prefsJugadores = context.getSharedPreferences(PREF_SEGUIMIENTO_JSON, Context.MODE_PRIVATE);
        String jugadorJson = gson.toJson(jugador);
        prefsJugadores.edit().putString(jugador.getNombre(), jugadorJson).apply();
    }

    public static void eliminarJugadorSeguido(Context context, Jugador jugador) {
        SharedPreferences prefsSeg = context.getSharedPreferences(PREF_SEGUIMIENTO, Context.MODE_PRIVATE);
        prefsSeg.edit().remove(jugador.getNombre()).apply();

        SharedPreferences prefsJugadores = context.getSharedPreferences(PREF_SEGUIMIENTO_JSON, Context.MODE_PRIVATE);
        prefsJugadores.edit().remove(jugador.getNombre()).apply();
    }

    public static ArrayList<Jugador> obtenerJugadoresSeguidos(Context context) {
        SharedPreferences prefsSeg = context.getSharedPreferences(PREF_SEGUIMIENTO, Context.MODE_PRIVATE);
        SharedPreferences prefsJugadores = context.getSharedPreferences(PREF_SEGUIMIENTO_JSON, Context.MODE_PRIVATE);

        ArrayList<Jugador> lista = new ArrayList<>();

        for (String clave : prefsSeg.getAll().keySet()) {
            boolean siguiendo = prefsSeg.getBoolean(clave, false);
            if (siguiendo) {
                String jugadorJson = prefsJugadores.getString(clave, null);
                if (jugadorJson != null) {
                    Jugador jugador = gson.fromJson(jugadorJson, Jugador.class);
                    lista.add(jugador);
                }
            }
        }
        return lista;
    }

    //Nombre de usuario.
    public static void guardarNombreUsuario(Context context, String nombre) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_USUARIO, Context.MODE_PRIVATE);
        prefs.edit().putString(CLAVE_NOMBRE, nombre).apply();
    }

    public static String obtenerNombreUsuario(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_USUARIO, Context.MODE_PRIVATE);
        return prefs.getString(CLAVE_NOMBRE, "");
    }

    //Equipo y escudo del home.
    public static void guardarNombreEquipo(Context context, String nombre) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_EQUIPO, Context.MODE_PRIVATE);
        prefs.edit().putString(CLAVE_EQUIPO, nombre).apply();
    }

    public static String obtenerNombreEquipo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_EQUIPO, Context.MODE_PRIVATE);
        return prefs.getString(CLAVE_EQUIPO, "");
    }

    public static void guardarEscudoEquipo(Context context, String url) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ESCUDO, Context.MODE_PRIVATE);
        prefs.edit().putString(CLAVE_ESCUDO, url).apply();
    }

    public static String obtenerEscudoEquipo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ESCUDO, Context.MODE_PRIVATE);
        return prefs.getString(CLAVE_ESCUDO, "");
    }
}
