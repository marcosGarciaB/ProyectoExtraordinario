package com.example.proyectoextraordinario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPlantilla extends Fragment {

    public FragmentPlantilla() {
        // Required empty public constructor
    }

    public static FragmentPlantilla newInstance(String param1, String param2) {
        FragmentPlantilla fragment = new FragmentPlantilla();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_plantilla, container, false);
    }
}