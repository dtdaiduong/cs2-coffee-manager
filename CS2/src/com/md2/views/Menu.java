package com.md2.views;

import com.md2.function.UserService;
import com.md2.mainItems.User;
import com.md2.mainItems.UserType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static final UserService userService = new UserService();
    private static final Scanner scanner =  new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("|---------- CAFE MANAGER -----------|");
        System.out.println("|   1. Quản lý người dùng (Admin)   |");
        System.out.println("|   2. Quản lý Thức uống            |");
        System.out.println("|   3. Quản lý nhân viên            |");
        System.out.println("|   4. Quản lý đơn hàng             |");
        System.out.println("|   5. Thoát chương trình           |");
        System.out.println("|-----------------------------------|");
    }

    static UserView userView = new UserView();
    static DrinkView drinkView = new DrinkView();
    static OrderView orderView = new OrderView();
    static StaffView staffView = new StaffView();

    public static void select(User user) {
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Lựa chọn chức năng muốn thực hiện: ");
                System.out.print("\t➺ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        if(user.getUserType()==UserType.ADMIN){
                            userView.optionMenuUser();
                        }
                        else {
                            System.out.println("Chức năng chỉ dành cho Admin");
                        }
                        break;
                    case 2:
                        drinkView.optionMenuDrink();
                        break;
                    case 3:
                        staffView.optionMenuStaff();
                        break;
                    case 4:
                        orderView.optionMenuOrder();
                        break;
                    case 5:
                        exit();
                        break;
                    default:
                        System.out.println("Vui lòng chọn số theo menu");
                        select(user);
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại số theo menu");
            } catch (Exception e) {
                System.out.println("Không nhập chữ nhé!! ❌" + e.getStackTrace());
            }
        } while (true);
    }

    public static void exit() {
        System.out.println("bye ạ!");
        System.exit(5);
    }
    public static void loginAdmin() {
        System.out.println("************** ĐĂNG NHẬP HỆ THỐNG ***************");
        System.out.println("Username");
        System.out.print("\t➺ ");
        String username = scanner.nextLine();
        System.out.println("Mật khẩu");
        System.out.print("\t➺ ");
        String password = scanner.nextLine();
        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("Nhập sai tên đăng nhập hoặc sai mật khẩu");

        } else {
            System.out.println("Bạn đã đăng nhập thành công");
        }
        select(user);
    }


}
