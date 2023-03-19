package com.curso_simulaciones.mivigesimaquintaapp.controlador;

import com.curso_simulaciones.mivigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaquintaapp.modelo.ModeloFisico;
import com.curso_simulaciones.mivigesimaquintaapp.vista.CR;

public class HiloAnimacion extends Thread {


    public boolean pausa = true;
    private boolean corriendo;
    private long periodo_muestreo = 100;
    public float tiempo = 0;

    private ModeloFisico modelo = new ModeloFisico();

    private ActividadControladora actividad;


    public HiloAnimacion(ActividadControladora actividad) {

        this.actividad = actividad;

    }


    @Override
    public void run() {
        corriendo = true;
        while (corriendo) {


            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (pausa == false) {
                tiempo = tiempo + 0.01f;
                setCalculos(tiempo);

                actualizarFisica();

            }


        }


    }

    private void setCalculos(float tiempo) {
        float m1 = AlmacenDatosRAM.m1;
        float m2 = AlmacenDatosRAM.m2;
        float m3 = AlmacenDatosRAM.m3;
        modelo.setCalculos(tiempo, m1, m2, m3);

    }


    private void actualizarFisica() {

        actividad.cambiarEstadosEscenaPizarra();

        //en caso de que se termine el recorrido de alguna de las masas
        //en la regla se repetirá la animación
        boolean a = AlmacenDatosRAM.y1_en_pixeles > (CR.pcApxY(80) - CR.pcApxL(12));
        boolean b = AlmacenDatosRAM.y1_en_pixeles < (CR.pcApxY(41) - CR.pcApxL(28));
        boolean c = AlmacenDatosRAM.yP3_en_pixeles < (CR.pcApxY(41) - CR.pcApxL(28));
        boolean d = AlmacenDatosRAM.yP3_en_pixeles > (CR.pcApxY(80) - CR.pcApxL(12));
        boolean e = AlmacenDatosRAM.y2_en_pixeles > (CR.pcApxY(80) - CR.pcApxL(12));
        boolean f = AlmacenDatosRAM.y2_en_pixeles < AlmacenDatosRAM.yP3_en_pixeles + CR.pcApxL(11);
        boolean g = AlmacenDatosRAM.y3_en_pixeles < AlmacenDatosRAM.yP3_en_pixeles + CR.pcApxL(11);
        boolean h = AlmacenDatosRAM.y3_en_pixeles > (CR.pcApxY(80) - CR.pcApxL(12));

        if (a || b || c || d || e || h || f || g) {

            tiempo = 0.0f;

        }

    }


}
