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
import utils.AuthUtils;
import utils.PasswordUtlis;

/**
 *
 * @author trang
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    
    UserDAO udao = new UserDAO();

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
        String email = request.getParameter("email");
        String birthDay = request.getParameter("birthDay");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        
        Date BirthDay = null;
        try {
            if (birthDay != null && !birthDay.isEmpty()) {
                BirthDay = Date.valueOf(birthDay); // yyyy-MM-dd

                // ✅ Check if date is in the future
                Date today = new Date(System.currentTimeMillis());
                if (BirthDay.after(today)) {
                    checkError += "<br/> Birth Day must be in the past.";
                }
            }
        } catch (Exception e) {
            checkError += "<br/> Invalid Birth Day.";
        }

        if (checkError.isEmpty()) {
            message = "Add product successfully.";
        }

        UserDTO user = new UserDTO(userName, fullName, password, phone, email, BirthDay, address, phone, true);
        if (!udao.create(user)) {
            checkError += "<br/>Can not add new product.";
        }

        request.setAttribute("user", user);
        request.setAttribute("checkError", checkError);
        request.setAttribute("message", message);
        return "userForm.jsp";
    }

    private String handleUpdateProfile(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        String keyword = request.getParameter("keyword");

        int id_value = Integer.parseInt(productId);

            UserDTO user = udao.getUserByUserName(keyword);
            if (user != null) {
                request.setAttribute("keyword", keyword);
                request.setAttribute("user", user);
                request.setAttribute("isUpdate", true);
                return "userForm.jsp";
            
        }
        return handleRegister(request, response);
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
