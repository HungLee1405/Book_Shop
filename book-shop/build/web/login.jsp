<%-- 
    Document   : login
    Created on : May 29, 2025, 10:28:07 PM
    Author     : trang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="assets/css02/login.css">
        <jsp:include page = "components/link.jsp"></jsp:include>
        <jsp:include page = "components/header.jsp"></jsp:include>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <c:redirect url="index.jsp"/>
            </c:when>
            <c:otherwise>
    </head>

    <body>
        
                <h1>Login</h1>
                <section class = "login-form">
                    <div class = "container">
                        <form action="MainController" method="post">
                            <input type="hidden" name="action" value="login"/>
                            <div>
                                <label for="strUserName">Username</label>
                                <input type="text" name="strUserName" id="strUserName" />
                            </div>
                            <div>
                                <label for="strPassword">Password</label>
                                <input type="password" name="strPassword" id="strPassword" />
                            </div>
                            <div>
                                <input type="submit" value="Login"/>
                                <a href="registerForm.jsp" class="back-link">Register</a>
                            </div>
                        </form>
                        <c:if test="${not empty requestScope.message}">
                            <div>${requestScope.message}</div>
                        </c:if>
                    </div>
                </section>
            

    </body>
    
    <footer>
        </c:otherwise>
        </c:choose>
        <jsp:include page = "components/footer.jsp"></jsp:include>
    </footer>
</html>
