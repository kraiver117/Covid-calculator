package com.example.covid_19calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class prediagnostico_recomendaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediagnostico_recomendaciones);

        String tollens=getIntent().getStringExtra("tollens");
        String tips=getIntent().getStringExtra("tips");

        TextView recomendaciones=findViewById(R.id.recomendaciones);
        recomendaciones.setFocusable(false);
        TextView prediagnostico=findViewById(R.id.prediagnostico);
        prediagnostico.setFocusable(false);
        prediagnostico.setText(tollens);

        Button regresar = findViewById(R.id.regresar);

        tips+="•Quedate en casa! \n";
        recomendaciones.setText(tips);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
