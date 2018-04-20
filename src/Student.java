import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
* 04/06/2018

*/

public class Student extends JFrame //implements ActionListener
{
   //Attributes
   private JTextArea jtextArea;
   
   private JButton jbEdit, jbCancel, jbView, jbImport, jbExport, jbDiscard;
   private JButton jbInvite;
   
   private JTextField jtfTypes;
   
   private JLabel jlDate;
   private JTextField jtfDate;
   
   private JLabel jlName;
   private JTextField jtfName;
   
   private JLabel jlProject;
   private JTextField jtfProject;
   
   private JLabel jlDescription;
   private JTextField jtfDescription;
   
   private JLabel jlPlagiarism;
   private JTextField jtfPlagiarism;
   
   private JLabel jlGrade;
   private JTextField jtfGrade;
   
   private JLabel jlType;
   private JTextField jtfType;
   
   private JLabel jlStatus;
   private JTextField jtfStatus;
   
   private JLabel jlCurrentFaculty;
   private JTextField jtfCurrentFaculty;
   
   private JLabel jlRole;
   private String[] roleList = { "Student", "Faculty", "Staff" };
   
   private JLabel jlEmail;
   private JTextField jtfEmail;
   
   private JPanel jpNorthPanel, jpSouthPanel, jpLeftPanel, jpRightPanel;
   private JPanel jpFirstRow, jpSecondRow, jpThirdRow, jpFourRow, jpFiveRow, jpSixRow, jpSevenRow, jpEightRow;
   private JPanel jpFirstBtnRow, jpSecondBtnRow, jpThirdBtnRow, jpFourBtnRow, jpFiveBtnRow;
   
   private JPanel jpCenterPanel,jpPaddedCenterPanel;
   private JPanel jpCenterFirstRow, jpCenterSecondRow;
   private JPanel jpCenterLeftPanel, jpCenterRightPanel;
   
   
   //private JLabel jlDate4;
   
   private JScrollPane jScrollPane1;
   
   //CapstoneInfo Object
   CapstoneInfo capstoneInfos;
    
   //private JTextField jtfDate4;

   public static void main(String [] args)
   {
      Student gui = new Student(); 
   }//end main
   
