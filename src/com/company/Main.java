package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    Server server  = new Server(5555, "localhost");
	    server.startServer();
    }
}


