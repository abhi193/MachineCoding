package data;

import payment.PaymentProcessor;
import payment.PaymentStatus;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Order {

    private final String orderId;
    private final Item item;
    private PaymentStatus paymentStatus;
    private static ExecutorService pool = Executors.newFixedThreadPool(8);

    public Order(Item item) {
        this.orderId = UUID.randomUUID().toString();
        this.item = item;
        this.paymentStatus = PaymentStatus.NONE;
    }


    public boolean checkoutCart(){
        CompletableFuture<Boolean> paymentResult = CompletableFuture.supplyAsync(() -> new PaymentProcessor().makePayment(this),pool);

        try {
            // Wait for the payment response for 5 minutes (300 seconds)
            return paymentResult.get(5, TimeUnit.SECONDS);
        }catch (Exception e){
            // If the payment takes too long or there's an exception, cancel the task and return false
            System.out.println("Payment took more than expected time for orderId:"+this.orderId);
            paymentResult.cancel(true);
            return false;
        }
//        finally {
//            pool.shutdownNow();
//        }

    }

//    ExecutorService executor = Executors.newSingleThreadExecutor();
//    Future<Boolean> paymentResult = executor.submit(this::processPayment);
//
//        try {
//        // Wait for the payment response for 5 minutes (300 seconds)
//        return paymentResult.get(5, TimeUnit.MINUTES);
//    } catch (InterruptedException | ExecutionException | TimeoutException e) {
//        // If the payment takes too long or there's an exception, cancel the task and return false
//        paymentResult.cancel(true);
//        return false;
//    } finally {
//        executor.shutdownNow();
//    }


//    CompletableFuture<GetUserSpecificPayloadResponse> userPayloadFuture
//            = CompletableFuture.supplyAsync(() -> {
//        GetUserSpecificPayloadRequest getUserSpecificPayloadRequest = GetUserSpecificPayloadRequest.newBuilder()
//                .setRequestId(requestId).setUserRequest(request.getRequest()).build();
//        GetUserSpecificPayloadResponse  response = ResourceManager.getInstance().getInternalGamePlayServiceBlocking().getUserSpecificPayload(getUserSpecificPayloadRequest);
//        log.info("getCombinedSectionGroupOrderV2 GetUserSpecificPayloadResponse for user {} game {} firsttimeUser {}", userId, gameId, response.getFirstTimeUser());
//        return response;
//    },pool);


//    private void makePaymentSuccess(){
//        this.paymentStatus = PaymentStatus.SUCCESS;
//    }
//
//
//    private void makePaymentFail(){
//        this.paymentStatus = PaymentStatus.FAILED;
//    }

    public String getOrderId() {
        return orderId;
    }

    public Item getItem() {
        return item;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}

//import java.util.concurrent.*;
//
//public class ShoppingCart {
//    // Simulating a payment service response time
//    private static final int PAYMENT_PROCESSING_TIME_MS = 6000; // 6 seconds
//
//    public boolean checkoutCart() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<Boolean> paymentResult = executor.submit(this::processPayment);
//
//        try {
//            // Wait for the payment response for 5 minutes (300 seconds)
//            return paymentResult.get(5, TimeUnit.MINUTES);
//        } catch (InterruptedException | ExecutionException | TimeoutException e) {
//            // If the payment takes too long or there's an exception, cancel the task and return false
//            paymentResult.cancel(true);
//            return false;
//        } finally {
//            executor.shutdownNow();
//        }
//    }
//
//    private boolean processPayment() {
//        // Simulate payment processing by waiting for some time
//        try {
//            Thread.sleep(PAYMENT_PROCESSING_TIME_MS);
//        } catch (InterruptedException e) {
//            // Handle interruption if needed
//        }
//
//        // Simulate payment success/failure (You can replace this logic with actual payment processing)
//        return true; // Payment success
//    }
//
//    public static void main(String[] args) {
//        ShoppingCart cart = new ShoppingCart();
//        boolean paymentResult = cart.checkoutCart();
//        System.out.println("Payment processed successfully: " + paymentResult);
//    }
//}

