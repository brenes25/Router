package ac.cr.ucr.ci1320;

import java.util.Map;
import java.lang.Thread;

/**
 * Created by josue on 06/11/17.
 */
public class Controller {
    private Map<String,String> oneToOneRelation;
    Thread thread = new Thread(new ReadThread());


}
