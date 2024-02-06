package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndEdit(){
        Product product = new Product();
        product.setProductId("d6a5e627-155b-432d-8f8a-5df64d2f01f9");
        product.setProductName("Chiffon Cheese Cake");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product productEdit = productRepository.findById(product.getProductId());
        productEdit.setProductId("d6a5e627-155b-432d-8f8a-5df64d2f01f9");
        productEdit.setProductName("Chiffon Matcha Cake");
        productEdit.setProductQuantity(3);
        productRepository.update(productEdit);

        assertEquals(productEdit.getProductQuantity(), product.getProductQuantity());
        assertEquals(productEdit.getProductName(), product.getProductName());
    }

    @Test
    void testCreateAndDelete(){
        Product product = new Product();
        product.setProductId("0ec45358-634b-48a8-984e-f16a1c1f59a2");
        product.setProductName("Strawberry Pie");
        product.setProductQuantity(1);
        productRepository.create(product);

        productRepository.deleteById(product.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNotFound(){
        Product product = new Product();
        product.setProductId("573d6d0a-02b7-475b-80d3-53c773d3e48b");
        product.setProductName("Martabak manis Coklat keju");
        product.setProductQuantity(6);
        productRepository.create(product);

        productRepository.deleteById("0ec45358-634b-48a8-984e-f16a1c1f59a2");
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

}