package com.md2.views;

import com.md2.function.UserService;
import com.md2.mainItems.User;
import com.md2.mainItems.UserStatus;
import com.md2.mainItems.UserType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static final UserService userService = new UserService();
    private static final Scanner scanner = new Scanner(System.in);
    public static User userLogin;

    public static void menuAdmin() {
        System.out.println("|---------- CAFE MANAGER -----------|");
        System.out.println("|   1. Quản lý người dùng           |");
        System.out.println("|   2. Quản lý Thức uống            |");
        System.out.println("|   3. Quản lý nhân viên            |");
        System.out.println("|   4. Quản lý đơn hàng             |");
        System.out.println("|   5. Thoát chương trình           |");
        System.out.println("|-----------------------------------|");
    }

    public static void menuUser() {
        System.out.println("|---------- CAFE MANAGER -----------|");
        System.out.println("|   1. Quản lý Thức uống            |");
        System.out.println("|   2. Kiểm tra đơn hàng            |");
        System.out.println("|   3. Thoát chương trình           |");
        System.out.println("|-----------------------------------|");
    }

    static UserView userView = new UserView();
    static DrinkView drinkView = new DrinkView();
    static OrderView orderView = new OrderView();
    static StaffView staffView = new StaffView();

    public static void select(User user) {
        int number = 0;
        if (user.getUserType() == UserType.ADMIN) {
            do {
                menuAdmin();
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Lựa chọn chức năng muốn thực hiện: ");
                    System.out.print("\t➺ ");
                     number = Integer.parseInt(scanner.nextLine());
                    switch (number) {
                        case 1:
                            userView.optionMenuUser(user);
                            break;
                        case 2:
                            drinkView.optionMenuDrink();
                            break;
                        case 3:
                            staffView.optionMenuStaff();
                            break;
                        case 4:
                            orderView.optionMenuOrder(user);
                            break;
                        case 5:
                            exit();
                            break;
                        default:
                            System.out.println("Vui lòng chọn số theo menu");
                            break;
                    }
                } catch (InputMismatchException io) {
                    System.out.println("Nhập sai! Vui lòng nhập lại số theo menu");
                } catch (Exception e) {
                    System.out.println("❌ Không nhập chữ nhé!! ");
                }
            } while (number !=5);
        } else {
            do {
                menuUser();
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Lựa chọn chức năng muốn thực hiện: ");
                    System.out.print("\t➺ ");
                    number = Integer.parseInt(scanner.nextLine());
                    switch (number) {
                        case 1:
                            drinkView.optionMenuDrink();
                            break;
                        case 2:
                            orderView.optionMenuOrder(user);
                            break;
                        case 3:
                            exit();
                            break;
                        default:
                            System.out.println("Vui lòng chọn số theo menu");
                            break;
                    }
                } catch (InputMismatchException io) {
                    System.out.println("Nhập sai! Vui lòng nhập lại số theo menu");
                } catch (Exception e) {
                    System.out.println("❌ Không nhập chữ nhé!! ");
                }
            } while (number!=4);
        }
    }

    public static void exit() {
        System.out.println("bye ạ!");
        System.exit(5);
    }

    public static void loginAdmin() {
        System.out.println("* * * * * * * * *  ĐĂNG NHẬP HỆ THỐNG * * * * * * * * *");
        System.out.println("Username");
        System.out.print("\t➺ ");
        String username = scanner.nextLine();
        System.out.println("Mật khẩu");
        System.out.print("\t➺ ");
        String password = scanner.nextLine();

        userLogin = userService.login(username, password);
        if (userLogin != null) {
            if (userLogin.getActiveStatus()== UserStatus.LICENSED){
                System.out.println("Bạn đã đăng nhập thành công");
                select(userLogin);
            }else System.out.println("Tài khoản này chưa được cấp quyền sử dụng");
        } else {
            System.out.println("Bạn đã nhập sai thông tin!! ");
            selectLogin();
        }
    }


    public static void selectLogin() {

        boolean login = true;
        do {
            System.out.println("|------------------------------------------|");
            System.out.println("|   Nhấn 't'(try) để đăng nhập lại         |");
            System.out.println("|        'e'(exit) để thoát chương trình   |");
            System.out.println("|------------------------------------------|");
            System.out.print("\t➺ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "t":
                    loginAdmin();
                    break;
                case "e":
                    exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại ");
                    login = false;
            }
        }while (!login);
    }
}
