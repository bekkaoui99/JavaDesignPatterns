package ma.learningProject.Observer.Example.service.impl;

import ma.learningProject.Observer.Example.entity.Client;
import ma.learningProject.Observer.Example.entity.Product;
import ma.learningProject.Observer.Example.service.NotifyClient;

import java.util.ArrayList;
import java.util.List;

public class NotifyClientsImpl implements NotifyClient {

    public static List<Client>  clients = new ArrayList<>();

    @Override
    public void subscribe(Client client) {
        clients.add(client);
    }

    @Override
    public void unsubscribe(Client client) {
        clients.remove(client);
    }

    @Override
    public void notifyClients(Product product) {
        for (Client client : clients){
            client.update(product);
        }
    }


}
