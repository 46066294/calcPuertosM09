import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 46066294p on 10/02/16.
 */
public class HiloServidor extends Thread {
    String msgSalida = "Comenzando...";
    String msgEntrada;

    Socket socketEscucha;
    String ip;


    public HiloServidor(Socket socketEscucha, String ip) {
        this.socketEscucha = socketEscucha;
        this.ip = ip;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("\n...se ha recibido una llamada");

        try {
            System.out.println("Recibida la llamada de: " + ip + "\n");

            InputStream is = socketEscucha.getInputStream();
            OutputStream os = socketEscucha.getOutputStream();

            os.write(msgSalida.getBytes());

            byte[] mensaje = new byte[500];
            is.read(mensaje);

            msgEntrada = new String(mensaje);

            os.write(Operacion.calcular(msgEntrada).getBytes());
            //System.out.println(msgEntrada);

            System.out.println("...cerrando");


            //socketEscucha.close();
            //serverSocket.close();
            is.close();
            os.close();


        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("...Error... cerrando");
        }
    }
}