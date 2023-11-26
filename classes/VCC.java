package classes;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import java.awt.*;
public class VCC {
    //Adding server functionality
    private static final int PORT = 8080;
    private static ServerSocket server;
    private static ExecutorService executor;
    public static ArrayList<job> currentJobList = new ArrayList<job>();
    public static ArrayList<vehicle> currentParkedVehicles = new ArrayList<vehicle>();
    public static ArrayList<cRR> computationalResourceRequestors = new ArrayList<cRR>();
    public static ArrayList<vO> vehicleOwners = new ArrayList<vO>();
    private static Connection conn;
    private static String requestStatusMessage;

     public static String getRequestStatusMessage() {
        return requestStatusMessage;
    }
   






    public static String password; // Will not have a setter because a random password should be given to the
    // garage owner/admin to access this classes features
      public static void dbConnect(){


   
        try{
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/vcrtsDB";
        String DB_USER = "root";
        String DB_PASSWORD = "BruceAlmighty_23";


        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected To VCRTS Database");
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public static void runServer() {
        executor = Executors.newCachedThreadPool(); //Adds new threads as needed
        executor.submit(() -> {
        try{
            server = new ServerSocket(PORT);
            System.out.println("Server Online, listening for requests");
            while(true){
                //Accepts incoming connections
                Socket cSocket = server.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(cSocket.getInputStream());
                System.out.println("A client has connected to the server!");
                String data = objectInputStream.readUTF();
                handleIncomingData(data);
            }
        }
        catch(IOException e){
            e.getStackTrace();
        }
    });
    }


    public static void createJob(int clientID,int jobID, int jobDuration, String deadline) {
        job newJob = new job(jobID, jobDuration, deadline);
        cRR req = new cRR(clientID);
        VCC.currentJobList.add(newJob);
        VCC.computationalResourceRequestors.add(req);
        // Write the job details to the "jobs.txt" file
        writeCRRtoDB(req, newJob);

        if (writeCRRtoDBSuccessful()) {
            requestStatusMessage = "Job request accepted for Client ID: " + clientID;
        } else {
            requestStatusMessage = "Job request rejected for Client ID: " + clientID;
        }
    }
    }
    //Database writer for CRRs
    public static void writeCRRtoDB(cRR r, job j) {
        try{
            String query = "INSERT INTO CRR (ID, jobID, duration, deadline, timestamp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, r.getUserID());
            preparedStatement.setInt(2, j.getJobID());
            preparedStatement.setInt(3, j.getJobDuration());
            preparedStatement.setString(4, j.getDeadline());
            preparedStatement.setTimestamp(5, getCurrentTimestamp());
            preparedStatement.executeUpdate();
            conn.commit();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static void writeVOtoDB(vO v){
        try {
            String query = "INSERT INTO VO (ID, make, model, plate, timestamp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, v.getUserID());
            preparedStatement.setString(2, v.getVehicle().getMake());
            preparedStatement.setString(3, v.getVehicle().getModel());
            preparedStatement.setString(4, v.getVehicle().getPlate());
            preparedStatement.setTimestamp(5, getCurrentTimestamp());
            preparedStatement.executeUpdate();
            conn.commit();
        }
        catch(SQLException e){
            e.printStackTrace();
        }


    }
    public static void createVOandV(int vOID, String make, String model, String plate) {
        vehicle newVehicle = new vehicle(make, model, plate);
        vO newVO = new vO(vOID, newVehicle);
        VCC.currentParkedVehicles.add(newVehicle);
        VCC.vehicleOwners.add(newVO);
        // Writes to VO
        writeVOtoDB(newVO);

        if (writeVOtoDBSuccessful()) {
            requestStatusMessage = "Vehicle owner and vehicle request accepted for Vehicle Owner ID: " + vOID;
        } else {
            requestStatusMessage = "Vehicle owner and vehicle request rejected for Vehicle Owner ID: " + vOID;
        }
    
    }
 


    public void acceptVOReq(int vO_ID,vehicle newVehicle){
        createVOandV(vO_ID, newVehicle.getMake(), newVehicle.getModel(), newVehicle.getPlate());  
        System.out.println("Added information for user " + Integer.toString(vO_ID) + "to data structures.");
    }


    private static void writeVOToFile(vO newVO) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vOs.txt", true))) {
            // Format the newVO information as a string
            String voDetails = newVO.getUserID() + "," + newVO.getVehicle().getMake() + ","
                    + newVO.getVehicle().getModel() + "," + newVO.getVehicle().getPlate();


            // Write the newVO information to the file
            writer.write(voDetails);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., log an error message or throw an exception
        }
    }
    private static Timestamp getCurrentTimestamp() {
        Date currentDate = new Date();
        return new Timestamp(currentDate.getTime());
    }
     private static void handleVehicleOwnerData(String data) {
        // Use StringTokenizer to tokenize the data
        String clean = data.replaceAll("Vehicle and Owner Info: ", "");
        StringTokenizer tokenizer = new StringTokenizer(clean, " ");
       
        // Assuming the format is "UserID : Make : Model : Plate"
        int userID = Integer.parseInt(tokenizer.nextToken().trim());
        String make = tokenizer.nextToken().trim();
        String model = tokenizer.nextToken().trim();
        String plate = tokenizer.nextToken().trim();
        vehicle v = new vehicle(make, model, plate);
        // Create a VehicleOwner object and add it to the array list
        vO vehicleOwner = new vO(userID, v);
        createVOandV(userID, make, model, plate);
       
    }


    private static void handleJobInformationData(String data) {
        // Use StringTokenizer to tokenize the data
        String clean = data.replaceAll("Job Information: ", "");
        StringTokenizer tokenizer = new StringTokenizer(clean, " ");
       
        int cliID = Integer.parseInt(tokenizer.nextToken().trim());
        int jobID = Integer.parseInt(tokenizer.nextToken().trim());
        int jobDuration = Integer.parseInt(tokenizer.nextToken().trim());
        String deadline = tokenizer.nextToken().trim();
        // Create a JobInformation object and add it to the array list
        createJob(cliID, jobID, jobDuration, deadline);  
    }
    public static void handleIncomingData(String data) {
        JFrame alertFrame = new JFrame("Incoming Data Alert");
        alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel alertPanel = new JPanel();
        alertFrame.getContentPane().removeAll();
        alertFrame.add(alertPanel);


        alertPanel.setLayout(new BorderLayout());


        JTextArea dataTextArea = new JTextArea(data);
        dataTextArea.setEditable(false);
        dataTextArea.setWrapStyleWord(true);
        dataTextArea.setLineWrap(true);
        dataTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);


        JScrollPane dataScrollPane = new JScrollPane(dataTextArea);
        dataScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        JButton acceptButton = new JButton("Accept");
        JButton rejectButton = new JButton("Reject");


        acceptButton.addActionListener(e -> {
            // Check the data and perform appropriate actions
            if (data.startsWith("Vehicle and Owner Info: ")) {
                handleVehicleOwnerData(data);
            } else {
                handleJobInformationData(data);
            }
            alertFrame.dispose(); // Close the alert after processing
        });


        rejectButton.addActionListener(e -> {
            alertFrame.dispose(); // Close the alert without processing
        });


        alertPanel.add(dataScrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);


        alertPanel.add(buttonPanel, BorderLayout.SOUTH);


        alertFrame.pack();
        alertFrame.setLocationRelativeTo(null); // Center the frame on the screen
        alertFrame.setVisible(true);
    }




