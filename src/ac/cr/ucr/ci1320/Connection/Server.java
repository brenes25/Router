package ac.cr.ucr.ci1320.Connection;
import ac.cr.ucr.ci1320.IpData;
import javafx.util.Pair;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Universidad de Costa Rica
 * Facultad de Ingeniería
 * Escuela de Ciencias de la Computación e Informática
 * Profesora: Gabriela Barrantes
 * Autores:
 * Abellán Jiménez Mariana B50031
 * Brenes Solano Silvia B41133
 * Cubero Sánchez Josué B42190
 * Garita Centeno Alonso B42791
 */

public class Server extends Connection {
    private Client client;
    private Pair<String,String> pair;
    private Map<String,String> relation;
    private Map<String, IpData> ipTable;


    public Server(Pair<String,String> pair, Map<String,String> relation,Map<String,IpData> ipTable) throws IOException {
        this.pair = pair;
        this.relation = relation;
        this.ipTable = ipTable;
    }

    public Server (Map<String, IpData> ipTable){
        this.ipTable = ipTable;
    }

    public void startServer() throws IOException{
        super.createSocket("server", 5555, "localhost");
        try {
            while(true){
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                System.out.println("Se recibio: "+ Arrays.toString(newMessage.split("\n"))+"\n");
                this.client = new Client(this.pair, this.relation,this.ipTable);
                this.client.startClient(newMessage);
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
                this.printTable();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void printTable(){
        Iterator iterator = this.ipTable.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry pair = (Map.Entry) iterator.next();
            IpData ipData = (IpData) pair.getValue();
            System.out.println(pair.getKey()+"--"+ipData.getFakeIp()+"--"+ipData.getRealIp()+"--"
                    +ipData.getFakePath()+"--"+ipData.getDistance()+"--"+ipData.getPort());
        } //ya aca tiene el campo de mensaje lleno ak7
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