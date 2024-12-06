package co.edu.uptc.model;

public class User {
    private String user;
    private String password;
    public User() {
    }
    
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [user=" + user + ", password=" + password + "]";
    }
    
   
    
}
