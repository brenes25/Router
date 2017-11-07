package ac.cr.ucr.ci1320;

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
