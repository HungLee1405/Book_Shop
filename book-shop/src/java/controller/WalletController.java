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
import model.WalletDAO;
import model.WalletDTO;
import utils.AuthUtils;

/**
 *
 * @author trang
 */
@WebServlet(name = "WalletController", urlPatterns = {"/WalletController"})
public class WalletController extends HttpServlet {

    private WalletDAO wdao = new WalletDAO();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";

        try {
            String action = request.getParameter("action");
            if (action.equals("viewWallets")) {
                url = handleViewWallets(request, response);

            } else if (action.equals("searchWallet")) {
                url = handleSearchWallet(request, response);

            } else if (action.equals("editWallet")) {
                url = handleEditWallet(request, response);

            } else if (action.equals("updateWallet")) {
                url = handleUpdateWallet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String handleViewWallets(HttpServletRequest request, HttpServletResponse response) {
        if (AuthUtils.isAdmin(request)) {
            List<WalletDTO> listW = wdao.getAllWallets();
            request.setAttribute("list", listW);
        }
        return "manageWallets.jsp";
    }

    private String handleSearchWallet(HttpServletRequest request, HttpServletResponse response) {
        if (AuthUtils.isAdmin(request)) {
            String keyword = request.getParameter("keyword");
            WalletDTO oneW = wdao.getWalletByUserName(keyword);
            request.setAttribute("list", oneW);
            request.setAttribute("keyword", keyword);
        }
        return "manageWallets.jsp";
    }

    private String handleEditWallet(HttpServletRequest request, HttpServletResponse response) {
        if (AuthUtils.isAdmin(request)) {
            String uname = request.getParameter("userName");
            WalletDTO wallet = wdao.getWalletByUserName(uname);
            request.setAttribute("wallet", wallet);
            request.setAttribute("isEdit", true);
        }
        return "walletForm.jsp";
    }

    private String handleUpdateWallet(HttpServletRequest request, HttpServletResponse response) {
        if (AuthUtils.isAdmin(request)) {
            String checkError = "";
            String message = "";

            String userName = request.getParameter("userName");
            String balance = request.getParameter("balance");
            double balance_value = -1;

            try {
                balance_value = Double.parseDouble(balance);
                if (balance_value < 0) {
                    checkError += "Price must be greater than zero.<br/>";
                }
            } catch (Exception e) {
                checkError += "Invalid price format.<br/>";
            }

            WalletDTO wallet = new WalletDTO(userName, balance_value);

            if (checkError.isEmpty()) {
                if (wdao.updateBalance(wallet)) {
                    message = "Wallet updated successfully.";
                    request.setAttribute("wallet", wallet);
                    request.setAttribute("message", message);
                    request.setAttribute("isEdit", true);
                    return "walletForm.jsp";
                } else {
                    checkError = "Failed to update wallet to the database.";
                }
            }

            request.setAttribute("wallet", wallet);
            request.setAttribute("checkError", checkError);
            request.setAttribute("message", message);

            request.setAttribute("isEdit", true);
        }
        return "walletForm.jsp";
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
