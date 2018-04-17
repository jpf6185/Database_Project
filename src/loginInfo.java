import java.io.*;
// a class to contain the info the is used to login, it is only used for this
// use this classtologin
public class LoginInfo implements Serializable{
    
    // Attributes
    private String userName;
    private String password;

    // Default Constructor
    public LoginInfo(){
    
    }
    // Constructor with two parameters
    public LoginInfo(String _userName, String _password){
        userName=_userName;
        password=_password;
        
        
    }

    // getUserName Accessor
    public String getUserName() {
        return userName;
    }
    
    
    // getPassword Accessor
    public String getPassword() {
        return password;
    }
}