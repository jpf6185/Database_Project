import java.util.*;
public class commitee_info {
    //attributes
    private String userName;
    private int capStoneID;
    private int hasDecline;
    private int hasAccepted;
    private String position;
    private int Tracking;

    //default contructor

    public commitee_info(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCapStoneID(int capStoneID) {
        this.capStoneID = capStoneID;
    }

    public void setHasDecline(int hasDecline) {
        this.hasDecline = hasDecline;
    }

    public void setHasAccepted(int hasAccepted) {
        this.hasAccepted = hasAccepted;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTracking(int tracking) {
        Tracking = tracking;
    }

    public String getUserName() {
        return userName;
    }

    public int getCapStoneID() {
        return capStoneID;
    }

    public int getHasDecline() {
        return hasDecline;
    }

    public int getHasAccepted() {
        return hasAccepted;
    }

    public String getPosition() {
        return position;
    }

    public int getTracking() {
        return Tracking;
    }


}
