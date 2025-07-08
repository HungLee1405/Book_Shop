/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trang
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

public class CategoryDAO {

    // SQL Queries
    private static final String GET_ALL_CATEGORY = "SELECT * FROM tblCategories";

    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_CATEGORY);
            rs = ps.executeQuery();

            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.err.println("Error in getAllCategory(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return categories;
    }

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println("Error closing resources: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
