import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread.*;
import java.sql.*;
/*
* @author   Vincent Venutolo, Jacob Feiner, Chris Bonsu, and Ian Anyala
* @version  1.0
* Client program implements an GUI application 
* for a client logging in & manage on a Dashboard
*/
public class Client extends JFrame {
   
   // JComponent Declarations
   private JFrame login;
   private JPanel loginPanel, loginFirstRow, loginSecondRow, loginThirdRow;
   private JTextField jtxtUsername;
   private JPasswordField jtxtPassword;
   private JButton loginButton;
   
   // Attribute Declarations
   private Socket cs = null;
   private ObjectInputStream inputStream = null;
   private ObjectOutputStream outputStream = null;
   private BufferedReader br;
   private PrintWriter pw;
   private boolean loginProcess = true;
   private String username;
   private UserInfo user;
   private LoginGUI loginGui;
   private Student studentGui;

   
   //private String password;
   
   //Attributes for Connection
   String url = "jdbc:mysql://localhost/Project_Database?autoReconnect=true&useSSL=false";
   String driver = "com.mysql.jdbc.Driver";
   //String user = "iste330t21";
   //String password = "ChrJacIanVin"; //My password is root on my laptop
   
 // String user = "root";
   String password = "student"; //My password is root on my laptop

   Connection conn = null;


   // Client Constructor
   public Client() {
      try {
            // Create socket to be connected to Server 
            //conn = DriverManager.getConnection(url, user, password); 
            cs = new Socket("localHost", 4242);
            System.out.println("Connected");
            
            if (loginProcess){
               LoginGUI login = new LoginGUI(cs,this);
            }
            
            // Process Login
            //clientLogin();
         
         }
         
         catch (SocketException se) {
            se.printStackTrace();
            System.exit(0);
         } catch (IOException e) {
            e.printStackTrace();
         }
         catch (Exception e){
            e.printStackTrace();
         }
   } // end Client Constructor

   // Main Method to start Client GUI
   public static void main(String[] args) {
      new Client();
   } // end Main Method
    // sets the userobject for this item to be passed to the new gui opend

    public void setUser(UserInfo user) {
        this.user = user;
    }
    // determine which gui to open based on the user type and does that
    public void OpenGui(){
        switch(user.getUserType().toLowerCase()){
            case "student": openStudent();
            loginGui.setVisible(false);
            loginGui.dispose();
            break;
            case "faculty": openFaculty();
            loginGui.setVisible(false);
            loginGui.dispose();
            break;
            case "staff": openStaff();
            loginGui.setVisible(false);
            loginGui.dispose();
            break;
            default: break;

        }
    }
    // methods to open the various guis
    public void openStudent(){
       studentGui=new Student();
    }
    public void openFaculty(){}
    public void openStaff(){}

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }
} // end Client Class