package fei.stuba.socket.server;
import fei.stuba.socket.Result.Results;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private DataInputStream in;
    private Results results = new Results();
    private final String SOHandDC3 = "113";
    private final String DV = "4456";


    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while(true) {
            clientSocket = serverSocket.accept();
            in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            readingBinaryDataFromSocket(in,"123");
        }
    }

    /*
    SOH : 01h
    STX : 02h
    EOT : 04h
    DC3 : 13h
    ¬ : 20h (space)
    */

    public void readingBinaryDataFromSocket(DataInputStream in,String SOHandDC3) throws IOException{
        String temp=SOHandDC3;
        try {
            SOHandDC3 = Integer.toHexString(in.readChar());
        }catch (EOFException e){
            SOHandDC3 = "0";
        }
        System.out.println("SOH and DC3 = " + SOHandDC3);
        if(SOHandDC3.equals("113")) { //113 -> SOH (01) -> start of communication + DC3 (13)
            String DV = Integer.toHexString(in.readChar());
            System.out.println("DV = " + DV);
            if (DV.equals("4456")) { //4456 -> DV(44 56)
                String type = Integer.toHexString(in.readChar()) + Integer.toHexString(in.readByte());
                System.out.println("type = " + type);
                String STX = Integer.toHexString(in.readByte());
                switch (type) {
                    case "534e52": //SNR
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("id race");
                            byte[] message = new byte[8];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            test = test.replaceAll("\\s","");
                            results.setIdRace(test);
                            System.out.println(test);
                        }

                        break;
                    case "52574d": //RWM
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("out wind");
                            byte[] message = new byte[9];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            test = test.trim().replaceAll(" +"," ");
                            results.setWind(test);
                            System.out.println(test);
                        }

                        break;
                    case "524443": //RDC
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("start of transfer ranking");
                            byte[] message = new byte[3];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            System.out.println(test);
                        }

                        break;
                    case "524343": //RCC
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("result of ranked comp");
                            byte[] message = new byte[22];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            test = test.trim().replaceAll(" +"," ");
                            String[] splitBySpaces = test.split("\\s+");
                            results.setOrd(splitBySpaces[0]);results.setBib(splitBySpaces[1]);results.setLine(splitBySpaces[2]);results.setTime(splitBySpaces[3]);
                            System.out.println(results.toString());
                        }

                        break;
                    case "525443": //RTC
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("result of temp class");
                            byte[] message = new byte[26];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            System.out.println(test);
                        }

                        break;
                    case "524643": //RFC
                        if (STX.equals("4")) {
                            System.out.println("EOT = " + STX);
                            System.out.println("end of transfer ranking");
                            System.out.println("end of transmission");
                            //nothing
                        }

                        break;
                    case "524346": //RCF
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("DVRCF");
                            byte[] message = new byte[16];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            System.out.println(test);
                            String nullByte = Integer.toHexString(in.readByte());
                            if(nullByte.equals("4")){
                                System.out.println("end of transmission");
                                System.out.println("correct ending");
                                readingBinaryDataFromSocket(in,nullByte);

                            }

                        }
                        break;
                    case "52464a": //RFJ
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("end of judgement");
                            byte[] message = new byte[8];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            System.out.println(test);
                        }

                        break;
                    case "444643": // DFC
                        if (STX.equals("2")) {

                            System.out.println("STX = " + STX);
                            System.out.println("output day time");
                        }

                        break;
                    case "---":
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("unknown");
                        }
                        break;
                    case "534344": //SCD
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("supplementary .. data");
                        }

                        break;
                    case "534348": //SCH
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("supplementary .. header");
                        }

                        break;
                    case "524356": //RCV
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("Result of competitor");
                            byte[] message = new byte[19];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            System.out.println(test);
                        }
                        break;
                    case "524353": //RCS
                        if (STX.equals("2")) {
                            System.out.println("STX = " + STX);
                            System.out.println("DVRCS");
                            byte[] message = new byte[7];
                            in.read(message);
                            String test = new String(message,StandardCharsets.UTF_8);
                            System.out.println(test);
                        }
                        break;
                    default:
                        System.out.println("hello");
                }
                String EOT = Integer.toHexString(in.readByte());
                if (EOT.equals("4")) {
                    String nullByte = Integer.toHexString(in.readByte());
                    System.out.println("end of transmission");
                    System.out.println("correct ending");
                    readingBinaryDataFromSocket(in,nullByte);
                }
                else if(EOT.equals("0")){
                    System.out.println("correct ending");
                    readingBinaryDataFromSocket(in,EOT);

                }
                else{
                    System.out.println("error ending 2");
                    readingBinaryDataFromSocket(in,EOT);

                }
            }
        }

        else{
            if(SOHandDC3.equals(temp)){
                System.out.println("end of packet");
            }
            else if(!SOHandDC3.equals("0")){
                System.out.println("Error ending");
                readingBinaryDataFromSocket(in,SOHandDC3);
            }
            else {
                System.out.println("End of Packet");
                readingBinaryDataFromSocket(in,SOHandDC3);
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
    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
