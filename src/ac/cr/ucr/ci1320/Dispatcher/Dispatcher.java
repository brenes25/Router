package ac.cr.ucr.ci1320.Dispatcher;

import ac.cr.ucr.ci1320.IpData;

import java.util.Map;

/**
 * Created by josue on 06/11/17.
 */
public class Dispatcher {
    private Map<String,IpData> ipTable;

    public Dispatcher(){
        this.ipTable.put("12.0.0.0",new IpData("192.168.100.16", "12.0.0.8", "12.0.0.7", 0));//banderas
        this.ipTable.put("200.5.0.0",new IpData("192.168.100.16", "200.5.0.2", "12.0.0.7",1));//paletas
        this.ipTable.put("140.90.0.0",new IpData("192.168.100.16", "12.0.0.8", "12.0.0.7", 2));//bolinchas
        this.ipTable.put("201.6.0.0",new IpData("192.168.100.16", "12.0.0.8", "165.8.0.6", 1));//legos
        this.ipTable.put("25.0.0.0",new IpData("192.168.100.16", "12.0.0.8", "165.8.0.6", 0));//luces
    }

    public IpData getData(String key){
        return this.ipTable.get(key);
    }
}
