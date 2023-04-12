package com.curso_simulaciones.mitrigesimasegundaapp.utilidades;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimasegundaapp.datos.AlmacenDatosRAM;

public class TablaSimple extends LinearLayout {

    private ScrollView panelScroll;
    private TableLayout table;
    private int tamanoLetraResolucionIncluida;
    private int dimensionReferencia;
    private Context context;
    private int contador = -1;
    private String etiquetaX="x";
    private String etiquetaY="y";
    private float x,y;
    private int colorColumna1= Color.YELLOW;
    private int colorColumna2=Color.BLUE;
    private int colorColumna3=Color.RED;
    // private int n=0;


    /**
     * Cosntructor de TablaSimple
     * @param context
     */
    public TablaSimple(Context context) {
        super(context);
        this.context=context;

        gestionarResolucion();



        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        gui();

    }


    private void gestionarResolucion(){

        /*
        El alto en la actividad principal (PORTRAI)
        corresponde al ancho aquí (LANSCAPE
        */
        dimensionReferencia = (int)(0.4f* AlmacenDatosRAM.alto);

        tamanoLetraResolucionIncluida = (int)(0.6* AlmacenDatosRAM.tamanoLetraResolucionIncluida);


    }//fin método gestionarResolucion()


    private void gui(){

        panelScroll = new ScrollView(context);
        table=new TableLayout(context);

        LinearLayout linearLayoutPrincipal = new LinearLayout(context);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.BLACK);

        LinearLayout.LayoutParams parametroPegado = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        panelScroll.addView(table);
        linearLayoutPrincipal.addView(panelScroll);

        this.addView(linearLayoutPrincipal, parametroPegado);


    }

    /**
     * Modifica las etiquetas de las columnas
     * @param etiquetaX
     * @param etiquetaY
     */
    public void setEtiquetaColumnas(String etiquetaX, String etiquetaY){

        this.etiquetaX=etiquetaX;
        this.etiquetaY=etiquetaY;

    }

    /**
     * Envía los datos a la tabla
     * @param x
     * @param y
     */
    public void enviarDatos(float x, float y){

        this.x=x;
        this.y=y;
        contador=contador+1;
        incrementarFila();

    }


    /**
     * Borra los datos enviados a la tabla
     */
    public void borrar(){
        removerFilas();
    }

    /**
     * Modifica los colores de las columnas
     * @param colorColumna1
     * @param colorColumna2
     * @param colorColumna3
     */
    public void setColorColumnas(int colorColumna1, int colorColumna2,int colorColumna3){

        this.colorColumna1=colorColumna1;
        this.colorColumna2=colorColumna2;
        this.colorColumna3=colorColumna3;

    }


    private void incrementarFila() {

        //crear nueva TableRow
        TableRow fila = new TableRow(context);

        TableRow.LayoutParams layoutTexto = new TableRow.LayoutParams((int)(0.33*dimensionReferencia), TableRow.LayoutParams.WRAP_CONTENT);


        //columna uno
        TextView textNumeroDato = new TextView(context);
        textNumeroDato.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textNumeroDato.setLayoutParams(layoutTexto);

        if(contador==0){

            textNumeroDato.setText("# de DATO");

        } else {

            textNumeroDato.setText("DATO " + contador);
        }

        //columna 2
        textNumeroDato.setTextSize(tamanoLetraResolucionIncluida);
        textNumeroDato.setTextColor(colorColumna1);
        textNumeroDato.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView textValorX = new TextView(context);
        textValorX.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorX.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorX.setText(etiquetaX);

        }  else {

            textValorX.setText("" + x);

        }

        //columna 3
        textValorX.setTextSize(tamanoLetraResolucionIncluida);
        textValorX.setTextColor(colorColumna2);
        textValorX.setGravity(Gravity.CENTER_HORIZONTAL);


        TextView textValorY = new TextView(context);
        textValorY.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorY.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorY.setText(etiquetaY);

        } else {

            textValorY.setText("" + y);

        }

        textValorY.setTextSize(tamanoLetraResolucionIncluida);
        textValorY.setTextColor(colorColumna3);
        textValorY.setGravity(Gravity.CENTER_HORIZONTAL);


        fila.setGravity(Gravity.CENTER_HORIZONTAL);
        //adicionar las tres columnas a la fila
        fila.addView(textNumeroDato);
        fila.addView(textValorX);
        fila.addView(textValorY);

        //Adicionar TabRow a la Tabla
        table.addView( fila, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


    }


    private void removerFilas() {


        if (contador > 1) {

            table.removeAllViews();
            contador=-1;

        }

    }
}
