package com.curso_simulaciones.miprimeraapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActividadPrincipalMiPrimeraApp extends Activity {

    private TextView cadena;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        crearElementosGui();

        ViewGroup.LayoutParams parametro_Layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        this.setContentView(crearGui(), parametro_Layout_principal);
    }

    private void crearElementosGui() {
        cadena = new TextView(this);
        cadena.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        cadena.setTextColor(Color.YELLOW);
        cadena.setText("HOLA MUNDO");
    }

    private LinearLayout crearGui() {

        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.BLUE);


        linear_principal.addView(cadena);


        return linear_principal;

    }
}
