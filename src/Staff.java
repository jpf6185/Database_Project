import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

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
   private Object[][] tableData = null;
   
   private Client c;
   private UserInfo user;
   private ArrayList<CapstoneInfo> capstoneData = null;
   
   // Main Method
   public static void main(String [] args){}
   
   // Constructor
   public Staff(Client _client, UserInfo _user){
      
      // Getting all of the data
      this.c = _client;
      this.user = _user;
      capstoneData = c.getAllCapstoneData();
      fillTable(capstoneData);
      
      // North Panel Setup
      jpNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30,10));
      jlTitle = new JLabel("View Student Project Status", SwingConstants.LEFT);
      jpNorthPanel.add(jlTitle);
      add(jpNorthPanel, BorderLayout.NORTH);
      
      // Center Panel Setup
      jpCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
      jStaffTable = new JTable(tableData, columnNames);
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
   
   public Object[][] fillTable(ArrayList<CapstoneInfo> _capstoneData){
      
      tableData = new Object[4][_capstoneData.size()];
      
      String studentName;
      String projectTitle;
      String currentStatus;
      String date;
      
      // Begins iterating through all Capstone objects
      for(int i=0; i < _capstoneData.size(); i++){
         
         studentName = _capstoneData.get(i).getAuthor();
         projectTitle = _capstoneData.get(i).GetVersions().get(3).toString();
         currentStatus = _capstoneData.get(i).GetVersions().get(2).toString();
         date = _capstoneData.get(i).GetVersions().get(0).toString();
         
         tableData[1][i] = studentName;
         tableData[2][i] = projectTitle;
         tableData[3][i] = currentStatus;
         tableData[4][i] = date;
      }
      
      return tableData;
   }
} // end class
