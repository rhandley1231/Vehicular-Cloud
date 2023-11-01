package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.Duration;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class vehicleOwner extends User {
    private String password;
    private ArrayList<vehicle> vehicles = new ArrayList<vehicle>();

    public vehicleOwner(String userName, String password) {
        super(userName);
        this.password = password;
        this.vehicles = null;
    }

    /*
     * Notifies the VC that an owner's certain car
     * has been parked and is ready to be rented out
     * for computations
     */
    // Altin
    // Should take time stamp of when the car parks and add it to the vehicle date
    // parked attribute //Timestamp here DONE?
    public void parkCar(String make, String model, int year, String plate) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH");
        String formattedTimestamp = timestamp.format(formatter);
        vehicle newVehicle = new vehicle(make, model, year, plate, true,
                VCController.convertDateToHourInt(formattedTimestamp));
        this.vehicles.add(newVehicle);
        VCController.currentParkedVehicles.add(newVehicle);
    }

    public void viewCurrentParkedVehicles() {
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).getParked() == true) {
                viewVehicle(this.vehicles.get(i));
            }
        }
    }

    // Altin
    // Should print the amount of time that the car was in the lot, take a leave lot
    // time stamp and subtract from park time stamp
    public void leaveLot(String licensePlate) {
        LocalDateTime leaveTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH");
        String formattedTimestamp = leaveTime.format(formatter);
        int leaveTime2 = VCController.convertDateToHourInt(formattedTimestamp);

        // Removes a job from the arraylist of jobs
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).getPlate() == licensePlate) {
                // Time in lot = Timestamp - this.vehicles.get(i).getParkedTime();
                int timeInLot = leaveTime2 - this.vehicles.get(i).getParkedTime();
                // this.vehicles.get(i).setleftLotTime(Timestamp);
                this.vehicles.remove(this.vehicles.get(i));
                System.out.println("Thanks for parking with us, your lot has been in here for " + timeInLot + " hours");
            }
        }
        for (int i = 0; i < VCController.currentParkedVehicles.size(); i++) {
            if (VCController.currentParkedVehicles.get(i).getPlate() == licensePlate) {
                VCController.currentParkedVehicles.get(i).setParked(false);
                VCController.checkpoint(VCController.currentParkedVehicles.get(i));
                server.pastParkedVehicles.add(VCController.currentParkedVehicles.get(i));
                VCController.currentParkedVehicles.remove(VCController.currentParkedVehicles.get(i));
            }
        }
    }

    public Time timeParked() {
        return null; // Returns the time at which a particular car was parked
    }

    public void viewPastVehicles() {
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).getParked() == false) {
                viewVehicle(this.vehicles.get(i));
            }
        }
    }

    // Add time parked into here
    // Should print the amount of tiem that the car was inthe lot, take a lot
    // Time stamp and subtract from park time stamp
    public void viewVehicle(vehicle a) {
        LocalDateTime idleTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH");
        // Time in lot = Timestap - this.vehicles.get(i).getParkedTime()
        String formattedTimestamp = idleTime.format(formatter);
        int idleTime2 = VCController.convertDateToHourInt(formattedTimestamp);
        int parkedDuration = idleTime2 - a.getParkedTime();
        System.out.println("Make: " + a.getMake() + ", Model: " + a.getModel() + ", Year: " + a.getYear()
                + ", License Plate: " + a.getPlate() + ", Parked Status: " + a.getParked() + ", Total Time Parked:"
                + parkedDuration + " hours");

    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
}