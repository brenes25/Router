package ac.cr.ucr.ci1320;

import ac.cr.ucr.ci1320.Connection;

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

    public String parsePabloArray(char[] c){
        String msj = "";
        msj = msj + String.valueOf((int) c[0])+"."+String.valueOf((int) c[1])+"."+String.valueOf((int) c[2])+"."+String.valueOf((int) c[3])+"\n"
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