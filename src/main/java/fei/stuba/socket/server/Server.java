package fei.stuba.socket.server;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private DataInputStream in;
    private final String SOHandDC3 = "113";
    private final String DV = "4456";


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
        String SOHandDC3 = Integer.toHexString(in.readChar());
        System.out.println("SOH and DC3 = " + SOHandDC3);
        if(SOHandDC3.equals("113")) { //113 -> SOH (01) -> start of communication + DC3 (13)
            String DV = Integer.toHexString(in.readChar());
            System.out.println("DV = "+ DV);
            if(DV.equals("4456")){ //4456 -> DV(44 56)
                String type = Integer.toHexString(in.readChar()) + Integer.toHexString(in.readByte());
                System.out.println("type = " + type);
                String STX=Integer.toHexString(in.readByte());
                switch (type){
                    case "534e52":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("id race");
                            byte[] message = new byte[8];
                            in.read(message);
                        }

                        break;
                    case "52574d":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("out wind");
                            byte[] message = new byte[9];
                            in.read(message);
                        }

                        break;
                    case "524443":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("start of transfer ranking");
                            byte[] message = new byte[3];
                            in.read(message);
                        }

                        break;
                    case "524343":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("result of ranked comp");
                            byte[] message = new byte[22];
                            in.read(message);
                        }

                        break;
                    case "525443":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("result of temp class");
                            byte[] message = new byte[26];
                            in.read(message);
                        }

                        break;
                    case "544643":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("end of transfer ranking");
                        }

                        break;
                    case "524346":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("unknown");
                        }
                        break;
                    case "52464a":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("end of judgement");
                            byte[] message = new byte[8];
                            in.read(message);
                        }

                        break;
                    case "444643":
                        if(STX.equals("2")){

                            System.out.println("STX = " + STX);
                            System.out.println("output day time");
                        }

                        break;
                    case "---":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("unknown");
                        }

                        break;
                    case "534344":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("supplementary .. data");
                        }

                        break;
                    case "534348":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("supplementary .. header");
                        }

                        break;
                    case "524356":
                        if(STX.equals("2")){
                            System.out.println("STX = " + STX);
                            System.out.println("unknown");
                            byte[] message = new byte[19];
                            in.read(message);
                        }
                        break;
                    default:
                        System.out.println("hello");
                }
                String EOT = Integer.toHexString(in.readByte());
                if(EOT.equals("4")){
                    System.out.println("end of transmission");
                }

            }
            /*byte[] messageByte = new byte[3*2];
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
            }*/
        }
    }
    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
