package payment;

import data.Order;
import db.DBAccessor;

public class PaymentProcessor implements Payment{

    // Simulating a payment service response time
    private static final int PAYMENT_PROCESSING_TIME_MS = 3000; // 6 seconds

//    private final Order order;
//    private final DBAccessor dbAccessor;

//    public PaymentProcessor(DBAccessor dbAccessor) {
//        this.order = order;
//        this.dbAccessor = dbAccessor;
//    }

//    private void makePayment(){
//        this.dbAccessor.getAsyncEventBus().post(order);
//        //get callback
//        boolean callbackStatus = true;
//        if(callbackStatus)
//            dbAccessor.confirmOrder(order.getOrderId());
//        else
//            dbAccessor.orderFailed(order.getOrderId());
//
//
//    }

    @Override
    public boolean makePayment(Order order) {
        System.out.println("Processing payment for orderId:"+order.getOrderId());
        // Simulate payment processing by waiting for some time
        try {
            Thread.sleep(PAYMENT_PROCESSING_TIME_MS);
        } catch (InterruptedException e) {
            // Handle interruption if needed
            return false;
        }

        // Simulate payment success/failure (You can replace this logic with actual payment processing)
        System.out.println("Processed payment for orderId:"+order.getOrderId());
        return true; // Payment success
    }
}
