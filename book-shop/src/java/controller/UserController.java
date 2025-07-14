/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.UserDAO;
import model.UserDTO;
import model.WalletDAO;
import model.WalletDTO;
import utils.AuthUtils;
import utils.PasswordUtlis;

/**
 *
 * @author trang
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    UserDAO udao = new UserDAO();
    WalletDAO wdao = new WalletDAO();

    private static final String WELCOME_PAGE = "login.jsp";
    private static final String LOGIN_PAGE = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");
            if ("login".equals(action)) {
                url = handleLogin(request, response);
            } else if ("logout".equals(action)) {
                url = handleLogout(request, response);
            } else if ("register".equals(action)) {
                url = handleRegister(request, response);
            } else if ("updateProfile".equals(action)) {
                url = handleUpdateProfile(request, response);
            } else if ("changePassword".equals(action)) {
                url = handlePasswordChanging(request, response);
            } else if ("viewProfile".equals(action)){
                url = handleProfileViewing(request, response);
            } else {
                request.setAttribute("message", "Invalid action: " + action);
                url = LOGIN_PAGE;
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String handleLogin(HttpServletRequest request, HttpServletResponse response) {

        String url = LOGIN_PAGE;

        HttpSession session = request.getSession();
        String username = request.getParameter("strUserName");
        String password = request.getParameter("strPassword");
        //password = PasswordUtlis.encryptSHA256(password);
        UserDAO userDAO = new UserDAO();

        if (userDAO.login(username, password)) {
            // Dang nhap thanh cong
            url = "index.jsp";
            UserDTO user = userDAO.getUserByUserName(username);
            session.setAttribute("user", user);
        } else {
            // Dang nhap that bai
            url = "login.jsp";
            request.setAttribute("message", "UserName or Password incorrect!");
        }
        return url;
    }

    private String handleLogout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (session != null) {
            // get session info
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user != null) {
                // cancle all session
                session.invalidate();
            }
        }
        return LOGIN_PAGE;
    }

    private String handleRegister(HttpServletRequest request, HttpServletResponse response) {
        String checkError = "";
        String message = "";
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String birthDay = request.getParameter("birthDay");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        if (userName == null || userName.trim().isEmpty()) {
            checkError += "<br/>Username is required.";
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            checkError += "<br/>Full Name is required.";
        }

        if (password == null || password.trim().isEmpty()) {
            checkError += "<br/>Password is required.";
        }
        
        if (!password.equals(confirmPass)) {
            checkError = "password and confirmation do not match.";
        }

        Date BirthDay = null;
        try {
            if (birthDay != null && !birthDay.isEmpty()) {
                BirthDay = Date.valueOf(birthDay);

                Date today = new Date(System.currentTimeMillis());
                if (BirthDay.after(today)) {
                    checkError += "<br/> Birth Day must be in the past.";
                }
            }
        } catch (Exception e) {
            checkError += "<br/> Invalid Birth Day.";
        }

        UserDTO user = new UserDTO(userName, fullName, password, phone, email, BirthDay, address, phone, true);

        if (checkError.isEmpty()) {
            if (udao.create(user)) {
                message = "Create Account successfully.";
            } else {
                checkError += "Cannot Create Account.";
            }
        }

        request.setAttribute("user", user);
        request.setAttribute("checkError", checkError);
        request.setAttribute("message", message);

        return "registerForm.jsp";
    }

    private String handleUpdateProfile(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthDayStr = request.getParameter("birthDay");

        Date birthDay = null;
        if (birthDayStr != null && !birthDayStr.isEmpty()) {
            try {
                birthDay = Date.valueOf(birthDayStr);
            } catch (Exception e) {
                request.setAttribute("checkError", "Invalid birthdate.");
                return "userProfile.jsp";
            }
        }

        UserDTO user = new UserDTO(userName, fullName, "", phone, email, birthDay, address, phone, true);
        boolean success = udao.updateUser(user);
        if (success) {
            request.getSession().setAttribute("user", user); // cập nhật session
            request.setAttribute("message", "Profile updated successfully.");
        } else {
            request.setAttribute("checkError", "Update failed.");
        }

        return "userProfile.jsp";
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

    private String handlePasswordChanging(HttpServletRequest request, HttpServletResponse response) {
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        String checkError = "";
        String message = "";

        if (!newPass.equals(confirmPass)) {
            checkError = "New password and confirmation do not match.";
        } else if (!udao.checkPassword(user.getUserName(), oldPass)) {
            checkError = "Current password is incorrect.";
        } else {
            boolean success = udao.updatePassword(user.getUserName(), newPass);
            if (success) {
                message = "Password updated successfully!";
            } else {
                checkError = "Failed to update password.";
            }
        }

        request.setAttribute("checkError", checkError);
        request.setAttribute("message", message);
        return "changePassword.jsp";
    }

    private String handleProfileViewing(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        WalletDTO wallet = wdao.getWalletByUserName(userName);
        request.setAttribute("wallet", wallet);
        return "userProfile.jsp";
    }

}
