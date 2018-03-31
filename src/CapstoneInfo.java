import java.util.ArrayList;

public class CapstoneInfo{

    private String CapstoneID;
    private String LatestDate;
    private String PlagiarismScore;
    private String Grade;
    private String DefenseDate;
    public CapstoneInfo(String _CapstoneID){
        CapstoneID=_CapstoneID;
    }

    public String getCapstoneID() {
        return CapstoneID;
    }

    public void setCapstoneID(String capstoneID) {
        CapstoneID = capstoneID;
    }

    public String getLatestDate() {
        return LatestDate;
    }

    public void setLatestDate(String latestDate) {
        LatestDate = latestDate;
    }

    public String getPlagiarismScore() {
        return PlagiarismScore;
    }

    public void setPlagiarismScore(String plagiarismScore) {
        PlagiarismScore = plagiarismScore;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getDefenseDate() {
        return DefenseDate;
    }

    public void setDefenseDate(String defenseDate) {
        DefenseDate = defenseDate;
    }
}