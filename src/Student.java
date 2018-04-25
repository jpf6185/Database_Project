import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 04/06/2018

 */

public class Student extends JFrame //implements ActionListener
{
    //Attributes
    private JTextArea jtextArea;

    private JButton jbEdit, jbCancel, jbView, jbUpload, jbDownload, jbDiscard;
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

    private JLabel jlStatus;

    private JLabel jlCurrentFaculty;
    private JTextField jtfCurrentFaculty;

    private JLabel jlRole;
    private String[] roleList = { "Chair","Reader1","Reader2" };

    private String[] addList = { "Project","Thesis" };


    private JLabel jlEmail;
    private JTextField jtfEmail;

    private JPanel jpNorthPanel, jpSouthPanel, jpLeftPanel, jpRightPanel;
    private JPanel jpFirstRow, jpSecondRow, jpThirdRow, jpFourRow, jpFiveRow, jpSixRow, jpSevenRow, jpEightRow;
    private JPanel jpFirstBtnRow, jpSecondBtnRow, jpThirdBtnRow, jpFourBtnRow, jpFiveBtnRow;

    private JPanel jpCenterPanel,jpPaddedCenterPanel;
    private JPanel jpCenterFirstRow, jpCenterSecondRow;
    private JPanel jpCenterLeftPanel, jpCenterRightPanel;

    // the type of uesr that opens it, determiens what they are allowed to eddit
    private String userType;
    private UserInfo user;
    // the main client class that handels comumication
    private Client c;
    Vector<StatusInfo> statusVec=null;
    int StatusItemNum;
    private JComboBox statusDropList;
    private JScrollPane jScrollPane1;

    // ui elements realted to the defense date

    //CapstoneInfo Object
    CapstoneInfo capstoneInfos;

    private JComboBox typeList;

    //private JTextField jtfDate4;

