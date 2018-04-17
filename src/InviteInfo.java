import java.io.Serializable;

/* a class to store the info about a faculties pending invites since it has a combination of information from booth the commite table and the capstone info and version table makaing it
*  a
*/
public class InviteInfo implements Serializable {
    // the fields in the display for the invite tabe
    private String StudentName;
    private String ProjectTitle;
    private String Role;
    private String ProjectDate;
    // the info needed to modify the datbase when the user either accepts or declines
    private String CapstoneID;
    private String StaffUserName;

    public InviteInfo(String studentName, String projectTitle, String role, String projectDate, String capstoneID, String staffUserName) {
        StudentName = studentName;
        ProjectTitle = projectTitle;
        Role = role;
        ProjectDate = projectDate;
        CapstoneID = capstoneID;
        StaffUserName = staffUserName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getProjectDate() {
        return ProjectDate;
    }

    public void setProjectDate(String projectDate) {
        ProjectDate = projectDate;
    }

    public String getCapstoneID() {
        return CapstoneID;
    }

    public void setCapstoneID(String capstoneID) {
        CapstoneID = capstoneID;
    }

    public String getStaffUserName() {
        return StaffUserName;
    }

    public void setStaffUserName(String staffUserName) {
        StaffUserName = staffUserName;
    }
}
