import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread.*;
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
   ObjectInputStream objectInput = null;
   ObjectOutputStream objectOutput = null;
   loginInfo obj = null;
   private boolean loginProcess = true;
   private String username;
   private String password;

   // Client Constructor
   public Client() {
      try {
         cs = new Socket("localhost", 4242);
            //PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
         
         ObjectInputStream objectInput = new ObjectInputStream(cs.getInputStream());
            //ObjectOutputStream objectOutput = new ObjectOutputStream();
         ObjectOutputStream objectOutput = new ObjectOutputStream(cs.getOutputStream());
            //ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("object.data"));
            
            //Client o
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
         loginButton.addActionListener(
            new ActionListener() {
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
         cs.close();
         
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
         while (loginProcess){
            try{
               Thread.sleep(1000);}
            catch(InterruptedException ie){
               System.out.println(ie.getMessage());}
         }
         System.out.println("Begins send info to server");
         
         // create a Logininfo object using the usename and password in the text field
         loginInfo obj = new loginInfo(username, password);
         // sends the string login to the server using objectoutputstream.writeObject() and then flushes it
         objectOutput.writeObject(obj);
         objectOutput.flush();
         // sends the loginfo object using the objectoutputstream and then flushes the stream
         // reads in an object using the objectinputstream and converts it to string, this will be a string
         System.out.println("" + (String) objectInput.readObject());
         // that tells you wheter or not you loged in, print the string,
      } 
      catch (SocketException se) {
         se.printStackTrace();
         System.exit(0);
      } catch (IOException e) {
         e.printStackTrace();
      }catch(ClassNotFoundException cnfe){
         cnfe.printStackTrace();
      }
   }
} // end Client Class