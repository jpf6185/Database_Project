/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Ventutolo
 */

import java.io.Serializable;

/* This class is a container the stores the fields/values from an entry in the capstoneVersion Table
*
 */
public class CapstoneVersion implements Serializable {
    private String Date;
    private int CapstoneID;
    private String StatusName;
    private String Title;
    private String Description;
    private String FilePath;
    private String Type;
    private int StatusCode;
// constructor with the bare
        public CapstoneVersion(String _CapstoneID,String _Date){
            Date=_Date;
            CapstoneID=Integer.parseInt(_CapstoneID);
        }
        public CapstoneVersion(){

        }
    public CapstoneVersion(String date, String capstoneID, String status, String title, String description, String filePath,String type,String statusCode) {
        Date = date;
        CapstoneID = Integer.parseInt(capstoneID);
        StatusName = status;
        Title = title;
        Description = description;
        FilePath = filePath;
        Type=type;
        StatusCode=Integer.parseInt(statusCode);
    }

    public String getStatusCode() {
        return Integer.toString(StatusCode);
    }

    public void setStatusCode(String statusCode) {
        StatusCode = Integer.parseInt(statusCode);
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCapstoneID() {
        return Integer.toString(CapstoneID);
    }

    public void setCapstoneID(String capstoneID) {
        CapstoneID = Integer.parseInt(capstoneID);
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String status) {
        StatusName = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}