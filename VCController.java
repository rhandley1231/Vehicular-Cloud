import java.util.ArrayList;
import java.util.List;

;
public class VCController {
    private List<job> currentJobs = new ArrayList<job>();
    private List<vehicle> parkedVehicles = new ArrayList<vehicle>();
    private String password; //Will not have a setter because a random password should be given to the 
    //garage owner/admin to access this classes features
    


    //Completion time algorithm
     public List<Integer> jobCompletion(int[] jobTimes) {
        List<Integer> jobCompletionTimes = new ArrayList<>();
        int totalTime = 0;
        for (int i : jobTimes) {
            totalTime += i;
            jobCompletionTimes.add(totalTime);
        }
        return jobCompletionTimes;
    }
}
