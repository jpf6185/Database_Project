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
   private Faculty facultyGui;
   private Staff staffGui;

   
   //private String password;
   
   //Attributes for Connection
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
            case "graddirector": openStaff();
            break;
            default: break;

        }
    }
    // methods to open the various guis
    public void openStudent(){
       studentGui=new Student(this, this.getUser());
    }
    public void openFaculty(){
       facultyGui=new Faculty(this, user);
    }
    public void openStaff(){
      staffGui = new Staff(this, getUser());
    }

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

    // gets all capstone info
    public ArrayList<CapstoneInfo> getAllCapstoneData(){
      
      ArrayList<CapstoneInfo> capstoneData = null;
      try{
         outputStream.writeObject("getallcapstones");
         outputStream.flush();
         capstoneData = (ArrayList<CapstoneInfo>)inputStream.readObject();
         
         
      }
      catch (IOException ioe){
         ioe.printStackTrace();
         System.out.println("Error ioe @ Client class -> getAllCapstone() method");
      }
      catch (ClassNotFoundException cnfe){
         cnfe.printStackTrace();
         System.out.println("Error cnfe @ Client class -> getAllCapstone() method");
      }
      return capstoneData;
    }

    // gets capstone info
    public CapstoneInfo getCapstoneInfo(){
       try{
           outputStream.writeObject("getcommitees");
           outputStream.flush();
           outputStream.writeObject(user);
           outputStream.flush();
           user=(UserInfo)inputStream.readObject();
           String id = user.getCommitees().get(0).getCapStoneID();
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
       JOptionPane.showMessageDialog(null,"Error","An error has occured when trying to save the changes to the capstone",JOptionPane.ERROR_MESSAGE);
       return null;
    }
    public Vector<StatusInfo> getStatuses(){
       try{
            outputStream.writeObject("getstatuses");
            outputStream.flush();
            Vector<StatusInfo> res=(Vector<StatusInfo>)inputStream.readObject();
            return res;
       }
       catch (IOException ioe){
           ioe.printStackTrace();
           System.out.println("error when getting a list of all statuses");
       }
       catch (Exception e){
           e.printStackTrace();
           System.out.println("unexpected error when getting a list of all statuses");
       }
       return null;
    }
    // sends an invite to a facult
    public boolean sendInvite(commitee_info info){
        try{
            outputStream.writeObject("addInvite");
            outputStream.flush();
            info.setTracking("0");
            outputStream.writeObject(info);
            outputStream.flush();
            Boolean res=inputStream.readBoolean();
            return res;
        }
        catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("error when sending an invite a faculty member ot the committe");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("unexpected error when sending an invite to a faculty member ot the committe");
        }
        return false;
    }
    // method to get all the version of a capstone from a serverio
    public CapstoneInfo getCapstoneVersions(CapstoneInfo info){
        try{

            outputStream.writeObject("getcapstoneversions");
            outputStream.flush();
            outputStream.writeObject(new CapstoneInfo(info.getCapstoneID()));
            outputStream.flush();
            // reads and stores the resulta and then returns it
            CapstoneInfo res=(CapstoneInfo)inputStream.readObject();
            return res;

        }
        catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("error when getting all versions of a capstone from the server");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("unexpected error when getting all versions of a capstone from the server");
        }
        return null;
    }
    // method to read in a file and send it to the server
    public boolean UploadFile(CapstoneInfo capstone,UserInfo user){
       try{
           // gest teh soruce file path from the object provided
           File source=new File(capstone.GetVersions().get(0).getFilePath());
           // tells the server it wants to upload a file
           outputStream.writeObject("uploadfile");
           outputStream.flush();
           // writes out the username of the user uploading the file
           outputStream.writeObject(user.getUsername());
           outputStream.flush();
           // creates a byte array of the files to be uploaded to the server
           // casting could get into trouble since I am casting a long to a int but its unlikely as the filesize would have to be at lease over 250mb to cause problems
           byte[]fileBytes=new byte[(int)source.length()];
           // tells the server what length file to excepct
           outputStream.writeInt((int)source.length());
           outputStream.flush();
           // prepares the input stream to read the file in
           FileInputStream fis=new FileInputStream(source);
           BufferedInputStream fileIn=new BufferedInputStream(fis);
           // reads in the file
           fileIn.read(fileBytes,0,fileBytes.length);
           fileIn.close();
           // then sends the file
           outputStream.write(fileBytes,0,fileBytes.length);
           outputStream.flush();
           return true;
       }
       catch (IOException ioe){
           ioe.printStackTrace();
           System.out.println("error when uploading a file to the server");
       }
       catch (Exception e){
           e.printStackTrace();
           System.out.println("unexpected error when uploading a file to the server");
       }
       return false;
    }

    public ArrayList<CapstoneInfo> getCommiteeCapstones() {
        try {

        }
        catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("error when uploading a file to the server");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("unexpected error when uploading a file to the server");
        }
    }
} // end Client Class