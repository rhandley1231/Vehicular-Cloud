
package classes;

public class vehicle {
    private String make; // for the make of the car
    private String model; // for the model of the car
    private int year; // We can realistically pass the year as an int; date formatting isn't necessary
    private String plate; // License plate number
    private boolean parked; // Parked True/False
    private int assignedToJobID;
    private int parkedTime;
    private int leftLotTime;

    // Transferred from the Ben branch worked on by Altin and Ben
    public vehicle(String make, String model, int year, String plate, boolean parked, int parkedTime) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.plate = plate;
        this.parked = parked;
        this.assignedToJobID = 0;
        this.parkedTime = parkedTime;
        this.leftLotTime = 0;
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

    public void setAssignedToJobID(int jobID) {
        this.assignedToJobID = jobID;
    }

    public int getAssignedToJobID() {
        return assignedToJobID;
    }

    public void setParkedTime(int parkedTime) {
        this.parkedTime = parkedTime;
    }

    public int getParkedTime() {
        return parkedTime;
    }

    public void setLeftLotTime(int leftLotTime) {
        this.leftLotTime = leftLotTime;
    }

    public int getLeftLotTime() {
        return leftLotTime;
    }
}
