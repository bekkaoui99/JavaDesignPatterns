package ma.learningProject.Observer.Example.client;

public class StartClient {

    public static void main(String[] args) {
        ClientConnection clientConnection = new ClientConnection();
        Thread clientConnectionThread = new Thread(clientConnection);
        clientConnectionThread.start();
    }
}
