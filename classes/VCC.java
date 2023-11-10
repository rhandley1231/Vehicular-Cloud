package classes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VCC {
    public static ArrayList<job> currentJobList = new ArrayList<job>();
    public static ArrayList<vehicle> currentParkedVehicles = new ArrayList<vehicle>();
    public static ArrayList<cRR> computationalResourceRequestors = new ArrayList<cRR>();
    public static ArrayList<vO> vehicleOwners = new ArrayList<vO>();

    public static String password; // Will not have a setter because a random password should be given to the
    // garage owner/admin to access this classes features

    public static void createJob(int clientID,int jobID, int jobDuration, String deadline) {
        job newJob = new job(jobID, jobDuration, deadline);
        cRR req = new cRR(clientID);
        VCC.currentJobList.add(newJob);
        VCC.computationalResourceRequestors.add(req);
        // Write the job details to the "jobs.txt" file
        writeJobToFile(req, newJob);
    }

    private static void writeJobToFile(cRR r,job newJob) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobs.txt", true))) {
            // Format the job details as a string
            String jobDetails =r.getUserID() + ", " + newJob.getJobID() + "," + newJob.getJobDuration() + "," + newJob.getDeadline();
            // Write the job details to the file
            writer.write(jobDetails);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., log an error message or throw an exception
        }
    }

    public static void createVOandV(int vOID, String make, String model, String plate) {
        vehicle newVehicle = new vehicle(make, model, plate);
        vO newVO = new vO(vOID, newVehicle);
        VCC.currentParkedVehicles.add(newVehicle);
        VCC.vehicleOwners.add(newVO);
        // Writes to VO
        writeVOToFile(newVO);
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

    private static void writeCompletionTimesToFile(ArrayList[] a) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("completion_times.txt", true))) {
            // Write the a[0] values on the first line
            for (int i = 0; i < a[0].size(); i++) {
                writer.write(a[0].get(i).toString());
                if (i < a[0].size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();

            // Write the a[1] values on the second line
            for (int i = 0; i < a[1].size(); i++) {
                writer.write(a[1].get(i).toString());
                if (i < a[1].size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();

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

    public static void viewVehicle(vehicle a) {
        System.out.println("Make: " + a.getMake() + ", Model: " + a.getModel() + ", License Plate: " + a.getPlate());
    }

    public static void viewJob(job a) {
        System.out.println("Job ID: " + a.getJobID() + "Job Duration: " + a.getJobDuration());
        System.out.println("Deadline(Hours from start): " + (a.getDeadline()));
    }

}
