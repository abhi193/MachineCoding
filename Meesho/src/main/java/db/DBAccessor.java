package db;

import data.Item;
import data.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DBAccessor {

    private Map<String,String> productDetails; //productId,Name of product
    private Map<String,Integer> productCount; //productId,count
    private Map<String, Item> blockedItemMap;  //orderId,Item
    private List<Order> orders;


    private final ReadWriteLock lock;


    public DBAccessor() {
        this.productDetails = new HashMap<>();
        this.productCount = new ConcurrentHashMap<>();
        this.blockedItemMap = new ConcurrentHashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public void createProduct(String productId, String name, Integer count){
        /**
         * Using synchronised block to handle the case when 2 threads try to create product at the same time
         *
         * Do I need this block to be synchronised if I am using a ConcurrentHashMap??
         */
        synchronized (productId) {
            if (this.productDetails.containsKey(productId) || this.productCount.containsKey(productId))
                throw new RuntimeException("Product already exists");

            this.productDetails.put(productId, name);
            this.productCount.put(productId, count);
        }
    }


    public Integer getInventory(String productId) {
        if(!this.productCount.containsKey(productId))
            throw new RuntimeException("Product does not exist");

        /**
         * What is better here?? Read write lock or synchronised block??
         * If I use synchronised, only 1 thread will be able to fetch the count at a time
         */

        lock.readLock().lock();
        try {
            return this.productCount.get(productId);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void blockInventory(String productId, Integer count, String orderId) {
        if(!this.productDetails.containsKey(productId) || !this.productCount.containsKey(productId))
            throw new RuntimeException("Product does not  exist");

        reduceInventoryCount(productId,count);
        this.blockedItemMap.put(orderId,new Item(productId,count));
    }


    public void confirmOrder(String orderId) {
        if(!this.blockedItemMap.containsKey(orderId))
            throw new RuntimeException("order does not exist");

        this.blockedItemMap.remove(orderId);
    }

    public void orderFailed(String orderId){
        if(!this.blockedItemMap.containsKey(orderId))
            throw new RuntimeException("order does not exist");
        freeInventoryCount(this.blockedItemMap.get(orderId).getProductId(),this.blockedItemMap.get(orderId).getCount());

        this.blockedItemMap.remove(orderId);
    }


    private void reduceInventoryCount(String productId, Integer count){
//        synchronized (productId) {
//            if(this.productCount.get(productId)<count)
//                throw new RuntimeException("Count in inventory is lesser than required");
            lock.writeLock().lock();
            try {
                int currentCount = this.productCount.get(productId);
                int newCount = currentCount - count;
                if(newCount<0)
                    throw new RuntimeException("Count in inventory is lesser than required");
                this.productCount.put(productId, newCount);
            }finally {
                lock.writeLock().unlock();
            }

//        }
    }

    private void freeInventoryCount(String productId, Integer count){
        /**
         * should we use read -write lock here? or synchronised on product id?
         * can we acquire read write lock on product Id?
         */
//        synchronized (productId) {
        lock.writeLock().lock();
        try {
            int currentCount = this.productCount.get(productId);
            int newCount = currentCount + count;
            this.productCount.put(productId, newCount);
        }finally {
            lock.writeLock().unlock();
        }
//        }
    }
}
