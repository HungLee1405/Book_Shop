<%-- 
    Document   : productForm
    Created on : Jun 9, 2025, 9:08:33 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="checkError" value="${requestScope.checkError}" />
<c:set var="message" value="${requestScope.message}" />
<c:set var="product" value="${requestScope.product}" />
<c:set var="isEdit" value="${requestScope.isEdit}" />
<c:set var="keyword" value="${requestScope.keyword}" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Form</title>
        <link rel="stylesheet" href="assets/css02/productForm.css"> 
    </head>
    
    <body>
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.roleID == 'AD'}">

                    <div class="header">
                        <a href="productEdit.jsp" class="back-link">← Back to Products</a>
                        <h1>${isEdit ? "EDIT PRODUCT" : "ADD PRODUCT"}</h1>
                    </div>

                    <div class="form-container">
                        <form action="MainController" method="post">
                            <input type="hidden" name="action" value="${isEdit ? 'updateProduct' : 'addProduct'}"/>

                            <c:if test="${isEdit}">
                                <div class="form-group">
                                    <label for="id">ID <span class="required">*</span></label>
                                    <input type="text" id="id" name="id" value="${product.id}" readonly />
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label for="name">Name <span class="required">*</span></label>
                                <input type="text" id="name" name="name" required value="${product.name}" />
                            </div>

                            <div class="form-group">
                                <label for="image">Image URL</label>
                                <input type="text" id="image" name="image" value="${product.image}" />
                            </div>

                            <div class="form-group">
                                <label for="description">Description</label>
                                <textarea id="description" name="description"
                                          placeholder="Enter product description...">${product.description}</textarea>
                            </div>

                            <div class="form-group">
                                <label for="price">Price <span class="required">*</span></label>
                                <input type="number" id="price" name="price" required min="0" step="0.01"
                                       placeholder="0.00" value="${product.price}" />
                            </div>

                            <div class="form-group">
                                <label for="catID">Category_id</label>
                                <input type="text" id="catID" name="catID" value="${product.catID}" />
                            </div>

                            <div class="form-group">
                                <label for="name">Author <span class="required">*</span></label>
                                <input type="text" id="author" name="author" required value="${product.author}" />
                            </div>

                            <div class="checkbox-group">
                                <input type="checkbox" id="status" name="status" value="true"
                                <c:if test="${product.status}">checked</c:if> />
                                <label for="status">Active Product</label>
                            </div>

                            <div class="button-group">
                                <input type="hidden" name="keyword" value="${keyword}" />
                                <input type="submit" value="${isEdit ? 'Update Product' : 'Add Product'}"/>
                                <input type="reset" value="Reset"/>
                            </div>
                        </form>

                        <c:if test="${not empty checkError}">
                            <div class="error-message">${checkError}</div>
                        </c:if>
                        <c:if test="${empty checkError && not empty message}">
                            <div class="success-message">${message}</div>
                        </c:if>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="header">
                        <h1>ACCESS DENIED</h1>
                    </div>
                    <div class="access-denied">
                        🚫 You do not have permission to access this page.
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>
