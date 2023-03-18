package com.curso_simulaciones.mivigesimacuartaapp.vista;

public class CR {
    public static float anchoPizarra;
    public static float altoPizarra;


    public CR(){

    }


    /**
     * Método para convertir porcentaje de
     * posición en X a pixeles.
     * @param pcX
     * @return
     */
    public static float pcApxX(float pcX) {

        float pxX = pcX * anchoPizarra / 100f;

        return pxX;

    }




    /**
     * Método para convertir porcentaje de
     * posición en Y a pixeles.
     * @param pcY
     * @return
     */
    public static float pcApxY(float pcY) {

        float pxY = pcY * altoPizarra / 100f;

        return pxY;

    }


    /**
     * Dada una longitud pcL en porcentaje
     * referido al menor entre el ancho
     * y el alto de Pizarra la convierte a una
     * longitud en pixeles.
     * @param pcL
     * @return
     */
    public static float pcApxL(float pcL) {

        float pxL = 0;

        if (anchoPizarra > altoPizarra) {

            pxL = pcL * altoPizarra / 100f;


        } else {

            pxL = pcL * anchoPizarra / 100f;
        }


        return pxL;

    }



    /**
     * Convierte pixeles de uan posicón en
     * X a porcentaje.
     * @param pxX
     * @return
     */
    public static float  pxXApc(float pxX) {

        float pcX = pxX * 100f / anchoPizarra;

        return pcX;

    }



    /**
     * Convierte pixeles de una posición en
     * Y a porcentaje.
     * @param pxY
     * @return
     */
    public static float  pxYApc(float pxY) {

        float pcY = pxY * 100f / altoPizarra;

        return pcY;

    }


    /*Convierte pixeles referidas
      al menor entre el ancho y el
      alto en porcentaje
  */

    /**
     * Dada una longitud pxL en pixeles la
     * convierte a porcentaje referido al menor entre
     * el ancho y el alto.
     * Pizarra.
     * @param pxL
     * @return
     */
    public static float pxApcL(float pxL) {

        float pcL = 0;

        if (CR.anchoPizarra > altoPizarra) {

            pcL = pxL * 100f/ altoPizarra ;


        } else {

            pcL = pxL * 100f/ anchoPizarra;
        }


        return pcL;

    }

}
