package classes;
import java.util.ArrayList;
import java.util.List;

public class server {
    public static List<job> pastJobList = new ArrayList<job>();
    public static List<vehicle> pastParkedVehicles = new ArrayList<vehicle>();

    public void viewPastJobs() {
        for (int i = 0; i < pastJobList.size(); i++) {
            VCController.viewJob(pastJobList.get(i));
        }
    }

    public void viewPastParkedVehicles() {
        for (int i = 0; i < pastParkedVehicles.size(); i++) {
            VCController.viewVehicle(pastParkedVehicles.get(i));
        }
    }

}
