package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Connexion extends Thread {

    private Socket socketClient; //cliente
    private DataInputStream dataInputStreamInput; //entrada
    private DataOutputStream dataOutputStreamOutput; //saida

    public Connexion(Socket socket) { // s
        try {
            socketClient = socket;
            dataInputStreamInput = new DataInputStream(socketClient.getInputStream());
            dataOutputStreamOutput = new DataOutputStream(socketClient.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Falha: Conexao de entrada e saída " + e.getMessage());
        }
    }

    public void run() {
        try {
            String received = dataInputStreamInput.readUTF();
            dataOutputStreamOutput.writeUTF(received.toUpperCase());
        } catch (EOFException e) {
            System.out.println("Falha: EOFException " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Falha: IOException " + e.getMessage());
        } finally {
            try {
                socketClient.close();
            } catch (IOException e) {
                System.out.println("Falha: Fechamento do socket");
            }
        }
    }
}
