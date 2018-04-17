import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;

public class LoginGUI extends JFrame{

   // JComponent Declarations
   private JPanel jpLoginPanel, jpLoginFirstRow, jpLoginSecondRow, jpLoginThirdRow;
   private JPanel jpErrorPanel, jpErrorIcon, jpErrorTextModule;
   private JLabel jlErrorText;
   private JTextField jtxtUsername;
   private JPasswordField jtxtPassword;
   private JButton loginButton;
   private LineBorder redBorder = new LineBorder(new Color(204,0,0),1);
   
   // Attributes
   private Socket cs;
   private BufferedReader br;
   private PrintWriter pw;
   
   // Constant Attributes
   private static final int MAX_LENGTH = 7;
   // Default Constructor
   public LoginGUI(Socket _cs, BufferedReader _br, PrintWriter _pw){
   
      this.cs = _cs;
      this.br = _br;
      this.pw = _pw;
      //////////////////////////////////////////
      //////////// Login GUI Setup /////////////
      //////////////////////////////////////////
      // JPanel Setup
      jpLoginPanel = new JPanel(new GridLayout(3,3));
      
      // Error Message for showing Login Error Information
      jpErrorPanel = new JPanel(new GridLayout(1,2));
      jpErrorPanel.setBackground(new Color(255,204,204));
      jpErrorPanel.setForeground(new Color(204,0,0));
      jpErrorPanel.setBorder(redBorder);
      
      jpErrorIcon = new JPanel(new FlowLayout());
      jpErrorIcon.add(new JLabel(javax.swing.UIManager.getIcon("OptionPane.errorIcon")));
      jpErrorPanel.add(jpErrorIcon);
      
      jpErrorTextModule = new JPanel(new FlowLayout());
      jlErrorText = new JLabel();
      jpErrorTextModule.add(jlErrorText);
      
      jpErrorPanel.add(jpErrorIcon);
      jpErrorPanel.add(jpErrorTextModule);
      add(jpErrorPanel, BorderLayout.NORTH);
      jpErrorPanel.setVisible(false);
      
      // First Row for Username input
      jpLoginFirstRow = new JPanel(new FlowLayout());
      jpLoginFirstRow.add(new JLabel("Username:", JLabel.LEFT));
      jtxtUsername = new JTextField(20);
      jpLoginFirstRow.add(jtxtUsername);
      
      // Second Row for Password input
      jpLoginSecondRow = new JPanel(new FlowLayout());
      jpLoginSecondRow.add(new JLabel("Password:", JLabel.LEFT));
      jtxtPassword = new JPasswordField(20);
      jpLoginSecondRow.add(jtxtPassword);
      
      // Third Row for Login Button
      jpLoginThirdRow = new JPanel(new FlowLayout());
      loginButton = new JButton("Login");
      
      // Activates when Login button was clicked
      loginButton.addActionListener(new ManageLogin(this, cs, br, pw, jlErrorText, jtxtUsername, jtxtPassword));
         
      jpLoginThirdRow.add(loginButton);
      
      // add JComponents to JFrame
      jpLoginPanel.add(jpLoginFirstRow, BorderLayout.NORTH);
      jpLoginPanel.add(jpLoginSecondRow, BorderLayout.NORTH);
      jpLoginPanel.add(jpLoginThirdRow, BorderLayout.NORTH);
      add(jpLoginPanel, BorderLayout.CENTER);
      
      // JFrame Setup
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      /////////////////////////////////////////////////
      //////////// End of Login GUI Setup /////////////
      /////////////////////////////////////////////////
   
   } // end Default Constructor
   
      class ManageLogin implements ActionListener{
      
      // Attributes
      private LoginGUI window;
      private Socket cs;
      private BufferedReader br;
      private PrintWriter pw;
      private JLabel jlErrorText;
      private JTextField jtxtUsername;
      private JPasswordField jtxtPassword;
      
      public ManageLogin(LoginGUI _window, Socket _cs, BufferedReader _br, PrintWriter _pw, JLabel _jlErrorText, JTextField _jtxtUsername, JPasswordField _jtxtPassword){
         this.window = _window;
         this.cs = _cs;
         this.br = _br;
         this.pw = _pw;
         this.jlErrorText = _jlErrorText;
         this.jtxtUsername = _jtxtUsername;
         this.jtxtPassword = _jtxtPassword;
      }
      
      // communicateLogin Method
      public void communicateLogin(Object _objClientLogin){
         
         try{
         ObjectOutputStream outputStream;
         
         System.out.println("Begins comm");
         outputStream = new ObjectOutputStream(cs.getOutputStream());
         outputStream.writeObject("login");
         System.out.println("Sent login object");
         outputStream.flush();
         outputStream.writeObject(_objClientLogin);
         System.out.println("Sent UserInfo object");
         outputStream.flush();
         
         }
         catch (IOException ioe){
            System.out.println("LoginGUI()-communicateLogin Method Error Occurred:\n" + ioe.getMessage());
            
         }
      
      } // end communicateLogin
      
      // ActionPerformed Method for listening to Login Button
      @Override
      public void actionPerformed(ActionEvent ae){
         
         Boolean status = true;
         String currentUsername = jtxtUsername.getText();
         String currentPassword = new String(jtxtPassword.getPassword());
         
         
         Object choice = ae.getSource();
         if (status){
            
            // Username MUST BE LEAST 7 CHAR 
            if(status && !(currentUsername.length() == MAX_LENGTH)){
               status = showErrorMsg("Please enter username at least 7 characters.", 1);
            }
            // Password cannot be entered nothing
            if (status && (currentPassword == null)){
               status = showErrorMsg("Please enter your password.", 2);
            }
            // Checking if Login Credentials are found on database
            if (status){
                  // Create LoginInfo Object
                  LoginInfo objClientLogin = new LoginInfo(currentUsername,currentPassword);
                  communicateLogin(objClientLogin);
            }
         
         } // end Status if-else
         
      } // end actionPerformed Method
      
      
      // Show Error Message
      public boolean showErrorMsg(String msg, int cmd){
         
         jlErrorText.setText(msg);
         switch (cmd){
            case 1:
                  jtxtUsername.setBorder(redBorder);
                  break;
            case 2:
                  jtxtPassword.setBorder(redBorder);
                  break;
         }
         jpErrorPanel.setVisible(true);
         return false;
      } // end showErrorMsg Method
   } // end class ManageLogin
} // end class LoginGUI