package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.Duration;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class vO extends User {

    private ArrayList<vehicle> vehicles = new ArrayList<vehicle>();
    private vehicle v;

    public vO(int userID, vehicle v) {
        super(userID);
        this.v = v;
    }

    public vehicle getVehicle() {
        return this.v;
    }

    public void setVehicle(vehicle v) {
        this.v = v;
    }

}