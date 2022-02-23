package com.md2.mainItems;

public class User {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String username;
    private String password;
    private UserType userType;
    private UserStatus activeStatus;


    public User(int id, String fullName, String phoneNumber, String email, String address, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public User(String draw) {
        String[] strings = draw.split(",");
        this.id = Integer.parseInt(strings[0]);
        this.fullName = strings[1];
        this.phoneNumber = strings[2];
        this.email = strings[3];
        this.address = strings[4];
        this.username = strings[5];
        this.password = strings[6];
        this.userType = UserType.parseUserType(strings[7]);
        this.activeStatus= UserStatus.parseUserStatus(strings[8]);
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(UserStatus activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return id + "," + fullName + "," + phoneNumber + "," + email + "," + address + "," + username + "," + password + "," + userType.getValue() + "," + activeStatus.getValue();
    }

}
