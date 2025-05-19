package com.example.proyectoextraordinario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

public class JugadorDetallado extends AppCompatActivity {

    private TextView texto;
    private ImageView escudo, foto;
    private Button btVolver, btComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugador_detallado);

        Jugador jugador = getIntent().getParcelableExtra("jugador");
        Estadisticas estadisticas = getIntent().getParcelableExtra("estadisticas");

        texto = findViewById(R.id.textoInfoID);
        escudo = findViewById(R.id.escudoEquipoID);
        foto = findViewById(R.id.fotoJugadorID);
        btVolver = findViewById(R.id.btVolverID);
        btComprar = findViewById(R.id.btComprarID);

        if (jugador != null && estadisticas != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("👤 Nombre: ").append(jugador.getNombre()).append("\n\n");
            sb.append("📌 Posición: ").append(jugador.getPosicion()).append("\n");
            sb.append("💰 Valor de mercado: ").append(jugador.getValor_mercado()).append("\n");
            sb.append("🎮 Valor FIFA: ").append(jugador.getValor_carta()).append("\n\n");
            sb.append("📊 Estadísticas:\n");
            sb.append("⚡ Ritmo: ").append(estadisticas.getRitmo()).append("\n");
            sb.append("🎯 Regate: ").append(estadisticas.getRegate()).append("\n");
            sb.append("🔥 Tiro: ").append(estadisticas.getTiro()).append("\n");
            sb.append("🛡️ Defensa: ").append(estadisticas.getDefensa()).append("\n");
            sb.append("🎯 Pase: ").append(estadisticas.getPase()).append("\n");
            sb.append("💪 Físico: ").append(estadisticas.getFisico());

            texto.setText(sb.toString());

            Picasso.get().load(jugador.getEscudo()).into(escudo);
            Picasso.get().load(jugador.getFoto()).into(foto);
        }

        btVolver.setOnClickListener(v -> {
            finish();
        });

        btComprar.setOnClickListener(v -> {
            if (jugador != null) {
                Intent resultado = new Intent();
                resultado.putExtra("jugadorComprado", jugador);
                setResult(RESULT_OK, resultado);

                btComprar.setText("Jugador en propiedad");
                btComprar.setBackgroundColor(Color.GRAY);
                btComprar.setEnabled(false);

            } else {
                Toast.makeText(this, "Error al cargar jugador para comprar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}