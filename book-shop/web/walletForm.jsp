<%-- 
    Document   : walletForm
    Created on : Jul 13, 2025, 12:13:00 AM
    Author     : trang
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="checkError" value="${requestScope.checkError}" />
<c:set var="message" value="${requestScope.message}" />
<c:set var="isEdit" value="${requestScope.isEdit}" />
<c:set var="keyword" value="${requestScope.keyword}" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Wallet Form</title>
    </head>
    <body>
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.roleID == 'AD'}">
                    <div class="form-container">
                        <form id="walletForm"
                              method="post"
                              data-controller="WalletController">

                            <input type="hidden" name="action" value="${isEdit ? 'updateWallet' : 'addWallet'}" />

                            <c:if test="${isEdit}">
                                <div class="form-group">
                                    <label for="userName">ID <span class="required">*</span></label>
                                    <input type="text" id="userName" name="userName" readonly value="${wallet.userName}" />
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label for="balance">Balance <span class="required">*</span></label>
                                <input type="number" id="balance" name="balance" required min="0" step="0.01"
                                       value="${wallet.balance}" />
                            </div>

                            <div class="button-group">
                                <input type="hidden" name="keyword" value="${keyword}" />
                                <input type="submit" value="${isEdit ? 'Update Wallet' : 'Add Wallet'}" />
                                <input type="reset" value="Reset" />
                            </div>
                        </form>

                        <!-- Error or success messages -->
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
