import data.Item;
import data.Order;
import db.DBAccessor;
import inventory.InventoryManager;

public class Tester {



    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager(new DBAccessor());
        inventoryManager.createProduct("P1","iphone14",10);
        inventoryManager.createProduct("P2","iphone14Pro",10);
        inventoryManager.createProduct("P3","iphone14Mini",10);
        inventoryManager.createProduct("P4","iphone14Plus",10);
        inventoryManager.createProduct("P5","iwatch",10);


        Item item1 = new Item("P1",2);
        Order order1 = new Order(item1);

        Item item2 = new Item("P2",2);
        Order order2 = new Order(item2);

        boolean order1Status = order1.checkoutCart();
        boolean order2Status = order2.checkoutCart();
        System.out.println("order1Status:"+order1Status);
        System.out.println("order2Status:"+order2Status);
    }
}
