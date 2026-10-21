package se.iths.viggo.webshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.viggo.webshop.model.Product;

public class WebShopManagerTest {

    @Test
    @DisplayName("Save product to storage test")
    void saveProductToStorageTest() {
        ProductStorage productStorage = new ProductListStorage();
        OutInputHandler outInputHandler = new ConsoleOutInputHandler();
        WebShopManager webShopManager = new WebShopManager(productStorage, outInputHandler);

        Product productOne = new Product("001", "test 1", 200, "Test object 1");
        Product productTwo = new Product("002", "test 2", 525, "Test object 2");
        webShopManager.saveProductToStorage(productOne);
        webShopManager.saveProductToStorage(productTwo);

        int size = productStorage.getProducts().size();

        Assertions.assertEquals(2, size);
    }

    @Test
    @DisplayName("Get products from storage")
    void getProductToStorageTest() {
        ProductStorage productStorage = new ProductListStorage();
        OutInputHandler outInputHandler = new ConsoleOutInputHandler();
        WebShopManager webShopManager = new WebShopManager(productStorage, outInputHandler);

        Product product = new Product("001", "test 1", 200, "Test object 1");
        webShopManager.saveProductToStorage(product);

        Product getProduct = webShopManager.getProductFromStorage("001");

        Assertions.assertEquals("001", getProduct.getArticleNumber());
    }

    @Test
    @DisplayName("Get products from storage but get Null")
    void getProductToStorageNullTest() {
        ProductStorage productStorage = new ProductListStorage();
        OutInputHandler outInputHandler = new ConsoleOutInputHandler();
        WebShopManager webShopManager = new WebShopManager(productStorage, outInputHandler);

        Product product = new Product("001", "test 1", 200, "Test object 1");
        webShopManager.saveProductToStorage(product);

        Product getProduct = webShopManager.getProductFromStorage("002");

        Assertions.assertNull(getProduct);
    }
}
