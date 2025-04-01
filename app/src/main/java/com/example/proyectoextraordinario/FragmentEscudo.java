package com.example.proyectoextraordinario;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentEscudo extends Fragment {

    private ImageView iEscudo;
    private TextView tvEquipo, tvLiga;
    private ImageButton ibBack, ibNext;
    private Button btConfirmar;

    private String urlEscudos = "https://api.myjson.online/v1/records/987c3e96-c0e7-4a43-984e-6d867f24a5b1";

    private ArrayList<String> escudosList;
    private ArrayList<String> nombresEquipos;
    private ArrayList<String> nombresLigas;
    private ArrayList<Liga> ligasList;

    private int ligaIndex = 0;
    private int equipoIndex = 0;

    public FragmentEscudo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escudo, container, false);

        nombresLigas = new ArrayList<>();
        nombresEquipos = new ArrayList<>();
        escudosList = new ArrayList<>();
        ligasList = new ArrayList<>();

        iEscudo = view.findViewById(R.id.imagenEscudoID);
        tvEquipo = view.findViewById(R.id.nombreEquipoID);
        tvLiga = view.findViewById(R.id.nombreLigaID);
        ibBack = view.findViewById(R.id.anteriorID);
        ibNext = view.findViewById(R.id.siguienteID);
        btConfirmar = view.findViewById(R.id.confirmarID);

        consultaEscudos(getContext());

        ibNext.setOnClickListener(v -> {
            if (!ligasList.isEmpty()) {
                Liga liga = ligasList.get(ligaIndex);

                if (equipoIndex < liga.getEquipos().size() - 1) {
                    equipoIndex++;
                } else if (ligaIndex < ligasList.size() - 1) {
                    ligaIndex++;
                    equipoIndex = 0;
                }
                seleccionEquipo();
            }
        });

        ibBack.setOnClickListener(v -> {
            if (!ligasList.isEmpty()) {
                if (equipoIndex > 0) {
                    equipoIndex--;

                } else if (ligaIndex > 0) {
                    ligaIndex--;
                    equipoIndex = ligasList.get(ligaIndex).getEquipos().size() - 1;
                }
                seleccionEquipo();
            }
        });

        //Poner un botón para seleccionar el escudo y que se actualice en el nombre, con un aviso de que se ha actualizado
        //correctamente.
        btConfirmar.setOnClickListener(v -> {
            Toast.makeText(getContext(), " Se ha confirmado el escudo", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    public void consultaEscudos(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlEscudos, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject root = response.getJSONObject("data");
                            JSONArray ligasArray = root.getJSONArray("ligas");

                            String nombreLiga = "";
                            String nombreEquipo = "";
                            String urlEscudo = "";

                            for (int i = 0; i < ligasArray.length(); i++) {
                                nombreLiga = ligasArray.getJSONObject(i).getString("nombre");
                                nombresLigas.add(nombreLiga);

                                JSONArray equiposArray = ligasArray.getJSONObject(i).getJSONArray("equipos");

                                for (int j = 0; j < equiposArray.length(); j++) {
                                    nombreEquipo = equiposArray.getJSONObject(j).getString("nombre");
                                    nombresEquipos.add(nombreEquipo);

                                    urlEscudo = equiposArray.getJSONObject(j).getString("escudo");
                                    escudosList.add(urlEscudo);
                                }

                                Liga liga = new Liga(nombreLiga, new ArrayList<>(escudosList), new ArrayList<>(nombresEquipos));
                                Log.e("TAG", "onResponse: " + liga.toString());

                                ligasList.add(liga);
                                escudosList.clear();
                                nombresEquipos.clear();
                            }

                            seleccionEquipo();



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

    public void seleccionEquipo() {
        if (!ligasList.isEmpty()) {
            Liga liga = ligasList.get(ligaIndex); //
            tvLiga.setText(liga.getNombre());

            if (!liga.getEquipos().isEmpty() && !liga.getEscudos().isEmpty()) {
                tvEquipo.setText(liga.getEquipos().get(equipoIndex));
                Picasso.get().load(liga.getEscudos().get(equipoIndex)).into(iEscudo);
            }
        }
    }
}