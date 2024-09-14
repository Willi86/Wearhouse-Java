package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateProductTest {

    @Test
    void testUpdateProductSuccess() {
        Warehouse warehouse = new Warehouse();
        Product originalProduct = new Product("1", "Original Product", Category.ACTION, 8, LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));
        warehouse.addProduct(originalProduct);

        // Update the product
        boolean result = warehouse.updateProduct("1", "Updated Product", Category.SPORTS, 9);

        // Check the result
        assertTrue(result);

        // Retrieve the updated product
        Product updatedProduct = warehouse.getProductById("1").orElse(null);
        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.name());
        assertEquals(Category.SPORTS, updatedProduct.category());
        assertEquals(9, updatedProduct.rating());
        // Ensure the creation date remains the same, but the modification date should be updated
        assertEquals(originalProduct.creationDate(), updatedProduct.creationDate());
        assertTrue(updatedProduct.modificationDate().isAfter(originalProduct.modificationDate()));
    }

    @Test
    void testUpdateProductFailure() {
        Warehouse warehouse = new Warehouse();
        // Attempt to update a product that does not exist
        boolean result = warehouse.updateProduct("non-existent-id", "New Name", Category.FPS, 7);

        // Check the result
        assertFalse(result);

        // Verify no product has been added
        Product product = warehouse.getProductById("non-existent-id").orElse(null);
        assertNull(product);
    }
}
