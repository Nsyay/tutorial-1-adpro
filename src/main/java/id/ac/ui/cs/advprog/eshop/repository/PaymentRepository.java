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

    public Payment save(Payment payment){
        int i=0;
        for(Payment savedPayment : payments){
            if(savedPayment.getId().equals(payment.getId())){
                payments.remove(i);
                payments.add(i, payment);
                return payment;
            }
            i+=1;
        }
        payments.add(payment);
        return payment;
    }
    public Payment getPayment(String id){
        for(Payment savedPayment : payments){
            if(savedPayment.getId().equals(id)){
                return savedPayment;
            }
        }
        return null;
    }
    public List<Payment> findVoucherPayment(){
        List<Payment> voucherPayments = new ArrayList<>();
        for(Payment savedPayment : payments){
            System.out.println(savedPayment.getMethod());
            if(savedPayment.getMethod().equals("VOUCHER_CODE")){
                voucherPayments.add(savedPayment);
            }
        }
        return voucherPayments;
    }
    public List<Payment> findBankTransferPayment(){
        List<Payment> bankTransferPayments = new ArrayList<>();
        for(Payment savedPayment : payments){
            if(savedPayment.getMethod().equals("BANK_TRANSFER")){
                bankTransferPayments.add(savedPayment);
            }
        }
        return bankTransferPayments;
    }
    public List<Payment> getAllPayments(){
        return payments;
    }

}