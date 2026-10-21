package se.iths.viggo.webshop;

public class Main {

    static void main() {
        ProductStorage productStorage = new ProductFileStorage();
        OutInputHandler outInputHandler = new ConsoleOutInputHandler();

        WebShopManager webShopManager = new WebShopManager(productStorage, outInputHandler);

        webShopManager.startWebshop();
    }
}
