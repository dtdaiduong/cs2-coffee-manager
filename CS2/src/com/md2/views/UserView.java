package com.md2.views;

import com.md2.function.IUserService;
import com.md2.function.UserService;
import com.md2.mainItems.User;
import com.md2.mainItems.UserStatus;
import com.md2.mainItems.UserType;
import com.md2.ulits.ConvertUtils;
import com.md2.ulits.ValidateUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = new UserService();
    }

    public void menuUser() {
        System.out.println("\t\t\t\t|-------------- User Manager ---------------|");
        System.out.println("\t\t\t\t|   1. Thêm người dùng mới                  |");
        System.out.println("\t\t\t\t|   2. Hiển thị danh sách người dùng        |");
        System.out.println("\t\t\t\t|   3. Sửa thông tin người dùng             |");
        System.out.println("\t\t\t\t|   4. Cấp phép truy cập cho người dùng     |");
        System.out.println("\t\t\t\t|   5. Quay lại Menu chính                  |");
        System.out.println("\t\t\t\t|-------------------------------------------|");
    }

    public void optionMenuUser(User user) {
        int option = 0;
        do {
            menuUser();
            try {
                System.out.println("\t\t\t\tChọn chức năng: ");
                System.out.print("\t\t\t\t \t➺ ");
                option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        showUsers();
                        break;
                    case 3:
                        updateUser();
                        break;
                    case 4:
                        accessPermissions();
                        break;
                    case 5:
                        Menu.select(Menu.userLogin);
                        break;
                    default:
                        System.out.println("\t\t\t\t❌ Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } catch (InputMismatchException io) {
                System.out.println("\t\t\t\t❌ Nhập sai! Vui lòng nhập lại");
            }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
        } while (option != 5);
    }

    public void setUserType(User user) {
        boolean check = true;
        do {
            System.out.println("\t\t\t\t|----Chọn chức năng----|");
            System.out.println("\t\t\t\t|      1. USER         |");
            System.out.println("\t\t\t\t|      2. ADMIN        |");
            System.out.println("\t\t\t\t|----------------------|");
            System.out.print("\t\t\t\t\t➺ ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        user.setUserType(UserType.USER);
                        break;
                    case 2:
                        user.setUserType(UserType.ADMIN);
                        break;
                    default:
                        System.out.println("\t\t\t\t❌ Nhập không đúng!! Chọn 1 hoặc 2");
                        check = false;
                }
            } catch (Exception e) {
                System.out.println("\t\t\t\t❌ Vui lòng nhấn 1 hoặc 2!");
            }
        } while (!check);
    }

    public void setUserStatus(User user) {
        boolean is = true;
        do {
            System.out.println("\t\t\t\t|- - - Bạn có muốn cấp phép sử dụng cho tài khoản này không? - - -|");
            System.out.println("\t\t\t\t|                   'Y'. CÓ         'N'. KHÔNG                    |");
            System.out.println("\t\t\t\t|-----------------------------------------------------------------|");
            System.out.print("\t\t\t\t\t➺ ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "y":
                    user.setActiveStatus(UserStatus.LICENSED);
                    is = true;
                    break;
                case "n":
                    user.setActiveStatus(UserStatus.UNLICENSED);
                    is = true;
                    break;
                default:
                    System.out.println("\t\t\t\t❌ Nhập không đúng!! Chọn 1 hoặc 2");
                    is = false;
            }
        } while (!is);
    }

    public void addUser() {
        System.out.println("\t\t\t\tNhấn phím bất kì để tiếp tục và 0 để hủy thao tác");
        System.out.print("\t\t\t\t\t➺ ");
        String cancel = scanner.nextLine();
        if (cancel.equals("0")) return;

        int id = 0;
        System.out.println("\t\t\t\tNhập ID người dùng: ");
        System.out.print("\t\t\t\t\t➺ ");
        boolean checkID = true;
        do {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                if (userService.checkDuplicateId(id)) {
                    System.out.println("\t\t\t\t❌ ID " + id + " này đã tồn tại! vui lòng kiểm tra lại!!");
                    System.out.print("\t\t\t\t\t➺ ");
                    checkID = false;
                }
                if (id < 1) {
                    System.out.println("\t\t\t\t❌ Không nên để id là số âm, hãy nhập lại!!");
                    System.out.print("\t\t\t\t\t➺ ");
                    checkID = false;
                }
            } catch (Exception e) {
                System.out.println("\t\t\t\t❌ ID là 1 số bất kì");
            }
        } while (!checkID);

        System.out.println("\t\t\t\tNhập tên đăng nhập lưu ý không có khoảng trắng ");
        System.out.print("\t\t\t\t\t➺ ");
