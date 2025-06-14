package app;

import controller.InventoryManager;
import model.Product;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        // Step 1: Add a sample product
        Set<String> tags = new HashSet<>();
        tags.add("new");
        tags.add("on_sale");

        Product addedProduct = manager.addProduct(
                "SKU1001",
                "Wireless Mouse",
                "Electronics",
                "High precision ergonomic wireless mouse",
                50,
                24.99,
                "Tech Supplies Inc.",
                "support@techsupplies.com",
                "Aisle 2 - Bin 5",
                LocalDate.now(),
                LocalDate.now().plusMonths(12),
                tags
        );

        System.out.println("\n✅ Product added:");
        System.out.println(addedProduct);

        // Step 2: Update product quantity (simulate sale of 5 units)
        boolean success = manager.updateProductQuantity(addedProduct.getId(), -5, "sale");

        if (success) {
            System.out.println("\n✅ Sold 5 units. Updated quantity:");
            System.out.println(manager.getProductById(addedProduct.getId()));
        } else {
            System.out.println("\n❌ Failed to update quantity.");
        }

        // Step 3: View all products
        System.out.println("\n📦 All Products:");
        manager.viewAllProducts();

        // Step 4: View transaction log
        System.out.println("\n📜 Transaction Log:");
        manager.viewTransactionLog();
    }
}
