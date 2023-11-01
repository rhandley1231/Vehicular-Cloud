package classes;
import java.text.SimpleDateFormat;
import java.util.*;

public class computationalResourceRequestor extends User {
    private ArrayList<job> jobList = new ArrayList<job>();

    public computationalResourceRequestor(String userName, String password, ArrayList<job> jobList) {
        super(userName, password);
        this.jobList = jobList;
    }

    // Methods for the Job Owner Class
    public void createJob(String jobTitle, String jobDescription, int jobDuration, int crr_ID, boolean assigned,
            Date deadline, String status) {
        job newJob = new job(jobTitle, jobDescription, jobDuration, crr_ID, assigned, deadline, status);
        this.jobList.add(newJob);
        VCController.currentJobList.add(newJob);
    }

    // Adds a job to the arraylist of jobs for the owner
    // Needs to go through the CRR's list of Jobs and Display the Necessary
    // Information about the Job
    public void viewCurrentJobs() { // Status: In Progress | Paused | Not Started
        for (int i = 0; i < this.jobList.size(); i++) {
            if (this.jobList.get(i).getStatus() == "In Progress" || this.jobList.get(i).getStatus() == "Paused"
                    || this.jobList.get(i).getStatus() == "Not Started") {
                viewJob(this.jobList.get(i));
            }
        }
    }

    public void removeJob(int jobID) {
        // Removes a job from the arraylist of jobs
        for (int i = 0; i < this.jobList.size(); i++) {
            if (this.jobList.get(i).getJobID() == jobID) {
                this.jobList.remove(this.jobList.get(i));
            }
        }
        for (int i = 0; i < VCController.currentJobList.size(); i++) {
            if (VCController.currentJobList.get(i).getJobID() == jobID) {
                VCController.currentJobList.get(i).setStatus("Cancelled");
                server.pastJobList.add(VCController.currentJobList.get(i));
                VCController.currentJobList.remove(VCController.currentJobList.get(i));
            }
        }
    }

    public void viewPastJobs() { // Status: Completed | Cancelled
        for (int i = 0; i < this.jobList.size(); i++) {
            if (this.jobList.get(i).getStatus() == "Completed" || this.jobList.get(i).getStatus() == "Cancelled") {
                viewJob(this.jobList.get(i));
            }
        }
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public void viewJob(job a) {
        System.out.println("Job Title: " + a.getJobTitle() + ", Job Description: " + a.getJobDescription());
        System.out.println("Job Duration: " + a.getJobDuration() + " hours, Job ID: " + a.getJobID()
                + ", Resource Requestor ID: " + a.getCRR_ID());
        System.out.println("Status: " + a.getStatus() + ", Deadline: " + dateFormat.format(a.getDeadline()));
    }

}