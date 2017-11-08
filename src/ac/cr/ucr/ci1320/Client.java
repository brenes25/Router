package ac.cr.ucr.ci1320;
import javafx.util.Pair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;


public class Client extends Connection {


    private Pair<String,String> pair;
    private Map<String, String> relation;
    private int dispatcherPort;
    private String dispatcherRealIp;

    public  Client(Pair<String,String> pair, Map<String, String> relation) throws IOException {
        this.pair = pair;
        this.relation = relation;

    }

    public Client (String realIp){
        this.dispatcherPort = 6666;
        this.dispatcherRealIp = realIp;
    }

    boolean isLocal(String ip){
        boolean isLocal;
        String[] host = ip.split(".");
        if(host[1].equalsIgnoreCase("165")){
            isLocal = true;
        }
        else{
            isLocal = false;
        }
        return isLocal;
    }


    public void dispatcherClient(String myRealIp, Pair<String, String> address, int port) {
        String newMessage;
        newMessage = "1" + "\n " + address.getKey() + "\n" + myRealIp + "\n" + address.getValue() + "\n" + port;

        try {
            super.createSocket("client", this.dispatcherPort, this.dispatcherRealIp);
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(newMessage);
            this.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startClient2() throws IOException {
        try {
            outServer = new DataOutputStream(cs.getOutputStream());
                outServer.writeUTF("hola");
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

 /*   public void startClient(String message){
        try {
            String[] arrayMessage = message.split("\n");
            int action = Integer.parseInt(arrayMessage[2]);
            String answerMessage = "";
            switch (action){
                case 1:
                    //llamar al dispatcher
                    if(ipData != null) { //no soy yo
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '5' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    } else { //soy yo
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '3' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    break;
                case 2:
                    //llamar al dispatcher
                    if(ipData2 != null) { //si se la ruta
                        String routing = ipData2.getDestinyIp() + "\n" +  ipData2.getFakePath() + "\n" + ipData2.getDistance();
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '4' + "\n" + routing.length() + "\n" + routing;
                    }
                    else{ //mensaje de error, no conozco la ruta al mae
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '5' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    break;
                default: //caso 0
                    if(isLocal(arrayMessage[1])){
                        answerMessage = answerMessage + "\n" + this.pair.getValue() + "\n" + this.relation.get(arrayMessage[0]) + "\n";
                        //empaquetar fisico
                    }
                    break;
            }
            //IpData ipData = this.dispatcher.getData(arrayMessage[1]);
            super.createSocket("client",ipData.getPort(),ipData.getRealIp());
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(answerMessage);
            this.cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }*/

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