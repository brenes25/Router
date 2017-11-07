package ac.cr.ucr.ci1320;

import ac.cr.ucr.ci1320.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(5000,"localhost");
        server.startServer();
    }
}


