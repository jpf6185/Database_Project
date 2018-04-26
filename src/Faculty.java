import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.Properties;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList.*;
import java.util.*;



public class Faculty extends JFrame
{
   //attributes
   private JButton jbView;
   private JMenuBar menuBar;
   private JMenu menu, menu1, menu2;
   private JMenuItem jMenuItem,jMenuItem2,jMenuItem3;
   private JButton btOpenDetails, btTrack, btApprove, btDecline;
   private JTable committeesTable, trackingTable, inviteTable;
   
   private Object [] columns = {
      "StudentName", "Project Title", "Current Status", "Date"
   };
   
   private Object[][] rowData;
   private Client C;
   private UserInfo user;
   
   DefaultTableModel model1 = new DefaultTableModel();
   DefaultTableModel model2 = new DefaultTableModel();
   DefaultTableModel model3 = new DefaultTableModel();

   //default constructor 
   public Faculty(Client c, UserInfo user)
   {
         this.C=c;
         this.user=user;
         // Tracking table with data
      committeesTable = new JTable(rowData, columns);
      model1.setColumnIdentifiers( columns);
      committeesTable.setModel(model1);
      committeesTable.setRowHeight(30);
         
         // Committees Table with data
      trackingTable = new JTable();
      model2.setColumnIdentifiers(columns);
      trackingTable.setModel(model2);
      trackingTable.setRowHeight(30);
         
         // Invitations table with data
      inviteTable = new JTable();
      model3.setColumnIdentifiers(columns);
      inviteTable.setModel(model3);
      inviteTable.setRowHeight(30);
          
         //create a View Committies GUI
      JPanel jpNorthRow = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
      JPanel jpCenterRow = new JPanel();
      JPanel jpBottomRow = new JPanel(new FlowLayout());
      JPanel jpFirstTabPanel = new JPanel(new FlowLayout());
      btOpenDetails = new JButton("OpenProjectDetails");
      committeesTable.getModel().addTableModelListener(
         new TableModelListener() {
         
            public void tableChanged(TableModelEvent e) {
               System.out.println(e);
            }
         });
         
      jpNorthRow.add(new JScrollPane(committeesTable));
      jpBottomRow.add(btOpenDetails);
      jpFirstTabPanel.add(jpNorthRow, BorderLayout.NORTH);
      jpFirstTabPanel.add(jpBottomRow, BorderLayout.SOUTH);
         
         //create new tabs 
         
      JTabbedPane tabbedPane = new JTabbedPane();
         
      JComponent panel1 = makeTextPanel("Panel #1");
      panel1.add(jpFirstTabPanel);
      tabbedPane.addTab("View Committees", jpFirstTabPanel);
         
         // For 2nd Tab Panel
      JPanel jp2NorthRow = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
      JPanel jp2BottomRow = new JPanel(new FlowLayout());
      JPanel jpSecondTabPanel = new JPanel(new FlowLayout());
      btTrack = new JButton("Add Track");
      btOpenDetails = new JButton("OpenProjectDetails");
         
      jp2NorthRow.add(new JScrollPane(trackingTable));
      jp2BottomRow.add(btOpenDetails);
      jp2BottomRow.add(btTrack);
      jpSecondTabPanel.add(jp2NorthRow, BorderLayout.NORTH);
      jpSecondTabPanel.add(jp2BottomRow, BorderLayout.SOUTH);
         
      JComponent panel2 = makeTextPanel("Panel #2");
      panel2.add(jpSecondTabPanel);
         
      tabbedPane.addTab("Tracking list", jpSecondTabPanel);
         
         //for 3rd Tab Panel
      JPanel jp3NorthRow = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
      JPanel jp3BottomRow = new JPanel(new FlowLayout());
      JPanel jpThirdTabPanel = new JPanel(new FlowLayout());
      btApprove = new JButton("Approve");
      btDecline = new JButton("Decline");
      btOpenDetails = new JButton("OpenProjectDetails");
         
         
      jp3NorthRow.add(new JScrollPane(inviteTable));
      jp3BottomRow.add(btApprove);
      jp3BottomRow.add(btDecline);
      jp3BottomRow.add(btOpenDetails);
      jpThirdTabPanel.add(jp3NorthRow, BorderLayout.NORTH);
      jpThirdTabPanel.add(jp3BottomRow, BorderLayout.SOUTH);
         
      JComponent panel3 = makeTextPanel("Panel #3");
      panel3.add(jpThirdTabPanel);
         
      tabbedPane.addTab("Invitations", jpThirdTabPanel);
   
         
         //add the tabbled pane to this panel
      add(tabbedPane);
         
      tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
         
         //create the menu bar
      menuBar = new JMenuBar();
         
         //build the first menu
      menu = new JMenu("File");
      menu2 = new JMenu("Edit");
      menu1 = new JMenu("Help");
      menu.setMnemonic(KeyEvent.VK_A);
         
      menuBar.add(menu);
      menuBar.add(menu2);
      menuBar.add(menu1);
         
         //a group of JMenuItems
      jMenuItem = new JMenuItem("View Committees");
      menu.add(jMenuItem);
         
      jMenuItem2 = new JMenuItem("Tracking list");
      menu.add(jMenuItem2); 
         
      jMenuItem3 = new JMenuItem("Invitations");
      menu.add(jMenuItem3);
      defaultTable(); 
         
   }//end of faculty constructor
   
// get the defaultablemodel for Jtable
// take an arraylist of capstoneinfo
// for each capstoneinfo in the array
// call defaultableModelName.addRow(capstoneInfoName.getFacultyGuiInfo()
// refersh the disply (or whatever you need to do to make the changes visable

   public void defaultTable()
   {
       ArrayList<CapstoneInfo>tracking=C.get
      
      for (CapstoneInfo capInfo : capstone)
      {

      }
      model1.fireTableDataChanged();   
      display();                
   }
   
   public void display()
   {
      pack();
      setJMenuBar(menuBar);
      setTitle("Faculty Information");
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   protected JComponent makeTextPanel(String text){
      JPanel panel = new JPanel(false);
      JLabel filler = new JLabel(text);
      filler.setHorizontalAlignment(JLabel.CENTER);
      panel.setLayout(new GridLayout(1,1));
      panel.add(filler);
      return panel;
   }
   
   public void windowClosing(WindowEvent e) {
      dispose();
      System.exit(0);
   }


   
}//end of faculty class