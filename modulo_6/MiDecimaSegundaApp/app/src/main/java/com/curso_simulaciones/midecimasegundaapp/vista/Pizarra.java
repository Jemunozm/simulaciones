package com.curso_simulaciones.midecimasegundaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.midecimasegundaapp.objetos_laboratorio.CuerpoRigido;

public class Pizarra extends View {

    private CuerpoRigido cuerpos[];

    /**
     * Constructor
     *
     * @param context
     */
    public Pizarra(Context context) {
        super(context);

    }


    public void setEstadoEscena(CuerpoRigido[] cuerpos) {

        this.cuerpos = cuerpos;

    }



    //Método para dibujar la escena
    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {

        //dibujar las poleas
        for (int i = 0; i < cuerpos.length; i++) {
            if (cuerpos[i] != null) {
                cuerpos[i].dibujese(canvas, pincel);
            }
        }


    }



    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        if (cuerpos != null)
            dibujarEscena(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();

    }


}
