package ma.learningProject.Observer.Example.service;

import ma.learningProject.Observer.Example.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);
    Product delete(String id);
    List<Product> showAllProduct();

}
