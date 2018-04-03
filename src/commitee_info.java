/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */


import java.util.*;

/* This class is a container the stores the fields/values from an entry in the commite table
 */
public class commitee_info {
    //attributes
    private String userName;
    private String capStoneID;
    private String hasDecline;
    private String hasAccepted;
    private String position;
    private String Tracking;

    //default contructor

    public commitee_info(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCapStoneID(String capStoneID) {
        this.capStoneID = capStoneID;
    }

    public void setHasDecline(String hasDecline) {
        this.hasDecline = hasDecline;
    }

    public void setHasAccepted(String hasAccepted) {
        this.hasAccepted = hasAccepted;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTracking(String tracking) {
        Tracking = tracking;
    }

    public String getUserName() {
        return userName;
    }

    public String getCapStoneID() {
        return capStoneID;
    }

    public String getHasDecline() {
        return hasDecline;
    }

    public String getHasAccepted() {
        return hasAccepted;
    }

    public String getPosition() {
        return position;
    }

    public String getTracking() {
        return Tracking;
    }


}
