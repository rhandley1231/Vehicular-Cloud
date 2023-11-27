package classes;

public class User {
    private int userID;

    public User(int userID) {
        this.userID = userID;
    }

    // getter and setters for userID
    public void setUserName(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return this.userID;
    }
}