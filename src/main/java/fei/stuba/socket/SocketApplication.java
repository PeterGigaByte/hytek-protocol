package fei.stuba.socket;

import fei.stuba.socket.client.Client;
import fei.stuba.socket.server.Server;
import fei.stuba.socket.server.ServerB;
import fei.stuba.socket.server.ServerC;
import fei.stuba.socket.server.ServerD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SocketApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SocketApplication.class,args);

        Server server = new Server();
        server.start(8055);

        //ServerB serverB = new ServerB();
        //serverB.start(8055);

        //ServerC serverC = new ServerC();
        //serverC.start(8055);

        //ServerD serverD = new ServerD();
        //serverD.start(8055);

        /*
        Client client = new Client();
        client.startConnection("192.168.1.31",8055);
        for (int n = 0;n<500;n++){
            System.out.println(client.sendMessage("packet number " + n));
        }
        System.out.println(client.sendMessage("."));
            */

    }

}
