public class Product extends Menu {

    private float price;

    public Product(String name, float price, String description) {
        super(name, description);
        this.price = price;
    }

    @Override
    public void printMenu() {
        System.out.printf("%-15s | W %-4s | %-30s\n",
                super.getName(), String.format("%.1f", price), super.getDescription());
    }
}