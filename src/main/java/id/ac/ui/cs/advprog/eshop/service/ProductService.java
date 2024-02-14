package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product findById(String productId);
    void update(Product product);
    void delete(String productId);
    public List<Product> findAll();

}