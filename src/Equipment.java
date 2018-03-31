import java.util.*; 
import java.sql.*;

/**
*  Name:   Chris Bonsu
*  Date:   3/20/2018
*  Course: ISTE 330
*  PE07:  
*/ 

public class Equipment
{
   //Attributes
   private int equipId;
   private int equipCapacity; 
   private String equipName;
   private String equipDesc; 
    
   MySQLDatabase mysql = new MySQLDatabase();
   
   //Default constructor 
   public Equipment()
   {
      equipId = 0;
      equipCapacity = 0; 
      equipName = "default name";
      equipDesc = "default description"; 
   } //default constructor 
   
  //Parameterized Contructor for equipId attribute
   public Equipment(int _equipId)
   {
      equipId = _equipId; 
   }
   
   //Parameterized Constructor for all attirbutes
   public Equipment(int _equipId, int _equipCapacity, String _equipName, String _equipDesc)
   {
      equipId = _equipId;
      equipCapacity = _equipCapacity; 
      equipName = _equipName;
      equipDesc = _equipDesc; 
   }
   
   //mutator method
   public void setEquipId(int _equipId)
   {
      equipId = _equipId; 
   }
   
   //accessor method
   public int getEquipId()
   {
      return equipId; 
   }
   
   //mutator method
   public void setEquipCapacity(int _equipCapacity)
   {
      equipCapacity = _equipCapacity;
   }
   
   //accessor method
   public int getEquipCapacity()
   {
      return equipCapacity; 
   }
   
   //mutator method
   public void setEquipName(String _equipName)
   {
      equipName = _equipName; 
   }
   
   //accessor method
   public String getEquipName()
   {
      return equipName; 
   }
   
   //mutator method
   public void setEquipDesc(String _equipDesc)
   {
      equipDesc = _equipDesc;
   }
   
   //accessor method
   public String getEquipDesc()
   {
      return equipDesc;
   }
   
   //Fetch method
   public void fetch() throws DLException
   {
      ArrayList<String>values = new ArrayList<String>();
    
      if(mysql.connect())
      {   
         try{
            String sql = "SELECT * FROM Equipment WHERE EquipId = ?;"; 
            
            values.add(Integer.toString(equipId)); 
           
            ArrayList<ArrayList<String>> grabData = new ArrayList<ArrayList<String>>(); 
            grabData = mysql.getData(sql, values);
            
            for(int i = 1; i < grabData.size(); i++)
            {  
               setEquipId(Integer.parseInt(grabData.get(i).get(0))); 
               setEquipName(grabData.get(i).get(1)); 
               setEquipDesc(grabData.get(i).get(2));
               setEquipCapacity(Integer.parseInt(grabData.get(i).get(3)));   
            }
            System.out.println(grabData); 
         }
         catch (DLException e){
            throw new DLException(e);   
         } 
         mysql.close();
      }//end if
   }//end Fetch
   
   //put method
   public void put() throws DLException
   {
      ArrayList<String>values = new ArrayList<String>();
   
      if(mysql.connect())
      {
         String sql = "UPDATE Equipment SET EquipmentDescription=?" 
                       + ", EquipmentName=?" 
                       + ", EquipmentCapacity=?" 
                       +  " WHERE EquipId=?";
         
         values.add(equipDesc); 
         values.add(equipName); 
         values.add(Integer.toString(equipCapacity)); 
         values.add(Integer.toString(equipId)); 
               
         try{
            mysql.setData(sql, values); 
         }catch (DLException e){
            throw new DLException(e);
         }
      }
      mysql.close();  
   }//end put method
   
   //post method
   public void post() throws DLException
   {
      ArrayList<String>values = new ArrayList<String>();
   
      if(mysql.connect())
      {
         String sql = "INSERT INTO Equipment(EquipId, EquipmentName, EquipmentDescription, EquipmentCapacity)" + 
               "VALUES(?,?,?,?)"; 
             
         values.add(equipDesc); 
         values.add(equipName); 
         values.add(Integer.toString(equipCapacity)); 
         values.add(Integer.toString(equipId)); 
         
         try{
            mysql.setData(sql, values); 
         }catch(DLException e){
            throw new DLException(e);
         }
      }
     
      mysql.close(); 
   }//end post method
   
   //delete method
   public void delete() throws DLException
   { 
      ArrayList<String>values = new ArrayList<String>();
      
      if(mysql.connect())
      {
         String sql = "DELETE FROM Equipment WHERE EquipId = ?"; 
         
         try{
            mysql.setData(sql, values); 
         }catch(DLException e){
            throw new DLException(e);
         }
      }
     
      mysql.close(); 
   }//end delete method
}//end Equipment class