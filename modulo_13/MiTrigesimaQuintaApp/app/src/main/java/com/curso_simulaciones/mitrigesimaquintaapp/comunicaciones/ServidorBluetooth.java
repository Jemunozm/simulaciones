package com.curso_simulaciones.mitrigesimaquintaapp.comunicaciones;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import com.curso_simulaciones.mitrigesimaquintaapp.datos.AlmacenDatosRAM;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ServidorBluetooth {


    public static final String NOMBRE_SEGURO = "BluetoothServiceSecure";
    public BluetoothAdapter adaptadorBluetooth;
    public BluetoothServerSocket serverSocket;
    public BluetoothSocket clienteSocket;

    private BufferedInputStream flujoEntrada;
    private BufferedOutputStream flujoSalida;


      /*
     Como estamos creando una conexión SPP (como si fuese un puerto serie virtual)
     a través del protocolo RFCOMM, debemos obtener el socket que conecta con el
     servicio 00001101-0000-1000-8000-00805F9B34FB, que es el identificador único
     de servicio para SPP en el estandar Bluetooth.
     */

    public static final UUID UUID_SEGURO = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    public ServidorBluetooth() {

        adaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();

    }


    // 1. Abrir un socket (ServerSocket) para esperar por peticiones de conexión

    /**
     * Crear un ServerSocket
     */


    public void abrirSocketServidor() {

        if (serverSocket != null)
            serverSocket = null;

        try {
            serverSocket = adaptadorBluetooth.listenUsingRfcommWithServiceRecord(NOMBRE_SEGURO, UUID_SEGURO);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    // 2. Al llegar una petición, crear otro socket (Socket) por medio del cual se comunicará con el cliente

    /**
     * Socket para comunicarse con el cliente
     */

    public void abrirSocketCliente() {

        try {

            clienteSocket = serverSocket.accept();
            AlmacenDatosRAM.conexion_bluetooth = " Conectado con  " + clienteSocket.getRemoteDevice().getName().toString();

        } catch (IOException e) {
            AlmacenDatosRAM.conexion_bluetooth = "Falló la conexión";
            e.printStackTrace();

        }

    }

    //3. Asociar uno o más flujos intermedios a el flujo de entrada (InputStream) asignado al socket
    public void abrirFlujoEntrada() {


        try {


            flujoEntrada = new BufferedInputStream(clienteSocket.getInputStream());


        } catch (IOException e) {

        }

    }


    //3. Asociar uno o más flujos intermedios al flujo de salida (OutpuStream) asignado al socket
    public void abrirFlujoSalida() {

        try {

            flujoSalida = new BufferedOutputStream(clienteSocket.getOutputStream());

        } catch (IOException e) {

        }


    }


    //  4. Leer/Escribir a los flujos de acuerdo al protocolo establecido

    /**
     * Lee los datos que le fueron enviados  como flujo de bytes.
     *
     * @return
     */


    //public void leerBytes() {
    public byte[] leerBytes() {


        int nuevoDato = 0;
        byte[] buffer = new byte[1024];

        byte[] datoBytesRecibido = null;


        if (flujoEntrada != null) {

            try {

                nuevoDato = flujoEntrada.read(buffer);
                datoBytesRecibido = (new String(buffer, 0, nuevoDato)).getBytes();//en byte[]


            } catch (IOException e) {

                e.printStackTrace();


            }


        }

        return datoBytesRecibido;

    }


    /**
     * Envía los datos en flujo de bytes
     */
    public void escribirBytes(byte[] datoByteParaEnviar) {


        int size = datoByteParaEnviar.length;

        byte[] buffer = new byte[1024];

        if (datoByteParaEnviar != null)
            buffer = datoByteParaEnviar;


        if (flujoSalida != null) {
            try {

                flujoSalida.write(buffer);
                flujoSalida.flush();
                //Log.d(TAG, "Holis");

            } catch (IOException e) {
            }


        }
    }


    //  6. Cerrar flujos y sockets

    /**
     * Cierra el socket cliente
     */

    public void cerrarFlujoEntrada() {


        if (flujoEntrada != null) {

            try {

                flujoEntrada.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public void cerrarFlujoSalida() {


        if (flujoSalida != null) {
            try {

                flujoSalida.flush();
                flujoSalida.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void cerrarSocketCliente() {


        if (clienteSocket != null) {
            try {

                clienteSocket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
