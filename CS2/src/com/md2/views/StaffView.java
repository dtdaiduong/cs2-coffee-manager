package com.md2.views;

import com.md2.function.StaffService;
import com.md2.mainItems.Staff;
import com.md2.ulits.ValidateUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StaffView {
    public static List<Staff> staffs = new ArrayList<>();
    private final StaffService staffService;
    private final Scanner scanner;

    public StaffView() {
        staffService = new StaffService();
        scanner = new Scanner(System.in);
    }

    public static void menuStaff() {
        System.out.println("|---------- Staff Manager ------------|");
        System.out.println("|   1. Thêm nhân viên mới             |");
        System.out.println("|   2. Hiển thị danh sách nhân viên   |");
        System.out.println("|   3. Sửa thông tin nhân viên        |");
        System.out.println("|   4. Xóa nhân viên khỏi danh sách   |");
        System.out.println("|   5. Quay lại Menu chính            |");
        System.out.println("|-------------------------------------|");
    }

    public void optionMenuStaff() {
        do {
            menuStaff();
            try {
                System.out.println("Chọn chức năng: ");
                System.out.print("⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        addStaff();
                        break;
                    case 2:
                        showStaffList();
                        break;
                    case 3:
                        updateStaff();
                        break;
                    case 4:
                        remove();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Chọn chức năng không đúng!! Mời chọn lại!");
                        menuStaff();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai rồi!! Nhập lại nào!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Nhập số nhé mọi người!!");
            }
        } while (true);
    }

    public void addStaff() {
        int id;
        while (true) {
            System.out.println("Tạo ID cho nhân viên nào: ");
            System.out.print("\t➺ ");
            Scanner input = new Scanner(System.in);
            try {
                id = Integer.parseInt(input.nextLine());
                if (id > 0) break;
                System.out.println("Nhập ID sai rồi, nhập số dương đi ạ!");
            } catch (Exception e) {
                System.out.println("Nhập ID sai rồi, nhập số đi ạ!");
            }
        }
        String staffName;
        do {
            System.out.println("Nhập tên cho nhân viên nào (Lưu ý không dấu và viết hoa chữ cái đầu): ");
            System.out.print("\t➺ ");
            staffName = scanner.nextLine();
            while (!ValidateUtils.isNameValid(staffName)) {
                System.out.println("Nhập sai rồi, nhớ nhập không dấu và viết hoa chữ cái đầu nhé!");
                System.out.print("\t➺ ");
                staffName = scanner.nextLine();
            }
            if (staffService.checkDuplicateStaffName(staffName)) {
                System.out.println("Trùng tên nhân viên rồi, nhập lại nào!");
                System.out.print("\t➺ ");
                staffName = null;
            }
        } while (staffName == null);

        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại nào (Lưu ý nhập đủ 10 số nhé!)");
            System.out.print("\t➺ ");
            phoneNumber = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phoneNumber)) {
                System.out.println("Nhập sai rồi, nhớ bắt đầu bằng số 0 và đủ 10 số nhé!");
                System.out.print("\t➺ ");
                phoneNumber = scanner.nextLine();
            }
            if (staffService.checkDuplicatePhone(phoneNumber)) {
                System.out.println("Trùng số điện thoại rồi, nhập số khác đi!");
                System.out.print("\t➺ ");
                phoneNumber = null;
            }
        } while (phoneNumber == null);

        String email;
        do {
            System.out.println("Nhập Email nào: ");
            System.out.print("\t➺ ");
            email = scanner.nextLine();
            while (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Nhập sai rồi, Nhập lại theo mẫu abc@gmail.com(.vn)");
                System.out.print("\t➺ ");
                email = scanner.nextLine();
            }
            if (staffService.checkDuplicateEmail(email)) {
                System.out.println("Trùng Email rồi, chọn cái khác đi!");
                System.out.print("\t➺ ");
                email = null;
            }
        } while (email == null);

        System.out.println("Nhập địa chỉ:");
        System.out.print("\t➺ ");
        String address = scanner.nextLine();
        System.out.println("Nhập chức vụ nhân viên nào: ");
        System.out.print("\t➺ ");
        String department = scanner.nextLine();
        Staff staff = new Staff(id, staffName, phoneNumber, email, address, department);
        staffService.add(staff);
        boolean check = true;
        do {
            System.out.println("|----------------------------------------------|");
            System.out.println("|   Nhấn 'm'(more) để thêm tiếp người dùng     |");
            System.out.println("|        'b'(back) để quay lại User Manager    |");
            System.out.println("|----------------------------------------------|");
            String choice = scanner.nextLine();
            switch (choice) {
                case "m":
                    addStaff();
                    break;
                case "b":
                    optionMenuStaff();
                    break;
                default:
                    System.out.println("!!Chọn 'm' hoặc 'b' để thực hiện chức năng!!");
                    check = false;
            }
        } while (!check);
    }

    public void showStaff() {
        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.printf("|%-8s %-15s %-15s %-18s %-13s %-12s|\n", "ID", "Họ và Tên", "Số điện thoại", "Email", "Địa chỉ", "Chức vụ");
        List<Staff> staffs = staffService.getStaffs();
        for (Staff staff : staffs) {
            System.out.printf("|%-8d %-15s %-15s %-18s %-13s %-12s|\n", staff.getStaffId(), staff.getStaffName(), staff.getPhoneNumber(), staff.getEmail(), staff.getAddress(), staff.getDepartment());
        }
        System.out.println("|--------------------------------------------------------------------------------------|");
        System.out.println("   ");
    }

    public void showStaffList() {
        List<Staff> staffs = staffService.getStaffs();
        try {
            showStaff();
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
                        optionMenuStaff();
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
        }
    }

    public void updateStaff() {
        try {
            showStaff();
            int id;
            while (true) {
                System.out.println("Nhập ID của người dùng bạn muốn sửa");
                System.out.print("\t➺ ");
                Scanner input = new Scanner(System.in);
                try {
                    id = Integer.parseInt(input.nextLine());
                    while (!staffService.checkDuplicateId(id)) {
                        System.out.println("ID" + id + "của bạn không tồn tại! vui lòng kiểm tra lại!!");
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
                if (staffService.exist(id)) {
                    System.out.println("|------------ SỬA ------------|");
                    System.out.println("|   1. Sửa tên Nhân viên      |");
                    System.out.println("|   2. Sửa số điện thoại      |");
                    System.out.println("|   3. Đổi Email              |");
                    System.out.println("|   4. Sửa địa chỉ            |");
                    System.out.println("|   5. Sửa chức vụ            |");
                    System.out.println("|   6. Quay lại User Manager  |");
                    System.out.println("|-----------------------------|");
                    System.out.println("Chọn chức năng");
                    System.out.print("\t➺ ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    Staff staff = new Staff();
                    staff.setStaffId(id);
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên nhân viên không dấu và lưu ý viết hoa chữ cái đầu!!");
                            System.out.print("\t➺ ");
                            String StaffNameUpdate = scanner.nextLine();
                            while (!ValidateUtils.isNameValid(StaffNameUpdate)) {
                                System.out.println("Nhập sai rồi! lưu ý viết hoa chữ cái đầu!!");
                                System.out.printf("\t➺ ");
                                StaffNameUpdate = scanner.nextLine();
                            }
                            while (staffService.checkDuplicateStaffName(StaffNameUpdate)) {
                                System.out.println("Họ tên đã tồn tại");
                                StaffNameUpdate = scanner.nextLine();
                            }
                            staff.setStaffName(StaffNameUpdate);
                            staffService.update(staff);
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
                            while (staffService.checkDuplicatePhone(phoneNumberUpdate)) {
                                System.out.println("Số điện thoại này đã tồn tại! Mời bạn nhập lại");
                                System.out.print("\t➺ ");
                                phoneNumberUpdate = scanner.nextLine();
                            }
                            staff.setPhoneNumber(phoneNumberUpdate);
                            staffService.update(staff);
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
                            while (staffService.checkDuplicateEmail(emailUpdate)) {
                                System.out.println("Email này đã tồn tại! Nhập cái mới đi nào!!");
                                System.out.print("\t➺ ");
                                emailUpdate = scanner.nextLine();
                            }
                            staff.setEmail(emailUpdate);
                            staffService.update(staff);
                            System.out.println("Đổi Email thành công!");
                            break;

                        case 4:
                            System.out.println("Nhập địa chỉ muốn đổi");
                            System.out.print("\t➺ ");
                            String address = scanner.nextLine();
                            staff.setAddress(address);
                            staffService.update(staff);
                            System.out.println("Đổi địa chỉ thành công");
                            break;
                        case 5:
                            System.out.println("Nhập chức vụ bạn muốn sửa: ");
                            System.out.print("\t➺ ");
                            String department = scanner.nextLine();
                            staff.setDepartment(department);
                            staffService.update(staff);
                            System.out.println("Sửa thành công");
                        case 6:
                            optionMenuStaff();
                            break;
                        default:
                            System.out.println("Vui lòng chọn số theo chức năng bên dưới!!");
                            updateStaff();
                    }
                    boolean is = true;
                    do {
                        System.out.println("|--------------------------------------------|");
                        System.out.println("|   Nhấn 'm'(more) để tiếp tục sửa           |");
                        System.out.println("|        'd'(different) để sửa món khác      |");
                        System.out.println("|        'b'(back) để quay lại User Manager  |");
                        System.out.println("|--------------------------------------------|");
                        System.out.print("\t➺ ");
                        String choice1 = scanner.nextLine();
                        switch (choice1) {
                            case "m":
                                check = false;
                                break;
                            case "d":
                                updateStaff();
                                break;
                            case "b":
                                optionMenuStaff();
                                break;
                            default:
                                System.out.println("Nhấn không đúng! vui lòng chọn lại");
                                is = false;
                        }
                    } while (!is);
                } else {
                    System.out.println("Không tìm thấy ID! Vui lòng nhập lại");
                    updateStaff();
                }
            } while (!check);
        } catch (Exception e) {
            System.out.println("Nhập sai! vui lòng nhập lại");
        }
    }

    public void remove () {
        showStaff();
        System.out.println("Nhập ID người dùng cần xóa");
        System.out.print("\t➺ ");
        int id = Integer.parseInt(scanner.nextLine());
        Staff staff = staffService.getStaffById(id);
        if (staff == null)
            System.out.println(" ⭆ Mgười dùng này không tồn tại!! ");
        else {
            staffService.remove(staff);
            System.out.println(" ⭆ Đã xóa thành công!! ");
        }
        optionMenuStaff();
    }
}

