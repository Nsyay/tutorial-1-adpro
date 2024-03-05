package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
@Repository
public class PaymentRepository{
    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment){ return null; }
    public Payment getPayment(String id){return null;}
    public List<Payment> findVoucherPayment(){return null;}
    public List<Payment> findBankTransferPayment(){return null;}
    public List<Payment> getAllPayments(){return null;}

}