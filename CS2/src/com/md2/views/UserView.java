package com.md2.views;

import com.md2.function.UserService;
import com.md2.mainItems.User;
import com.md2.mainItems.UserType;
import com.md2.ulits.ValidateUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = new UserService();
    }

    public void menuUser() {
        System.out.println("|----------- User Manager ------------|");
        System.out.println("|   1. Thêm người dùng mới            |");
        System.out.println("|   2. Hiển thị danh sách người dùng  |");
        System.out.println("|   3. Sửa thông tin người dùng       |");
        System.out.println("|   4. Xóa Người dùng                 |");
        System.out.println("|   5. Quay lại Menu chính            |");
        System.out.println("|-------------------------------------|");
    }

    public void optionMenuUser() {
        do {
            menuUser();
            try {
                System.out.println("Chọn chức năng: ");
                System.out.print("\t➺ ");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        showUsers();
                    case 3:
                        updateUser();
                        break;
                    case 4:
                        remove();
                        break;
                    case 5:

                        return;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        menuUser();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }


    public void select() {
        System.out.println("|------------------------------------------|");
        System.out.println("|   Nhấn 't'(try) để đăng nhập lại         |");
        System.out.println("|        'e'(exit) để thoát chương trình   |");
        System.out.println("|------------------------------------------|");
        System.out.print("\t➺ ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "t":
                Menu.loginAdmin();
                break;
            case "e":
                Menu.exit();
                System.exit(0);
                break;
            default:
                System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại ");
                select();
        }
    }

    public void setUserType(User user) {
        System.out.println("|----Chọn chức năng----|");
        System.out.println("|      1. USER         |");
        System.out.println("|      2. ADMIN        |");
        System.out.println("|----------------------|");
        System.out.print("\t➺ ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                user.setUserType(UserType.USER);
                break;
            case 2:
                user.setUserType(UserType.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng!! Chọn 1 hoặc 2");
                setUserType(user);
        }
    }

    public void addUser() {
        try {
            int id = 0;
            boolean isValid = false;
            do {
                System.out.println("Nhập ID người dùng: ");
                System.out.print("\t➺ ");
                id = Integer.parseInt(scanner.nextLine());
                try {
                    while (userService.checkDuplicateId(id)) {
                        System.out.println("ID " + id + " này đã tồn tại! vui lòng kiểm tra lại!!");
                        System.out.print("\t➺ ");
                        id = Integer.parseInt(scanner.nextLine());
                    }
                    if (id < 1) {
                        System.out.println("Không nên nhập ID số âm");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Nhập ID là số!! Không nhập chữ nhé");
                }
                isValid = true;
            } while (!isValid);
            System.out.println("Nhập tên đăng nhập lưu ý không có khoảng trắng ");
            System.out.print("\t➺ ");
            String username = scanner.nextLine();
            while (!ValidateUtils.isUserName(username)) {
                System.out.println("Nhập tên đăng nhập lưu ý không có khoảng trắng");
                System.out.print("\t➺ ");
                username = scanner.nextLine();
            }
            while (userService.checkDuplicateUserName(username)) {
                System.out.println("Tên tài khoản đã tồn tại. Nhập lại nào!!");
                System.out.print("\t➺ ");
                username = scanner.nextLine();
            }

            System.out.println("Nhập mật khẩu (> 6 kí tự và không có khoảng trắng)");
            System.out.print("\t➺ ");
            String password = scanner.nextLine();
            while (!ValidateUtils.isPasswordValid(password)) {
                System.out.println("Mật khẩu phải trên 6 kí tự và không có khoảng trắng!!");
                System.out.print("\t➺ ");
                password = scanner.nextLine();
            }

            System.out.println("Nhập họ và tên không dấu và lưu ý viết hoa chữ cái đầu!!");
            System.out.print("\t➺ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Nhập lại họ tên không dấu Lưu ý viết hoa chữ cái đầu");
                System.out.print("\t➺ ");
                name = scanner.nextLine();
            }

            System.out.println("Nhập số điện thoại và đủ 10 số và bắt đầu là số 0");
            System.out.print("\t➺ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số điện thoại bao gồm 10 số và bắt đầu là số 0");
                System.out.print("\t➺ ");
                phone = scanner.nextLine();
            }
            while (userService.checkDuplicatePhone(phone)) {
                System.out.println("Số điện thoại này đã tồn tại! vui lòng kiểm tra lại!");
                System.out.print("\t➺ ");
                phone = scanner.nextLine();
            }

            System.out.println("Nhập email: ");
            System.out.print("\t➺ ");
            String email = scanner.nextLine();
            while (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Email của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
                System.out.println("Ví dụ: abcd@gmail.com");
                System.out.print("\t➺ ");
                email = scanner.nextLine();
            }
            while (userService.checkDuplicateEmail(email)) {
                System.out.println("Email" + email + "của bạn đã tồn tại! vui lòng kiểm tra lại");
                System.out.print("\t➺ ");
                email = scanner.nextLine();
            }

            System.out.println("Nhập địa chỉ:");
            System.out.print("\t➺ ");
            String address = scanner.nextLine();

            User user = new User(id, name, phone, email, address, username, password);
            setUserType(user);
            userService.add(user);
            System.out.println("Đã thêm thành công! ");
            System.out.println("Tài Khoản gồm: ");
            System.out.printf("|%-8s %-22s %-15s %-22s %-20s %-10s| \n", "ID", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Chức năng");
            System.out.printf("|%-8d %-22s %-15s %-22s %-20s %-10s|\n", user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getUserType());

            boolean check = true;
            do {
                System.out.println("|----------------------------------------------|");
                System.out.println("|   Nhấn 'm'(more) để thêm tiếp người dùng     |");
                System.out.println("|        'b'(back) để quay lại User Manager    |");
                System.out.println("|----------------------------------------------|");
                System.out.print("\t➺ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "m":
                        addUser();
                        break;
                    case "b":
                        optionMenuUser();
                        break;
                    default:
                        System.out.println("!!Chọn 'm' hoặc 'b' để thực hiện chức năng!!");
                        check = false;
                }
            } while (!check);
        } catch (Exception e) {
            System.out.println("Nhập sai!! Nhập lại nào");
        }
    }

    public void show() {
        System.out.println("|----------------------------------------- DANH SÁCH NGƯỜI DÙNG----------------------------------------|");
        System.out.printf("|%-8s %-22s %-15s %-22s %-20s %-10s| \n", "ID", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Chức năng");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("|%-8d %-22s %-15s %-22s %-20s %-10s|\n", user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getUserType());
        }
        System.out.println("|------------------------------------------------------------------------------------------------------|");
        System.out.println(" ");
    }

    public void updateUser() {

        try {
            show();
            int id;
            while (true) {
                System.out.println("Nhập ID của người dùng bạn muốn sửa");
                System.out.print("\t➺ ");
                Scanner input = new Scanner(System.in);
                try {
                    id = Integer.parseInt(input.nextLine());
                    while (!userService.checkDuplicateId(id)) {
                        System.out.println("ID " + id + " của bạn không tồn tại! vui lòng kiểm tra lại!!");
                        System.out.print("\t➺ ");
                        id = Integer.parseInt(scanner.nextLine());
                    }
                    if (id > 0) break;
                    else System.out.println("Không có ID nào là số âm");
                } catch (Exception e) {
                    System.out.println("Nhập ID là số!! Không nhập chữ nhé!");
                }
            }
            boolean check = true;
            do {
                if (userService.exist(id)) {
                    System.out.println("|------------ SỬA -------------|");
                    System.out.println("|   1. Sửa tên người dùng      |");
                    System.out.println("|   2. Sửa số điện thoại       |");
                    System.out.println("|   3. Đổi Email               |");
                    System.out.println("|   4. Sửa địa chỉ             |");
                    System.out.println("|   5. Quay lại User Manager   |");
                    System.out.println("|------------------------------|");
                    System.out.println("Chọn chức năng");
                    System.out.print("\t➺ ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    User user = new User();
                    user.setId(id);
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên người dùng không dấu và lưu ý viết hoa chữ cái đầu!!");
                            System.out.print("\t➺ ");
                            String fullNameUpdate = scanner.nextLine();
                            while (!ValidateUtils.isNameValid(fullNameUpdate)) {
                                System.out.println("Nhập sai rồi! lưu ý viết hoa chữ cái đầu!!");
                                System.out.printf("\t➺ ");
                                fullNameUpdate = scanner.nextLine();
                            }
                            while (userService.checkDuplicateFullName(fullNameUpdate)) {
                                System.out.println("Họ tên đã tồn tại");
                                fullNameUpdate = scanner.nextLine();
                            }
                            user.setFullName(fullNameUpdate);
                            userService.update(user);
                            System.out.println("Đổi tên thành công rồi!!");
                            break;
                        case 2:
                            System.out.println("Nhập số điện thoại muốn sửa đổi");
                            System.out.print("\t➺ ");
                            String phoneNumberUpdate = scanner.nextLine();
                            while (!ValidateUtils.isPhoneValid(phoneNumberUpdate)) {
                                System.out.println("Số điện thoại bao gồm 10 số và bắt đầu là số 0!! Nhập lại nào!");
                                System.out.print("\t➺ ");
                                phoneNumberUpdate = scanner.nextLine();
                            }
                            while (userService.checkDuplicatePhone(phoneNumberUpdate)) {
                                System.out.println("Số điện thoại này đã tồn tại! Mời bạn nhập lại");
                                System.out.print("\t➺ ");
                                phoneNumberUpdate = scanner.nextLine();
                            }
                            user.setPhoneNumber(phoneNumberUpdate);
                            userService.update(user);
                            System.out.println("Đổi số điện thoại thành công");
                            break;
                        case 3:
                            System.out.println("Nhập Email muốn sửa đổi: ");
                            System.out.print("\t➺ ");
                            String emailUpdate = scanner.nextLine();
                            while (!ValidateUtils.isEmailValid(emailUpdate)) {
                                System.out.println("Email sai rồi, nhớ nhập theo dạng abc@gmail.com(.vn)");
                                System.out.print("\t➺ ");
                                emailUpdate = scanner.nextLine();
                            }
                            while (userService.checkDuplicateEmail(emailUpdate)) {
                                System.out.println("Email này đã tồn tại! Nhập cái mới đi nào!!");
                                System.out.print("\t➺ ");
                                emailUpdate = scanner.nextLine();
                            }
                            user.setEmail(emailUpdate);
                            userService.update(user);
                            System.out.println("Đổi Email thành công!");
                            break;

                        case 4:
                            System.out.println("Nhập địa chỉ muốn đổi");
                            System.out.print("\t➺ ");
                            String address = scanner.nextLine();
                            user.setAddress(address);
                            userService.update(user);
                            System.out.println("Đổi địa chỉ thành công");
                            break;
                        case 5:
                            optionMenuUser();
                            break;
                        default:
                            System.out.println("Vui lòng chọn số theo chức năng bên dưới!!");
                            updateUser();
                    }
                    boolean is = true;
                    do {
                        System.out.println("|-----------------------------------------------|");
                        System.out.println("|   Nhấn 'm'(more) để sửa thông tin khác        |");
                        System.out.println("|        'd'(different) để sửa người dùng khác  |");
                        System.out.println("|        'b'(back) để quay lại User Manager     |");
                        System.out.println("|-----------------------------------------------|");
                        System.out.print("\t➺ ");
                        String choice1 = scanner.nextLine();
                        switch (choice1) {
                            case "m":
                                check = false;
                                break;
                            case "d":
                                updateUser();
                                break;
                            case "b":
                                optionMenuUser();
                                break;
                            default:
                                System.out.println("Nhấn không đúng! vui lòng chọn lại");
                                is = false;
                        }
                    } while (!is);
                } else {
                    System.out.println("Không tìm thấy ID! Vui lòng nhập lại");
                    updateUser();
                }
            } while (!check);
        } catch (Exception e) {
            System.out.println("Nhập ID là số!! Không nhập chữ nhé");
            updateUser();
        }
    }

    public void showUsers() {
        List<User> users = userService.getUsers();
        try {
            show();
            boolean is = true;
            do {
                System.out.println("|-------------------------------------------|");
                System.out.println("|  Nhấn 'b'(back) để quay lại User Manager  |");
                System.out.println("|       'e'(exit) để thoát chương trình     |");
                System.out.println("|-------------------------------------------|");
                System.out.print("\t➺ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        optionMenuUser();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("!!Nhấn 'b' hoặc 'e' để thực hiện chức năng");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Nhập sai rồi!! Nhập lại nào!");
            showUsers();
        }
    }

    public void remove() {
        show();
        System.out.println("Nhập ID người dùng cần xóa");
        System.out.print("\t➺ ");
        int id = Integer.parseInt(scanner.nextLine());
        User user = userService.getUserById(id);
        if (user == null)
            System.out.println(" ⭆ Mgười dùng này không tồn tại!! ");
        else {
            userService.remove(user);
            System.out.println(" ⭆ Đã xóa thành công!! ");
//            if (user.getUserType() == UserType.ADMIN) {
//                System.out.println("Không thể xóa Admin");
//            } else {
//                userService.remove(user);
//                System.out.println(" ⭆ Đã xóa thành công!! ");
//            }
        }
        optionMenuUser();
    }
}
