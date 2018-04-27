import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class ProjectView extends JFrame implements ActionListener{
    // JComponent Attributes
    private JPanel jpNorthPanel, jpCenterPanel, jpSouthPanel;
    private JLabel jlTitle;
    private JTable jProjectTable;
    private JButton jbChangeStatus, jbPlagiarismScore,jbApply,jbClose,jbFinalGrade;
    private JFrame frame;
    CapstoneInfo obj1 = new CapstoneInfo();

    // JComponent ScrollPane
    private JScrollPane jScrollPane;

    // ArrayList Attributes
    private String[] dataHeaders = {
            "Date",
            "Student Name:",
            "Project Title:",
            "Description:",
            "Plagiarism Score:",
            "Grade:",
            "Type:",
            "Status:"};


    // Attributes
    private Boolean headersFinished = false;
    private Client c;
    private Staff s;
    private Faculty f;
    private CapstoneInfo selectedCapstoneData = null;

    private String[] columnNames = {"null","null"};
    private Object[][] tableCapstoneData = new Object[8][2];

    private String getRole = "Faculty";
    private String changeTitle;


    // Constructor
    public ProjectView(Client _client, Staff _staff, CapstoneInfo _selectedCapstoneData){
        // Gets Client Socket & CapstoneInfo Object
        this.c = _client;
        this.selectedCapstoneData = _selectedCapstoneData;
        this.s = _staff;
        openGUI();
    }

    public void openGUI(){

        if (!getRole.equals("Faculty")){
            changeTitle = "Project Management";
        }
        else{
            changeTitle = "View Project Details";
        }


        // North Panel Setup
        jpNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30,10));
        jlTitle = new JLabel(changeTitle, SwingConstants.LEFT);
        jpNorthPanel.add(jlTitle);
        add(jpNorthPanel, BorderLayout.NORTH);

        // Center Panel Setup
        jpCenterPanel = new JPanel(new GridLayout(1,1));
        jProjectTable = new JTable(fillCapstoneTable(selectedCapstoneData),columnNames);
        jProjectTable.setTableHeader(null);
        jScrollPane = new JScrollPane(jProjectTable);
        jProjectTable.setPreferredScrollableViewportSize(jProjectTable.getPreferredSize());
        jProjectTable.setFillsViewportHeight(true);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        jProjectTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);


        // Set Size of the Project View Table
        jProjectTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jProjectTable.getColumnModel().getColumn(1).setPreferredWidth(300);

        jpCenterPanel.add(jScrollPane, BorderLayout.CENTER);
        add(jpCenterPanel, BorderLayout.CENTER);

        // South Panel Setup
        jpSouthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        if (!getRole.equals("Faculty")){
            jbFinalGrade = new JButton("Enter a final grade");
            jbFinalGrade.addActionListener(this);
            jpSouthPanel.add(jbFinalGrade);

            jbApply = new JButton("Apply");
            jbApply.addActionListener(this);
            jpSouthPanel.add(jbApply);
        }
        else{
            // This else when role is Staff
            jbChangeStatus = new JButton("Change Status");
            jbChangeStatus.addActionListener(this);
            jpSouthPanel.add(jbChangeStatus);

            jbPlagiarismScore = new JButton("Enter a Plagiarism Score");
            jbPlagiarismScore.addActionListener(this);
            jpSouthPanel.add(jbPlagiarismScore);
        }

        jbClose = new JButton("Close");
        jbClose.addActionListener(this);

        jpSouthPanel.add(jbClose);

        add(jpSouthPanel, BorderLayout.SOUTH);


        // GUI Layout Setup
        pack();
        //Color c = new Color(0,0,0,100);
        //jtextArea.setBackground(c);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(600,300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                              @Override
                              public void windowClosing(WindowEvent e) {
                                  confirmCloseWindow();
                              }
                          }
        );

    }

    // fillCapstoneTable Method
    public Object[][] fillCapstoneTable(CapstoneInfo _capstoneInfoData){

        try {
            // iterate dataHeaders into table
            if (!headersFinished){
                int iteratedNum = 0;
                for (String dh : dataHeaders) {

                    tableCapstoneData[iteratedNum][0] = dh;
                    iteratedNum++;
                }
                headersFinished = true;
            }

            tableCapstoneData[0][1] = _capstoneInfoData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getDate();
            tableCapstoneData[1][1] = _capstoneInfoData.getAuthor();
            tableCapstoneData[2][1] = _capstoneInfoData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getTitle();
            tableCapstoneData[3][1] = _capstoneInfoData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getDescription();
            tableCapstoneData[4][1] = _capstoneInfoData.getPlagiarismScore();
            tableCapstoneData[5][1] = _capstoneInfoData.getGrade();
            tableCapstoneData[6][1] = _capstoneInfoData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getType();
            tableCapstoneData[7][1] = _capstoneInfoData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getStatusName();

            for (int i = 0; i < tableCapstoneData.length; i++){
                if (tableCapstoneData[i][1] == null){
                    tableCapstoneData[i][1] = "blank";
                }
            }

        }
        catch (Exception e){
            System.out.println("Error:ProjectView:getDataObject Method:" + e.getMessage());
        }
        return tableCapstoneData;
    } // fillCapstoneTable Method

    @Override
    public void actionPerformed(ActionEvent ae){

        // Apply button is clicked
        if (ae.getSource() == jbApply){
            c.saveCapstone(selectedCapstoneData);
        }

        // Close button is clicked
        if (ae.getSource() == jbClose){
            confirmCloseWindow();
        }

        // ChangeStatus button is clicked
        if (ae.getSource() == jbChangeStatus){
            try {
                int index = 0;
                int matchedIndex = 0;
                Vector<StatusInfo> statusChoices = c.getStatuses();
                String[] statusNameArray = new String[statusChoices.size()];
                String[] statusCodeArray = new String[statusChoices.size()];
                for (StatusInfo sc : statusChoices) {
                    statusNameArray[index] = (sc.getStatusName());
                    statusCodeArray[index] = (sc.getStatusNumber());
                    if ((sc.getStatusName().equals(selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getStatusName()))) {
                        matchedIndex = index;
                    }
                    index++;
                }

                String input = (String) JOptionPane.showInputDialog(null, "Choose status: ", "Change Capstone's Status",
                        JOptionPane.QUESTION_MESSAGE, null, statusNameArray, statusNameArray[matchedIndex] );

                for (int i = 0; i < statusNameArray.length; i++){
                    if (statusNameArray[i].equals(input)) {
                        matchedIndex = i;
                        break;
                    }
                }

                if ((input != null) && (input.length() > 0)) {
                    String tempStatusName = selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getStatusName();
                    String tempStatusCode = selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getStatusCode();
                    selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).setStatusName(statusNameArray[matchedIndex]);
                    selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).setStatusCode(statusCodeArray[matchedIndex]);
                    selectedCapstoneData = c.saveCapstone(selectedCapstoneData);
                    if (selectedCapstoneData != null) {
                        selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 2).setStatusName(tempStatusName);
                        selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 2).setStatusCode(tempStatusCode);
                        c.saveCapstone(selectedCapstoneData);
                        tableCapstoneData[7][1] = selectedCapstoneData.GetVersions().get(selectedCapstoneData.GetVersions().size() - 1).getStatusName();
                    }
                }
            }
            catch (Exception e){
                System.out.println("Error:ProjectView:actionPerformed-ChangeStatus:" + e.getMessage());
            }
        }

        // PlagiarismScore button is clicked
        if (ae.getSource() == jbPlagiarismScore){
            String input = JOptionPane.showInputDialog(null, "Enter Plagiarism Score: ", "Plagiarism Score Input", JOptionPane.INFORMATION_MESSAGE);
            CapstoneInfo tempValues = selectedCapstoneData;
            tempValues.setPlagiarismScore(input);
            tempValues=c.saveCapstone(tempValues);
            if(tempValues!=null){
                selectedCapstoneData=tempValues;
                tableCapstoneData[4][1] = selectedCapstoneData.getPlagiarismScore();
            }
        }

        // FinalGrade button is clicked
        if (ae.getSource() == jbFinalGrade){
            String input = JOptionPane.showInputDialog(null, "Enter Final Grade: ", "Final Grade Input", JOptionPane.INFORMATION_MESSAGE);
            CapstoneInfo tempValues = selectedCapstoneData;
            tempValues.setGrade(input);
            tempValues=c.saveCapstone(tempValues);
            if (tempValues!=null){
                selectedCapstoneData=tempValues;
                tableCapstoneData[5][1] = selectedCapstoneData.getGrade();
            }
        }

    } // end actionPerformed Method

    // Method to confirm closing Window & safely save Data;
    public void confirmCloseWindow(){

        if (JOptionPane.showConfirmDialog(null,
                "Are you sure to close this window?", "Really Closing?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

            if (s != null) {
                if (this.s.updateAllCapstoneData(selectedCapstoneData)) {
                    System.out.println("SUCCESS: Passed Inserted updated Capstone Info back to Staff's allCapstoneData between Staff n ProjectView classes.");
                } else {
                    System.out.println("UNSUCCESS: Passed Inserted updated Capstone Info back to Staff's allCapstoneData between Staff n ProjectView classes.");
                }
            }
            if (f != null){

            }
            setVisible(false);
            dispose();
        }
    }

} // end Staff class