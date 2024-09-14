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
        assertThat(warehouse.getProductById("100")).isPresent();
        assertThat(warehouse.getProductById("100").get().name()).isEqualTo("New Product");

        // Verify the output
        String output = out.toString();
        assertTrue(output.contains("Product added successfully!"));
    }

}
