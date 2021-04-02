package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) {
        try {
            int portUsed = 5678; // porta de utilização do service
            //recebe a string inserida
            if (args.length > 0) portUsed = Integer.parseInt(args[0]);
            ServerSocket socketListener = new ServerSocket(portUsed);
            System.out.println("Server on *");
            System.out.println("Listener on port: " + portUsed);
            while (true) {
                // accept: bloqueia até uma nova requisição do cliente
                Socket socketCliente = socketListener.accept();
                System.out.println("A conexão foi aceita: " + socketCliente.getRemoteSocketAddress());
                // cria nova thread para ocorrer o atendimento ao cliente
                Connexion connexion = new Connexion(socketCliente);
            }
        } catch (IOException e) {
            System.out.println("Falha: erro de listener " + e.getMessage());
        }
    }
}
