import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 46066294p on 03/02/16.
 */
public class ServidorCalc {


    public static void main(String[] args) {
        System.out.println("...creando servidor");

        String ip;

        int contador = 0;
        /*
        El constructor del serverSocket es diferente del cliente
        Tiene metodos que el cliente no tiene
         */

        try {

            ServerSocket serverSocket = new ServerSocket();
            System.out.println("...realizando bind");//bind = vincular
            InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);
            serverSocket.bind(addr);//el servidor escucha en la direccion que le digamos
            System.out.println("...escuchando");

            while (contador <9) {

                Socket socketEscucha = serverSocket.accept();
                ip = socketEscucha.getInetAddress().toString();
                HiloServidor hiloServidor = new HiloServidor(socketEscucha, ip);
                hiloServidor.run();
                System.out.print("Lamada: "+contador);
                contador++;
            }


            serverSocket.close();
            System.out.println("FIN");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}