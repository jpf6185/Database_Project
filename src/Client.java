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
               loginGui = new LoginGUI(cs,this);
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
       studentGui=new Student(this);
    }
    public void openFaculty(){}
    public void openStaff(){}

    // getters and setters


    public UserInfo getUser() {
        return user;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public CapstoneInfo getCapstoneInfo(){
       try{
           outputStream.writeObject("getcommitees");
           outputStream.flush();
           outputStream.writeObject(user);
           outputStream.flush();
           user=(UserInfo)inputStream.readObject();
           String id=user.getCommitees().get(0).getCapStoneID();
           CapstoneInfo cap=new CapstoneInfo();
           cap.setCapstoneID(id);
           outputStream.writeObject("getcapstoneinfo");
           outputStream.flush();
           outputStream.writeObject(cap);
           outputStream.flush();
           cap=(CapstoneInfo)inputStream.readObject();
           return cap;
       }
       catch (IOException ioe){
           ioe.printStackTrace();
           System.out.println("error when getting capstone info");
       }
       catch (Exception e){
           e.printStackTrace();
           System.out.println("unexpected error when getting capstone info");
       }
       return null;
    }
    public CapstoneInfo saveCapstone(CapstoneInfo info){
       try{
           outputStream.writeObject("updatecapstone");
           outputStream.flush();
           outputStream.writeObject(info);
           outputStream.flush();
           info=(CapstoneInfo)inputStream.readObject();
           return info;
       }
       catch (IOException ioe){
           ioe.printStackTrace();
           System.out.println("error when updating capstone info");
       }
       catch (Exception e){
           e.printStackTrace();
           System.out.println("unexpected error when updating capstone info");
       }
       return null;
    }
} // end Client Class