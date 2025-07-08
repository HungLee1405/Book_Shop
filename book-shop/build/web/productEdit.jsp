<%-- 
    Document   : productEdit
    Created on : Jun 12, 2025, 9:30:40 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="currentUser" value="${sessionScope.user}" />
<c:set var="isLoggedIn" value="${not empty currentUser}" />
<c:set var="isAdmin" value="${currentUser.roleID eq 'AD'}" />
<c:set var="keyword" value="${requestScope.keyword}" />

<c:set var="productList" value="${requestScope.list}" />
<c:set var="hasKeyword" value="${not empty keyword}" />
<c:set var="hasProducts" value="${not empty productList}" />
<c:set var="productCount" value="${fn:length(productList)}" />
<c:set var="keywordParam" value="${hasKeyword ? keyword : ''}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
        <link rel="stylesheet" href="assets/css02/productEdit.css"> 
    </head>
    <body>

        <c:choose>
            <c:when test="${not isLoggedIn}">
                <c:redirect url="login.jsp"/>
            </c:when>
            <c:otherwise>
                <div class="container">
                    <div class="header-section">
                        <h1>Welcome ${currentUser.fullName}!</h1>
                        <div>
                            <a href="MainController?action=mc">Back to home</a>
                            <a href="MainController?action=logout">Logout</a>
                        </div>
                    </div>

                    <div class="search-section">
                        <label>Search by name:</label>
                        <form action="ProductController" method="post">
                            <input type="hidden" name="action" value="searchProduct"/>
                            <input type="text" name="keyword" value="${keywordParam}" placeholder="Enter product name..."/>
                            <input type="submit" value="Search"/>
                        </form>
                    </div>

                    <c:if test="${isAdmin}">
                        <a href="productForm.jsp" class="add-product-link">Add New Product</a>
                    </c:if>

                    <c:choose>
                        <c:when test="${hasProducts and productCount == 0}">
                            <div>
                                No products have names that match the keyword!
                            </div>
                        </c:when>
                        <c:when test="${hasProducts and productCount > 0}">
                            <div class="table-container">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Price</th>
                                            <th>Category_id</th>
                                            <th>Author</th>
                                            <th>Status</th>
                                                <c:if test="${isAdmin}">
                                                <th>Action</th>
                                                </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${productList}">
                                            <tr>
                                                <td>${product.productId}</td>
                                                <td>${product.productName}</td>
                                                <td>${product.description}</td>
                                                <td>VND ${product.unitPrice}</td>
                                                <td>${product.category}</td>
                                                <td>${product.author}</td>
                                                <td class="status-inactive">
                                                    ${product.status ? 'Active' : 'Inactive'}
                                                </td>
                                                <c:if test="${isAdmin}">
                                                    <td>
                                                        <div class="action-buttons">
                                                            <form action="MainController" method="post">
                                                                <input type="hidden" name="action" value="editProduct"/>
                                                                <input type="hidden" name="productId" value="${product.productId}"/>
                                                                <input type="hidden" name="keyword" value="${keywordParam}" />
                                                                <input type="submit" value="Edit" class="edit-btn" />
                                                            </form>

                                                            <form action="MainController" method="post">
                                                                <input type="hidden" name="action" value="changeProductStatus"/>
                                                                <input type="hidden" name="productId" value="${product.productId}"/>
                                                                <input type="hidden" name="keyword" value="${keywordParam}" />
                                                                <input type="submit" value="Delete" class="delete-btn"
                                                                       onclick="return confirm('Are you sure you want to delete this product?')"/>
                                                            </form>
                                                        </div>
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </tbody>    
                                </table>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
