package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Proudct> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product
    }

    Public Iterator<Product> findAll(){
        return productData.iterator();
    }
}