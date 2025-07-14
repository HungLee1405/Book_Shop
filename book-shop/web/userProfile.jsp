<%-- 
    Document   : userProfile
    Created on : Jul 13, 2025, 8:33:21 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <jsp:include page = "components/link.jsp"></jsp:include>
            <link rel="stylesheet" href="assets/css02/userProfile.css"/>
        <jsp:include page = "components/header.jsp"></jsp:include>
        </head>

        <body>
        
            <div class="profile-container">
                <div class="left-column">
                    <!-- Avatar -->
                    <div class="avatar-section">
                        <h3>Avatar</h3>
                        <img src="https://phanmemmkt.vn/wp-content/uploads/2024/09/Hinh-anh-dai-dien-mac-dinh-Facebook.jpg" alt="Avatar" class="avatar-img"/>
                    </div>

                    <!-- Wallet -->
                    <div class="wallet-section">
                        <h3>Shopping wallet</h3>
                        <div class="wallet-box">
                            Balance amount: <strong>${wallet.balance} Vnd</strong>
                    </div>
                </div>
            </div>

            <div class="right-column">
                <!-- Profile Form -->
                <div class="form-section">
                    <h2>Your Profile</h2>
                    <form action="MainController" method="post">
                        <input type="hidden" name="action" value="updateProfile"/>

                        <label>Username</label>
                        <input type="text" name="userName" value="${user.userName}" readonly/>

                        <label>Full name</label>
                        <input type="text" name="fullName" value="${user.fullName}" required/>

                        <label>Address</label>
                        <input type="text" name="address" value="${user.address}" />

                        <label>Email address</label>
                        <input type="email" name="email" value="${user.email}" required/>

                        <label>Phone number</label>
                        <input type="text" name="phone" value="${user.phone}" required/>

                        <label>Birthdate</label>
                        <input type="date" name="birthDay" value="${user.birthDay}" />

                        <button type="submit">Save</button>
                    </form>
                </div>

                <c:if test="${not empty checkError}">
                    <div class="error-message">${checkError}</div>
                </c:if>
                <c:if test="${not empty message}">
                    <div class="success-message">${message}</div>
                </c:if>
            </div>

        </div>
    </body>
</html>
