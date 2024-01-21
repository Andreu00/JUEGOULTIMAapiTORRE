package com.example.mijuegonuevoapi;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityJuego extends AppCompatActivity {
    ImageButton btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09;
    ImageButton[] tablero= new ImageButton[10];
    Button btnReiniciar, btnSalir;
    TextView txtPuntuacion;
    int puntuacion;
    int aciertos;

    //imagenes
    int[] imagenes;
    int fondo;

    //variables del juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numPrimero, numSegundo;
    boolean bloqueo=false;
    final Handler handler =new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        init();

    }

    private void cargarTablero() {
        btn00 = findViewById(R.id.boton00);
        btn01 = findViewById(R.id.boton01);
        btn02 = findViewById(R.id.boton02);
        btn03 = findViewById(R.id.boton03);
        btn04 = findViewById(R.id.boton04);
        btn05 = findViewById(R.id.boton05);
        btn06 = findViewById(R.id.boton06);
        btn07 = findViewById(R.id.boton07);
        btn08 = findViewById(R.id.boton08);
        btn09 = findViewById(R.id.boton09);

        tablero[0] = btn00;
        tablero[1] = btn01;
        tablero[2] = btn02;
        tablero[3] = btn03;
        tablero[4] = btn04;
        tablero[5] = btn05;
        tablero[6] = btn06;
        tablero[7] = btn07;
        tablero[8] = btn08;
        tablero[9] = btn09;

    }

    private void cargarBotones(){
        btnReiniciar=findViewById(R.id.btnReinicar);
        btnSalir=findViewById(R.id.btnSalir);

        btnReiniciar.setOnClickListener(v -> init());

        btnSalir.setOnClickListener(v -> finish());
    }

    private void cargarTexto(){
        txtPuntuacion=findViewById(R.id.txtPuntuacion);
        puntuacion=0;
        aciertos=0;
        txtPuntuacion.setText("Puntuacion: "+puntuacion);
    }

    private void cargarImagenes(){
        imagenes=new int[]{
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4
        };
        fondo=R.drawable.fondo;
    }


    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> resultado=new ArrayList<Integer>();
        for (int i=0; i<longitud*2 ; i++){
            resultado.add(i % longitud);
        }
        Collections.shuffle(resultado);
        return resultado;
    }

    private void comprobar(int i, final ImageButton imgb){
        if(primero == null){
            primero=imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numPrimero=arrayDesordenado.get(i);
        }else{
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numSegundo=arrayDesordenado.get(i);
            if(numPrimero == numSegundo){
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                txtPuntuacion.setText("Puntuacion: "+puntuacion);
                if(aciertos == imagenes.length){
                    Toast toast = Toast.makeText(getApplicationContext(), "WIIN!!!!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }else{
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo = false;
                        primero = null;

                    }
                }, 1000);
            }

        }
    }

    private void init(){
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for(int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<tablero.length; i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 500);

        for(int i=0; i<tablero.length; i++){
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo){
                        comprobar(j, tablero[j]);
                    }
                }
            });
        }

    }
}
