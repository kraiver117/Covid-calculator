package com.example.covid_19calculator;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Animacion_barra_progreso extends Animation {

    private Context context;
    private ProgressBar progessBar;
    private TextView textView;
    private float from;
    private float  to;


    public Animacion_barra_progreso(Context context, ProgressBar progessBar, TextView textView, float form, float to){
        this.context=context;
        this.progessBar=progessBar;
        this.textView=textView;
        this.from=from;
        this.to=to;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value= from+(to-from) * interpolatedTime;
        progessBar.setProgress((int)value);
        textView.setText((int)value+" %");

        if (value== to){
            context.startActivity(new Intent(context,MainActivity.class));
        }


    }
}
