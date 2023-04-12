package com.curso_simulaciones.mitrigesimasegundaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimasegundaapp.datos.AlmacenDatosRAM;

public class ActividadConfiguracion extends Activity {

    private EditText periodo_muestreo, numero_datos;
    private TextView text_periodo_muestreo, text_numero_datos, espacio_1, espacio_2;
    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        gestionarResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

    } //fin del método onCreate





    private void gestionarResolucion(){

        tamanoLetraResolucionIncluida = (int)(0.6f* AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionandoResolucion()



    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI(){

        espacio_1 = new TextView(this);
        espacio_1.setTextSize(tamanoLetraResolucionIncluida );
        espacio_1.setText("    ");

        espacio_2 = new TextView(this);
        espacio_2.setTextSize(tamanoLetraResolucionIncluida );
        espacio_2.setText("    ");

        text_periodo_muestreo = new TextView(this);
        text_periodo_muestreo.setGravity(Gravity.FILL_VERTICAL);
        text_periodo_muestreo.setBackgroundColor(Color.YELLOW);
        text_periodo_muestreo.setTextSize(tamanoLetraResolucionIncluida );
        text_periodo_muestreo.setText("  PERIODO MUESTREO EN ms (Mínimo 50)");
        text_periodo_muestreo.setTextColor(Color.BLACK);

        text_numero_datos = new TextView(this);
        text_numero_datos.setGravity(Gravity.FILL_VERTICAL);
        text_numero_datos.setBackgroundColor(Color.argb(100,220,156,80));
        text_numero_datos.setTextSize(tamanoLetraResolucionIncluida );
        text_numero_datos.setText("  NÚMERO DE DATOS (Maximo 2 000)");
        text_numero_datos.setTextColor(Color.BLACK);


        numero_datos = new EditText(this);
        //Despliega teclado solo con números enteros positivos.
        numero_datos.setKeyListener(DigitsKeyListener.getInstance(false, false));
        numero_datos.setTextSize(tamanoLetraResolucionIncluida );
        numero_datos.setText("" + AlmacenDatosRAM.nDatos);


        periodo_muestreo = new EditText(this);
        //Despliega teclado solo con números enteros positivos.
        periodo_muestreo.setKeyListener(DigitsKeyListener.getInstance(false, false));
        periodo_muestreo.setTextSize(tamanoLetraResolucionIncluida );
        periodo_muestreo.setText("" + AlmacenDatosRAM.periodoMuestreo);

    }



    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setBackgroundColor(Color.WHITE);


        //fila uno
        LinearLayout linear_fila_uno = new LinearLayout(this);
        linear_fila_uno.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los elementos que se agregaran esta fila
        linear_fila_uno.setWeightSum(1.0f);

        //fila dos
        LinearLayout linear_fila_dos = new LinearLayout(this);
        linear_fila_dos.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los elementos que se agregaran esta fila
        linear_fila_dos.setWeightSum(1.0f);

        //fila tres
        LinearLayout linear_fila_tres = new LinearLayout(this);
        linear_fila_tres.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los elementos que se agregaran esta fila
        linear_fila_tres.setWeightSum(3.0f);

        //fila cuatro
        LinearLayout linear_fila_cuatro = new LinearLayout(this);
        linear_fila_cuatro.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los elementos que se agregaran esta fila
        linear_fila_cuatro.setWeightSum(3.0f);

        linear_principal.addView(linear_fila_uno);
        linear_principal.addView(linear_fila_dos);
        linear_principal.addView(linear_fila_tres);
        linear_principal.addView(linear_fila_cuatro);

        //pegado de elementos a fila uno
        LinearLayout.LayoutParams parametros_pegado_elementos_fila_uno = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_fila_uno.weight = 1.0f;
        linear_fila_uno.addView(espacio_1, parametros_pegado_elementos_fila_uno);

        //pegado de elementos a fila dos
        LinearLayout.LayoutParams parametros_pegado_elementos_fila_dos = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_fila_dos.weight = 1.0f;
        linear_fila_dos.addView(espacio_2, parametros_pegado_elementos_fila_dos);

        //pegado de elementos a fila tres
        LinearLayout.LayoutParams parametros_pegado_elementos_fila_tres_izquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_fila_tres_izquierda.weight = 2.0f;
        linear_fila_tres.addView( text_periodo_muestreo,parametros_pegado_elementos_fila_tres_izquierda);

        LinearLayout.LayoutParams parametros_pegado_elementos_fila_tres_derecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_fila_tres_derecha.weight = 1.0f;
        linear_fila_tres.addView(periodo_muestreo,parametros_pegado_elementos_fila_tres_derecha);

        //pegado de elementos a fila cuatro
        LinearLayout.LayoutParams parametros_pegado_elementos_fila_cuatro_izquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_fila_cuatro_izquierda.weight = 2.0f;
        linear_fila_cuatro.addView(text_numero_datos,parametros_pegado_elementos_fila_cuatro_izquierda);

        LinearLayout.LayoutParams parametros_pegado_elementos_fila_cuatro_derecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_fila_cuatro_derecha.weight = 1.0f;
        linear_fila_cuatro.addView(numero_datos,parametros_pegado_elementos_fila_cuatro_derecha);

        return linear_principal;
    }


    /*
      Guardar el estado automáticamente cuando se salga
      de esta pantalla
    */

    protected void onPause() {
        super.onPause();

        AlmacenDatosRAM.configurar=true;
        String valor_muestreo = periodo_muestreo.getText().toString();
        String valor_n = numero_datos.getText().toString();
        AlmacenDatosRAM.periodoMuestreo = Integer.parseInt(valor_muestreo);
        AlmacenDatosRAM.nDatos = Integer.parseInt(valor_n);

    }

}
