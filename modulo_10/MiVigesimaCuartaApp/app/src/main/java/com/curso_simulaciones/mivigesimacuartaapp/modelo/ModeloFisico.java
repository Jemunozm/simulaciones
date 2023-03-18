package com.curso_simulaciones.mivigesimacuartaapp.modelo;

import com.curso_simulaciones.mivigesimacuartaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimacuartaapp.vista.CR;

public class ModeloFisico {

    private float factorConversion_metroApixel, factorConversion_pixelAmetro;

    private float x_en_pixeles, y_en_pixeles;

    private float desplazamiento_en_metros_X, desplazamiento_en_metros_Y;

    private float desplazamiento_en_pixeles_X, desplazamiento_en_pixeles_Y;


    public ModeloFisico() {


    }


    /*
      Calcula y lleva los datos de elongación y tiempo
      a la clase AlmaceDatosRAM
   */
    public void setEstadoSistema(float tiempo) {

        factorConversion();

        float angulo = AlmacenDatosRAM.angulo;

        //velocidades iniciales en m/s y aceleración en m/s^2
        float vXInicial = (float) (AlmacenDatosRAM.vInicial * Math.cos(Math.toRadians(angulo)));
        float vYInicial = (float) (AlmacenDatosRAM.vInicial * Math.sin(Math.toRadians(angulo)));
        float aceleracion = AlmacenDatosRAM.aceleracion;

        //desplazamientos en metros
        desplazamiento_en_metros_X = vXInicial * tiempo;
        desplazamiento_en_metros_Y = vYInicial * tiempo + 0.5f * aceleracion * tiempo * tiempo;

        //almacenar valor para que pizarra lo reporte
        AlmacenDatosRAM.desplazamiento_en_metros_X = desplazamiento_en_metros_X;
        AlmacenDatosRAM.desplazamiento_en_metros_Y = desplazamiento_en_metros_Y;


        /*
         Convertir m a pixeles
        */
        //desplazamientos en pixeles
        desplazamiento_en_pixeles_X = factorConversion_metroApixel * desplazamiento_en_metros_X;
        desplazamiento_en_pixeles_Y = factorConversion_metroApixel * desplazamiento_en_metros_Y;

        //posición incial en pixeles
        float xi_en_pixeles = AlmacenDatosRAM.xi_en_pixeles;
        float yi_en_pixeles = AlmacenDatosRAM.yi_en_pixeles;

        //posición en pixeles (con esto se grafica en pantalla)
        x_en_pixeles = xi_en_pixeles + desplazamiento_en_pixeles_X;
        y_en_pixeles = yi_en_pixeles + desplazamiento_en_pixeles_Y;

        //velocidades en m/s
        float velocidadX = vXInicial;
        float velocidadY = vYInicial + aceleracion * tiempo;

        AlmacenDatosRAM.x_en_metros = factorConversion_pixelAmetro * x_en_pixeles;
        AlmacenDatosRAM.y_en_metros = factorConversion_pixelAmetro * y_en_pixeles;
        AlmacenDatosRAM.velocidadX = velocidadX;//en m/s
        AlmacenDatosRAM.velocidadY = velocidadY;//en m/s
        AlmacenDatosRAM.velocidad = (float) (Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY));
        //Devuelve el ángulo (ccordenadas polares) en radianes y
        AlmacenDatosRAM.anguloVelocidad = (float) (Math.atan2(velocidadY, velocidadX));//radianes
        AlmacenDatosRAM.tiempo = tiempo;

        AlmacenDatosRAM.x_en_pixeles = x_en_pixeles;
        AlmacenDatosRAM.y_en_pixeles = y_en_pixeles;

    }


    private void factorConversion() {

       /*
        Para dar una equivalencia de pixeles
        en metros se aumirá que 50 m equivale
        al ALTO de la pantalla (en posición
        LANSCAPE) en pixeles. Con base en esto
        el factor de conversion de metos  apixeles
        es:

        factorConversion_metroApixel= (ALTO en pixeles/ 50 metros)
        factorConversion_pixelAmetro= (ALTO metros / ALTO en pixeles)

        */

        factorConversion_metroApixel = CR.pcApxY(100f) / 50f;

        factorConversion_pixelAmetro = 50f / CR.pcApxY(100f);

    }

}
