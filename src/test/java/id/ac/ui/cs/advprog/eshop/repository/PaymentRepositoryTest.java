package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Product> products;
    List<Payment> payments;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudarajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee607ecbf1e", products,
                1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        payments = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<String, String>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", orders.get(0), paymentData1);
        payments.add(payment1);
        Map<String, String> paymentData2 = new HashMap<String, String>();
        paymentData2.put("bankName", "ABC");
        paymentData2.put("referenceCode", "00000000");
        Payment payment2 = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "BANK_TRANSFER", orders.get(0), paymentData2);
        payments.add(payment2);
    }

    // happy
    @Test
    void testSaveCreate(){
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound(){
        for(Payment payment : payments){
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        for(Payment payment : payments){
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.getPayment("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindVoucherPayment(){
        for(Payment payment : payments){
            paymentRepository.save(payment);
        }
        List<Payment> paymentList = paymentRepository.findVoucherPayment();
        assertEquals(1, paymentList.size());
    }

    @Test
    void testFindBankTransferPayment(){
        for(Payment payment : payments){
            paymentRepository.save(payment);
        }
        List<Payment> paymentList = paymentRepository.findBankTransferPayment();
        assertEquals(1, paymentList.size());
    }

    @Test
    void testFindAllPayment(){
        for(Payment payment : payments){
            paymentRepository.save(payment);
        }
        List<Payment> paymentList = paymentRepository.getAllPayments();
        assertEquals(1, paymentList.size());
    }
}