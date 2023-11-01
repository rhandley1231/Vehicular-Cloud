package classes;
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
    public void parkCar(String make, String model, int year, String plate) {
        vehicle newVehicle = new vehicle(make, model, year, plate, true);
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
    public void leaveLot(String licensePlate) {

        // Removes a job from the arraylist of jobs
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).getPlate() == licensePlate) {
                this.vehicles.remove(this.vehicles.get(i));
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

    public void viewVehicle(vehicle a) {
        System.out.println("Make: " + a.getMake() + ", Model: " + a.getModel() + ", Year: " + a.getYear()
                + ", License Plate: " + a.getPlate() + ", Parked Status: " + a.getParked());

    }
}