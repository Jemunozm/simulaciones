package com.curso_simulaciones.misegundaapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActividadPrincipalMiSegundaApp extends Activity {
    private TextView resultados;
    private String cadena;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crearElementosGui();


        ViewGroup.LayoutParams parameto_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        this.setContentView(crearGui(), parameto_layout_principal);


        calculoOverflowByte();
        calculoOverflowShort();
        calculoOverflowInt();
        calculoOverflowLong();
        calculoOverflowFloat();
        calculoOverflowDouble();
        calculoUnderflowFloat();
        calculoUnderflowDouble();
        calculoEpsilonFloat();
        calculoEpsilonDouble();
        desplegarResultado();


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void crearElementosGui() {

        resultados = new TextView(this);
        resultados.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        resultados.setTextColor(Color.YELLOW);
        resultados.setBackgroundColor(Color.BLACK);

    }

    private LinearLayout crearGui() {
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));

        LinearLayout.LayoutParams parametrosPegada = new
                LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegada.setMargins(50, 50, 50, 50);
        parametrosPegada.weight = 1.0f;
        linear_principal.addView(resultados, parametrosPegada);


        return linear_principal;
    }

    private void calculoOverflowByte() {

        byte x = 127;
        byte y = 1;
        byte z = (byte) (x + y); //se hizo casting

        //cadena resultado
        String resultadoOverflowByte = "   Overflow en byte: " + x + " + " + y + "= " + z;
        cadena = "   RESULTADOS\n" + resultadoOverflowByte;

    }

    /*Método para mostrar overflow en short*/
    private void calculoOverflowShort() {

        short x = 32767;
        short y = 1;
        short z = (short) (x + y); //se hizo casting

        //cadena resultado
        String resultadoOverflowShort = "   Overflow en short: " + x + " + " + y + "= " + z;
        cadena = cadena + "\n" + resultadoOverflowShort;

    }


    /*Método para mostrar overflow en int*/
    private void calculoOverflowInt() {

        int x = 2147483647;
        int y = 1;
        int z = x + y; //se hizo casting

        //cadena resultado
        String resultadoOverflowInt = "   Overflow en int: " + x + " + " + y + "= " + z;
        cadena = cadena + "\n" + resultadoOverflowInt;

    }

    /*Método para mostrar overflow en long*/
    private void calculoOverflowLong() {

        long x = (long) (Math.pow(2, 63) - 1);//se hizo casting
        long y = 1;
        long z = x + y;

        //cadena resultado
        String resultadoOverflowLong = "   Overflow en long: " + x + " + " + y + "= " + z;
        cadena = cadena + "\n" + resultadoOverflowLong;

    }

    /*Método para mostrar overflow en float*/
    private void calculoOverflowFloat() {

        float x = (float) (3.40282347 * Math.pow(10, 38));//se hizo casting
        //aguanta hasta 7 decimales
        float y = (float) (0.0000001 * x);//se hizo casting
        float z = x + y;

        //cadena resultado
        String resultadoOverflowFloat = "   Overflow en float: " + x + " + " + y + "= " + z;
        cadena = cadena + "\n" + resultadoOverflowFloat;

    }

    /*Método para mostrar overflow en double*/
    private void calculoOverflowDouble() {

        double x = 1.7976931348623157 * Math.pow(10, 308);//se hizo casting
        //aguanta hasta 16 decimales
        double y = 0.0000000000000001 * x;
        double z = x + y;

        //cadena resultado
        String resultadoOverflowDouble = "   Overflow en double: " + x + " + " + y + "= " + z;
        cadena = cadena + "\n" + resultadoOverflowDouble;

    }

    /*Método para mostrar underflow en float*/
    private void calculoUnderflowFloat() {

        float x = (float) Math.pow(2, -149);
        float y = 2f;
        float z = x / y;

        //cadena resultado
        String resultadoUnderflowFloat = "   Underflow en float: " + x + " / " + y + "= " + z;
        cadena = cadena + "\n" + resultadoUnderflowFloat;

    }

    /*Método para mostrar underflow en double*/
    private void calculoUnderflowDouble() {

        double x = Math.pow(2, -1074);
        double y = 2d;
        double z = x / y;

        //cadena resultado
        String resultadoUnderflowDouble = "   Underflow en double: " + x + " / " + y + "= " + z;
        cadena = cadena + "\n" + resultadoUnderflowDouble;

    }


    /*calcula el epsilon para float*/
    private void calculoEpsilonFloat() {

        //Cálculo teórico del épsilon
        float epsilonTeoricoFloat = 0.5f * (float) (Math.pow(2, 1 - 23));//eps por redondeo

        //Cálculo computacional del épsilon
        float epsilonCalculadoFloat = 1f;

        do {
            epsilonCalculadoFloat = epsilonCalculadoFloat / 2;

        } while (epsilonCalculadoFloat + 1.0f > 1.0f);

        /* Se debe multiplicar por 2 por que el epsilon se calcula con el primer
        suceso que cumpla que epsilon+1.0=1.0 y el algoritmo  lo sobrepasa.*/

        epsilonCalculadoFloat = 2 * epsilonCalculadoFloat;


        cadena = cadena + "\n" + "   Epsilon float teorico  " + epsilonTeoricoFloat + ",   Epsilon float computacional  " + epsilonCalculadoFloat;

    }


    /*calcula el epsilon para double*/
    private void calculoEpsilonDouble() {

        //Cálculo teórico del épsilon
        float epsilonTeoricoDouble = 0.5f * (float) (Math.pow(2, 1 - 52));//eps por redondeo

        //Cálculo computacional del épsilon
        float epsilonCalculadoDouble = 1f;

        do {
            epsilonCalculadoDouble = epsilonCalculadoDouble / 2;

        } while (epsilonCalculadoDouble + 1.0d > 1.0d);

        /* Se debe multiplicar por 2 por que el epsilon se calcula con el primer
        suceso que cumpla que epsilon+1.0=1.0 y el algoritmo  lo sobrepasa.*/

        epsilonCalculadoDouble = 2 * epsilonCalculadoDouble;


        cadena = cadena + "\n" + "   Epsilon double teorico  " + epsilonTeoricoDouble + ",   Epsilon double computacional  " + epsilonCalculadoDouble;

    }


    private void desplegarResultado() {

        resultados.setText(cadena);
    }

}
