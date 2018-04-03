import java.sql.*;
import java.util.*;
/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */

/* this is a class just to hold the main method for testing purposes
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