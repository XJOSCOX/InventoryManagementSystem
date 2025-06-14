package controller;

import model.Product;
import model.TransactionLogEntry;

import java.time.LocalDate;
import java.util.*;

public class InventoryManager {
    private Map<Integer, Product> productMap;
    private List<TransactionLogEntry> transactionLog;
    private int nextProductId = 1;
    private int nextSaleId = 1;

    public InventoryManager() {
        productMap = new HashMap<>();
        transactionLog = new ArrayList<>();
    }

    public Product addProduct(String sku, String name, String category, String description,
                              int quantity, double price,
                              String supplierName, String supplierContact,
                              String warehouseLocation, LocalDate lastRestocked,
                              LocalDate expirationDate, Set<String> tags) {

        int productId = nextProductId++;
        Product newProduct = new Product(productId, sku, name, category, description, quantity, price,
                supplierName, supplierContact, warehouseLocation, lastRestocked, expirationDate, tags);

        productMap.put(productId, newProduct);
        logTransaction("ADD-" + productId, productId, sku, quantity, "restock");

        return newProduct;
    }

    public Product getProductById(int productId) {
        return productMap.get(productId);
    }

    public boolean updateProductQuantity(int productId, int delta, String action) {
        Product product = productMap.get(productId);
        if (product == null) return false;

        int newQty = product.getQuantity() + delta;
        if (newQty < 0) return false; // prevent negative quantity

        product.setQuantity(newQty);
        product.setLastRestocked(LocalDate.now());
        logTransaction("TXN-" + nextSaleId++, productId, product.getSku(), Math.abs(delta), action);
        return true;
    }

    private void logTransaction(String saleId, int productId, String sku, int qty, String action) {
        TransactionLogEntry logEntry = new TransactionLogEntry(saleId, productId, sku, qty, action);
        transactionLog.add(logEntry);
    }

    public void viewTransactionLog() {
        if (transactionLog.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (TransactionLogEntry entry : transactionLog) {
                System.out.println(entry);
            }
        }
    }

    public void viewAllProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            for (Product p : productMap.values()) {
                System.out.println(p);
            }
        }
    }
}
