/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.booking;

import hnqm.resource.ResourceDAO;
import hnqm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Minh Hoang
 */
public class RequestDAO {
    
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public boolean insertRequest(RequestDTO req) throws NamingException, SQLException, Exception {
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "INSERT INTO Request(RequestID,userID,createDate,status,resourceID) values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, req.getRequestID());
                ps.setString(2, req.getUserID());
                ps.setTimestamp(3, new Timestamp(req.getCreateDate().getTime()));
                ps.setString(4, req.getStatus());
                ps.setString(5, req.getDto().getResourceID());
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean updateAcceptRequest(String requestID) throws Exception {
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "update Request set status=? where RequestID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Accept");
                ps.setString(2, requestID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }
 public boolean updateDeleteRequest(String requestID) throws Exception {
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "update Request set status=? where RequestID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Delete");
                ps.setString(2, requestID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }
    public List<RequestDTO> getAllRequest() {
        List<RequestDTO> result = null;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "SELECT RequestID,userID,createDate,status,resourceID FROM Request";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String requestID = rs.getString("RequestID");
                    String userID = rs.getString("userID");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    String resourceID = rs.getString("resourceID");
                    
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    ResourceDAO resourceDAO = new ResourceDAO();
                    
                    result.add(new RequestDTO(requestID, status, userID, createDate, resourceDAO.getResourceByID(resourceID)));
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
