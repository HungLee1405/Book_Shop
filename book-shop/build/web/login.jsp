<%-- 
    Document   : login
    Created on : May 29, 2025, 10:28:07 PM
    Author     : trang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <title>Login</title>
    <link href="assets/login/login.css" rel="stylesheet" type="text/css"/>
    <script>
        function togglePassword() {
            const passwordInput = document.getElementById("strPassword");
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
</header>

<body>
    <section class="login-container">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <c:redirect url="index.jsp"/>
            </c:when>

            <c:otherwise>
                <div class="login-wrapper">
                    <!-- Navigation Links -->
                    <div class="nav-links">
                        <a href="#" class="nav-link active">Log In</a>
                        <span style="color: #ccc;">|</span>
                        <a href="index.jsp" class="nav-link">Home</a>
                    </div>

                    <!-- Login Form Section -->
                    <div class="login-form-section">
                        <!-- Brand Logo -->
                        <div class="brand-logo">
                            <div class="book-icon"></div>
                        </div>

                        <!-- Title -->
                        <h1 class="login-title">Log In</h1>

                        <!-- Login Form -->
                        <form action="MainController" method="post">
                            <input type="hidden" name="action" value="login"/>

                            <div class="form-group">
                                <span class="input-icon">👤</span>
                                <input type="text" 
                                       name="strUserName" 
                                       id="strUserName" 
                                       class="form-input"
                                       placeholder="user1"
                                       required />

                            </div>

                            <div class="form-group">
                                <span class="input-icon">🔒</span>
                                <input type="password" 
                                       name="strPassword" 
                                       id="strPassword" 
                                       class="form-input"
                                       placeholder="password123"
                                       required />


                                <!-- Toggle button -->
                                <span class="toggle-password" onclick="togglePassword()">
                                    👁️
                                </span>
                            </div>



                            <button type="submit" class="login-button">Log in</button>
                        </form>

                        <!-- Error Message -->
                        <c:if test="${not empty requestScope.message}">
                            <div class="error-message">${requestScope.message}</div>
                        </c:if>

                        <!-- Register Link -->
                        <div class="register-link">
                            Don't have an account? <a href="registerForm.jsp">Sign up here</a>
                        </div>
                    </div>

                    <!-- Image Section -->
                    <div class="login-image-section">
                        <div class="knowledge-text">
                            <h2>Knowledge</h2>
                            <p>Discover amazing books</p>
                        </div>
                        <div class="book-visual">
                            <div class="book-stack">
                                <div class="book">
                                    <div class="book-spine"></div>
                                    <div class="book-pages"></div>
                                </div>
                                <div class="book">
                                    <div class="book-spine"></div>
                                    <div class="book-pages"></div>
                                </div>
                                <div class="book">
                                    <div class="book-spine"></div>
                                    <div class="book-pages"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</body>