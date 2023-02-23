package com.curso_simulaciones.misextaapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reloj extends View {
    private float largo;
    private int colorLineasDivisionesPequenas = Color.BLACK;
    private int colorLineasDivisionesGrandes = Color.BLACK;
    private int colorMarco = Color.BLACK;
    private int colorFondo = Color.WHITE;
    private int colorNumeros = Color.BLACK;
    private int colorAgujaSegundero = Color.BLACK;
    private int colorAgujaMinutero = Color.BLACK;
    private int colorAgujaHorario = Color.BLACK;
    private int colorMarcaEmpresa = Color.BLACK;
    private int colorHoraDigital = Color.BLACK;

    private Calendar calendario;

    private SimpleDateFormat hora;

    private String horaFormatoDigital = "";

    private int segundos, minutos, horas;


    /**
     * Constructor de la clase Reloj
     */
    public Reloj(Context context) {

        super(context);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }
    }


    //Calcula la hora

    private void obtenerHora() {
        /** Calendar es una clase abstracta, por lo que solo se puede
         * acceder a ella usando el metodo getInstance()
         * NO PUEDES CREAR OBETJOS CON ELLA
         */

        calendario = Calendar.getInstance();

        segundos = calendario.get(Calendar.SECOND);
        minutos = calendario.get(Calendar.MINUTE);
        horas = calendario.get(Calendar.HOUR);
    }

    public void setColorAgujaSegundero(int colorAgujaSegundero) {

        this.colorAgujaSegundero = colorAgujaSegundero;

    }

    public void setColorAgujaMinutero(int colorAgujaMinutero) {

        this.colorAgujaMinutero = colorAgujaMinutero;

    }

    public void setColorAgujaHorario(int colorAgujaHorario) {

        this.colorAgujaHorario = colorAgujaHorario;

    }

    public void setColorMarco(int colorMarco) {

        this.colorMarco = colorMarco;

    }

    public void setColorFondo(int colorFondo) {

        this.colorFondo = colorFondo;

    }

    public void setColorNumeros(int colorNumeros) {

        this.colorNumeros = colorNumeros;

    }

    public void setColorLineasDivisonesPequenas(int colorLineasDivisonesPequenas) {

        this.colorLineasDivisionesPequenas = colorLineasDivisonesPequenas;

    }

    public void setColorLineasDivisonesGrandes(int colorLineasDivisonesGrandes) {

        this.colorLineasDivisionesGrandes = colorLineasDivisonesGrandes;

    }

    public void setColorMarcaEmpresa(int colorMarcaEmpresa) {

        this.colorMarcaEmpresa = colorMarcaEmpresa;

    }


    public void setColorHoraDigital(int colorHoraDigital) {

        this.colorHoraDigital = colorHoraDigital;


    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //configurando el pincel
        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);
        //tamaño texto
        pincel.setTextSize(0.05f * largo);
        //para mejor manejo de la métrica de texto
        pincel.setLinearText(true);
        //para efectos de buen escalado de bitmaps
        pincel.setFilterBitmap(true);
        //para buen manejo de gradientes de color
        pincel.setDither(true);

        canvas.save();

        /**
         * La vista tendrá las mismas dimensiones
         * de su contenedor
         */
        float ancho = this.getWidth();
        float alto = this.getHeight();

        /**
         * Se define la variable largo como el 80%
         * del menor valor entre alto y largo del
         * contenedor
         */
        if (ancho > alto) {

            largo = 0.8f * alto;

        } else {

            largo = 0.8f * ancho;

        }

        canvas.translate(0.5f * ancho, 0.5f * alto);


        // dibujar circulo
        float radio = 0.5f * largo;
        pincel.setColor(colorFondo);
        canvas.drawCircle(0,0,radio,pincel);
        pincel.setColor(colorMarco);
        pincel.setStyle(Paint.Style.STROKE);
        // perimetro del circulo
        pincel.setStrokeWidth(0.02f*largo);
        canvas.drawCircle(0,0,radio,pincel);

        //divisiones del reloj

        float indent = 0.05f*largo;
        float posicionY = 0.5f*largo;

        for (int i=0; i<60; i=i+1) {
            int anguloRotacion = 6 * i;

            canvas.save();
            canvas.rotate(anguloRotacion, 0, 0);
            if (anguloRotacion % 30 == 0) {
                //marcas más largas cada 30 grados
                pincel.setStrokeWidth(0.02f * largo);//grueso de marcas largas
                pincel.setColor(colorLineasDivisionesGrandes);
                canvas.drawLine(0, -posicionY, 0, -posicionY + 1.5f * indent, pincel);

            } else {
                //marcas pequñeas cada 10 grados
                pincel.setStrokeWidth(0.005f * largo);//grueso de marcas pequeñas
                pincel.setColor(colorLineasDivisionesPequenas);
                canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);
            }

            canvas.restore();

        }
            pincel.setStyle(Paint.Style.FILL);

            for (int i=1; i<13; i=i+1) {
                //dibujar los números
                int anguloRotacion = 30 * i;
                canvas.save();
                //rota los números los números
                canvas.rotate(anguloRotacion, 0, 0);
                //endereza los números
                canvas.rotate(-anguloRotacion, 0, -posicionY + 2.5f * indent);
                //ancho de la cadena del número
                float anchoCadenaNumero = pincel.measureText("" + i);
                pincel.setColor(colorNumeros);
                canvas.drawText("" + i, -0.5f * anchoCadenaNumero, -posicionY + 2.5f * indent, pincel);
                canvas.restore();
                //fin dibujar números
            }

        obtenerHora();
        //dibuja segundero (hay 60 divisiones)
        int anguloRotacionSegundero = segundos*360/60;;
        //grosor línea
        pincel.setStrokeWidth(0.005f*largo);
        pincel.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(anguloRotacionSegundero, 0,0);
        pincel.setColor(colorAgujaSegundero);
        canvas.drawLine(0, -0.7f*radio, 0, 2*indent, pincel);
        canvas.restore();

        //dibuja minutero (hay 60 divisiones)
        int anguloRotacionMinutero = (int)((minutos + (float)(segundos/60))*360/60);
        //grosor línea
        pincel.setStrokeWidth(0.015f*largo);
        pincel.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(anguloRotacionMinutero, 0,0);
        pincel.setColor(colorAgujaMinutero);
        canvas.drawLine(0, -0.6f*radio, 0, 2*indent, pincel);
        canvas.restore();

        //dibuja horario (hay 60 divisiones)
        int anguloRotacionHorario = (int)((horas + (float)minutos/60)*360/12);
        //grosor línea
        pincel.setStrokeWidth(0.025f*largo);
        pincel.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(anguloRotacionHorario, 0,0);
        pincel.setColor(colorAgujaHorario);
        canvas.drawLine(0, -0.5f*radio, 0, 2*indent, pincel);
        canvas.restore();



        //dibuja punto central
        pincel.setColor(Color.RED);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 0.05f*radio,pincel);

        //marcar empresa
        String empresa = "IoT.PhysicsSensor";
        pincel.setTextSize(0.05f * largo);
        float anchoCadenaNombreEmpresa = pincel.measureText(empresa);
        pincel.setColor(colorMarcaEmpresa);
        canvas.drawText(empresa, -0.5f * anchoCadenaNombreEmpresa, -0.20f * largo, pincel);

        //dibuja despliegue forma digital de la hora
        horaFormatoDigital = ""+horas + ":" + minutos + ":" + segundos;
        pincel.setTextSize(0.08f * largo);
        float anchoCadenaHoraDigital = pincel.measureText(horaFormatoDigital);
        pincel.setColor(colorHoraDigital);
        canvas.drawText(horaFormatoDigital, -0.5f * anchoCadenaHoraDigital, 0.20f * largo, pincel);


        //se restaura el canvas al estado incial
        //el que se grabó al principio de este método
        canvas.restore();

        //para efectos de animación
        invalidate();



        }


}