//        String username;
////        boolean checkUsername = true;
////        do {
//        username = scanner.nextLine().trim();
//        while (userService.checkDuplicateUserName(username)) {
//            System.out.println("\t\t\t\t❌ Tên tài khoản đã tồn tại. Nhập lại nào!!");
//            System.out.print("\t\t\t\t\t➺ ");
//            username = scanner.nextLine().trim();
//        }
//        while (!ValidateUtils.isUserName(username)) {
//            System.out.println("\t\t\t\tNhập tên đăng nhập lưu ý không có khoảng trắng");
//            System.out.print("\t\t\t\t\t➺ ");
//            username = scanner.nextLine().trim();
////                checkUsername = false;
//        }

//        } while (!checkUsername);

        String username;
        boolean checkUsername = true;
        do {
            username = scanner.nextLine().trim();
            if (!ValidateUtils.isUserName(username)) {
                System.out.println("Nhập tên đăng nhập lưu ý không có khoảng trắng");
                System.out.print("\t➺ ");
                checkUsername = false;
            }
            if (userService.checkDuplicateUserName(username)) {
                System.out.println("Tên tài khoản đã tồn tại. Nhập lại nào!!");
                System.out.print("\t➺ ");
                checkUsername = false;
            }
        } while (!checkUsername);

        System.out.println("\t\t\t\tNhập mật khẩu (> 6 kí tự và không có khoảng trắng)");
        System.out.print("\t\t\t\t\t➺ ");
        String password = scanner.nextLine().trim();
        while (!ValidateUtils.isPasswordValid(password)) {
            System.out.println("\t\t\t\t❌ Mật khẩu phải trên 6 kí tự và không có khoảng trắng!!");
            System.out.print("\t\t\t\t\t➺ ");
            password = scanner.nextLine();
        }

        System.out.println("\t\t\t\tNhập họ và tên lưu ý viết hoa chữ cái đầu!!");
        System.out.print("\t\t\t\t\t➺ ");
        String fullName;
        String nameConvert;
//        boolean checkName = true;
//        do {
        fullName = scanner.nextLine().trim();
        nameConvert = ConvertUtils.removeAccent(fullName);
        while (userService.checkDuplicateFullName(fullName)) {
            System.out.println("\t\t\t\t❌ Họ tên " + fullName + " đã tồn tại, vui lòng nhập lại!!");
            System.out.print("\t\t\t\t\t➺ ");
            fullName = scanner.nextLine().trim();
//                checkName = false;
        }
        while (!ValidateUtils.isNameValid(nameConvert)) {
            System.out.println("\t\t\t\t❌ Nhập sai rồi! lưu ý viết hoa chữ cái đầu!!");
            System.out.print("\t\t\t\t\t➺ ");
            fullName = scanner.nextLine().trim();
            nameConvert = ConvertUtils.removeAccent(fullName);
//                checkName = false;
        }

//        } while (!checkName);

        System.out.println("\t\t\t\tNhập số điện thoại và đủ 10 số và bắt đầu là số 0");
        System.out.print("\t\t\t\t\t➺ ");
        String phone;
