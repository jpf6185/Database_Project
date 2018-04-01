import java.sql.*;
import java.util.*; 

/**
*  Name:   Chris Bonsu
*  Date:   3/20/2018
*  Course: ISTE 330
*  PE07:  
*/

public class MainTest{

   public static void main(String [] args)
   {
     //Equipment Object 
     CapstoneTrackerServer s= new CapstoneTrackerServer();
     CapstoneInfo i=new CapstoneInfo("1");
     s.GetCapstoneInfo(i);
   }
}