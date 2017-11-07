package ac.cr.ucr.ci1320;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Controller controller = new Controller();
       //controller.startController();
       // Server server = new Server(5555,"localhost", null, null);
        //server.startServer();
        Client client = new Client("client", 5555, "192.168.0.11", null, null);
        client.startClient2();

    }
}


