package org.example.Payment;

public class CardPaymentStrategy implements PaymentStrategy{

    @Override
    public void processPayment(int cost) {
        System.out.println("Processing payment of " + cost + " rupees via card");
        // write logic to process payment using card.
    }
}
