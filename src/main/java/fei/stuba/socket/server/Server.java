package fei.stuba.socket.server;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private DataInputStream in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        readingBinaryDataFromSocket(in);
    }

    /*
    SOH : 01h
    STX : 02h
    EOT : 04h
    DC3 : 13h
    ¬ : 20h (space)
    */

    public void readingBinaryDataFromSocket(DataInputStream in) throws IOException{
        char dataType = in.readChar();
        int length = in.readInt();
        if(dataType == 's') {
            byte[] messageByte = new byte[length];
            boolean end = false;
            StringBuilder dataString = new StringBuilder(length);
            int totalBytesRead = 0;
            while(!end) {
                int currentBytesRead = in.read(messageByte);
                totalBytesRead = currentBytesRead + totalBytesRead;
                if(totalBytesRead <= length) {
                    dataString
                            .append(new String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
                } else {
                    dataString
                            .append(new String(messageByte, 0, length - totalBytesRead + currentBytesRead,
                                    StandardCharsets.UTF_8));
                }
                if(dataString.length()>=length) {
                    end = true;
                }
            }
        }
    }
    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

}
