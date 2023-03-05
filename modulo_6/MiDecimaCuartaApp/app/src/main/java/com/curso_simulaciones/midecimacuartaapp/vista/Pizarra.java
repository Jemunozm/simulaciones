package com.curso_simulaciones.midecimacuartaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.ObjetoEspacial;

public class Pizarra extends View {
    private ObjetoEspacial objetosEspacial[];


    /**
     * Constructor
     *
     * @param context
     */
    public Pizarra(Context context) {
        super(context);

    }


    public void setEstadoEscena(ObjetoEspacial[] cuerpos) {

        this.objetosEspacial = cuerpos;

    }


    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {

        //dibujar las poleas
        for (int i = 0; i < objetosEspacial.length; i++) {
            if (objetosEspacial[i] != null) {
                objetosEspacial[i].dibujese(canvas, pincel);
            }
        }


    }


    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        if (objetosEspacial != null)
            dibujarEscena(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();

    }


}
