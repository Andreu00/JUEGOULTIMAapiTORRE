package com.example.mijuegonuevoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnInstrucciones, btnJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInstrucciones = findViewById(R.id.btnInstrucciones);
        btnJugar = findViewById(R.id.btnJugar);

        // Agrega un oyente al botón
        btnInstrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un objeto Intent que represente la actividad ActivityInstrucciones
                Intent intent = new Intent(MainActivity.this, ActivityInstrucciones.class);

                // Inicia la actividad ActivityInstrucciones
                startActivity(intent);
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarJuego();
            }
        });
    }

    private void iniciarJuego(){
        Intent intent = new Intent(this, ActivityJuego.class);
        startActivity(intent);
    }
}