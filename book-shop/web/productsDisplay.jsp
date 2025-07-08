
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Displaying</title>
        <jsp:include page = "components/link.jsp"></jsp:include>
            <link rel="stylesheet" href="assets/css02/display.css"> 
        </head>

        <body data-bs-spy="scroll" data-bs-target="#header" tabindex="0">
        <jsp:include page = "components/header.jsp"></jsp:include>

            <section class="main-wrapper my-5">
                <!-- Left -->
                <div class="category-box">
                    <h5>Categories</h5>
                    <ul>
                        <li><a href="#">Foreign books</a></li>
                        <li><a href="#">Other Languages</a></li>
                        <li><a href="#">Children's Books</a></li>
                        <li><a href="#">Dictionaries & Languages</a></li>
                        <li><a href="#">Fiction</a></li>
                        <li><a href="#">Business, Finance & Management</a></li>
                        <li><a href="#">Personal Development</a></li>
                        <li><a href="#">Biography</a></li>
                        <li><a href="#">Society & Social Sciences</a></li>
                    </ul>
                </div>

                <!-- Right -->
                <div class="product-box">
                    <div class="row">
                    <c:if test="${not empty listP}">
                        <c:forEach var="p" items="${listP}">
                            <div class="col-md-3 mb-4">
                                <div class="product-item">
                                    <figure class="product-style">
                                        
                                        <a href="MainController?action=viewProduct&id=${p.productId}">
                                            <img src="${p.image}" alt="${p.productName}" class="product-item">
                                        </a>

                                        <form action="MainController" method="get">
                                            <input type="hidden" name="action" value="addToCart">
                                            <input type="hidden" name="id" value="${p.productId}">
                                            <input type="hidden" name="qty" value="1">
                                            <button type="submit" class="add-to-cart">Add to Cart</button>
                                        </form>
                                    </figure>
                                    <figcaption>
                                        <h3>${p.productName}</h3>
                                        <div class="item-price">
                                            <fmt:formatNumber value="${p.unitPrice}" type="number" minFractionDigits="0"/> đ
                                        </div>
                                    </figcaption>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty listP}">
                        <div class="col-md-12">
                            <div class="alert alert-info">No products available at this time.</div>
                        </div>
                    </c:if>
                </div>

                <!-- Pagination (optional static) -->
                <div class="row">
                    <div class="col-md-12">
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center mt-4">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                                </li>
                                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="components/footer.jsp" />
    </body>
</html>