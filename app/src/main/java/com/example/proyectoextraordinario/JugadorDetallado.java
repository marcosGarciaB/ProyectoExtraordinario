package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

public class JugadorDetallado extends AppCompatActivity {

    private SwitchCompat switchCompat;
    private TextView texto, tv1, tv2, tv3, tv4, tv5, tv6;
    private ImageView escudo, foto;
    private Button btVolver, btComprar;
    private boolean estadoSwitch;
    private Jugador jugador;
    private Estadisticas estadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugador_detallado);

        jugador = getIntent().getParcelableExtra("jugador");
        estadisticas = getIntent().getParcelableExtra("estadisticas");

        texto = findViewById(R.id.textoInfoID);
        escudo = findViewById(R.id.escudoEquipoID);
        foto = findViewById(R.id.fotoJugadorID);
        btVolver = findViewById(R.id.btVolverID);
        btComprar = findViewById(R.id.btComprarID);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);

        switchCompat = findViewById(R.id.switchSeguimientoID);

        if (jugador != null && estadisticas != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("👤 Nombre: ").append(jugador.getNombre()).append("\n\n");
            sb.append("📌 Posición: ").append(jugador.getPosicion()).append("\n");
            sb.append("💰 Valor de mercado: ").append(jugador.getValor_mercado()).append("\n");
            sb.append("🎮 Valor FIFA: ").append(jugador.getValor_carta()).append("\n\n");
            sb.append("📊 Estadísticas:\n");

            texto.setText(sb.toString());
            tv1.setText(tv1.getText() + String.valueOf(estadisticas.getRitmo()));
            tv2.setText(tv2.getText() + String.valueOf(estadisticas.getRegate()));
            tv3.setText(tv3.getText() + String.valueOf(estadisticas.getTiro()));
            tv4.setText(tv4.getText() + String.valueOf(estadisticas.getDefensa()));
            tv5.setText(tv5.getText() + String.valueOf(estadisticas.getPase()));
            tv6.setText(tv6.getText() + String.valueOf(estadisticas.getFisico()));

            Picasso.get().load(jugador.getEscudo()).into(escudo);
            Picasso.get().load(jugador.getFoto()).into(foto);
        }

        if (jugador != null) {
            estadoSwitch = true;
            boolean siguiendo = Preferencias.estaSiguiendo(this, jugador);
            switchCompat.setChecked(siguiendo);
            switchCompat.setText(siguiendo ? R.string.siguiendo_jugador : R.string.seguir_jugador);
            estadoSwitch = false;
        }

        btVolver.setOnClickListener(v -> finish());

        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        btComprar.setOnClickListener(v -> {
            if (jugador != null) {
                viewModel.agregarJugadorComprado(this, jugador);

                btComprar.setText(R.string.boton_comprar_jugador_comprado);
                btComprar.setBackgroundColor(Color.GRAY);
                btComprar.setEnabled(false);
            }
        });

//        btComprar.setOnClickListener(v -> {
//            if (jugador != null) {
//                Preferencias.guardarJugadorComprado(this, jugador);
//
//                btComprar.setText(R.string.boton_comprar_jugador_comprado);
//                btComprar.setBackgroundColor(Color.GRAY);
//                btComprar.setEnabled(false);
//
//                Intent resultado = new Intent();
//                resultado.putExtra("jugadorComprado", jugador);
//                setResult(RESULT_OK, resultado);
//
//            } else {
//                Toast.makeText(this, "Error al cargar jugador para comprar", Toast.LENGTH_SHORT).show();
//            }
//        });

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Preferencias.guardarJugadorSeguido(getApplicationContext(), jugador);
            } else {
                Preferencias.eliminarJugadorSeguido(getApplicationContext(), jugador);
            }
            switchCompat.setText(isChecked ? R.string.siguiendo_jugador : R.string.seguir_jugador);
        });
    }
}