    //Default Constructor
    public Student(Client client, UserInfo _user)
    {
        //This will show at the top Title
        setTitle("Student Information");
        this.c=client;
        user=_user;
        userType=user.getUserType();
        capstoneInfos=c.getCapstoneInfo();
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
        jlDate = new JLabel("Last Edited:", SwingConstants.RIGHT);
        jtfDate = new JTextField(capstoneInfos.getLatestDate(),50);

        jpFirstRow.add(jlDate);
        jpFirstRow.add(jtfDate);
        jtfDate.setEditable(false);

        jlName = new JLabel("Student Name: ", SwingConstants.RIGHT);
        jtfName = new JTextField(capstoneInfos.getAuthor(),50);
        jpSecondRow.add(jlDate);
        jpSecondRow.add(jtfDate);
        jtfName.setEditable(false);


        jlProject = new JLabel("Project Title: ", SwingConstants.RIGHT);
        jtfProject = new JTextField(capstoneInfos.GetVersions().get(0).getTitle(),50);
        jpThirdRow.add(jlProject);
        jpThirdRow.add(jtfProject);
        jtfProject.setEditable(false);

        jlDescription = new JLabel("Description: ", SwingConstants.RIGHT);
        jtfDescription = new JTextField(capstoneInfos.GetVersions().get(0).getDescription(),50);
        jpFourRow.add(jlDescription);
        jpFourRow.add(jtfDescription);
        jtfDescription.setEditable(false);


        jlPlagiarism = new JLabel("Plagiarism Score: ", SwingConstants.RIGHT);
        jtfPlagiarism = new JTextField(capstoneInfos.getPlagiarismScore(),50);
        jpFiveRow.add(jlPlagiarism);
        jpFiveRow.add(jtfPlagiarism);
        jtfPlagiarism.setEditable(false);
\


        jlGrade = new JLabel("Grade: ", SwingConstants.RIGHT);
        jtfGrade = new JTextField(capstoneInfos.getGrade(),50);
        jpSixRow.add(jlGrade);
        jpSixRow.add(jtfGrade);
        jtfGrade.setEditable(false);

        jlType = new JLabel("Type: ", SwingConstants.RIGHT);
        typeList = new JComboBox(addList);
        typeList.setEnabled(false);
        jpSevenRow.add(jlType);
        jpSevenRow.add(typeList);
        typeList.setEnabled(false);
        statusVec = c.getStatuses();
        jlStatus = new JLabel("Status: ", SwingConstants.RIGHT);
        // creates the droplist and has the right item shown
        statusDropList = new JComboBox(statusVec);
        statusDropList.setEnabled(false);
        UpdateShownStatus();

        jpEightRow.add(jlStatus);
        jpEightRow.add(statusDropList);
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
        jbUpload = new JButton("Upload");
        jpFirstBtnRow.add(jbUpload);
        jbUpload.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {

                    }
                });

        jbDownload = new JButton("Download");
        jpSecondBtnRow.add(jbDownload);
        jbDownload.addActionListener(
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
                        // bunch of booleans to control what is editable
                        if(jbEdit.getText().toLowerCase().equals("edit")) {
                            boolean nameEdit = false;
                            boolean projectEdit = false;
                            boolean descEdit = false;
                            boolean plagEdit = false;
                            boolean gradEdit = false;
                            boolean TypeEdit = false;
                            boolean StatusEdit = false;
                            switch (userType.toLowerCase()) {
                                case "student":
                                    nameEdit = true;
                                    projectEdit = true;
                                    descEdit = true;
                                    TypeEdit = true;
                                    jbEdit.setText("save");
                                    break;
                                case "faculty":
                                    gradEdit = true;
                                    jbEdit.setText("save");
                                    break;
                                case "staff":
                                    StatusEdit = true;
                                    jbEdit.setText("save");
                                    break;
                                default:
                                    break;

                            }


                            jtfName.setEditable(nameEdit);
                            jtfProject.setEditable(projectEdit);
                            jtfDescription.setEditable(descEdit);
                            jtfPlagiarism.setEditable(plagEdit);
                            jtfGrade.setEditable(gradEdit);
                            statusDropList.setEnabled(StatusEdit);
                            typeList.setEnabled(TypeEdit);
                        }
                        else if(jbEdit.getText().equals("save")){



                            jbEdit.setText("edit");
                            // disables edditing on fields
                            jtfName.setEditable(false);
                            jtfProject.setEditable(false);
                            jtfDescription.setEditable(false);
                            jtfPlagiarism.setEditable(false);
                            jtfGrade.setEditable(false);
                            statusDropList.setEnabled(false);
                            typeList.setEnabled(false);
                            // gets the new values of the fields and stores them temporarly
                            CapstoneInfo tempValues=capstoneInfos;
                            tempValues.GetVersions().get(0).setTitle(jtfProject.getText());
                            tempValues.GetVersions().get(0).setDescription(jtfDescription.getText());
                            tempValues.setPlagiarismScore(jtfPlagiarism.getText());
                            tempValues.setGrade(jtfGrade.getText());
                            tempValues.GetVersions().get(0).setType((String)typeList.getSelectedItem());
                            // then tries to save the new value and stores the return
                            tempValues=c.saveCapstone(tempValues);
                            if(tempValues!=null){
                                capstoneInfos=tempValues;
                            }
                            // only saves the return if it is not null
                            updateFields();
                        }
                        else{}
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
                        JOptionPane.showMessageDialog(null, jtfDate.getText() + " " + jtfProject.getText());
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

        jlEmail = new JLabel("Email Address or Name: ", SwingConstants.RIGHT);
        jtfEmail = new JTextField(50);
        jpCenterLeftPanel.add(jlEmail);
        jpCenterLeftPanel.add(jtfEmail);
        jbInvite = new JButton("Send an Invitation");
        jbInvite.addActionListener(
                new ActionListener()
                {
                    // sends a invite to the faculty specified
                    public void actionPerformed(ActionEvent e)
                    {
                        String usernameOrName="";
                        if(jtfEmail.getText().contains("@")){
                            usernameOrName=jtfEmail.getText().split("@")[0];
                        }
                        else{
                            usernameOrName=jtfEmail.getText();
                        }
                        commitee_info invite=new commitee_info(usernameOrName);
                        invite.setPosition((String)jcbRoleDropList.getSelectedItem());
                        invite.setCapStoneID(capstoneInfos.getCapstoneID());
                        if(!(c.sendInvite(invite))){
                            JOptionPane.showMessageDialog(null,"An error has occured when sending the invite, please insure the name or email is correct","error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            jtfEmail.setText("");
                        }
                    }
                    }
                );

        jpCenterRightPanel.add(jbInvite);

        jpCenterSecondRow.add(jpCenterLeftPanel, BorderLayout.WEST);
        jpCenterSecondRow.add(jpCenterRightPanel, BorderLayout.EAST);

        jpCenterPanel.add(jpCenterFirstRow);
        jpCenterPanel.add(jpCenterSecondRow);
        jpPaddedCenterPanel.add(jpCenterPanel);
        add(jpPaddedCenterPanel, BorderLayout.CENTER);



        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateFields(){
        jtfDate.setText(capstoneInfos.getLatestDate());
        jtfName.setText(capstoneInfos.getAuthor());
        jtfProject.setText(capstoneInfos.GetVersions().get(0).getTitle());
        jtfDescription.setText(capstoneInfos.GetVersions().get(0).getDescription());
        jtfPlagiarism.setText(capstoneInfos.getPlagiarismScore());
        jtfGrade.setText(capstoneInfos.getGrade());
        UpdateShownStatus();
        UpdateShownType();
    }
    // sets the status dropdown to show the correct item
    private void UpdateShownStatus(){
        boolean foundMatch=false;
        for(int i=0; i<statusDropList.getItemCount(); i++){
            if(((StatusInfo)statusDropList.getItemAt(i)).getStatusName().equals(capstoneInfos.GetVersions().get(0).getStatus())){
                StatusItemNum=i;
                statusDropList.setSelectedIndex(i);
            }
        }
    }
    // sets the project type dropdown to display the correct value
    private void UpdateShownType(){
        if(capstoneInfos.GetVersions().get(0).getType().equals("Project")){
            typeList.setSelectedIndex(0);
        }
        else{
            typeList.setSelectedIndex(1);
        }
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



    public Boolean studentUpdate(CapstoneInfo inObj)
    {
        //CapstoneVersion inObj2 = inObj.GetVersion().get(0);
        return true;
    }


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