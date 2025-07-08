/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author trang
 */
public class ProductDAO {

    // SQL Queries
    private static final String GET_ALL_PRODUCTS = "SELECT ProductID, ProductName, Author, SupplierID, CategoryID, "
            + "UnitPrice, UnitsInStock, QuantitySold, Image, Description, "
            + "ReleaseDate, Discount, Status "
            + "FROM tblProducts";

    private static final String CREATE_PRODUCT = "INSERT INTO tblProducts (name, image, description, price, catID, status, author) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE tblProducts SET name = ?, image=?, description = ?, price = ?, catID = ?, status = ?, author = ? WHERE id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM tblProducts WHERE id = ?";

    public List<ProductDTO> getAll() {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(GET_ALL_PRODUCTS);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in getAll(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    public ProductDTO getProductByID(int id) {  //get 1 sp
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = GET_ALL_PRODUCTS + " WHERE ProductID = ? AND Status = 1";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));
            }
        } catch (Exception e) {
            System.err.println("Error in getProductByID(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return product;
    }

    public List<ProductDTO> getAllActiveProducts() {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM tblProducts WHERE Status = 1";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in getAllActiveProducts(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    public List<ProductDTO> getProductsByCatID(int categoryId) {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = GET_ALL_PRODUCTS + " WHERE CategoryID like ? and Status=1";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in getProductsByCatID(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    public List<ProductDTO> getProductsByName(String name) {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = GET_ALL_PRODUCTS + " WHERE ProductName like ? and Status=1";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in getProductsByStatus(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    public List<ProductDTO> getProductsByStatus(boolean status) {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = GET_ALL_PRODUCTS + " WHERE Status = ?";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, status);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in getProductsByStatus(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    public List<ProductDTO> get4NewestProducts() {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT TOP 4 * FROM tblProducts WHERE Status = 1 ORDER BY ProductID DESC";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in get4NewestProducts(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    public List<ProductDTO> getNext4NewestProducts(int lastSeenId) {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT TOP 4 * FROM tblProducts WHERE Status = 1 AND ProductID < ? ORDER BY ProductID DESC";

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lastSeenId); // load less than the last seen ProductID
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setAuthor(rs.getString("Author"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));
                product.setUnitsInStock(rs.getInt("UnitsInStock"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setDescription(rs.getString("Description"));
                product.setReleaseDate(rs.getDate("ReleaseDate"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setStatus(rs.getBoolean("Status"));

                int catID = rs.getInt("CategoryID");
                CategoryDTO category = new CategoryDTO();
                category.setCategoryId(catID);
                product.setCategory(category);

                int supID = rs.getInt("SupplierID");
                SupplierDTO supplier = new SupplierDTO();
                supplier.setSupplierId(supID);
                product.setSupplier(supplier);

                products.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error in getNext4NewestProducts(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return products;
    }

    /* public boolean create(ProductDTO product) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(CREATE_PRODUCT);

            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getCatID());
            ps.setBoolean(6, product.isStatus());
            ps.setString(7, product.getAuthor());

            int rowsAffected = ps.executeUpdate();
            success = (rowsAffected > 0);

        } catch (Exception e) {
            System.err.println("Error in create(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }

        return success;
    }

    public boolean update(ProductDTO product) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(UPDATE_PRODUCT);

            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getCatID());
            ps.setBoolean(6, product.isStatus());
            ps.setString(7, product.getAuthor());
            ps.setInt(8, product.getId()); // WHERE condition

            int rowsAffected = ps.executeUpdate();
            success = (rowsAffected > 0);

        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }

        return success;
    }

    public boolean updateStatus(int productId, boolean status) {
        ProductDTO product = getProductByID(productId);
        if (product != null) {
            product.setStatus(status);
            return update(product);
        }
        return false;
    }

    public boolean delete(String id) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(DELETE_PRODUCT);
            ps.setString(1, id);

            int rowsAffected = ps.executeUpdate();
            success = (rowsAffected > 0);

        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
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
