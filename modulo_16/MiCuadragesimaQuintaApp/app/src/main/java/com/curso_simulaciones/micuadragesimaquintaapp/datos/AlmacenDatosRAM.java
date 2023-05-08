package com.curso_simulaciones.micuadragesimaquintaapp.datos;

import java.util.ArrayList;

public class AlmacenDatosRAM {


    public static int ancho, alto, dimensionReferencia, tamanoLetraResolucionIncluida;

    public static int nDatos = 50;

    public static int nDatosGraficar= 20;

    public static int estado_conexion_nube=1;

    public static String MQTTHOST;
    public static String USERNAME;
    public static String PASSWORD;
    public static String topicStr;

    public static String conectado_PubSub="Hacer clic en CONECTAR para acceder al BROKER...";

    public static boolean conectado=false;

    //public static int periodo_muestreo= 1000; // 1 segundo

    public static String unidades = "unidades";

    public static String tiempo;

    public static double[] x = new double[1000];

    public static double[] y = new double[1000];

     /*
      ArrayList es una clase que permite almacenar
      objetos con la diferencia respecto a los
      arreglos [], que ellla misma va cambiando
      dinámicamente su tamaño a medida que se le
      agregan elementos
     */

    public static ArrayList datos = new ArrayList<>();
}
