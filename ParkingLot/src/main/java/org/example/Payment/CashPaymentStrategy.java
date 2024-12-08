package org.example.Payment;

public class CashPaymentStrategy implements PaymentStrategy{

    @Override
    public void processPayment(int cost) {
        System.out.println("Processing payment of " + cost + " rupees via cash");
        // write logic to process payment using cash.
    }
}
