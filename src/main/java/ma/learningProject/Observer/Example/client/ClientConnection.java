package ma.learningProject.Observer.Example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection implements Runnable{


    private final Scanner writingSystem = new Scanner(System.in);
    private Socket connectionSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public ClientConnection(){
        handShake();
    }

    private void handShake(){
        try {
            String serverAddress = "localhost";
            int serverPort = 8888;
            this.connectionSocket = new Socket(serverAddress, serverPort);

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

    private void startInteractionWithServer(){
        try {

            while (!this.connectionSocket.isClosed()){
                String request = writingSystem.nextLine();
                printWriter.println(request);
            }

        }catch (Exception ex){
            System.out.println("Disconnected from server.");
            closeConnection();
        }
    }

    private void listeningToServerResponse() {
        Thread thread = new Thread(() -> {
            try {
                String response;
                while ((response = bufferedReader.readLine()) != null) {
                    System.out.println("[Server]: " + response);
                }
            } catch (Exception ex) {
                System.out.println("Disconnected from server.");
                closeConnection();
            }
        });
        thread.start();
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
        if(this.connectionSocket != null && !this.connectionSocket.isClosed()){
            listeningToServerResponse();
        }
        startInteractionWithServer();
    }
}
