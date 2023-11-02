package classes;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;

public class vehicle {
    private String make; // for the make of the car
    private String model; // for the model of the car
    private String plate; // License plate number
    // private boolean parked; // Parked True/False
    // private int assignedToJobID;
    // tranfered from the Ben branch worked on by Altin and Ben
    // private int parkedTime;
    // private int leftLotTime;

    public vehicle(String make, String model, String plate) {
        this.make = make;
        this.model = model;
        this.plate = plate;
        // this.parked = parked;
        // this.assignedToJobID = 0;
        // this.parkedTime = parkedTime;
        // this.leftLotTime = 0;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }
    /*
     * public void setParked(boolean parked) {
     * this.parked = parked;
     * }
     * 
     * public boolean getParked() {
     * return parked;
     * }
     * 
     * public void setAssignedToJobID(int JobID) {
     * this.assignedToJobID = JobID;
     * }
     * 
     * public int getAssignedToJobID() {
     * return assignedToJobID;
     * }
     * 
     * // From Ben Branch as well
     * public void setParkedTime(int parkedTime) {
     * this.parkedTime = parkedTime;
     * }
     * 
     * public int getParkedTime() {
     * return parkedTime;
     * }
     * 
     * public void setLeftLotTime(int leftLotTime) {
     * this.leftLotTime = leftLotTime;
     * }
     * 
     * public int getLeftLotTime() {
     * return leftLotTime;
     * }
     */
}
