package com.example.mijuegonuevoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnInstrucciones, btnJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInstrucciones = findViewById(R.id.btnInstrucciones);
        btnJugar = findViewById(R.id.btnJugar);
        // Creamos la animación
        ObjectAnimator animacion = ObjectAnimator.ofFloat(btnJugar, "translationY", 0, -100, 0);
        animacion.setDuration(1000);
        animacion.setRepeatCount(Animation.INFINITE);
        animacion.setRepeatMode(Animation.REVERSE);

        // Iniciamos la animación
        animacion.start();

        // Repetimos para el otro botón
        ObjectAnimator animacion2 = ObjectAnimator.ofFloat(btnInstrucciones, "translationY", 0, -100, 0);
        animacion2.setDuration(1000);
        animacion2.setRepeatCount(Animation.INFINITE);
        animacion2.setRepeatMode(Animation.REVERSE);

        // Iniciamos la animación
        animacion2.start();

        btnInstrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityInstrucciones.class);
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