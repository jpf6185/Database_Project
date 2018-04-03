/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */
import java.util.ArrayList;


/* This class is a contain to hold the info stored in the capstonInfo table, as well as to store all associated
* Capstone Version Objects, which represent entries in the Capstone Version Table
 */
public class CapstoneInfo{
    // fields of the Capstone_Info Table on
    private String CapstoneID;
    private String LatestDate;
    private String PlagiarismScore;
    private String Grade;
    private String DefenseDate;
    // Arraylist of Capstone Versions associeated with this paticular capstone;
    private ArrayList<CapstoneVersion> Versions;

    //default constructor;
    public CapstoneInfo(){
        Versions=new ArrayList<CapstoneVersion>();
    }
    // constructor with both the bare minum of info as well as one for all atributes
    public CapstoneInfo(String _CapstoneID) {
        CapstoneID = _CapstoneID;
        Versions=new ArrayList<CapstoneVersion>();
    }
    public CapstoneInfo(String capstoneID, String latestDate, String plagiarismScore, String grade, String defenseDate) {
        CapstoneID = capstoneID;
        LatestDate = latestDate;
        PlagiarismScore = plagiarismScore;
        Grade = grade;
        DefenseDate = defenseDate;
        Versions=new ArrayList<CapstoneVersion>();
    }
    // acessors and mutators
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
    public void addVersion(CapstoneVersion _Version){
        Versions.add(_Version);
    }

    public ArrayList<CapstoneVersion> GetVersions(){
        return Versions;
    }
}