package ac.cr.ucr.ci1320;

/**
 * Created by josue on 06/11/17.
 */
public class IpData {
    private String realIp;
    private String destinyIp;
    private String path;
    private int distance;

    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public String getDestinyIp() {
        return destinyIp;
    }

    public void setDestinyIp(String destinyIp) {
        this.destinyIp = destinyIp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
