package com.curso_simulaciones.midecimaseptimaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.midecimaseptimaapp.R;
import com.curso_simulaciones.midecimaseptimaapp.datos.AlmacenDatosRAM;

public class ActividadSecundaria_2 extends Activity {

    private int tamanoLetraResolucionIncluida;
    private int margenesResolucionIncluida;

    private TextView textNombre, textPrimerApellido, textSegundoApellido,
            textEdad;

    private EditText editNombres, editPrimerApellido, editSegundoApellido, editEdad;

    private ImageView imagen;


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


    } //fin del método onCreate


    private void gestionarResolucion() {


        tamanoLetraResolucionIncluida = (int) (0.8f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);
        margenesResolucionIncluida = (int) (1.2f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);


    }//fin método gestionarResolucion()


    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {

        textNombre = new TextView(this);
        textNombre.setGravity(Gravity.FILL_VERTICAL);
        textNombre.setBackgroundColor(Color.GREEN);
        textNombre.setTextSize(tamanoLetraResolucionIncluida);
        textNombre.setPadding(margenesResolucionIncluida, 0, 0, 0);
        textNombre.setText("NOMBRES");
        textNombre.setTextColor(Color.BLACK);

        textPrimerApellido = new TextView(this);
        textPrimerApellido.setGravity(Gravity.FILL_VERTICAL);
        textPrimerApellido.setBackgroundColor(Color.YELLOW);
        textPrimerApellido.setTextSize(tamanoLetraResolucionIncluida);
        textPrimerApellido.setPadding(margenesResolucionIncluida, 0, 0, 0);
        textPrimerApellido.setText("PRIMER APELLIDO");
        textPrimerApellido.setTextColor(Color.BLACK);

        textSegundoApellido = new TextView(this);
        textSegundoApellido.setGravity(Gravity.FILL_VERTICAL);
        textSegundoApellido.setBackgroundColor(Color.GREEN);
        textSegundoApellido.setTextSize(tamanoLetraResolucionIncluida);
        textSegundoApellido.setPadding(margenesResolucionIncluida, 0, 0, 0);
        textSegundoApellido.setText("SEGUNDO APELLIDO");
        textSegundoApellido.setTextColor(Color.BLACK);

        textEdad = new TextView(this);
        textEdad.setGravity(Gravity.FILL_VERTICAL);
        textEdad.setBackgroundColor(Color.YELLOW);
        textEdad.setTextSize(tamanoLetraResolucionIncluida);
        textEdad.setPadding(margenesResolucionIncluida, 0, 0, 0);
        textEdad.setText("EDAD");
        textEdad.setTextColor(Color.BLACK);


        editNombres = new EditText(this);
        editNombres.setTextSize(tamanoLetraResolucionIncluida);
        editNombres.setText(AlmacenDatosRAM.nombres);

        editPrimerApellido = new EditText(this);
        editPrimerApellido.setTextSize(tamanoLetraResolucionIncluida);
        editPrimerApellido.setText(AlmacenDatosRAM.apellidoUno);


        editSegundoApellido = new EditText(this);
        editSegundoApellido.setTextSize(tamanoLetraResolucionIncluida);
        editSegundoApellido.setText(AlmacenDatosRAM.apellidoDos);


        editEdad = new EditText(this);
//despliega solo el teclado para entrar números enteros positivos
        editEdad.setKeyListener(DigitsKeyListener.getInstance(false, false));
        editEdad.setTextSize(tamanoLetraResolucionIncluida);
        editEdad.setText("" + AlmacenDatosRAM.edad);


        imagen = new ImageView(this);
        imagen.setImageResource(R.drawable.identidad);


    }//fin método crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linearPrincipal = new LinearLayout(this);

        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
//pera definir los pesos de las filas que se agregaran
        linearPrincipal.setWeightSum(10.0f);

//LinearLyout secundaria: arriba
        LinearLayout linearSecundariaArriba = new LinearLayout(this);
//los componentes se agregarán verticalmente
        linearSecundariaArriba.setOrientation(LinearLayout.VERTICAL);
//para definir los pesos de las filas que se agregaran
        linearSecundariaArriba.setWeightSum(4.0f);

//LinearLyout secundaria: abajo
        LinearLayout linearSecundariaAbajo = new LinearLayout(this);
//los componentes se agregarán verticalmente
        linearSecundariaAbajo.setOrientation(LinearLayout.VERTICAL);
//pera definir los pesos de las filas que se agregaran
        linearSecundariaAbajo.setWeightSum(1.0f);

        /*
Pegado de las dos LinearLayout secundarias  a linearPrincipal.
Aqui se debe tener cuidado que el segundo parámetro es 0 indicando
que se agregan verticalmente respetando unos pesos. El primer parametro
es MATCH_PARENT indicando que esos componentes llenaran todo el ancho
del contenedor
*/

//pegar linear_secundaria_arriba a linear_principal
        LinearLayout.LayoutParams parametros_pegado_linear_secundaria_arriba_a_linear_principal = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
//ocupará el 60% de linear_principal
        parametros_pegado_linear_secundaria_arriba_a_linear_principal.weight = 6.0f;
        linearPrincipal.addView(linearSecundariaArriba, parametros_pegado_linear_secundaria_arriba_a_linear_principal);

