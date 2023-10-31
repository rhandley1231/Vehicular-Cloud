import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;

public class vehicle {
    private String make; // for the make of the car
    private String model; // for the model of the car
    private int year; // We can realistically pass the year as an int, date formating isn't necessary
    private String plate; // License plate number
    private boolean parked; // Parked True/False
    private int assignedToJobID;
    private Date parkedTime;
    private Date leftLotTime;

    public vehicle(String make, String model, int year, String plate, boolean parked, String formattedTimestamp, Date leftLotTime) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.plate = plate;
        this.parked = parked;
        this.assignedToJobID = 0;
        this.parkedTime = parkedTime;
        this.leftLotTime = leftLotTime;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
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

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public boolean getParked() {
        return parked;
    }

    public void setAssignedToJobID(int JobID) {
        this.assignedToJobID = JobID;
    }

    public int getAssignedToJobID() {
        return assignedToJobID;
    }

    public Date getParkedTime() {
        return parkedTime;
    }

    public void setLeftLoTime(Date leftLotTime) {
        this.leftLotTime = leftLotTime;
    }

    public Date getLeftLoTime() {
        return leftLotTime;
    }
}