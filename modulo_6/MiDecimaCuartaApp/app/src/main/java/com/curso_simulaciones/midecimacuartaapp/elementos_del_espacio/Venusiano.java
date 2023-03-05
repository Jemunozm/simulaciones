package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Venusiano extends Extraterrestre{
    private float tamaño = 50f;
    /**
     * Constructor por defecto
     * del marciano con &quot;centroide&quot; en (0,0)
     * y de radio 50f
     */
    public Venusiano() {
        super();
    }

    /**
     * Constructor del marciano con &quot;centroide&quot;
     * en (posicionCentroideX,posicionCentroideY)
     * y diámetro igual a 2*radio
     */
    public Venusiano(float posicionCentroideX, float posicionCentroideY) {
        super(posicionCentroideX, posicionCentroideY);
    }

    public void setTamaño(float tamaño){
        this.tamaño =  tamaño;
    }

    public void dibujese(Canvas canvas, Paint pincel) {
        //estilo del pindel
        pincel.setStyle(Paint.Style.STROKE);
//grosor del pincel
        pincel.setStrokeWidth(2f);
//color del pincel
        pincel.setColor(color);

        canvas.save();
//magnificar
        canvas.scale(magnificacion, magnificacion, posicionCentroideX, posicionCentroideY);


//rotar
        canvas.rotate(posicionAngularRotacionEjeXY, posicionEjeRotacionX, posicionEjeRotacionY);
//dibujar círculo de la cara del marciano
        float radio = tamaño;
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(posicionCentroideX, posicionCentroideY, radio, pincel);


//dibujar los ojitos del marcianito
        float separacion = 0.3f * radio;
        float posicionOjoCentroIzquierdoX = posicionCentroideX - 1.1f*separacion;
        float posicionOjoCentroIzquierdoY = posicionCentroideY - separacion;
        float posicionOjoCentroDerechoX = posicionCentroideX + 1.1f*separacion;
        float posicionOjoCentroDerechoY = posicionCentroideY - separacion;
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.WHITE);
        canvas.drawOval(posicionOjoCentroIzquierdoX-0.28f*radio,
                posicionOjoCentroIzquierdoY-0.3f*radio,posicionOjoCentroIzquierdoX+0.28f*radio,
                posicionOjoCentroIzquierdoY+0.3f*radio,pincel);
        canvas.drawOval(posicionOjoCentroDerechoX+0.28f*radio,
                posicionOjoCentroDerechoY-0.3f*radio,posicionOjoCentroDerechoX-0.28f*radio,
                posicionOjoCentroDerechoY+0.3f*radio,pincel);
//dibujar la pupila de los ojitos
        pincel.setColor(color);
        canvas.drawCircle(posicionOjoCentroIzquierdoX, posicionOjoCentroIzquierdoY,
                0.07f * radio, pincel);
        canvas.drawCircle(posicionOjoCentroDerechoX, posicionOjoCentroDerechoY,
                0.07f * radio, pincel);
//dibujar la boquita del marcianito
        float altoBoca = 0.05f * radio;
        float anchoBoca = 0.6f * radio;
        pincel.setColor(Color.WHITE);
        float descenso = 0.4f * radio;
        float posicionBocaCentroX = posicionCentroideX;
        float posicionBocaCentroY = posicionCentroideY + descenso;
        float xs_izquierda = posicionBocaCentroX - 0.5f * anchoBoca;
        float ys_izquierda = posicionBocaCentroY - 0.5f * altoBoca;
        float xi_derecha = posicionBocaCentroX + 0.5f * anchoBoca;
        float yi_derecha = posicionBocaCentroY + 0.5f * altoBoca;
        canvas.drawRect(xs_izquierda, ys_izquierda, xi_derecha, yi_derecha, pincel);

        //gorrito del venusiano
        pincel.setColor(color);
        canvas.drawOval(posicionOjoCentroIzquierdoX-0.5f*radio,posicionCentroideY-1.3f*radio, posicionOjoCentroDerechoX+0.5f*radio,
                posicionCentroideY-0.6f*radio,pincel);

//regresa la rotación
        canvas.restore();

    }
}
