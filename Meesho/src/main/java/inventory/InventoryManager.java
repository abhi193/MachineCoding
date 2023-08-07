package inventory;

import db.DBAccessor;

public class InventoryManager implements InventoryManagement{

    private final DBAccessor dbAccessor;

    public InventoryManager(DBAccessor dbAccessor) {
        this.dbAccessor = dbAccessor;
    }


    @Override
    public void createProduct(String productId, String name, Integer count) {
        dbAccessor.createProduct(productId,name,count);
    }

    @Override
    public Integer getInventory(String productId) {
        return dbAccessor.getInventory(productId);
    }

    @Override
    public void blockInventory(String productId, Integer count, String orderId) {
        dbAccessor.blockInventory(productId,count,orderId);
    }

    @Override
    public void confirmOrder(String orderId) {
        dbAccessor.confirmOrder(orderId);
    }



}
