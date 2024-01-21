package com.example.mijuegonuevoapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityInstrucciones extends AppCompatActivity {
    Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        btnVolver = findViewById(R.id.btnVolver);

        // Agrega un oyente al botón
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un objeto Intent que represente la actividad ActivityInstrucciones
                Intent intent = new Intent(ActivityInstrucciones.this, MainActivity.class);

                // Inicia la actividad ActivityInstrucciones
                startActivity(intent);
            }
        });
    }
}
