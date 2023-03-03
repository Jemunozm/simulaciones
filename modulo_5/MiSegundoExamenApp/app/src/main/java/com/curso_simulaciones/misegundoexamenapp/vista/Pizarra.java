package com.curso_simulaciones.misegundoexamenapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.misegundoexamenapp.objetos_laboratorio.Bala;


public class Pizarra extends View {

    private Bala balas[];


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
     * @param balas
     */
    public void setEstadoEscena(Bala[] balas) {

        this.balas = balas;


    }


    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {


        for (int i = 0; i < balas.length; i++) {
            if (balas[i] != null) {
                balas[i].dibujese(canvas, pincel);
            }
        }

    }


    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        if (balas != null)
            dibujarEscena(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();
    }
}

