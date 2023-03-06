package com.curso_simulaciones.midecimaoctavaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimaoctavaapp.actividades_secundarias.ActividadInstrumentos;
import com.curso_simulaciones.midecimaoctavaapp.utilidades.MiBoton;

public class ActividadPrincipalMiDecimaOctavaApp extends Activity {

    //private Button informar, actualizar,aceptar;
    private MiBoton entrar, salir, actualizar;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        //Creación elementosGUI
        creacionElementosGui();

        eventos();

        ViewGroup.LayoutParams linear_layou_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setContentView(crearGUI(), linear_layou_principal);

    }


    //Creación elementosGUI
    private void creacionElementosGui() {

        entrar = new MiBoton(this);
        entrar.setImagen(R.drawable.entrar);
        entrar.setText("ENTRAR ");

        salir = new MiBoton(this);
        salir.setImagen(R.drawable.salir);
        salir.setText("SALIR");

        actualizar = new MiBoton(this);
        actualizar.setImagen(R.drawable.actualizar);
        actualizar.setText("ACTUALIZAR");



    }


    private LinearLayout crearGUI(){

        LinearLayout linear_layout_principal =new  LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        linear_layout_principal.setWeightSum(10);


        //LinearLayout primera fila
        LinearLayout linear_layout_primera_fila = new LinearLayout(this);
        linear_layout_primera_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_fila.setGravity(Gravity.FILL);
        linear_layout_primera_fila.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams parametros_primera_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);//ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_primera_fila.weight = 8.0f;
        linear_layout_primera_fila.setLayoutParams(parametros_primera_fila);


        //fondo primera fila
        Drawable fondo = getResources().getDrawable(R.drawable.cinco_g);
        linear_layout_primera_fila.setBackgroundDrawable(fondo);

        //LinearLayout segunda fila
        LinearLayout linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);// ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_segunda_fila.weight = 2.0f;
        linear_layout_segunda_fila.setWeightSum(3.0f);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);


        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_boton.weight = 1.0f;
        entrar.setLayoutParams(parametros_pegado_boton);
        salir.setLayoutParams(parametros_pegado_boton);
        actualizar.setLayoutParams(parametros_pegado_boton);
        linear_layout_segunda_fila.addView(entrar);
        linear_layout_segunda_fila.addView(salir);
        linear_layout_segunda_fila.addView(actualizar);


        linear_layout_principal.addView(linear_layout_primera_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);



        return linear_layout_principal;

    }

    private void eventos(){

        entrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadInstrumentos();


            }
        });

        salir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


            }
        });
    }


    private void lanzarActividadInstrumentos(){

        Intent intent = new Intent(this, ActividadInstrumentos.class);
        startActivity(intent);
    }

}

