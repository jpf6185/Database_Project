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
   private ArrayList<String> errorLog = new ArrayList<String>();
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
      private Thread.UncaughtExceptionHandler ex;
      private Socket innerCs;
      private String fromClient;
      private ArrayList<String> data = new ArrayList<String>();
      private ObjectInputStream inStream = null;
      private ObjectOutputStream outStream = null;
      private Object obj = null;
      private MySQLDatabase openDB = null;
      private boolean loginStatus = true;
      
      //private PrintWriter pout;
      //private BufferedReader br;
      
      // ThreadedServer Constructor
      public ThreadedServer( Socket _cs ){
         innerCs = _cs;
         openDB = new MySQLDatabase();
      } // end Constructor Method
      
      // Begins running Thread
      public void run(){
         
         try{
         
            while (loginStatus){
            
               System.out.println("awaiting for login action from" + innerCs.getPort() + ".");
               inStream = new ObjectInputStream(innerCs.getInputStream());
               obj = (LoginInfo)inStream.readObject();
               inStream.close();
               try{
               // Connect to Database
               loginStatus = processLogin(obj);
               }
               catch (InterruptedException ie){
                  throw new DLException(ie);
               }
               
            }
         
         }
         catch (RuntimeException rte){
            throw new DLException(rte);
         }
         catch (IOException ioe){
            System.out.println(innerCs.getPort() + " has left.");
         }
         catch (ClassNotFoundException cnfe){
            System.out.println("Error occurred at Server-side:" + cnfe.getMessage());
         }
         
      } // end run() Method

      
      public boolean processLogin(Object _obj) throws DLException{
      
         boolean status = true;
      
         try{
            openDB.connect();
         }
         catch (DLException dle){
            System.out.println("Error: Server-processLogin(): " + dle.getMessage());
            throw new DLException(dle);
         }
         return status;
      }
      
   } // end ThreadedServer class
} // end Server class