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
     CapstoneTrackerDBInterface dbCon=new CapstoneTrackerDBInterface();

             dbCon.GetAllCapstones();
   }
}