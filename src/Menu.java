import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private String name;
    private String description;
    private final List<Product> products = new ArrayList<>();

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addProducts(Product...products) {
        this.products.addAll(Arrays.asList(products));
    }

    public List<Product> getProducts() {
        return products;
    }

    public String printMenu() {
        return name + " - "  + description;
    }
}