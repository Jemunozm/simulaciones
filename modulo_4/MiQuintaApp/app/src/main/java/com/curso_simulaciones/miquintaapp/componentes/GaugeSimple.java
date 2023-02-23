package com.curso_simulaciones.miquintaapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;

public class GaugeSimple extends View {

    private float largo;
    private float minimo = 0;
    private float maximo = 100f;
    private float medida = 40.0f; //medida inicial
    private String unidades = "UNIDADES";
    private int colorPrimerTercio = Color.GREEN;
    private int colorSegundoTercio = Color.YELLOW;
    private int colorTercerTercio = Color.BLUE;

    private int colorAguja = Color.RED;
    private int colorFondo = Color.BLACK;
    private int colorNumerosDespliegue = Color.WHITE;
    private int angPrimerTercio = 100;
    private int angSegundoTercio = 100;
    private int angTercerTercio = 70;
    private int colorUnidades = Color.WHITE;


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
    public void setColorUnidades(int colorUnidades){
        this.colorUnidades = colorUnidades;
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

    public void setColorEscala(int colorPrimerTercio, int colorSegundoTercio, int colorTercerTercio) {
        this.colorPrimerTercio = colorPrimerTercio;
        this.colorSegundoTercio = colorSegundoTercio;
        this.colorTercerTercio = colorTercerTercio;
    }

    /**
     * Modifica los angulos de los sectores circulares
     * Deben sumar 270°
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

    public void setMedida(int medida){
        this.medida = medida;
    }
    public void setColorAguja(int colorAguja) {
        this.colorAguja = colorAguja;
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
        /*
         dibujar el tacometro sin la aguja
        */
        //aqui empieza el dibujo

        float radio = (float) (0.5 * largo);
        pincel.setColor(Color.RED);
        canvas.drawCircle(0, 0, radio, pincel);
        float radioExterno = (float) (0.48 * largo);
        pincel.setColor(colorFondo);
        canvas.drawCircle(0, 0, radioExterno, pincel);


        float esquinaSuperiorIzquierdaX = -0.36f * largo;
        float esquinaSuperiorIzquierdaY = -0.36f * largo;
        float esquinaInferiorDerechaX = 0.36f * largo;
        float esquinaInferiorDerechaY = 0.36f * largo;

        //dibujar los tres segmentos circulares
        RectF rect = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);
        pincel.setColor(colorPrimerTercio);
        canvas.drawArc(rect, 90, angPrimerTercio, true, pincel);
        pincel.setColor(colorSegundoTercio);
        canvas.drawArc(rect, 90 + angPrimerTercio, angSegundoTercio, true, pincel);
        pincel.setColor(colorTercerTercio);
        canvas.drawArc(rect, 90 + angPrimerTercio + angSegundoTercio, angTercerTercio, true, pincel);



        float radioInterno = (float) (0.34 * largo);
        pincel.setColor(colorFondo);
        canvas.drawCircle(0, 0, radioInterno, pincel);

        float indent = (float) (0.05 * largo);
        float posicionY = (float) (0.34 * largo);
        float largoAguja = (float) (0.42 * largo);

        //calcular angulo para ubicar la aguja de acuerdo al valor medido
        float angulo_rotacion_medida = 180 + (270f / (maximo - minimo)) * (medida - minimo);
        float a = (float) 0.01 * largo;

        //Ahora dibujaremos la aguja
        pincel.setStrokeWidth(0.005f * largo);
        pincel.setColor(colorAguja);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        float b = (float) (3 * indent);
        canvas.drawLine(0, -largoAguja, 0, b, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);


