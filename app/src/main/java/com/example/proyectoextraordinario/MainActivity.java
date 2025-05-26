package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private String urlJugadores = "https://api.myjson.online/v1/records/f8023be6-48e4-4f1d-ab57-90e2ced1c118";
    private SharedViewModel sharedViewModel;
    private static final int REQUEST_CODE_JUGADOR_COMPRADO = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        cargarFragment(new FragmentBarraTareas(), R.id.contenedor1ID);
        cargarFragment(new FragmentHome(), R.id.contenedor2ID);

        sharedViewModel.getBotonSeleccionado().observe(this, botonID -> {
            Fragment f = null;

            switch (botonID) {
                case 1:
                    f = new FragmentHome();
                    break;

                case 2:
                    f = new FragmentEscudo();
                    break;

                case 3:
                    f = new FragmentMercado();
                    break;

                case 4:
                    f = new FragmentPlantilla();
                    break;

                case 5:
                    f = new FragmentAjustes();
                    break;
            }

            cargarFragment(f, R.id.contenedor2ID);
        });
    }

    public void cargarFragment(Fragment fragment, int contenedorID) {
        getSupportFragmentManager().beginTransaction().replace(contenedorID, fragment).commit();
    }

    public void abrirJugadorDetallado(Jugador jugador, Estadisticas estadisticas) {
        Intent intent = new Intent(this, JugadorDetallado.class);
        intent.putExtra("jugador", jugador);
        intent.putExtra("estadisticas", estadisticas);
        startActivityForResult(intent, REQUEST_CODE_JUGADOR_COMPRADO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_JUGADOR_COMPRADO && resultCode == RESULT_OK && data != null) {
            Jugador jugadorComprado = data.getParcelableExtra("jugadorComprado");
            if (jugadorComprado != null) sharedViewModel.agregarJugadorComprado(this, jugadorComprado);
        }
    }
}