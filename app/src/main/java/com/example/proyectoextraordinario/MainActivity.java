package com.example.proyectoextraordinario;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private String urlJugadores = "https://api.myjson.online/v1/records/f8023be6-48e4-4f1d-ab57-90e2ced1c118";
    private ImageButton btPlantilla, btMercado, btEscudo, btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btPlantilla = findViewById(R.id.plantillaID);
        btMercado = findViewById(R.id.mercadoID);
        btEscudo = findViewById(R.id.escudoID);
        btHome = findViewById(R.id.homeID);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2ID, new FragmentHome()).commit();

    }

    public void seleccionarFragment(View v) {
        Fragment f = null;

        if (v.getId() == R.id.plantillaID) {
            f = new FragmentPlantilla();

        } else if (v.getId() == R.id.homeID) {
            f = new FragmentHome();

        } else if (v.getId() == R.id.mercadoID) {
            f = new FragmentMercado();

        } else {
            f = new FragmentEscudo();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2ID, f).commit();
    }

}