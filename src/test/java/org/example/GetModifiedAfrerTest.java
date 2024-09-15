package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetModifiedAfrerTest {
    @Test
    void testGetProductsModifiedAfterCreation() {
        Warehouse warehouse = new Warehouse();
        LocalDate now = LocalDate.now();

        // Create products with modification dates after their creation dates
        Product product1 = new Product("1", "Product 1", Category.ACTION, 8, now.minusDays(2), now);
        Product product2 = new Product("2", "Product 2", Category.FPS, 7, now.minusDays(3), now.minusDays(1));
        Product product3 = new Product("3", "Product 3", Category.RACING, 9, now, now); // No modification after creation

        warehouse.addProduct(product1);
        warehouse.addProduct(product2);
        warehouse.addProduct(product3);

        List<Product> products = warehouse.getProductsModifiedAfterCreation();

        // There should be two products modified after creation
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).name());
        assertEquals("Product 2", products.get(1).name());
    }
    @Test
    void testGetProductsModifiedAfterCreationNone() {
        Warehouse warehouse = new Warehouse();
        LocalDate now = LocalDate.now();

        // Create products with modification dates the same as their creation dates
        Product product1 = new Product("1", "Product 1", Category.ACTION, 8, now, now);
        Product product2 = new Product("2", "Product 2", Category.FPS, 7, now, now);

        warehouse.addProduct(product1);
        warehouse.addProduct(product2);

        List<Product> products = warehouse.getProductsModifiedAfterCreation();


        assertEquals(0, products.size());
    }

    @Test
    void testGetProductsModifiedAfterCreationEmptyWarehouse() {
        Warehouse warehouse = new Warehouse();

        List<Product> products = warehouse.getProductsModifiedAfterCreation();

        // The warehouse is empty, so the list should also be empty
        assertEquals(0, products.size());
    }
}








