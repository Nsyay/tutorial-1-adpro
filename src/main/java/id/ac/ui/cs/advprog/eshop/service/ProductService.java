package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    Product findById(String productId); // Add this method
    void update(Product product); // Add this method
    public List<Product> findAll();

}