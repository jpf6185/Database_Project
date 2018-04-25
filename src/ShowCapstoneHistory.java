import java.awt.*;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.*;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class ShowCapstoneHistory extends JFrame  {
   //attributes
   private JTable capstoneHistory;
   CapstoneInfo capstone;
   
   private String [] columns = {
      "Project Title", "Status", "Date"
   };
   
   private String [] columns1 = {
       "Project Title", "Role", "Date"
   };
   
   
    //add rows to the table
   CapstoneInfo obj1 = new CapstoneInfo();
   private Object [][] rowData = {{}}; 

   
   DefaultTableModel model = new DefaultTableModel();
   

   
   //default constructor 
   public ShowCapstoneHistory(CapstoneInfo info)
   {
         capstone=info;

         // Committees table with data
      model = new DefaultTableModel(rowData, columns){
            @Override
            public boolean isCellEditable(int _arg0, int _arg1) { 
               return false; }
         };
      model.removeRow(0);
      capstoneHistory = new JTable();
      capstoneHistory.addMouseListener(
         new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent mouse) {
               if (mouse.getClickCount() == 2) {
                     //create an object from ProjectView class
                  ProjectView view = new ProjectView();
               }
            }
         });

      model.setColumnIdentifiers( columns);
      capstoneHistory.setModel(model);

   
         
          
         //create a View Committies GUI
      JPanel jpNorthRow = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
      JPanel jpBottomRow = new JPanel(new FlowLayout());
      JPanel jpFirstTabPanel = new JPanel(new FlowLayout());


      jpNorthRow.add(new JScrollPane(capstoneHistory));
      jpFirstTabPanel.add(jpNorthRow, BorderLayout.NORTH);
      add(jpFirstTabPanel);
      populateInfo();
      pack();
     // setJMenuBar(menuBar);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         
         
   }//end of project histor constructor

   private void populateInfo(){
      try{
         DefaultTableModel model=(DefaultTableModel)capstoneHistory.getModel();
         ArrayList<CapstoneVersion> versions=capstone.GetVersions();
         for(CapstoneVersion aVersion : versions){
             System.out.println(aVersion.getTitle());
            model.addRow(new String[]{aVersion.getTitle(),aVersion.getStatus(),aVersion.getDate()});
         }
      }
      catch (Exception e){
         e.printStackTrace();
         System.out.println("This message shold not display, this means teh castinf of the tablemodel to defaultablemodel in showcaspstonhistory failed");
      }
   }

   
   public void windowClosing(WindowEvent e) {
      dispose();
      System.exit(0);
   }

   
   public static void main(String [] args)
   {
      SwingUtilities.invokeLater(
         new Runnable(){
            public void run(){
               UIManager.put("swing.boldMetal", Boolean.FALSE);
            //createAndShowGUI();
               ShowCapstoneHistory gui = new ShowCapstoneHistory(new CapstoneInfo());
            }
         });
      
   }
   
}//end of faculty class