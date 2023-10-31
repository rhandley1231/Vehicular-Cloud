import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.Duration;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class vehicleOwner extends User {
    private ArrayList<vehicle> vehicles = new ArrayList<vehicle>();

    public vehicleOwner(String userName, String password, ArrayList<vehicle> vehicles) {
        super(userName, password);
        this.vehicles = vehicles;
    }

    /*
     * Notifies the VC that an owner's certain car
     * has been parked and is ready to be rented out
     * for computations
     */
    /*
     * We have to get a time stamp on when the user calls
     * This method, so we may view how long the car has been parked
     * in another method
     */

    // Altin DONE?
    // Should take time stamp of when the car parks and add it to the vehicle date
    // parked attribute //Timestamp here DONE?
    public void parkCar(String make, String model, int year, String plate) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedTimestamp = timestamp.format(formatter);
        vehicle newVehicle = new vehicle(make, model, year, plate, true, formattedTimestamp, null);
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

    /*
     * Notifies the VC that an owner's certain car
     * has left the parking lot
     */

    // Altin
    // Should print the amount of time that the car was in the lot, take a leave lot
    // time stamp and subtract from park time stamp
    public void leaveLot(String licensePlate) {
        //Put timestamp here
        LocalDateTime leaveTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // Removes a job from the arraylist of jobs
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).getPlate() == licensePlate) {
                // Time in lot = Timestamp - this.vehicles.get(i).getParkedTime();
                Duration timeInLot = Duration.between((Temporal) this.vehicles.get(i).getParkedTime(), leaveTime);
                //this.vehicles.get(i).setleftLotTime(Timestamp);
                this.vehicles.remove(this.vehicles.get(i));
                System.out.println("Thanks for parking with us, your lot has been in here for " + timeInLot);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // Time in lot = Timestap - this.vehicles.get(i).getParkedTime()
        Date parkedTime = a.getParkedTime();
        Duration parkedDuration = Duration.between((Temporal) a.getParkedTime(), idleTime);
        System.out.println("Make: " + a.getMake() + ", Model: " + a.getModel() + ", Year: " + a.getYear()
                + ", License Plate: " + a.getPlate() + ", Parked Status: " + a.getParked() +", Total Time Parked:" + a.getParkedTime());

    }
}