import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
/*
* @author   Vincent Venutolo, Jacob Feiner, Chris Bonsu, and Ian Anyala
* @version  1.0
* implements Server to handle the rest of Profile & Capstone data.
*/
public class Server {
   
   // Attribute Declarations
   private int randomNum = -1;
   private boolean noBingo = true;
   private Vector<PrintWriter> clients = new Vector<PrintWriter>();
   private ServerSocket ss;
   private ObjectInputStream inStream = null;
   //private ArrayList<String> usernameArray = new ArrayList<String>();
   
   // Main Method for starting Server
   public static void main(String[] args) {
      new Server();
   } // end Main Method
   
   // Server Contructor
   public Server() {
      
      try{
         // Create a server socket for incoming connections
         ss = new ServerSocket(4445);
         
         // Begins Listening to Client upcoming connections
         while (true){
            System.out.println("Waiting for a client...");
            Socket cs = ss.accept(); // This waits for client
            
            System.out.println("We have a client: " + cs);
            
            // Create a thread for the client and start
            ThreadedServer ts = new ThreadedServer( cs );
            Thread th = new Thread(ts);
            th.start();
         }
      }
      catch (IOException ioe){
         System.out.println("Server Socket Host Error" + ioe.getMessage());
      }
      
   } // end Server Constructor

///////////////////////////////////////////////
//////////// ThreadedServer class /////////////
///////////////////////////////////////////////
   class ThreadedServer extends Thread{
      
      // Attributes
      private Socket innerCs;
      private String fromClient;
      private ArrayList<String> loginData = new ArrayList<String>();
      private ObjectInputStream inStream = null;
      private ObjectOutputStream outStream = null;
      private Object obj = null;
      //private PrintWriter pout;
      //private BufferedReader br;
      
      // ThreadedServer Constructor
      public ThreadedServer( Socket _cs ){
         innerCs = _cs;
      } // end Constructor Method
      
      // Begins running Thread
      public void run(){
         
         System.out.println("awaiting for login action from" + innerCs.getPort() + ".");
         try{
         
            while (true){
               inStream = new ObjectInputStream(innerCs.getInputStream());
               obj = inStream.readObject();
               loginData = (ArrayList<String>) obj;
               inStream.close();
               System.out.println(loginData.get(0));
               System.out.println(loginData.get(1));
            }
         
         }
         catch (IOException ioe){
            System.out.println(innerCs.getPort() + " has left.");
         }
         catch (ClassNotFoundException cnfe){
            System.out.println("Error occurred at Server-side:" + cnfe.getMessage());
         }
         
      } // end run() Method
      
   } // end ThreadedServer class
} // end Server class