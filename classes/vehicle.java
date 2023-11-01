<<<<<<< HEAD:classes/vehicle.java
package classes;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;
=======
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;

>>>>>>> 378e16a0b444a7e48c9bbc31e96a56d69f34973f:vehicle.java
public class vehicle {
    private String make; // for the make of the car
    private String model; // for the model of the car
    private int year; // We can realistically pass the year as an int, date formating isn't necessary
    private String plate; // License plate number
    private boolean parked; // Parked True/False
    private int assignedToJobID;
<<<<<<< HEAD:classes/vehicle.java
    //tranfered from the Ben branch worked on by Altin and Ben
    private int parkedTime;
    private int leftLotTime;


    public vehicle(String make, String model, int year, String plate, boolean parked,
    int parkedTime) {
=======
    private int parkedTime;
    private int leftLotTime;

    public vehicle(String make, String model, int year, String plate, boolean parked,
            int parkedTime) {
>>>>>>> 378e16a0b444a7e48c9bbc31e96a56d69f34973f:vehicle.java
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

    public void setAssignedToJobID(int JobID) {
        this.assignedToJobID = JobID;
    }

    public int getAssignedToJobID() {
        return assignedToJobID;
    }
<<<<<<< HEAD:classes/vehicle.java
    //From Ben Branch as well
=======

>>>>>>> 378e16a0b444a7e48c9bbc31e96a56d69f34973f:vehicle.java
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
