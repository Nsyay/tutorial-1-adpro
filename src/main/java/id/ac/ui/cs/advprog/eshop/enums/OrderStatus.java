package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum OrderStatus{
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final string value;
    private OrderStatus(string value){
        this.value = value;
    }

    public static boolean contains(Stringn param){
        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}