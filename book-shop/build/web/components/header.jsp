<%-- 
    Document   : topContent
    Created on : Jun 22, 2025, 1:05:57 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="currentUser" value="${sessionScope.user}" />
<c:set var="isAdmin" value="${currentUser.roleID eq 'AD'}" />

<div id="header-wrap">

    <!-- 1st HEAD -->
    <div class="top-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <div class="social-links">
                        <ul>
                            <li>
                                <a href="#"><i class="icon icon-facebook"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="icon icon-twitter"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="icon icon-youtube-play"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="icon icon-behance-square"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>              
                <div class="col-md-6">
                    <div class="right-element">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <a href="userForm.jsp" class="user-account for-buy">  
                                    <i class="icon icon-user"></i><span>${sessionScope.user.fullName}</span>
                                </a>
                                <a href="MainController?action=logout" class="logout for-buy">
                                    <span style="color: #ff6666;">Logout</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="login.jsp" class="user-account for-buy">
                                    <i class="icon icon-user"></i><span>Login</span>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <a href="MainController?action=viewCart" class="cart for-buy"><i class="icon icon-clipboard"></i><span>Cart</span></a>

                        <!-- SEARCH -->
                        <div class="action-menu">
                            <div class="search-bar">
                                <a href="#" class="search-button search-toggle" data-selector="#header-wrap">
                                    <i class="icon icon-search"></i>
                                </a>
                                <form role="search" method="get" class="search-box">
                                    <input class="search-field text search-input" placeholder="Search" type="search">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 2nd HEAD -->
    <header id="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <div class="main-logo">
                        <a href="MainController"><img src="assets/images/main-logo.png" alt="logo"></a>
                    </div>
                </div>

                <div class="col-md-10">
                    <nav id="navbar">
                        <div class="main-menu stellarnav">
                            <ul class="menu-list">

                                <c:if test="${isAdmin}">
                                    <li class="menu-item has-sub">
                                        <a href="#pages" class="nav-link">Admin</a>
                                        <ul>                                       
                                            <li><a href="productEdit.jsp">Products</a></li>                                        
                                        </ul>
                                    </li>
                                </c:if>

                                <li class="menu-item"><a href="MainController" class="nav-link">Home</a></li>
                                <li class="menu-item has-sub">
                                    <a href="#categories" class="nav-link">Categories</a>
                                    <ul>
                                        <c:if test="${not empty listC}">
                                            <c:forEach var="c" items="${listC}">
                                                <li>
                                                    <a href="MainController?action=viewCat&catID=${c.categoryId}">
                                                        ${c.categoryName}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </li>
                                <li class="menu-item"><a href="MainController?action=viewAllProducts" class="nav-link">View All Products</a></li>
                            </ul>

                            <div class="hamburger">
                                <span class="bar"></span>
                                <span class="bar"></span>
                                <span class="bar"></span>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
</div>

