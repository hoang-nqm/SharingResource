/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.resource;

import hnqm.category.CategoryDTO;
import hnqm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hoang
 */
public class ResourceDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ResourceDTO> getAllResource() {

        List<ResourceDTO> result = null;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "SELECT resourceID,resourceName,color,usingDate,image,quantity,categoryID,status FROM Resources";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String resourceID = rs.getString("resourceID");
                    String resourceName = rs.getString("resourceName");
                    String color = rs.getString("color");
                    Date usingDate = rs.getDate("usingDate");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String cateID = rs.getString("categoryID");
                    String status=rs.getString("status");

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new ResourceDTO(resourceID, resourceName, color, usingDate, image, quantity, cateID,status));
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public int getNumberPage() {

        String sql = "SELECT count(*) FROM Resources";
        try {
            con = new DBHelper().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);

                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<ResourceDTO> pagingResource(int index) {

        List<ResourceDTO> result = new ArrayList<>();
        String sql = "SELECT resourceID,resourceName,color,usingDate,image,quantity,categoryID,status FROM Resources\n "
                + "ORDER BY resourceID \n"
                + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY;";
        try {
            con = new DBHelper().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 20);
            rs = ps.executeQuery();
            while (rs.next()) {
                String resourceID = rs.getString("resourceID");
                String resourceName = rs.getString("resourceName");
                String color = rs.getString("color");
                Date usingDate = rs.getDate("usingDate");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                String cateID = rs.getString("categoryID");
                  String status=rs.getString("status");
                result.add(new ResourceDTO(resourceID, resourceName, color, usingDate, image, quantity, cateID,status));
            }
        } catch (Exception e) {
        }
        return result;
    }

    public List<ResourceDTO> searchByName(String txtResourceName) {
        List<ResourceDTO> result = new ArrayList<>();
        String sql = "select  resourceID,resourceName,color,usingDate,image,quantity,categoryID,status from Resources\n"
                + "where [resourceName] like ?";
        try {
            con = new DBHelper().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtResourceName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String resourceID = rs.getString("resourceID");
                String resourceName = rs.getString("resourceName");
                String color = rs.getString("color");
                Date usingDate = rs.getDate("usingDate");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                String cateID = rs.getString("categoryID");
                String status = rs.getString("status");
                result.add(new ResourceDTO(resourceID, resourceName, color, usingDate, image, quantity, cateID, status));
            }
        } catch (Exception e) {
        }
        return result;
    }

    public List<ResourceDTO> searchByUsingDate(String date) {
        List<ResourceDTO> result = new ArrayList<>();
        String sql = "select  resourceID,resourceName,color,usingDate,image,quantity,categoryID,status from Resources\n"
                + "where [usingDate] like ?";
        try {
            con = new DBHelper().getConnection();//mo ket noi voi sql
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + date + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String resourceID = rs.getString("resourceID");
                String resourceName = rs.getString("resourceName");
                String color = rs.getString("color");
                Date usingDate = rs.getDate("usingDate");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                String cateID = rs.getString("categoryID");
                String status = rs.getString("status");
                result.add(new ResourceDTO(resourceID, resourceName, color, usingDate, image, quantity, cateID, status));
            }
        } catch (Exception e) {
        }
        return result;
    }
    public List<ResourceDTO> searchByCategoryID(String categoryID) {
        List<ResourceDTO> result = new ArrayList<>();
        String sql = "select  resourceID,resourceName,color,usingDate,image,quantity,categoryID,status from Resources\n"
                + "where [categoryID] like ?";
        try {
            con = new DBHelper().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + categoryID + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String resourceID = rs.getString("resourceID");
                String resourceName = rs.getString("resourceName");
                String color = rs.getString("color");
                Date usingDate = rs.getDate("usingDate");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                String cateID = rs.getString("categoryID");
                String status = rs.getString("status");
                result.add(new ResourceDTO(resourceID, resourceName, color, usingDate, image, quantity, cateID, status));
            }
        } catch (Exception e) {
        }
        return result;
    }

    public ResourceDTO getResourceByID(String id) throws Exception {
        ResourceDTO resource = null;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "select resourceID,resourceName,color,usingDate,image,quantity,categoryID,status from Resources where resourceID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String resourceID = rs.getString("resourceID");
                    String resourceName = rs.getString("resourceName");
                    String color = rs.getString("color");
                    Date usingDate = rs.getDate("usingDate");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String cateID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    resource = new ResourceDTO(resourceID, resourceName, color, usingDate, image, quantity, cateID);
                }
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return resource;
    }

    public boolean updateResource(String resourceID) throws Exception {
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "update Resources set status=? where resourceID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Access right");
                ps.setString(2, resourceID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }
}
