import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Staff extends JFrame implements ActionListener{

   // JComponent Attributes
   private JPanel jpNorthPanel, jpCenterPanel, jpSouthPanel;
   private JLabel jlTitle;
   private JTable jStaffTable;
   private JButton jbOpenProjectMgmt;
   
   // JComponent ScrollPane
   private JScrollPane jScrollPane;
   
   // Attributes
   private String[] columnNames = {"Student Name","Project Title", "Current Status","Date"};
   private Object[][] dataStatus ={
   {"Vincent Venutolo", "Test Name", "Approved", "04-23-18"},
   {"Jacob Feiner", "Test Name", "Approved", "04-23-18"},
   {"Ian Anyala", "Test Name", "Approved", "04-23-18"},
   {"Chris Bonsu", "Test Name", "Approved", "04-23-18"},
   {"Vincent Venutolo", "Test Name", "Approved", "04-23-18"},
   {"Vincent Venutolo", "Test Name", "Approved", "04-23-18"}};
   
   // Main Method
   public static void main(String [] args){
      Staff gui = new Staff();
   }
   
   // Constructor
   public Staff(){
      
      // North Panel Setup
      jpNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30,10));
      jlTitle = new JLabel("View Student Project Status", SwingConstants.LEFT);
      jpNorthPanel.add(jlTitle);
      add(jpNorthPanel, BorderLayout.NORTH);
      
      // Center Panel Setup
      jpCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
      jStaffTable = new JTable(dataStatus, columnNames);
      jScrollPane = new JScrollPane(jStaffTable);
      jStaffTable.setFillsViewportHeight(true);
      
      jpCenterPanel.add(jScrollPane, BorderLayout.CENTER);
      add(jpCenterPanel, BorderLayout.CENTER);
      
      // South Panel Setup
      jpSouthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      jbOpenProjectMgmt = new JButton("Open Project Management");
      jbOpenProjectMgmt.addActionListener(this);
      
      jpSouthPanel.add(jbOpenProjectMgmt, BorderLayout.EAST);
      add(jpSouthPanel, BorderLayout.SOUTH);
      
      
      // GUI Layout Setup
      pack();
      //Color c = new Color(0,0,0,100);
      //jtextArea.setBackground(c);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   @Override
   public void actionPerformed(ActionEvent ae){
      Object choice = ae.getSource();
   }
   
   public void openProjectManagementWindow(){
      
   }
   
} // end class
