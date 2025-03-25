package com.example.proyectoextraordinario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentEscudo extends Fragment {

    private ImageView escudo;
    private TextView equipo;
    private String urlEscudos = "https://api.myjson.online/v1/records/987c3e96-c0e7-4a43-984e-6d867f24a5b1";


    public FragmentEscudo() {
        // Required empty public constructor
    }

    public static FragmentEscudo newInstance(String param1, String param2) {
        FragmentEscudo fragment = new FragmentEscudo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_escudo, container, false);
    }


}