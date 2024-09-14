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

public class InvalidRatingTest {

    @Test
    public void testModifyProductUI_InvalidRating() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("100", "Test Product", Category.FPS, 9, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        // Simulate user input for product modification with invalid rating (out of range)
        String input = "100\nNew Name\nFPS\n11\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Scanner scanner = new Scanner(System.in);

        // Call the method to test
        App.modifyProductUI(warehouse, scanner);

        // Assert that the product was not modified due to invalid rating
        String output = out.toString();
        assertTrue(output.contains("Rating must be between 0 and 10. Product not modified."));
    }

}
