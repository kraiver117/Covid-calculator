package com.example.covid_19calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Objetos
    Button calcular,btn_limpiar,btn_segundo_activity;
    Spinner spinner_edades;
    //RadioGroup temperatura,edad,sexo;
    RadioButton sin_fiebre,fiebre_moderada,fiebre;
    RadioButton mujer,hombre;
    //RadioButton joven,adulto,adulto_mayor;
    CheckBox obesidad, hipertension,diabetes,tabaco,cardiovascular,EPOC,inmunosupresion;
    TextView prediagnostico,riesgo;

    //Variables
    String tollens="",recomendaciones="",_edad="",_genero="",_temperatura="",resultado_final="";
    Integer estado=0,factor_de_riesgo=0,posicion_spinner=0;
    double porcentaje_riesgo=0,porcentaje_riesgo_temp=0;


    //Lo que va a hacer la app al iniciar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner de edades
        spinner_edades=findViewById(R.id.spinner_edades);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this  ,R.array.edades,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_edades.setAdapter(adapter);
        spinner_edades.setOnItemSelectedListener(this);


        //RadioGroups
        //temperatura=findViewById(R.id.temperatura);

        //edad=findViewById(R.id.edad);
        //sexo=findViewById(R.id.sexo);

        //edad.setVisibility(View.INVISIBLE);

        //Temperatura
        sin_fiebre=findViewById(R.id.sin_fiebre);
        fiebre_moderada=findViewById(R.id.fiebre_moderada);
        fiebre=findViewById(R.id.fiebre);

        //Sexo
        mujer=findViewById(R.id.mujer);
        hombre=findViewById(R.id.hombre);

        //Edad
        //joven=findViewById(R.id.joven);
        //adulto=findViewById(R.id.adulto);
        //adulto_mayor=findViewById(R.id.adulto_mayor);

        //Padecimientos
        obesidad=findViewById(R.id.obsesidad);
        tabaco=findViewById(R.id.tabaco);
        hipertension=findViewById(R.id.hipertension);
        diabetes=findViewById(R.id.diabetes);
        cardiovascular=findViewById(R.id.enfermedad_cardiovascular);
        EPOC=findViewById(R.id.enfermedad_pulmonar);
        inmunosupresion=findViewById(R.id.inmunosupresion);

        //Riesgo
        riesgo=findViewById(R.id.riesgo);

        //Prediagnostico
        //prediagnostico=findViewById(R.id.prediagnostico_label);

        //Botones
        calcular=findViewById(R.id.calcular);
        btn_limpiar=findViewById(R.id.btn_limpiar);
        btn_segundo_activity=findViewById(R.id.activity_prediagnostico);

        btn_segundo_activity.setVisibility(View.GONE);
    }
    ////////////////////////////////////////////////////////////////////////////////


    public void verificar(View view){

       //Verificacion de fiebre
        if(sin_fiebre.isChecked()){
            _temperatura="normal";
        }else if(fiebre_moderada.isChecked() ){
            _temperatura="fiebre";
        }else if (fiebre.isChecked()){
            _temperatura="fiebre muy alta";
        }
        /*else{
            Toast.makeText(getApplicationContext(),"Selecciona tu temperatura",Toast.LENGTH_SHORT).show();
        }*/

        //Verificacion de genero
        if(mujer.isChecked()) {
            _genero = "mujer";
            porcentaje_riesgo+=8.8;
        }else if(hombre.isChecked()){
            _genero="hombre";
            porcentaje_riesgo+=14.8;
        }
        /*else{
            Toast.makeText(getApplicationContext(),"Selecciona tu género",Toast.LENGTH_SHORT).show();
        }*/

        //Verificacion de edad
        /*if(joven.isChecked()){
            _edad="joven";
        }else if(adulto.isChecked()) {
            _edad = "adulto";
        }else if (adulto_mayor.isChecked()){
            _edad="adulto mayor";
        }else{
            Toast.makeText(getApplicationContext(),"Selecciona tu edad",Toast.LENGTH_SHORT).show();
        }*/


        //Condiciones para fiebre
        if (estado==0 && _temperatura.equals("fiebre muy alta")){
            estado=1;
            tollens+="Temperatura muy alta,";
        }else if (estado==0 && _temperatura.equals("fiebre")){
            estado=4;
            tollens+="Tienes fiebre, quedate en casa y atiende las recomendaciones en este caso";
        }else if(estado ==0 && _temperatura.equals("normal")){
            estado=6;
            tollens+="Temperatura normal, estás bien en general, ten los cuidades de rutina";
        }


        //Condiciones para genero
        if (estado==1 && _genero.equals("mujer")){
            //Estado 3 es de mujer
            estado=3;
            tollens+=" es género femenino";

        }else if (estado==1 && _genero.equals("hombre")) {
            //Estado 5 es de hombre
            estado=5;
            tollens+=" es género masculino";
        }


        //Edades con Spinner
        if (!mujer.isChecked() && !hombre.isChecked()){
            //Toast.makeText(getApplicationContext(),"Selecciona tu edad",Toast.LENGTH_SHORT).show();
            spinner_edades.setSelection(0);
        }else{
            if (mujer.isChecked() && estado==3){
                for (int i=0;i<=posicion_spinner;i++){
                    //Calculo de porcentaje de rango de edades de 20-39 en mujer
                    if(posicion_spinner>1 && posicion_spinner<=19 && mujer.isChecked()){
                        porcentaje_riesgo+=0.6;
                       // _edad="joven";
                    }

                    //Edad entre 40-59
                    if(posicion_spinner>=20 && posicion_spinner<=39 && mujer.isChecked()){
                        porcentaje_riesgo+=1.5;
                       // _edad="adulto";

                    }

                    //Edad entre 60-79
                    if(posicion_spinner>=40 && posicion_spinner<=59 && mujer.isChecked()){
                        porcentaje_riesgo+=0.5;
                        //_edad="adulto mayor";
                    }

                    //Edad entre 80-99
                    if(posicion_spinner>=60 && posicion_spinner<=80 && mujer.isChecked()){
                        porcentaje_riesgo+=0.8;
                        //_edad="adulto mayor";
                    }
                }
                estado=7;

            }else if (hombre.isChecked() && estado==5){

                for (int i=0;i<=posicion_spinner;i++){
                    //Calculo de porcentaje de rango de edades de 20-39 en mujer
                    if(posicion_spinner>0 && posicion_spinner<=19 && hombre.isChecked()){
                        porcentaje_riesgo+=1.23;
                    }

                    //Edades entre 40-59
                    if(posicion_spinner>=20 && posicion_spinner<=39 && hombre.isChecked()){
                        porcentaje_riesgo+=1.56;
                    }

                    //Edades entre 60 y 79
                    if(posicion_spinner>=40 && posicion_spinner<=59 && hombre.isChecked()){
                        porcentaje_riesgo+=1.3;
                    }

                    //Edades entre 80 y 99
                    if(posicion_spinner>=60 && posicion_spinner<=80 && hombre.isChecked()){
                        porcentaje_riesgo+=0.8;
                    }
                }
            }
        }



        //Condiciones para edad para mujer
        /*if (estado==3 && _edad.equals("joven")){
            //Porcentaje de riesgo no aumenta
            estado=7;

        }else if(estado==3 && _edad .equals("adulto")){
            estado=9;
            porcentaje_riesgo+=18.4;

        }else if(estado==3 && _edad.equals("adulto mayor")){
            estado=11;
            porcentaje_riesgo+=51.7;
        }

        //Condiciones para edad para hombre
        if (estado==5 && _edad.equals("joven")){
            //Porcentaje de riesgo no aumenta
            estado=7;

        }else if(estado==5 && _edad .equals("adulto")){
            estado=9;
            porcentaje_riesgo+=18.4;

        }else if(estado==5 && _edad.equals("adulto mayor")){
            estado=11;
            porcentaje_riesgo+=51.7;
        }*/


        //Estado 3 corresponde a mujer

        if (estado==7 && tabaco.isChecked()){
            porcentaje_riesgo+=3.9;
           tollens+=" ,fuma tabaco";
           recomendaciones+="•Consumo de tabaco: Evita en lo posible fumar y el uso de vapeadores y cigarros electronicos \n";
        }

        if(estado==7 && obesidad.isChecked()){
            porcentaje_riesgo+=5.3;
            tollens+=" ,tiene obesidad";
            recomendaciones+="•Obesidad: Cuida tus proporciones de comida, has ejercicio y mantente en un peso adecuado \n";
        }

        if(estado == 7 && diabetes.isChecked()){
            porcentaje_riesgo+=16.0;
            tollens+=" ,tiene diabetes";
            recomendaciones+="•Diabetes: Continua tomando tus medicamentos para la diabetes y revisa diariamente tu nivel de azúcar\n";
        }

        if (estado == 7 && EPOC.isChecked()){
            //checar porcentajes
            porcentaje_riesgo+=1.2;
            tollens+=" ,tiene EPOC";
            recomendaciones+="•EPOC:sigue tomando los medicamentos recetados por tu médico y evita sofocarte, ejercicios vigorozos y aquello que te genera alergia\n";
        }

        if (estado == 7 && cardiovascular.isChecked()){
            //checar porcentajes
            porcentaje_riesgo+=1.2;
            tollens+=" ,tiene enfermedad cardiovascular";
            recomendaciones+="•Enfermedad cardiovascular:Continúa tomando tus medicamentos para el corazón y procura mantenerte relajado\n";
        }

        if (estado == 7 && inmunosupresion.isChecked()){
            //checar porcentajes
            porcentaje_riesgo+=1.2;
            tollens+=", tiene inmunosupresión";
            recomendaciones+="•Inmunosupresión: Continúa tomando tus medicamentos para tu padecimiento\n";
        }

        if(estado==7 && obesidad.isChecked() && hipertension.isChecked()){
            tollens+=" ,tiene hipertension";
            porcentaje_riesgo+=9.1;
            recomendaciones+="•Hipertensión:No dejes tus medicamentos para la presión arterial, mide tu presión todos los días, limita ingesta de sal y trata de relajarte\n";
        }

        if(estado == 7 && diabetes.isChecked() && obesidad.isChecked()){
            porcentaje_riesgo+=24;
        }


        //If para realizar los calculos
        if (fiebre.isChecked() || sin_fiebre.isChecked() || fiebre_moderada.isChecked() && posicion_spinner != 0 && mujer.isChecked()||hombre.isChecked()){
            //Funcion para dar formato al porcentaje de riesgo
            DecimalFormat df = new DecimalFormat("#.0");
            resultado_final=Double.toString(porcentaje_riesgo);
            riesgo.setText( df.format(porcentaje_riesgo)+"%");
            //prediagnostico.setText(tollens);

            estado=0;
            calcular.setVisibility(View.GONE);
            btn_limpiar.setVisibility(View.GONE);
            btn_segundo_activity.setVisibility(View.VISIBLE);

        }else{
            Toast.makeText(getApplicationContext(),"Rellena los campos faltantes",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        //Reinicializa variable factor de riesgo
        factor_de_riesgo=0;

        //Limpia el spinner de edads
        spinner_edades.setSelection(0);
        posicion_spinner=0;

        //Resetea el porcentaje de riesgo
        porcentaje_riesgo=0;

        //Resetea el estado
        estado=0;

        //Limpia cadenas
        tollens="";
        recomendaciones="";

        //Limpia los textview de cada seccion
        //prediagnostico.setText("");
        riesgo.setText("");

        //Limpia los radiogroups de cada seccion
        //temperatura.clearCheck();
        //sexo.clearCheck();
        //edad.clearCheck();

        //Limpia campos de padecimientos
        cardiovascular.setChecked(false);
        diabetes.setChecked(false);
        tabaco.setChecked(false);
        obesidad.setChecked(false);
        hipertension.setChecked(false);
        EPOC.setChecked(false);
        inmunosupresion.setChecked(false);

        //Habilita boton de calcular
        calcular.setVisibility(View.VISIBLE);
        btn_limpiar.setVisibility(View.VISIBLE);
        btn_segundo_activity.setVisibility(View.GONE);


    }

    public void limpiar(View view){

        //Reinicializa variable factor de riesgo
        factor_de_riesgo=0;

        //Limpia el spinner de edads
        spinner_edades.setSelection(0);
        posicion_spinner=0;

        //Resetea el porcentaje de riesgo
        porcentaje_riesgo=0;

        //Resetea el estado
        estado=0;

        //Limpia cadenas
        tollens="";
        recomendaciones="";

        //Limpia los textview de cada seccion
        //prediagnostico.setText("");
        riesgo.setText("");

        //Limpia los radiogroups de cada seccion
        //temperatura.clearCheck();
        //sexo.clearCheck();
        //edad.clearCheck();

        //Limpia campos de padecimientos
        cardiovascular.setChecked(false);
        diabetes.setChecked(false);
        tabaco.setChecked(false);
        obesidad.setChecked(false);
        hipertension.setChecked(false);
        EPOC.setChecked(false);
        inmunosupresion.setChecked(false);

        //Habilita boton de calcular
        calcular.setEnabled(true);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Se obtiene la posicion del spinner
        posicion_spinner=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void ir_segundo_activity(View view) {
        Intent intent = new Intent(view.getContext(),prediagnostico_recomendaciones.class);
        intent.putExtra("tips",recomendaciones);
        intent.putExtra("tollens",tollens);
        startActivity(intent);
    }
}



