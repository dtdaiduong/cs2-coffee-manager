package com.md2.function;

import com.md2.mainItems.Staff;
import com.md2.ulits.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class StaffService implements IStaffService {
    public static String path = "data/staff.csv";
    List<Staff> staffs = new ArrayList<>();

    public StaffService() {
        getStaffs();
    }

    @Override
    public List<Staff> getStaffs() {
        List<Staff> newStaffs = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newStaffs.add(new Staff(record));
        }
        return staffs = newStaffs;
    }

    @Override
    public void add(Staff newStaff) {
        staffs.add(newStaff);
        CSVUtils.write(path, staffs);
    }

    @Override
    public void update() {
        CSVUtils.write(path, staffs);
    }

    @Override
    public Staff getStaffById(int id) {
        for (Staff staff : staffs) {
            if (staff.getStaffId() == id)
                return staff;
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return getStaffById(id) != null;
    }

    @Override
    public boolean checkDuplicateStaffName(String staffName) {
        for (Staff staff : staffs) {
            if (staff.getStaffName().equals(staffName)) ;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(double id) {
        for (Staff staff : staffs) {
            if (staff.getStaffId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        for (Staff staff : staffs) {
            if (staff.getPhoneNumber().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        for (Staff staff : staffs) {
            if (staff.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public void update(Staff newStaff) {
        for (Staff staff : staffs) {
            if (staff.getStaffId() == newStaff.getStaffId()) {
                if (newStaff.getStaffName() != null && !newStaff.getStaffName().isEmpty())
                    staff.setStaffName(newStaff.getStaffName());
                if (newStaff.getEmail() != null && !newStaff.getEmail().isEmpty())
                    staff.setEmail(newStaff.getEmail());
                if (newStaff.getPhoneNumber() != null && !newStaff.getPhoneNumber().isEmpty())
                    staff.setPhoneNumber(newStaff.getPhoneNumber());
                if (newStaff.getAddress() != null && !newStaff.getAddress().isEmpty())
                    staff.setAddress(newStaff.getAddress());
                CSVUtils.write(path, staffs);
                break;
            }
        }
    }

    @Override
    public void remove(Staff staff) {
        staffs.remove(staff);
        update();
    }
}
