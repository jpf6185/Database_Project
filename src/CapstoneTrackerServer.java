import java.io.*;
import java.util.*;


public class CapstoneTrackerServer{

    MySQLDatabase my = new MySQLDatabase();

    public CapstoneInfo GetCapstoneInfo(CapstoneInfo){
        try{

           if(my.connect()) {
               String sqlStatement = "Select username, userType, name, email, `Phone Number`, Department FROM USERS;";


           }
            my.close();



        }
        catch(Exception e){
            System.out.println("An error occured attempting to get the info on a capstone");
        }

    }

}