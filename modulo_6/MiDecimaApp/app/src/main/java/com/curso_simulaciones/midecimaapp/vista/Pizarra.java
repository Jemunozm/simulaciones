package com.curso_simulaciones.midecimaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.midecimaapp.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.midecimaapp.objetos_laboratorio.Polea;
import com.curso_simulaciones.midecimaapp.objetos_laboratorio.Rueda;

public class Pizarra extends View {
    private Polea poleas[];
    private Rueda ruedas[];
    private CuerpoRectangular cuerposRect[];


    /**
     * Constructor
     *
     * @param context
     */
    public Pizarra(Context context) {
        super(context);

    }


    public void setEstadoEscena(Polea[] poleas, Rueda ruedas[], CuerpoRectangular cuerposRect[]) {

        this.poleas = poleas;
        this.ruedas = ruedas;
        this.cuerposRect = cuerposRect;


    }


    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {

        //dibujar las poleas
        for (int i = 0; i < poleas.length; i++) {
            if (poleas[i] != null) {
                poleas[i].dibujese(canvas, pincel);
            }
        }


        //dibujar las ruedas
        for (int i = 0; i < ruedas.length; i++) {
            if (ruedas[i] != null && ruedas.length > 0) {
                ruedas[i].dibujese(canvas, pincel);
            }
        }


        //dibujar los cuerpos rectangularespoleas
        for (int i = 0; i < cuerposRect.length; i++) {
            if (cuerposRect[i] != null && cuerposRect.length > 0) {
                cuerposRect[i].dibujese(canvas, pincel);
            }

        }


    }


    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        if (poleas != null)
            dibujarEscena(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();

    }

}
