package fei.stuba.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerC {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Scanner scanner;
    public void start(int port) throws IOException {
        int number,temp;
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        scanner = new Scanner(clientSocket.getInputStream());

        number = scanner.nextInt();

        temp=number*2;

        PrintStream p = new PrintStream(clientSocket.getOutputStream());
        p.println(temp);

    }


}
