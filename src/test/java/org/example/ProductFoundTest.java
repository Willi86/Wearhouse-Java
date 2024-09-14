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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductFoundTest {

    @Test
    public void testFindProductByIdUI_ProductFound() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("101", "Test Product", Category.FPS, 9, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        // Simulate user input for product ID
        String input = "101\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner(System.in);

        // Call the method to test
        App.findProductByIdUI(warehouse, scanner);

        // Assert output contains the product details
        String output = out.toString();
        assertTrue(output.contains("Product found: " + product.toString()));
    }

    @Test
    public void testFindProductByIdUI_ProductNotFound() {
        Warehouse warehouse = new Warehouse();

        // Simulate user input for a non-existent product ID
        String input = "999\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner(System.in);

        // Call the method to test
        App.findProductByIdUI(warehouse, scanner);

        // Assert output says product not found
        String output = out.toString();
        assertTrue(output.contains("Product not found."));
    }





}
