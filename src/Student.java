import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
* 04/06/2018
*/

public class Student extends JFrame implements ActionListener
{
   //Attributes
   private JTextArea jtextArea;
   
   private JButton jbEdit, jbCancel, jbView, jbImport, jbExport, jbDiscard;
   private JButton jbInvite;
   
   private JTextField jtfTypes;
   
   private JLabel jlDate;
   private JTextField jtfDate;
   
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
   
   private JPanel jpCenterPanel;
   private JPanel jpCenterFirstRow, jpCenterSecondRow;
   private JPanel jpCenterLeftPanel, jpCenterRightPanel;
   
   
   //private JLabel jlDate4;
   
   private JScrollPane jScrollPane1;
   
   
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
      jpNorthPanel = new JPanel(new GridLayout(0,2));
      jpCenterPanel = new JPanel(new GridLayout(2,0));
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
      
      jlDate = new JLabel("Student Name: ", SwingConstants.RIGHT);
      jtfDate = new JTextField(10);
      jpSecondRow.add(jlDate);
      jpSecondRow.add(jtfDate);
      
      
      jlProject = new JLabel("Project Title: ", SwingConstants.RIGHT);
      jtfProject = new JTextField(10);
      jpThirdRow.add(jlProject);
      jpThirdRow.add(jtfProject);
      
      
      jlDescription = new JLabel("Description: ", SwingConstants.RIGHT);
      jtfDescription = new JTextField(10);
      jpFourRow.add(jlDescription);
      jpFourRow.add(jtfDescription);
      
      
      jlPlagiarism = new JLabel("Plagiarism Score: ", SwingConstants.RIGHT);
      jtfPlagiarism = new JTextField(10);
      jpFiveRow.add(jlPlagiarism);
      jpFiveRow.add(jtfPlagiarism);
      
      
      jlGrade = new JLabel("Grade: ", SwingConstants.RIGHT);
      jtfGrade = new JTextField(10);
      jpSixRow.add(jlGrade);
      jpSixRow.add(jtfGrade);
      
      jlType = new JLabel("Type: ", SwingConstants.RIGHT);
      jtfType = new JTextField(10);
      jpSevenRow.add(jlType);
      jpSevenRow.add(jtfType);
      
      jlStatus = new JLabel("Status: ", SwingConstants.RIGHT);
      jtfStatus = new JTextField(10);
      jpEightRow.add(jlStatus);
      jpEightRow.add(jtfStatus);
      
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
      jbImport.addActionListener(this);
      
      jbExport = new JButton("Export");
      jpSecondBtnRow.add(jbExport);
      jbExport.addActionListener(this);
      
      jbEdit = new JButton("Edit");
      jpThirdBtnRow.add(jbEdit);
      jbEdit.addActionListener(this);
      
      jbDiscard = new JButton("Discard");
      jpFourBtnRow.add(jbDiscard);
      jbDiscard.addActionListener(this);
      
      jbView = new JButton("View History");
      jpFiveBtnRow.add(jbView);
      
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
      jcbRoleDropList.addActionListener(this);
      jpCenterLeftPanel.add(jlRole);
      jpCenterLeftPanel.add(jcbRoleDropList);
      
      jlEmail = new JLabel("Email Address: ", SwingConstants.RIGHT);
      jtfEmail = new JTextField(10);
      jpCenterLeftPanel.add(jlEmail);
      jpCenterLeftPanel.add(jtfEmail);
      
      jbInvite = new JButton("Send an Invitation");
      jbInvite.addActionListener(this);
      jpCenterRightPanel.add(jbInvite);
      
      jpCenterSecondRow.add(jpCenterLeftPanel, BorderLayout.WEST);
      jpCenterSecondRow.add(jpCenterRightPanel, BorderLayout.EAST);
      
      jpCenterPanel.add(jpCenterFirstRow);
      jpCenterPanel.add(jpCenterSecondRow);
      
      add(jpCenterPanel, BorderLayout.CENTER);
      
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
   
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
   
      //This will cancel the the text field
      if(choice == jbCancel)
      {
         System.exit(0);
      }
      
      else if(choice.equals(jbEdit))
      {
           
      }
      else if(choice.equals(jbView))
      {
         
      }
      else if(choice.equals(jbInvite))
      {
         
      }
   }
         
}//end class