package com.curso_simulaciones.mitrigesimaquintaapp.comunicaciones;

import static android.content.ContentValues.TAG;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.curso_simulaciones.mitrigesimaquintaapp.datos.AlmacenDatosRAM;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ClienteBluetooth {

    //socket del cliente
    public BluetoothAdapter adaptadorBluetooth;
    public BluetoothDevice dispositivo;
    public BluetoothSocket clienteSocket;

    private BufferedInputStream flujoEntrada;
    private BufferedOutputStream flujoSalida;

    //private JSONObject obj;
    //private String dato;
    private String datoString;


    /*
     Como estamos creando una conexión SPP (como si fuese un puerto serie virtual)
     a través del protocolo RFCOMM, debemos obtener el socket que conecta con el
     servicio 00001101-0000-1000-8000-00805F9B34FB, que es el identificador único
     de servicio para SPP en el estandar Bluetooth.
     */

    public static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /**
     * Constructor
     * direccion es el MAC del dispositivo
     */

    public ClienteBluetooth() {

    }


    // 1. Abrir un socket (Socket) para conectarse y comunicarse con el Servidor

    public void abrirSocketCliente(String direccion) {

        adaptadorBluetooth = BluetoothAdapter.getDefaultAdapter();
        dispositivo = adaptadorBluetooth.getRemoteDevice(direccion);


        // Obtenemos un socket cliente para el dispositivo con el que se quiere conectar
        try {

            clienteSocket = dispositivo.createRfcommSocketToServiceRecord(uuid);

        } catch (IOException e) {

        }


    }

    //2. Establecer conexión con el Servidor

    public void conectarSocketCliente() {

        try {

            clienteSocket.connect();

            AlmacenDatosRAM.conexion_bluetooth = "  Conectado con " + clienteSocket.getRemoteDevice().getName().toString();


        } catch (IOException e) {
            AlmacenDatosRAM.conexion_bluetooth = "  No se pudo conectar...";
            e.printStackTrace();

        }


    }


    //3. Asociar uno o más flujos intermedios a el flujo de entrada (InputStream) asignado al socket

    public void abrirFlujoEntrada() {

        if (clienteSocket != null) {

            try {

                flujoEntrada = new BufferedInputStream(clienteSocket.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();

            }


        }

    }


    //3. Asociar uno o más flujos intermedios al flujo de salida (OutpuStream) asignado al socket
    public void abrirFlujoSalida() {

        if (clienteSocket != null) {
            try {

                flujoSalida = new BufferedOutputStream(clienteSocket.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }


    // 4. Leer/Escribir a los flujos de acuerdo al protocolo establecido

    /**
     * Lee los datos que le fueron enviados  como flujo de bytes.
     *
     * @return
     */

    public String leerString() {

        int dato;

        byte[] buffer = new byte[8 * 1024];//1024*8];

        try {

            if (flujoEntrada != null) {

                dato = flujoEntrada.read(buffer);


                datoString = new String(buffer, 0, dato);

            }
        } catch (IOException e) {
            Log.d(TAG, "falla");//bien aqui
            e.printStackTrace();
        }


        return datoString;

    }


    /**
     * Envía los datos en flujo de bytes
     */
    public void escribirBytes(byte[] datoByteParaEnviar) {

        byte[] buffer = new byte[1024];

        if (datoByteParaEnviar != null)
            buffer = datoByteParaEnviar;


        if (flujoSalida != null) {
            try {

                flujoSalida.write(buffer);
                flujoSalida.flush();

            } catch (IOException e) {
            }

        }

    }


    //  6. Cerrar flujos y sockets

    /**
     * Cierra el socket cliente
     */
    public void cerrarSocketCliente() {

        if (clienteSocket != null) {
            try {

                clienteSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


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

}
