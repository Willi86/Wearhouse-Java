package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetAllProductsTest {
//Verifies Warehouse returns a list with 2 products
    @Test
    void testGetAllProducts() {

        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(new Product("1", "Product 1", Category.ACTION, 8, LocalDate.now(), LocalDate.now()));
        warehouse.addProduct(new Product("2", "Product 2", Category.FPS, 7, LocalDate.now(), LocalDate.now()));


        List<Product> products = warehouse.getAllProducts();

        assertEquals(2, products.size(), "The number of products should be 2");
    }
// checks if the listAllProducts contains the product name
    @Test
    public void testListAllProducts() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(new Product("100", "Test Product", Category.FPS, 9, LocalDate.now(), LocalDate.now()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App.listAllProducts(warehouse);

        String output = out.toString();
        assertTrue(output.contains("Test Product"));
    }



}