//        boolean checkPhone = true;
//        do {
        phone = scanner.nextLine().trim();
        while (userService.checkDuplicatePhone(phone)) {
            System.out.println("\t\t\t\t❌ Số điện thoại này đã tồn tại! vui lòng kiểm tra lại!");
            System.out.print("\t\t\t\t\t➺ ");
            phone = scanner.nextLine().trim().trim();
//                checkPhone = false;
        }
        while (!ValidateUtils.isPhoneValid(phone)) {
            System.out.println("\t\t\t\t❌ Số điện thoại bao gồm 10 số và bắt đầu là số 0");
            System.out.print("\t\t\t\t\t➺ ");
            phone = scanner.nextLine().trim();
//                checkPhone = false;

        }

//        } while (!checkPhone);


        System.out.println("\t\t\t\tNhập email: ");
        System.out.print("\t\t\t\t\t➺ ");
        String email;
//        boolean checkEmail = true;
//        do {
        email = scanner.nextLine().trim();
        while (userService.checkDuplicateEmail(email)) {
            System.out.println("\t\t\t\t❌ Email " + email + " của bạn đã tồn tại! vui lòng kiểm tra lại");
            System.out.print("\t\t\t\t\t➺ ");
            email = scanner.nextLine().trim();

//                checkEmail = false;
        }

        while (!ValidateUtils.isEmailValid(email)) {
            System.out.println("\t\t\t\t❌ Email của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
            System.out.println("\t\t\t\tVí dụ: abcd@gmail.com");
            System.out.print("\t\t\t\t\t➺ ");
            email = scanner.nextLine().trim();
//                checkEmail = false;
        }

//        } while (!checkEmail);


        System.out.println("\t\t\t\tNhập địa chỉ:");
        System.out.print("\t\t\t\t\t➺ ");
        String address = scanner.nextLine().trim();
        User user = new User(id, fullName, phone, email, address, username, password);

        setUserType(user);
        setUserStatus(user);
        userService.add(user);

        System.out.println("\t\t\t\tĐã thêm thành công! ");
        System.out.println("\t\t\t\tTài Khoản gồm: ");
        System.out.printf(" |%-8s %-22s %-15s %-30s %-20s %-10s %-12s| \n", "ID", "Họ và Tên", "Số điện thoại", "       Email", "    Địa chỉ", "Chức năng", "Trạng thái");
        System.out.printf(" |%-8d %-22s %-15s %-30s %-20s %-10s %-12s|\n", user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getUserType(), user.getActiveStatus());
        String choice;
        boolean check = true;
        do {
            System.out.println("\t\t\t\t|----------------------------------------------|");
            System.out.println("\t\t\t\t|   Nhấn 'm'(more) để thêm tiếp người dùng     |");
            System.out.println("\t\t\t\t|        'b'(back) để quay lại User Manager    |");
            System.out.println("\t\t\t\t|----------------------------------------------|");
            System.out.print("\t\t\t\t\t➺ ");
            choice = scanner.nextLine();
            switch (choice) {
                case "m":
                    addUser();
                    break;
                case "b":
                    optionMenuUser(Menu.userLogin);
                    break;
                default:
                    System.out.println("\t\t\t\t!!Chọn 'm' hoặc 'b' để thực hiện chức năng!!");
                    break;
            }
        } while (!choice.equals("m") && !choice.equals("b"));
    }


    public void show() {
        System.out.println("\t\t\t\t|-------------------------------------------------- DANH SÁCH NGƯỜI DÙNG -------------------------------------------------------------|");
        System.out.printf(" |%-10s %-22s %-15s %-30s %-30s %-10s %-10s| \n", "ID", "Họ và Tên", "Số điện thoại", "    Email", "    Địa chỉ", "Chức năng", "Trạng thái");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf(" |%-10d %-22s %-15s %-30s %-30s %-10s %-10s|\n", user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getUserType(), user.getActiveStatus());
        }
        System.out.println("\t\t\t\t|-------------------------------------------------------------------------------------------------------------------------------------|");
    }

    public void accessPermissions() {
        show();
        System.out.println("\t\t\t\tNhấn phím bất kì để tiếp tục và 0 để hủy thao tác");
        String cancel = scanner.nextLine();
        if (cancel.equals("0")) return;

        int id;
        while (true) {
            System.out.println("\t\t\t\tNhập ID của người dùng bạn muốn sửa trạng thái hoạt động");
            System.out.print("\t\t\t\t\t➺ ");
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                while (!userService.checkDuplicateId(id)) {
                    System.out.println("\t\t\t\t❌ ID " + id + " của bạn không tồn tại! vui lòng kiểm tra lại!!");
                    System.out.print("\t\t\t\t\t➺ ");
                    id = Integer.parseInt(scanner.nextLine());
                }
                if (id > 0) break;
            } catch (Exception e) {
                System.out.println("\t\t\t\t❌ Nhập ID là số!! Không nhập chữ nhé!");
            }
        }

        User user = userService.getUserById(id);
        System.out.println("\t\t\t\t|- - - - - - Bạn có muốn cấp phép sử dụng cho tài khoản này không? - - - - - -|");
        System.out.println("\t\t\t\t|                    'Y' CÓ                'N' KHÔNG                            |");
        System.out.println("\t\t\t\t|-----------------------------------------------------------------------------|");
        System.out.println("\t\t\t\tChọn chức năng: ");
        System.out.print("\t\t\t\t\t➺ ");
        boolean is = true;
        do {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "y":
                    user.setActiveStatus(UserStatus.LICENSED);
                    userService.update();
                    is = true;
                    break;
                case "n":
                    user.setActiveStatus(UserStatus.UNLICENSED);
                    userService.update();
                    is = true;
                    break;
                default:
                    System.out.println("\t\t\t\t❌ Nhập không đúng!! Chọn 'Y' hoặc 'N'");
                    System.out.print("\t\t\t\t\t➺ ");
                    is = false;
                    break;
            }
        } while (!is);

        boolean check = true;
        do {
            System.out.println("\t\t\t\t|---------------------------------------------|");
            System.out.println("\t\t\t\t|   Nhấn 'b'(back) để quay lại User Manager   |");
            System.out.println("\t\t\t\t|        'e'(exit) để thoát chương trình      |");
            System.out.println("\t\t\t\t|---------------------------------------------|");
            System.out.print("\t\t\t\t\t➺ ");
            String choice1 = scanner.nextLine().trim();
            switch (choice1) {
                case "e":
                    Menu.exit();
                    break;
                case "b":
                    optionMenuUser(Menu.userLogin);
                    break;
                default:
                    System.out.println("\t\t\t\t❌ Nhấn không đúng! vui lòng chọn lại");
                    check = false;
            }
        } while (!check);
    }

    public void updateUser() {
        show();
        System.out.println("\t\t\t\tNhấn phím bất kì để tiếp tục và 0 để hủy thao tác");
        System.out.print("\t\t\t\t\t➺ ");
        String cancel = scanner.nextLine();
        if (cancel.equals("0")) return;

        int id;
        while (true) {
            System.out.println("\t\t\t\tNhập ID của người dùng bạn muốn sửa");
            System.out.print("\t\t\t\t\t➺ ");
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                while (!userService.checkDuplicateId(id)) {
                    System.out.println("\t\t\t\t❌ ID " + id + " của bạn không tồn tại! vui lòng kiểm tra lại!!");
                    System.out.print("\t\t\t\t\t➺ ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                }
                if (id > 0) break;
            } catch (Exception e) {
                System.out.println("\t\t\t\t❌ Nhập ID là số!! Không nhập chữ nhé!");
            }
        }
        User user = userService.getUserById(id);
        boolean check = true;
        do {
            if (userService.exist(id)) {
                System.out.println("\t\t\t\t|------------ SỬA -------------|");
                System.out.println("\t\t\t\t|   1. Sửa tên người dùng      |");
                System.out.println("\t\t\t\t|   2. Sửa số điện thoại       |");
                System.out.println("\t\t\t\t|   3. Đổi Email               |");
                System.out.println("\t\t\t\t|   4. Sửa địa chỉ             |");
                System.out.println("\t\t\t\t|   5. Quay lại User Manager   |");
                System.out.println("\t\t\t\t|------------------------------|");
                System.out.println("\t\t\t\tChọn chức năng: ");
                System.out.print("\t\t\t\t\t➺ ");
                try {
                    boolean is = true;
                    do {
                        int choice = Integer.parseInt(scanner.nextLine().trim());
                        user.setId(id);
                        switch (choice) {
                            case 1:
                                System.out.println("\t\t\t\tNhập tên người và lưu ý viết hoa chữ cái đầu!!");
                                System.out.print("\t\t\t\t\t➺ ");
                                String fullNameUpdate;
                                String namecheck;
                                boolean checkUpdate = true;
                                do {
                                    fullNameUpdate = scanner.nextLine();
                                    namecheck = ConvertUtils.removeAccent(fullNameUpdate);
                                    if (!ValidateUtils.isNameValid(namecheck.trim())) {
                                        System.out.println("\t\t\t\t❌ Nhập sai rồi! lưu ý viết hoa chữ cái đầu!!");
                                        System.out.print("\t\t\t\t\t➺ ");
                                        checkUpdate = false;
                                    } else {
                                        if (userService.checkDuplicateFullName(fullNameUpdate.trim())) {
                                            System.out.println("\t\t\t\t❌ Họ tên đã tồn tại");
                                            System.out.print("\t\t\t\t\t➺ ");
                                            checkUpdate = false;
                                        }
                                    }
                                } while (!checkUpdate);
                                user.setFullName(fullNameUpdate);
                                userService.update(user);
                                System.out.println("\t\t\t\tĐổi tên thành công rồi!!");
                                break;

                            case 2:
                                System.out.println("\t\t\t\tNhập số điện thoại muốn sửa đổi");
                                System.out.print("\t\t\t\t\t➺ ");
                                String phoneNumberUpdate;
                                boolean checkPhone = true;
                                do {
                                    phoneNumberUpdate = scanner.nextLine().trim();
                                    if (!ValidateUtils.isPhoneValid(phoneNumberUpdate)) {
                                        System.out.println("\t\t\t\t❌ Số điện thoại bao gồm 10 số và bắt đầu là số 0!! Nhập lại nào!");
                                        System.out.print("\t\t\t\t\t➺ ");
                                        checkPhone = false;
                                    } else {
                                        if (userService.checkDuplicatePhone(phoneNumberUpdate)) {
                                            System.out.println("\t\t\t\t❌ Số điện thoại này đã tồn tại! Mời bạn nhập lại");
                                            System.out.print("\t\t\t\t\t➺ ");
                                            checkPhone = false;
                                        }
                                    }

                                } while (!checkPhone);

                                user.setPhoneNumber(phoneNumberUpdate);
                                userService.update(user);
                                System.out.println("\t\t\t\tĐổi số điện thoại thành công");
                                break;

                            case 3:
                                System.out.println("\t\t\t\tNhập Email muốn sửa đổi: ");
                                System.out.print("\t\t\t\t\t➺ ");
                                String emailUpdate;
                                boolean checkEmail = true;
                                do {
                                    emailUpdate = scanner.nextLine().trim();
                                    if (!ValidateUtils.isEmailValid(emailUpdate)) {
                                        System.out.println("\t\t\t\t❌ Email sai rồi, nhớ nhập theo dạng abc@gmail.com(.vn)");
                                        System.out.print("\t\t\t\t\t➺ ");
                                        checkEmail = false;
                                    } else {
                                        if (userService.checkDuplicateEmail(emailUpdate)) {
                                            System.out.println("\t\t\t\t❌ Email này đã tồn tại! Nhập cái mới đi nào!!");
                                            System.out.print("\t\t\t\t\t➺ ");
                                            checkEmail = false;
                                        }
                                    }
                                } while (!checkEmail);

                                user.setEmail(emailUpdate);
                                userService.update(user);
                                System.out.println("\t\t\t\tĐổi Email thành công!");
                                break;

                            case 4:
                                System.out.println("\t\t\t\tNhập địa chỉ muốn đổi");
                                System.out.print("\t\t\t\t\t➺ ");
                                String address = scanner.nextLine().trim();
                                user.setAddress(address);
                                userService.update(user);
                                System.out.println("\t\t\t\tĐổi địa chỉ thành công");
                                check = false;
                                break;
                            case 5:
                                return;
                            default:
                                System.out.println("\t\t\t\t❌ Vui lòng chọn số theo chức năng bên trên!!");
                                System.out.print("\t\t\t\t\t➺ ");
                                is = false;
                        }
                    } while (!is);
                } catch (Exception e) {
                    System.out.println("\t\t\t\t❌ Vui lòng nhập số theo chức năng!!");
                    optionMenuUser(Menu.userLogin);
                }

                boolean is = true;
                do {
                    System.out.println("\t\t\t\t|-----------------------------------------------|");
                    System.out.println("\t\t\t\t|   Nhấn 'm'(more) để sửa thông tin khác        |");
                    System.out.println("\t\t\t\t|        'd'(different) để sửa người dùng khác  |");
                    System.out.println("\t\t\t\t|        'b'(back) để quay lại User Manager     |");
                    System.out.println("\t\t\t\t|-----------------------------------------------|");
                    System.out.print("\t\t\t\t\t➺ ");
                    String choice1 = scanner.nextLine().trim();
                    switch (choice1) {
                        case "m":
                            check = false;
                            break;
                        case "d":
                            updateUser();
                            break;
                        case "b":
                            return;
                        default:
                            System.out.println("\t\t\t\t❌ Nhấn không đúng! vui lòng chọn lại");
                            is = false;
                    }
                } while (!is);
            } else {
                System.out.println("\t\t\t\t❌ Không tìm thấy ID! Vui lòng nhập lại");
                updateUser();
            }
        } while (!check);
    }

    public void showUsers() {
        List<User> users = userService.getUsers();
        try {
            show();
            boolean is = true;
            do {
                System.out.println("\t\t\t\t |-------------------------------------------|");
                System.out.println("\t\t\t\t |  Nhấn 'b'(back) để quay lại User Manager  |");
                System.out.println("\t\t\t\t |       'e'(exit) để thoát chương trình     |");
                System.out.println("\t\t\t\t |-------------------------------------------|");
                System.out.print("\t\t\t\t\t➺ ");
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "b":
                        optionMenuUser(Menu.userLogin);
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\t\t\t\t❌ Nhấn 'b' hoặc 'e' để thực hiện chức năng");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("\t\t\t\t❌ Nhập sai rồi!! Nhập lại nào!");
            showUsers();
        }
    }

    public void remove() {
        show();
        System.out.println("\t\t\t\t Nhấn phím bất kì để tiếp tục và 0 để hủy thao tác");
        String cancel = scanner.nextLine();
        if (cancel.equals("0")) return;

        System.out.println("\t\t\t\t Nhập ID người dùng cần xóa");
        System.out.print("\t\t\t\t\t➺ ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        User user = userService.getUserById(id);
        if (user == null)
            System.out.println("\t\t\t\t❌ Mgười dùng này không tồn tại!! ");
        else {
            if (user.getUserType() == UserType.ADMIN)
                System.out.println("\t\t\t\t❌ Không thể xóa tài khoản Admin!!");
            else {
                userService.remove(user);
                System.out.println("\t\t\t\t❌ Đã xóa thành công!! ");
            }
        }
        optionMenuUser(Menu.userLogin);
    }
}
