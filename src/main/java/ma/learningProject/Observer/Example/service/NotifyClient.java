package ma.learningProject.Observer.Example.service;

import ma.learningProject.Observer.Example.entity.Client;
import ma.learningProject.Observer.Example.entity.Product;

public interface NotifyClient {

    void subscribe(Client client);
    void unsubscribe(Client client);
    void notifyClients(Product product);

}
