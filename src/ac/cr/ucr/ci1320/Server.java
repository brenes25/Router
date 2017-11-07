package ac.cr.ucr.ci1320;

import ac.cr.ucr.ci1320.Connection;
import ac.cr.ucr.ci1320.Dispatcher.Dispatcher;
import javafx.util.Pair;

import java.io.*;

public class Server extends Connection {
    private Client client;
    private Dispatcher dispatcher;
    private Pair<String,String> pair;

    public Server(int PORT, String HOST, Dispatcher dispatcher,Pair<String,String> pair) throws IOException {
        super("server", PORT, HOST);
        this.dispatcher = dispatcher;
        this.pair = pair;
    }

    public void startServer() {
        try {
            while(true) {
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                System.out.println(newMessage);
                this.client = new Client("client",5555,"192.168.100.15",this.dispatcher,this.pair);
                this.client.startClient(newMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String parsePabloArray(char[] c){
        String msj;
        msj = String.valueOf((int) c[0])+"."+String.valueOf((int) c[1])+"."+String.valueOf((int) c[2])+"."+String.valueOf((int) c[3])+"\n"
        + String.valueOf((int) c[4])+"."+String.valueOf((int) c[5])+"."+String.valueOf((int) c[6])+"."+String.valueOf((int) c[7])+"\n"
        + String.valueOf((int) c[8])+"\n" //action
        + String.valueOf((int) c[9])+"."+String.valueOf((int) c[10])+"."+String.valueOf((int) c[11])+"."+String.valueOf((int) c[12])+"\n"
        + String.valueOf((int) c[13])+"\n"; //msj size
        for(int i=14;i<+14+c[4];i++){
            msj = msj + c[i];
        }
        msj = msj + "\n";
        return msj;
    }
}