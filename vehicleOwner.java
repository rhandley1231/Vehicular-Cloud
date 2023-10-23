import java.sql.Time;
import java.util.*;
public class vehicleOwner extends User{
    private ArrayList<vehicle> vehicles = new ArrayList<vehicle>();
    public vehicleOwner(String userName, String password, ArrayList<vehicle> vehicles){
        super(userName, password);
        this.vehicles = vehicles;
    }
    public void parkCar(vehicle v){

        // Timestamp for when the car is parked
        Time timestamp = new Time(System.currentTimeMillis());

        /*Notifies the VC that an owner's certain car
         * has been parked and is ready to be rented out
         * for computations
         */ 
        VCController.notifyCarParked(v);

        /*We have to get a time stamp on when the user calls 
         *This method, so we may view how long the car has been parked
         *in another method
         */
        parkingTimestamps.put(v, timestamp);

    }

    public void leaveLot(vehicle v){
        /*Notifies the VC that an owner's certain car
        *has left the parking lot*/
       VCController.notifyCarLeftLot(v);
    }
    public Time timeParked(vehicle v){
        return null; //Returns the time at which a particular car was parked
    }
    public ArrayList<vehicle> myVehicles(){
        return vehicles; //Returns all of the cars owned by a specific owner
    }
}
