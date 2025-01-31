package com.bharath.copilot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to manage inventory that uses a Map of products
 * and provides methods to add, remove, list, and update products.
 */
public class InventoryManager {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addProduct(Product.builder().id(1).name("Laptop").quantity(5).build());
        inventoryManager.addProduct(Product.builder().id(2).name("Mobile").quantity(10).build());
        inventoryManager.addProduct(Product.builder().id(3).name("Tablet").quantity(15).build());
        inventoryManager.listProducts();
        inventoryManager.removeProduct(2);
        inventoryManager.listProducts();
        inventoryManager.updateProductQuantity(3, 20);
        inventoryManager.listProducts();
    }

    private final Map<Integer, Product> products = new HashMap<>();

    /**
     * Adds a product to the inventory.
     *
     * @param product the product to be added
     * @throws IllegalArgumentException if the product is null or already exists
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with ID="+product.getId()+" already exists");
        }
        products.put(product.getId(), product);
    }

    /**
     * Removes a product from the inventory by its ID.
     *
     * @param id the ID of the product to be removed
     * @throws IllegalArgumentException if the product does not exist
     */
    public void removeProduct(int id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with this ID does not exist");
        }
        products.remove(id);
    }

    /**
     * return a List of all products in the inventory.
     */
    public List<Product> listProducts() {
        return new ArrayList<>(products.values());
    }


    /**
     * Updates the quantity of a product in the inventory.
     *
     * @param id the ID of the product to be updated
     * @param quantity the new quantity of the product
     * @throws IllegalArgumentException if the product does not exist or quantity is negative
     */
    public void updateProductQuantity(int id, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        Product product = products.get(id);
        if (product == null) {
            throw new IllegalArgumentException("Product with this ID="+id+" does not exist");
        }
        product.setQuantity(quantity);
    }
}