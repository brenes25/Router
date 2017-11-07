package ac.cr.ucr.ci1320;

import ac.cr.ucr.ci1320.Dispatcher.Dispatcher;
import javafx.util.Pair;

import java.io.DataOutputStream;
import java.io.IOException;


public class Client extends Connection {

    private Dispatcher dispatcher;
    private Pair<String,String> pair;

    public  Client(String type,int port,String ip,Dispatcher dispatcher,Pair<String,String> pair) throws IOException {
        super(type,port,ip);
        this.dispatcher = dispatcher;
        this.pair = pair;
    }

    boolean isLocal(){
        return true;
    }

    public void startClient(String message){
        try {
            String[] arrayMessage = message.split("\n");
            int action = Integer.parseInt(arrayMessage[2]);
            String answerMessage = "";
            switch (action){
                case 1:
                    IpData ipData = dispatcher.getData(arrayMessage[1]);
                    if(ipData != null) { //si lo conozco
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '4' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    } else { //soy yo
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '3' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    break;
                case 2:
                    IpData ipData2 = dispatcher.getData(arrayMessage[1]);
                    if(ipData2 != null) { //si se la ruta
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '4' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    else{ //mensaje de error, no conozco la ruta al mae
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '5' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }

                    break;
                default: //caso 0


                    break;
            }
            /*posibles soluciones:
            1. new String(bla bla)

             */
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(answerMessage);
            this.cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void testClient(){
        try {
            outServer = new DataOutputStream(cs.getOutputStream());
            for (int i = 0; i < 2; i++) {
                outServer.writeUTF("Este es el número"+(i+1)+"\nHOLA1\nHOLA2");
            }
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}