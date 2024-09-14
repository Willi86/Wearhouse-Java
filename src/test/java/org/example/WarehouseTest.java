package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
// add product
class WarehouseTest {

    @Test
    void testAddProductSuccess() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("1", "Test Product", Category.ACTION, 8, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);
        assertNotNull(warehouse.getProductById("1").orElse(null));
    }

    @Test
    void testAddProductFailure() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("1", "", Category.ACTION, 8, LocalDate.now(), LocalDate.now());
        assertThrows(IllegalArgumentException.class, () -> warehouse.addProduct(product));
    }
}
