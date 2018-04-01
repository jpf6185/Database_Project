

public class CapstoneVersion{
    private String Date;
    private String CapstoneID;
    private String Status;
    private String Title;
    private String Description;
    private String FilePath;
    private String Type;

        public CapstoneVersion(String _CapstoneID,String _Date){
            Date=_Date;
            CapstoneID=_CapstoneID;
        }

    public CapstoneVersion(String date, String capstoneID, String status, String title, String description, String filePath,String type) {
        Date = date;
        CapstoneID = capstoneID;
        Status = status;
        Title = title;
        Description = description;
        FilePath = filePath;
        Type=type;
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
        return CapstoneID;
    }

    public void setCapstoneID(String capstoneID) {
        CapstoneID = capstoneID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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