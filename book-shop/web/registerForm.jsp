<%-- 
    Document   : user-account
    Created on : Jul 8, 2025, 8:48:11 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="checkError" value="${requestScope.checkError}" />
<c:set var="message" value="${requestScope.message}" />
<c:set var="user" value="${requestScope.user}" />
<c:set var="keyword" value="${requestScope.keyword}" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link href="assets/css02/registerForm.css" rel="stylesheet" type="text/css"/>
        <script>
            function togglePassword() {
                const passwordInput = document.getElementById("password");
                const toggleIcon = document.querySelector(".toggle-password");

                if (passwordInput.type === "password") {
                    passwordInput.type = "text";
                    toggleIcon.textContent = "🙈"; // You can change to a "hide" icon
                } else {
                    passwordInput.type = "password";
                    toggleIcon.textContent = "👁️";
                }
            }
        </script>
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <a href="index.jsp" class="home-icon" title="Back to Home">
                    <i class="fas fa-home"></i>
                </a>

                <div class="container">
                    <!-- Left Panel -->
                    <div class="left-panel">

                    </div>

                    <!-- Right Panel -->
                    <div class="right-panel">
                        <form action="MainController" method="post">
                            <h2>Become a Member</h2>
                            <input type="hidden" name="action" value="register"/>
                            <input type="hidden" name="keyword" value="${keyword}" />

                            <div class="form-group">
                                <label for="userName">Username *</label>
                                <input type="text" id="userName" name="userName" required value="${user.userName}" />
                                <i class="fa fa-user"></i>
                            </div>

                            <div class="form-group">
                                <label for="fullName">Full Name *</label>
                                <input type="text" id="fullName" name="fullName" required value="${user.fullName}" />
                                <i class="fa fa-id-card"></i>
                            </div>

                            <div class="form-group">
                                <label for="password">Password *</label>
                                <input type="password" id="password" name="password" required/>
                                <i class="fa fa-lock"></i>
                                <!-- Toggle button -->
                                <span class="toggle-password" onclick="togglePassword()">👁️</span>
                            </div>

                            <div class="form-group">
                                <label for="confirmPassword">Confirm Password *</label>
                                <input type="password" id="confirmPassword" name="confirmPassword" required/>
                                <i class="fa fa-lock-open"></i>
                            </div>

                            <div class="form-group">
                                <label for="email">Email *</label>
                                <input type="email" id="email" name="email" required value="${user.email}" />
                                <i class="fa fa-envelope"></i>
                            </div>

                            <div class="form-group">
                                <label for="birthDay">Birthday</label>
                                <input type="date" id="birthDay" name="birthDay" value="${user.birthDay}" />
                                <i class="fa fa-calendar"></i>
                            </div>

                            <div class="form-group">
                                <label for="address">Address</label>
                                <input type="text" id="address" name="address" value="${user.address}" />
                                <i class="fa fa-location-dot"></i>
                            </div>

                            <div class="form-group">
                                <label for="phone">Phone</label>
                                <input type="text" id="phone" name="phone" value="${user.phone}" />
                                <i class="fa fa-phone"></i>
                            </div>

                            <input type="submit" value="Join Now"/>
                            <input type="reset" value="Reset"/>

                            <a href="login.jsp">Already have an account? Log in</a>

                            <c:if test="${not empty checkError}">
                                <div class="error-message">${checkError}</div>
                            </c:if>
                            <c:if test="${empty checkError && not empty message}">
                                <div class="success-message">${message}</div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </body>
</html>
