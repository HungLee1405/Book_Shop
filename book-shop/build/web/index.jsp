<%-- 
    Document   : index
    Created on : May 29, 2025, 10:21:32 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="isAdmin" value="${currentUser.roleID eq 'AD'}" />


<!DOCTYPE html>
<html lang="en">

    <head>
        <title>BookSaw</title>
        <jsp:include page = "components/link.jsp"></jsp:include>
            <link rel="stylesheet" href="assets/css02/index.css">
        </head>

        <body data-bs-spy="scroll" data-bs-target="#header" tabindex="0">
        <jsp:include page = "components/header.jsp"></jsp:include>

            <!-- Banner -->
            <section id="promo-banner">
                <div class="banner-wrapper">
                    <img src="assets/images/Blue Green Summer Super Sale Banner.png"
                         alt="Big Sale Banner"
                         class="banner-image">
                </div>
            </section>


            <!-- New Arrivals -->
            <section id="featured-books" class="py-5 my-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">

                            <div class="section-header align-center">
                                <div class="title"><span>Some quality items</span></div>
                                <h2 class="section-title">New Arrivals</h2>
                            </div>

                            <div class="product-list" data-aos="fade-up">
                                <div class="row" id="product-container">
                                <c:choose>
                                    <c:when test="${not empty listP}">
                                        <c:forEach var="p" items="${listP}">
                                            <div class="col-md-3">
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
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-12">
                                            <p>No featured products available.</p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-md-12">
                        <div class="btn-wrap align-right">

                            <a id="loadMoreBtn" href="javascript:void(0)" class="btn-accent-arrow">
                                View More <i class="icon icon-ns-arrow-right"></i>
                            </a>
>
                            <a id="viewAllBtn" href="MainController?action=viewAllProducts" class="btn-accent-arrow" style="display: none;">
                                View all products <i class="icon icon-ns-arrow-right"></i>
                            </a>

                        </div>
                    </div>
                </div>

            </div>
        </section>


        <!-- Offer -->
        <section id="special-offer" class="bookshelf pb-5 mb-5">
            <div class="section-header align-center">
                <div class="title">
                    <span>Grab your opportunity</span>
                </div>
                <h2 class="section-title">Books with offer</h2>
            </div>

            <div class="container">
                <div class="row">
                    <div class="inner-content">
                        <div class="product-list" data-aos="fade-up">
                            <div class="grid product-grid">
                                <div class="product-item">
                                    <figure class="product-style">
                                        <img src="assets/images/product-item5.jpg" alt="Books" class="product-item">
                                        <button type="button" class="add-to-cart" data-product-tile="add-to-cart">Add to
                                            Cart</button>
                                    </figure>
                                    <figcaption>
                                        <h3>Simple way of piece life</h3>
                                        <span>Armor Ramsey</span>
                                        <div class="item-price">
                                            <span class="prev-price">$ 50.00</span>$ 40.00
                                        </div>
                                    </figcaption>
                                </div>


                                <div class="product-item">
                                    <figure class="product-style">
                                        <img src="assets/images/product-item6.jpg" alt="Books" class="product-item">
                                        <button type="button" class="add-to-cart" data-product-tile="add-to-cart">Add to
                                            Cart</button>
                                    </figure>
                                    <figcaption>
                                        <h3>Great travel at desert</h3>
                                        <span>Sanchit Howdy</span>
                                        <div class="item-price">
                                            <span class="prev-price">$ 30.00</span>$ 38.00
                                        </div>
                                    </figcaption>
                                </div>

                                <div class="product-item">
                                    <figure class="product-style">
                                        <img src="assets/images/product-item7.jpg" alt="Books" class="product-item">
                                        <button type="button" class="add-to-cart" data-product-tile="add-to-cart">Add to
                                            Cart</button>
                                    </figure>
                                    <h3>The lady beauty Scarlett</h3>
                                    <span>Arthur Doyle</span>
                                    <div class="item-price">
                                        <span class="prev-price">$ 35.00</span>$ 45.00
                                    </div>
                                </div>
                                </figcaption>

                                <div class="product-item">
                                    <figure class="product-style">
                                        <img src="assets/images/product-item8.jpg" alt="Books" class="product-item">
                                        <button type="button" class="add-to-cart" data-product-tile="add-to-cart">Add to
                                            Cart</button>
                                    </figure>
                                    <h3>Once upon a time</h3>
                                    <span>Klien Marry</span>
                                    <div class="item-price">
                                        <span class="prev-price">$ 25.00</span>$ 35.00
                                    </div>
                                </div>

                                <div class="product-item">
                                    <figure class="product-style">
                                        <img src="assets/images/product-item2.jpg" alt="Books" class="product-item">
                                        <button type="button" class="add-to-cart" data-product-tile="add-to-cart">Add to
                                            Cart</button>
                                    </figure>
                                    <figcaption>
                                        <h3>Simple way of piece life</h3>
                                        <span>Armor Ramsey</span>
                                        <div class="item-price">$ 40.00</div>
                                    </figcaption>
                                </div>
                            </div><!--grid-->
                        </div>
                    </div><!--inner-content-->
                </div>
            </div>
        </section>
        <jsp:include page = "components/footer.jsp"></jsp:include>
    </body>
    <script>
        let loadCount = 0;

        function getLastProductId() {
            const inputs = document.querySelectorAll('#product-container input[name="id"]');
            if (inputs.length === 0)
                return 0;
            return inputs[inputs.length - 1].value;
        }

        document.getElementById("loadMoreBtn").addEventListener("click", function () {
            const lastId = getLastProductId();

            fetch("loadMore?lastId=" + lastId)
                    .then(response => response.text())
                    .then(html => {
                        const container = document.getElementById("product-container");
                        container.insertAdjacentHTML("beforeend", html);

                        loadCount++;
                        if (loadCount >= 2) {
                            document.getElementById("loadMoreBtn").style.display = "none";
                            document.getElementById("viewAllBtn").style.display = "inline-block";
                        }
                    })
                    .catch(err => console.error("Load more failed:", err));
        });
    </script>
</html>