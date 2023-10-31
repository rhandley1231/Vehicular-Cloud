import java.util.*;
public class server {
    private ArrayList<job> jobHistory = new ArrayList<job>();
    private ArrayList<vehicle> lotHistory = new ArrayList<vehicle>();
    public server(ArrayList<job> jH, ArrayList<vehicle> lH) {
        this.jobHistory = jH;
        this.lotHistory = lH;
    }
}
