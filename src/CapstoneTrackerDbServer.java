
/* this class determinne what function needs to be called and uses capstonetrackerDBInterface to do so
* This is needed because ultimately the client and the server will be comuncating over the network and thus the client
*  cannot directly call anything on the server
*  For now this is NOT threaded and as a result does NOT support multiple concurrent users, this is a delibrate
*  choice to make things simpler for now
*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class CapstoneTrackerDbServer {

    private CapstoneTrackerDBInterface dbInterface;
    // objects to handle  io for connected client
    private Socket connect;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    // constructor that creates a interface for this instance and then goes into the listenForConnection method to wait for a connection
    public CapstoneTrackerDbServer(){
        dbInterface=new CapstoneTrackerDBInterface();
        listenForConnection();
    }
    // method that has a serverSocket that Listens for a connection
    public void listenForConnection(){
        try{
            // creates a server socket
            ServerSocket ss=new ServerSocket(4242);
            while(true){
                System.out.println("Waiting for a client...");
                Socket connect= ss.accept();
                System.out.println("A client has connectedw waiting for login");
                input=new ObjectInputStream(connect.getInputStream());
                output=new ObjectOutputStream(connect.getOutputStream());
                login();



            }
        }
        catch(IOException ioe){
            System.out.println("An error occured while listening for connection, please restart the server");
        }
        catch (Exception e){
            System.out.println("An  unexpected error occured while listening for connection, please restart the server");
        }

    }


    // function to handle login
    private void login(){
        // try catch to handle errors
        try{
            // boolean to control loop
            boolean notLoggedIn=true;
            // boolean to control whether or not to move ont the next part
            boolean quit=false;
            while(notLoggedIn){
                // string to determine if the user wants to log in or quit
                String action= (String)input.readObject();
                // tries to log in if the user wants to
                if(action.equals("login")){
                    // gets the loginInfo object that should be sent
                    loginInfo credentials=(loginInfo)input.readObject();
                    //then calls the login function using that object and stores the results
                    user_info user=dbInterface.Login(credentials);
                    /* if login was success the returned result is not null so the server then indicates the login sucedded and sends back the user_info object before changing
                    *  the not Logged in to false and which breaks out of the loop allowing the program to go to the controlfuction;
                    */
                    if(user!=null){
                        output.writeObject("login");
                        output.flush();
                        output.writeObject(user);
                        output.flush();
                        notLoggedIn=false;
                    }
                    // otherwise infomrs the client the login failed
                    else{
                        output.writeObject("login_failed");
                        output.flush();
                    }
                }
                // if the user does not it ends the connection, sets quit to true, sets notLoggedIn to false to break out, and then ends the connection
                else{
                    output.writeObject("Closeing conection");
                    output.flush();
                    quit=true;
                    notLoggedIn=false;
                    output.close();
                    connect.close();
                }
            }
            // if the loop w
            if(quit==false){
                controlerFunction();
            }


        }
        catch(IOException ioe){
            System.out.println("An network error has occured when trying to login");
        }
        catch (Exception e){
            System.out.println("An  unexpected error occured while listening for connection");
        }
    }

    private void controlerFunction(){

    }



}
