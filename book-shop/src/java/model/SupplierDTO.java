/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trang
 */
public class SupplierDTO {
     private int supplierId;
    private String companyName;
    private String contactName;
    private String country;
    private String phone;
    private String homePage;

    public SupplierDTO() {
    }

    public SupplierDTO(String companyName, String contactName, String country, String phone, String homePage) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.country = country;
        this.phone = phone;
        this.homePage = homePage;
    }

    public SupplierDTO(int supplierId, String companyName, String contactName, String country, String phone, String homePage) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.country = country;
        this.phone = phone;
        this.homePage = homePage;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

   
}
