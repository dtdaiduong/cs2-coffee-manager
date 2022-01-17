package com.md2.mainItems;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    private int staffId;
    private String staffName;
    private String phoneNumber;
    private String email;
    private String address;
    private String department;

    private List<Staff> staff = new ArrayList<>();

    public Staff() {
    }

    public Staff(int staffNameId, String staffName, String phoneNumber, String email, String address, String department) {
        this.staffId = staffNameId;
        this.staffName = staffName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.department = department;
    }

    public Staff(String draw) {
        String[] strings = draw.split(",");
        this.staffId = Integer.parseInt(strings[0]);
        this.staffName = strings[1];
        this.phoneNumber = strings[2];
        this.email = strings[3];
        this.address = strings[4];
        this.department = strings[5];
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return staffId + "," + staffName + "," + phoneNumber + "," + email + "," + address + "," + department;
    }
}
