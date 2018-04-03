import java.util.ArrayList;

public class CapstoneTrackerServer {

    /* method to Get a Get the info about a current version of the capstone from the database bassed on the capstoneID
     * In the CapstoneInfo object passed in and then stores that info in the capstoneInfo object passed in before reurning it
     */
    MySQLDatabase db;

    // default constructor to initialize database object
    public CapstoneTrackerServer() {
        db = new MySQLDatabase();
    }


    public CapstoneInfo GetCapstoneInfo(CapstoneInfo outObj) {
        // arraylist to hold data returned
        ArrayList<ArrayList<String>> Values;
        try {
            // sql statement to pull out data from database
            String sqlStatement = "select capstone_info.CapStoneId,`Date:`," +
                    "Plagerism_Score,Grade,DefenseDate,Title,Capstone_Version.Description,FileLocation,Name,`type`,status from" +
                    " Capstone_Info JOIN capstone_Version ON capstone_info.capstoneId=capstone_Version.capstoneId" +
                    " JOIN status_code ON status_code.SID=capstone_Version.status WHERE capstone_info.capstoneId=?" +
                    " AND Lattest_Date=`Date:`; ";
            // arraylist of paramaters for the folliwing method
            ArrayList<String> Params = new ArrayList<String>();
            Params.add(outObj.getCapstoneID());
            // connects to and runs the query against the database returns null if an error occurs connecting to or closing the dabatse
            db.connect();
            Values = db.getData(sqlStatement, Params);
            db.close();


        } catch (DLException dle) {
            System.out.println("An error occured attempting to get the info on a capstone");
            return null;
        }
        try{
            // sets the lattest date in outobj
            outObj.setLatestDate(Values.get(0).get(1));
            // sets the plagerism score
            outObj.setPlagiarismScore(Values.get(0).get(2));
            // sets the grade
            outObj.setGrade(Values.get(0).get(3));
            // sets the defenseDAte
            outObj.setDefenseDate(Values.get(0).get(4));
            // object to map to the capstone version table
            CapstoneVersion outObj2=new CapstoneVersion(outObj.getCapstoneID(),outObj.getLatestDate());
            // adds outobj2 to outobj.
            outObj.addVersion(outObj2);
            // sets the rest of the paramater for object 2
            outObj2.setTitle(Values.get(0).get(5));
            outObj2.setDescription(Values.get(0).get(6));
            outObj2.setFilePath(Values.get(0).get(7));
            outObj2.setStatus(Values.get(0).get(8));
            outObj2.setType(Values.get(0).get(9));
            outObj2.setStatusCode(Values.get(0).get(10));
        }
        catch(Exception E){
            return null;
        }
        return outObj;
    }
    public CapstoneInfo GetCapstoneVersions(CapstoneInfo outObj) {
        // arraylist to hold data returned
        ArrayList<ArrayList<String>> Values;
        try {

            // sql statement to pull out data from database
            String sqlStatement = "Select CapstoneId, `Date:`,name,Title,Capstone_Version.description,`Type`,FileLocation " +
                    "FROM capstone_Version JOIN status_code on capstone_Version.Status=status_code.SID Where capstoneId=?;";
            // arraylist of paramaters for the folliwing method
            ArrayList<String> Params = new ArrayList<String>();
            Params.add(outObj.getCapstoneID());
            // connects to and runs the query against the database returns null if an error occurs connecting to or closing the dabatse
            db.connect();
            Values = db.getData(sqlStatement, Params);
            db.close();


        } catch (DLException dle) {
            System.out.println("An error occured attempting to get the info on a capstone");
            return null;
        }
        try {
            // loops over each entry in the array lis and creates a corispond Capstonversion object and adds it to the capstone info object
            for (ArrayList<String> info : Values) {
                CapstoneVersion outObj2 = new CapstoneVersion(info.get(0), info.get(1));
                outObj2.setStatus(info.get(2));
                outObj2.setTitle(info.get(3));
                outObj2.setDescription(info.get(4));
                outObj2.setType(info.get(5));
                outObj2.setFilePath(info.get(6));
                outObj.addVersion(outObj2);
            }
        } catch (Exception E) {
            return null;
        }
        return outObj;
    }
    // updates the capstone info by inserts new capstone version and changing the lattest date in capstone_info table to
    // the date in the provided capstoneVersion Object
    public Boolean updateCapstone(CapstoneInfo inObj){
        try {
            // grabs the capstone version object that conatins much of the data, asside from capstoneID

            CapstoneVersion inObj2=inObj.GetVersions().get(0);

            ArrayList<String>Params=new ArrayList<String>();
            ArrayList<String>Params2=new ArrayList<String>();
            String insertStatement = "Insert INTO Capstone_Version Values(?,?,?,?,?,?,?);";
            String updateStatement="Update Capstone_Info Set Lattest_Date=? Where CapstoneID=?";
            //sets the paramaters useing inObj1 and inObj2
            Params.add(inObj2.getDate());
            Params.add(inObj.getCapstoneID());
            Params.add(inObj2.getStatusCode());
            Params.add(inObj2.getTitle());
            Params.add(inObj2.getDescription());
            Params.add(inObj2.getType());
            Params.add(inObj2.getFilePath());
            Params2.add(inObj2.getDate());
            Params2.add(inObj.getCapstoneID());
            // todo make this a transaction dont curently have enough time

            db.connect();
            db.setData(insertStatement,Params);
            db.setData(updateStatement,Params2);
            db.close();
            return true
        }
        catch(DLException dle){
            System.out.println("an error has ocured when trying to update the capstone project");
        }
        return false;
    }

}