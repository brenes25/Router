package ac.cr.ucr.ci1320.Conection;

import ac.cr.ucr.ci1320.IpData;
import javafx.util.Pair;

import java.io.*;
import java.util.Map;

public class Server extends Connection {
    private Client client;
    private Pair<String,String> pair;
    private Map<String,String> relation;
    private Map<String, IpData> ipTable;


    public Server(Pair<String,String> pair, Map<String,String> relation) throws IOException {
        this.pair = pair;
        this.relation = relation;
    }

    public Server (Map<String, IpData> ipTable){
        this.ipTable = ipTable;
    }

    public void startServer() throws IOException{
        super.createSocket("server", 5555, "localhost");
        try {
            while(true) {
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                System.out.println(newMessage);
                this.client = new Client(this.pair, this.relation);
                //this.client.startClient(newMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void startServerDispatcher() throws IOException{
        super.createSocket("server", 4444, "localhost");
        try {
            while(true) {
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                this.fillTable(newMessage);
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

    public void fillTable(String message){
        String[] entries = message.split("\n");
        for(int i = 0; i < entries.length;  i++){
            String[] data = entries[i].split(",");
            ipTable.put(data[0], new IpData(data[1], data[2],data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
        }
    }
}