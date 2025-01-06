package ma.learningProject.Observer.Example.entity;

import java.io.PrintWriter;

public class Client {

    private String userName;
    private String userAddress;
    private PrintWriter printWriter;
    private String role;


    public Client(String userName , String userAddress ,PrintWriter printWriter ){
        this.userName = userName;
        this.userAddress = userAddress;
        this.printWriter = printWriter;
        this.role = "CLIENT";
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void update(Product product){
        printWriter.println("new notification");
        printWriter.println(product);
    }


}
