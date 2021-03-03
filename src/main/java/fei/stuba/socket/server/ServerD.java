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
    public void readData(Socket clientSocket) throws IOException{
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (".".equals(inputLine)) {
                break;
            }
            System.out.println(inputLine);
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
