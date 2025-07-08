/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author trang
 */
public class UserDTO {

    private int userID;
    private String userName;
    private String fullName;
    private String password;
    private String roleID;
    private String email;
    private Date birthDay;
    private String address;
    private String phone;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(int userID, String userName, String fullName, String password, String roleID, String email, Date birthDay, String address, String phone, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public UserDTO(String userName, String fullName, String password, String roleID, String email, Date birthDay, String address, String phone, boolean status) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }
    
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
