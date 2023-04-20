package com.curso_simulaciones.mitrigesimacuartaapp.utilidades;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimacuartaapp.datos.AlmacenDatosRAM;

public class TablaSimple extends LinearLayout {

    private ScrollView panelScroll;
    private TableLayout table;
    private int tamanoLetraResolucionIncluida;
    private int dimensionReferencia;
    private Context context;
    private int contador = -1;
    private String etiquetaX="x";
    private String etiquetaY="y";
    private String etiquetaY1="y1";
    private String etiquetaY2="y2";
    private String etiquetaY3="y3";
    private float x,y,y1,y2,y3;
    private int colorColumna1= Color.YELLOW;
    private int colorColumna2=Color.BLUE;
    private int colorColumna3=Color.RED;
    private int colorColumna4=Color.RED;
    private int colorColumna5=Color.RED;
    private int colorColumna6=Color.RED;
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

        tamanoLetraResolucionIncluida = (int)(0.5* AlmacenDatosRAM.tamanoLetraResolucionIncluida);


    }//fin método gestionarResolucion()


    private void gui(){

        panelScroll = new ScrollView(context);
        table=new TableLayout(context);

        LinearLayout linearLayoutPrincipal = new LinearLayout(context);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.BLACK);

        LayoutParams parametroPegado = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        panelScroll.addView(table);
        linearLayoutPrincipal.addView(panelScroll);

        this.addView(linearLayoutPrincipal, parametroPegado);


    }

    /**
     * Modifica las etiquetas de las columnas
     * @param etiquetaX
     * @param etiquetaY
     * @param etiquetaY1
     * @param etiquetaY2
     * @param etiquetaY3
     */
    public void setEtiquetaColumnas(String etiquetaX, String etiquetaY, String etiquetaY1, String etiquetaY2, String etiquetaY3){

        this.etiquetaX=etiquetaX;
        this.etiquetaY=etiquetaY;
        this.etiquetaY1=etiquetaY1;
        this.etiquetaY2=etiquetaY2;
        this.etiquetaY3=etiquetaY3;

    }

    /**
     * Envía los datos a la tabla
     * @param x
     * @param y
     * @param y1
     * @param y2
     * @param y3
     */
    public void enviarDatos(float x, float y, float y1, float y2, float y3){

        this.x=x;
        this.y=y;
        this.y1=y1;
        this.y2=y2;
        this.y3=y3;
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
    public void setColorColumnas(int colorColumna1, int colorColumna2,int colorColumna3, int colorColumna4, int colorColumna5, int colorColumna6){

        this.colorColumna1=colorColumna1;
        this.colorColumna2=colorColumna2;
        this.colorColumna3=colorColumna3;
        this.colorColumna4=colorColumna4;
        this.colorColumna5=colorColumna5;
        this.colorColumna6=colorColumna6;

    }


    private void incrementarFila() {

        //crear nueva TableRow
        TableRow fila = new TableRow(context);

        TableRow.LayoutParams layoutTexto = new TableRow.LayoutParams((int)(0.18*dimensionReferencia), TableRow.LayoutParams.WRAP_CONTENT);


        //columna uno
        TextView textNumeroDato = new TextView(context);
        textNumeroDato.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textNumeroDato.setLayoutParams(layoutTexto);

        if(contador==0){

            textNumeroDato.setText("# de DATO");

        } else {

            textNumeroDato.setText("DATO " + contador);
        }

        textNumeroDato.setTextSize(tamanoLetraResolucionIncluida);
        textNumeroDato.setTextColor(colorColumna1);
        textNumeroDato.setGravity(Gravity.CENTER_HORIZONTAL);


        //columna 2
        TextView textValorX = new TextView(context);
        textValorX.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorX.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorX.setText(etiquetaX);

        }  else {

            textValorX.setText("" + x);

        }

        textValorX.setTextSize(tamanoLetraResolucionIncluida);
        textValorX.setTextColor(colorColumna2);
        textValorX.setGravity(Gravity.CENTER_HORIZONTAL);


        //columna 3
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

        //columna 4
        TextView textValorY1 = new TextView(context);
        textValorY1.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorY1.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorY1.setText(etiquetaY1);

        } else {

            textValorY1.setText("" + y1);

        }

        textValorY1.setTextSize(tamanoLetraResolucionIncluida);
        textValorY1.setTextColor(colorColumna4);
        textValorY1.setGravity(Gravity.CENTER_HORIZONTAL);

        //columna 5
        TextView textValorY2 = new TextView(context);
        textValorY2.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorY2.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorY2.setText(etiquetaY2);

        } else {

            textValorY2.setText("" + y2);

        }

        textValorY2.setTextSize(tamanoLetraResolucionIncluida);
        textValorY2.setTextColor(colorColumna5);
        textValorY2.setGravity(Gravity.CENTER_HORIZONTAL);


        //columna 6
        TextView textValorY3 = new TextView(context);
        textValorY3.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorY3.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorY3.setText(etiquetaY3);

        } else {

            textValorY3.setText("" + y3);

        }

        textValorY3.setTextSize(tamanoLetraResolucionIncluida);
        textValorY3.setTextColor(colorColumna6);
        textValorY3.setGravity(Gravity.CENTER_HORIZONTAL);


        fila.setGravity(Gravity.CENTER_HORIZONTAL);
        //adicionar las tres columnas a la fila
        fila.addView(textNumeroDato);
        fila.addView(textValorX);
        fila.addView(textValorY);
        fila.addView(textValorY1);
        fila.addView(textValorY2);
        fila.addView(textValorY3);

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
