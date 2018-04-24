import java.io.Serializable;

public class StatusInfo implements Serializable {
    private String StatusName;
    private String StatusNumber;

    public StatusInfo(String statusName, String statusNumber) {
        StatusName = statusName;
        StatusNumber = statusNumber;
    }
    public StatusInfo(){}

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getStatusNumber() {
        return StatusNumber;
    }

    public void setStatusNumber(String statusNumber) {
        StatusNumber = statusNumber;
    }

    public String toString(){
        return this.getStatusName();
    }
}
