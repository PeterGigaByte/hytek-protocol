package fei.stuba.socket.server;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ServerD {
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        String clientSentence;
        while (true){
            connectionSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = in.readLine();
            System.out.println("Received: " + clientSentence);
        }
    }
    /*
    SOH : 01h
    STX : 02h
    EOT : 04h
    DC3 : 13h
    ¬ : 20h (space)
    */


}
