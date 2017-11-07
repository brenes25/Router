package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server extends Connection {

    public Server(int PORT, String HOST) throws IOException {
        super("server", PORT, HOST);
    }

    public void startServer() {
        try {
            System.out.println("\nServidor  esperando...");
            cs = ss.accept();
            System.out.println("Cliente conectado en el servidor ");
            outClient = new DataOutputStream(cs.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            while ((serverMessage = input.readLine())!=null) {
                System.out.println(serverMessage);
            }
            System.out.println("\nServidor cerrado");
            ss.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}