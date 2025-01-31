package com.bharath.copilot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryManagerTest {
    InventoryManager inventoryManager;

    @BeforeEach
    void setUp() {
        inventoryManager = new InventoryManager();
    }

    @Test
    void addProductSuccessfully() {
        Product product = Product.builder().id(1).name("Laptop").quantity(5).build();
        inventoryManager.addProduct(product);

        // Junit assertions
        assertEquals(1, inventoryManager.listProducts().size());
        assertEquals(product, inventoryManager.listProducts().getFirst());

        // AssertJ assertions
        assertThat(inventoryManager.listProducts()).hasSize(1);
        assertThat(inventoryManager.listProducts().getFirst()).isEqualTo(product);

    }

    @Test
    void addProductThrowsExceptionWhenProductIsNull() {
        // Junit assertions
        assertThrows(IllegalArgumentException.class, () -> inventoryManager.addProduct(null));

        // AssertJ assertions
        assertThatThrownBy(() -> inventoryManager.addProduct(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product cannot be null");
    }

    @Test
    void addProductThrowsExceptionWhenProductAlreadyExists() {
        Product product = Product.builder().id(1).name("Laptop").quantity(5).build();
        inventoryManager.addProduct(product);

        // Junit assertions
        assertThrows(IllegalArgumentException.class, () -> inventoryManager.addProduct(product));

        // AssertJ assertions
        assertThatThrownBy(() -> inventoryManager.addProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with ID="+product.getId()+" already exists");


    }

    @Test
    void removeProductSuccessfully() {
        Product product = Product.builder().id(1).name("Laptop").quantity(5).build();
        inventoryManager.addProduct(product);
        inventoryManager.removeProduct(1);

        // Junit assertions
        assertTrue(inventoryManager.listProducts().isEmpty());

        // AssertJ assertions
        assertThat(inventoryManager.listProducts()).isEmpty();
    }

    @Test
    void removeProductThrowsExceptionWhenProductDoesNotExist() {
        // Junit assertions
        assertThrows(IllegalArgumentException.class, () -> inventoryManager.removeProduct(1));

        // AssertJ assertions
        assertThatThrownBy(() -> inventoryManager.removeProduct(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with this ID does not exist");
    }

    @Test
    void listProductsReturnsAllProducts() {
        Product product1 = Product.builder().id(1).name("Laptop").quantity(5).build();
        Product product2 = Product.builder().id(2).name("Mobile").quantity(10).build();
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);
        List<Product> products = inventoryManager.listProducts();

        // Junit assertions
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));

        // AssertJ assertions
        assertThat(products).hasSize(2).contains(product1, product2);
    }

    @Test
    void updateProductQuantitySuccessfully() {
        Product product = Product.builder().id(1).name("Laptop").quantity(5).build();
        inventoryManager.addProduct(product);
        inventoryManager.updateProductQuantity(1, 10);

        // Junit assertions
        assertEquals(10, inventoryManager.listProducts().getFirst().getQuantity());

        // AssertJ assertions
        assertThat(inventoryManager.listProducts().getFirst().getQuantity()).isEqualTo(10);
    }

    @Test
    void updateProductQuantityThrowsExceptionWhenProductDoesNotExist() {
        // Given
        int productId = 1;
        // Junit5 assertions
        assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.updateProductQuantity(productId, 10));

        // AssertJ assertions
        assertThatThrownBy(() -> inventoryManager.updateProductQuantity(productId, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with this ID="+productId+" does not exist");
    }

    @Test
    void updateProductQuantityThrowsExceptionWhenQuantityIsNegative() {
        Product product = Product.builder().id(1).name("Laptop").quantity(5).build();
        inventoryManager.addProduct(product);

        // Junit5 assertions
        assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.updateProductQuantity(1, -1));

        // AssertJ assertions
        assertThatThrownBy(() -> inventoryManager.updateProductQuantity(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantity cannot be negative");
    }
}