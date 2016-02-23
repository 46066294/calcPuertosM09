import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 46066294p on 03/02/16.
 */
public class ClienteCalc {
    public static void main(String[] args) {
        try {

        Scanner scanner = new Scanner(System.in);

            int cont = 0;
            while(cont < 5){
                //os.flush();
                Socket clienteSocket = new Socket();
                System.out.println("...creado el socket cliente");
                System.out.println("...estableciendo conexion");


                InetSocketAddress addr = new InetSocketAddress("172.31.83.41", 5555);


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
