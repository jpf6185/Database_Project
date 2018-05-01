/* Group 1
 * ISTE-330 Database Connectivity and access
 *  Written By Jacob Feiner, Ian Ayala, Chris Bonsu, Vincent Venutolo
 */
import java.sql.*;
import java.util.*;


/* this class is the Data layer, that handle interfacing with the Mysql Database
 */
public class MySQLDatabase
{
    //Modified Database class
    
   //Attributes 
   String sql;
   String uri = "jdbc:mysql://localhost/Capstone_Tracker?autoReconnect=true&useSSL=false";
   String driver = "com.mysql.jdbc.Driver";
   String user = "root";
   //String password = "student";
   String password="RYcGEpo!OVQklmdZ3Zf0";
   Connection conn = null;        
   Statement stmt = null; 
   
   StringBuilder printTable = new StringBuilder();  
  
    //MySQL Database contructor 
   public MySQLDatabase()
   {
      
   } //end constructor
   
   //Connect  method
   public boolean connect() throws DLException
   {
      
      // Load the driver
      try {
         Class.forName( driver );
         System.out.println("Driver loaded");
         conn = DriverManager.getConnection( uri, user, password );
         //System.out.println("MySQL database open");
         return true; 
      }catch(ClassNotFoundException cnfe ){
         System.out.println("Cannot find or load driver: "+ driver );
         System.exit(1);
         return false; 
      }catch(SQLException sqle ){
         System.out.println("Could not connect to db: "+uri );
         System.exit(1);
         return false; 
      }             
   }//end connect
   
   public boolean close() throws DLException
   {
      // Close the database connection
      boolean close = false; 
      try{
         if(conn != null)
         {
            conn.close();
     
            //System.out.println("MySQL Database closed");
         }
      }
      catch(SQLException sqle ){
         System.out.println("Could not close MySQL Database");
         System.exit(3);
         close = false; 
      }
      return close; 
   
   }
   //Prepare method
   public PreparedStatement prepare(String sql, ArrayList<String>values) throws DLException
   {
      PreparedStatement stmt = null;
       
      try{
         stmt = conn.prepareStatement(sql);
         
         for(int i = 0; i < values.size();  i++)
         {
         
            stmt.setString(i +1, values.get(i)); 
         }
      }catch(SQLException sqle){
         System.out.println(sqle.getMessage());
         System.out.println("Prepared Statement error"); 
      }
      return stmt;
   } 
 
   //first getData method
   public ArrayList<ArrayList<String>> getData(String sql) throws DLException
   {
    
      //2D arraylist of another Data
      ArrayList<ArrayList<String>> anotherData = new ArrayList<ArrayList<String>>();
   
      try{
         conn = DriverManager.getConnection( uri, user, password );
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);  
         ResultSetMetaData rsmd = rs.getMetaData();  
         int numFields = rsmd.getColumnCount(); 
        
        //Creating a Header for column names
         while(rs.next())
         {
            ArrayList<String>in = new ArrayList<String>(); 
         
            for(int i = 1; i<=numFields; i++)
            {
               in.add(rs.getString(i));
            }
            anotherData.add(in); 
         }
      
      }catch(SQLException sqle){
         System.out.println("SQL Exception");
         ArrayList<String>msg=new ArrayList<String>();
         msg.add(sql);
         throw new DLException(sqle,msg);
      }
   
