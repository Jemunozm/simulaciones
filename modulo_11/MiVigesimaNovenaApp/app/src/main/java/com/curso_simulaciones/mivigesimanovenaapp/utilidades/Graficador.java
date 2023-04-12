package com.curso_simulaciones.mivigesimanovenaapp.utilidades;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Graficador extends LineChart {


    private LineDataSet lineDataSet;
    private String tituloEjeY="";
    private int colorLinea;
    private float grosorLinea=1f;
    private int colorValores;
    private int colorMarcadores;



    public Graficador(Context context) {

        super(context);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }


    }


    public void setDatos(ArrayList datos) {

        LineDataSet lineDataSet = new LineDataSet(datos, tituloEjeY);
        lineDataSet.setLineWidth(grosorLinea);
        lineDataSet.setColor(colorLinea);
        lineDataSet.setValueTextColor(colorValores);
        lineDataSet.setCircleColor(colorMarcadores);
        LineData lineData = new LineData(lineDataSet);
        this.setData(lineData);

        /*
         Esta instrucción es de la librería y es necesaria para
         que el eje se vaya desplazando a medida que entran datos
         */

        this.moveViewTo(lineData.getEntryCount() - 7, 50f, YAxis.AxisDependency.LEFT);


    }


    /*
     Modifica el grosr del trazo de la gráfica
     */
    public void setGrosorLinea(float grosorLinea){

        this.grosorLinea=grosorLinea;
    }

    public void setColorLinea(int colorLinea){

        this.colorLinea=colorLinea;

    }


    public  void setColorFondo(int colorFondo){

        this.setBackgroundColor(colorFondo);

    }


    public void setColorTextoEjes(int colorTextoEjes){

        //texto eje y izquierda
        this.getAxisLeft().setTextColor(colorTextoEjes);
        //texto eje y derecha
        this.getAxisRight().setTextColor(colorTextoEjes);
        //texto eje x
        this.getXAxis().setTextColor(colorTextoEjes);
        //texto título eje y
        this.getLegend().setTextColor(colorTextoEjes);
        //texto título eje x
        this.getDescription().setTextColor(colorTextoEjes);

    }

    public void setColorValores(int colorValores){

        this.colorValores=colorValores;


    }


    public void setTituloEjeX(String tituloEjeX){


        this.getDescription().setText(tituloEjeX);

    }


    public void setTituloEjeY(String tituloEjeY){

        this.tituloEjeY=tituloEjeY;

    }


    public void setColorMarcadores(int colorMarcadores){

        this.colorMarcadores=colorMarcadores;
    }


}
