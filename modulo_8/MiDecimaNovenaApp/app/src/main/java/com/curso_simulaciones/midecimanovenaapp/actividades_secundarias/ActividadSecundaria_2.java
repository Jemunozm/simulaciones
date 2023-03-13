package com.curso_simulaciones.midecimanovenaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.midecimanovenaapp.R;
import com.curso_simulaciones.midecimanovenaapp.datos.AlmacenDatosRAM;

public class ActividadSecundaria_2 extends Activity{
    private TextView textNombre;
    private EditText editNombres;
    private int tamanoLetraResolucionIncluida;
    private ImageView imagen;
    private int margenesResolucionIncluida;

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
        int tamanoLetra = dimensionReferencia / 26;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;
        margenesResolucionIncluida = (int) (1f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }//fin método gestionarResolucion()

    private void crearElementosGUI() {
        textNombre = new TextView(this);
        textNombre.setGravity(Gravity.CENTER);
        textNombre.setBackgroundColor(Color.rgb(44, 5, 46));
        textNombre.setTextSize(TypedValue.COMPLEX_UNIT_SP, 2.5f * tamanoLetraResolucionIncluida);
        textNombre.setText("NOMBRE");
        textNombre.setTextColor(Color.rgb(187,143,206));

        editNombres = new EditText(this);
        editNombres.setTextSize(tamanoLetraResolucionIncluida);
        editNombres.setText(AlmacenDatosRAM.nombres1);


        imagen = new ImageView(this);
        imagen.setImageResource(R.drawable.pngegg);
    }

    private LinearLayout crearGUI() {
        int a = margenesResolucionIncluida;
        LinearLayout linearPrincipal = new LinearLayout(this);
        //los componentes se agregarán verticalmente
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        //para definir los pesos de las filas que se agregaran
        linearPrincipal.setWeightSum(10.0f);
        linearPrincipal.setBackgroundColor(Color.rgb(44, 5, 46));

        LinearLayout linearArriba = new LinearLayout(this);
        //los componentes se agregarán verticalmente
        linearArriba.setOrientation(LinearLayout.HORIZONTAL);
        //para definir los pesos de las filas que se agregaran
        linearArriba.setWeightSum(2.0f);
        linearArriba.setBackgroundColor(Color.rgb(187, 143, 206));

        LinearLayout.LayoutParams parametro_pegado_linear_arriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametro_pegado_linear_arriba.weight = 1.0f;
        parametro_pegado_linear_arriba.setMargins(a, a, a, a);
        linearPrincipal.addView(linearArriba, parametro_pegado_linear_arriba);


        LinearLayout linearAbajo = new LinearLayout(this);
        //los componentes se agregarán horizontalmente
        linearAbajo.setOrientation(LinearLayout.HORIZONTAL);
        //para definir el peso de los botones que se agregaran esta fila
        linearAbajo.setWeightSum(1.0f);
        linearAbajo.setBackgroundColor(Color.rgb(187, 143, 206));

        LinearLayout.LayoutParams parametro_pegado_linear_abajo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametro_pegado_linear_abajo.weight = 9.0f;
        parametro_pegado_linear_abajo.setMargins(a, a, a, a);
        linearPrincipal.addView(linearAbajo, parametro_pegado_linear_abajo);

        LinearLayout.LayoutParams parametros_pegado_text_view_a_filas_arriba = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_text_view_a_filas_arriba.weight = 1.0f;
        parametros_pegado_text_view_a_filas_arriba.setMargins(3 * a, 3*a, 2 * a,  a);

        LinearLayout.LayoutParams parametros_pegado_edit_text_a_filas_arriba = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_edit_text_a_filas_arriba.weight = 1.0f;
        parametros_pegado_edit_text_a_filas_arriba.setMargins(2 * a, 3*a, 3 * a,  a);

        linearArriba.addView(textNombre, parametros_pegado_text_view_a_filas_arriba);
        linearArriba.addView(editNombres, parametros_pegado_edit_text_a_filas_arriba);


        LinearLayout.LayoutParams parametros_pegado_imagen_arriba = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_imagen_arriba.weight = 1.0f;
        linearAbajo.addView(imagen, parametros_pegado_imagen_arriba);

        return linearPrincipal;
    }

    protected void onPause() {
        String nombres = editNombres.getText().toString();
        AlmacenDatosRAM.nombres1 = nombres;
        AlmacenDatosRAM.habilitar_boton_tres=true;

        super.onPause();
    }

    private void eventos() {
    }

}

