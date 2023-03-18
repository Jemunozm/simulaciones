package com.curso_simulaciones.mivigesimacuartaapp.datos;

public class AlmacenDatosRAM {
    public static float ancho_pantalla, alto_pantalla;
    public static int tamanoLetraResolucionIncluida;

    public static float vInicial =20;//en m/s
    public static float velocidad, velocidadX,velocidadY;//en m/s
    public static float angulo=45;//en grados
    public static float anguloVelocidad;//en radianes
    public static float aceleracion=10;//en m/s^2
    public static float tiempo;//en s

    public static boolean NUEVO=false;


    public static float origenX_en_pixeles,origenY_en_pixeles;

    public static float origenX_en_metros, origenY_en_metros;

    public static float xi_en_pixeles, yi_en_pixeles,x_en_pixeles,y_en_pixeles;
    public static float xi_en_metros, yi_en_metros, x_en_metros, y_en_metros;

    public static float desplazamiento_en_metros_X, desplazamiento_en_metros_Y;

}

