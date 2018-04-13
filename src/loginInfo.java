import java.io.Serializable;

// a class to contain the info the is used to login, it is only used for this
// use this classtologin
public class loginInfo implements Serializable {
    private String userName;
    private String password;

    public loginInfo(String _userName, String _password){
        userName=_userName;
        password=_password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
