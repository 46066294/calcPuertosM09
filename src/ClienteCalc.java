import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

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
public class ClienteCalc {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "marc");
        System.out.println("...iniciando protocolo Secure Sockets Layer (SSL) (Client)");

        try {

        Scanner scanner = new Scanner(System.in);

            int cont = 0;
            while(cont < 5){
                //os.flush();

                SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
                SSLSocket clienteSocket = (SSLSocket) ssf.createSocket();
                //Socket clienteSocket = new Socket();
                System.out.println("...creado el socket cliente");
                System.out.println("...estableciendo conexion");


                //InetSocketAddress addr = new InetSocketAddress("172.31.83.41", 5555);
                InetSocketAddress addr = new InetSocketAddress("localhost", 5555);


                clienteSocket.connect(addr);

                InputStream is = clienteSocket.getInputStream();
                OutputStream os = clienteSocket.getOutputStream();
                System.out.println("\n...escriba mensaje:");
                String operacion = scanner.nextLine();
                os.write(operacion.getBytes());
                System.out.println("...mensaje enviado");
                System.out.println("Resultado de la operacion:");

                byte[] k = new byte[50];
                is.read(k);
                System.out.println(new String(k));
                cont++;


                byte[] mngBienvenidaServer = new byte[50];
                is.read(mngBienvenidaServer);
                System.out.println(new String(mngBienvenidaServer));

                //System.out.println("\n...escriba mensaje:");



                is.close();
                os.close();
                clienteSocket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
