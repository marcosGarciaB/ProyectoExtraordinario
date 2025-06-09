package com.example.proyectoextraordinario;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.Random;

public class FragmentHome extends Fragment {

    private VideoView videoView;
    private ImageView imgPrueba;
    private MediaController mediaController;
    private SharedViewModel sharedViewModel;
    private ImageView imgEscudo;
    private TextView tvNombreEquipo, tvNombreUsuario;
    private MediaPlayer mediaPlayer;
    private int index = 0;
    private int[] canciones = {R.raw.acordeon, R.raw.pacman, R.raw.intro, R.raw.coche};
    private ImageButton btPlay, btSiguiente, btAnterior;
    private TextView tvTitulo;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniciarMusica();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        videoView = view.findViewById(R.id.videoViewID);
        mediaController = new MediaController(getContext());
        imgEscudo = view.findViewById(R.id.imgEscudoHomeID);
        tvNombreEquipo = view.findViewById(R.id.nombreEquipoHomeID);
        tvNombreUsuario = view.findViewById(R.id.nombreUsuarioID);

        btAnterior = view.findViewById(R.id.anteriorIDHOME);
        btPlay = view.findViewById(R.id.playIDHOME);
        btSiguiente = view.findViewById(R.id.siguienteIDHOME);
        tvTitulo = view.findViewById(R.id.tituloCancionID);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        seleccionEquipo();
        bindingVideos();

        String nombreUsuario = Preferencias.obtenerNombreUsuario(getContext());

        if (!nombreUsuario.isEmpty()) {
            tvNombreUsuario.setText(getString(R.string.nombre_usuario) + ": " + nombreUsuario);
        } else {
            mostrarDialogoNombreUsuario();
        }

        String nombreEquipo = Preferencias.obtenerNombreEquipo(getContext());
        String urlEscudo = Preferencias.obtenerEscudoEquipo(getContext());

        if (!nombreEquipo.isEmpty()) {
            tvNombreEquipo.setText(nombreEquipo);
            Picasso.get().load(urlEscudo).into(imgEscudo);
        } else {
            tvNombreEquipo.setText(R.string.equipo_sin_confirmar);
        }


        return view;
    }

    private void seleccionEquipo() {
        sharedViewModel.getEscudoSeleccionado().observe(getViewLifecycleOwner(), map -> {
            if (map != null) {
                String nombre = map.get("nombre");
                String url = map.get("url");

                tvNombreEquipo.setText(nombre);
                Picasso.get().load(url).into(imgEscudo);
            }
        });
        tvNombreEquipo.setText(R.string.equipo_sin_confirmar);
    }

    private void bindingVideos() {
        String path = "";
        int random = new Random().nextInt(3);

        switch (random) {
            case 0:
                path = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video1;
                break;
            case 1:
                path = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video2;
                break;
            case 2:
                path = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video3;
                break;
        }

        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }

    private void mostrarDialogoNombreUsuario() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.titulo_alert);

        EditText et = new EditText(requireContext());
        et.setHint(R.string.hint_alert);

        builder.setView(et);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.confirmar_alert, null);

        builder.setNegativeButton(R.string.cancelar_alert, (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(d -> {
            Button botonConfirmar = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

            botonConfirmar.setOnClickListener(v -> {
                String nombre = et.getText().toString().trim();

                if (nombre.isEmpty()) {
                    Toast.makeText(requireContext(), R.string.nombre_vacio_alert, Toast.LENGTH_SHORT).show();
                } else {
                    Preferencias.guardarNombreUsuario(getContext(), nombre);
                    tvNombreUsuario.setText(getString(R.string.nombre_usuario) + ": " + nombre);
                    dialog.dismiss();
                }
            });
        });
        dialog.show();
    }

    private void iniciarMusica() {
        mediaPlayer = MediaPlayer.create(getContext(), canciones[index]);
        actualizarTituloCancion();

        btPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btPlay.setImageResource(R.drawable.play);
            } else {
                mediaPlayer.start();
                btPlay.setImageResource(R.drawable.pause);
            }
        });

        btSiguiente.setOnClickListener(v -> {
            cambiarCancion(1);
        });

        btAnterior.setOnClickListener(v -> {
            cambiarCancion(-1);
        });

        mediaPlayer.setOnCompletionListener(mp -> cambiarCancion(1));
    }

    private void cambiarCancion(int cambio) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        index += cambio;

        if (index >= canciones.length) {
            index = 0;
        } else if (index < 0) {
            index = canciones.length - 1;
        }

        mediaPlayer = MediaPlayer.create(getContext(), canciones[index]);
        mediaPlayer.start();
        btPlay.setImageResource(R.drawable.pause);
        actualizarTituloCancion();

        mediaPlayer.setOnCompletionListener(mp -> cambiarCancion(1));
    }

    private void actualizarTituloCancion() {
        String[] titulos = {"Acordeón", "Pacman", "Intro", "Coche"};
        tvTitulo.setText(titulos[index]);
    }
}