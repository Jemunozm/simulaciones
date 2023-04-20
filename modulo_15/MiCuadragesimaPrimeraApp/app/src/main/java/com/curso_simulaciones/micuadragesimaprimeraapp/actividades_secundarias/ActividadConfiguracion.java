package com.curso_simulaciones.micuadragesimaprimeraapp.actividades_secundarias;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.micuadragesimaprimeraapp.datos.AlmacenDatosRAM;

public class ActividadConfiguracion extends Activity {

    private EditText edit_text_broker, edit_text_usuario, edit_text_topico,
            edit_text_contrasena;
    ;
    private TextView text_broker, text_usuario, text_contrasena, text_topico;
    private int tamano_letra;
    private Button boton_grabar;

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

        //Eventos GUI
        eventosGui();

    } //fin del método onCreate

    private void gestionarResolucion() {

        tamano_letra = (int) (0.5f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);

    }

    private void eventosGui() {

        boton_grabar.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {

                guardar();
                //regresar a la actividad anterior
                onBackPressed();

            }
        });
    }


    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        text_broker = new TextView(this);
        text_broker.setGravity(Gravity.FILL_VERTICAL);
        text_broker.setBackgroundColor(Color.YELLOW);
        text_broker.setTextSize(tamano_letra);
        text_broker.setText("  URL DEL BROKER");
        text_broker.setTextColor(Color.BLACK);

        edit_text_broker = new EditText(this);
        edit_text_broker.setTextSize(tamano_letra);
        edit_text_broker.setText("" + AlmacenDatosRAM.MQTTHOST);

        text_usuario = new TextView(this);
        text_usuario.setGravity(Gravity.FILL_VERTICAL);
        text_usuario.setBackgroundColor(Color.YELLOW);
        text_usuario.setTextSize(tamano_letra);
        text_usuario.setText("  USUARIO");
        text_usuario.setTextColor(Color.BLACK);

        edit_text_usuario = new EditText(this);
        edit_text_usuario.setTextSize(tamano_letra);
        edit_text_usuario.setText("" + AlmacenDatosRAM.USERNAME);

        text_contrasena = new TextView(this);
        text_contrasena.setGravity(Gravity.FILL_VERTICAL);
        text_contrasena.setBackgroundColor(Color.YELLOW);
        text_contrasena.setTextSize(tamano_letra);
        text_contrasena.setText("  CONTRASEÑA");
        text_contrasena.setTextColor(Color.BLACK);

        edit_text_contrasena = new EditText(this);
        // edit_text_contrasena.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edit_text_contrasena.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edit_text_contrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edit_text_contrasena.setTextSize(tamano_letra);
        edit_text_contrasena.setText("" + AlmacenDatosRAM.PASSWORD);

        text_topico = new TextView(this);
        text_topico.setGravity(Gravity.FILL_VERTICAL);
        text_topico.setBackgroundColor(Color.YELLOW);
        text_topico.setTextSize(tamano_letra);
        text_topico.setText("  TOPICO");
        text_topico.setTextColor(Color.BLACK);

        edit_text_topico = new EditText(this);
        edit_text_topico.setTextSize(tamano_letra);
        edit_text_topico.setText("" + AlmacenDatosRAM.topicStr);

        boton_grabar = new Button(this);
        boton_grabar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamano_letra);
        boton_grabar.setText("GUARDAR");
        boton_grabar.getBackground().setColorFilter(Color.rgb(255, 255, 100), PorterDuff.Mode.MULTIPLY);

    }


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setBackgroundColor(Color.WHITE);

        //LinearLayout primera fila
        LinearLayout linearLayoutPrimeraFila = new LinearLayout(this);
        linearLayoutPrimeraFila.setBackgroundColor(Color.YELLOW);
        linearLayoutPrimeraFila.setWeightSum(2f);
        linearLayoutPrimeraFila.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayout segunda fila
        LinearLayout linearLayoutSegundaFila = new LinearLayout(this);
        linearLayoutSegundaFila.setBackgroundColor(Color.YELLOW);
        linearLayoutSegundaFila.setWeightSum(2f);
        linearLayoutSegundaFila.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayout tercera fila
        LinearLayout linearLayoutTerceraFila = new LinearLayout(this);
        linearLayoutTerceraFila.setBackgroundColor(Color.YELLOW);
        linearLayoutTerceraFila.setWeightSum(2f);
        linearLayoutTerceraFila.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayout cuarta fila
        LinearLayout linearLayoutCuartaFila = new LinearLayout(this);
        linearLayoutCuartaFila.setBackgroundColor(Color.YELLOW);
        linearLayoutCuartaFila.setWeightSum(2f);
        linearLayoutCuartaFila.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayout quinta fila
        LinearLayout linearLayoutQuintaFila = new LinearLayout(this);
        linearLayoutQuintaFila.setBackgroundColor(Color.YELLOW);
        linearLayoutQuintaFila.setWeightSum(2f);
        linearLayoutQuintaFila.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayout sexta fila
        LinearLayout linearLayoutSextaFila = new LinearLayout(this);
        linearLayoutSextaFila.setBackgroundColor(Color.YELLOW);
        linearLayoutSextaFila.setWeightSum(1f);
        linearLayoutSextaFila.setOrientation(LinearLayout.HORIZONTAL);

        //LinearLayout septima fila y pegar panel deslizable
        LinearLayout linearLayoutSeptimaFila = new LinearLayout(this);
        linearLayoutSeptimaFila.setBackgroundColor(Color.BLACK);
        linearLayoutSeptimaFila.setOrientation(LinearLayout.VERTICAL);

        //pegar las filas en el principal
        LinearLayout.LayoutParams parametrosPrimerasCincoFilas = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrimerasCincoFilas.weight = 0.5f;
        linearLayoutPrimeraFila.setLayoutParams(parametrosPrimerasCincoFilas);
        linearLayoutSegundaFila.setLayoutParams(parametrosPrimerasCincoFilas);
        linearLayoutTerceraFila.setLayoutParams(parametrosPrimerasCincoFilas);
        linearLayoutCuartaFila.setLayoutParams(parametrosPrimerasCincoFilas);
        linearLayoutQuintaFila.setLayoutParams(parametrosPrimerasCincoFilas);
        linearLayoutSextaFila.setLayoutParams(parametrosPrimerasCincoFilas);

        LinearLayout.LayoutParams parametrosSeptimaFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSeptimaFila.weight = 7.5f;
        linearLayoutSeptimaFila.setLayoutParams(parametrosSeptimaFila);

        linear_principal.addView(linearLayoutPrimeraFila);
        linear_principal.addView(linearLayoutSegundaFila);
        linear_principal.addView(linearLayoutTerceraFila);
        linear_principal.addView(linearLayoutCuartaFila);
        linear_principal.addView(linearLayoutQuintaFila);
        linear_principal.addView(linearLayoutSextaFila);
        linear_principal.addView(linearLayoutSeptimaFila);

        //pegar elementos en cinco primeras filas
        LinearLayout.LayoutParams parametros_pegado_elementos_cinco_primeras_filas_I = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_cinco_primeras_filas_I.weight = 1.0f;
        LinearLayout.LayoutParams parametros_pegado_elementos_cinco_primeras_filas_D = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elementos_cinco_primeras_filas_D.weight = 1.0f;
        //primera fila
        text_broker.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_I);
        edit_text_broker.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_D);
        linearLayoutPrimeraFila.addView(text_broker);
        linearLayoutPrimeraFila.addView(edit_text_broker);

        //segunda fila
        text_usuario.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_I);
        edit_text_usuario.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_D);
        linearLayoutSegundaFila.addView(text_usuario);
        linearLayoutSegundaFila.addView(edit_text_usuario);
        //tercera fila
        text_contrasena.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_I);
        edit_text_contrasena.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_D);
        linearLayoutTerceraFila.addView(text_contrasena);
        linearLayoutTerceraFila.addView(edit_text_contrasena);

        //cuarta fila
        text_topico.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_I);
        edit_text_topico.setLayoutParams(parametros_pegado_elementos_cinco_primeras_filas_D);
        linearLayoutCuartaFila.addView(text_topico);
        linearLayoutCuartaFila.addView(edit_text_topico);

        //pegar elemento quinta fila
        LinearLayout.LayoutParams parametros_pegado_elemento_quinta_fila = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_elemento_quinta_fila.weight = 1.0f;
        linearLayoutSextaFila.addView(boton_grabar, parametros_pegado_elemento_quinta_fila);


        return linear_principal;
    }


    /*
      Guardar el estado automáticamente cuando se salga
      de esta pantalla
    */
    protected void onPause() {
        super.onPause();

    }

    private void guardar() {

        String broker = edit_text_broker.getText().toString();
        AlmacenDatosRAM.MQTTHOST = broker;

        String usuario = edit_text_usuario.getText().toString();
        AlmacenDatosRAM.USERNAME = usuario;

        String pasword = edit_text_contrasena.getText().toString();
        AlmacenDatosRAM.PASSWORD = pasword;

        String topico = edit_text_topico.getText().toString();
        AlmacenDatosRAM.topicStr = topico;


        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("broker", AlmacenDatosRAM.MQTTHOST);
        editor.putString("usuario", AlmacenDatosRAM.USERNAME);
        editor.putString("pasword", AlmacenDatosRAM.PASSWORD);
        editor.putString("topico", AlmacenDatosRAM.topicStr);
        editor.commit();

    }

}
