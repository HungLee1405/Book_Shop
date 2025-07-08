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
import model.ProductDTO;
import model.ProductDAO;
import model.CategoryDTO;
import model.CategoryDAO;

/**
 *
 * @author trang
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/cc", "/CategoryController"})
public class CategoryController extends HttpServlet {

    ProductDAO pdao = new ProductDAO();
    CategoryDAO cdao = new CategoryDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String action = request.getParameter("action");
            if (action.equals("viewCat")) {
                url = handleViewCat(request, response);

            } else if (action.equals("viewAllProducts")) {
                url = handleViewAllProducts(request, response);
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String handleViewCat(HttpServletRequest request, HttpServletResponse response) {
        String catID = request.getParameter("catID"); //call catID 
        int cat_value = Integer.parseInt(catID);

        List<ProductDTO> listByCat = pdao.getProductsByCatID(cat_value);
        request.setAttribute("listP", listByCat);

        List<CategoryDTO> listC = cdao.getAllCategory(); //reload the cat
        request.setAttribute("listC", listC);
        return "productsDisplay.jsp";
    }

    private String handleViewAllProducts(HttpServletRequest request, HttpServletResponse response) {
        List<CategoryDTO> listC = cdao.getAllCategory();
        request.setAttribute("listC", listC);

        List<ProductDTO> listP = pdao.getAllActiveProducts();
        request.setAttribute("listP", listP);
        return "productsDisplay.jsp";
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
