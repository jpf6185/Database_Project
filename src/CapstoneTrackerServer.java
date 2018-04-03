import java.io.*;
import java.util.*;


public class CapstoneTrackerServer{

    MySQLDatabase db = new MySQLDatabase();

    public user_info GetStudentDates(user_info outObj){

        // arraylist to hold data returned
        ArrayList<ArrayList<String>> Values;
        ArrayList<ArrayList<String>> Value;
        try{
            String sqlStatement = "select startDate FROM StudentDetails JOIN Term ON StudentDetails.MasterStart=Term.term WHERE username=?;";
            String sqlStatementTwo = "Select startDate FROM StudentDetails JOIN Term ON StudentDetails.CapstoneStart=Term.term WHERE username=?;";

            ArrayList<String> Params = new ArrayList<String>();
            Params.add(outObj.getUsername() );

            // connects to and runs the query against the database returns null if an error occurs connecting to or closing the dabatse
            db.connect();
            Values = db.getData(sqlStatement, Params);
            Value = db.getData(sqlStatementTwo, Params);
            db.close();

        }
        catch(DLException dle){
            System.out.println("An error occured attempting to get the info on a capstone");
            return null;
        }

        try{

            //
            outObj.setCapstoneStartDate(Values.get(0).get(0));
            outObj.setMasterStart(Value.get(0).get(1));

            return outObj;


        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        return null;
    }

    public user_info GetUserInfo(user_info outObj){

        ArrayList<ArrayList<String>> Values;
        try{
            String sqlStatement = "SELECT UserType,Name,Email,`Phone Number`,Department FROM users WHERE Username=?;";

            //arraylist of parameters for the following method
            ArrayList<String> Params = new ArrayList<String>();
            Params.add(outObj.getUsername());
            db.connect();
            Values = db.getData(sqlStatement, Params);
            db.close();

        }catch(DLException dle){
            return null;

        }
        try
        {
            //how to pull all fields from the user table
            outObj.setUserName(Values.get(0).get(1));
            outObj.setUserType(Values.get(0).get(2));
            outObj.setName(Values.get(0).get(3));
            outObj.setEmail(Values.get(0).get(4));
            outObj.setPhoneNumber(Values.get(0).get(5));
            outObj.setDepartment(Values.get(0).get(6));

            return outObj;



        }catch(Exception e){


        }
        return null;
    }

    public commitee_info GetCommitee(user_info outObj){

        ArrayList<ArrayList<String>> Values;
        commitee_info outObj2=outObj.getCommitees().get(0);
        try{
            String sqlStatement = "";

            //arraylist of parameters for the following method
            ArrayList<String> Params = new ArrayList<String>();
            Params.add(outObj.getUsername());
            db.connect();
            Values = db.getData(sqlStatement, Params);
            db.close();

        }catch(DLException dle){
            return null;

        }
        try
        {
            //how to pull all fields from the user table
            outObj2.setUserName(Values.get(0).get(1));
            outObj2.setCapStoneID(Values.get(0).get(2));
            outObj2.setHasAccepted(Values.get(0).get(3));
            outObj2.setHasDecline(Values.get(0).get(4));
            outObj2.setPosition(Values.get(0).get(5));
            outObj2.setTracking(Values.get(0).get(6));

            return outObj2;



        }catch(Exception e){


        }
        return null;

    }

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