package com.curso_simulaciones.mivigesimaterceraapp.datos;

public class AlmacenDatosRAM {


    public static float ancho_pantalla, alto_pantalla;
    public static int tamanoLetraResolucionIncluida;

    //masas en kg
    public static float  m1,m2;

    //radio de las poleas en pixeles
    public static float radio;

    public static float tiempo;

    //aceleraciones de m1 y m2  en m/s2
    public static float a1,a2;

    //tensión en la cuerda en N;
    public static float F=0;

    /*
     desplazamiento angular (radianes)
     de rotación de las poleas
     */
    public static float teta_1, teta_2;


    //origen
    public static float origenY_en_pixeles;
    public static float origenY_en_metros;

    //posiciones
    public static float x1_en_pixeles,x2_en_pixeles,y1_en_pixeles,y2_en_pixeles;
    public static float yi1_en_metros, yi2_en_metros, y1_en_metros, y2_en_metros;

    //posiciónes en Y iniciales
    public static float yi1_en_pixeles, yi2_en_pixeles;


    public static float desplazamiento_m1_en_metros, desplazamiento_m2_en_metros;
    public static float desplazamiento_m1_en_pixeles, desplazamiento_m2_en_pixeles;
}

