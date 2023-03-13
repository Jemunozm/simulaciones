package com.curso_simulaciones.midecimanovenaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.midecimanovenaapp.datos.AlmacenDatosRAM;

public class ActividadSecundaria_3 extends Activity {

    private int tamanoLetraResolucionIncluida,margenesResolucionIncluida;
    private TextView aviso;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestionarResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //para administrar los eventos
        eventos();
    }//fin del método onCreate

    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;
        margenesResolucionIncluida = (int) (1f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionarResolucion()
    private void crearElementosGUI(){
        aviso = new TextView(this);
        aviso.setTextSize(tamanoLetraResolucionIncluida);
        aviso.setTextColor(Color.WHITE);
        String nombres= AlmacenDatosRAM.nombres;
        String nombres1= AlmacenDatosRAM.nombres1;

        String componer_cadena= "La imagen de la ActividadSecundaria_1 se denomina " + nombres
                + " y la de la AplicacionSecundaria_2 se denomina " + nombres1;

        aviso.setText(componer_cadena);
    }
    private LinearLayout crearGUI() {
        LinearLayout linearPrincipal = new LinearLayout(this);

        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.rgb(35, 3, 36));
        linearPrincipal.setWeightSum(1.0f);

        //parámtero de pegado
        //pegado de botones a cada fila
        LinearLayout.LayoutParams parametros_aviso = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_aviso.weight = 1.0f;
        parametros_aviso.setMargins(margenesResolucionIncluida, 0, 0, 0);
        linearPrincipal.addView(aviso, parametros_aviso);

        return linearPrincipal;
    }
    private void eventos(){

    }//fin método eventos

}
