import java.util.Date;
public class job {
    private String jobTitle; //Title of the job
    private String jobDescription; //A string describing the job
    private int jobDuration; //To be passed in as an estimate to the nearest hour
    private String cRR_ID;  //The ID of the resoure requestor
    private String status; // Completed | In Progress | Cancelled | Paused
    private Date deadline;// The deadline of the job (Use DateFormat to handle formatting)
    public job(String title, String description, int duration, String ID, Date deadline, String status) {
        this.jobTitle = title;
        this.jobDescription=description;
        this.jobDuration=duration;
        this.cRR_ID=ID;
        this.status=status;
        this.deadline=deadline;
    }
    public void setJobTitle(String newTitle){
        this.jobTitle=newTitle;
    }
    public String getJobTitle(){
        return this.jobTitle;
    }
    public void setJobDescription(String newDesc){
        this.jobDescription=newDesc;
    }
    public String getJobDescription(){
        return this.jobDescription;
    }
    public void setJobDuration(int newDur){
        this.jobDuration=newDur;
    }
    public int getJobDuration(){
        return this.jobDuration;
    }
    public void setCRR_ID(String newID){
        this.cRR_ID=newID;
    }
    public String getCRR_ID(){
        return this.cRR_ID;
    }
    public void setStatus(String newStat){
        this.status=newStat;
    }
    public String getStatus(){
        return this.status;
    }
    public void setDeadline(Date newDL){
        this.deadline=newDL;
    }
    public Date getDeadline(){
        return this.deadline;
    }    
}
