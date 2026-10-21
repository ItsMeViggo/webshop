package se.iths.viggo.webshop.model;

public class HalfPriceDiscount extends Discount {

    public HalfPriceDiscount() {
        super("Halva priset av allt");
    }

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.5;
    }
}
