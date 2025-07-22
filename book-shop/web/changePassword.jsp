<%-- 
    Document   : changePassword
    Created on : Jul 13, 2025, 11:51:58 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Redirect to login if not logged in --%>
<c:set var="currentUser" value="${sessionScope.user}" />
<c:set var="isLoggedIn" value="${not empty currentUser}" />
<c:if test="${not isLoggedIn}">
    <c:redirect url="login.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Change Password</title>
        <link rel="stylesheet" href="assets/css02/changePassword.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <script>
            function togglePassword(inputId, icon) {
                const input = document.getElementById(inputId);
                if (input.type === "password") {
                    input.type = "text";
                    icon.textContent = "🙈"; // hoặc dùng icon khác
                } else {
                    input.type = "password";
                    icon.textContent = "👁️";
                }
            }
        </script>
    </head>
    <body>

        <a href="index.jsp" class="home-icon" title="Back to Home">
            <i class="fas fa-home"></i>
        </a>

        <div class="main-container">
            <div class="image-side"></div>

            <div class="form-side">
                <div class="change-password-container">
                    <h2>Update Password</h2>

                    <c:if test="${not empty checkError}">
                        <div class="error-message">${checkError}</div>
                    </c:if>

                    <c:if test="${not empty message}">
                        <div class="success-message">${message}</div>
                    </c:if>

                    <form action="MainController" method="post">
                        <input type="hidden" name="action" value="password"/>

                        <div class="form-group">
                            <label for="oldPassword">Current Password</label>
                            
                            <div class="input-wrapper">
                                <input type="password" id="oldPassword" name="oldPassword" required />
                                <span class="toggle-password" onclick="togglePassword('oldPassword', this)">👁️</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="newPassword">New Password</label>
                            <div class="input-wrapper">
                            <input type="password" id="newPassword" name="newPassword" required/>
                            <!-- Toggle button -->
                            <span class="toggle-password" onclick="togglePassword('newPassword', this)">👁️</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword">Confirm New Password</label>
                            <div class="input-wrapper">
                            <input type="password" id="confirmPassword" name="confirmPassword" required/>
                            </div>
                        </div>

                        <div class="button-group">
                            <button type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
