package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTes0t {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
    List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();
    }

    @Test
    void TestCreateProduct(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);
        verify(productRepository).create(product);
    }

    @Test
    public void testFindById() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        when(productRepository.findById(productId)).thenReturn(product);
        Product foundProduct = productService.findById(productId);
        assert(foundProduct.equals(product));
    }

    @Test
    public void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        productService.delete(productId);
        verify(productRepository).deleteById(productId);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        Product product2 = new Product();
        product2.setProductId("f47ac10b-58cc-4372-a567-0e02b2c3d479");
        product2.setProductName("Sabun Cair");
        product2.setProductQuantity(10);
        productService.update(product2);

        verify(productRepository).update(product2);
    }

    @Test
    public void testFindAllProducts() {
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> allProducts = productService.findAll();
        assert(allProducts.size() == productList.size());
        for (int i = 0; i < productList.size(); i++) {
            assert(allProducts.get(i).equals(productList.get(i)));
        }
    }
}