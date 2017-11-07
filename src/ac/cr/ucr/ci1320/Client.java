package ac.cr.ucr.ci1320;

import java.io.DataOutputStream;
import java.io.IOException;


public class Client extends Connection {
    public  Client() throws IOException {
        super("client", 5555, "192.168.100.14");
    }

    public void startClient(){
        try {
            outServer = new DataOutputStream(cs.getOutputStream());
            for (int i = 0; i < 2; i++) {
                outServer.writeUTF("Este es el número"+(i+1)+"\n");
            }
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}