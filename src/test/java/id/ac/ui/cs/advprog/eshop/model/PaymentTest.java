package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private Order order;
    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity (2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
    }

    //unhappy
    @Test
    void testPaymentEmptyMethod(){
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () ->{
            Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                    "", order, paymentData);
        });
    }

    //unhappy
    @Test
    void testPaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("lama_cicil", "8");
        assertThrows(IllegalArgumentException.class, () ->{
            Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                    "CICILAN", order, paymentData);
        });
    }

    //unhappy
    @Test
    void testPaymentEmptyOrder(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () ->{
            Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                    "VOUCHER_CODE", null, paymentData);
        });
    }

    //unhappy
    @Test
    void testPaymentEmptyPaymentData(){
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () ->{
            Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                    "VOUCHER_CODE", order, paymentData);
        });
    }

    //happy
    @Test
    void testPaymentVoucherMethod(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", order, paymentData);
        assertEquals("VOUCHER_CODE", payment.getMethod());
    }

    //happy
    @Test
    void testPaymentVoucherSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", order, paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    //unhappy
    @Test
    void testPaymentVoucherRejectedLessSixteenChar(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234AB");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", order, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    //unhappy
    @Test
    void testPaymentVoucherRejectedWithoutEshop(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "1234ABC5678");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", order, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    //unhappy
    @Test
    void testPaymentVoucherRejectedLessEightNum(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOPABC");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "VOUCHER_CODE", order, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    //happy
    void testPaymentBankTransferMethod(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "00000000");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "BANK_TRANSFER", order, paymentData);
        assertEquals("BANK_TRANSFER", payment.getMethod());
    }

    //happy
    void testPaymentBankTransferSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "ABC");
        paymentData.put("referenceCode", "00000000");
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "BANK_TRANSFER", order, paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    //happy
    void testPaymentBankTransferRejected(){
        Map<String, String> paymentData = new HashMap<String, String>();
        Payment payment = new Payment("bd0bbf26-08e0-4d35-8042-0913c25ff335",
                "BANK_TRANSFER", order, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}