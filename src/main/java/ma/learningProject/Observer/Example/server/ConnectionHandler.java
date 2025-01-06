package ma.learningProject.Observer.Example.server;

import ma.learningProject.Observer.Example.entity.Client;
import ma.learningProject.Observer.Example.entity.Product;
import ma.learningProject.Observer.Example.entity.ShopOwner;
import ma.learningProject.Observer.Example.service.NotifyClient;
import ma.learningProject.Observer.Example.service.ProductService;
import ma.learningProject.Observer.Example.service.impl.NotifyClientsImpl;
import ma.learningProject.Observer.Example.service.impl.ProductServiceImpl;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket connectionSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private final NotifyClient notifyClient;
    private final ProductService productService;



    public ConnectionHandler(Socket socket){
        handShake(socket);
        this.notifyClient = new NotifyClientsImpl();
        this.productService = new ProductServiceImpl();
    }

    private void handShake(Socket socket){
        try {
            this.connectionSocket = socket;

            InputStream inputStream = this.connectionSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            this.bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = this.connectionSocket.getOutputStream();
            this.printWriter = new PrintWriter(outputStream, true);

        } catch (IOException e) {
            System.out.println("Can not connect to the Server , Please try again.");
            closeConnection();
        }
    }

    private void startInteractionWithClient(){
        try {
            printWriter.println("create account as a client : type create");
            printWriter.println("login as a shop owner : type login");
            String request;
            request = bufferedReader.readLine();
            while (!"create".equals(request) && !"login".equals(request) ){
                printWriter.println("invalid input :( ");
                printWriter.println("create account as a client : type create");
                printWriter.println("login as a shop owner : type login");
                request = bufferedReader.readLine();
            }

            switch (request){
                case "create":{

                    printWriter.println("write your user name");
                    String userNameRequest = bufferedReader.readLine();
                    String userAddress = this.connectionSocket.getRemoteSocketAddress().toString();

                    Client newClient = new Client(userNameRequest, userAddress , printWriter);
                    String userName = newClient.getUserName();
                    System.out.println(userName + " has joined our show notification products .");
                    notifyClient.subscribe(newClient);
                    printWriter.println(userName + " your account has been created .");
                    printWriter.println("we will send you all our new product .");

                }
                break;

                case "login":{
                    printWriter.println("write your user name");
                    String userName = bufferedReader.readLine();
                    printWriter.println("write your password");
                    String password = bufferedReader.readLine();
                    ShopOwner shopOwner = new ShopOwner(userName, password);
                    while (!this.connectionSocket.isClosed()){
                        printWriter.println("add new product : type : add");
                        String addNewProductKeyword = bufferedReader.readLine();
                        if(addNewProductKeyword.equals("add")){
                            printWriter.println("write product name");
                            String productName = bufferedReader.readLine();
                            printWriter.println("write product price");
                            String productPrice = bufferedReader.readLine();
                            printWriter.println("write product quantity");
                            String productQuantity = bufferedReader.readLine();
                            Product newProduct = new Product(productName , productPrice , productQuantity);
                            this.productService.create(newProduct);
                            printWriter.println("Product has been added .");
                            this.notifyClient.notifyClients(newProduct);
                            printWriter.println("all client has been notified .");
                        }

                    }

                }
                break;
            }

        }catch (Exception ex){

        }
    }

    private void listeningToClientRequest(){
        try {
            while (!this.connectionSocket.isClosed()){
                String s = bufferedReader.readLine();
            }
        }catch (Exception ex){

        }
    }

    public void closeConnection(){
        try {
            if (connectionSocket != null) {
                connectionSocket.close();
            }
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(printWriter != null){
                printWriter.close();
            }

        }catch (Exception ex){
        }
    }

    @Override
    public void run() {
        startInteractionWithClient();
    }
}
