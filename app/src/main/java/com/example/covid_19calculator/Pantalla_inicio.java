package com.example.covid_19calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Pantalla_inicio extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar=findViewById(R.id.progress_bar);
        textView=findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        Animacion_barra_progreso();

    }

    public void Animacion_barra_progreso(){
        Animacion_barra_progreso animation= new Animacion_barra_progreso(this,progressBar,textView,0f,100f);
        animation.setDuration(8000);
        progressBar.setAnimation(animation);
    }

}
