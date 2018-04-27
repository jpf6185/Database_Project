import java.io.Serializable;

public class StatusInfo implements Serializable {
    private String StatusName;
    private int StatusNumber;

    public StatusInfo(String statusName, String statusNumber) {
        StatusName = statusName;
        StatusNumber = Integer.parseInt(statusNumber);
    }
    public StatusInfo(){}

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getStatusNumber() {
        return Integer.toString(StatusNumber);
    }

    public void setStatusNumber(String statusNumber) {
        StatusNumber = Integer.parseInt(statusNumber);
    }

    public String toString(){
        return this.getStatusName();
    }
}
