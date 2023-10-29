import java.text.DateFormat;

public class vehicle {
    private String make; //for the make of the car
    private String model; //for the model of the car
    private int year; //We can realistically pass the year as an int, date formating isn't necessary
    private String plate; //License plate number

    public vehicle(String make, String model, int year, String plate){
        this.make = make;
        this.model = model;
        this.year = year;
        this.plate = plate;
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
    public int getYear(){
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
}
