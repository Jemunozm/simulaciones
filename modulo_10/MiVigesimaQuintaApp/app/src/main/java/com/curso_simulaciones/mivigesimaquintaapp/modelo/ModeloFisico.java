package com.curso_simulaciones.mivigesimaquintaapp.modelo;

import com.curso_simulaciones.mivigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaquintaapp.vista.CR;

public class ModeloFisico {

    //gravedad
    private float g = 9.8f;//en m/s2


    private float desplazamiento_m1_en_pixeles, desplazamiento_mP3_en_pixeles, desplazamiento_m2_en_pixeles, desplazamiento_m3_en_pixeles;
    private float factorConversion_metroApixel, factorConversion_pixelAmetro;
    private float y1_en_pixeles, yP3_en_pixeles, y2_en_pixeles, y3_en_pixeles;


    public ModeloFisico() {


    }


    /*
      Dados los valores de las masas calcula posiciones,
      aceleraciones, tensión, desplazamiento
      angular de las poleas

     */
    public void setCalculos(float tiempo, float m1, float m2, float m3) {

        factorConversion();


        //cálculo de la tensión en la cuerda 1 y 2
        float F1 = ((4 * (m1 * m2 * m3)) / ((4 * (m2 * m3)) + (m1 * m3) + (m1 * m2))) * g;

        float F = ((8 * (m1 * m2 * m3)) / ((4 * (m2 * m3)) + (m1 * m3) + (m1 * m2))) * g;

        //aceleraciones de m1 y mP3 en m/s^^2
        float a1 = -(F/m1)+g;
        float a2 = -(F1/m2)+g;
        float a3 = -(F1/m3)+g;


        //desplazamiento de las masas en metros
        float desplazamiento_m1_en_metros = 0.5f * a1 * tiempo * tiempo;
        float desplazamiento_mP3_en_metros = 0.5f * -a1 * tiempo * tiempo;
        float desplazamiento_m2_en_metros = 0.5f * a2 * tiempo * tiempo;
        float desplazamiento_m3_en_metros = 0.5f * a3 * tiempo * tiempo;

        //almacenar valores para que pizarra los reporte
        AlmacenDatosRAM.desplazamiento_m1_en_metros = desplazamiento_m1_en_metros;
        AlmacenDatosRAM.desplazamiento_mP3_en_metros = desplazamiento_mP3_en_metros;
        AlmacenDatosRAM.desplazamiento_m2_en_metros = desplazamiento_m2_en_metros;
        AlmacenDatosRAM.desplazamiento_m3_en_metros = desplazamiento_m3_en_metros;

        /*
         Convertir m a pixeles
        */
        //desplazamientos de las masas en pixeles
        desplazamiento_m1_en_pixeles = factorConversion_metroApixel * desplazamiento_m1_en_metros;
        desplazamiento_mP3_en_pixeles = factorConversion_metroApixel * desplazamiento_mP3_en_metros;
        desplazamiento_m2_en_pixeles = factorConversion_metroApixel * desplazamiento_m2_en_metros;
        desplazamiento_m3_en_pixeles = factorConversion_metroApixel * desplazamiento_m3_en_metros;

        AlmacenDatosRAM.desplazamiento_m1_en_pixeles = desplazamiento_m1_en_pixeles;
        AlmacenDatosRAM.desplazamiento_mP3_en_pixeles = desplazamiento_mP3_en_pixeles;
        AlmacenDatosRAM.desplazamiento_m2_en_pixeles = desplazamiento_m2_en_pixeles;
        AlmacenDatosRAM.desplazamiento_m3_en_pixeles = desplazamiento_m3_en_pixeles;


        //posición inciales en pixeles
        float yi1_en_pixeles = AlmacenDatosRAM.yi1_en_pixeles;
        float yiP3_en_pixeles = AlmacenDatosRAM.yiP3_en_pixeles;
        float yi2_en_pixeles = AlmacenDatosRAM.yi2_en_pixeles;
        float yi3_en_pixeles = AlmacenDatosRAM.yi3_en_pixeles;


        //posiciones de las masas en pixeles
        y1_en_pixeles = yi1_en_pixeles + desplazamiento_m1_en_pixeles;
        yP3_en_pixeles = yiP3_en_pixeles + desplazamiento_mP3_en_pixeles;
        y2_en_pixeles = yi2_en_pixeles + desplazamiento_m2_en_pixeles;
        y3_en_pixeles = yi3_en_pixeles + desplazamiento_m3_en_pixeles;
        AlmacenDatosRAM.y1_en_pixeles = y1_en_pixeles;
        AlmacenDatosRAM.yP3_en_pixeles = yP3_en_pixeles;
        AlmacenDatosRAM.y2_en_pixeles = y2_en_pixeles;
        AlmacenDatosRAM.y3_en_pixeles = y3_en_pixeles;

        float teta_1 = -(float) (Math.toDegrees(desplazamiento_m1_en_pixeles / AlmacenDatosRAM.radio));
        float teta_2 = teta_1;
        float teta_3;
        if (desplazamiento_m2_en_pixeles > desplazamiento_m3_en_pixeles) {
            teta_3 = -(float) (Math.toDegrees(desplazamiento_m2_en_pixeles / AlmacenDatosRAM.radio));
        } else if (desplazamiento_m2_en_pixeles < desplazamiento_m3_en_pixeles) {
            teta_3 = (float) (Math.toDegrees(desplazamiento_m3_en_pixeles / AlmacenDatosRAM.radio));
        } else {
            teta_3 = 0;
        }


        AlmacenDatosRAM.y1_en_metros = factorConversion_pixelAmetro * y1_en_pixeles;
        AlmacenDatosRAM.yP3_en_metros = factorConversion_pixelAmetro * yP3_en_pixeles;
        AlmacenDatosRAM.y2_en_metros = factorConversion_pixelAmetro * y2_en_pixeles;
        AlmacenDatosRAM.y3_en_metros = factorConversion_pixelAmetro * y3_en_pixeles;

        //enviar resultados a AlmacenDatosRAM
        AlmacenDatosRAM.a1 = a1;
        AlmacenDatosRAM.aP3 = -a1;
        AlmacenDatosRAM.a2 = a2;
        AlmacenDatosRAM.a3 = a3;
        AlmacenDatosRAM.teta_1 = teta_1;
        AlmacenDatosRAM.teta_2 = teta_2;
        AlmacenDatosRAM.teta_3 = teta_3;


        AlmacenDatosRAM.F = F;
        AlmacenDatosRAM.F1 = F1;

        AlmacenDatosRAM.tiempo = tiempo;


    }


    private void factorConversion() {

         /*
        Para dar una equivalencia de pixeles
        en metros se asumirá que 2 m equivale
        al ALTO de la pantalla (en posición
        LANSCAPE) en pixeles. Con base en esto
        el factor de conversion de metros a pixeles
        es:

        factorConversion_metroApixel= (ALTO en pixeles/ 2 metro)
        factorConversion_pixelAmetro= (2 metro / ALTO en pixeles)

        */

        factorConversion_metroApixel = CR.pcApxY(100f) / 2;

        factorConversion_pixelAmetro = 2 / CR.pcApxY(100f);

    }


}
