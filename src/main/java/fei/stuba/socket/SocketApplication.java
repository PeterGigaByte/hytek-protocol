package fei.stuba.socket;

import fei.stuba.socket.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SocketApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SocketApplication.class,args);

        Server server = new Server();
        server.start(8055);



    }

}
