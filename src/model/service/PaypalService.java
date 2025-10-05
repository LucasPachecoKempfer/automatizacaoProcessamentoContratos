package model.service;

public class PaypalService implements OnlinePaymentService {

    public Double interest(Double amount, Integer months){
        return amount + (amount * 0.01 * months);
    }

    public Double paymentFee(Double amount){
        return amount * 1.02;
    }
}
