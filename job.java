import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class job {
    private String jobTitle; // Title of the job
    private String jobDescription; // A string describing the job
    private int jobDuration; // To be passed in as an estimate to the nearest hour
    private int jobID; // The ID of the specific job
    public static int nextJobID = 100000;
    private int crr_ID; // The ID of the resoure requestor
    private ArrayList<String> assignedToVehiclePlates = new ArrayList<String>(); // A vehicle's ID. If not assigned, is
                                                                                 // assigned 0.
    private Date deadline;// The deadline of the job (Use DateFormat to handle formatting)
    private String status; // Completed | In Progress | Cancelled | Paused | Not Started

    public job(String title, String description, int duration, int crr_ID, boolean assigned, Date deadline,
            String status) {
        this.jobTitle = title;
        this.jobDescription = description;
        this.jobDuration = duration;
        this.jobID = nextJobID++;
        this.crr_ID = crr_ID;
        this.assignedToVehiclePlates = null;
        this.status = status;
        this.deadline = deadline;
    }

    public void setJobTitle(String newTitle) {
        this.jobTitle = newTitle;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobDescription(String newDesc) {
        this.jobDescription = newDesc;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public void setJobDuration(int newDur) {
        this.jobDuration = newDur;
    }

    public int getJobDuration() {
        return this.jobDuration;
    }

    public int getJobID() {
        return this.jobID;
    }

    public void setCRR_ID(int newID) {
        this.crr_ID = newID;
    }

    public int getCRR_ID() {
        return this.crr_ID;
    }

    public ArrayList<String> getAssignedToVehicleID() {
        return assignedToVehiclePlates;
    }

    public void assignVehiclesToJob(ArrayList<String> vehiclePlates) {
        this.assignedToVehiclePlates = vehiclePlates;
    }

    public void addVehicleIdToJob(String vehiclePlate) {
        assignedToVehiclePlates.add(vehiclePlate);
    }

    public void removeVehicleIdToJob(String vehiclePlate) {
        for (int i = 0; i < assignedToVehiclePlates.size(); i++) {
            if (assignedToVehiclePlates.get(i) == vehiclePlate) {
                assignedToVehiclePlates.remove(assignedToVehiclePlates.get(i));
            }
        }
    }

    public void setStatus(String newStat) {
        this.status = newStat;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDeadline(Date newDL) {
        this.deadline = newDL;
    }

    public Date getDeadline() {
        return this.deadline;
    }

}