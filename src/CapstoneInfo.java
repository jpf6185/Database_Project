/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

/* This class is a contain to hold the info stored in the capstonInfo table, as well as to store all associated
* Capstone Version Objects, which represent entries in the Capstone Version Table
 */
public class CapstoneInfo implements Serializable {
    // fields of the Capstone_Info Table on
    private int CapstoneID;
    private String LatestDate;
    private int PlagiarismScore;
    private int Grade;
    private String DefenseDate;
    // the full name of the user who wrote the capstone gotten from the user table using some sql
    private String Author;
    // Arraylist of Capstone Versions associeated with this paticular capstone;
    private ArrayList<CapstoneVersion> Versions;

    //default constructor;
    public CapstoneInfo(){
        Versions=new ArrayList<CapstoneVersion>();
    }
    // constructor with both the bare minum of info as well as one for all atributes
    public CapstoneInfo(String _CapstoneID) {
        CapstoneID = Integer.parseInt(_CapstoneID);
        Versions=new ArrayList<CapstoneVersion>();
    }

    // acessors and mutators
    public String getCapstoneID() {

        return Integer.toString(CapstoneID);
    }

    public void setCapstoneID(String capstoneID) {

        CapstoneID = Integer.parseInt(capstoneID);
    }

    public void setAuthor(String author){

        Author=author;
    }
    public String getAuthor(){
        return
                Author;
    }
    public String getLatestDate() {

        return LatestDate;
    }

    public void setLatestDate(String latestDate) {

        LatestDate = latestDate;
    }

    public String getPlagiarismScore() {

        return Integer.toString(PlagiarismScore);
    }

    public void setPlagiarismScore(String plagiarismScore) {

        try {
            PlagiarismScore = Integer.parseInt(plagiarismScore);
        }
        catch (NumberFormatException e){
            PlagiarismScore = 0;
        }
    }

    public String getGrade() {

        return Integer.toString(Grade);
    }

    public void setGrade(String grade) {
        try {
            Grade = Integer.parseInt(grade);
        }
        catch (NumberFormatException e){
            Grade = 0;
        }
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

    // returns a vector of certian values for constructing faculthy GUI
    public Vector<String> getFacultyGuiInfo(){
        Vector<String> capstoneSummary=new Vector<String>();
        capstoneSummary.add(getAuthor());
        // if the data has been added to the array properly
        if(Versions.size()>0){
            capstoneSummary.add(Versions.get(Versions.size() - 1).getTitle());
            capstoneSummary.add(Versions.get(Versions.size() - 1).getStatusName());
            capstoneSummary.add(Versions.get(Versions.size() - 1).getDate());
        }
        // adds blank string to prevent any error from wrong length of versions
        else{
            capstoneSummary.add("");
            capstoneSummary.add("");
            capstoneSummary.add("");
        }
        return capstoneSummary;

    }
}