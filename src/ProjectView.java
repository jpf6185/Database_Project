import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class ProjectView extends JFrame{
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
           "Date:",
           "Student Name:",
           "Project Title:",
           "Description:",
           "Plagiarism Score:",
           "Grade:",
           "Type:",
           "Status:"};


   // Attributes
   private String[] columnNames = {"null","null"};
   private Object[][] currentData = new Object[8][2];

   private String getRole = "Faculty";
   private String changeTitle;

   // Main Method
   public static void main(String [] args){
      ProjectView gui = new ProjectView();
   }

   // Constructor
   public ProjectView(){

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
      jpCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
      jProjectTable = new JTable(getDataObject(currentData),columnNames);
      jProjectTable.setTableHeader(null);
      jScrollPane = new JScrollPane(jProjectTable);
      jProjectTable.setPreferredScrollableViewportSize(jProjectTable.getPreferredSize());
      jProjectTable.setFillsViewportHeight(true);


      // Set Size of the Project View Table
      jProjectTable.getColumnModel().getColumn(0).setPreferredWidth(200);
      jProjectTable.getColumnModel().getColumn(1).setPreferredWidth(30);

      jpCenterPanel.add(jScrollPane, BorderLayout.CENTER);
      add(jpCenterPanel, BorderLayout.CENTER);

      // South Panel Setup
      jpSouthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

      if (!getRole.equals("Faculty")){
         jbChangeStatus = new JButton("Change Status");
         //jbChangeStatus.addActionListener
         jpSouthPanel.add(jbChangeStatus);

         jbPlagiarismScore = new JButton("Enter a Plagiarism Score");
         //jbPlagiarismScore.addActionListener(this);
         jpSouthPanel.add(jbPlagiarismScore);

         jbApply = new JButton("Apply");
         //jbApply.addActionListener(this);
         jpSouthPanel.add(jbApply);
      }
      else{
         // This else when role is Staff
         jbFinalGrade = new JButton("Enter a final grade");
         jbFinalGrade.addActionListener((new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(dataHeaders.equals("Grade:")){

               }
            }
         }));
         jpSouthPanel.add(jbFinalGrade);
      }

      jbClose = new JButton("Close");
      jbClose.addActionListener((new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Faculty gui = new Faculty();
            gui.setVisible(true);
            dispose();
         }
      }));

      jpSouthPanel.add(jbClose);

      add(jpSouthPanel, BorderLayout.SOUTH);


      // GUI Layout Setup
      pack();
      //Color c = new Color(0,0,0,100);
      //jtextArea.setBackground(c);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }


   public Object[][] getDataObject(Object[][] data){

      // iterate dataHeaders into table
      int iteratedNum = 0;
      for (String dh : dataHeaders){

         data[iteratedNum][0] = dh;
         iteratedNum++;
      }

      return data;
   }

} // end class