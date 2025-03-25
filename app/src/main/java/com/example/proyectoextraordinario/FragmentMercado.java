package com.example.proyectoextraordinario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentMercado extends Fragment {

    public FragmentMercado() {
        // Required empty public constructor
    }

    public static FragmentMercado newInstance(String param1, String param2) {
        FragmentMercado fragment = new FragmentMercado();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mercado, container, false);
    }
}