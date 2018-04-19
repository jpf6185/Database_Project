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
   private UserInfo
   
   //private String password;
   
   //Attributes for Connection
   String url = "jdbc:mysql://localhost/Project_Database?autoReconnect=true&useSSL=false";
   String driver = "com.mysql.jdbc.Driver";
   //String user = "iste330t21";
   //String password = "ChrJacIanVin"; //My password is root on my laptop
   
   String user = "root";
   String password = "student"; //My password is root on my laptop

   Connection conn = null;


   // Client Constructor
   public Client() {
      try {
            // Create socket to be connected to Server 
            //conn = DriverManager.getConnection(url, user, password); 
            cs = new Socket("localHost", 4242);
            System.out.println("Connected");
            br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            pw = new PrintWriter(cs.getOutputStream(), true);
            
            if (loginProcess){
               LoginGUI openLoginGUI = new LoginGUI(cs, br, pw);
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
   } // end Client Constructor

   // Main Method to start Client GUI
   public static void main(String[] args) {
      new Client();
   } // end Main Method
   
   
} // end Client Class