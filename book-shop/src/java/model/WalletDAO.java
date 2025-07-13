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

public class WalletDAO {

    public List<WalletDTO> getAllWallets() {
        List<WalletDTO> wallets = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT UserName, Balance FROM Wallets";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                WalletDTO wallet = new WalletDTO();
                wallet.setUserName(rs.getString("UserName"));
                wallet.setBalance(rs.getDouble("Balance"));

                wallets.add(wallet);
            }
        } catch (Exception e) {
            System.err.println("Error in getAllWallets(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return wallets;
    }

    public WalletDTO getWalletByUserName(String userName) {
        WalletDTO wallet = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT UserName, Balance FROM Wallets WHERE UserName = ?";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (rs.next()) {
                wallet = new WalletDTO();
                wallet.setUserName(rs.getString("UserName"));
                wallet.setBalance(rs.getDouble("Balance"));

            }
        } catch (Exception e) {
            System.err.println("Error in getWalletByUserName(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return wallet;
    }

    public boolean updateBalance(WalletDTO wallet) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Wallets SET Balance = ? WHERE UserName = ?";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, wallet.getBalance());
            ps.setString(2, wallet.getUserName());

            int rowsAffected = ps.executeUpdate();
            success = (rowsAffected > 0);
        } catch (Exception e) {
            System.err.println("Error in updateBalance(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }

        return success;
    }
    
    public boolean updateBalanceByCheckOut(String userName, double newBalance) {
        String sql = "UPDATE Wallets SET Balance = ? WHERE UserName = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setString(2, userName);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
