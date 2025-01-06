package ma.learningProject.Observer.Example.service;

import ma.learningProject.Observer.Example.entity.Product;

public interface Observer {
    void update(Product product);
}
