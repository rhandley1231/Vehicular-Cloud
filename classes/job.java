package classes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class job {
    private int jobID; // The ID of the specific job
    private int jobDuration; // To be passed in as an estimate to the nearest hour

    private String deadline;// The deadline of the job (Use DateFormat to handle formatting)

    public job(int jobID, int duration, String deadline) {
        this.jobID = jobID;
        this.jobDuration = duration;
        this.deadline = deadline;
    }

    public void setJobDuration(int newDur) {
        this.jobDuration = newDur;
    }

    public int getJobDuration() {
        return this.jobDuration;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getJobID() {
        return this.jobID;
    }

    public void setDeadline(String deadLine) {
        this.deadline = deadLine;
    }

    public String getDeadline() {
        return this.deadline;
    }
}
