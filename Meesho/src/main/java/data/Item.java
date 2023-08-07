package data;

public class Item {


    private final String productId;
    private final Integer count;

    public Item(String productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }
}
