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
      Equipment equip = new Equipment(); 
    
      try{
        
         // set equip id
         equip.setEquipId(568);
          
         //display
         equip.fetch();
        
         //set equip capacaity 
         equip.setEquipCapacity(385); 
         
         //update
         equip.put(); 
         
         //display 
         equip.fetch(); 
      }
      catch(Exception e){
         System.out.println("DLEXception error:" + e.getMessage()); 
      }
   }
}