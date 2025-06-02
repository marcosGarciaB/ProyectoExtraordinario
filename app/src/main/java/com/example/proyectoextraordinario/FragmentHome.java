package com.example.proyectoextraordinario;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.MediaController;
import android.widget.VideoView;

public class FragmentHome extends Fragment {

    private VideoView videoView;
    private MediaController mediaController;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        videoView = view.findViewById(R.id.videoViewID);
        mediaController = new MediaController(getContext());

        String path = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video0;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);

        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

        return view;
    }
}