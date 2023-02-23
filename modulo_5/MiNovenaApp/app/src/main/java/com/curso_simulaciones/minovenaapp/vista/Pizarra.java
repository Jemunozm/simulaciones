package com.curso_simulaciones.minovenaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.minovenaapp.objetos_laboratorio.Rueda;


public class Pizarra extends View {

    private Rueda ruedas[];


    /**
     * Constructor
     *
     * @param context
     */
    public Pizarra(Context context) {
        super(context);

    }


    /**
     * Método para cambiar la escena
     *
     * @param ruedas
     */
    public void setEstadoEscena(Rueda[] ruedas) {

        this.ruedas = ruedas;


    }


    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {


        for (int i = 0; i < ruedas.length; i++) {
            if (ruedas[i] != null) {
                ruedas[i].dibujese(canvas, pincel);
            }
        }

    }


    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        if (ruedas != null)
            dibujarEscena(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();
    }
}

