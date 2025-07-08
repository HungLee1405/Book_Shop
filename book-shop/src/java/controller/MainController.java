/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.CategoryDAO;
import model.CategoryDTO;
import model.ProductDAO;
import model.ProductDTO;

/**
 *
 * @author trang
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController", "/mc"})

public class MainController extends HttpServlet {

    private static final String WELCOME = "login.jsp";
    ProductDAO pdao = new ProductDAO();
    CategoryDAO cdao = new CategoryDAO();

    private boolean isUserAction(String action) {
        return "login".equals(action)
                || "logout".equals(action)
                || "register".equals(action)
                || "updateProfile".equals(action)
                || "viewProfile".equals(action)
                || "changePassword".equals(action);
    }

    private boolean isProductAction(String action) {
        return "addProduct".equals(action)
                || "searchProduct".equals(action)
                || "loadHP".equals(action)
                || "changeProductStatus".equals(action)
                || "editProduct".equals(action)
                || "updateProduct".equals(action)
                || "viewProduct".equals(action);
    }

    private boolean isCategoryAction(String action) {
        return "viewCat".equals(action)
                || "viewAllProducts".equals(action);
    }

    private boolean isCartAction(String action) {
        return "addToCart".equals(action)
                || "updateQuantity".equals(action)
                || "removeCart".equals(action)
                || "viewCart".equals(action)
                || "checkOut".equals(action);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");

            List<ProductDTO> listP = pdao.get4NewestProducts();
            List<CategoryDTO> listC = cdao.getAllCategory();
            request.setAttribute("listP", listP);
            request.setAttribute("listC", listC);
            url = "index.jsp";

            if (isUserAction(action)) {
                url = "/UserController";
            } else if (isProductAction(action)) {
                url = "/ProductController";
            } else if (isCategoryAction(action)) {
                url = "/CategoryController";
            } else if (isCartAction(action)) {
                url = "/CartController";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

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
