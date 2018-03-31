import java.util.*;

public class user_info
{

    private String userName;
    private String email;
    private int userType;
    private String name;
    private String password;
    private String phoneNumber;
    private String department;
    private ArrayList<commitee_info> Commitees;
    //default constructor
    public user_info()
    {
        Commitees=new ArrayList<commitee_info>();

    }//end of constructor

    /*
    *Create constructor for user
    * /@ param String userName;String email;
        int userType;
        String name;
        String password;
        String phoneNumber;
        String department;
     */
    public user_info(String userName, String email, int userType, String name, String password, String phoneNumber, String department, ArrayList<commitee_info> Commitees)
    {

        this.userName = userName;
        this.email = email;
        this.userType = userType;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.Commitees = Commitees;
    }//end of contructor for user

    /*
    *create mutator for username
    *
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /*
     *create mutator for email
     *
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /*
     *create mutator for userType
     *
     */
    public void setUserType(int  userType)
    {
        this.userType = userType;
    }

    /*
     *create mutator for name
     *
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /*
     *create mutator for username
     *
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /*
     *create mutator for username
     *
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /*
     *create mutator for username
     *
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }

    public void addCommitees(commitee_info commitees) {
        Commitees.add(commitees);
    }
    /*
     *create acessor method for getUsername
     *@return userName
     */
    public String getUsername()
    {
        return userName;
    }

    /*
     *create acessor method for getEmail
     *@return email
     */
    public String getEmail()
    {
        return email;
    }

    /*
     *create acessor method for getUserType
     *@return userType
     */
    public int getUserType()
    {
        return userType;
    }

    /*
     *create acessor method for getName
     *@return name
     */
    public String getName()
    {
        return name;
    }

    /*
     *create acessor method for getPassword
     *@return password
     */
    public String getPassword()
    {
        return password;
    }

    /*
     *create acessor method for getPhoneNumber
     *@return phoneNumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /*
     *create acessor method for getDepartment
     *@return department
     */
    public String getDepartment()
    {
        return department;
    }

    public ArrayList<commitee_info> getCommitees() {
        return Commitees;
    }
}