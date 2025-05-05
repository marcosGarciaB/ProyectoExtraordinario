package com.example.proyectoextraordinario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentMercado extends Fragment {
    private ListView listView;
    private Spinner spinner;
    private String urlJugadores = "https://api.myjson.online/v1/records/f8023be6-48e4-4f1d-ab57-90e2ced1c118";

    private ArrayList<Jugador> jugadorList;
    private ArrayList<Estadisticas> estadisticasList;

    public FragmentMercado() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mercado, container, false);

        listView = view.findViewById(R.id.listview);
        spinner = view.findViewById(R.id.spinnerID);

        jugadorList = new ArrayList<>();
        estadisticasList = new ArrayList<>();

        consultaTodos(getContext());

        bindingSpinner(getContext());

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), JugadorDetallado.class);

            intent.putParcelableArrayListExtra("jugadores", jugadorList);


            startActivity(intent);
        });

        return view;
    }

    public void bindingSpinner(Context context) {
        String[] opciones = {"Posiciones", "Defensas", "Centrocampistas", "Delanteros", "Todos"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = parent.getItemAtPosition(position).toString();

                jugadorList.clear();
                estadisticasList.clear();

                switch (seleccionado) {
                    case "Defensas":
                        consultaDefensas(getContext());
                        break;

                    case "Centrocampistas":
                         consultaCentrocampistas(getContext());
                        break;

                    case "Delanteros":
                        consultaDelanteros(getContext());
                        break;

                    case "Todos":
                        consultaTodos(getContext());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void consultaDefensas(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJugadores, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject root = response.getJSONObject("data");
                            JSONArray defensasArray = root.getJSONArray("defensas");

                            String nombre = "";
                            String valoracion = "";
                            String posicion = "";
                            String escudoEquipo = "";
                            String fotoJugador = "";
                            String valorMercado = "";

                            String pase = "";
                            String tiro = "";
                            String fisico = "";
                            String regate = "";
                            String defensa = "";
                            String ritmo = "";

                            for (int i = 0; i < defensasArray.length(); i++) {
                                nombre = defensasArray.getJSONObject(i).getString("nombre");
                                valoracion = defensasArray.getJSONObject(i).getString("valor_fifa");
                                posicion = defensasArray.getJSONObject(i).getString("posicion_real");
                                escudoEquipo = defensasArray.getJSONObject(i).getString("escudo");
                                fotoJugador = defensasArray.getJSONObject(i).getString("foto");
                                valorMercado = defensasArray.getJSONObject(i).getString("valor_mercado");

                                Jugador jugador = new Jugador(nombre,valorMercado, valoracion, escudoEquipo, fotoJugador, posicion);
                                Log.e("TAG", "onResponse: " + jugador.toString());

                                JSONArray estadisticasArray = defensasArray.getJSONObject(i).getJSONArray("estadisticas");

                                for (int j = 0; j < estadisticasArray.length(); j++) {
                                    pase = estadisticasArray.getJSONObject(j).getString("pase");
                                    tiro = estadisticasArray.getJSONObject(j).getString("tiro");
                                    fisico = estadisticasArray.getJSONObject(j).getString("fisico");
                                    regate = estadisticasArray.getJSONObject(j).getString("regate");
                                    defensa = estadisticasArray.getJSONObject(j).getString("defensa");
                                    ritmo = estadisticasArray.getJSONObject(j).getString("ritmo");

                                    Estadisticas stats = new Estadisticas(ritmo, regate, tiro, defensa, pase, fisico);
                                    Log.e("TAG", "onResponse: " + stats.toString());

                                    estadisticasList.add(stats);

                                }

                                jugadorList.add(jugador);
                            }

                            Adaptador adaptador = new Adaptador(context, jugadorList, estadisticasList);
                            listView.setAdapter(adaptador);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Error al obtener algún dato. " + error.getMessage());
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void consultaCentrocampistas(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJugadores, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject root = response.getJSONObject("data");
                            JSONArray defensasArray = root.getJSONArray("centrocampistas");

                            String nombre = "";
                            String valoracion = "";
                            String posicion = "";
                            String escudoEquipo = "";
                            String fotoJugador = "";
                            String valorMercado = "";

                            String pase = "";
                            String tiro = "";
                            String fisico = "";
                            String regate = "";
                            String defensa = "";
                            String ritmo = "";

                            for (int i = 0; i < defensasArray.length(); i++) {
                                nombre = defensasArray.getJSONObject(i).getString("nombre");
                                valoracion = defensasArray.getJSONObject(i).getString("valor_fifa");
                                posicion = defensasArray.getJSONObject(i).getString("posicion_real");
                                escudoEquipo = defensasArray.getJSONObject(i).getString("escudo");
                                fotoJugador = defensasArray.getJSONObject(i).getString("foto");
                                valorMercado = defensasArray.getJSONObject(i).getString("valor_mercado");

                                Jugador jugador = new Jugador(nombre,valorMercado, valoracion, escudoEquipo, fotoJugador, posicion);
                                Log.e("TAG", "onResponse: " + jugador.toString());

                                JSONArray estadisticasArray = defensasArray.getJSONObject(i).getJSONArray("estadisticas");

                                for (int j = 0; j < estadisticasArray.length(); j++) {
                                    pase = estadisticasArray.getJSONObject(j).getString("pase");
                                    tiro = estadisticasArray.getJSONObject(j).getString("tiro");
                                    fisico = estadisticasArray.getJSONObject(j).getString("fisico");
                                    regate = estadisticasArray.getJSONObject(j).getString("regate");
                                    defensa = estadisticasArray.getJSONObject(j).getString("defensa");
                                    ritmo = estadisticasArray.getJSONObject(j).getString("ritmo");

                                    Estadisticas stats = new Estadisticas(ritmo, regate, tiro, defensa, pase, fisico);
                                    Log.e("TAG", "onResponse: " + stats.toString());

                                    estadisticasList.add(stats);

                                }

                                jugadorList.add(jugador);
                            }

                            Adaptador adaptador = new Adaptador(context, jugadorList, estadisticasList);
                            listView.setAdapter(adaptador);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Error al obtener algún dato. " + error.getMessage());
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void consultaDelanteros(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJugadores, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject root = response.getJSONObject("data");
                            JSONArray defensasArray = root.getJSONArray("delanteros");

                            String nombre = "";
                            String valoracion = "";
                            String posicion = "";
                            String escudoEquipo = "";
                            String fotoJugador = "";
                            String valorMercado = "";

                            String pase = "";
                            String tiro = "";
                            String fisico = "";
                            String regate = "";
                            String defensa = "";
                            String ritmo = "";

                            for (int i = 0; i < defensasArray.length(); i++) {
                                nombre = defensasArray.getJSONObject(i).getString("nombre");
                                valoracion = defensasArray.getJSONObject(i).getString("valor_fifa");
                                posicion = defensasArray.getJSONObject(i).getString("posicion_real");
                                escudoEquipo = defensasArray.getJSONObject(i).getString("escudo");
                                fotoJugador = defensasArray.getJSONObject(i).getString("foto");
                                valorMercado = defensasArray.getJSONObject(i).getString("valor_mercado");

                                Jugador jugador = new Jugador(nombre,valorMercado, valoracion, escudoEquipo, fotoJugador, posicion);
                                Log.e("TAG", "onResponse: " + jugador.toString());

                                JSONArray estadisticasArray = defensasArray.getJSONObject(i).getJSONArray("estadisticas");

                                for (int j = 0; j < estadisticasArray.length(); j++) {
                                    pase = estadisticasArray.getJSONObject(j).getString("pase");
                                    tiro = estadisticasArray.getJSONObject(j).getString("tiro");
                                    fisico = estadisticasArray.getJSONObject(j).getString("fisico");
                                    regate = estadisticasArray.getJSONObject(j).getString("regate");
                                    defensa = estadisticasArray.getJSONObject(j).getString("defensa");
                                    ritmo = estadisticasArray.getJSONObject(j).getString("ritmo");

                                    Estadisticas stats = new Estadisticas(ritmo, regate, tiro, defensa, pase, fisico);
                                    Log.e("TAG", "onResponse: " + stats.toString());

                                    estadisticasList.add(stats);

                                }

                                jugadorList.add(jugador);
                            }

                            Adaptador adaptador = new Adaptador(context, jugadorList, estadisticasList);
                            listView.setAdapter(adaptador);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Error al obtener algún dato. " + error.getMessage());
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void consultaTodos(Context context) {
        jugadorList.clear();
        estadisticasList.clear();

        consultaDefensas(context);
        consultaCentrocampistas(context);
        consultaDelanteros(context);
    }

}