package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentAjustes extends Fragment {

    private Button btCambiarNombre, btCambiarIdioma, btModoOscuro, btSalir;
    private SwitchCompat switchPartidos, switchNoticias;

    public FragmentAjustes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        btCambiarNombre = view.findViewById(R.id.btCambiarNombreID);
        btCambiarIdioma = view.findViewById(R.id.btCambiarIdiomaID);
        btModoOscuro = view.findViewById(R.id.btModoOscuroID);
        btSalir = view.findViewById(R.id.btSalirID);

        switchPartidos = view.findViewById(R.id.switchPartidosID);
        switchNoticias = view.findViewById(R.id.switchNoticiasID);

        btCambiarNombre.setOnClickListener(v -> {
            mostrarDialogoNombreUsuario();
        });

        btCambiarIdioma.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        });

        btSalir.setOnClickListener(v -> requireActivity().finish());


        return view;
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
                    dialog.dismiss();
                }
            });
        });

        dialog.show();
    }
}