//pegar linear_secundaria_abajo a linear_principal
        LinearLayout.LayoutParams parametros_pegado_linear_secundaria_abajo_a_linear_principal = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
//ocupará el 40% de linear_principal
        parametros_pegado_linear_secundaria_abajo_a_linear_principal.weight = 4.0f;
        linearPrincipal.addView(linearSecundariaAbajo, parametros_pegado_linear_secundaria_abajo_a_linear_principal);


        //creación las cuatro filas para pegar a linear_secundaria_arriba
        LinearLayout linearUno = new LinearLayout(this);
//los componentes se agregarán horizontalmente
        linearUno.setOrientation(LinearLayout.HORIZONTAL);
//para definir el peso de los componentes que se agregaran en esta fila
        linearUno.setWeightSum(3.0f);

        LinearLayout linearDos = new LinearLayout(this);
//los componentes se agregarán horizontalmente
        linearDos.setOrientation(LinearLayout.HORIZONTAL);
//para definir el peso de los componentes que se agregaran en  esta fila
        linearDos.setWeightSum(3.0f);

        LinearLayout linearTres = new LinearLayout(this);
//los componentes se agregarán horizontalmente
        linearTres.setOrientation(LinearLayout.HORIZONTAL);
//para definir el peso de los componentes que se agregaran esta fila
        linearTres.setWeightSum(3.0f);

        LinearLayout linearCuatro = new LinearLayout(this);
//los componentes se agregarán horizontalmente
        linearCuatro.setOrientation(LinearLayout.HORIZONTAL);
//para definir el peso de los componentes que se agregaran en esta fila
        linearCuatro.setWeightSum(3.0f);

//pegado de las filas linear a linear_secundaria_arriba
        LinearLayout.LayoutParams parametros_pegado_filas_a_linear_secundaria_arriba = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametros_pegado_filas_a_linear_secundaria_arriba .weight = 1.0f;
        linearSecundariaArriba.addView(linearUno, parametros_pegado_filas_a_linear_secundaria_arriba );
        linearSecundariaArriba.addView(linearDos, parametros_pegado_filas_a_linear_secundaria_arriba );
        linearSecundariaArriba.addView(linearTres, parametros_pegado_filas_a_linear_secundaria_arriba );
        linearSecundariaArriba.addView(linearCuatro, parametros_pegado_filas_a_linear_secundaria_arriba );


        /*
Pegado de las dos LinearLayout secundarias  a linear_principal.
Aqui se debe tener cuidado que el primer parámetro es 0 indicando
que se agregan horizontalmente respetando unos pesos. El segundo parametro
es MATCH_PARENT indicando que esos componentes llenaran todo el alto
del contenedor
*/


//pegado de componentes a las filas pegadas linear_secundaria_arriba
        LinearLayout.LayoutParams parametros_pegado_text_view_a_filas_arriba = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_text_view_a_filas_arriba.weight = 2.0f;
        int a=margenesResolucionIncluida;
        parametros_pegado_text_view_a_filas_arriba.setMargins(a,a,a,a);

        LinearLayout.LayoutParams parametros_pegado_edit_text_a_filas_arriba = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_edit_text_a_filas_arriba.weight = 1.0f;
        parametros_pegado_edit_text_a_filas_arriba.setMargins(a,a,a,a);

        linearUno.addView(textNombre, parametros_pegado_text_view_a_filas_arriba);
        linearUno.addView(editNombres, parametros_pegado_edit_text_a_filas_arriba);
        linearDos.addView(textPrimerApellido, parametros_pegado_text_view_a_filas_arriba);
        linearDos.addView(editPrimerApellido, parametros_pegado_edit_text_a_filas_arriba);
        linearTres.addView(textSegundoApellido, parametros_pegado_text_view_a_filas_arriba);
        linearTres.addView(editSegundoApellido, parametros_pegado_edit_text_a_filas_arriba);
        linearCuatro.addView(textEdad, parametros_pegado_text_view_a_filas_arriba);
        linearCuatro.addView(editEdad, parametros_pegado_edit_text_a_filas_arriba);


        //pegado de imagen a linear_secundaria_abajo
        LinearLayout.LayoutParams parametros_pegado_imagen_abajo = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametros_pegado_imagen_abajo.weight = 1.0f;
        linearSecundariaAbajo.addView(imagen,parametros_pegado_imagen_abajo);



        return linearPrincipal;

    }//fin método crearGUI


    /* Este método es automático*/
    protected void onPause() {

        String nombres = editNombres.getText().toString();
        String primer_apellido = editPrimerApellido.getText().toString();
        String segundo_apellido = editSegundoApellido.getText().toString();
        String edad = editEdad.getText().toString();

        AlmacenDatosRAM.nombres = nombres;
        AlmacenDatosRAM.apellidoUno = primer_apellido;
        AlmacenDatosRAM.apellidoDos = segundo_apellido;
        //pasar String a int
        AlmacenDatosRAM.edad = Integer.parseInt(edad);

        AlmacenDatosRAM.habilitar_boton_tres=true;

        super.onPause();


    }



    /*Administra los eventos de la GUI*/
    private void eventos() {


    }//fin método eventos



}

