package com.md2.views;

import com.md2.function.DrinkService;
import com.md2.function.IDrinkService;
import com.md2.function.IOrderService;
import com.md2.function.OrderService;
import com.md2.mainItems.Drink;
import com.md2.mainItems.Order;
import com.md2.mainItems.User;
import com.md2.mainItems.UserType;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderView {
    DecimalFormat format = new DecimalFormat("###,###.###" + " đ");
    public static ArrayList<Order> orders = new ArrayList<>();
    private final IOrderService orderService;
    private final IDrinkService drinkService;
    private final DrinkView drinkView;
    private final Scanner scanner;

    public OrderView() {
        drinkService = new DrinkService();
        orderService = new OrderService();
        drinkView = new DrinkView();
        scanner = new Scanner(System.in);

    }

    public static void menuOrderAD() {
        System.out.println("|------------ Manager ORDER ------------|");
        System.out.println("|   1. Tạo đơn hàng mới                 |");
        System.out.println("|   2. Hiển thị danh sách đơn hàng      |");
        System.out.println("|   3. Xóa đơn hàng                     |");
        System.out.println("|   4. Xem tổng doanh thu               |");
        System.out.println("|   5. Quay lại Menu chính              |");
        System.out.println("|---------------------------------------|");
    }

    public static void menuOrderUS() {
        System.out.println("|------------ Manager ORDER ------------|");
        System.out.println("|   1. Tạo đơn hàng mới                 |");
        System.out.println("|   2. Hiển thị danh sách đơn hàng      |");
        System.out.println("|   3. Quay lại Menu chính              |");
        System.out.println("|---------------------------------------|");
    }

    public void optionMenuOrder(User user) {
        if (user.getUserType() == UserType.ADMIN) {
            do {
                menuOrderAD();
                try {
                    System.out.println("\nChọn chức năng: ");
                    System.out.print("⭆ ");
                    int number = Integer.parseInt(scanner.nextLine().trim());
                    switch (number) {
                        case 1:
                            addOrder();
                            break;
                        case 2:
                            showAllOrder();
                            break;
                        case 3:
                            remove();
                            break;
                        case 4:
                            totalRevenue();
                            break;
                        case 5:
                            Menu.select(Menu.userLogin);
                            break;
                        default:
                            System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                            optionMenuOrder(user);
                            break;
                    }
                } catch (InputMismatchException io) {
                    System.out.println("Nhập sai! Vui lòng nhập lại");
                } catch (Exception e) {
                    System.out.println("Nhập không hợp lệ! Phải nhập vào một số \n");
                }
            } while (true);
        } else {
            do {
                menuOrderUS();
                try {
                    System.out.println("\nChọn chức năng: ");
                    System.out.print("⭆ ");
                    int number = Integer.parseInt(scanner.nextLine().trim());
                    switch (number) {
                        case 1:
                            addOrder();
                            break;
                        case 2:
                            showAllOrder();
                            break;
                        case 3:
                            Menu.select(Menu.userLogin);
                            break;
                        default:
                            System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                            optionMenuOrder(user);
                            break;
                    }
                } catch (InputMismatchException io) {
                    System.out.println("Nhập sai! Vui lòng nhập lại");
                } catch (Exception e) {
                    System.out.println("Nhập hợp lệ! Phải nhập vào một số \n");
                }
            } while (true);
        }
    }

    public void addOrder() {
        drinkView.show();
        System.out.println("Nhấn phím bất kì để tiếp tục và 0 để hủy thao tác");
        String cancel = scanner.nextLine();
        if (cancel.equals("0")) return;
        try {
            long id = System.currentTimeMillis() / 1000;
            System.out.println("Bạn có muốn tìm kiếm thức uống không? ");
            System.out.println("Nhập 1 để tìm kiếm và phím bất kì nếu không muốn ");
            System.out.print("⭆ ");
            String x = scanner.nextLine().trim();
            if (x.equals("1")){
                drinkView.findByName();
            }

            System.out.println("Nhập ID thức uống cần order: ");
            System.out.print("\t➺ ");
            String drinkId = scanner.nextLine().trim();
            while (!drinkService.checkDuplicateId(drinkId)) {
                System.out.println("ID món này không tồn tại!! Nhập lại nào!!");
                System.out.print("\t➺ ");
                drinkId = scanner.nextLine().trim();
            }

            Drink drink = drinkService.getDrinkById(drinkId);
            double price = drink.getPrice();

            int quantity;
            boolean check = true;
            do {
                System.out.println("Nhập số lượng cần đặt:");
                System.out.print("\t➺ ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity < 1) {
                    System.out.println(" ⭆ Số lượng phải lớn hơn 0 !!");
                    check = false;
                } else {
                    if (quantity >= 10) {
                        System.out.println("|----------------------------------------------|");
                        System.out.printf("|%-40s %-5s|\n", "   Bạn có chắc chắn muốn order số lượng ", quantity);
                        System.out.println("|   Nhấn '1'(yes) để xác nhận                  |");
                        System.out.println("|        '2'(don't) để nhập lại                |");
                        System.out.println("|----------------------------------------------|");
                        int choice = Integer.parseInt(scanner.nextLine().trim());
                        switch (choice) {
                            case 1:
                                break;
                            case 2:
                                check = false;
                                break;
                            default:
                                System.out.println(" ⭆ Nhập 'y' hoặc 'd' nhé!! Nhập lại nào!!");
                                break;
                        }
                    } else check = true;
                }
            } while (!check);

            String drinkName = drink.getDrinkName();
            double totalPrice = quantity * price;
            String timeOrder = getTimeNow();
            Order order = new Order(id, drinkName, quantity, price, totalPrice, timeOrder);
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
                String option = scanner.nextLine().trim();
                switch (option) {
                    case "m":
                        addOrder();
                        break;
                    case "b":
                        return;
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

    public static String getTimeNow() {
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(d);
    }

    private void optionMenuOrder() {
    }

    public void showBill(Order order) {
        try {
            System.out.printf("|%-15s %-20s %-10s %-20s %-20s %-30s|\n", "ID", "Tên món", "Số lượng", "Giá món", "Tổng tiền", "Ngày và giờ tạo đơn");
            System.out.printf("|%-15d %-20s %-10d %-20s %-20s %-30s|\n", order.getId(), order.getDrinkName(), order.getQuantity(), format.format(order.getPrice()), format.format(order.getTotalPrice()), order.getTimeOder());

            boolean is = true;
            do {
                System.out.println("|----------------------------------------------------|");
                System.out.println("|  Nhấn 'b'(back) để trở lại Manager ORDER           |");
                System.out.println("|       's'(show total) nếu muốn xem tổng doanh thu  |");
                System.out.println("|       'e'(exit) để thoát chương trình              |");
                System.out.println("|----------------------------------------------------|");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "b":
                        return;
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
        System.out.printf("|%-15s %-20s %-10s %-20s %-20s %-30s|\n", "   ID", "Tên món", "Số lượng", "Giá món", "Tổng tiền","Ngày và giờ tạo đơn");
        for (Order order : orders) {
            System.out.printf("|%-15d %-20s %-10d %-20s %-20s %-30s|\n", order.getId(), order.getDrinkName(), order.getQuantity(), format.format(order.getPrice()), format.format(order.getTotalPrice()), order.getTimeOder());
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
                System.out.print(" ⭆ ");

                String choice = scanner.nextLine().trim();
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
        System.out.println("Nhấn phím bất kì để tiếp tục và 0 để hủy thao tác");
        String cancel = scanner.nextLine();
        if (cancel.equals("0")) return;
        System.out.println("Nhập ID đơn hàng bạn cần xóa");
        System.out.print("\t➺ ");
        double id = Double.parseDouble(scanner.nextLine().trim());
        Order order = orderService.getOrderById(id);
        if (order == null)
            System.out.println(" ⭆ Món này không tồn tại!! ");
        else {
            orderService.remove(order);
            System.out.println(" ⭆ Đã xóa thành công!! ");
        }
        optionMenuOrder();
    }

    public void totalRevenue() {
        showOrder();
        List<Order> orders = orderService.getOrders();
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }
        System.out.println("Tổng doanh thu là: " + format.format(total));
    }

}
