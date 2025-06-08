package com.example.proyectoextraordinario;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

public class JugadorDetallado extends AppCompatActivity {

    private SwitchCompat switchCompat;
    private TextView tvNombre, tvPoscion, tvMercado, tvFifa, tv1, tv2, tv3, tv4, tv5, tv6;
    private ImageView escudo, foto;
    private Button btVolver, btComprar;
    private Jugador jugador;
    private Estadisticas estadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugador_detallado);

        jugador = getIntent().getParcelableExtra("jugador");
        estadisticas = getIntent().getParcelableExtra("estadisticas");

        tvNombre = findViewById(R.id.textoNombreID);
        tvPoscion = findViewById(R.id.textoPosicionID);
        tvMercado = findViewById(R.id.textoValorMercadoID);
        tvFifa = findViewById(R.id.textoValorFIFAID);

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

        configuracionTexto();
        estadoConfiguraciones();

        btVolver.setOnClickListener(v -> finish());

        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        btComprar.setOnClickListener(v -> {
            if (jugador != null) {
                viewModel.agregarJugadorComprado(this, jugador);
                estadoConfiguraciones();
            }
        });

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Preferencias.guardarJugadorSeguido(getApplicationContext(), jugador);
            } else {
                Preferencias.eliminarJugadorSeguido(getApplicationContext(), jugador);
            }
            switchCompat.setText(isChecked ? R.string.siguiendo_jugador : R.string.seguir_jugador);
        });
    }

    public void configuracionTexto() {
        if (jugador != null && estadisticas != null) {
            tvNombre.setText(tvNombre.getText() + String.valueOf(jugador.getNombre()));
            tvPoscion.setText(tvPoscion.getText() + String.valueOf(jugador.getPosicion()));
            tvMercado.setText(tvMercado.getText() + String.valueOf(jugador.getValor_mercado()));
            tvFifa.setText(tvFifa.getText() + String.valueOf(jugador.getValor_carta()));

            tv1.setText(tv1.getText() + String.valueOf(estadisticas.getRitmo()));
            tv2.setText(tv2.getText() + String.valueOf(estadisticas.getRegate()));
            tv3.setText(tv3.getText() + String.valueOf(estadisticas.getTiro()));
            tv4.setText(tv4.getText() + String.valueOf(estadisticas.getDefensa()));
            tv5.setText(tv5.getText() + String.valueOf(estadisticas.getPase()));
            tv6.setText(tv6.getText() + String.valueOf(estadisticas.getFisico()));

            Picasso.get().load(jugador.getEscudo()).into(escudo);
            Picasso.get().load(jugador.getFoto()).into(foto);
        }
    }

    public void estadoConfiguraciones() {
        if (jugador != null) {
            boolean siguiendo = Preferencias.estaSiguiendo(this, jugador);
            switchCompat.setChecked(siguiendo);
            switchCompat.setText(siguiendo ? R.string.siguiendo_jugador : R.string.seguir_jugador);
        }

        if (jugador != null) {
            boolean comprado = Preferencias.estaComprado(this, jugador);

            if (comprado) {
                btComprar.setText(R.string.boton_comprar_jugador_comprado);
                btComprar.setBackgroundColor(Color.GRAY);
                btComprar.setEnabled(false);
            } else {
                btComprar.setText(R.string.boton_comprar_jugador);
                btComprar.setBackgroundColor(Color.GREEN);
                btComprar.setEnabled(true);
            }
        }
    }
}