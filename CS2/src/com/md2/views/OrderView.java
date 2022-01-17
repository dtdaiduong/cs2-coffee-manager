package com.md2.views;

import com.md2.function.DrinkService;
import com.md2.function.OrderService;
import com.md2.mainItems.Drink;
import com.md2.mainItems.Order;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public static ArrayList<Order> orders = new ArrayList<>();
    private final OrderService orderService;
    private final DrinkService drinkService;
    private final DrinkView drinkView;
    private final Scanner scanner;

    public OrderView() {
        drinkService = new DrinkService();
        orderService = new OrderService();
        drinkView = new DrinkView();
        scanner = new Scanner(System.in);
    }

    public static void menuOrder() {
        System.out.println("|-------------- Manager ORDER --------------|");
        System.out.println("|   1. Tạo đơn hàng mới                     |");
        System.out.println("|   2. Hiển thị danh sách đơn hàng          |");
        System.out.println("|   3. Xóa đơn hàng                         |");
        System.out.println("|   4. Quay lại Menu chính                  |");
        System.out.println("|-------------------------------------------|");
    }

    public void optionMenuOrder() {
        do {
            Scanner scanner = new Scanner(System.in);
            DrinkView drinkView = new DrinkView();
            menuOrder();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.print("⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        drinkView.show();
                        addOrder();
                        break;
                    case 2:
                        showAllOrder();
                        break;
                    case 3:
                        remove();
                        break;
                    case 4:
                        return;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        optionMenuOrder();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println("Nhập hợp lệ! Phải nhập vào một số \n");
            }
        } while (true);
    }

    public void addOrder() {
        try {
            drinkView.show();
            long id = System.currentTimeMillis() / 1000;

            System.out.println("Nhập ID thức uống cần order: ");
            System.out.printf("\t➺ ");
            int drinkId = Integer.parseInt(scanner.nextLine());
            while (!drinkService.checkDuplicateId(drinkId)) {
                System.out.println("ID món này không tồn tại!! Nhập lại nào!!");
                System.out.printf("\t➺ ");
                drinkId = Integer.parseInt(scanner.nextLine());
            }
            Drink drink = drinkService.getDrinkById(drinkId);
            double price = drink.getPrice();
            int quantity;
            do {
                System.out.println("Nhập số lượng cần đặt:");
                System.out.printf("\t➺ ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    System.out.println(" ⭆ Số lượng lớn hơn 1 mới được nhé!!");
                    break;
                } else {
                    if (quantity > 10) {
                        System.out.println("|-----------------------------------------------|");
                        System.out.printf("|%-20s %-5s |\n", "   Bạn có chắc chắn muốn order số lượng ", quantity);
                        System.out.println("|   Nhấn 'y'(yes) để xác nhận                   |");
                        System.out.println("|        'd'(don't) để nhập lại                 |");
                        System.out.println("|-----------------------------------------------|");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "y":
                                break;
                            case "d":
                                quantity = 0;
                                break;
                            default:
                                System.out.println(" ⭆ Nhập 'y' hoặc 'd' nhé!! Nhập lại nào!!");
                                break;
                        }
                        break;
                    } else {
                        break;
                    }
                }

            } while (quantity < 1);

            String drinkName = drink.getDrinkName();
            double totalPrice = quantity * price;
            Order order = new Order(id, drinkName, quantity, price, totalPrice);
            orderService.add(order);
            System.out.println("Đơn hàng " + id + "\n Đã được thêm thành công");

            boolean is = true;
            do {
                System.out.println("|--------------------------------------------|");
                System.out.println("|  Nhấn 'm'(more) để tiếp tục tạo đơn hàng   |");
                System.out.println("|       'b'(back) để quay lại Manager ORDER  |");
                System.out.println("|       'p'(printing) để in hóa đơn          |");
                System.out.println("|       'e'(exit) để thoát                   |");
                System.out.println("|--------------------------------------------|");
                System.out.print("\t➺ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "m":
                        addOrder();
                        break;
                    case "b":
                        optionMenuOrder();
                        break;
                    case "p":
                        showBill(order);
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" ⭆ vui lòng chọn lại các kí tự như trên");
                        is = false;
                        break;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Nhập sai nhé!! Nhập lại đi!!");
        }
    }

    public void showBill(Order order) {
        try {
            System.out.printf("|%-15s %-20s %-10s %-20s %-20s|\n", "ID", "Tên món", "Số lượng", "Giá món", "Tổng tiền");
            System.out.printf("|%-15d %-20s %-10d %-20f %-20f|\n", order.getId(), order.getDrinkName(), order.getQuantity(), order.getPrice(), order.getTotalPrice());

            boolean is = true;
            do {
                System.out.println("|----------------------------------------------------|");
                System.out.println("|  Nhấn 'b'(back) để trở lại Manager ORDER           |");
                System.out.println("|       's'(show total) nếu muốn xem tổng doanh thu  |");
                System.out.println("|       'e'(exit) để thoát chương trình              |");
                System.out.println("|----------------------------------------------------|");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        optionMenuOrder();
                        break;
                    case "s":
                        totalRevenue();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" ⭆  Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void showOrder() {
        List<Order> orders = orderService.getOrders();
        System.out.printf("|%-15s %-20s %-10s %-20s %-20s|\n", "   ID", "Tên món", "Số lượng", "Giá món", "Tổng tiền");
        for (Order order : orders) {
            System.out.printf("|%-15d %-20s %-10d %-20f %-20f|\n", order.getId(), order.getDrinkName(), order.getQuantity(), order.getPrice(), order.getTotalPrice());
        }
    }

    public void showAllOrder() {
        try {
            showOrder();
            boolean is = true;
            do {
                System.out.println("|--------------------------------------------|");
                System.out.println("|  Nhấn 'b'(back) để trở lại Manager ORDER   |");
                System.out.println("|       'e'(exit) để thoát chương trình      |");
                System.out.println("|--------------------------------------------|");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        optionMenuOrder();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" ⭆ Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void remove() {
        showOrder();
        System.out.println("Nhập ID đơn hàng bạn cần xóa");
        System.out.print("\t➺ ");
        double id = Double.parseDouble(scanner.nextLine());
        Order order = orderService.getOrderById(id);
        if (order == null)
            System.out.println(" ⭆ Món này không tồn tại!! ");
        else {
            orderService.remove(order);
            System.out.println(" ⭆ Đã xóa thành công!! ");
        }
        optionMenuOrder();
    }

    public void totalRevenue(){
        showOrder();
        List<Order> orders = orderService.getOrders();
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }
        System.out.println("Tổng doanh thu là: " + total);
    }

}
