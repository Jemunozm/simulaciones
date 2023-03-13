package com.curso_simulaciones.simulphysics.metodos_numericos;

public abstract class RungeKuttaOrdenDos {


    /**
     * Constructor
     */
    public RungeKuttaOrdenDos(){

    }


    /**
     * Resuelve la ecuación diferencial por el método de Runge-Kutta
     * @param tf: resolver hasta tf
     * @param e: estado inicial (posición y velocidad en to
     * @param h: paso para resolver la ecuación diferencial
     */

    public void resolver(double tf, Estado e, double h){

        //variables auxiliares
        double k1, k2, k3, k4;
        double l1, l2, l3, l4;
        //estado inicial
        double x=e.x;
        double v=e.v;
        double t0=e.t;

        for(double t=t0; t<tf; t+=h){
            k1=h*v;
            l1=h*f(x, v, t);
            k2=h*(v+l1/2);
            l2=h*f(x+k1/2, v+l1/2, t+h/2);
            k3=h*(v+l2/2);
            l3=h*f(x+k2/2, v+l2/2, t+h/2);
            k4=h*(v+l3);
            l4=h*f(x+k3, v+l3, t+h);
            x+=(k1+2*k2+2*k3+k4)/6;
            v+=(l1+2*l2+2*l3+l4)/6;
        }

        //estado final
        e.t=tf;
        e.x=x;
        e.v=v;
    }


    /**
     * La ecuación dieferencial tiene la forma d2x/dt2=f(x,v,t)
     * @param x: posición
     * @param v: velocidad
     * @param t: tiempo
     * @return
     */

    abstract public double f(double x, double v, double t);

}
