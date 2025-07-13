<%-- 
    Document   : manageWallets
    Created on : Jul 12, 2025, 11:51:45 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="currentUser" value="${sessionScope.user}" />
<c:set var="isLoggedIn" value="${not empty currentUser}" />
<c:set var="isAdmin" value="${currentUser.roleID eq 'AD'}" />
<c:set var="keyword" value="${requestScope.keyword}" />

<c:set var="walletList" value="${requestScope.list}" />
<c:set var="hasKeyword" value="${not empty keyword}" />
<c:set var="hasWallets" value="${not empty walletList}" />
<c:set var="walletCount" value="${fn:length(walletList)}" />
<c:set var="keywordParam" value="${hasKeyword ? keyword : ''}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wallet Management</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${isLoggedIn and isAdmin}">
                <div class="content-header">
                    <h1 class="page-title">Manage Wallets</h1>
                    <div class="header-controls">
                        <div class="search-container">
                            <form data-controller="WalletController" method="post"
                                  data-success-action="viewWallets" style="display: flex; align-items: center; gap: 10px;">
                                <input type="hidden" name="action" value="searchWallet"/>
                                <input type="text" name="keyword" value="${keyword}" 
                                       placeholder="Search wallet name" class="search-input"/>
                                <button type="submit" class="search-btn"></button>
                            </form>

                        </div>
                    </div>
                </div>


                <div class="main-content">
                    <c:choose>
                        <c:when test="${hasWallets and walletCount == 0}">
                            <div>
                                No wallets have names that match the keyword!
                            </div>
                        </c:when>
                        <c:when test="${hasWallets and walletCount > 0}">
                            <div class="table-container">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>User Name</th>
                                            <th>Balance (VNĐ)</th>
                                            <th>Action</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="w" items="${walletList}">
                                            <tr>                                            
                                                <td>${w.userName}</td>
                                                <td>VND ${w.balance}</td>

                                                <td>
                                                    <div class="action-buttons">
                                                        <!-- Form Edit -->
                                                        <form class="ajax-action-form" data-controller="WalletController" data-success-action="viewWallets" method="post">
                                                            <input type="hidden" name="action" value="editWallet" />
                                                            <input type="hidden" name="userName" value="${w.userName}" />
                                                            <input type="hidden" name="keyword" value="${keyword}" />
                                                            <input type="submit" value="Edit" class="edit-btn" />
                                                        </form>                  
                                                    </div>
                                                </td>   
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:when>
                    </c:choose>
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
    </body>
</html>
