package org.example;

import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class GetProductByCategoryTest {

    @Test
    void testGetProductsByCategory() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(new Product("1", "Product 1", Category.ACTION, 8, LocalDate.now(), LocalDate.now()));
        warehouse.addProduct(new Product("2", "Product 2", Category.ACTION, 7, LocalDate.now(), LocalDate.now()));
        List<Product> products = warehouse.getProductsByCategory(Category.ACTION);
        assertEquals(2, products.size(), "The number of products should be 2");
    }

}
