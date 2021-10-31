/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.account;

import hnqm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Minh Hoang
 */
public class AccountDAO {

    public boolean checkEmail(String email) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        String sql = "select fullName from Users where userID=?";
        try {
            con = new DBHelper().getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public AccountDTO checkAccount(String email, String password) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDTO account = null;
         String sql = "SELECT * FROM Users\n"
                + "WHERE userID=?\n"
                + "AND password=?";
        try {
            con = new DBHelper().getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String status = rs.getString("status");
                Date createDate = rs.getDate("createDate");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                account = new AccountDTO(email, fullName, roleID);
                account.setStatus(status);
                account.setCreateDate(createDate);
                account.setPhone(phone);
                account.setAddress(address);

            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return account;
    }

    public boolean createAccount(AccountDTO account) throws Exception {
         Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "insert into Users(userID,fullName,password,roleID,status,createDate,address,phone) values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, account.getUserID());
                ps.setString(2, account.getFullName());
                ps.setString(3, account.getPassword());
                ps.setString(4, account.getRolID());
                ps.setString(5, account.getStatus());
                ps.setTimestamp(6, new Timestamp(account.getCreateDate().getTime()));
                ps.setString(7, account.getAddress());
                ps.setString(8, account.getPhone());
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    //UPDATE STATUS FUNCION 3
    public boolean updateStatus(String userID) throws Exception {
          Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "update Users set status=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Active");
                ps.setString(2, userID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

}
