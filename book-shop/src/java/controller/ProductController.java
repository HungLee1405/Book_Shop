/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ProductDAO;
import model.ProductDTO;
import utils.AuthUtils;

/**
 *
 * @author trang
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    ProductDAO pdao = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String action = request.getParameter("action");

            if (action.equals("addProduct")) {
                url = "";

            } else if (action.equals("searchProduct")) {
                url = handleProductSearching(request, response);

            } else if (action.equals("changeProductStatus")) {
                url = "";

            } else if (action.equals("editProduct")) {
                url = handleProductEditing(request, response);

            } else if (action.equals("updateProduct")) {
                url = "";

            } else if (action.equals("viewProduct")) {
                url = handleViewProduct(request, response);
            } else if (action.equals("viewProductList")){
                url = handleShowProductList(request, response);
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String handleViewProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        int id_value = Integer.parseInt(id);
        ProductDTO oneP = pdao.getProductByID(id_value);
        request.setAttribute("p", oneP);
        return "detail.jsp";
    }

    private String handleProductSearching(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        List<ProductDTO> list = pdao.getProductsByName(keyword);
        request.setAttribute("list", list);
        request.setAttribute("keyword", keyword);
        return "productEdit.jsp";
    }

    private String handleShowProductList(HttpServletRequest request, HttpServletResponse response) {
        List<ProductDTO> productList = pdao.getAll(); // Lấy toàn bộ sản phẩm
        request.setAttribute("list", productList);            // Đặt vào requestScope
        return "productEdit.jsp";
    }

    /*private String handleProductStatusChanging(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        int id_value = Integer.parseInt(productId);
        if (AuthUtils.isAdmin(request)) {
            pdao.updateStatus(id_value, false);
        }
        return handleProductSearching(request, response);
    }*/

    private String handleProductEditing(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        String keyword = request.getParameter("keyword");

        int id_value = Integer.parseInt(productId);

        if (AuthUtils.isAdmin(request)) {
            ProductDTO product = pdao.getProductByID(id_value);
            if (product != null) {
                request.setAttribute("keyword", keyword);
                request.setAttribute("product", product);
                request.setAttribute("isEdit", true);
                return "productForm.jsp";
            }
        }
        return handleProductSearching(request, response);
    }

    /*private String handleProductAdding(HttpServletRequest request, HttpServletResponse response) {
        if (AuthUtils.isAdmin(request)) {
            String checkError = "";
            String message = "";
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            String catID = request.getParameter("catID");
            String status = request.getParameter("status");
            String author = request.getParameter("author");

            double price_value = -1;
            try {
                price_value = Double.parseDouble(price);
                if (price_value <= 0) {
                    checkError += "Price must be greater than zero.<br/>";
                }
            } catch (NumberFormatException e) {
                checkError += "Invalid price format. Please enter a valid number.<br/>";
            }

            int cat_value = 0;
            try {
                cat_value = Integer.parseInt(catID);
                if (cat_value <= 0) {
                    checkError += "Category ID must be a positive number.<br/>";
                }
            } catch (NumberFormatException e) {
                checkError += "Invalid Category ID format.<br/>";
            }

            boolean status_value = true;
            try {
                status_value = Boolean.parseBoolean(status);
            } catch (Exception e) {
            }

            if (checkError.isEmpty()) {
                message = "Add product successfully.";
            }

            ProductDTO product = new ProductDTO(name, image, description, price_value, cat_value, author, status_value);
            if (!pdao.create(product)) {
                checkError += "<br/>Can not add new product.";
            }

            request.setAttribute("product", product);
            request.setAttribute("checkError", checkError);
            request.setAttribute("message", message);
        }
        return "productForm.jsp";
    }

    private String handleProductUpdating(HttpServletRequest request, HttpServletResponse response) {
        if (AuthUtils.isAdmin(request)) {
            String checkError = "";
            String message = "";
            String id = request.getParameter("id");
            int id_value = Integer.parseInt(id);
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            String catID = request.getParameter("catID");
            String status = request.getParameter("status");
            String author = request.getParameter("author");

            double price_value = -1;
            try {
                price_value = Double.parseDouble(price);
                if (price_value <= 0) {
                    checkError += "Price must be greater than zero.<br/>";
                }
            } catch (NumberFormatException e) {
                checkError += "Invalid price format. Please enter a valid number.<br/>";
            }

            int cat_value = 0;
            try {
                cat_value = Integer.parseInt(catID);
                if (cat_value <= 0) {
                    checkError += "Category ID must be a positive number.<br/>";
                }
            } catch (NumberFormatException e) {
                checkError += "Invalid Category ID format.<br/>";
            }

            boolean status_value = status != null && status.equals("true");

            if (name == null || name.trim().isEmpty()) {
                checkError += "Product name is requried.<br/>";
            }

            if (checkError.isEmpty()) {
                ProductDTO product = new ProductDTO(id_value, name, image, description, price_value, cat_value, author, status_value);
                if (pdao.update(product)) {
                    message = "Product updated succesfully";
                    //return to productEdit
                    return handleProductSearching(request, response);
                } else {
                    checkError += "Cannot update product <br/>";
                    request.setAttribute("product", product);
                }
            } else {
                //keep the form 
                ProductDTO product = new ProductDTO(id_value, name, image, description, price_value, cat_value, author, status_value);
                request.setAttribute("product", product);
            }

            request.setAttribute("checkError", checkError);
            request.setAttribute("message", message);
            request.setAttribute("isEdit", true);
        }
        return "productForm.jsp";
    }*/

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
