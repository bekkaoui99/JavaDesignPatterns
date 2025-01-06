package ma.learningProject.Observer.Example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable{


    private static final List<ConnectionHandler> connectionHandlerList = new ArrayList<>();

    private void startServer(){

        int port = 8888;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server starts running on port : " + port);
            System.out.println("Server is waiting for connections ...");
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ConnectionHandler connectionHandler = new ConnectionHandler(socket);
                connectionHandlerList.add(connectionHandler);
                Thread connectionHandlerThread = new Thread(connectionHandler);
                connectionHandlerThread.start();
            }


        } catch (IOException e) {
            System.out.println("server stops .");
            for (ConnectionHandler connectionHandler : connectionHandlerList){
                connectionHandler.closeConnection();
            }
        }

    }

    @Override
    public void run() {
        startServer();
    }

}
