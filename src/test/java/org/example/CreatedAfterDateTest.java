package org.example;
import org.example.entities.Category;
import org.example.entities.Product;
import org.example.service.Warehouse;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;  // Import for assertions


public class CreatedAfterDateTest {

    @Test
    void testGetProductsCreatedAfter() {
        Warehouse warehouse = new Warehouse();
        LocalDate date = LocalDate.of(2023, 1, 1);
        warehouse.addProduct(new Product("1", "Product 1", Category.ACTION, 8, LocalDate.of(2023, 2, 1), LocalDate.now()));
        warehouse.addProduct(new Product("2", "Product 2", Category.FPS, 8, LocalDate.of(2022, 12, 31), LocalDate.now()));

        List<Product> products = warehouse.getProductsCreatedAfter(date);
        assertEquals(1, products.size());
        assertEquals("Product 1", products.getFirst().name());
    }
    @Test
    void testGetProductsCreatedAfterNoProducts() {
        Warehouse warehouse = new Warehouse();
        LocalDate date = LocalDate.of(2023, 1, 1);

        List<Product> products = warehouse.getProductsCreatedAfter(date);
        assertEquals(0, products.size());
    }

}





