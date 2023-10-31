import java.util.Date;

public class job {
    private String jobTitle; // Title of the job
    private String jobDescription; // A string describing the job
    private int jobDuration; // To be passed in as an estimate to the nearest hour
    private int jobID; // The ID of the specific job
    public static int nextJobID = 100000;
    private int crr_ID; // The ID of the resoure requestor
    private boolean assigned; // Assigned True or False
    private Date deadline;// The deadline of the job (Use DateFormat to handle formatting)
    private String status; // Completed | In Progress | Cancelled | Paused

    public job(String title, String description, int duration, int crr_ID, boolean assigned, Date deadline,
            String status) {
        this.jobTitle = title;
        this.jobDescription = description;
        this.jobDuration = duration;
        this.jobID = nextJobID++;
        this.crr_ID = crr_ID;
        this.assigned = assigned;
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

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean getAssigned() {
        return this.assigned;
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