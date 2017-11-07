package ac.cr.ucr.ci1320;

/**
 * Created by josue on 06/11/17.
 */
public class IpData {
    private String realIp;
    private String destinyIp;
    private String path;
    private int distance;

    public IpData(String newRealIp, String newDestinyIp, String newPath, int newDistance){
        this.realIp = newRealIp;
        this.destinyIp = newDestinyIp;
        this.path = newPath;
        this.distance = newDistance;
    }

    public String getRealIp() {
        return this.realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public String getDestinyIp() {
        return this.destinyIp;
    }

    public void setDestinyIp(String destinyIp) {
        this.destinyIp = destinyIp;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
