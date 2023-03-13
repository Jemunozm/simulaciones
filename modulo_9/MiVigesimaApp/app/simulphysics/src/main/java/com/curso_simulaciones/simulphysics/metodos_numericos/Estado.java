package com.curso_simulaciones.simulphysics.metodos_numericos;

public class Estado {

    /**
     * Variable indpenediente (tiempo)
     */
    public double t;

    /**
     * Posición
     */
    public double x;

    /**
     * Velocidad
     */
    public double v;


    /**
     * Estado
     * @param t: tiempo
     * @param x: posición
     * @param v: velocidad
     */
    public Estado(double t, double x, double v) {
        this.t=t;
        this.x=x;
        this.v=v;
    }

}
