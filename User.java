public class User{
    private String userName;
    private String password;
   public User(String userName, String password){
    this.userName = userName;
    this.password= password;
   }
   //getter and setters for username and password
   public void setUserName(String userName){
    this.userName = userName;
   }
   public String getUserName(){
    return userName;
   }

   public void setPassword(String password){
    this.password = password;
   }
   public String getPassword(){
    return password;
   }
}