   //Default Constructor
   public Student()
   {   
      //This will show at the top Title 
      setTitle("Student Information");
   
      // JPanel Setup
      jpNorthPanel = new JPanel(new GridLayout(0,2,30,30));
      jpCenterPanel = new JPanel(new GridLayout(2,0,30,30));
      jpPaddedCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30,30));
      add(jpNorthPanel, BorderLayout.NORTH);
      
      jpLeftPanel = new JPanel(new GridLayout(8,0));
      jpRightPanel = new JPanel(new GridLayout(5,0));
      
      jpCenterFirstRow = new JPanel(new GridLayout(2,2));
      jpCenterSecondRow = new JPanel(new GridLayout(0,2));
      jpCenterLeftPanel = new JPanel(new GridLayout(2,2));
      jpCenterRightPanel = new JPanel(new FlowLayout());
      
      // JPanel Setup for TextFields
      jpFirstRow = new JPanel(new GridLayout(0,2));
      jpSecondRow = new JPanel(new GridLayout(0,2));
      jpThirdRow = new JPanel(new GridLayout(0,2));
      jpFourRow = new JPanel(new GridLayout(0,2));
      jpFiveRow = new JPanel(new GridLayout(0,2));
      jpSixRow = new JPanel(new GridLayout(0,2));
      jpSevenRow = new JPanel(new GridLayout(0,2));
      jpEightRow = new JPanel(new GridLayout(0,2));
      
      // JPanel Setup for Buttons
      jpFirstBtnRow = new JPanel(new FlowLayout());
      jpSecondBtnRow = new JPanel(new FlowLayout());
      jpThirdBtnRow = new JPanel(new FlowLayout());
      jpFourBtnRow = new JPanel(new FlowLayout());
      jpFiveBtnRow = new JPanel(new FlowLayout());
      
      // Project Details Left Panel Setup
      jlDate = new JLabel("Date: ", SwingConstants.RIGHT);
      jtfDate = new JTextField(10);
      
      jpFirstRow.add(jlDate);
      jpFirstRow.add(jtfDate);
      jtfDate.setEditable(false);
      
      jlName = new JLabel("Student Name: ", SwingConstants.RIGHT);
      jtfName = new JTextField(10);
      jpSecondRow.add(jlDate);
      jpSecondRow.add(jtfDate);
      jtfName.setEditable(false);
      
      
      jlProject = new JLabel("Project Title: ", SwingConstants.RIGHT);
      jtfProject = new JTextField(10);
      jpThirdRow.add(jlProject);
      jpThirdRow.add(jtfProject);
      jtfProject.setEditable(false);
      
      jlDescription = new JLabel("Description: ", SwingConstants.RIGHT);
      jtfDescription = new JTextField(10);
      jpFourRow.add(jlDescription);
      jpFourRow.add(jtfDescription);
      jtfDescription.setEditable(false);
      
      
      jlPlagiarism = new JLabel("Plagiarism Score: ", SwingConstants.RIGHT);
      jtfPlagiarism = new JTextField(10);
      jpFiveRow.add(jlPlagiarism);
      jpFiveRow.add(jtfPlagiarism);
      jtfPlagiarism.setEditable(false);
      
      
      jlGrade = new JLabel("Grade: ", SwingConstants.RIGHT);
      jtfGrade = new JTextField(10);
      jpSixRow.add(jlGrade);
      jpSixRow.add(jtfGrade);
      jtfGrade.setEditable(false);
      
      jlType = new JLabel("Type: ", SwingConstants.RIGHT);
      jtfType = new JTextField(10);
      jpSevenRow.add(jlType);
      jpSevenRow.add(jtfType);
      jtfType.setEditable(false);
      
      jlStatus = new JLabel("Status: ", SwingConstants.RIGHT);
      jtfStatus = new JTextField(10);
      jpEightRow.add(jlStatus);
      jpEightRow.add(jtfStatus);
      jtfStatus.setEditable(false);
      
      jpLeftPanel.add(jpFirstRow);
      jpLeftPanel.add(jpSecondRow);
      jpLeftPanel.add(jpThirdRow);
      jpLeftPanel.add(jpFourRow);
      jpLeftPanel.add(jpFiveRow);
      jpLeftPanel.add(jpSixRow);
      jpLeftPanel.add(jpSevenRow);
      jpLeftPanel.add(jpEightRow);
      
      jpNorthPanel.add(jpLeftPanel, BorderLayout.WEST);
      
      // Button Right Panel Setup
      jbImport = new JButton("Import");
      jpFirstBtnRow.add(jbImport);
      jbImport.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
            
            }
         });
      
      jbExport = new JButton("Export");
      jpSecondBtnRow.add(jbExport);
      jbExport.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
            
            }
         });
      
      jbEdit = new JButton("Edit");
      jpThirdBtnRow.add(jbEdit);
      jbEdit.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               jtfDate.setEditable(true);
               jtfName.setEditable(true);
               jtfProject.setEditable(true);
               jtfDescription.setEditable(true);
               jtfPlagiarism.setEditable(true);
               jtfGrade.setEditable(true);
               jtfType.setEditable(true);
               jtfStatus.setEditable(true);
               System.out.print("test");
            }
         });
      
      jbDiscard = new JButton("Discard");
      jpFourBtnRow.add(jbDiscard);
      jbDiscard.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               //UndoManager manager = new UndoManager();
               //Document document = textField.getDocument();
               //document.addUndoableEditListener(manager);
            }
         });
      
      jbView = new JButton("View History");
      jpFiveBtnRow.add(jbView);
      jbView.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               JOptionPane.showMessageDialog(null, jtfDate.getText() + " " + jtfProject.getText() + " " + jtfStatus.getText());
            }
         });
      
      jpRightPanel.add(jpFirstBtnRow);
      jpRightPanel.add(jpSecondBtnRow);
      jpRightPanel.add(jpThirdBtnRow);
      jpRightPanel.add(jpFourBtnRow);
      jpRightPanel.add(jpFiveBtnRow);
      
      jpNorthPanel.add(jpRightPanel, BorderLayout.EAST);
      
      
      // Currently Faculty Member Panel Setup
      jlCurrentFaculty = new JLabel("Currently Invited Faculty Members: ", SwingConstants.LEFT);
      
      jtfCurrentFaculty = new JTextField(10);
      jpCenterFirstRow.add(jtfCurrentFaculty, 1,0);
      jpCenterFirstRow.add((new JLabel(" ")), 0,1);
      jpCenterFirstRow.add(jlCurrentFaculty, 0,0);
      jpCenterFirstRow.add((new JLabel(" ")), 1,1);
      
      // Send Invitation Panel Setup
      jlRole = new JLabel("Roles: ", SwingConstants.RIGHT);
      JComboBox jcbRoleDropList = new JComboBox(roleList);
      jcbRoleDropList.setSelectedIndex(2);
      jcbRoleDropList.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
            
            }
         });
      jpCenterLeftPanel.add(jlRole);
      jpCenterLeftPanel.add(jcbRoleDropList);
      
      jlEmail = new JLabel("Email Address: ", SwingConstants.RIGHT);
      jtfEmail = new JTextField(10);
      jpCenterLeftPanel.add(jlEmail);
      jpCenterLeftPanel.add(jtfEmail);
      
      jbInvite = new JButton("Send an Invitation");
      jbInvite.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
            
            }
         });
   
      jpCenterRightPanel.add(jbInvite);
      
      jpCenterSecondRow.add(jpCenterLeftPanel, BorderLayout.WEST);
      jpCenterSecondRow.add(jpCenterRightPanel, BorderLayout.EAST);
      
      jpCenterPanel.add(jpCenterFirstRow);
      jpCenterPanel.add(jpCenterSecondRow);
      jpPaddedCenterPanel.add(jpCenterPanel);
      add(jpPaddedCenterPanel, BorderLayout.CENTER);
      
      // JPanel jpFirst = new JPanel(new FlowLayout());
   //       
   //       jtfType = new JTextField(20);
   //       jpFirst.add(jtfType);
   //       jtfType.setText("Name, Email");
   //       jtfType.addActionListener(this);
   //       
   //       
   //       jbCancel = new JButton("Cancel");
   //       jpFirst.add(jbCancel);
   //       jbCancel.addActionListener(this);
   //       
   //       add(jpFirst, BorderLayout.SOUTH);
     
            
      pack();
      //Color c = new Color(0,0,0,100);
      //jtextArea.setBackground(c);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   private String capstoneInfo;
   private String date;
   private String name;
   private String project;
   private String description;
   private String plagiarism;
   private String grade;
   private String type;
   private String status;
   
   /*
   public Student(String _capstoneInfo, String _date, String _name, String _project, String _description, String _plagiarism, String _grade, String _type, String _status)
   {
      capstoneInfo = _capstoneInfo;
      date = _date;
      name = _name;
      project = _project;
      description = _description;
      plagiarism = _plagiarism;
      grade = _grade;
      type = _type;
      status = _status; 
   }
   */
   
   public Boolean studentUpdate(CapstoneInfo inObj)
   {
     //CapstoneVersion inObj2 = inObj.GetVersion().get(0);
     return true;
   }
   
   /*
   public Student(CapstoneInfo _cap, JTextField _jtfDate, JTextField _jtfName, JTextField _jtfProject, JTextField _jtfDescription, JTextField _jtfPlagiarism, JTextField _jtfGrade, JTextField _jtfType, JTextField _jtfStatus)
   {
      capstoneInfo = _cap;
      jtfDate = _jtfDate;
      jtfName = _jtfName;
      jtfProject = _jtfProject;
      jtfDescription = _jtfDescription;
      jtfPlagiarism = _jtfPlagiarism;
      jtfGrade = _jtfGrade;
      jtfType = _jtfType;
      jtfStatus = _jtfStatus; 
   }
   */
  
   
   
   
   /*
   //@Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
   
      //This will cancel the the text field
      if(choice.equals(jbImport))
      {
        
         //System.exit(0);
      }
      else if(choice.equals(jbExport))
      {
      
      }
      else if(choice.equals(jbEdit))
      {
       
      }
      else if(choice.equals(jbDiscard))
      {
         //jtfItemName.setText("");
      }
      else if(choice.equals(jbView))
      {
         
      }
      else if(choice.equals(jbInvite))
      {
      
      }
   }
   */
   
   class ManageInvite implements ActionListener
   {
      //Attributes
      private Student form;
      private Socket cs;
      private BufferedReader br;
      private PrintWriter pw;
      public void actionPerformed(ActionEvent ae)
      { 
         if(ae.getActionCommand().equals("Send an Invitation"))
         {
         
         }
      }
   }
   
   public class UndoManager
   {
   
   }
         
}//end class