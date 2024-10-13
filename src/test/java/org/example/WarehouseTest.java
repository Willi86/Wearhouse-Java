package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// add product
class WarehouseTest {

    @Test
    void testAddProductSuccess() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("1", "Test Product", Category.ACTION, 5, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);
        assertNotNull(warehouse.getProductById("1").orElse(null));
    }

    @Test
    void testAddProductDuplicateIdFailure() {
        Warehouse warehouse = new Warehouse();

        // Add the first product with ID "1"
        Product product1 = new Product("1", "Halo Infinite", Category.ACTION, 5, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product1);

        // Try to add another product with the same ID "1" - this should fail
        Product product2 = new Product("1", "Forza Horizon 5", Category.RACING, 4, LocalDate.now(), LocalDate.now());

        // Test should expect an IllegalArgumentException for duplicate product ID
        assertThrows(IllegalArgumentException.class, () -> warehouse.addProduct(product2));
    }

    @Test
    public void testAddProductUI() {
        Warehouse warehouse = new Warehouse();

        // Simulate user input
        String input = "100\nNew Product\nFPS\n9\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Simulate output capture
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner(System.in);

        // Call the method
        App.addProductUI(warehouse, scanner);

        // Assert product was added
        Product product = warehouse.getProductById("100").orElseThrow();
        assertThat(product.name()).isEqualTo("New Product");

        // Verify the output
        String output = out.toString();
        assertTrue(output.contains("Product added successfully!"));
    }

}