      return anotherData; 
   }//end getData method
   
   //2nd GetData Method takes String and boolean values  
   public ArrayList<ArrayList<String>> getData(String sql, boolean test) throws DLException
   {
    
      //2D arraylist of another Data
      ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>(getData(sql));
       
      int nbrColumn = data.get(0).size(); 
      
      try{
      
         //if the boolean value is true, the first row of the 2D arraylist should return column name
         if(test == true){
         
            //ArrayList<String>in = new ArrayList<String>(); 
            for(int i = 0; i < nbrColumn; i++)
            {
               String colName = data.get(0).get(i).toString(); 
               printTable.append(String.format("%-20s", colName)); 
            } 
            //data.add(in); 
            printTable.append("\n"); 
         }

         return data; 
      }catch(Exception e){
         System.out.println("Exception Error"); 
         throw new DLException(e); 
      }
   }//end 2nd getData method

   //3rd getData method for prepare statement sql 
   public ArrayList<ArrayList<String>> getData(String sql, ArrayList<String> values) throws DLException
   {
      //2D Arraylist of another String 
      ArrayList<ArrayList<String>> anotherData = new ArrayList<ArrayList<String>>();
            
      try{
         
         PreparedStatement stmnt = prepare(sql, values);
         ResultSet rs = stmnt.executeQuery();  
         ResultSetMetaData rsmd = rs.getMetaData();  
         int numFields = rsmd.getColumnCount();
      	
         while(rs.next())
         {
            ArrayList<String> in = new ArrayList<String>(); 
         
            for(int i = 1; i <= numFields; i++)
            {
               in.add(rs.getString(i));
            }
            anotherData.add(in);  //add in arraylist to main arraylis
         }//end while 
      }catch(SQLException sqle){
         System.out.println("SQL Exception");
         ArrayList<String>msg=new ArrayList<String>();
         msg.add(sql);
         throw new DLException(sqle,msg);
      }
      return anotherData; 
   }
   //end 3rd getData method
   
   //2nd setData method
   public boolean setData(String sql, ArrayList<String> values) throws DLException
   {
      PreparedStatement stmnt = prepare(sql, values);
      try { 
         if(stmnt.execute())
         {
            return true;
         }
         else
         {
            return false; 
         }
      }
      catch(SQLException sqle){
         System.out.println("SQL Exception Error" + sqle.getMessage());
         throw new DLException(sqle );
      }
   }//end 2nd setdata method
   
   //execute statement method
   public int executeStmt(String sql, ArrayList<String> values) throws DLException
   {
      int intVal = 0; 
      PreparedStatement stmnt = prepare(sql, values);
      try{
         intVal = stmnt.executeUpdate(sql); 
      }catch(SQLException sqle){
         System.out.println("SQL Exception Error"); 
         throw new DLException(sqle );
      }
      return intVal;
   }// end execute statment method 
   
   //SetData method
   public boolean setData(String sql){
     
      try{
      
         System.out.println("Creating statement.."); 
         stmt = conn.createStatement();
         
         if(stmt.executeUpdate(sql) > 0)
         {
            return true; 
         }//end if
         else
         {
            return false; 
         }//end else 
      
      }//end try 
      catch(SQLException sqle){
         System.out.println("SQL exception"); 
         return false; 
      }
   }//SetData
   
   //descTable method 
   public void descTable(ArrayList<ArrayList<String>> data) throws DLException
   {
      try{
         //int fields = rsmd.getColumnCount(); 
         int nbrColumn = data.get(0).size(); 
      
         for(int i = 1; i < data.size(); i++)
         {
            for(int k = 0; k < nbrColumn; k++)
            {      
               if(data.get(i).get(k) == null)
               {   
                  printTable.append(String.format("%-20s", "null")); 
               } 
               else
               {
                  printTable.append(String.format("%-20s", data.get(i).get(k).toString()));
               } 
               
            }//end k loop
            printTable.append("\n");      
         }//end j loop 
      
         // printing table from DB
         System.out.println(printTable.toString()); 
      
      }catch(Exception e){
         System.out.println("Exception Error"); 
         throw new DLException(e); 
      }
   }
   // starts a transaction
   public void startTrans() throws DLException
   {

      try
      {

         conn.setAutoCommit(false);


      }
      catch(SQLException sqle){
         System.out.println(sqle.getMessage());
         ArrayList<String>errorMsg=new ArrayList<String>();
         errorMsg.add("MYSQLDatabase>startTransction");
         throw new DLException(sqle, errorMsg);
      }

   }
   // ends a transaction
   public void endTrans() throws DLException
   {
      try
      {
         //conn.commit();
         conn.setAutoCommit(true);
      }
      catch(SQLException sqle){
         ArrayList<String>errorMsg=new ArrayList<String>();
         errorMsg.add("MYSQLDatabase>endTransction");
         throw new DLException(sqle, errorMsg);
      }

   }
   // rolls back a transaction
   public void rollback() throws DLException
   {
      try
      {
         conn.rollback();
      }
      catch(SQLException sqle){
         ArrayList<String>errorMsg=new ArrayList<String>();
         errorMsg.add("MYSQLDatabase>RollbackTransaction");
         throw new DLException(sqle, errorMsg);
      }

   }
}//end MySQL Database class