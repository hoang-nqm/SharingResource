/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.account;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Minh Hoang
 */
public class AccountDTO implements Serializable{
    private String userID;
    private String fullName;
    private String password;
    private String rolID;
    private String status;
    private Date createDate;
    private String address;
    private String phone;

    public AccountDTO() {
    }

    public AccountDTO(String userID, String fullName, String rolID) {
        this.userID = userID;
        this.fullName = fullName;
        this.rolID = rolID;
    }

    public AccountDTO(String userID, String fullName, String password, String rolID) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.rolID = rolID;
    }

    public AccountDTO(String userID, String fullName, String password, String rolID, String status, Date createDate) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.rolID = rolID;
        this.status = status;
        this.createDate = createDate;
    }

    public AccountDTO(String userID, String fullName, String password, String address, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public AccountDTO(String userID, String fullName, String password, String rolID, String status, Date createDate, String address, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.rolID = rolID;
        this.status = status;
        this.createDate = createDate;
        this.address = address;
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getRolID() {
        return rolID;
    }

    public void setRolID(String rolID) {
        this.rolID = rolID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    
}
