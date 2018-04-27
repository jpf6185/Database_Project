
/* this class determinne what function needs to be called and uses capstonetrackerDBInterface to do so
* This is needed because ultimately the client and the server will be comuncating over the network and thus the client
*  cannot directly call anything on the server
*  For now this is NOT threaded and as a result does NOT support multiple concurrent users, this is a delibrate
*  choice to make things simpler for now
*/

import javax.print.attribute.standard.JobOriginatingUserName;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class CapstoneTrackerDbServer extends Thread {

    private CapstoneTrackerDBInterface dbInterface;
    // objects to handle  io for connected client
    private Socket connect;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    // constructor that creates a interface for this instance and then goes into the listenForConnection method to wait for a connection

    public static void main(String[] args) {
        CapstoneTrackerDbServer server = new CapstoneTrackerDbServer();
        Thread th = new Thread(server);
        th.run();
    }


    public CapstoneTrackerDbServer() {
        dbInterface = new CapstoneTrackerDBInterface();
    }

    // method that has a serverSocket that Listens for a connection
    public void run() {
        try {
            // creates a server socket
            ServerSocket ss = new ServerSocket(4242);
            while (true) {
                System.out.println("Waiting for a client...");
                Socket connect = ss.accept();
                System.out.println("A client has connectedw waiting for login");
                InputStream in = connect.getInputStream();
                System.out.println("creating input");
                input = new ObjectInputStream(in);
                System.out.println("input created");
                OutputStream out = connect.getOutputStream();
                System.out.println("creating output");
                output = new ObjectOutputStream(out);
                System.out.println("Output created");
                login();


            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            System.out.println("An error occured while listening for connection, please restart the server");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("An  unexpected error occured while listening for connection, please restart the server");
        }

    }


    // function to handle login
    private void login() {
        // try catch to handle errors
        try {
            // boolean to control loop  
            boolean notLoggedIn = true;
            // boolean to control whether or not to move ont the next part
            boolean quit = false;
            while (notLoggedIn) {
                // string to determine if the user wants to log in or quit
                String action = ((String) input.readObject()).toLowerCase();
                // tries to log in if the user wants to
                if (action.equals("login")) {
                    // gets the loginInfo object that should be sent
                    loginInfo credentials = (loginInfo) input.readObject();
                    //then calls the login function using that object and stores the results
                    UserInfo user = dbInterface.Login(credentials);
                    /* if login was success the returned result is not null so the server then indicates the login sucedded and sends back the UserInfo object before changing
                     *  the not Logged in to false and which breaks out of the loop allowing the program to go to the controlfuction;
                     */
                    if (user != null) {
                        output.writeObject("login");
                        output.flush();
                        output.writeObject(user);
                        output.flush();
                        notLoggedIn = false;
                        System.out.println(user.getUsername());
                    }
                    // otherwise infomrs the client the login failed
                    else {
                        output.writeObject("login_failed");
                        System.out.println("Login_failed");
                        output.flush();
                    }
                }
                // if the user does not it ends the connection, sets quit to true, sets notLoggedIn to false to break out, and then ends the connection
                else {
                    output.writeObject("Closeing conection");
                    output.flush();
                    quit = true;
                    notLoggedIn = false;
                    output.close();
                    connect.close();
                }
            }
            // if the loop
            if (quit == false) {
                controllerFunction();
            }


        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            System.out.println("An network error has occured when trying to login");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("An  unexpected error occured while listening for connection");
        }
    }

    // function with a switch that takes in a imput from the client and decides what to do.
    private void controllerFunction() {
        // boolean to control loop
        try {
            boolean keepGoing = true;
            while (keepGoing) {
                // reads in the action the server send
                String action = ((String) input.readObject()).toLowerCase();
                // uses a big switch statement to decide what Iam
                switch (action) {
                    case "getcapstoneinfo":
                        callGetCapstoneInfo();
                        break;
                    case "getuserinfo":
                        callGetUserInfo();
                        break;
                    case "getcommiteecapstones":
                        callGetCommiteeCapstones();
                        break;
                    case "getpendinginvites":
                        callGetPendingInvites();
                        break;
                    case "getcapstoneversions":
                        callGetCapstoneVersions();
                        break;
                    case "updatecapstone":
                        callUpdateCapstone();
                        break;
                    case "getcommitees":
                        callGetCommitees();
                        break;
                    case "getstatuses":
                        callGetStatuses();
                        break;
                    case "gettrackedcapstones":
                        callGetTrackedCapstones();
                        break;
                    case "getallcapstones":
                        callGetAllCapstones();
                        break;
                    case "addinvite":
                        callAddInvite();
                        break;
                    case "uploadfile": storeFile();
                        break;
                    default:
                        keepGoing = false;

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("A error occured in operation");
        }
    }

    // gets all the possible statuses of a project
    private void callGetStatuses() {
        try {
            Vector<StatusInfo> info = dbInterface.getAllStatuses();
            output.writeObject(info);
            output.flush();
        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get a list of statuses: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get a list of satuses " + e.getMessage());
        }
    }

    // updates a capstone with the values recived
    private void callUpdateCapstone() {
        try {
            // gets the capstoneinfo
            CapstoneInfo info = (CapstoneInfo) input.readObject();
            dbInterface.updateCapstone(info);
            info = dbInterface.GetCapstoneInfo(info);
            output.writeObject(info);
            output.flush();
        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get info on committes: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get the info on a comitees: " + e.getMessage());
        }
    }

    // gets the commmitees associated with a user
    private void callGetCommitees() {
        try {
            UserInfo user = (UserInfo) input.readObject();
            user = dbInterface.GetCommitees(user);
            output.writeObject(user);
            output.flush();
        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get info on committes: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get the info on a comitees: " + e.getMessage());
        }
    }

    // method to get the info about a capstone
    private void callGetCapstoneInfo() {
        try {
            CapstoneInfo capstone = (CapstoneInfo) input.readObject();
            capstone = dbInterface.GetCapstoneInfo(capstone);
            output.writeObject(capstone);
            output.flush();
        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get info on a capstone: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get the info on a capstone: " + e.getMessage());
        }
    }

    // method to get info about a user and send it to the client
    private void callGetUserInfo() {
        try {
            UserInfo user = (UserInfo) input.readObject();
            user = dbInterface.GetUserInfo(user);
            output.writeObject(user);
            output.flush();

        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get info on a user" + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get the info on a user" + e.getMessage());
        }
    }

    // gets all the capstones a staff is the commitee member of
    private void callGetCommiteeCapstones() {
        try {
            UserInfo user = (UserInfo) input.readObject();
            ArrayList<CapstoneInfo> capstones = dbInterface.GetCommiteeCapstones(user);
            output.writeObject(capstones);
            output.flush();

        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get a list of committes a user in on" + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get a list of committes a user in on" + e.getMessage());
        }
    }

    // gets all the pending invites a faculty has;
    private void callGetPendingInvites() {
        try {
            UserInfo user = (UserInfo) input.readObject();
            ArrayList<InviteInfo> capstones = dbInterface.getPendingInvites(user);
            output.writeObject(capstones);
            output.flush();

        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get info on a user pending invites" + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get the info on a users pending invites" + e.getMessage());
        }
    }

    // gets all the capstones a faculty meber is tracker
    private void callGetTrackedCapstones() {
        try {
            UserInfo info = (UserInfo) input.readObject();
            ArrayList<CapstoneInfo> capstones = dbInterface.GetTrackedCapstones(info);
            output.writeObject(capstones);
            output.flush();

        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get all the tracked capstone for a faculty" + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get get all the tracked capstone for a faculty" + e.getMessage());
        }
    }

    private void callGetAllCapstones() {
        try {
            ArrayList<CapstoneInfo> capstones = dbInterface.GetAllCapstones();
            output.writeObject(capstones);
            output.flush();

        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to get all the capstones" + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get get all the capstones" + e.getMessage());
        }
    }

    // method to add an invite that was sent
    private void callAddInvite() {
        try {
            commitee_info info = (commitee_info) input.readObject();
            boolean success = dbInterface.addInvite(info);
            output.writeBoolean(success);
            output.flush();
        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to send an invite" + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to send an invite" + e.getMessage());
        }
    }

    private void callGetCapstoneVersions(){
        try {
            CapstoneInfo info=(CapstoneInfo)input.readObject();
            CapstoneInfo versions=dbInterface.GetCapstoneVersions(info);
            output.writeObject(versions);
            output.flush();
        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying get all versions of a capstone " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to get all versions of a capstone " + e.getMessage());
        }
    }
    // method to store a file uploaded
    // this is horribly inficiant as it store the whole file in ram before writing it but its what was easiest to write
    private void storeFile(){
        // username of the user uplaodin a file
        String userName;
        // the path the program is running in
        String curPath=System.getProperty("user.dir");
        // the full path the file will be stored in
        String fullPath;
        //length fo the file in byte
        int fileLength;
        //file object for the uploaded file
        File uploadedFile=null;
        try {
            // reads in the username of the user uploading the file
            userName=(String)input.readObject();
            //file object that represent the users directory
            File userDir=new File(curPath+"/"+userName);
            // if the directory curpath/username does not exist
            if(!(userDir.exists() && userDir.isDirectory())){
                userDir.mkdir();
            }
            // generates a random series of digits and appends that to the current path and username to create the full path
            boolean isUnique=false;
            //insures the path is unique
            while(!(isUnique)){

                fullPath=curPath+"/"+userName+"/"+randomString();
                System.out.println(fullPath);
                uploadedFile=new File(fullPath);
                if(!(uploadedFile.exists())){
                    isUnique=true;
                }
            }

            // reads in the file length
            fileLength=input.readInt();
            // then creates a buffer of the correct size
            byte[]fileByes=new byte[fileLength];
            // creats writer to write the file
            uploadedFile.createNewFile();
            FileOutputStream fis=new FileOutputStream(uploadedFile);
            // reads in all of the bytes
            input.readFully(fileByes,0,fileLength);
            // and then writes them all out
            fis.write(fileByes,0,fileLength);
            fis.close();
            output.writeBoolean(true);

        } catch (IOException ioe) {
            System.out.println("the following IOException has occured trying to save a capstone " + ioe.getMessage());
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("the following exception has occured trying to save a capstone " + e.getMessage());
        }
        try {

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    // generates random string for storing file
    public String randomString(){
        // charcters the random string consists off
        String allowedChars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // stringubild to build the string
        StringBuilder randomeBuilder=new StringBuilder();
        // random generator
        Random rnd=new Random();
        while(randomeBuilder.length()<30){
            // generates a random number between 0 and 1 and uses that to select a number that represent the length of the stirng
            int charToAppened=(int)(rnd.nextFloat()*allowedChars.length());
            // appends the cahricter
            randomeBuilder.append(allowedChars.charAt(charToAppened));

        }
        String randomString= randomeBuilder.toString();
        return randomString;
    }

}
