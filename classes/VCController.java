package classes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VCController {
    public static List<job> currentJobList = new ArrayList<job>();
    public static List<vehicle> currentParkedVehicles = new ArrayList<vehicle>();
    public static List<computationalResourceRequestor> computationalResourceRequestors = new ArrayList<computationalResourceRequestor>();
    public static List<vehicleOwner> vehicleOwners = new ArrayList<vehicleOwner>();

    public static String password; // Will not have a setter because a random password should be given to the
    // garage owner/admin to access this classes features

    // Completion time algorithm
    public List<Integer> jobCompletion(int[] jobTimes) {
        List<Integer> jobCompletionTimes = new ArrayList<>();
        int totalTime = 0;
        for (int i : jobTimes) {
            totalTime += i;
            jobCompletionTimes.add(totalTime);
        }
        return jobCompletionTimes;
    }

    public void viewCurrentParkedVehicles() {
        for (int i = 0; i < currentParkedVehicles.size(); i++) {
            if (currentParkedVehicles.get(i).getParked() == false) {
                viewVehicle(currentParkedVehicles.get(i));
            }
        }
    }

    public void viewCurrentJobList() { // Status: In Progress | Paused
        for (int i = 0; i < currentJobList.size(); i++) {
            if (currentJobList.get(i).getStatus() == "In Progress" || currentJobList.get(i).getStatus() == "Paused"
                    || currentJobList.get(i).getStatus() == "Not Started") {
                viewJob(currentJobList.get(i));
            }
        }
    }

    public void assignJobToVehicles() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input an ID for the job you want to assign:");
        int jobID = in.nextInt();
        boolean jobIDExists = false;
        for (int i = 0; i < currentJobList.size(); i++) {
            if (currentJobList.get(i).getStatus() == "Not Started" && currentJobList.get(i).getJobID() == jobID) {
                jobIDExists = true;
                boolean exit3 = false;
                while (!exit3) {
                    ArrayList<String> vehiclesPlates = new ArrayList<String>();
                    System.out.println(
                            "Enter an license plate of a vehicle that you would like to assign to this job(Enter 'quit' to exit)");
                    String vehiclePlate = in.next();
                    if (vehiclePlate == "quit") {
                        exit3 = true;
                    } else {
                        for (int j = 0; j < currentParkedVehicles.size(); j++) {
                            if (currentParkedVehicles.get(j).getPlate() == vehiclePlate) {
                                vehiclesPlates.add(vehiclePlate);
                                currentParkedVehicles.get(j).setAssignedToJobID(jobID);
                            } else {
                                System.out.println("License plate not found in system.");
                            }
                        }
                        currentJobList.get(i).setStatus("In Progress");
                    }
                    currentJobList.get(i).assignVehiclesToJob(vehiclesPlates);
                }
            }
            if (jobIDExists == false) {
                System.out.println("The ID you selected is either already assigned to a vehicle or does not exist.");
            }
        }
    }

    public static void checkpoint(vehicle a) {
        // Print the job ID and the status of the job that the vehicle has been working
        // on
        // Remove the vehicle from the job list
        // check if the vehicle list is empty, if it is, change job progress to paused
        if (a.getAssignedToJobID() == 0) {
        }
        // Remove the vehicle from the job list
        else {
            for (int i = 0; i < currentParkedVehicles.size(); i++) {
                if (currentParkedVehicles.get(i).getAssignedToJobID() == a.getAssignedToJobID()) { // first find the
                                                                                                   // vehicle assigned
                                                                                                   // to the job
                    // Found the vehicle where the jobID matches
                    // Now go into that job's list and remove the vehicle
                    for (int j = 0; j < currentJobList.size(); j++) {
                        if (currentJobList.get(j).getJobID() == a.getAssignedToJobID()) { // then find vehicle in the
                                                                                          // job that you are looking
                                                                                          // for
                            currentJobList.get(j).removeVehicleIdToJob(a.getPlate()); // then remove the vehicle from
                                                                                      // the job list
                            // check if the vehicle list is empty, if it is, change job progress to paused
                            if (currentJobList.get(j).getAssignedToVehicleID() == null) {
                                currentJobList.get(j).setStatus("Paused");
                            }
                            System.out.println("Job Id " + currentJobList.get(j).getJobID() + "is currently"
                                    + currentJobList.get(j).getStatus());
                        }
                    }
                }
            }
        }
    }

    public static void viewVehicle(vehicle a) {
        System.out.println("Make: " + a.getMake() + ", Model: " + a.getModel() + ", Year: " + a.getYear()
                + ", License Plate: " + a.getPlate() + ", Parked Status: " + a.getParked());
    }

    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public static void viewJob(job a) {
        System.out.println("Job Title: " + a.getJobTitle() + ", Job Description: " + a.getJobDescription());
        System.out.println("Job Duration: " + a.getJobDuration() + " hours, Job ID: " + a.getJobID()
                + ", Resource Requestor ID: " + a.getCRR_ID());
        System.out.println("Status: " + a.getStatus() + ", Deadline: " + dateFormat.format(a.getDeadline()));
    }
}
