package com.curso_simulaciones.simulphysics.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Masa extends CuerpoRectangular {


    private String marca;

    private int colorMarca;

    public Masa(float posicionCentroMasaX, float posicionCentroMasaY, float largo, float alto){

        super(posicionCentroMasaX, posicionCentroMasaY,largo,alto);

    }


    public void setMarca(String marca){

        this.marca=marca;


    }

    public void setColorMarca(int colorMarca){
        this.colorMarca=colorMarca;

    }

    //se implementó este método dque en su clase madre es abstracto
    public void dibujese(Canvas canvas, Paint pincel) {

        super.dibujese(canvas, pincel);


        canvas.save();

        canvas.rotate(posicionAngularRotacionEjeXY,posicionEjeRotacionX,posicionEjeRotacionY);

        pincel.setStyle(Paint.Style.FILL);

        if(marca!=null) {

            pincel.setStyle(Paint.Style.FILL);
            float tamano=(float)(0.5*Math.min(largo,alto));
            pincel.setTextSize(tamano);
            float ancho_cadena=pincel.measureText(marca);

            Paint.FontMetrics fm = pincel.getFontMetrics();
            float altura = fm.bottom - fm.top + fm.leading;//fm.top - fm.ascent;

            Rect bounds = new Rect((int)(posicionCentroMasaX-0.5f*largo), (int)(posicionCentroMasaY-0.5f*alto),(int)(posicionCentroMasaX+0.5f*largo), (int)(posicionCentroMasaY+0.5f*alto));
            pincel.getTextBounds(marca, 0, marca.length(), bounds);

            pincel.setColor(colorMarca);
            canvas.drawText(marca,posicionCentroMasaX-0.5f*ancho_cadena, posicionCentroMasaY+0.25f*altura , pincel);
        }

        //regresar la rotación
        canvas.restore();
        //restaurar el pincel
        pincel.reset();




    }

}
