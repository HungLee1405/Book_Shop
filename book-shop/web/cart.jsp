<%-- 
    Document   : cart
    Created on : Jul 3, 2025, 2:09:18 PM
    Author     : trang
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your Cart</title>
    <jsp:include page="components/link.jsp"/>
</head>

<body>
<jsp:include page="components/header.jsp"/>

<div class="container my-5">
    <h2 class="mb-4">🛒 Your Shopping Cart</h2>

    <c:choose>
        <c:when test="${not empty cartItems}">
            <table class="table table-bordered text-center align-middle">
                <thead class="table-light">
                    <tr>
                        <th>Image</th>
                        <th>Book Title</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="totalAmount" value="0" />
                    <c:forEach var="item" items="${cartItems}">
                        <c:set var="unitPrice" value="${item.product.getSalePrice()}" />
                        <c:set var="lineTotal" value="${unitPrice * item.quantity}" />
                        <c:set var="totalAmount" value="${totalAmount + lineTotal}" />

                        <tr>
                            <td><img src="${item.product.image}" style="width: 80px;" alt="${item.product.productName}" /></td>
                            <td>${item.product.productName}</td>
                            <td><fmt:formatNumber value="${unitPrice}" type="number" maxFractionDigits="2"/> đ</td>

                            <!-- Quantity Update Form -->
                            <td>
                                <form action="CartController" method="post" class="d-flex justify-content-center align-items-center">
                                    <input type="hidden" name="action" value="updateQuantity" />
                                    <input type="hidden" name="id" value="${item.product.productId}" />
                                    <input type="number" name="qty" value="${item.quantity}" min="1" class="form-control form-control-sm me-2" style="width: 60px;" />
                                    <button type="submit" class="btn btn-sm btn-primary">Update</button>
                                </form>
                            </td>

                            <td><fmt:formatNumber value="${lineTotal}" type="number" maxFractionDigits="2"/> đ</td>

                            <!-- Remove Item Form -->
                            <td>
                                <form action="CartController" method="post" class="d-inline">
                                    <input type="hidden" name="action" value="removeCart" />
                                    <input type="hidden" name="id" value="${item.product.productId}" />
                                    <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr class="table-warning fw-bold">
                        <td colspan="4" class="text-end">Total:</td>
                        <td colspan="2">
                            <fmt:formatNumber value="${totalAmount}" type="number" maxFractionDigits="2"/> đ
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="text-end">
                <a href="MainController?action=checkOut" class="btn btn-success">Proceed to Checkout</a>
                <a href="MainController?action=viewAllProducts" class="btn btn-secondary">Continue Shopping</a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="alert alert-info text-center">
                🛒 Your cart is currently empty.
            </div>
            <div class="text-center">
                <a href="MainController?action=viewAllProducts" class="btn btn-primary">Browse Books</a>
            </div>
        </c:otherwise>
        
    </c:choose>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
