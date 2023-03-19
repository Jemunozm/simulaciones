package com.curso_simulaciones.mivigesimaquintaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.mivigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;

public class Pizarra extends View {

    private ObjetoLaboratorio objetosLab[];

    private float origen_x, origen_y;
    private float m_x = 1;
    private float m_y = 1;


    /**
     * Constructor
     *
     * @param context
     */
    public Pizarra(Context context) {
        super(context);

    }


    public void setSistemaCoordenadas(float origen_x, float origen_y, float m_x, float m_y) {

        this.origen_x = origen_x;
        this.origen_y = origen_y;
        this.m_x = m_x;
        this.m_y = m_y;

    }


    public void setEstadoEscena(ObjetoLaboratorio[] cuerpos) {

        this.objetosLab = cuerpos;

    }


    //Método para dibujar la escena
    private void dibujarEscena(Canvas canvas, Paint pincel) {

        //dibujar las objetos de laboratorio
        for (int i = 0; i < objetosLab.length; i++) {
            if (objetosLab[i] != null) {
                objetosLab[i].dibujese(canvas, pincel);
            }
        }


    }


    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);

        canvas.save();
        //cambio a sistema de coordenadas tradicional
        canvas.translate(origen_x, origen_y);
        canvas.scale(m_x, m_y);

        //dibujar objetos
        if (objetosLab != null)
            dibujarEscena(canvas, pincel);

        canvas.restore();

        //dibujar letreros
        dibujarLetreros(canvas, pincel);


        //necesario para actualizar los dibujos en animaciones
        invalidate();

    }

    private void dibujarLetreros(Canvas canvas, Paint pincel) {

        pincel.setTextSize(CR.pcApxL(3f));
        pincel.setColor(Color.BLACK);

        float m1 = AlmacenDatosRAM.m1;
        float m2 = AlmacenDatosRAM.m2;
        float m3 = AlmacenDatosRAM.m3;

        canvas.drawText("m1 = " + m1 + " kg", CR.pcApxX(2), CR.pcApxY(5), pincel);
        canvas.drawText("m2 = " + m2 + " kg", CR.pcApxX(2), CR.pcApxY(10), pincel);
        canvas.drawText("m3 = " + m3 + " kg", CR.pcApxX(2), CR.pcApxY(15), pincel);

        //aceleraciones con dos decimales
        String a1 = String.format("%.2f", AlmacenDatosRAM.a1);
        String a2 = String.format("%.2f", AlmacenDatosRAM.a2);
        String a3 = String.format("%.2f", AlmacenDatosRAM.a3);
        String F = String.format("%.2f", AlmacenDatosRAM.F);
        String F1 = String.format("%.2f", AlmacenDatosRAM.F1);
        canvas.drawText("a1 = " + a1 + " m/s2", CR.pcApxX(2), CR.pcApxY(20), pincel);
        canvas.drawText("a2 = " + a2 + " m/s2", CR.pcApxX(2), CR.pcApxY(25), pincel);
        canvas.drawText("a3 = " + a3 + " m/s2", CR.pcApxX(2), CR.pcApxY(30), pincel);
        canvas.drawText("Tensión1 = " + F + " N", CR.pcApxX(2), CR.pcApxY(35), pincel);
        canvas.drawText("Tensión2 = " + F1 + " N", CR.pcApxX(2), CR.pcApxY(40), pincel);


        //posiciones con tres decimales
        String y1 = String.format("%.3f", AlmacenDatosRAM.y1_en_metros);
        String y2 = String.format("%.3f", AlmacenDatosRAM.y2_en_metros);
        String y3 = String.format("%.3f", AlmacenDatosRAM.y3_en_metros);
        canvas.drawText("y1 = " + y1 + " m", CR.pcApxX(2), CR.pcApxY(45), pincel);
        canvas.drawText("y2 = " + y2 + " m", CR.pcApxX(2), CR.pcApxY(50), pincel);
        canvas.drawText("y3 = " + y3 + " m", CR.pcApxX(2), CR.pcApxY(55), pincel);

        //desplazamientos con tres decimales
        String d1 = String.format("%.3f", AlmacenDatosRAM.desplazamiento_m1_en_metros);
        String d2 = String.format("%.3f", AlmacenDatosRAM.desplazamiento_m2_en_metros);
        String d3 = String.format("%.3f", AlmacenDatosRAM.desplazamiento_m3_en_metros);

        canvas.drawText("Desplaz. m1 = " + d1 + " m", CR.pcApxX(2), CR.pcApxY(60), pincel);
        canvas.drawText("Desplaz. m2 = " + d2 + " m", CR.pcApxX(2), CR.pcApxY(65), pincel);
        canvas.drawText("Desplaz. m3 = " + d3 + " m", CR.pcApxX(2), CR.pcApxY(70), pincel);

        //tiempo con dos decimales
        String tiempo = String.format("%.2f", AlmacenDatosRAM.tiempo);
        canvas.drawText("Tiempo = " + tiempo + " s", CR.pcApxX(2), CR.pcApxY(75), pincel);


        //autor
        String autor = "Copyright 2023 para Jesus David Muñoz Mejia - Universidad Nacional de Colombia - Sede Medellín";
        canvas.drawText(autor, CR.pcApxX(5), CR.pcApxY(99), pincel);

    }

}
