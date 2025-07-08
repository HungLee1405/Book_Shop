<%-- 
    Document   : productDetail
    Created on : Jun 21, 2025, 11:24:14 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Detail</title>
        <jsp:include page = "components/link.jsp"></jsp:include>
        <link rel="stylesheet" href="assets/css02/detail.css"> 
    </head>

    <body data-bs-spy="scroll" data-bs-target="#header" tabindex="0">

        <jsp:include page = "components/header.jsp"></jsp:include>

            <section class="product-detail-container py-4">
                <div class="container">
                    <div class="row">

                        <!-- Image -->
                        <div class="col-md-5 d-flex flex-column align-items-center">
                            <div class="card p-3 w-100 mb-3 text-center">
                                <div style="width: 214px; height: 320px; margin: 0 auto;">
                                    <img src="${p.image}" alt="${p.productName}" class="img-fluid h-100 w-100 object-fit-cover rounded">
                            </div>
                        </div>

                        <div class="d-flex justify-content-center gap-3 w-100">                          
                            <form action="MainController" method="get">
                                <input type="hidden" name="action" value="addToCart">
                                <input type="hidden" name="id" value="${p.productId}">
                                <input type="hidden" name="qty" value="1">
                                <button class="btn btn-success w-100">ADD TO CART</button>
                            </form>

                        </div>
                    </div>

                    <!-- Info -->
                    <div class="col-md-7 d-flex flex-column gap-3">
                        <div class="card p-3">
                            <h2 class="product-title mb-2">${p.productName}</h2>
                            <h4 class="product-price text-danger">
                                <fmt:formatNumber value="${p.unitPrice}" type="number" minFractionDigits="0"/> đ
                            </h4>
                        </div>

                        <div class="card p-3">
                            <h5 class="mb-2">Quantity:</h5>
                            <input type="number" name="quantity" class="form-control" value="1" min="1" style="width: 120px;">
                        </div>

                        <div class="card p-3">
                            <h5>Info</h5>
                            <table class="table table-bordered table-sm mt-3">
                                <tbody>
                                    <tr><th scope ="row">Id</th><td>${p.productId}</td></tr>
                                    <tr><th scope="row">Supplier</th><td>IPM</td></tr>
                                    <tr><th scope="row">Author</th><td>${p.author}</td></tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="card p-3">
                            <h5>Description:</h5>
                            <p>${p.description}</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page = "components/footer.jsp"></jsp:include>
    </body>
</html>