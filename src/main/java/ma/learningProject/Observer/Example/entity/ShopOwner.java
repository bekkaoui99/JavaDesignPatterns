package ma.learningProject.Observer.Example.entity;

public class ShopOwner {

    private String userName;
    private String password;
    private String role;
    
    public ShopOwner(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.role = "SHOP_OWNER";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
