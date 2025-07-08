/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author trang
 */
public class ProductDTO {

    private int productId;
    private String productName;
    private String author;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private double unitPrice;
    private int unitsInStock;
    private int quantitySold;
    private String image;
    private String description;
    private Date releaseDate;
    private double discount;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String productName, String author, CategoryDTO category, SupplierDTO supplier, double unitPrice, int unitsInStock, int quantitySold, String image, String description, Date releaseDate, double discount, boolean status) {
        this.productName = productName;
        this.author = author;
        this.category = category;
        this.supplier = supplier;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.quantitySold = quantitySold;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;
        this.discount = discount;
        this.status = status;
    }

    public ProductDTO(int productId, String productName, String author, CategoryDTO category, SupplierDTO supplier, double unitPrice, int unitsInStock, int quantitySold, String image, String description, Date releaseDate, double discount, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.author = author;
        this.category = category;
        this.supplier = supplier;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.quantitySold = quantitySold;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;
        this.discount = discount;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSalePrice() {
        if (discount > 0) {
            double salePrice = unitPrice * (1 - discount);
            return Math.round(salePrice * 100.0) / 100.0;
        } else {
            return unitPrice;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
