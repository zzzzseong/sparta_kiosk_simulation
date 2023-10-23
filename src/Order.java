import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Order {
    private final List<CartData> cart = new ArrayList<>();
    private final Queue<OrderData> order = new LinkedList<>();

    private int orderNumber;
    private double totalPrice;

    public Order() {
        this.orderNumber = 0;
        this.totalPrice = 0;
    }

    public List<CartData> getCart() {
        return cart;
    }

    public Queue<OrderData> getOrder() {
        return order;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getCartPrice() {
        return cart.stream().mapToDouble(
                cartData -> cartData.getProduct().getPrice() * cartData.getCount()
        ).sum();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void addProductToCart(Product product) {
        boolean exist = false;
        for (int i = 0; i < cart.size(); i++) {
            CartData cartData = cart.get(i);
            if(cartData.getProduct().equals(product)) {
                exist = true;
                cartData.setCount(cartData.getCount()+1);
                break;
            }
        }

        if(!exist) cart.add(new CartData(product));
    }

    public void addOrder() {
        for (CartData cartData : cart) {
            Product product = cartData.getProduct();
            for (int i = 0; i < cartData.getCount(); i++) {
                order.offer(new OrderData(product.getName(), product.getPrice()));
            }
        }

        totalPrice += this.getCartPrice();
        orderNumber++;
        cart.clear();
    }

    public void clearCart() {
        cart.clear();
    }
}

class CartData {
    private Product product;
    private int count;

    public CartData(Product product) {
        this.product = product;
        this.count = 1;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}

class OrderData {
    private String productName;
    private double price;

    public OrderData(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}