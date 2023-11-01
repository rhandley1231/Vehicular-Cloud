package classes;

public class User {
    private String userName;
    public int userID;
    public static int nextUserID = 100000;

    public User(String userName) {
        this.userName = userName;
        this.userID = nextUserID++;

    }

    // getter and setters for username and password
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return this.userID;
    }

}