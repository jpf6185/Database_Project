import java.util.*; 
import java.sql.*; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*; 
import java.lang.*;

/**
*  Name:   Chris Bonsu
*  Date:   3/20/2018
*  Course: ISTE 330
*  PE07:  
*/  

public class DLException extends Exception{
   
   private ArrayList<ArrayList<String>> addMsg = new ArrayList<ArrayList<String>>();
   Exception e;  
   
   //Exception Constructor 
   public DLException(Exception _e)
   {
      e = _e; 
      writeLog(); 
   }
   
   //Exception and Arraylist of String Constructor
   public DLException(Exception _e, ArrayList<ArrayList<String>>msg)
   {
      e = _e; 
      addMsg = msg; 
      System.out.println(addMsg); 
      writeLog(); 
   }
   
   //writeLog method 
   public void writeLog()
   {
      SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date timeStamp = new Date(); 
      
      try{
         PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("writeLog.txt", true)));
         pw.println(simpleDate.format(timeStamp) + "\n");
         pw.flush(); 
         pw.close();
         
      }catch(Exception e){
         e.printStackTrace(); 
      } 
   }
}