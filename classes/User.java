package classes;

public class user {
    private int userID;

    public user(int userID) {
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