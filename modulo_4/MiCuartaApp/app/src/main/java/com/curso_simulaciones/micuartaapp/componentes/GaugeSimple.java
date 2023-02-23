package com.curso_simulaciones.micuartaapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

public class GaugeSimple extends View {

    private float largo;
    private float minimo = 0;
    private float maximo = 100f;
    private float medida = 40.0f; //medida inicial
    private String unidades = "UNIDADES";
    private int colorPrimerTercio = Color.rgb(200, 200, 0);
    private int colorSegundoTercio = Color.rgb(0, 180, 0);
    private int colorTercerTercio = Color.RED;
    private int colorLineas = Color.BLACK;
    private int colorFondo = Color.WHITE;
    private int colorNumerosDespliegue = Color.BLACK;
    private int colorFranjaDinamica = Color.rgb(0, 0, 255);
    private int angPrimerTercio = 100;
    private int angSegundoTercio = 100;
    private int angTercerTercio = 50;


    public GaugeSimple(Context context) {
        super(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    /**
     * Modifica el rango de medicion
     * desde minimo hasta maximo
     *
     * @param minimo
     * @param maximo
     */
    public void setRango(float minimo, float maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
    }

    public void setMedida(float medida) {
        this.medida = medida;
    }

    public float getMedida() {
        return medida;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    /**
     * Modifica los colores de los sectores circulares
     *
     * @param colorPrimerTercio
     * @param colorSegundoTercio
     * @param colorTercerTercio
     */

    public void setColorSectores(int colorPrimerTercio, int colorSegundoTercio, int colorTercerTercio) {
        this.colorPrimerTercio = colorPrimerTercio;
        this.colorSegundoTercio = colorSegundoTercio;
        this.colorTercerTercio = colorTercerTercio;
    }

    /**
     * Modifica los angulos de los sectores circulares
     * Deben sumar 250°
     *
     * @param angPrimerTercio
     * @param angSegundoTercio
     * @param angTercerTercio
     */

    public void setAnguloSectores(int angPrimerTercio, int angSegundoTercio, int angTercerTercio) {
        this.angPrimerTercio = angPrimerTercio;
        this.angSegundoTercio = angSegundoTercio;
        this.angTercerTercio = angTercerTercio;
    }

    public void setColorFranjaDinamica(int colorFranjaDinamica) {
        this.colorFranjaDinamica = colorFranjaDinamica;
    }

    /**
     * Modifica el color de fondo tacometro
     *
     * @param colorFondo
     */

    public void setColorFondoTacometro(int colorFondo) {
        this.colorFondo = colorFondo;
    }

    /**
     * Modifica el color de las lineas del tacometro
     *
     * @param color_lineas
     */
    public void setColorLineasTacometro(int color_lineas) {
        this.colorLineas = color_lineas;
    }


    /**
     * Modifica el color del numero que se despliega
     *
     * @param colorNumerosDespliegue
     */

    public void setColorNumeroDespliegue(int colorNumerosDespliegue) {
        this.colorNumerosDespliegue = colorNumerosDespliegue;
    }

    /**
     * @param canvas
     */

    //metodo para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * se graba el estado actual del canvas
         * para al final restaurarlo
         */
        canvas.save();

        /*
        La vista tendra las mismas dimensiones de su
        contenedor
         */

        float ancho = this.getWidth();
        float alto = this.getHeight();

         /*
         Se define la variable largo como el 80%
         del menor valor entre alto y largo del
         contenedor
         */
        if (ancho > alto) {
            largo = 0.8f * alto;
        } else {
            largo = 0.8f * ancho;
        }
        /*
          se hace tralación del (0,0) al centro
          del contenedor
        */
        canvas.translate(0.5f * ancho, 0.5f * alto);

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

        float esquinaSuperiorIzquierdaX = -0.5f * largo;
        float esquinaSuperiorIzquierdaY = -0.5f * largo;
        float esquinaInferiorDerechaX = 0.5f * largo;
        float esquinaInferiorDerechaY = 0.5f * largo;

        //dibujar los tres segmentos circulares
        RectF rect = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);
        pincel.setColor(colorPrimerTercio);
        canvas.drawArc(rect, 145, angPrimerTercio, true, pincel);
        pincel.setColor(colorSegundoTercio);
        canvas.drawArc(rect, 145 + angPrimerTercio, angSegundoTercio, true, pincel);
        pincel.setColor(colorTercerTercio);
        canvas.drawArc(rect, 145 + angPrimerTercio + angSegundoTercio, angTercerTercio, true, pincel);

        float indent = (float) (0.05 * largo);
        float posicionY = (float) (0.5 * largo);

        //franja dinamica
        //calcular angulo para ubicar la aguja de acuerdo al valor medido
        float angulo_rotacion_medida = 235 + (250f / (maximo - minimo)) * (medida - minimo);
        float a = (float) 0.01 * largo;
        rect = new RectF(esquinaSuperiorIzquierdaX + a, esquinaSuperiorIzquierdaY + a,
                esquinaInferiorDerechaX - a, esquinaInferiorDerechaY - a);
        pincel.setColor(colorFranjaDinamica);
        canvas.drawArc(rect, 145, angulo_rotacion_medida - 235, true, pincel);

        /*
         dibujar el tacometro sin la aguja
        */
        //aqui empieza el dibujo
        float radio = (float) (0.48 * largo);
        pincel.setColor(colorFondo);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(colorLineas);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setStrokeWidth(1f);
        canvas.drawCircle(0, 0, 0.5f * largo, pincel);

        //grosor de las líneas
        pincel.setStrokeWidth(0.01f * largo);

        /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
          */

        for (int i = 0; i < 6; i += 1) {
            float anguloRotacion = 235 + 50 * i;
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

            // dibujar los numeros

            int valorIncrementoMarcas = (int) ((maximo - minimo) / 5f);
            int valorMarca = (int) (minimo + valorIncrementoMarcas * i);
            String numero = "" + valorMarca;

            //ancho de la cadena del numero
            float anchoCadenaNumero = pincel.measureText(numero);
            //alto de la cadena del numero
            Rect frontera = new Rect();
            pincel.getTextBounds(numero, 0, numero.length(), frontera);
            int altoCadenaNumero = frontera.height();

            /**
             * calculo de cordenadas de la posicion (p_x,p_y)
             * del numero segun las marcas de la divisiones grandes
             */
            double angulo = Math.toRadians(anguloRotacion);
            float radio_posicion_numero = 0.5f * largo - 1.4f * indent;
            int p_x = (int) (radio_posicion_numero * Math.sin(angulo));
            int p_y = (int) (radio_posicion_numero * Math.cos(angulo) - 0.5f * altoCadenaNumero);
            float d_x = 0;

            if (p_x < 0 || p_x == 0) {
                d_x = -0.2f * anchoCadenaNumero;
            } else {
                d_x = -0.8f * anchoCadenaNumero;
            }


            // dibujar la letra rellenita
            pincel.setStyle(Paint.Style.FILL);

            //dibujar los numeros de las marcas

            canvas.translate(p_x, -p_y);
            canvas.drawText(numero, d_x, 0, pincel);
            canvas.translate(-p_x, p_y);

        }

        /*
         Divisiones pequeñas
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 10 grados comenzando
         en 235 grados.
         */
        pincel.setStyle(Paint.Style.STROKE);
        //grosor de las líneas
        pincel.setStrokeWidth(0.005f * largo);
        for (int i = 0; i < 26; i = i + 1) {

            float anguloRotacion = 235 + 10 * i;
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + (float) (0.6 * indent), pincel);
            canvas.rotate(-anguloRotacion, 0, 0);
        }
        //Aqui finaliza el dibujo del tacometro sin la aguja

        //Ahora dibujaremos la aguja

        pincel.setStrokeWidth(0.005f * largo);
        pincel.setColor(Color.RED);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        float b = (float) (1.5f * indent);
        canvas.drawLine(0, -posicionY, 0, b, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorFondo);
        canvas.drawCircle(0, 0, (float) (0.4 * indent), pincel);
        pincel.setColor(Color.RED);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, (float) (0.4 * indent), pincel);

        // termina dibujo de la aguja

        //Dibujar las unidades

        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorLineas);
        pincel.setTextSize(0.08f * largo);
        float anchoCadenaUnidades = pincel.measureText(unidades);
        canvas.drawText(unidades, -0.5f * anchoCadenaUnidades, -0.15f * largo, pincel);
        //aqui termina dibujo de las unidades

        //aqui despliegue de la medida
        pincel.setTextSize(0.1f * largo);
        float anchoCadenaNumero = pincel.measureText("" + medida);
        pincel.setColor(colorNumerosDespliegue);
        canvas.drawText("" + medida, -0.5f * anchoCadenaNumero, 0.2f * largo, pincel);

        //marcar empresa
        String empresa = "IoT.PhysicsSensor";
        pincel.setTextSize(0.05f * largo);
        float anchoCadenaNombreEmpresa = pincel.measureText(empresa);
        canvas.drawText(empresa, -0.5f * anchoCadenaNombreEmpresa, 0.35f * largo, pincel);

        //se restaura el canvas al estado incial
        //el que se grabó al principio de este método
        canvas.restore();

        //para efectos de animación
        invalidate();

    } // finaliza el onDraw
}
