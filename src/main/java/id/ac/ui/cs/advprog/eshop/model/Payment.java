package id.ac.ui.cs.advprog.eshop.model;

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
        this.status = "ON_PROCESS";

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
                    this.status = "SUCCESS";
                } else {
                    this.status = "REJECTED";
                }
            }
            else{
                this.status="REJECTED";
            }
        } else if (method == "BANK_TRANSFER") {
            this.method = method;
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");
            if (bankName != null && referenceCode != null){
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
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




    }
}