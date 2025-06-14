package model;

import java.time.LocalDateTime;

public class TransactionLogEntry {
    private String saleId;
    private int productId;
    private String sku;
    private int quantitySold;
    private LocalDateTime timestamp;
    private String action; // sale, restock, return, adjustment

    public TransactionLogEntry(String saleId, int productId, String sku, int quantitySold, String action) {
        this.saleId = saleId;
        this.productId = productId;
        this.sku = sku;
        this.quantitySold = quantitySold;
        this.timestamp = LocalDateTime.now();
        this.action = action.toLowerCase();
    }

    public String getSaleId() { return saleId; }
    public int getProductId() { return productId; }
    public String getSku() { return sku; }
    public int getQuantitySold() { return quantitySold; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAction() { return action; }

    @Override
    public String toString() {
        return "[" + timestamp + "] "
                + action.toUpperCase()
                + " | Sale ID: " + saleId
                + " | Product ID: " + productId
                + " | SKU: " + sku
                + " | Quantity: " + quantitySold;
    }
}
