/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author trang
 */
public class OrderDAO {

    public boolean addOrder(UserDTO user, CartDTO cart) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // 1. insert tblOrders
            String sql01 = "INSERT INTO tblOrders (Date, UserName, TotalMoney, status) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql01, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, user.getUserName());
            ps.setDouble(3, cart.getTotalMoney());
            ps.setBoolean(4, false); // false: not handle

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Insert tblOrders failed, no rows affected.");
            }

            // 2. get orderIId
            rs = ps.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve OrderID.");
            }

            // 3. insert tblOrderDetails
            String sql02 = "INSERT INTO tblOrderDetails (OrderID, ProductID, Quantity, UnitPrice, Discount) VALUES (?, ?, ?, ?, ?)";
            for (ItemDTO item : cart.getListItems()) {
                try ( PreparedStatement psDetail = conn.prepareStatement(sql02)) {
                    psDetail.setInt(1, orderId);
                    psDetail.setInt(2, item.getProduct().getProductId());
                    psDetail.setInt(3, item.getQuantity());
                    psDetail.setDouble(4, item.getProduct().getUnitPrice());
                    psDetail.setDouble(5, item.getProduct().getDiscount());
                    psDetail.executeUpdate();
                }
            }
            conn.commit(); // succes
            success = true;

        } catch (Exception e) {
            System.err.println("Error in addOrder(): " + e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            closeResources(conn, ps, rs);
        }
        
        return success;
    }

    public CartDTO getCart(String username) {
        CartDTO cart = new CartDTO();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();

            // 1. Tìm orderID của đơn hàng chưa xử lý mới nhất
            String getLatestOrderSQL = "SELECT TOP 1 OrderID FROM tblOrders WHERE UserName = ? AND status = 0 ORDER BY OrderID DESC";
            ps = conn.prepareStatement(getLatestOrderSQL);
            ps.setString(1, username);
            rs = ps.executeQuery();

            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt("OrderID");
            }

            if (orderId == -1) {
                return cart; 
            }

            String sql = "SELECT d.ProductID, d.Quantity, d.UnitPrice, d.Discount, "
                    + "p.ProductName, p.Image, p.Description, p.Author, p.CategoryID, p.Status "
                    + "FROM tblOrderDetails d "
                    + "JOIN tblProducts p ON d.ProductID = p.ProductID "
                    + "WHERE d.OrderID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setImage(rs.getString("Image"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int quantity = rs.getInt("Quantity");
                double price = product.getSalePrice();
                ItemDTO item = new ItemDTO(product, quantity, price);

                cart.addItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return cart;
    }

    /*public boolean updateQuantity(String username, int productId, int quantity) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "IF EXISTS (SELECT * FROM tblCartItems WHERE username = ? AND productId = ?) "
                + "BEGIN UPDATE tblCartItems SET quantity = quantity + ? WHERE username = ? AND productId = ? END "
                + "ELSE BEGIN INSERT INTO tblCartItems (username, productId, quantity) VALUES (?, ?, ?) END";
        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setString(4, username);
            ps.setInt(5, productId);
            ps.setString(6, username);
            ps.setInt(7, productId);
            ps.setInt(8, quantity);

            int rowsAffected = ps.executeUpdate();
            success = (rowsAffected > 0);

        } catch (Exception e) {
            System.err.println("Error in addToCart():  " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }

        return success;
    }*/
    
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
