package com.curso_simulaciones.mivigesimaterceraapp.controlador;

import com.curso_simulaciones.mivigesimaterceraapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaterceraapp.modelo.ModeloFisico;
import com.curso_simulaciones.mivigesimaterceraapp.vista.CR;

public class HiloAnimacion extends Thread {


    public boolean pausa =true;
    private boolean corriendo;
    private long periodo_muestreo = 100;
    public float tiempo = 0;

    private ModeloFisico modelo = new ModeloFisico();

    private ActividadControladora actividad;


    public HiloAnimacion(ActividadControladora actividad) {

        this.actividad =actividad;

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


            if(pausa==false) {
                tiempo = tiempo + 0.01f;
                setCalculos(tiempo);

                actualizarFisica();

            }


        }


    }

    private void setCalculos(float tiempo){
        float m1 = AlmacenDatosRAM.m1;
        float m2 = AlmacenDatosRAM.m2;
        modelo.setCalculos(tiempo, m1,m2 );

    }


    private void actualizarFisica(){

        actividad.cambiarEstadosEscenaPizarra();

        //en caso de que se termine el recorrido de alguna de las masas
        //en la regla se repetirá la animación
        boolean a = AlmacenDatosRAM.y1_en_pixeles> CR.pcApxY(80.0f);
        boolean b = AlmacenDatosRAM.y1_en_pixeles < CR.pcApxY(10f);
        boolean c = AlmacenDatosRAM.y2_en_pixeles < CR.pcApxY(10f);
        boolean d = AlmacenDatosRAM.y2_en_pixeles > CR.pcApxY(80.0f);

        if (a || b || c || d) {

            tiempo = 0.0f;

        }

    }


}
