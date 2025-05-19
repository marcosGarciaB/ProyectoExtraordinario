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


//Tengo que meter que se puedan seguir a los jugadores, opción de venderlos, que se gaste el dinero
// y en la plantilla poder poner algunos en favoritos y que cuando pinche sobre ellos salgan las estadísticas.
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
            Jugador jugadorSeleccionado = jugadorList.get(position);
            Estadisticas estadisticasSeleccionadas = estadisticasList.get(position);

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).abrirJugadorDetallado(jugadorSeleccionado, estadisticasSeleccionadas);
            }
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
                        consultarPorTipo(getContext(), "defensas");
                        break;

                    case "Centrocampistas":
                        consultarPorTipo(getContext(), "centrocampistas");
                        break;

                    case "Delanteros":
                        consultarPorTipo(getContext(), "delanteros");
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

    public void consultarPorTipo(Context context, String tipo) {
        jugadorList.clear();
        estadisticasList.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJugadores, null,
                response -> {
                    try {
                        JSONObject root = response.getJSONObject("data");
                        JSONArray array = root.getJSONArray(tipo);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jugadorJson = array.getJSONObject(i);
                            Jugador jugador = new Jugador(
                                    jugadorJson.getString("nombre"),
                                    jugadorJson.getString("valor_mercado"),
                                    jugadorJson.getString("valor_fifa"),
                                    jugadorJson.getString("escudo"),
                                    jugadorJson.getString("foto"),
                                    jugadorJson.getString("posicion_real")
                            );

                            JSONArray estadisticasArray = jugadorJson.getJSONArray("estadisticas");
                            for (int j = 0; j < estadisticasArray.length(); j++) {
                                JSONObject stat = estadisticasArray.getJSONObject(j);
                                Estadisticas stats = new Estadisticas(
                                        stat.getString("ritmo"),
                                        stat.getString("regate"),
                                        stat.getString("tiro"),
                                        stat.getString("defensa"),
                                        stat.getString("pase"),
                                        stat.getString("fisico")
                                );
                                estadisticasList.add(stats);
                            }

                            jugadorList.add(jugador);
                        }

                        Adaptador adaptador = new Adaptador(context, jugadorList, estadisticasList);
                        listView.setAdapter(adaptador);

                    } catch (JSONException e) {
                        Log.e("JSON_ERROR", "Error al parsear JSON", e);
                    }
                },
                error -> Log.e("VOLLEY_ERROR", "Error en la solicitud: " + error.getMessage())
        );

        requestQueue.add(jsonObjectRequest);
    }


    public void consultaTodos(Context context) {
        jugadorList.clear();
        estadisticasList.clear();

        consultarPorTipo(getContext(), "defensas");
        consultarPorTipo(getContext(), "centrocampistas");
        consultarPorTipo(getContext(), "delanteros");
    }

}