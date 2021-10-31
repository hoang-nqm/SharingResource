/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.category;

import hnqm.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh Hoang
 */
public class CategoryDAO {

    public List<CategoryDTO> getAllCategory() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CategoryDTO> result = null;
        try {
            con = new DBHelper().getConnection();
            if(con != null){
                String sql = "SELECT categoryID,categoryName FROM Category";
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
                while(rs.next()){
                    String cateID=rs.getString("categoryID");
                    String cateName = rs.getString("categoryName");
                    if(result==null){
                        result= new ArrayList<>();
                    }
                    result.add(new CategoryDTO(cateID, cateName));
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
