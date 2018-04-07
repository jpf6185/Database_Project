import java.util.*; 
import java.sql.*; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*; 
import java.lang.*;

/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */

/* This a custom exception class that handels data logging on the server Side
 */
public class DLException extends Exception{
   
   private ArrayList<String> addMsg = new ArrayList<String>();
   Exception e;  
   
   //Exception Constructor 
   public DLException(Exception _e)
   {
      e = _e; 
      writeLog(); 
   }
   
   //Exception and Arraylist of String Constructor
   public DLException(Exception _e, ArrayList<String>msg)
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
         for(String s : addMsg){
            pw.println(s);
         }
         pw.flush(); 
         pw.close();
         
      }catch(Exception e){
         e.printStackTrace(); 
      } 
   }
}