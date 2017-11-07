package ac.cr.ucr.ci1320;

import java.io.DataOutputStream;
import java.io.IOException;


public class Client extends Connection {
    public  Client() throws IOException {
        super("client", 5000, "192.168.100.16");
    }

    public void startClient(){
        try {

            outServer = new DataOutputStream(cs.getOutputStream());
            char[] c = new char[1000];
            c[0] = 165;
            c[1] = 8;
            c[2] = 5;
            c[3] = 6;

            c[4] = 165;
            c[5] = 8;
            c[6] = 5;
            c[7] = 6;

            c[8] = 0;

            c[9] = 165;
            c[10] = 7;
            c[11] = 3;
            c[12] = 0;

            c[13] = 1;

            c[14] = 'h';

            String msj = "";
            msj = msj + String.valueOf((int) c[0])+"."+String.valueOf((int) c[1])+"."+String.valueOf((int) c[2])+"."+String.valueOf((int) c[3])+"\n"
                    + String.valueOf((int) c[4])+"."+String.valueOf((int) c[5])+"."+String.valueOf((int) c[6])+"."+String.valueOf((int) c[7])+"\n"
                    + String.valueOf((int) c[8])+"\n" //action
                    + String.valueOf((int) c[9])+"."+String.valueOf((int) c[10])+"."+String.valueOf((int) c[11])+"."+String.valueOf((int) c[12])+"\n"
                    + String.valueOf((int) c[13])+"\n"; //msj size
            for(int i=14;i<+14+c[13];i++){
                msj = msj + c[i];
            }
            msj = msj + "\n";

            outServer.writeUTF(msj);

            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}