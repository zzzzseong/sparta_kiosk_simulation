import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final Scanner scanner = new Scanner(System.in);

    private final List<Menu> menus = new ArrayList<>();
    private final Order order = new Order();

    public Kiosk() {
        initData();
    }

    public void start() throws Exception {
        while(true) {
            String select = mainScreen();
            switch (select) {
                case "1", "2", "3", "4" -> productScreen(Integer.parseInt(select)-1);
                case "5" -> orderScreen();
                case "6" -> cancelScreen();
                default -> System.out.println("!!!올바른 값을 입력해주세요!!!");
            }
        }
    }

    public String mainScreen() {
        System.out.println("========================================================================");
        System.out.println("엽기떡볶이에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ 엽기떡볶이 메뉴 ]");
        for (int i = 1; i <= menus.size(); i++) {
            System.out.println(i + ". " + menus.get(i-1).printMenu());
        }

        System.out.println();
        System.out.println("[ 주문 메뉴 ]");
        System.out.println("5. 주문 - 장바구니를 확인 후 주문합니다.");
        System.out.println("6. 취소 - 진행중인 주문을 취소합니다. \n");

        System.out.print("메뉴를 선택하세요: ");
        return scanner.nextLine();
    }

    public void productScreen(int menuIdx) {
        Menu curMenu = menus.get(menuIdx);
        List<Product> products = curMenu.getProducts();

        while(true) {
            System.out.println("========================================================================");
            System.out.println("엽기떡볶이에 오신걸 환영합니다.");
            System.out.println("아래 메뉴판을 보시고 상품을 골라 입력해주세요.\n");
            System.out.println("[ " + curMenu.getName() + " MENU ]");
            for (int i = 1; i <= products.size(); i++) {
                System.out.println(i + ". " + products.get(i-1).printMenu());
            }
            System.out.println("0. 뒤로가기\n");
            System.out.print("상품을 선택하세요: ");

            try {
                int productIdx = Integer.parseInt(scanner.nextLine());

                if(productIdx < 0 || productIdx > products.size())
                    throw new RuntimeException("");
                else {
                    if(productIdx == 0) break;
                    addProductToCart(menuIdx, productIdx-1);
                    break;
                }
            } catch(RuntimeException e) {
                System.out.println("!!!올바른 값을 입력해주세요!!!");
            }
        }
    }

    public void orderScreen() throws Exception {
        if(order.getCart().isEmpty()) {
            System.out.println("========================================================================");
            System.out.println("장바구니가 비어있습니다.");
            return;
        }

        boolean run = true;
        while(run) {
            System.out.println("========================================================================");
            System.out.println("아래와 같이 주문 하시겠습니까?\n");

            System.out.println("[ Orders ]");
            for (Menu menu : order.getCart()) {
                Product product = (Product) menu;
                System.out.println(product.printMenu());
            }
            System.out.println("\n[ Total ]");
            System.out.println("W " + order.getTotalPrice() + "\n");
            System.out.println("1. 주문   2. 메뉴판");
            System.out.print("입력: ");

            switch (scanner.nextLine()) {
                case "1" -> {
                    order.addOrder();
                    System.out.println("주문이 완료되었습니다!\n");
                    System.out.println("대기번호는 [ " + order.getOrder().size() + " ] 번입니다.");
                    System.out.println("(3초 후 메뉴판으로 돌아갑니다.)");
                    Thread.sleep(3000);
                    run = false;
                }
                case "2" -> run = false;
                default -> System.out.println("!!!올바른 값을 입력해주세요!!!");
            };
        }
    }

    public void cancelScreen() {
        if(order.getCart().isEmpty()) {
            System.out.println("========================================================================");
            System.out.println("장바구니가 비어있습니다");
            return;
        }

        boolean run = true;
        while(run) {
            System.out.println("========================================================================");
            System.out.println("진행하던 주문을 취소하시겠습니까?");
            System.out.println("1. 확인   2. 취소");
            System.out.print("입력: ");

            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.println("진행하던 주문이 취소되었습니다.");
                    order.clearCart();
                    run = false;
                }
                case "2" -> run = false;
                default -> System.out.println("!!!올바른 값을 입력해주세요!!!");
            }
        }
    }

    public void addProductToCart(int menuIdx, int productIdx) {
        boolean run = true;
        while(run) {
            System.out.println("========================================================================");
            Product product = menus.get(menuIdx).getProducts().get(productIdx);

            System.out.println(product.printMenu());
            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인   2. 취소");
            System.out.print("입력: ");

            switch (scanner.nextLine()) {
                case "1" -> {
                    order.addProductToCart(product);
                    System.out.println("\n" + product.getName() + " 이(가) 장바구니에 추가되었습니다.");
                    run = false;
                }
                case "2" -> run = false;
                default -> System.out.println("!!!올바른 값을 입력해주세요!!!");
            }
        }
    }

    public void initData() {
        Menu mainMenu = new Menu("메인메뉴", "다양한 맛을 즐길 수 있는 엽떡의 메인 떡볶이");
        mainMenu.addProducts(
                new Product("엽기메뉴", 14.0f, "기본 맛 떡볶이"),
                new Product("로제메뉴", 16.0f, "로제 맛 떡볶이"),
                new Product("짜장메뉴", 16.0f, "짜장 맛 떡볶이"),
                new Product("마라떡볶이", 16.0f, "마라 맛 떡볶이"),
                new Product("엽기닭볶음탕", 24.0f, "엽떡만의 비밀소스로 만든 마약 닭볶음탕"),
                new Product("2인 엽기떡볶이", 9.0f, "엽기떡볶이를 2인분으로 즐겨보세요")
        );

        Menu chickenFeetMenu = new Menu("닭발메뉴", "엽떡만의 비밀 소스로 만들어낸 중독성 있는 닭발메뉴");
        chickenFeetMenu.addProducts(
            new Product("엽기 무뼈닭발", 15.0f, ""),
            new Product("엽기 국물닭발", 15.0f, ""),
            new Product("엽기 오돌뼈", 14.0f, "")
        );

        Menu setMenu = new Menu("세트메뉴", "엽떡의 다양한 메뉴를 한 번에 즐길 수 있는 세트메뉴");
        setMenu.addProducts(
            new Product("실속세트", 17.5f, "떡볶이 + 주먹김밥 + 모둠튀김(김말이1개, 야채튀김1개, 만두2개)"),
            new Product("베스트세트", 20.0f, "떡볶이 + 주먹김밥 + 모둠튀김(김말이1개, 야채튀김1개, 만두2개) + 중국당면"),
            new Product("스페셜세트", 25.0f, "떡볶이 + 주먹김밥 + 모둠튀김(김말이1개, 야채튀김1개, 만두2개) + 중국당면 + 엽봉(5개)"),
            new Product("닭발세트", 17.5f, "닭발메뉴 + 주먹김밥 + 계란찜")
        );

        Menu sideMenu = new Menu("사이드메뉴", "엽떡의 메인메뉴와 함께 먹으면 맛이 두배가 되는 특별한 사이드메뉴");
        sideMenu.addProducts(
                new Product("엽기오돌뼈밥", 4.5f, "")
        );


        menus.add(mainMenu);
        menus.add(chickenFeetMenu);
        menus.add(setMenu);
        menus.add(sideMenu);
    }
}
