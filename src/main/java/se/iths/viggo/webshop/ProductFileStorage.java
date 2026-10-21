package se.iths.viggo.webshop;

import se.iths.viggo.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {

    private Path filePath = Path.of("products.txt");

    @Override
    public void saveProduct(Product product) {
        String productLine = product.toFileLine();
        try {
            Files.writeString(filePath, productLine + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            IO.println("File error: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getProducts() {
        if (Files.notExists(filePath)) {
            return List.of();
        }

        List<String> productLines = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        try {
            productLines = Files.readAllLines(filePath);
        } catch (IOException e) {
            IO.println("Could not find file.");
        }

        for (String row : productLines) {
            Product product = Product.fromFileLine(row);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product getProduct(String articleNumber) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getArticleNumber().equals(articleNumber)) {
                return product;
            }
        }

        return null;
    }
}
