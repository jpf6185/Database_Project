import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private Client c;
    private UserInfo user;
    private CapstoneInfo selectedCapstoneData;
    private ArrayList<CapstoneInfo> allCapstoneData = null;
    private int selectedRow;

    private String[] columnNames = {"Student Name","Project Title", "Current Status","Date"};
    private Object[][] tableData = null;

    // Constructor
    public Staff(Client _client, UserInfo _user){

        // Getting all of the data
        this.c = _client;
        this.user = _user;
        allCapstoneData = c.getAllCapstoneData();

        // North Panel Setup
        jpNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30,10));
        jlTitle = new JLabel("View Student Project Status", SwingConstants.LEFT);
        jpNorthPanel.add(jlTitle);
        add(jpNorthPanel, BorderLayout.NORTH);

        // Center Panel Setup
        jpCenterPanel = new JPanel(new BorderLayout(30,30));
        jStaffTable = new JTable(fillTable(allCapstoneData), columnNames);
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

    // fillTable Method
    public Object[][] fillTable(ArrayList<CapstoneInfo> _capstoneData){

        try {
            tableData = new Object[_capstoneData.size()][4];

            String studentName;
            String projectTitle;
            String currentStatus;
            String date;

            // Begins iterating through all Capstone objects
            for (int i = 0; i < _capstoneData.size(); i++) {

                studentName = _capstoneData.get(i).getAuthor();
                projectTitle = _capstoneData.get(i).GetVersions().get(_capstoneData.get(i).GetVersions().size() - 1).getTitle();
                currentStatus = _capstoneData.get(i).GetVersions().get(_capstoneData.get(i).GetVersions().size() - 1).getStatusName();
                date = _capstoneData.get(i).GetVersions().get(_capstoneData.get(i).GetVersions().size() - 1).getDate();

                tableData[i][0] = studentName;
                tableData[i][1] = projectTitle;
                tableData[i][2] = currentStatus;
                tableData[i][3] = date;
            }
        }
        catch (Exception e){
            System.out.println("Error at fillTable() Method: " + e.getMessage());
        }

        return tableData;
    } // fillTable Method

    // Method to update AllCapstoneData object after closing ProjectView
    public Boolean updateAllCapstoneData(CapstoneInfo _updatedCapstoneData){

        Boolean status = false;

        if (allCapstoneData.get(selectedRow).getCapstoneID() == _updatedCapstoneData.getCapstoneID()){
            allCapstoneData.set(selectedRow, _updatedCapstoneData);

            fillTable(allCapstoneData);

            status = true;
        }
        else{
            status = false;
        }
        return status;
    } // end updateAllCapstoneData Method

    @Override
    public void actionPerformed(ActionEvent ae){

        selectedRow = jStaffTable.getSelectedRow();
        if (selectedRow != -1){
            if (ae.getSource() == jbOpenProjectMgmt){

                selectedCapstoneData = allCapstoneData.get(selectedRow);

                ProjectView staffProjectView = new ProjectView(c,this,selectedCapstoneData);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select Capstone Project on table first.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }

} // end class
