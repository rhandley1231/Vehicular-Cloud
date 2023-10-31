public class User {
    private String userName;
    private String password;
    public int userID;
    public static int nextUserID = 100000;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userID = nextUserID++;

    }

    // getter and setters for username and password
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return this.userID;
    }

}