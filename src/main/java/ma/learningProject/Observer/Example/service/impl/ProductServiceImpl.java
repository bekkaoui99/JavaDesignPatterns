package ma.learningProject.Observer.Example.service.impl;

import ma.learningProject.Observer.Example.entity.Product;
import ma.learningProject.Observer.Example.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    public static List<Product> products = new ArrayList<>();

    @Override
    public Product create(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product delete(String id) {
        Product productById = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product does not exist ."));
        products.remove(productById);
        return productById;
    }

    @Override
    public List<Product> showAllProduct() {
        return products;
    }
}
