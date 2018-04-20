import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.*;
import java.util.*;

/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */

/* this is a class just to hold the main method for testing purposes
 */
public class  MainTest{

   public static void main(String [] args)
   {
       try{

           Thread th=new Thread(new CapstoneTrackerDbServer());
           Thread.sleep(4000);
           th.start();
           InetAddress addr=InetAddress.getByName("129.21.73.76");
           Socket conn=new Socket(addr,4242);
           ObjectOutputStream out=new ObjectOutputStream(conn.getOutputStream());
           ObjectInputStream in=new ObjectInputStream(conn.getInputStream());
           out.writeObject("login");
           out.flush();
           loginInfo info=new loginInfo("jpf6185","pass");
           out.writeObject(info);
           out.flush();
           System.out.println((String)in.readObject());
           System.out.println(((user_info)in.readObject()).getName());
           out.close();
           conn.close();

       }
       catch (Exception e){
           System.out.println(e.toString());
       }
   }

}