    // Completion time algorithm
    public static ArrayList[] jobCompletion() {
        ArrayList<Integer> jobIDs = new ArrayList<Integer>();
        for (int i = 0; i < currentJobList.size(); i++) {
            jobIDs.add(currentJobList.get(i).getJobID());
        }
        ArrayList<Integer> jobTimes = new ArrayList<Integer>();
        for (int i = 0; i < currentJobList.size(); i++) {
            jobTimes.add(currentJobList.get(i).getJobDuration());
        }
        ArrayList<Integer> jobCompletionTimes = new ArrayList<Integer>();
        int totalTime = 0;
        for (int i : jobTimes) {
            totalTime += i;
            jobCompletionTimes.add(totalTime);
        }
        ArrayList[] a = new ArrayList[3];
        a[0] = jobIDs;
        a[1] = jobTimes;
        a[2] = jobCompletionTimes;
        writeCompletionTimesToFile(a);
        return a;
    }

    private static boolean writeCRRtoDBSuccessful() {
        // Implement The Logic
        
        return true;
    }

    private static boolean writeVOtoDBSuccessful() {
        // Implement The Logic
        
        return true;
    }


    private static void writeCompletionTimesToFile(ArrayList[] a) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("completion_times.txt", true))) {
            // Write the a[0] values on the first line
            writer.write("Job IDs: ");
            for (int i = 0; i < a[0].size(); i++) {
                writer.write(a[0].get(i).toString());
                if (i < a[0].size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();


            writer.write("Individual Job Durations: ");
            // Write the a[1] values on the second line
            for (int i = 0; i < a[1].size(); i++) {
                writer.write(a[1].get(i).toString());
                if (i < a[1].size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();
            writer.write("Total computation time: ");


            // Write the a[2] values on the last line
            for (int i = 0; i < a[2].size(); i++) {
                writer.write(a[2].get(i).toString());
                if (i < a[2].size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., log an error message or throw an exception
        }
    }


    public void viewCurrentParkedVehicles() {
        for (int i = 0; i < currentParkedVehicles.size(); i++) {
            viewVehicle(currentParkedVehicles.get(i));
        }
    }


    































   

    

  

   
    

   
