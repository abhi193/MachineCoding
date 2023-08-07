package db;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import data.Order;

public class EventNotificationHandler {

    @AllowConcurrentEvents
    @Subscribe
    public void executePayment(Order order) throws Exception {

    }
}
