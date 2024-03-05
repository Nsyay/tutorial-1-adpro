package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment{
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData){
        this.id = id;
        setStatus("ON_PROCESS");

        if (order == null){
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }

        if (paymentData.isEmpty()){
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }

        if(method == "VOUCHER_CODE"){
            this.method = method;
            String voucherCode = paymentData.get("voucherCode");

            if(voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")){
                int count = 0;
                for (int i = 0; i < voucherCode.length(); i++) {
                    if (Character.isDigit(voucherCode.charAt(i))) {
                        count++;
                    }
                }
                if(count == 8){
                    setStatus("SUCCESS");
                } else {
                    setStatus("REJECTED");
                }
            }
            else{
                setStatus("REJECTED");
            }
        } else if (method == "BANK_TRANSFER") {
            this.method = method;
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");
            if (bankName != null && referenceCode != null){
                setStatus("SUCCESS");
            } else {
                setStatus("REJECTED");
            }
        } else {
            throw new IllegalArgumentException();
        }


    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status){
        this.id = id;
        this.method = method;

        if (order == null){
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }

        if (paymentData.isEmpty()){
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
        setStatus(status);
    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        } else{
            throw new IllegalArgumentException();
        }
    }
}