        // dibujaremos los circulo que estan dentro de la aguja
        float radioCirculoAguja1 = (float) (0.055 * largo);
        pincel.setColor(colorAguja);
        canvas.drawCircle(0, 0, radioCirculoAguja1, pincel);
        float radioCirculoAguja2 = (float) (0.039 * largo);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, radioCirculoAguja2, pincel);
        float radioCirculoAguja3 = (float) (0.025 * largo);
        pincel.setColor(colorAguja);
        canvas.drawCircle(0, 0, radioCirculoAguja3, pincel);


        // termina dibujo de la aguja


        /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 45 grados comenzando
         en 90 grados.
         */

        for (int i = 0; i < 7; i += 1) {
            pincel.setStyle(Paint.Style.STROKE);
            //grosor de las líneas
            pincel.setStrokeWidth(0.005f * largo);
            float anguloRotacion = 180 + 45 * i;
            if (anguloRotacion >= 180 && anguloRotacion <= (180 + angPrimerTercio)) {
                pincel.setColor(colorPrimerTercio);
            } else if (anguloRotacion >= 180 + angPrimerTercio && anguloRotacion <=
                    (180 + angPrimerTercio + angSegundoTercio)) {
                pincel.setColor(colorSegundoTercio);
            } else {
                pincel.setColor(colorTercerTercio);
            }
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

            // dibujar los numeros
            float valorIncrementoMarcas = ((maximo - minimo) / 6f);
            float valorMarca =  (minimo + valorIncrementoMarcas * i);
            String numero = "" + (int) valorMarca;

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
            float radio_posicion_numero = 0.3f * largo - (1.2f * indent);
            int p_x = (int) (radio_posicion_numero * Math.sin(angulo));
            int p_y = (int) (radio_posicion_numero * Math.cos(angulo) - 0.3f * altoCadenaNumero);
            float d_x = 0;

            if (p_x < 0 || p_x == 0) {
                d_x = -0.5f * anchoCadenaNumero;
            } else {
                d_x = -0.8f * anchoCadenaNumero;
            }


            // dibujar la letra rellenita
            pincel.setStyle(Paint.Style.FILL);
            pincel.setTextSize(0.078f * largo);
            pincel.setColor(colorUnidades);

            //dibujar los numeros de las marcas

            canvas.translate(p_x, -p_y);
            canvas.drawText(numero, d_x, 0, pincel);
            canvas.translate(-p_x, p_y);
        }
        /*
         Divisiones pequeñas
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 15 grados comenzando
         en 180 grados.
         */

        /*for (int i = 0; i < 18; i = i + 1) {
            pincel.setStyle(Paint.Style.STROKE);
            //grosor de las líneas
            pincel.setStrokeWidth(0.005f * largo);
            float anguloRotacion = 180 + 15 * i;
            if (anguloRotacion >= 180 && anguloRotacion <= (180 + angPrimerTercio)) {
                pincel.setColor(colorPrimerTercio);
            } else if (anguloRotacion >= 180 + angPrimerTercio && anguloRotacion <=
                    (180 + angPrimerTercio + angSegundoTercio)) {
                pincel.setColor(colorSegundoTercio);
            } else {
                pincel.setColor(colorTercerTercio);
            }
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + (0.5f * indent), pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

        }*/
        pincel.setColor(Color.YELLOW);
        canvas.drawRoundRect(0.11f * largo, 0.08f * largo, 0.26f * largo, 0.24f * largo, (float) 0.055 * largo, (float) 0.055 * largo, pincel);

        pincel.setColor(Color.BLACK);
        canvas.drawRoundRect(0.12f * largo, 0.09f * largo, 0.25f * largo, 0.23f * largo, (float) 0.045 * largo, (float) 0.045 * largo, pincel);
        //Aqui finaliza el dibujo del tacometro
        //Dibujar las unidades

        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorNumerosDespliegue);
        float anchoCadenaUnidades = pincel.measureText(unidades);
        pincel.setTypeface(Typeface.SANS_SERIF);
        pincel.setTextSize (1800/ anchoCadenaUnidades);
        canvas.drawText(unidades, 0.14f*largo, 0.21f * largo, pincel);
        //aqui termina dibujo de las unidades

        //aqui despliegue de la medida
        pincel.setTextSize(0.1f * largo);
        pincel.setColor(colorNumerosDespliegue);
        float anchoCadenaNumero = pincel.measureText("" + medida);
        pincel.setTextSize( 3000/ anchoCadenaNumero);
        canvas.drawText("" + (int) medida, 0.15f * largo, 0.17f * largo, pincel);


        //se restaura el canvas al estado incial
        //el que se grabó al principio de este método
        canvas.restore();

        //para efectos de animación
        invalidate();

    } // finaliza el onDraw
}
