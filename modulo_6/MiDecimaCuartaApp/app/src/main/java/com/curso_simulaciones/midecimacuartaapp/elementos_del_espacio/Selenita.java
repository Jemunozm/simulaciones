package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Selenita extends Extraterrestre{
    private float tamaño = 50f;

    public Selenita() {super();}

    public Selenita(float posicionCentroideX, float posicionCentroideY) {
        super(posicionCentroideX, posicionCentroideY);
    }
    public void setTamaño(float tamaño){
        this.tamaño = tamaño;
    }
    public void dibujese(Canvas canvas, Paint pincel) {
        //estilo del pindel
        pincel.setStyle(Paint.Style.STROKE);
        //grosor del pincel
        pincel.setStrokeWidth(2f);
        //color del pincel
        pincel.setColor(color);

        canvas.save();

        //rotar
        canvas.rotate(posicionAngularRotacionEjeXY, posicionEjeRotacionX, posicionEjeRotacionY);
        //dibujar círculo de la cara del marciano
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.BLUE);
        canvas.drawCircle(posicionCentroideX-0.05f*tamaño,posicionCentroideY-1.01f*tamaño,0.2f*tamaño,pincel);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color);
        canvas.drawRect(posicionCentroideX-0.8f*tamaño,posicionCentroideY+1.1f*tamaño,posicionCentroideX+0.8f*tamaño,posicionCentroideY-tamaño,pincel);
        pincel.setColor(Color.BLACK);

        //dibujar los ojitos del marcianito
        float separacion = 0.3f * tamaño;
        float posicionOjoCentroIzquierdoX = posicionCentroideX - separacion;
        float posicionOjoCentroIzquierdoY = posicionCentroideY - separacion;
        float posicionOjoCentroDerechoX = posicionCentroideX + separacion;
        float posicionOjoCentroDerechoY = posicionCentroideY - separacion;
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.WHITE);
        canvas.drawRect(posicionOjoCentroDerechoX-(0.4f*separacion),posicionOjoCentroDerechoY-(0.4f*separacion),posicionOjoCentroDerechoX+(0.4f*separacion),posicionOjoCentroDerechoY+(0.4f*separacion),pincel);
        canvas.drawRect(posicionOjoCentroIzquierdoX-(0.4f*separacion),posicionOjoCentroIzquierdoY-(0.4f*separacion),posicionOjoCentroIzquierdoX+(0.4f*separacion),posicionOjoCentroIzquierdoY+(0.4f*separacion),pincel);

        //dibujar la boquita del marcianito
        float altoBoca = 0.2f * tamaño;
        float anchoBoca = 0.48f * tamaño;
        pincel.setColor(Color.WHITE);
        float descenso = 0.35f * tamaño;
        float posicionBocaCentroX = posicionCentroideX;
        float posicionBocaCentroY = posicionCentroideY + descenso;
        float xs_izquierda = posicionBocaCentroX - 0.5f * anchoBoca;
        float ys_izquierda = posicionBocaCentroY - 0.5f * altoBoca;
        float xi_derecha = posicionBocaCentroX + 0.5f * anchoBoca;
        float yi_derecha = posicionBocaCentroY + 0.5f * altoBoca;
        canvas.drawRect(xs_izquierda, ys_izquierda, xi_derecha, yi_derecha, pincel);

        //tumor en la cabeza
        pincel.setStrokeWidth(2f);
        pincel.setColor(color);
        canvas.drawCircle(posicionCentroideX-0.05f*tamaño,posicionCentroideY-1.01f*tamaño,0.2f*tamaño,pincel);

        canvas.restore();
    }
}
