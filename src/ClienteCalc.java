import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 46066294p on 03/02/16.
 */
public class ClienteCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Socket clienteSocket = new Socket();
        System.out.println("...creado el socket cliente");
        System.out.println("...estableciendo conexion");

        /*
        Un puerto es un lugar logico donde escuchar una conexion
        Hay asta 65 mil puertos. Hay algunos puertos reservados
        el 80 es http
        el 5432 es reservado para postgreSQL
        ...

         */

        InetSocketAddress addr = new InetSocketAddress("172.31.83.41", 5555);
        try {
            /*
            Este comando conecta el socket con la direccion y
            el puerto especificado
             */

            clienteSocket.connect(addr);

            /*
            Los comandos siguientes por donde va a escuchar y por
            donde va a hablar
             */
            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

            byte[] mngBienvenidaServer = new byte[50];
            is.read(mngBienvenidaServer);
            System.out.println(new String(mngBienvenidaServer));

            System.out.println("\n...escriba mensaje:");

            String operacion = scanner.nextLine();

            /*
            Ponemos el mensaje en el canal
            RECORDAR que hay que ponerlo en bits
             */
            os.write(operacion.getBytes());

            System.out.println("...mensaje enviado");

            System.out.println("Resultado de la operacion:");


            byte[] k = new byte[50];
            is.read(k);
            System.out.println(new String(k));

            is.close();
            os.close();
            clienteSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
