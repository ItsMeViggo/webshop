package se.iths.viggo.webshop;

import se.iths.viggo.webshop.model.Discount;
import se.iths.viggo.webshop.model.HalfPriceDiscount;
import se.iths.viggo.webshop.model.Product;
import se.iths.viggo.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {
    private ProductStorage productStorage;
    private OutInputHandler outInputHandler;
    private Discount discount = null;
    private boolean isRunning = true;

    public WebShopManager(ProductStorage productStorage, OutInputHandler outInputHandler) {
        this.productStorage = productStorage;
        this.outInputHandler = outInputHandler;
    }

    public void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    public List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    public Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    public void startWebshop() {
        discount = getDiscount();

        while (isRunning) {
            switch (getMenuChoice()) {
                case 1 -> saveProductToStorage(createProduct());
                case 2 -> listAllProducts(getProductsFromStorage());
                case 3 -> {
                    String articleNumber = outInputHandler.prompt("Sök efter ett artikelnummer: ");
                    showInformationAboutAProduct(articleNumber);
                }
                case 4 -> isRunning = false;
                default -> outInputHandler.info("Fel inmatning");
            }
        }
    }

    public Discount getDiscount() {
        String discountCode = IO.readln("Skriv in rabattkod: ");

        if (discountCode.equals("halfprice")) {
            discount = new HalfPriceDiscount();
        } else {
            discount = new TenPercentDiscount();
        }

        return discount;
    }

    private int getMenuChoice() {
        int menuChoice = 0;
        do {
            try {
                menuChoice = Integer.parseInt(outInputHandler.menu());
            } catch (NumberFormatException e) {
                outInputHandler.info("Det behöver vara ett nummer");
            }
        } while (menuChoice < 1 || menuChoice > 4);
        return menuChoice;
    }

    private Product createProduct() {
        // TODO : Check if article number already exists.
        String articleNumber = outInputHandler.prompt("Skriv in ett artikel nummer: ");
        String title = outInputHandler.prompt("Skriv in en title på produkten: ");
        // TODO : Convert ',' to '.' and try in a loop.
        String priceInput = outInputHandler.prompt("Skriv in ett pris: ");
        double price = 0;
        try {
            price = Double.parseDouble(priceInput);
        } catch (NumberFormatException e) {
            outInputHandler.info("Det måste vara ett nummer. Priset blir 0.");
        }
        String description = outInputHandler.prompt("Skriv en produkt beskrivning: ");

        return new Product(articleNumber, title, price, description);
    }

    private void listAllProducts(List<Product> products) {
        for (Product product : products) {
            printProduct(product);
        }
    }

    private void showInformationAboutAProduct(String articleNumber) {
        Product product = getProductFromStorage(articleNumber);

        if (product != null) {
            printProduct(product);
        } else {
            outInputHandler.info("Det fanns inte någon produkt med '" + articleNumber + "' artikel nummer.");
        }
    }

    private void printProduct(Product product) {
        double newPrice = discount.calculatePrice(product.getPrice());
        outInputHandler.info(product.getArticleNumber() + " | " + product.getTitle() +
                " - " + newPrice + "(" + product.getPrice() + ") ..." +
                product.getDescription());
    }
}
