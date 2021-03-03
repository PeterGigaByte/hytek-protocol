package fei.stuba.socket.server;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ServerB {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
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


    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

}
