package se.iths.viggo.webshop.model;

public class TenPercentDiscount extends Discount {

    public TenPercentDiscount() {
        super("10% av alla produkter.");
    }

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.9;
    }
}
