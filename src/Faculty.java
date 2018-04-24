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
import java.util.Properties;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Faculty extends JFrame  {
    //attributes
    private JButton jbView;
    private JMenuBar menuBar;
    private JMenu menu, menu1, menu2;
    private JMenuItem jMenuItem,jMenuItem2,jMenuItem3;
    private JButton btOpenDetails, btTrack, btApprove, btDecline;
    private JTable committeesTable, trackingTable, inviteTable;
    ListSelectionModel listSelectionModel;
    private Client c;
    private  UserInfo user;

    private String [] columns = {
            "Student Name", "Project Title", "Current Status", "Date"
    };

    private String [] columns1 = {
            "StudentName", "Project Title", "Role", "Date"
    };


    //add rows to the table
    CapstoneInfo obj1 = new CapstoneInfo();
    //ArrayList<String>
    private Object [][] rowData = {{}};

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();



    //default constructor
    public Faculty(Client _c, UserInfo _User)
    {

        // Committees table with data
        model = new DefaultTableModel(rowData, columns){
            @Override
            public boolean isCellEditable(int _arg0, int _arg1) {
                return false; }
        };
        committeesTable = new JTable();
        committeesTable.addMouseListener(
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
        committeesTable.setModel(model);


        // Tracking Table with data
        model1 =
                new DefaultTableModel(rowData, columns){
                    @Override
                    public boolean isCellEditable(int _arg0, int _arg1){
                        return false;}
                };
        trackingTable = new JTable();
        trackingTable.addMouseListener(
                new java.awt.event.MouseAdapter(){
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent mouse){
                        if(mouse.getClickCount() == 2){
                            ProjectView view = new ProjectView();
                        }
                    }
                });

        model.setColumnIdentifiers(columns);
        trackingTable.setModel(model);

        // Invitations table with data
        model2 =
                new DefaultTableModel(rowData, columns1){
                    @Override
                    public boolean isCellEditable(int _arg0, int _arg1) {
                        return false; }
                };
        inviteTable = new JTable();
        inviteTable.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent mouse) {
                        if (mouse.getClickCount() == 2) {
                            //create an object from ProjectView class
                            ProjectView view = new ProjectView();
                        }
                    }
                });



        model1.setColumnIdentifiers(columns1);
        inviteTable.setModel(model1);

        //create a View Committies GUI
        JPanel jpNorthRow = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel jpBottomRow = new JPanel(new FlowLayout());
        JPanel jpFirstTabPanel = new JPanel(new FlowLayout());
        btOpenDetails = new JButton("OpenProjectDetails");
        btOpenDetails.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ProjectView view = new ProjectView();
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
        btOpenDetails.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ProjectView view = new ProjectView();
                    }
                });

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

        //actionListener for approve
        btApprove = new JButton("Approve");
        btApprove.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){

                    }
                });

        //actionListener for decline
        btDecline = new JButton("Decline");

        //actionListener for openProjectDetails
        btOpenDetails = new JButton("OpenProjectDetails");
        btOpenDetails.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ProjectView view = new ProjectView();
                    }
                });

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


        pack();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }//end of faculty constructor




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