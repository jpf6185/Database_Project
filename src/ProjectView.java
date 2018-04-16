import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;
import java.io.*;

public class ProjectView extends JFrame implements ActionListener{

   // JComponent Attributes
   private JPanel jpNorthPanel, jpCenterPanel, jpSouthPanel;
   private JLabel jlTitle;
   private JTable jProjectTable;
   private JButton jbChangeStatus, jbPlagiarismScore,jbApply,jbClose,jbFinalGrade;
   
   // JComponent ScrollPane
   private JScrollPane jScrollPane;
   
   // Attributes
   private String[] columnNames = {"null","null"};
   private Object[][] dataStatus ={
   {"Date:", "Test"},
   {"Student Name:", "Test"},
   {"Project Title:", "Test"},
   {"Description:", "Test"},
   {"Plagiarism Score:", "Test"},
   {"Grade:", "Test"},
   {"Type:", "Test"},
   {"Status:", "Test"}};
   
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
      jProjectTable = new JTable(dataStatus,columnNames);
      jProjectTable.setTableHeader(null);
      jScrollPane = new JScrollPane(jProjectTable);
      jProjectTable.setPreferredScrollableViewportSize(jProjectTable.getPreferredSize());
      jProjectTable.setFillsViewportHeight(true);
      
      // Right Align 1st Column and Center Align 2nd Column
      TableColumnModel cm = jProjectTable.getColumnModel();
      TableColumn colOne = cm.getColumn(0);
      TableColumn colTwo = cm.getColumn(1);
      DefaultTableCellRenderer rendererForOne = new DefaultTableCellRenderer();
      DefaultTableCellRenderer rendererForTwo = new DefaultTableCellRenderer();
      rendererForOne.setHorizontalAlignment(JLabel.RIGHT);
      colOne.setCellRenderer(rendererForOne);
      rendererForTwo.setHorizontalAlignment(JLabel.CENTER);
      colTwo.setCellRenderer(rendererForTwo);
      
      jpCenterPanel.add(jScrollPane, BorderLayout.CENTER);
      add(jpCenterPanel, BorderLayout.CENTER);
      
      // South Panel Setup
      jpSouthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      
      if (!getRole.equals("Faculty")){
         jbChangeStatus = new JButton("Change Status");
         jbChangeStatus.addActionListener(this);
         jpSouthPanel.add(jbChangeStatus);
         
         jbPlagiarismScore = new JButton("Enter a Plagiarism Score");
         jbPlagiarismScore.addActionListener(this);
         jpSouthPanel.add(jbPlagiarismScore);
         
         jbApply = new JButton("Apply");
         jbApply.addActionListener(this);
         jpSouthPanel.add(jbApply);
      }
      else{
         // This else when role is Staff
         jbFinalGrade = new JButton("Enter a final grade");
         jbFinalGrade.addActionListener(this);
         jpSouthPanel.add(jbFinalGrade);
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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   @Override
   public void actionPerformed(ActionEvent ae){
      Object choice = ae.getSource();
   }

} // end class