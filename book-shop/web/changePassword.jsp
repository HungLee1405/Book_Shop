<%-- 
    Document   : changePassword
    Created on : Jul 13, 2025, 11:51:58 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <jsp:include page = "components/link.jsp"></jsp:include>
        <link rel="stylesheet" href="assets/css02/changePassword.css"/>
        
    </head>

    <body>
         <jsp:include page = "components/header.jsp"></jsp:include>
            <div class="change-password-container">
                <h2>Change Password</h2>

            <c:if test="${not empty checkError}">
                <div class="error-message">${checkError}</div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="success-message">${message}</div>
            </c:if>

            <form action="MainController" method="post">
                <input type="hidden" name="action" value="changePassword"/>

                <div class="form-group">
                    <label for="oldPassword">Current Password</label>
                    <input type="password" id="oldPassword" name="oldPassword" required/>
                </div>

                <div class="form-group">
                    <label for="newPassword">New Password</label>
                    <input type="password" id="newPassword" name="newPassword" required/>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Confirm New Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required/>
                </div>

                <div class="button-group">
                    <button type="submit">Change Password</button>
                </div>
            </form>
        </div>
    </body>
</html>
