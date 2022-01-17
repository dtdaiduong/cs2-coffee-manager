package com.md2.function;

import com.md2.mainItems.Staff;

import java.util.ArrayList;
import java.util.List;

public interface IStaffService {
    List<Staff> getStaffs();

    void add(Staff newStaff);


    void update();

    Staff getStaffById(int id);

    boolean exist(int id);


    boolean checkDuplicateStaffName(String name);

    boolean checkDuplicateId(double id);

    boolean checkDuplicatePhone(String phone);

    boolean checkDuplicateEmail(String email);

    void update(Staff newStaff);

    void remove(Staff staff);
}
