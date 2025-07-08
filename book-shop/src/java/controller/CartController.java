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
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.CartDTO;
import model.OrderDAO;
import model.UserDTO;
import model.ProductDAO;
import utils.CartCookieUtils;

/**
 *
 * @author trang
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    OrderDAO odao = new OrderDAO();
    ProductDAO pdao = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String action = request.getParameter("action");

            if (action.equals("addToCart")) {
                url = handleAddToCart(request, response);

            } else if (action.equals("updateQuantity")) {
                url = handleUpdateQuantity(request, response);

            } else if (action.equals("removeCart")) {
                url = handleRemoveFromCart(request, response);

            } else if (action.equals("viewCart")) {
                url = handleViewCart(request, response);
                
            } else if (action.equals("checkOut")) {
                url = handleCheckout(request, response);
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String handleAddToCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("qty"));

            // Lấy map từ request
            Map<Integer, Integer> cartMap = CartCookieUtils.getCartMapFromRequest(request);

            // Cập nhật map
            cartMap.put(productId, cartMap.getOrDefault(productId, 0) + quantity);

            // Cập nhật cookie
            CartCookieUtils.updateCartCookie(response, cartMap);

            // Tạo lại CartDTO từ cartMap vừa cập nhật
            CartDTO cart = CartCookieUtils.buildCartFromMap(cartMap);
            request.setAttribute("cartItems", cart.getListItems());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "cart.jsp";
    }

    private String handleViewCart(HttpServletRequest request, HttpServletResponse response) {
        CartDTO cart = CartCookieUtils.getCartFromCookie(request);
        request.setAttribute("cartItems", cart.getListItems());
        return "cart.jsp";
    }

    private String handleRemoveFromCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            Map<Integer, Integer> cartMap = CartCookieUtils.getCartMapFromRequest(request);
            cartMap.remove(productId);
            CartCookieUtils.updateCartCookie(response, cartMap);

            CartDTO cart = CartCookieUtils.buildCartFromMap(cartMap);
            request.setAttribute("cartItems", cart.getListItems());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "cart.jsp";
    }

    private String handleUpdateQuantity(HttpServletRequest request, HttpServletResponse response) {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            int newQty = Integer.parseInt(request.getParameter("qty"));

            Map<Integer, Integer> cartMap = CartCookieUtils.getCartMapFromRequest(request);
            if (newQty <= 0) {
                cartMap.remove(productId);
            } else {
                cartMap.put(productId, newQty);
            }

            CartCookieUtils.updateCartCookie(response, cartMap);
            CartDTO cart = CartCookieUtils.buildCartFromMap(cartMap);
            request.setAttribute("cartItems", cart.getListItems());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "cart.jsp";
    }

    private String handleCheckout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            return "login.jsp";
        }

        CartDTO cart = CartCookieUtils.getCartFromCookie(request);
        if (cart == null || cart.getListItems().isEmpty()) {
            request.setAttribute("message", "Cart is empty!");
            return "cart.jsp";
        }

        boolean result = odao.addOrder(user, cart);

        if (result) {
            CartCookieUtils.clearCartCookie(response); // xoá cookie sau khi đặt hàng
            request.setAttribute("message", "Order placed successfully!");
            return "cart.jsp";
        } else {
            request.setAttribute("message", "Failed to place order.");
            return "cart.jsp";
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
