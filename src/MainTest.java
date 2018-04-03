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
     CapstoneInfo i=new CapstoneInfo("4");
     i=s.GetCapstoneInfo(i);
     i.GetVersions().get(0).setDate("2018-01-15 12:24:26");
     s.updateCapstone(i);
     CapstoneInfo i2=new CapstoneInfo("4");
     i=s.GetCapstoneInfo(i2);
     System.out.println(i2.GetVersions().get(0).getDate());
   }
}