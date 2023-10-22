public class Product extends Menu {
    private final float price;

    public Product(String name, float price, String description) {
        super(name, description);
        this.price = price;
    }

    @Override
    public String printMenu() {
        return super.getName() + " (W " + price + ")" + " - "  + super.getDescription();
    }

    public float getPrice() {
        return price;
    }
}