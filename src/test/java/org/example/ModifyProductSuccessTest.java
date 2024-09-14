package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ModifyProductSuccessTest {

    @Test
    void testModifyProductSuccess() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("1", "Old Name", Category.ACTION, 8, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);
        warehouse.updateProduct("1", "New Name", Category.SPORTS, 9);
        Product updatedProduct = warehouse.getProductById("1").orElse(null);
        assertNotNull(updatedProduct);
        assertEquals("New Name", updatedProduct.name());
        assertEquals(Category.SPORTS, updatedProduct.category());
        assertEquals(9, updatedProduct.rating());
    }

    @Test
    void testModifyProductFailure() {
        Warehouse warehouse = new Warehouse();
        assertFalse(warehouse.updateProduct("nonexistentId", "New Name", Category.SPORTS, 9));
    }
}
