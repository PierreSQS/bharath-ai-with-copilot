package com.bharath.copilot;

import java.util.HashMap;
import java.util.Map;

// InventoryManager class that uses a map and adds, removes, lists and updates products
public class InventoryManager {
    private final Map<Integer, Product> products = new HashMap<>();

    //add product
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    //remove product
    public void removeProduct(int id) {
        products.remove(id);
    }

    //list products
    public void listProducts() {
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    //update product quantity given the id and quantity
    public void updateProductQuantity(int id, int quantity) {
        Product product = products.get(id);
        product.setQuantity(quantity);
    }

    //main method
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addProduct(new Product(1, "Laptop", 100));
        inventoryManager.addProduct(new Product(2, "Mobile", 200));
        inventoryManager.addProduct(new Product(3, "Tablet", 300));
        inventoryManager.listProducts();
        inventoryManager.removeProduct(2);
        inventoryManager.listProducts();
        inventoryManager.updateProductQuantity(3, 500);
        inventoryManager.listProducts();
    }
}