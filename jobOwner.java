import java.util.*;
public class jobOwner extends User {
    private ArrayList<job> jobList = new ArrayList<job>();
    public jobOwner(String userName, String password, ArrayList<job> jobList){
        super(userName, password);
        this.jobList = jobList;
    }
    //Methods for the Job Owner Class
    public void requestJob(job j) {
        //Adds a job to the arraylist of jobs for the owner
    }
    public void removeJob(job j){
        //Removes a job from the arraylist of jobs
    }

    public ArrayList<job> myJobs(){
        //Allows the user to view their current jobs
        return jobList;
    }
}
