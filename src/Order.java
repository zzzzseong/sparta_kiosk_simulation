import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Order {
    private final List<Menu> cart = new ArrayList<>();

    //자료구조를 변경해야할 것 같아요
    //요구사항 처럼 <Object, count> 이런식으로 담을 자료구조가 필요한데
    //리스트는 안돼서
    private final Queue<List<Menu>> order = new LinkedList<>();

    public List<Menu> getCart() {
        return cart;
    }

    public Queue<List<Menu>> getOrder() {
        return order;
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(menu -> {
            Product p = (Product) menu;
            return p.getPrice();
        }).sum();
    }

    public void addProductToCart(Product product) {
        cart.add(product);
    }

    public void addOrder() {
        order.offer(cart);
        cart.clear();
    }

    public void clearCart() {
        cart.clear();
    }
}