package com.md2.views;

import com.md2.function.DrinkService;
import com.md2.mainItems.Drink;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DrinkView {
    public static ArrayList<Drink> drinks = new ArrayList<>();
    private final DrinkService drinkService;
    private final Scanner scanner;

    public DrinkView() {
        drinkService = new DrinkService();
        scanner = new Scanner(System.in);
    }

    public static void menuDrinks() {
        System.out.println();
        System.out.println("|--------- Menu Manager Cafe ---------|");
        System.out.println("|   1. Hiển thị danh sách thức uống   |");
        System.out.println("|   2. Thêm món mới                   |");
        System.out.println("|   3. Sửa món                        |");
        System.out.println("|   4. Xóa món                        |");
        System.out.println("|   5. Quay lại Menu chính            |");
        System.out.println("|-------------------------------------|");
    }

    public void optionMenuDrink() {
        do {
            menuDrinks();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.print("⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        showDrink();
                        break;
                    case 2:
                        addDrink();
                        break;
                    case 3:
                        updateDrink();
                        break;
                    case 4:
                        remove();
                        break;
                    case 5:
                        return;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        menuDrinks();
                }
            } catch (InputMismatchException io) {
                System.out.println("❌ Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("❌ Nhập số nhé mọi người!!");
            }
        } while (true);
    }

    public void addDrink() {
        drinkService.getDrinks();
        int id;
        while (true) {
            System.out.println("Nhập ID thức uống: ");
            System.out.print("\t➺ ");
            Scanner input = new Scanner(System.in);
            try {
                id = Integer.parseInt(input.nextLine());
                while (drinkService.checkDuplicateId(id)) {
                    System.out.println("ID" + id + "của bạn đã tồn tại! vui lòng kiểm tra lại!!");
                    System.out.print("\t➺ ");
                    id = Integer.parseInt(scanner.nextLine());
                }
                if (id > 0) break;
                else System.out.println("❌ ID không nên nhập số âm");
            } catch (Exception e) {
                System.out.println("Nhập ID là số!! ❌ Không nhập chữ nhé");
            }
        }
        while (drinkService.checkDuplicateId(id)) {
            System.out.println("ID món này đã tồn tại!! Nhập lại nào!!");
            System.out.printf("\t➺ ");
            id = Integer.parseInt(scanner.nextLine());
        }

        String drinkName;
        do {
            System.out.println("Nhập tên món: ");
            System.out.print("\t➺ ");
            drinkName = scanner.nextLine();
            if (drinkService.checkDuplicateName(drinkName)) {
                System.out.println("Trùng tên món! Nhập lại nào: ");
                drinkName = null;
            }
        } while (drinkName == null);

        double price;
        while (true) {
            System.out.println("Nhập giá sản phẩm: ");
            System.out.print("\t➺ ");
            Scanner input = new Scanner(System.in);
            try {
                price = Double.parseDouble(input.nextLine());
                if (price > 0) {
                    break;
                }
                System.out.println("\t❌ Giá phải lớn hơn 0");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t❌ Giá phải là số");
                System.out.println();
            }
        }
        Drink drink = new Drink(id, drinkName, price);
        drinkService.add(drink);
        System.out.printf("|%-15s %-20s %-20s |\n", "ID", "Tên món", "Giá món");
        System.out.printf("|%-15d %-20s %-20f |\n", drink.getDrinkId(), drink.getDrinkName(), drink.getPrice());
        System.out.println("Đã thêm vào thành công!!");

        boolean is = true;
        do {
            System.out.println("|----------------------------------------------|");
            System.out.println("|   Nhấn 'm'(enter more) để tiếp tục thêm món  |");
            System.out.println("|        'b'(back) để quay lại Manager Cafe    |");
            System.out.println("|----------------------------------------------|");
            System.out.print("\t➺ ");
            String option = scanner.nextLine();
            switch (option) {
                case "m":
                    addDrink();
                    return;
                case "b":
                    optionMenuDrink();
                    break;
                default:
                    System.out.println("vui lòng chọn lại các kí tự như trên");
                    is = false;
            }
        } while (!is);
    }

    public void show() {
        List<Drink> drinks = drinkService.getDrinks();
        System.out.println("|----------------- Menu Thức uống ----------------------|");
        System.out.printf("|%-13s %-25s %-15s|\n", "Id", "Tên món", "Giá tiền");
        for (Drink drink : drinks) {
            System.out.printf("|%-13d %-25s %-15f|\n", drink.getDrinkId(), drink.getDrinkName(), drink.getPrice());
        }
        System.out.println("|-------------------------------------------------------|");
    }

    public void showDrink() {
        show();
        boolean is = true;
        do {
            System.out.println("|-------------------------------------|");
            System.out.println("|  Nhấn 'b' để trở lại Manager Cafe   |");
            System.out.println("|       'e' để thoát chương trình     |");
            System.out.println("|-------------------------------------|");
            System.out.print("\t➺ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "b":
                    optionMenuDrink();
                    break;
                case "e":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("vui lòng chọn lại các kí tự như trên");
                    is = false;
            }
        } while (!is);
    }

    public void updateDrink() {
        try {
            show();
            drinkService.getDrinks();
            int id;
            while (true) {
                System.out.println("Nhập ID của món bạn cần sửa: ");
                System.out.print("\t➺ ");
                Scanner input = new Scanner(System.in);
                try {
                    id = Integer.parseInt(input.nextLine());

                    while (!drinkService.checkDuplicateId(id)) {
                        System.out.println("ID " + id + " này không tồn tại! vui lòng kiểm tra lại!!");
                        System.out.print("\t➺ ");
                        id = Integer.parseInt(scanner.nextLine());
                    }
                    if (id > 0) break;
                } catch (Exception e) {
                    System.out.println("Nhập ID là số!! ❌ Không nhập chữ nhé");
                }
            }
            Drink drink = drinkService.getDrinkById(id);
            boolean check = true;
            do {
                if (drinkService.exist(id)) {
                    System.out.println("|------------ SỬA ------------|");
                    System.out.println("|   1. Sửa tên Thức uống      |");
                    System.out.println("|   2. Sửa giá Thức uống      |");
                    System.out.println("|   3. Quay lại User Manager  |");
                    System.out.println("|-----------------------------|");
                    System.out.println("Chọn chức năng");
                    System.out.print("\t➺ ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên cần sửa: ");
                            System.out.print("\t➺ ");
                            String nameEdit = scanner.nextLine();
                            while (drinkService.checkDuplicateName(nameEdit)) {
                                System.out.println("Tên thức uống bị trùng, vui lòng chọn tên khác!");
                                System.out.print("\t➺ ");
                                nameEdit = scanner.nextLine();
                            }
                            drink.setDrinkName(nameEdit);
                            drinkService.update();
                            System.out.println("Bạn đã sửa tên thức uống thành: " + drink.getDrinkName());
                            break;
                        case 2:
                            long price;
                            while (true) {
                                System.out.println("Nhập giá cần sửa: ");
                                System.out.print("\t➺ ");
                                Scanner input = new Scanner(System.in);
                                try {
                                    price = Long.parseLong(input.nextLine());
                                    if (price > 0)
                                        break;
                                    else {
                                        System.out.println("\t❌ Giá phải lớn hơn 0");
                                        System.out.println();
                                    }
                                } catch (Exception e) {
                                    System.out.println("\t❌ Giá phải là số");
                                    System.out.println();
                                }
                            }
                            drink.setPrice(price);
                            drinkService.update();
                            System.out.println("Bạn đã sửa giá thức uống thành: " + drink.getPrice());
                            break;
                        case 3:
                            optionMenuDrink();
                            break;
                        default:
                            System.out.println("Vui lòng chọn số theo chức năng bên dưới!!");
                            updateDrink();
                    }

                    boolean is = true;
                    do {
                        System.out.println("|---------------------------------------------------|");
                        System.out.println("|   Nhấn 'm'(more) để sửa thông tin khác            |");
                        System.out.println("|        'y' nếu muốn xem tất cả thông tin vừa sửa  |");
                        System.out.println("|        'd'(different) để sửa món khác             |");
                        System.out.println("|        'b'(back) để quay lại User Manager         |");
                        System.out.println("|---------------------------------------------------|");
                        System.out.print("\t➺ ");
                        String choice1 = scanner.nextLine();
                        switch (choice1) {
                            case "m":
                                check = false;
                                break;
                            case "y":
                                System.out.printf("|%-15s %-20s %-20s |\n", "ID", "Tên món", "Giá món");
                                System.out.printf("|%-15d %-20s %-20f |\n", drink.getDrinkId(), drink.getDrinkName(), drink.getPrice());
                                break;
                            case "d":
                                drinkService.update();
                                updateDrink();
                                break;
                            case "b":
                                optionMenuDrink();
                                break;
                            default:
                                System.out.println("❌ Vui lòng chọn lại các kí tự như trên");
                                is = false;
                        }
                    } while (!is);
                }
            } while (!check);
        } catch (Exception e) {
            System.out.println("Nhập ID là số!! ❌ Không nhập chữ nhé");
            updateDrink();
        }
    }

    public void remove() {
        show();
        drinkService.getDrinks();
        System.out.println("nhập ID bạn cần xóa: ");
        System.out.print("\t➺ ");
        double id = Double.parseDouble(scanner.nextLine());
        Drink drink = drinkService.getDrinkById(id);
        if (drink == null) {
            System.out.println("Món này không tồn tại!! ");
        } else {
            drinkService.remove(drink);
            System.out.println("Đã xóa thành công!!");
        }
        optionMenuDrink();
    }
}
