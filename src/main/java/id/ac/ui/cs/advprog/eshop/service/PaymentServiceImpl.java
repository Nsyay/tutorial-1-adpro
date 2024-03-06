package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        Payment payment = new Payment("", method, order, paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    public Payment setStatus(Payment payment, String status){
        Payment newPayment = paymentRepository.getPayment(payment.getId());
        if(newPayment != null){
            if (status.equals("SUCCESS")){
                newPayment.getOrder().setStatus("SUCCESS");
            } else if (status.equals("REJECTED")){
                newPayment.getOrder().setStatus("FAILED");
            }
            newPayment.setStatus(status);
            paymentRepository.save(newPayment);
            return newPayment;
        } else{
            throw new NoSuchElementException();
        }
    }

    public List<Payment> getAllPayments(){
        return paymentRepository.getAllPayments();
    }

    public Payment getPayment(String paymentId){
        return paymentRepository.getPayment(paymentId);
    }
}