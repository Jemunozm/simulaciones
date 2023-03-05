package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.MainThread;

public class EstrellaFija extends ObjetoEspacial{
    private float longitud;
    private float posicionX, posicionY;
    private int color = Color.RED;


    /**
     * Contructor por defecto de la estrella
     * con origen en (0,0) y longitud 20f
     */

    public EstrellaFija( float posicionX, float posicionY, float longitud) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.longitud = longitud;

    }

    public void setColor(int color) {
        this.color = color;
    }

    public void dibujese(Canvas canvas, Paint pincel) {
        canvas.save();

        float rot = (float) (Math.PI/2 * 3);

        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color);
        pincel.setStrokeWidth(2);

        Path path = new Path();

        path.moveTo(posicionX,posicionY);
        for (int i=0; i<6; i+=1){
            float x = (float) (posicionX + Math.cos(rot)*longitud);
            float y = (float) (posicionY + Math.sin(rot)*longitud);
            path.lineTo(x,y);
            rot += Math.PI/5;
            float x1 = (float) (posicionX + Math.cos(rot)*(.5f*longitud));
            float y1 = (float) (posicionY + Math.sin(rot)*(.5f*longitud));
            path.lineTo(x1,y1);
            rot += Math.PI/5;
        }
        path.close();
        canvas.drawPath(path,pincel);


        //restaurar el canvas
        canvas.restore();

    }
}
