package ac.cr.ucr.ci1320.Dispatcher;

import ac.cr.ucr.ci1320.IpData;

import java.util.Map;

/**
 * Created by josue on 06/11/17.
 */
public class Dispatcher {
    private Map<String,IpData> ipTable;

    public Dispatcher(){}

    public IpData getData(String key){
        return this.ipTable.get(key);
    }
}
