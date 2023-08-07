package payment;

import data.Order;

public interface Payment {

    boolean makePayment(Order order);
}
