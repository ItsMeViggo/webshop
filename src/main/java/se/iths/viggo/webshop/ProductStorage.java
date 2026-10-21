package se.iths.viggo.webshop;

import se.iths.viggo.webshop.model.Product;

import java.util.List;

public interface ProductStorage {

    void saveProduct(Product product);

    List<Product> getProducts();

    Product getProduct(String articleNumber);
}
