import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 46066294p on 10/02/16.
 */
public class ServidorCalc {



    public static void main(String[] args) {
        System.out.println("...creando servidor");

        /*
        El constructor del serverSocket es diferente del cliente
        Tiene metodos que el cliente no tiene
         */

        try {
            String msgSalida;
            String msgEntrada;


            ServerSocket serverSocket = new ServerSocket();
            System.out.println("...realizando bind");//bind = vincular
            InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);

            serverSocket.bind(addr);//el servidor escucha en la direccion que le digamos

            /*
            El servidor tiene que ser la misma maquina que ejecuta
            el programa servidor???
             */

            System.out.println("...escuchando");
            /*
            Aqui es donde el servidor se quedara escuchando asta que reciba una conexion
             */
            Socket socketDeEscucha = serverSocket.accept();
            System.out.println("...se ha recibido una llamada");

            InputStream is = socketDeEscucha.getInputStream();
            OutputStream os = socketDeEscucha.getOutputStream();

            msgSalida = "Ha comenzado la calculadora, escriba la operacion";
            os.write(msgSalida.getBytes());

            byte[] mensaje = new byte[500];
            is.read(mensaje);

            msgEntrada = new String(mensaje);

            os.write(Operacion.calcular(msgEntrada).getBytes());
            System.out.println(msgEntrada);

            System.out.println("...cerrando");

            socketDeEscucha.close();
            serverSocket.close();
            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}