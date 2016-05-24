import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
INSTRUCCIONS PER A IMPLEMENTAR SSL

 1 - Obrir consola de windows (o Linux) amb drets d'administrador

 2 - Anar al directori on esta el JDK que utilitzem al nostre projecte
 En el nostre cas:
 C:\Program Files\Java\jdk1.8.0_66\bin
 (Aqui podrem veure que hi ha un executable que es diu keytool)

 3 - Introduir el seguent comandament:
 keytool -genkey -keystore marc -keyalg RSA

 4 - Ens demanara un password per al nou magatzem de claus que es creara (almenys 6 caracters)
 Password: marcstore
 nom i cognom: marc cano
 nom d'unitat d'organitzacio: ecaib
 nom de l'organitzacio: ecaib
 ciutat: barcelona
 provincia: barcelona
 codi del pais: ES
 Password: marcstore

 5 - Aixo ens generarà un arxiu a la ruta especificada C:\Program Files\Java\jdk1.8.0_66\bin
 amb el nom de "marc". Aquest es el fitxer que posteriroment copiarem al nostre projecte

 6 - A continuacio introduim el seguent comandament
 keytool -certreq -keyalg RSA -file certreq.csr -keystore marc

 7 - Aixo ens generara un arxiu de peticio amb nom certreq.csr
 Si volem veure el text de l'arxiu introduim el seguent comandament:
 type certreq.csr

 8 - Copiem el fitxer del punt 5 dins el directori mes generic del nostre projecte
 Important:
 Veure linies 19 i 20 de la clase ServidorCalc.java
 Veure linia 14 de la clase ClienteCalc.java

 Created by 46066294p on 03/02/16.
 Actualitzat 24/05/16
 */
public class ServidorCalc {


    public static void main(String[] args) {
        System.out.println("...creando servidor");

        System.setProperty("javax.net.ssl.keyStore", "marc");
        System.setProperty("javax.net.ssl.keyStorePassword", "marcstore");
        System.out.println("...iniciando protocolo Secure Sockets Layer (SSL) (Server)");

        String ip;

        int contador = 0;
        /*
        El constructor del serverSocket es diferente del cliente
        Tiene metodos que el cliente no tiene
         */

        try {
            //ServerSocket serverSocket = new ServerSocket();

            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket();
            System.out.println("...realizando bind");//bind = vincular
            InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);
            serverSocket.bind(addr);//el servidor escucha en la direccion que le digamos
            System.out.println("...escuchando");

            while (contador <9) {

                Socket socketEscucha = serverSocket.accept();
                ip = socketEscucha.getInetAddress().toString();
                HiloServidor hiloServidor = new HiloServidor(socketEscucha, ip);
                hiloServidor.run();
                //System.out.print("Llamada: " + contador++);
                contador++;
            }


            serverSocket.close();
            System.out.println("FIN");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}