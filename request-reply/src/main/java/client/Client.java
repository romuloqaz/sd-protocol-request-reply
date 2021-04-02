package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String args[]) {
        Socket socket = null;
        try {
            //conexão do socket para porta de entrada
            socket = new Socket(args[0], Integer.parseInt(args[1]));
            DataInputStream dataInputStreamIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStreamOut = new DataOutputStream(socket.getOutputStream());
            dataOutputStreamOut.writeUTF(args[2]);
            // leitura do buffer para porta de entrada
            String receiver = dataInputStreamIn.readUTF();
            System.out.println("Sucesso!");
            System.out.println("Mensagem recebida do server: " + receiver);
        } catch (UnknownHostException e) {
            System.out.println("Falha:  servidor não encontrado " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("Falha. Não existem dados " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Falha: Entrada e saida de dados desconhecida " + e.getMessage());
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Falha. Socket falhou " + e.getMessage());
                }
        }
    }
}
