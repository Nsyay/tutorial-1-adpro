package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
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
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest{
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudarajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<String, String>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", orders.get(0), paymentData1);
        payments.add(payment1);
        Map<String, String> paymentData2 = new HashMap<String, String>();
        paymentData2.put("bankName", "ABC");
        paymentData2.put("referenceCode", "00000000");
        Payment payment2 = new Payment("d54759ff-2cfd-4fb4-b028-e9f6bbcb3702",
                "BANK_TRANSFER", orders.get(0), paymentData2);
        payments.add(payment2);
    }

    @Test
    void testCreatePayment(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678AYU");
        Payment payment = this.payments.get(0);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(this.orders.get(0), "VOUCHER_CODE", paymentData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatus(){
        Payment payment = payments.get(0);
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP12345678AYU");
        Payment newPayment = new Payment("98a61d1a-7c9e-4e13-b2c3-dc74a77e04e8",
                "VOUCHER_CODE", this.orders.get(0), paymentData, PaymentStatus.SUCCESS.getValue());
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetInvalidStatus(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        assertThrows(IllegalArgumentException.class, ()->paymentService.setStatus(payment, "MEOW"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidOrderId(){
        Payment payment = payments.get(1);
        doReturn(null).when(paymentRepository).getPayment(payment.getId());

        assertThrows(NoSuchElementException.class,
                ()->paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue()));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentIdFound(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIdNotFound(){
        doReturn(null).when(paymentRepository).getPayment("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayment(){
        doReturn(payments).when(paymentRepository).getAllPayments();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);
    }
}