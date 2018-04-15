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
   private JPanel loginPanel, loginFirstRow, loginSecondRow, loginThirdRow;
   private JTextField jtxtUsername;
   private JPasswordField jtxtPassword;
   private JButton loginButton;
   
   // Attribute Declarations
   private Socket cs = null;
   private ObjectInputStream inputStream = null;
   private ObjectOutputStream outputStream = null;
   private boolean loginProcess = true;
   private String username;
   //private String password;
   
   //Attributes for Connection
   String uri = "jdbc:mysql://localhost/Project_Database?autoReconnect=true&useSSL=false";
   String driver = "com.mysql.jdbc.Driver";
   //String user = "iste330t21";
   //String password = "ChrJacIanVin"; //My password is root on my laptop
   
   String user = "root";
   String password = "root"; //My password is root on my laptop

   Connection conn = null;


   // Client Constructor
   public Client() {
      try {
            conn = DriverManager.getConnection(uri, user, password); 
            cs = new Socket("localHost", 4242);
            System.out.println("Connected");
            
            //////////////////////////////////////////
            //////////// Login GUI Setup /////////////
            //////////////////////////////////////////
            // JPanel Setup
            loginPanel = new JPanel(new GridLayout(3,3));
            
            // First Row for Username input
            loginFirstRow = new JPanel(new FlowLayout());
            loginFirstRow.add(new JLabel("Username:", JLabel.LEFT));
            jtxtUsername = new JTextField(20);
            loginFirstRow.add(jtxtUsername);
            
            // Second Row for Password input
            loginSecondRow = new JPanel(new FlowLayout());
            loginSecondRow.add(new JLabel("Password:", JLabel.LEFT));
            jtxtPassword = new JPasswordField(20);
            loginSecondRow.add(jtxtPassword);
            
            // Third Row for Login Button
            loginThirdRow = new JPanel(new FlowLayout());
            loginButton = new JButton("Login");
               // Activates when Login button was clicked
               loginButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent evt) {
                   
                     username = jtxtUsername.getText();
                     password = new String(jtxtPassword.getPassword());
                     System.out.println("TEST");
                     loginProcess = false;
         
                  }
               }); // end ActionListener
               
            loginThirdRow.add(loginButton);
            
            // add JComponents to JFrame
            loginPanel.add(loginFirstRow, BorderLayout.NORTH);
            loginPanel.add(loginSecondRow, BorderLayout.NORTH);
            loginPanel.add(loginThirdRow, BorderLayout.NORTH);
            add(loginPanel, BorderLayout.CENTER);
            
            // JFrame Setup
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            /////////////////////////////////////////////////
            //////////// End of Login GUI Setup /////////////
            /////////////////////////////////////////////////
            
            // Process Login
            clientLogin();
         
         } catch (SocketException se) {
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
   
   // Login Process
   public void clientLogin(){
      try{
         ArrayList<String> loginData = new ArrayList<String>();
         while (loginProcess){
            try{
               Thread.sleep(1000);}
            catch(InterruptedException ie){
               System.out.println(ie.getMessage());}
         }
         System.out.println("Begins send info to server");
         loginData.add(username);
         loginData.add(password);
         // select * from people where username='abc1234@rit.edu' and password=md5('password');
         outputStream = new ObjectOutputStream(cs.getOutputStream());
         // Sends ArrayList Object to Server
         outputStream.writeObject(loginData);
         outputStream.close();
   
      } catch (SocketException se) {
         se.printStackTrace();
         System.exit(0);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
} // end Client Class