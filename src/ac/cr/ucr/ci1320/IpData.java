package ac.cr.ucr.ci1320;

/**
 * Created by josue on 06/11/17.
 */
public class IpData {
    private String realIp;
    private String fakeIp;
    private String fakePath;
    private int distance;
    private int port;

    public IpData(String realIp, String destinyIp, String fakePath, int distance,int port){
        this.realIp = realIp;
        this.fakeIp = destinyIp;
        this.fakePath = fakePath;
        this.distance = distance;
        this.port = port;
    }

    public String getRealIp() {
        return this.realIp;
    }

    public String getFakeIp() {
        return this.fakeIp;
    }

    public String getFakePath() {
        return this.fakePath;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getPort(){ return this.port; }
}
