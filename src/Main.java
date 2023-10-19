import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Menu> menus = new ArrayList<>();
    private static final Order order = new Order();

    public static void main(String[] args) {
        initData();
        start();
    }

    //
    public static void initData() {
        Menu mainMenu = new Menu("메인메뉴", "다양한 맛을 즐길 수 있는 엽떡의 메인 떡볶이");
        mainMenu.getProducts().add(new Product("엽기메뉴", 14.0f, ""));
        mainMenu.getProducts().add(new Product("로제메뉴", 16.0f, ""));
        mainMenu.getProducts().add(new Product("짜장메뉴", 16.0f, ""));
        mainMenu.getProducts().add(new Product("마라떡볶이", 16.0f, ""));
        mainMenu.getProducts().add(new Product("엽기닭볶음탕", 24.0f, ""));
        mainMenu.getProducts().add(new Product("2인 엽기떡볶이", 9.0f, ""));

        Menu chickenFeetMenu = new Menu("닭발메뉴", "엽떡만의 비밀 소스로 만들어낸 중독성 있는 닭발메뉴");
        chickenFeetMenu.getProducts().add(new Product("엽기 무뼈닭발", 15.0f, ""));
        chickenFeetMenu.getProducts().add(new Product("엽기 국물닭발", 15.0f, ""));
        chickenFeetMenu.getProducts().add(new Product("엽기 오돌뼈", 14.0f, ""));

        Menu setMenu = new Menu("세트메뉴", "엽떡의 다양한 메뉴를 한 번에 즐길 수 있는 세트메뉴");
        setMenu.getProducts().add(new Product("실속세트", 17.5f, "떡볶이 + 주먹김밥 + 모둠튀김(김말이1개, 야채튀김1개, 만두2개)"));
        setMenu.getProducts().add(new Product("베스트세트", 20.0f, "떡볶이 + 주먹김밥 + 모둠튀김(김말이1개, 야채튀김1개, 만두2개) + 중국당면"));
        setMenu.getProducts().add(new Product("스페셜세트", 25.0f, "떡볶이 + 주먹김밥 + 모둠튀김(김말이1개, 야채튀김1개, 만두2개) + 중국당면 + 엽봉(5개)"));
        setMenu.getProducts().add(new Product("닭발세트", 17.5f, "닭발메뉴 + 주먹김밥 + 계란찜"));

        Menu sideMenu = new Menu("사이드메뉴", "엽떡의 메인메뉴와 함께 먹으면 맛이 두배가 되는 특별한 사이드메뉴");
        sideMenu.getProducts().add(new Product("엽기오돌뼈밥", 4.5f, ""));

        menus.add(mainMenu);
        menus.add(chickenFeetMenu);
        menus.add(setMenu);
        menus.add(sideMenu);
    }

    public static void start() {
        while(true) {
            clearScreen();
            printMainMenu();

            System.out.print("메뉴를 선택하세요 >>> ");
            String menu = scanner.nextLine();

            if(!Pattern.matches("^[0-6]", menu)) break;
            productScreen(Integer.parseInt(menu));
        }
    }

    public static void productScreen(int menu) {
        clearScreen();
        printProductMenu(menu);

        System.out.print("상품을 선택하세요 >>> ");
        String product = scanner.nextLine();


    }

    //
    public static void printMainMenu() {
        System.out.println("엽기떡볶이에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ 엽기떡볶이 메뉴 ]");
        for (int i = 1; i <= menus.size(); i++) {
            System.out.print(i + ". ");
            menus.get(i-1).printMenu();
        }

        System.out.println();
        System.out.println("[ 주문 메뉴 ]");
        System.out.printf("%-8s | %-30s\n", "5. 주문", "장바구니를 확인 후 주문합니다.");
        System.out.printf("%-8s | %-30s\n", "6. 취소", "진행중인 주문을 취소합니다.");
        System.out.println();
    }

    public static void printProductMenu(int idx) {
        System.out.println("엽기떡볶이에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        menus.get(idx-1).printMenu();

        System.out.println("[ " + menus.get(idx-1).getName() + " MENU ]");

        List<Product> products = menus.get(idx-1).getProducts();
        for (int i = 1; i <= products.size(); i++) {
            System.out.print(i + ". ");
            products.get(i-1).printMenu();
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 80; i++) {
            System.out.println(" ");
        }
    }
}