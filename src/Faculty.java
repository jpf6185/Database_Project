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



public class Faculty extends JFrame implements ActionListener
{
    //attributes
    private JMenuBar menuBar;
    private JMenu menu, menu1, menu2;
    private JMenuItem jMenuItem,jMenuItem2,jMenuItem3;
    private JButton jbOpenProjectDetails, jbOpenTrackDetails, jbAddNewTrack, jbApprove, jbDecline;
    private JTable committeesTable, trackingTable, inviteTable;
    private JTabbedPane tabbedPane;
    private JComponent panel1, panel2, panel3;
    private TableColumnModel columnModel;
    private int selectedCommitteesRow = 0;
    private int selectedTrackingRow = 0;
    private int selectedInviteRow = 0;

    private Object [] columns = {
            "StudentName", "Project Title", "Current Status", "Date"
    };

    private CapstoneInfo selectedCapstoneData;
    private ArrayList<CapstoneInfo> allCommitteesData;
    private Object[][] rowData;
    private Client c;
    private UserInfo user;

    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DefaultTableModel model3 = new DefaultTableModel();

    //default constructor
    public Faculty(Client _client, UserInfo _user)
    {
        this.c= _client;
        this.user= _user;
        allCommitteesData =c.getCommiteeCapstones();
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

        // Create new tabbedPane
        tabbedPane = new JTabbedPane();
        panel1 = makeTextPanel("Panel #1");
        panel2 = makeTextPanel("Panel #2");
        panel3 = makeTextPanel("Panel #3");

        // For 1st Tab Panel Interface
        JPanel jpNorthRow = new JPanel(new BorderLayout());
        JPanel jpBottomRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel jpFirstTabPanel = new JPanel(new BorderLayout());
        jbOpenProjectDetails = new JButton("Open Project Details");
        jbOpenProjectDetails.addActionListener(this);
        committeesTable.getModel().addTableModelListener(new TableModelListener(){
                    public void tableChanged(TableModelEvent tme) {
                        System.out.println("CommitteesTable Listener:" + tme);
                    }
                });
        jpBottomRow.add(jbOpenProjectDetails);
        jpNorthRow.add(new JScrollPane(committeesTable));
        jpFirstTabPanel.add(jpNorthRow, BorderLayout.NORTH);
        jpFirstTabPanel.add(jpBottomRow, BorderLayout.SOUTH);

        columnModel = committeesTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(45);
        columnModel.getColumn(3).setPreferredWidth(30);

        panel1.add(jpFirstTabPanel);
        tabbedPane.addTab("View Committees", jpFirstTabPanel);

        // For 2nd Tab Panel Interface
        JPanel jp2NorthRow = new JPanel(new BorderLayout());
        JPanel jp2BottomRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel jpSecondTabPanel = new JPanel(new BorderLayout());
        jbAddNewTrack = new JButton("Add Track");
        jbAddNewTrack.addActionListener(this);
        jbOpenTrackDetails = new JButton("Open Tracked Project Details");
        jbOpenTrackDetails.addActionListener(this);
        trackingTable.getModel().addTableModelListener(new TableModelListener(){
            public void tableChanged(TableModelEvent tme) {
                System.out.println("TrackingTable Listener:" + tme);
            }
        });

        jp2NorthRow.add(new JScrollPane(trackingTable));
        jp2BottomRow.add(jbOpenTrackDetails);
        jp2BottomRow.add(jbAddNewTrack);
        jpSecondTabPanel.add(jp2NorthRow, BorderLayout.NORTH);
        jpSecondTabPanel.add(jp2BottomRow, BorderLayout.SOUTH);

        columnModel = trackingTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(45);
        columnModel.getColumn(3).setPreferredWidth(30);

        panel2.add(jpSecondTabPanel);
        tabbedPane.addTab("Tracking list", jpSecondTabPanel);

        //for 3rd Tab Panel Interface
        JPanel jp3NorthRow = new JPanel(new BorderLayout());
        JPanel jp3BottomRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel jpThirdTabPanel = new JPanel(new FlowLayout());
        jbApprove = new JButton("Approve");
        jbApprove.addActionListener(this);
        jbDecline = new JButton("Decline");
        jbDecline.addActionListener(this);
        inviteTable.getModel().addTableModelListener(new TableModelListener(){
            public void tableChanged(TableModelEvent tme) {
                System.out.println("InvitationTable Listener:" + tme);
            }
        });

        jp3NorthRow.add(new JScrollPane(inviteTable));
        jp3BottomRow.add(jbApprove);
        jp3BottomRow.add(jbDecline);
        jpThirdTabPanel.add(jp3NorthRow, BorderLayout.NORTH);
        jpThirdTabPanel.add(jp3BottomRow, BorderLayout.SOUTH);

        columnModel = inviteTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(45);
        columnModel.getColumn(3).setPreferredWidth(30);

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
        this.defaultTable();

        // GUI Layout Setup
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//end of faculty constructor

// get the defaultablemodel for Jtable
// take an arraylist of capstoneinfo
// for each capstoneinfo in the array
// call defaultableModelName.addRow(capstoneInfoName.getFacultyGuiInfo()
// refersh the disply (or whatever you need to do to make the changes visable

    public void defaultTable()
    {

        for (CapstoneInfo capInfo : allCommitteesData)
        {
            model1.addRow(capInfo.getFacultyGuiInfo());
        }
        model1.fireTableDataChanged();

        ArrayList<CapstoneInfo>tracked=c.getTrackedCapstones();
        for(CapstoneInfo capInfo : tracked){
            model2.addRow(capInfo.getFacultyGuiInfo());
        }
        model2.fireTableDataChanged();

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

    public void syncCommitteesTable(CapstoneInfo _selectedCapstoneData) {
        model1.insertRow(selectedCommitteesRow, _selectedCapstoneData.getFacultyGuiInfo());
    }

    public Boolean updateAllCommitteesData(CapstoneInfo _updatedCapstoneData){

        Boolean status = false;

        if (allCommitteesData.get(selectedCommitteesRow).getCapstoneID().equals(_updatedCapstoneData.getCapstoneID())){
            allCommitteesData.set(selectedCommitteesRow, _updatedCapstoneData);

            syncCommitteesTable(allCommitteesData.get(selectedCommitteesRow));

            status = true;
        }
        else{
            status = false;
        }
        return status;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        /* Committees Table
         * If Open Project Details button is clicked
         */
        if (selectedCommitteesRow != -1 || selectedTrackingRow != -1 || selectedInviteRow != -1) {
            if (ae.getSource() == jbOpenProjectDetails) {
                selectedCapstoneData = allCommitteesData.get(selectedCommitteesRow);
                ProjectView facultyCommitteesView = new ProjectView(c, this, selectedCapstoneData);
            }


            /* Tracking Table
             * If Open Tracked Project Details button is clicked
             */
            if (ae.getSource() == jbOpenTrackDetails) {

            }


            /* Tracking Table
             * If Add New Track button is clicked
             */
            if (ae.getSource() == jbAddNewTrack) {

            }


            /* Invitation Table
             * If Approve button is clicked
             */
            if (ae.getSource() == jbApprove) {

            }


            /* Invitation Table
             * If Decline button is clicked
             */
            if (ae.getSource() == jbDecline) {

            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select the row on table first.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }

    } // end ActionListener Method


}//end of faculty class