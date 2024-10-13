package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.validation.Valid;
import org.example.entities.*;
import org.example.service.ProductInitializer;
import org.example.service.Warehouse;

import java.util.*;
import java.util.stream.Collectors;

@Path("/products")  // Base URL: /products
@Produces(MediaType.APPLICATION_JSON)  // All responses will be in JSON format
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final Warehouse warehouse;

    // Constructor to initialize the service
    public ProductResource() {
        this.warehouse = new Warehouse();
        ProductInitializer.addInitialProducts(warehouse);  // Add initial products

    }

    // 1. Add a new product
    @POST
    public Response addProduct(@Valid Product product) {
        try {
            System.out.println("Received POST request to add product: " + product);

            warehouse.addProduct(product);
            return Response.status(Response.Status.CREATED).entity("Product added successfully.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // 2. Get all products
    @GET
    public Response getAllProducts(@QueryParam("start") @DefaultValue("0") int start,
                                   @QueryParam("size") @DefaultValue("10") int size) {
        List<Product> allProducts = warehouse.getAllProducts();
        List<Product> paginatedProducts = allProducts.stream()
                .skip(start)
                .limit(size)
                .collect(Collectors.toList());

        return Response.ok(paginatedProducts).build();
    }

    // 3. Get a single product by ID
    @GET
    @Path("/{id}")  // URL: /products/{id}
    public Response getProductById(@PathParam("id") String id) {
        Optional<Product> product = warehouse.getProductById(id);
        if (product.isPresent()) {
            return Response.ok(product.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found with ID: " + id).build();
        }
    }

    // 4. Get all products by category
    @GET
    @Path("/category/{category}")  // URL: /products/category/{category}
    public Response getProductsByCategory(@PathParam("category") Category category) {
        List<Product> productsInCategory = warehouse.getProductsByCategory(category);
        if (productsInCategory.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No products found in category: " + category).build();
        }
        return Response.ok(productsInCategory).build();
    }
}
