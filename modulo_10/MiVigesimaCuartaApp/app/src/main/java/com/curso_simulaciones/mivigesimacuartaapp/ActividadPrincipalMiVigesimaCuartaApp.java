package com.curso_simulaciones.mivigesimacuartaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimacuartaapp.controlador.ActividadControladora;
import com.curso_simulaciones.mivigesimacuartaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimacuartaapp.utilidades.Boton;

public class ActividadPrincipalMiVigesimaCuartaApp extends Activity {

    private int tamanoLetraResolucionIncluida;

    private Boton entrar, salir;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gestionarResolucion();

        //Creación elementosGUI
        creacionElementosGui();


        ViewGroup.LayoutParams linear_layou_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setContentView(crearGUI(), linear_layou_principal);


        eventos();


    }//fin onCreate




    /*Método auxiliar para asuntos de resolución*/
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

        //guardar en almacen annho y alto de pantalla:
        //este cálculo está con dispoitivo móvil
        //en orientación portrait
        AlmacenDatosRAM.ancho_pantalla =ancho;
        AlmacenDatosRAM.alto_pantalla =alto;

    }

    //Creación elementosGUI
    private void creacionElementosGui() {

        //aceptar = new Button(this);
        entrar = new Boton(this);
        entrar.setImagen(R.drawable.entrar);
        entrar.setText(" ");

        salir = new Boton(this);
        salir.setImagen(R.drawable.salir);
        salir.setText(" ");



    }

    private LinearLayout crearGUI(){

        //LinearLayoutPrincipal
        LinearLayout linear_layout_principal  = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        linear_layout_principal.setWeightSum(10);

        //fondo primera fila
        Drawable fondo = getResources().getDrawable(R.drawable.imagen_entrada_app_24);

        //LinearLayout primera fila
        LinearLayout linear_layout_primera_fila = new LinearLayout(this);
        linear_layout_primera_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_fila.setGravity(Gravity.FILL);
        linear_layout_primera_fila.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams parametros_primera_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);//ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_primera_fila.weight = 8.0f;
        linear_layout_primera_fila.setLayoutParams(parametros_primera_fila);
        linear_layout_primera_fila.setBackgroundDrawable(fondo);

        //LinearLayout segunda fila
        LinearLayout linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);// ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_segunda_fila.weight = 2.0f;
        linear_layout_segunda_fila.setWeightSum(2);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);



        //pegado botones abajo
        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_boton.weight = 1.0f;
        int borde= 10;
        parametros_pegado_boton.setMargins(borde,borde,borde,borde);
        entrar.setLayoutParams(parametros_pegado_boton);
        salir.setLayoutParams(parametros_pegado_boton);
        linear_layout_segunda_fila.addView(entrar);
        linear_layout_segunda_fila.addView(salir);

        linear_layout_principal.addView(linear_layout_primera_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);

        return linear_layout_principal;

    }


    private void eventos(){

        //evento
        entrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarEntrar();

            }
        });

        salir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarSalir();


            }
        });


    }


    private void lanzarEntrar(){

        Intent intent = new Intent(this, ActividadControladora.class);
        startActivity(intent);

    }


    private void lanzarSalir(){

        this.finish();

    }

    @Override
    protected void onStop() {
        //..
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //...

    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

}
