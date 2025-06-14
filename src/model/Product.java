package model;

import java.time.LocalDate;
import java.util.*;

public class Product {
    private int id;
    private String sku;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private double price;

    private Map<LocalDate, Integer> dailySales; // Sales by date
    private LocalDate predictedLowStockDate;

    private String supplierName;
    private String supplierContact;
    private String warehouseLocation;
    private LocalDate lastRestocked;
    private LocalDate expirationDate;

    private Set<String> tags; // e.g., "new", "on_sale", "discontinued"

    public Product(int id, String sku, String name, String category, String description, int quantity, double price,
                   String supplierName, String supplierContact, String warehouseLocation,
                   LocalDate lastRestocked, LocalDate expirationDate, Set<String> tags) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.supplierName = supplierName;
        this.supplierContact = supplierContact;
        this.warehouseLocation = warehouseLocation;
        this.lastRestocked = lastRestocked;
        this.expirationDate = expirationDate;
        this.tags = tags != null ? tags : new HashSet<>();
        this.dailySales = new HashMap<>();
        this.predictedLowStockDate = null;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public Map<LocalDate, Integer> getDailySales() { return dailySales; }
    public String getSupplierName() { return supplierName; }
    public String getSupplierContact() { return supplierContact; }
    public String getWarehouseLocation() { return warehouseLocation; }
    public LocalDate getLastRestocked() { return lastRestocked; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public Set<String> getTags() { return tags; }
    public LocalDate getPredictedLowStockDate() { return predictedLowStockDate; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setLastRestocked(LocalDate date) { this.lastRestocked = date; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
    public void setPredictedLowStockDate(LocalDate date) { this.predictedLowStockDate = date; }

    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
    }

    public void addSale(LocalDate date, int quantitySold) {
        dailySales.put(date, dailySales.getOrDefault(date, 0) + quantitySold);
    }

    public double getTotalRevenueOverPeriod(LocalDate start, LocalDate end) {
        return dailySales.entrySet().stream()
            .filter(entry -> !entry.getKey().isBefore(start) && !entry.getKey().isAfter(end))
            .mapToDouble(entry -> entry.getValue() * price)
            .sum();
    }

    public int getTotalQuantitySoldOverPeriod(LocalDate start, LocalDate end) {
        return dailySales.entrySet().stream()
            .filter(entry -> !entry.getKey().isBefore(start) && !entry.getKey().isAfter(end))
            .mapToInt(Map.Entry::getValue)
            .sum();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Qty: %d | $%.2f | Tags: %s", sku, name, quantity, price, tags);
    }
}
