package ac.cr.ucr.ci1320.Connection;
import ac.cr.ucr.ci1320.IpData;
import javafx.util.Pair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public class Client extends Connection {

    private Pair<String,String> addressPair;
    private Map<String, String> relation;
    private int dispatcherPort;
    private String dispatcherRealIp;
    private Map<String, IpData> ipTable;

    public  Client(Pair<String,String> pair, Map<String, String> relation,Map<String,IpData> ipTable) throws IOException {
        this.addressPair = pair;
        this.relation = relation;
        this.ipTable = ipTable;
    }

    public Client (String realIp, Map<String, IpData> ipTable){
        this.ipTable = ipTable;
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



    public void startClient(String message){
        try {
            String[] arrayMessage = message.split("\n");
            int action = Integer.parseInt(arrayMessage[2]);
            String answerMessage = "";
            IpData ipData = this.ipTable.get(arrayMessage[3]);
            switch (action){
                case 1:
                    if(ipData == null) {
                        if (arrayMessage[3].contains("165")) {
                            answerMessage = this.addressPair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                    '3' + "\n" + this.addressPair.getKey() + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                        }
                    }
                    break;
                case 2:
                    if(ipData != null) { //si se la ruta
                        String routing = ipData.getFakeIp() + "\n" +  ipData.getFakePath() + "\n" + ipData.getDistance();
                        answerMessage = this.addressPair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '4' + "\n" + arrayMessage[4] + "\n" + arrayMessage[5] + routing.length() + "\n" + routing;
                    }
                    break;
                default: //caso 0
                    if(isLocal(arrayMessage[1])){
                        answerMessage = this.addressPair.getValue() + "\n" + this.relation.get(arrayMessage[0]) + "\n";
                        //empaquetar fisico
                    }
                    break;
            }
            if(ipData != null) {
                super.createSocket("client", ipData.getPort(), ipData.getRealIp());
            } else {
                super.createSocket("client",8888,"127.0.0.1");
            }
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