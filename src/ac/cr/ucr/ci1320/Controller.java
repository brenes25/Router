package ac.cr.ucr.ci1320;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.Thread;

/**
 * Created by josue on 06/11/17.
 */
public class Controller {
    private Map<String,String> oneToOneRelation;
    private String myIpAddress;
    private String myPhysicalAddress;

    public Controller(){
        this.oneToOneRelation = new HashMap<>();
        this.oneToOneRelation.put("165.8.0.6","CRR6");//josue
        this.oneToOneRelation.put("165.8.2.0","CRR2"); //mariana
        this.oneToOneRelation.put("165.8.48.2","CRR3"); //alonso
        //el propio se tiene como atributo
        this.myIpAddress = "165.8.25.6";
        this.myPhysicalAddress = "CRR4";
    }

    public void startController() throws IOException {
        Thread thread = new Thread(new ReadThread(new Server(new Pair<String,String>(this.myIpAddress,this.myPhysicalAddress), this.oneToOneRelation)));
        thread.start();
    }

    private void conectToDisptacher(String DispatcherRealIp, String myRealIp){
        Client client = new Client(DispatcherRealIp);
        Pair<String, String> address = new Pair<>(this.myIpAddress, this.myPhysicalAddress);
        client.dispatcherClient(myRealIp,address, 5555);
    }
}
